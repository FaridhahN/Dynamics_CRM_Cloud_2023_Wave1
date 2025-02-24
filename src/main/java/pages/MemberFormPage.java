package pages;


import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

//Test Case 7312:Add and update Primary contact to a Member account
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.text.TabableView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.util.SystemOutLogger;
import org.jsoup.nodes.Entities.EscapeMode;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aventstack.extentreports.Status;

import driver.Driver;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

public class MemberFormPage extends WebDriverServiceImpl {
	String attributevalue;
	String LOB;
	String Classificationbefore;
	String Classificationafter;
	String State;
	String topparent;
	String feeshare;
	ArrayList<String> crmList= new ArrayList<String>();

	//Enter account name
	public MemberFormPage typeAccountName(String accountName) throws InterruptedException {
		clearAllSuggestions();
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String executiondate = dateFormat.format(date);
		click(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")),"Account name");
		Thread.sleep(2000);
		System.out.println(accountName);
		type(((getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")))),accountName + "_" + executiondate,"Account name");
		return this;
	}

	//Verify the form type
	public MemberFormPage verifyFormType(String formtype) throws InterruptedException {
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='form-selector']")), formtype, "FormType");
		return this;
	}

	//Verify AccountType dropdown
	public MemberFormPage verifyAccounttypedropdown() {

		
		List<String> mylist = new ArrayList<String>();	

		click(getDriver().findElement(By.xpath("//button[@data-id='customertypecode.fieldControl-option-set-select']")),"Account Type");
		//loop through DOM and add dropdown values into mylist for comparison

		List<WebElement> options=getDriver().findElements(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div"));

		for(int i=1;i<=options.size();i++) {
			mylist.add(getDriver().findElement(By.xpath("(//div[contains(@id,'pa-option-set-component')]/div/div)["+i+"]")).getText());
		}

		/*
		 * Select AccountType= new Select(getDriver().findElement(By.xpath(
		 * "//*[@data-id='customertypecode.fieldControl-option-set-select']")));
		 * //Create temp Array List > add actual options from DOM for comparison
		 * List<WebElement> mylist =AccountType.getOptions();
		 */
		assertTrue(!(mylist.contains("Non-GPO Member")), "Non GPO Member Option");
		return this;
	}

	public MemberFormPage verifyTPDateCheck() throws InterruptedException {

		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		if(getDriver().findElements(By.xpath("//label[contains(text(),'County')]")).size()>0) {
			click(((getDriver().findElement(By.xpath("//label[contains(text(),'County')]")))), "County");
		}
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'State/Province')]")))), "State/Province");
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");

		WebElement ele =getDriver().findElement(By.xpath("//*[@data-id='ix_topparentrelationdate.fieldControl-date-time-input']"));
		ele.sendKeys(Keys.CONTROL, Keys.chord("a"));
		Thread.sleep(3000);
		ele.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		typeTPReason("test");
		clickSave();
		List<WebElement> popup=getDriver().findElements(By.xpath("//div[contains(@id,'popupContainer_innerPopup')]"));
		verifyElementisNotDisplayed(popup.size(), "Pop pup is not displayed");
		selectTopParentRelationDate("11/7/2001");
		return this;
	}
	//Verify the date calculation
	public MemberFormPage verifydateCalculation(String dpDate, String tpDate) throws InterruptedException {

		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "Street1");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		selectDirectParentRelationDate(dpDate);
		click((getDriver().findElement(By.xpath("//input[@aria-label='Other Phone']"))),"Other Phone");
		selectTopParentRelationDate(tpDate);
		return this;
	}

	//Navigate to AG DAte
	public MemberFormPage navigateToAGDate() throws InterruptedException {

		navigateToDoNotVerify();
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Ownership')]")))), "Ownership");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Stock Symbol')]")))), "Stock Symbol");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Exchange')]")))), "Exchange");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Is Payment Entity')]")))), "Is Payment Entity");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Payment Entity')]")))), "Payment Entity");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Corporate Rebate')]")))), "Corporate Rebate");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Corporate Rebate Fee Date' )]")))), "Corporate Rebate Fee Date");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Require Manual AG Assignment' )]")))), "Require Manual AG Assignment");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group' )]")))), "Affiliate Group");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group Effective Date' )]")))), "AG Effective Date");
		return this;
	}

	//Navigate to AG DAte
	public MemberFormPage navigateToAGDateMEF() {

		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Application Date')]")))), "Application Date");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Premier Start Date')]")))), "Premier Start Date");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Premier End Date')]")))), "Premier End Date");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Require Manual AG Assignment')]")))), "Require Manual AG Assignment");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group')]")))), "Affiliate Group");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group Effective Date' )]")))), "AG Effective Date");
		return this;
	}

	public MemberFormPage NavigateToSponsofromAGDAteMEF() throws InterruptedException {
		navigateToAGDateMEF();
		clickAndEsc(getDriver().findElement(By.xpath("//input[@aria-label='Date of Affiliate Group Effective Date']")), "AGDate");
		clickTab(15);

		return this;
	}


	//Verify the AG Effective date after the Field Update
	public MemberFormPage verifyAgDateAfterUpdate() throws InterruptedException {
		navigateToAGDate();
		String AGDatebeforeUpdate=getTextValueAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroupeffectivedate.fieldControl-date-time-input']")),"AG Effective Date");
		clickSystemTab();
		clickGeneralTab();
		navigateToAGDate();
		typeStreet1("Street");
		clickSave();
		navigateToAGDate();
		String AGDatebeforeAfter=getTextValueAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroupeffectivedate.fieldControl-date-time-input']")),"AG Effective Date");
		assertTrue(AGDatebeforeUpdate.contentEquals(AGDatebeforeAfter), "AG Effective Dates are different");
		return this;
	}

	//Verify Participation Type drop down
	public MemberFormPage verifyParticipationTypedropdown() {

		Select AccountType= new  Select(getDriver().findElement(By.xpath("//*[@data-id='ix_participationtype.fieldControl-option-set-select']")));		
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =AccountType.getOptions();		
		assertTrue(!(mylist.contains("Standard")), "Premier choice Option");
		return this;
	}

	//Verify Termination statusError message
	public MemberFormPage verifyTerminateStatusError() throws InterruptedException {
		verifyDisplayed(getDriver().findElement(By.xpath("//*[@data-id='dialogTitleText']")),"Error Message");
		verifyExactText(getDriver().findElement(By.xpath("//h2[@data-id='errorDialog_subtitle']")), Terminate_Status_Message, Terminate_Status_Message);
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"Ok Button");
		return this;
	}
	//verify if CRM number is auto generated
	public MemberFormPage verifyCRMNumberIsDisplayed() throws InterruptedException {
		Thread.sleep(5000);
		CRMNumber = getAttribute(getDriver().findElement(By.xpath("//*[@data-id='accountnumber.fieldControl-text-box-text']")),"value","CRM Number");
		System.out.println("crmnumber"+CRMNumber);
		if (CRMNumber==null) {
			setReport().log(Status.FAIL, "Value is blank in CRM Number"	,screenshotCapture());
			Driver.failCount++;		
		}
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='accountnumber.fieldControl-text-box-text']")), "CRM Account Number");
		try {
			DataInputProvider.setCellData(CRMNumber.toString(), Driver.iTestCaseRowNumDriver, "CRMNumber",Driver.properties.getProperty("DriverSheetName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	//verify if CRM number is auto generated
	public MemberFormPage verifyCRMNumberIsDisplayedMEF() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_hiscirostername.fieldControl-text-box-text']")),"Accouint nmber 2");
		click(getDriver().findElement(By.xpath("//input[@data-id='address1_name.fieldControl-text-box-text']")),"Address name");
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click((getDriver().findElement(By.xpath("//input[@data-id='address1_line3.fieldControl-text-box-text']"))),"Address 3");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'State/Province')]")))), "State/Province");


		Thread.sleep(5000);
		CRMNumber = getAttribute(getDriver().findElement(By.xpath("//*[@data-id='accountnumber.fieldControl-text-box-text']")),"value","CRM Number");
		System.out.println("crmnumber"+CRMNumber);
		if (CRMNumber==null) {
			setReport().log(Status.FAIL, "Value is blank in CRM Number"	,screenshotCapture());
			Driver.failCount++;		
		}
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='accountnumber.fieldControl-text-box-text']")), "CRM Account Number");
		try {
			DataInputProvider.setCellData(CRMNumber.toString(), Driver.iTestCaseRowNumDriver, "CRMNumber",Driver.properties.getProperty("DriverSheetName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public MemberFormPage verifyCRMNumberIsDisplayedForChilAccount(int childAcccount) {
		String sCRMNumber = getAttribute(getDriver().findElement(By.xpath("//*[@data-id='accountnumber.fieldControl-text-box-text']")),"value","CRM Number");
		try {
			DataInputProvider.setCellData(sCRMNumber.toString(), Driver.iTestCaseRowNumDriver, "CRMNumberChildAccount"+childAcccount,Driver.properties.getProperty("DriverSheetName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	//	//Click on Save button
	//	public MemberFormPage clickSave() throws InterruptedException {
	//		//click(getDriver().findElement(By.xpath("//*[@data-id='edit-form-save-btn']")),"Save");
	//		click(getDriver().findElement(By.xpath("//*[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Save']")),"Save");
	//		Thread.sleep(5000);
	//		try {
	//			List<WebElement> ignoreMessage=getDriver().findElements(By.xpath("//*[text()='Ignore and save']"));
	//			if(ignoreMessage.size()>0)
	//				click(getDriver().findElement(By.xpath("//*[text()='Ignore and save']")),"Ignore and Save");	
	//		} catch (Exception e) {
	//			e.getMessage();
	//		}
	//		Thread.sleep(7000);
	//		return this;	
	//	}

	public MemberFormPage clicksaveButton() {
		click(getDriver().findElement(By.xpath("//*[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Save']")),"Save");

		return this;
	}


	public MemberFormPage verifyDuplicateMessage() {

		Assert.assertTrue(getDriver().findElements(By.xpath("//*[text()='Ignore and save']")).size()>0);
		click(getDriver().findElement(By.xpath("//*[text()='Ignore and save']")),"Ignore and Save");
		return this;
	}

	public MemberFormPage clickSave() throws InterruptedException {

		//click(getDriver().findElement(By.xpath("//*[@data-id='edit-form-save-btn']")),"Save");

		click(getDriver().findElement(By.xpath("//*[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Save']")),"Save");


		List<WebElement> ignoreMessage=getDriver().findElements(By.xpath("//*[text()='Ignore and save']"));

		if(ignoreMessage.size()>0)

			click(getDriver().findElement(By.xpath("//*[text()='Ignore and save']")),"Ignore and Save"); 

		try {
			if(getDriver().findElements(By.xpath("//div[@data-id='confirmdialog']")).size()>0){

				if((getDriver().findElements(By.xpath("//h1[@title='DP change confirmation']"))).size()==0) {
					if(getDriver().findElements(By.xpath("//button[@aria-label='Continue']")).size()>0)
						click(getDriver().findElement(By.xpath("//button[@aria-label='Continue']")),"Continue Button");
				}
			}
		} catch (Exception e) {

			e.getMessage();

		}

		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(180));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Save']")));

		return this;    

	}

	//Set the Do not verify Address
	public MemberFormPage selectDonotVerify(String option) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Do Not Verify Address']")), option, "Do Not Verify Select dropdown");
		return this;
	}

	//Click NY Information Tab
	public MemberFormPage clickNyInformationTab() {

		click(getDriver().findElement(By.xpath("//li[@aria-label='NY INFORMATION']")),"NY Information");

		return this;
	}

	//Verify NY information tab is not displayed
	public MemberFormPage verifyNyInformationTabisNotDisplayed() {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//li[@aria-label='NY INFORMATION']")).size(), "NY information Tab");
		return this;
	}

	//Verify Contact tab is not displayed
	public MemberFormPage verifyContactTabisNotDisplayed() {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//li[@aria-label='CONTACT']")).size(), "Contact Tab");
		return this;
	}

	//VErify Contact under Related tab
	public MemberFormPage verifyGeneralTab() throws InterruptedException {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//*[@title='GENERAL DEMOGRAPHIC']")).size(), "General Demographic Tab");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//li[@title='General']")).size(), "General Tab");
		return this;
	}

	//VErify Contact under Related tab
	public MemberFormPage verifyContactOption() throws InterruptedException {
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(2000);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("(//span[text()='Contacts'])[2]")).size(), "Contact Option");
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(2000);
		return this;
	}

	//VErify Contact under Related tab
	public MemberFormPage verifyActivitiesOption() throws InterruptedException {
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(2000);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("(//*[text()='Activities'])[2]")).size(), "Ativities Option");
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(2000);
		return this;
	}
	//Verify LOB required Error message
	public MemberFormPage verifyLOBRequiredErrorMessage(String errorMessage) throws InterruptedException {
		Thread.sleep(2000);
		verifyDisplayed(getDriver().findElement(By.xpath("//h2[contains(@aria-label,'"+errorMessage+"')]")),"LOB Required");
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"Ok Button");
		return this;

	}

	//Verify LOB required Error message
	public MemberFormPage verifyErrorMessage(String errorMessage) throws InterruptedException {

		Thread.sleep(5000);
		verifyDisplayed(getDriver().findElement(By.xpath("//h2[contains(@aria-label,'"+errorMessage+"')]")),errorMessage);
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"Ok Button");

		return this;

	}


	//Verify LOB required Error message
	public MemberFormPage verifyErrorMessage_contains_Singlequote(String errorMessage) throws InterruptedException {
		Thread.sleep(6000);

		System.out.println("//h2[@aria-label=\""+errorMessage+"\"]");

		verifyDisplayed(getDriver().findElement(By.xpath("//h2[@aria-label=\""+errorMessage+"\"]")),"Business Process Error");
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"OK Button");
		return this;

	}



	//Verify Member permission error message
	public MemberFormPage verifyMemberPermissionError(String errorMessage) throws InterruptedException {

		Thread.sleep(5000);
		System.out.println("//h2[contains(@aria-label,'"+errorMessage+"')]");
		verifyDisplayed(getDriver().findElement(By.xpath("//h2[contains(@aria-label,'"+errorMessage+"')]")),"LOB Required");
		click(getDriver().findElement(By.xpath("//span[contains(@id,'okButtonText')]")),"Ok Button");
		return this;

	}

	//Verify LOB required Error message
	public MemberFormPage verifyLOBRequiredForMemberShipErrorMessage(String errorMessage) throws InterruptedException {

		Thread.sleep(3000);
		verifyDisplayed(getDriver().findElement(By.xpath("//h2[contains(@aria-label,'"+errorMessage+"')]")),"Deactivate Error");
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"Ok Button");
		return this;

	}

	//Select Contract Attachments under Related tab
	public ContractAttachmentPage selectRelatedContractAttachments() throws InterruptedException {
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//div[@data-id='form-tab-relatedEntity-nav_ix_account_ix_contractattachment']")), "Contract Attachments");
		Thread.sleep(5000);
		return new ContractAttachmentPage();
	}

	//Select Contract Volume under Related tab
	public ContractVolume selectRelatedContractVolume() throws InterruptedException {
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Contract Volume')]")), "Contract Volume");
		Thread.sleep(2000);
		return new ContractVolume();
	}


	//select Account Type
	public MemberFormPage verifyselectAccountTypeIsReadOnly(String accountType) throws InterruptedException{
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//div[@aria-label='Secured Account Type']")).size(), "accountType");
		//Application Update below code is not working
		/*
		 * click(getDriver().findElement(By.xpath(
		 * "//*[@data-id='form-sectionHeader-MembershipProviderConfiguration']"))
		 * ,"Account Type");
		 * selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath(
		 * "//*[@data-id='customertypecode.fieldControl-option-set-select']")))),
		 * accountType,"Account type"); Thread.sleep(2000);
		 * verifyTextDoesNotMatchTitleAttribute(getDriver().findElement(By.xpath(
		 * "//*[@data-id='customertypecode.fieldControl-option-set-select']")),
		 * accountType,"Account type");
		 */	return this;
	}


	public LoginPage clickLogout() {

		click(getDriver().findElement(By.xpath("//*[@id='mectrl_headerPicture']")),"User Name button");
		click(getDriver().findElement(By.xpath("//button[contains(text(),'Sign out')]")),"Sign Out button");
		if(getDriver().findElements(By.xpath("//span[contains(text(),'Discard changes')]")).size()>0) {
			click(getDriver().findElement(By.xpath("//span[contains(text(),'Discard changes')]")),"Discard button");
		}

		return new LoginPage();


	}
	//select Account Type
	public MemberFormPage selectAccountType(String accountType) throws InterruptedException{
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//*[@title='TOP PARENT']")),"TOP Parent");//Scroll down to make the record status field visible
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		if(getDriver().findElements(By.xpath("//label[contains(text(),'County')]")).size()>0){
			click(((getDriver().findElement(By.xpath("//label[contains(text(),'County')]")))), "County");
		}
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'State/Province')]")))), "State/Province");
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		clickTab(3);
		clickAndTab(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");		
		click((getDriver().findElement(By.xpath("//input[@aria-label='Other Phone']"))),"Other Phone");
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Receive Direct Mail')]"))),"Direct Mail");
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Siebel Address ID')]"))),"Do not Verify Address");
		if(getDriver().findElements(By.xpath("//*[@title='Sponsor']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Sponsor']")),"Sponsor");//Scroll down to make the record status field visible
		}
		click(getDriver().findElement(By.xpath("//*[@title='CORPORATE PARENT']")),"CP");//Scroll down to make the record status field visible
		click(getDriver().findElement(By.xpath("//*[@title='FOOD SERVICE PARENT']")),"Food Service");//Scroll down to make the record status field visible
		click(getDriver().findElement(By.xpath("//*[@title='FBO']")),"FBO");//Scroll down to make the record status field visible
		navigatetoNAICS();
		naivagateToReferredByFromNACS();
		click(getDriver().findElement(By.xpath("//*[@data-id='form-sectionHeader-MembershipProviderConfiguration']")),"Membership Provider Configuration Header");
		click(getDriver().findElement(By.xpath("//button[@data-id='customertypecode.fieldControl-option-set-select']")),"Account Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component-')]/div/div[contains(text(),'"+accountType+"')]")),"Account Type");


		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='customertypecode.fieldControl-option-set-select']")),accountType,"Account type"); 
		return this;
	}

	//Select Class of trade
	public MemberFormPage selectClassOfTrade(String classOfTrade) throws InterruptedException, AWTException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_classoftradedetail.fieldControl-LookupResultsDropdown_ix_classoftradedetail_InputSearch']")),"Class of Trade");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_classoftradedetail.fieldControl-LookupResultsDropdown_ix_classoftradedetail_textInputBox_with_filter_new']")))),classOfTrade,"Class of Trade");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[contains(@id,'ix_classoftradedetail.fieldControl-ix_parent')]")),"Class of Trade");
		Thread.sleep(3000);
		return this;
	}


	//Select Business Classification
	public MemberFormPage selectBusinessClassification(String classOfTrade) throws InterruptedException, AWTException {
		click(getDriver().findElement(By.xpath("//div[@data-id='ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification_InputSearch']")),"Business Classification");
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification_textInputBox_with_filter_new']")))),classOfTrade,"Business Classification");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//li[contains(@id,'ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification')]")),"Business Classification");
		return this;
	}

	//verify LOB
	public MemberFormPage verifyLOBTable() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label//div[contains(text(),'Line of Business')]")).size(), "LOB");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label//div[contains(text(),'Classification Type')]")).size(), "Classification Type");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label//div[contains(text(),'Reward/Rebate Option')]")).size(), "Rewared / Rebate Option");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label//div[contains(text(),'Start Date')]")).size(), "Start Date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label//div[contains(text(),'End Date')]")).size(), "End Date");
		return this;
	}

	//Select Class of trade
	public MemberFormPage clearClassOfTrade() throws InterruptedException, AWTException {
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='ix_classoftradedetail.fieldControl-LookupResultsDropdown_ix_classoftradedetail_SelectedRecordList']"))).perform();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[contains(@id,'cancelButton')]")),"Delete"); 
		return this;
	}

	//Select Class of trade
	public MemberFormPage clearBusinessClassification() throws InterruptedException, AWTException {
		// Wave 2023

		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification_SelectedRecordList']"))).build().perform();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification_selected_tag_delete']//*[contains(@id,'cancelButton')]")),"X mark on Business Classfication"); 
		return this;
	}


	//Verify business classification is auto populated
	public MemberFormPage verifyBusinessClassification(String verifyBusinessClassification) throws InterruptedException {
		Thread.sleep(2000);
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification_SelectedRecordList']"))).build().perform();
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification_selected_tag_text']")),verifyBusinessClassification,"Business Classification");
		return this;
	}

	//Verify business classification is auto populated
	public MemberFormPage clearAndSelectBusinessClassification(String businessClassification) throws InterruptedException {
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification_selected_tag_text']"))).perform();
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification_selected_tag_delete']")),"Delete"); 
		Thread.sleep(3000);
		click(((getDriver().findElement(By.xpath("//*[@data-id='ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification_InputSearch']")))),"Business Classification");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification_textInputBox_with_filter_new']")))),businessClassification,"Business Classification");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[contains(@id,'ix_businessclassification.fieldControl-ix_name0_0_0')]")),"Business Classification Option");
		return this;
	}

	//verify default account status is active
	public MemberFormPage verifyDefaultAccountStatus() throws InterruptedException {
		Thread.sleep(3000);
		navigateToApplicationDate();
		verifyExactTextWithTitleAttribute((getDriver().findElement(By.xpath("//*[@data-id='ix_accountstatus.fieldControl-option-set-select']"))),"Active"," 'Account Status' on Member Form");
		return this;
	}

	//verify default account status is active
	public MemberFormPage verifyDefaultAccountStatusinMEF() throws InterruptedException {
		Thread.sleep(3000);
		verifyExactTextWithTitleAttribute((getDriver().findElement(By.xpath("//*[@data-id='ix_accountstatus.fieldControl-option-set-select']"))),"Active"," 'Account Status' on Member Form");
		return this;
	}

	//Choose application start date
	public MemberFormPage chooseApplicationDate(String applicationDate) throws InterruptedException {
		Thread.sleep(15000);
		WebElement element = getDriver().findElement(By.xpath("//label[contains(text(),'Account Status')]"));
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		je.executeScript("arguments[0].scrollIntoView();",element);

		click(getDriver().findElement(By.xpath("//label[contains(text(),'Account Status')]")),"Account Status");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Application Date')]")),"Application Date");
		Thread.sleep(5000);
		type(((getDriver().findElement(By.xpath("//input[@aria-label='Date of Application Date']")))),applicationDate, "Application Start Date");
		return this;

	}

	public MemberFormPage navigateToApplicationDate() {


		click(getDriver().findElement(By.xpath("//label[contains(text(),'Primary Contact')]")),"Prmiary Contact");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Group')]")),"Group of Trade");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Class of Trade')]")),"Class of Trade");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Facility')]")),"Facility");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Business')]")),"Business Classification");
		if(getDriver().findElements(By.xpath("//label[contains(text(),'Premier Owner')]")).size()>0) {
			click(getDriver().findElement(By.xpath("//label[contains(text(),'Premier Owner')]")),"Premier owner");
		}
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Account Status')]")),"Account Status");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Application Date')]")),"Application Date");




		return this;
	}

	//Choose application start date
	public MemberFormPage chooseApplicationDateWithoutScrolling(String applicationDate) throws InterruptedException {
		Thread.sleep(15000);
		WebElement element = getDriver().findElement(By.xpath("//label[contains(text(),'Account Status')]"));
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		je.executeScript("arguments[0].scrollIntoView();",element);

		click(getDriver().findElement(By.xpath("//label[contains(text(),'Account Status')]")),"Account Status");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Application Date')]")),"Application Date");
		Thread.sleep(5000);
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_applicationstartdate.fieldControl._datecontrol-date-container']//input")))),applicationDate, "Application Start Date");
		return this;

	}


	//Choose application start date
	public MemberFormPage chooseApplicationStartDate(String applicationDate) throws InterruptedException {

		click(getDriver().findElement(By.xpath("//label[contains(text(),'Account Status')]")),"Application Status");
		Thread.sleep(3000);
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_applicationstartdate.fieldControl._datecontrol-date-container']//input")))),applicationDate, "Application Start Date");
		return this;
	}


	//Change CAMS flag
	public MemberFormPage changeCAMSFlagAsYes() {
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		WebElement element = getDriver().findElement(By.xpath("//label[contains(text(),'Group')]"));
		je.executeScript("arguments[0].scrollIntoView(true);",element);

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_camsflag.fieldControl-option-set-select']")),"CAMS Flag");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'Yes')]")),"CAMS Flag");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_camsflag.fieldControl-checkbox-select']")),"Yes","CAMS Flag"); 
		return this;
	}

	public MemberFormPage changeCAMSFlagAsNo() {
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		WebElement element = getDriver().findElement(By.xpath("//label[contains(text(),'Group')]"));
		je.executeScript("arguments[0].scrollIntoView(true);",element);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_camsflag.fieldControl-option-set-select']")),"CAMS Flag");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'No')]")),"CAMS Flag");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_camsflag.fieldControl-checkbox-select']")),"No","CAMS Flag"); 
		return this;
	}

	//Navigate to Participation type
	public MemberFormPage navigateToParticipationType() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")),"Account name");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Premier End Date')]")),"Premier End Date title");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Primary Contact')]")),"Primary Contact title");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Street 2')]")),"Street 2 title");
		click(getDriver().findElement(By.xpath("//input[@data-id='address1_line2.fieldControl-text-box-text']")),"Street 2 Textbox");
		clickTab(3);
		click(getDriver().findElement(By.xpath("//label[contains(text(),'County')]")),"County title");
		click(getDriver().findElement(By.xpath("//input[@data-id='address1_county.fieldControl-text-box-text']")),"County title");
		clickTab(4);
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Override Name')]")),"Override Name title");
		return this;
	}

	//Select Participation type
	public MemberFormPage selectParticipationType(String participationType) throws InterruptedException {
		//Wave 2023 update to scroll
		//		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		//		WebElement element = getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroup.fieldControl-LookupResultsDropdown_ix_affiliategroup_textInputBox_with_filter_new']"));
		//		je.executeScript("arguments[0].scrollIntoView(true);",element);f

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_participationtype.fieldControl-option-set-select']")),"Participation Type");

		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+participationType+"')]")),"Participation Type Option");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_participationtype.fieldControl-option-set-select']")),participationType,"Participation type");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_participationtype.fieldControl-option-set-select']")),participationType,"Participation type"); 
		return this;
	}

	//Select Participation type on Member Entry Form
	public MemberFormPage selectParticipationTypeMEF(String participationType) throws InterruptedException {
		//Wave 2023 update method for MEF
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_participationtype.fieldControl-option-set-select']")),"Participation Type");

		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+participationType+"')]")),"Participation Type Option");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_participationtype.fieldControl-option-set-select']")),participationType,"Participation type");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_participationtype.fieldControl-option-set-select']")),participationType,"Participation type");return this;
	}

	//select Direct parent
	public MemberFormPage selectDirectParent(String directParent) throws InterruptedException {
		if(getDriver().findElements(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")).size()>0) {
			click(getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")),"Direct Parent");
		}
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")))),directParent,"Direct Parent");
		//Thread.sleep(120000);
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+directParent+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+directParent+"')]")),"Direct Parent");

		/*
		 * if(getDriver().findElements(By.xpath(
		 * "//*[contains(@id,'parentaccountid.fieldControl-accountnumber')]//span[contains(text(),'"
		 * +directParent+"')]")).size()>0) { click(getDriver().findElement(By.xpath(
		 * "//*[contains(@id,'parentaccountid.fieldControl-accountnumber')]"))
		 * ,"Direct Parent"); } //2000033926 else {
		 * click(getDriver().findElement(By.xpath(
		 * "//*[contains(@data-id,'parentaccountid.fieldControl-ix_premierein')]//span[contains(text(),'"
		 * 
		 * //*[contains(@id,'parentaccountid.fieldControl-ix_premierein')]
		 * +directParent+"')]")),"Click Entity code"); }
		 */
		return this;
	}


	//select Direct parent
	public MemberFormPage selectDirectParentWithoutEnitiy(String directParent) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")),"Direct Parent");
		Thread.sleep(6000);
		type(((getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")))),directParent,"Direct Parent");
		Thread.sleep(5000);
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("(//div[contains(@id,'parentaccountid.fieldControl-LookupResultsDropdown')])[7]")),"Direct Parent");
		return this;
	}

	//Select DPR as Managed
	public MemberFormPage selectDirectParentRelationManaged() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_parentrelationship.fieldControl-option-set-select']")),"Participation Type");

		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'Managed')]")),"Participation Type Option");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_participationtype.fieldControl-option-set-select']")),participationType,"Participation type");
		Thread.sleep(2000);

		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_parentrelationship.fieldControl-option-set-select']")))),"Managed","Direct Parent Relation");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_parentrelationship.fieldControl-option-set-select']")),"Managed","Direct Parent Relation"); 		
		return this;
	}

	//Select DPRD
	public MemberFormPage selectDirectParentRelationDate(String directParentRelationDate) throws InterruptedException {
		Thread.sleep(5000);
		type(((getDriver().findElement(By.xpath("//input[@aria-label='Date of Direct Parent Relation Date']")))),directParentRelationDate, "Direct Parent Relation Date");
		return this;
	}

	//Click new on accounts page
	public NewAccountPage clickNewOnAccountsPage() throws InterruptedException { 
		click(getDriver().findElement(By.xpath("//button[@aria-label='New']")),"New");
		Thread.sleep(3000);
		return new NewAccountPage();
	}


	//clear Suggestion
	public MemberFormPage clearAllSuggestions() {
		List<WebElement> clearsugestion=getDriver().findElements(By.xpath("//button[@aria-label='Clear all suggestions']/span"));
		if(clearsugestion.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Clear all suggestions']/span")),"Clear sugesstion");
		}

		return this;
	}
	//Store the values

	public MemberFormPage getDPData() throws InterruptedException {

		changeMemberForm();
		//commenting since prod does not have save option for CRM02
		//	clickSave();

		String name=getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")).getAttribute("title");

		clearAllSuggestions();
		Dpdata.put("DP_Name",name );
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_hiscirostername.fieldControl-text-box-text']")),"Account Name 2");
		clickTab(2);
		click(getDriver().findElement(By.xpath("//input[@aria-label='Store/Location #']")),"Store Location");
		clickTab(7);
		click(getDriver().findElement(By.xpath("//label[contains(text(),'DP Exception Reason')]")),"DP exception Reason");
		Thread.sleep(5000);
		List<WebElement> entitycode= getDriver().findElements(By.xpath("//div[@data-id='TPQuickViewForm']//label"));
		if(entitycode.size()>0) {
			click(getDriver().findElement(By.xpath("//div[@data-id='TPQuickViewForm']//label")),"Entity Code");
		}else {
			click(getDriver().findElement(By.xpath("//label[contains(text(),'Is Top Parent')]")),"Is Top Parent");
		}
		Thread.sleep(5000);
		String isTP=getDriver().findElement(By.xpath("//button[@aria-label='Is Top Parent']")).getAttribute("value");
		if(isTP.equalsIgnoreCase("yes")) {
			Dpdata.put("TopParent_Name",name );	
		}else {
			Dpdata.put("TopParent_Name", getDriver().findElement(By.xpath("//div[@data-id='ix_topparent.fieldControl-LookupResultsDropdown_ix_topparent_selected_tag_text']")).getAttribute("title"));
		}
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		clickTab(10);
		List<WebElement> corporateHeader= getDriver().findElements(By.xpath("//*[@data-id='form-sectionHeader-SUMMARY_TAB_section_18']"));
		if(corporateHeader.size()>0) {

			click(getDriver().findElement(By.xpath("//*[@data-id='form-sectionHeader-SUMMARY_TAB_section_18']")),"Corporate Account");
		}
		Dpdata.put("IsCorporate", getDriver().findElement(By.xpath("//input[@aria-label='Is Corporate Account']")).getAttribute("value"));

		if(Dpdata.get("IsCorporate").equalsIgnoreCase("No")) {

			Dpdata.put("CorporateName", getDriver().findElement(By.xpath("//div[@data-id='ix_corporateparentname.fieldControl-LookupResultsDropdown_ix_corporateparentname_selected_tag_text']")).getAttribute("title"));
			click(getDriver().findElement(By.xpath("//div[@data-id='CPEntityCode.ix_premierein-FieldSectionItemContainer']")),"CP entity");

		}else {
			Dpdata.put("IsCorporate", "No");
			Dpdata.put("CorporateName",name);
		}
		click(getDriver().findElement(By.xpath("//h2[@title='FOOD SERVICE PARENT']")),"Food Service Grid Title");

		Dpdata.put("isFoodService", getDriver().findElement(By.xpath("//input[@aria-label='Is Food Service Parent']")).getAttribute("value"));

		if(Dpdata.get("isFoodService").equalsIgnoreCase("No")) {

			Dpdata.put("FoodServiceName", getDriver().findElement(By.xpath("//div[@data-id='ix_foodserviceparentname.fieldControl-LookupResultsDropdown_ix_foodserviceparentname_selected_tag_text']")).getAttribute("title"));

		}else {
			Dpdata.put("isFoodService", "No");
			Dpdata.put("FoodServiceName",name);
		}

		Dpdata.put("isSponsor", getDriver().findElement(By.xpath("//button[@data-id='ix_issponsor.fieldControl-option-set-select']")).getAttribute("title"));

		if(Dpdata.get("isSponsor").equalsIgnoreCase("no")) {

			Dpdata.put("SponsorName", getDriver().findElement(By.xpath("//*[@data-id='ix_sponsor.fieldControl-LookupResultsDropdown_ix_sponsor_selected_tag_text']")).getAttribute("title"));

		}else {
			Dpdata.put("isSponsor", "No");
			Dpdata.put("SponsorName",name);
		}

		click(getDriver().findElement(By.xpath("//h2[@title='FOOD SERVICE PARENT']")),"Food Service");
		click(getDriver().findElement(By.xpath("//h2[contains(text(),'FBO')]")),"FBO");

		Dpdata.put("IsFBO", getDriver().findElement(By.xpath("//input[@aria-label='Is FBO']")).getAttribute("value"));

		if(!(getDriver().findElement(By.xpath("//input[@aria-label='Is FBO']")).getAttribute("value").equalsIgnoreCase("yes"))){
			Dpdata.put("FBO", getDriver().findElement(By.xpath("//*[@data-id='ix_fbo.fieldControl-LookupResultsDropdown_ix_fbo_selected_tag_text']")).getAttribute("title"));

			Dpdata.put("FBORD", getDriver().findElement(By.xpath("//input[@aria-label='Date of FBO Relation Date']")).getAttribute("value"));

		}else {
			Dpdata.put("IsFBO", "No");
			Dpdata.put("FBO",name);

			Dpdata.put("FBORD", getDriver().findElement(By.xpath("//input[@aria-label='Date of FBO Relation Date']")).getAttribute("value"));

		}

		return this;
	}

	//Click Related and Contacts
	public MemberFormPage clickRelatedContacts() throws InterruptedException   {
		Thread.sleep(5000);

		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//span[text()='Contacts'])[2]")),"Contacts");
		Thread.sleep(2000);
		return this;
	}


	//Click Related and Contacts
	public ContactsPage NavigateToContactsPage() throws InterruptedException   {
		Thread.sleep(5000);

		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//span[text()='Contacts'])[2]")),"Contacts");
		Thread.sleep(2000);
		return new ContactsPage() ;
	}

	//Select the first contact details

	public ContactsPage doubleclickcontact() throws InterruptedException {
		Thread.sleep(3000);
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("(//div[@aria-label=Press SPACE to select this row.']//label)[3]"))).doubleClick().build().perform();
		return new ContactsPage() ;


	}

	//Select the first contact details without end date

	public ContactsPage doubleclickcontactwithoutEndDate() throws InterruptedException {
		Thread.sleep(3000);
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//div[@col-id='ix_contactenddate']//label[@aria-label='']"))).doubleClick().build().perform();
		return new ContactsPage() ;


	}


	// Click Contact Account Association from Related header field
	public ContactsPage clickCAAFromRelated() throws InterruptedException {
		Thread.sleep(2000);

		List<WebElement> ContactAccountAssociationTab=getDriver().findElements(By.xpath("//li[contains(text(),'Contact Account Associations')]"));
		if(ContactAccountAssociationTab.size()>0) {
			click(getDriver().findElement(By.xpath("//li[contains(text(),'Contact Account Associations')]")),"CAA");
		}else {
			if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
				click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
			}else {
				click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
			}
			Thread.sleep(3000);
			click(getDriver().findElement(By.xpath("//*[contains(text(),'Contact Account Associations')]")),
					"Contact Account Associations");
			Thread.sleep(2000);
		}
		return new ContactsPage() ;
	}


	//NY Information Tab
	public MemberFormPage clickNYInformationTab() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//li[text()='NY INFORMATION']")),"NY Information");	
		Thread.sleep(2000);				
		return this;
	}

	//Click Related and Activities
	public MemberFormPage clickRelatedActivities() throws InterruptedException   {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//span[text()='Activities'])[2]")),"Activities");
		Thread.sleep(2000);
		return this;
	}

	public MemberFormPage verifyRelatedOptionisSorted() throws InterruptedException {
		clickRelatedButton();
		ArrayList<String> relatedoptions= new ArrayList<String> ();
		List<WebElement>listOfOption=getDriver().findElements(By.xpath("//div[@data-id='related_area_Related - Common']/following-sibling::div[@role='menuitem']/span[contains(@data-id,'label')]"));
		for(int i=1;i<listOfOption.size();i++) {
			relatedoptions.add(getDriver().findElement(By.xpath("(//div[@data-id='related_area_Related - Common']/following-sibling::div[@role='menuitem']/span[contains(@data-id,'label')])["+i+"]")).getText());
		}
		ArrayList<String> optionbeforeSort=new ArrayList<String> ();

		optionbeforeSort.addAll(relatedoptions);
		for(String option: optionbeforeSort) {
			System.out.println(option);
		}
		Collections.sort(relatedoptions);
		Assert.assertTrue(relatedoptions.equals(optionbeforeSort));
		for(String option: relatedoptions) {
			System.out.println(option);
		}
		return this;
	}

	public MemberFormPage verifyRelatedOption(String option, boolean isrequired) throws InterruptedException {
		clickRelatedButton();
		ArrayList<String> relatedoptions= new ArrayList<String> ();
		List<WebElement>listOfOption=getDriver().findElements(By.xpath("//div[@data-id='related_area_Related - Common']/following-sibling::div[@role='menuitem']/span[contains(@data-id,'label')]"));
		for(int i=1;i<listOfOption.size();i++) {
			relatedoptions.add(getDriver().findElement(By.xpath("(//div[@data-id='related_area_Related - Common']/following-sibling::div[@role='menuitem']/span[contains(@data-id,'label')])["+i+"]")).getText());
		}

		//div[@role="menuitem"]/span[contains(@data-id,'label')]
		if(isrequired) {
			Assert.assertTrue(relatedoptions.contains(option));
		}else {
			Assert.assertTrue(!(relatedoptions.contains(option)));
		}
		return this;
	}




	//Click Related button
	public MemberFormPage clickRelatedButton() throws InterruptedException   {
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}		return this;
	}

	//Click New Activity- Task
	public MemberFormPage clickNewTaskActivity() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[contains(@title,'New Activity')]")),"New Activity");
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//span[text()='Task']")),"Task");
		Thread.sleep(2000);
		return this;
	}


	//Click New Activity- Fax
	public MemberFormPage clickNewFaxActivity() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[contains(@title,'New Activity')]")),"New Activity");
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//span[text()='Fax']")),"FAX");
		Thread.sleep(2000);
		return this;
	}


	//Click New Activity- Phone
	public MemberFormPage clickNewPhoneActivity() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[contains(@title,'New Activity')]")),"New Activity");
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//span[text()='Phone Call']")),"Phone Call Activity");
		Thread.sleep(2000);
		return this;
	}


	//Click New Activity- Email
	public MemberFormPage clickNewEmailActivity() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[contains(@title,'New Activity')]")),"New Activity");
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//span[text()='Email']")),"Email Activity");
		Thread.sleep(2000);
		return this;
	}


	//Click New Activity- Letter
	public MemberFormPage clickNewLetterActivity() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[contains(@title,'New Activity')]")),"New Activity");
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//span[text()='Letter']")),"Letter Activity");
		Thread.sleep(2000);
		return this;
	}


	//Click New Activity- 
	public MemberFormPage clickAppointmentActivity() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[contains(@title,'New Activity')]")),"New Activity");
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//span[text()='Appointment']")),"Appointment Activity");
		Thread.sleep(2000);
		return this;
	}


	//verify Autopopulated Data in new Task

	public MemberFormPage verfiyAutopopulatedFields() throws InterruptedException   {
		getTextValue(getDriver().findElement(By.xpath("//div[@data-id='regardingobjectid.fieldControl-LookupResultsDropdown_regardingobjectid_selected_tag_text']")),"Regarding Field");
		getTextValue(getDriver().findElement(By.xpath("//div[@data-id='ownerid.fieldControl-LookupResultsDropdown_ownerid_selected_tag_text']")),"Assigned To");
		System.out.println("Verified Auto populated fields");
		return this;
	}

	//verify Autopopulated Data in new Fax

	public MemberFormPage verfiyAutopopulatedFieldsFax() throws InterruptedException   {
		getTextValue(getDriver().findElement(By.xpath("//div[@data-id='regardingobjectid.fieldControl-LookupResultsDropdown_regardingobjectid_selected_tag_text']")),"Regarding Feidl");
		getTextValue(getDriver().findElement(By.xpath("//div[@data-id='ownerid.fieldControl-LookupResultsDropdown_ownerid_selected_tag_text']")),"Owner feild");
		getTextValue(getDriver().findElement(By.xpath("//div[@data-id='from.fieldControl-LookupResultsDropdown_from_selected_tag_text']")),"From feild");
		getTextValue(getDriver().findElement(By.xpath("//div[@data-id='to.fieldControl-LookupResultsDropdown_to_selected_tag_text']")),"To feild");
		return this;
	}

	//verify Autopopulated Data in Email

	public MemberFormPage verfiyAutopopulatedFieldsEmail() throws InterruptedException   {
		getTextValue(getDriver().findElement(By.xpath("//div[@data-id='regardingobjectid.fieldControl-LookupResultsDropdown_regardingobjectid_selected_tag_text']")),"Regarding Feidl");
		getTextValue(getDriver().findElement(By.xpath("//div[@data-id='from.fieldControl-LookupResultsDropdown_from_selected_tag_text']")),"From feild");
		return this;
	}

	//verify Autopopulated Data in new Appointment

	public MemberFormPage verfiyAutopopulatedFieldsAppointment() throws InterruptedException   {
		getTextValue(getDriver().findElement(By.xpath("//div[@data-id='regardingobjectid.fieldControl-LookupResultsDropdown_regardingobjectid_selected_tag_text']")),"Regarding Feidl");
		getTextValue(getDriver().findElement(By.xpath("//div[@data-id='requiredattendees.fieldControl-LookupResultsDropdown_requiredattendees_selected_tag_text']")),"Required feild");
		return this;
	}

	//verify the subject feild is null
	public MemberFormPage verifySubjectFieldisNull() {
		verifIsNullWithTitleAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")), "Subject");
		return this;

	}

	//Select Regarding 
	public MemberFormPage selectRagrding( String entityCode) throws InterruptedException, IOException   {
		click(getDriver().findElement(By.xpath("//input[@aria-label='Regarding, Lookup']")),"Click the REgarding text box");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Regarding, Lookup']")),entityCode, "Entity code");
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@data-id,'regardingobjectid.fieldControl-ix_premierein')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+entityCode+"')]")),"Direct Parent");
		return this;
	}

	//Select Information view
	public MemberFormPage selectInformationview() throws InterruptedException   {
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//button[@data-id='form-selector']")),"Dropdown Button");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//div[@data-text='Information']")),"Inforamtion Dropdown Button");
		Thread.sleep(4000);
		List<WebElement> element=getDriver().findElements(By.xpath("//button[@title='Discard changes']"));
		if(element.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@title='Discard changes']")),"Discard Changes");
		}
		Thread.sleep(5000);
		verifyIsDisplayed(getDriver().findElement(By.xpath("//label[contains(text(),'Quick Subject')]")));
		return this;
	}

	//Enter the Task Details
	public MemberFormPage EnterTaskDetails(String subject, String duedate, String duration, String taskdetails) throws InterruptedException, IOException   {
		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Due']")),duedate, "Due DAte");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Duration']")),duration,"Duration Dropdown");
		Actions a=new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//*[contains(text(),'"+duration+"')]"))).click().sendKeys(Keys.TAB).build().perform();
		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),taskdetails,"Task details");
		//Changed on 10/11/2023
		//click(getDriver().findElement(By.xpath("//button[@aria-label='Save (CTRL+S)']")),"Save button");
		List<WebElement> savebutton=getDriver().findElements(By.xpath("//button[@aria-label='Save and Close']"));
		if(savebutton.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Save and Close']")),"Save button");
		}else {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Save & Close']")),"Save button");
		}
		Thread.sleep(10000);
		//String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		//System.out.println(saveStatus);
		///assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}


	//Enter the Fax Details
	public MemberFormPage EnterFaxDetails(String subject, String duedate, String duration, String Faxnumber, String noofPages, String coverpage,String direction, String taskdetails) throws InterruptedException, IOException   {

		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Due']")),duedate, "Due DAte");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Duration']")),duration,"Duration Dropdown");
		Thread.sleep(200);
		Actions a=new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//*[contains(text(),'"+duration+"')]"))).click().build().perform();

		type(getDriver().findElement(By.xpath("//input[@aria-label='Fax Number']")),Faxnumber,"Fax Number");
		click(getDriver().findElement(By.xpath("//input[@aria-label='Number of Pages']")),"No of Pages");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Number of Pages']")),noofPages,"Number of Pages");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Cover Page']")),coverpage,"Cover Page");

		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//button[@aria-label='Direction']")))),direction,"Direction");

		click(getDriver().findElement(By.xpath("//button[@aria-label='Direction']")),"direction");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+direction+"')]")),"direction");

		click(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),"Task Details");
		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),taskdetails,"task details feild");

		click(getDriver().findElement(By.xpath("//*[contains(text(),'Save & Close')]")),"Save button");
		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}

	//Enter the Phone Details
	public MemberFormPage EnterFPhoneDetails(String subject, String duedate, String duration, String Phonenumber) throws InterruptedException, IOException   {

		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Due Date']")),duedate, "Due DAte");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Duration']")),duration,"Duration Dropdown");
		Thread.sleep(200);
		Actions a=new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//*[contains(text(),'"+duration+"')]"))).click().build().perform();
		type(getDriver().findElement(By.xpath("//input[@aria-label='Phone Number']")),Phonenumber,"Phone Number");
		click(getDriver().findElement(By.xpath("//button[@aria-label='Save and Close']")),"Save button");
		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}

	//Edit the Phone Details
	public MemberFormPage EditPhoneDetails(String subject) throws InterruptedException, IOException   {

		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");

		click(getDriver().findElement(By.xpath("//*[contains(text(),'Save & Close')]")),"Save button");
		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}


	//Enter the Letter Details
	public MemberFormPage EnterLetterDetails(String subject, String duedate, String duration, String letterdetails) throws InterruptedException, IOException   {

		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Due']")),duedate, "Due DAte");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Duration']")),duration,"Duration Dropdown");
		Thread.sleep(200);
		Actions a=new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//li[contains(text(),'"+duration+"')]"))).build().perform();

		click(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),"Tasks details");
		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),letterdetails,"task details feild");

		click(getDriver().findElement(By.xpath("//*[contains(text(),'Save & Close')]")),"Save button");
		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}


	//Enter the Appointment Details
	public MemberFormPage EnterAppointmentDetails(String subject,String letterdetails) throws InterruptedException, IOException   {

		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");

		click(getDriver().findElement(By.xpath("//button[@aria-label='Save and Close']")),"Save button");
		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}


	//Enter the Appointment Details
	public MemberFormPage EnterAppointmentDetailsWithQuickSubject(String subject,String letterdetails) throws InterruptedException, IOException   {

		selectInformationview();
		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//select[@aria-label='Quick Subject']")))),subject,"Quick Subject");
		click(((getDriver().findElement(By.xpath("//button[@aria-label='Quick Subject']")))),"subject");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+subject+"')]")),"Subject");
		
		click(getDriver().findElement(By.xpath("//button[@aria-label='Save & Close']")),"Save button");
		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}




	//click the Recurrence Meeting

	public MemberFormPage selectWeekdayForrecuurence() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> selectedDays=getDriver().findElements(By.xpath("//label[@aria-checked='true']"));
		if(selectedDays.size()>0) {
			for(int i=1;i<=selectedDays.size();i++) {
				click(getDriver().findElement(By.xpath("(//label[@aria-checked='true'])["+i+"]")),"Deselect the selected days");
			}
		}

		click(getDriver().findElement(By.xpath("//label[@aria-label='Monday' and @aria-checked='false']")),"Select Monday");
		click(getDriver().findElement(By.xpath("//label[@aria-label='Tuesday' and @aria-checked='false']")),"Select Tuesday");
		click(getDriver().findElement(By.xpath("//label[@aria-label='Wednesday' and @aria-checked='false']")),"Select Wedenesday");
		click(getDriver().findElement(By.xpath("//label[@aria-label='Thursday' and @aria-checked='false']")),"Select Thursday");
		click(getDriver().findElement(By.xpath("//label[@aria-label='Friday' and @aria-checked='false']")),"Select Friday");
		click(getDriver().findElement(By.xpath("//button[@aria-label='Set']")),"Click Set Button");
		Thread.sleep(5000);
		List<WebElement> Appointment=getDriver().findElements(By.xpath("//span[@data-id='entity_name_span' and contains(text(),'Recurring Appointment')]"));
		verifyElementisDisplayed(Appointment.size(), "Recurring Meeting");

		return this;
	}

	public MemberFormPage clickRecurringmeetingButton() {

		click(getDriver().findElement(By.xpath("//button//span[contains(text(),'Recurrence')]")),"Recurrence button");
		return this;

	}

	//Enter the Recurring Appointment Details
	public MemberFormPage EnterRecurringAppointmentDetails(String subject,String letterdetails) throws InterruptedException, IOException   {

		clickRecurringmeetingButton();
		selectWeekdayForrecuurence();
		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");

		for(int i=0;i<5;i++) {
			Actions a =new Actions(getDriver());
			a.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(3000);
		}
		click(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),"Tasks details");

		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),letterdetails,"task details feild");

		click(getDriver().findElement(By.xpath("//button[@aria-label='Save and Close']")),"Save button");
		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}

	//Enter the Letter Details With quick Subject
	public MemberFormPage EnterLetterDetailsWithQuickSubject(String subject, String duedate, String duration, String letterdetails) throws InterruptedException, IOException   {

		selectInformationview();

		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//select[@aria-label='Quick Subject']")))),subject,"Quick Subject");
		click(((getDriver().findElement(By.xpath("//button[@aria-label='Quick Subject']")))),"subject");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+subject+"')]")),"Subject");
		
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Due']")),duedate, "Due DAte");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Duration']")),duration,"Duration Dropdown");
		Thread.sleep(200);
		Actions a=new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//li[contains(text(),'"+duration+"')]"))).build().perform();

		click(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),"Tasks details");
		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),letterdetails,"task details feild");

		click(getDriver().findElement(By.xpath("//*[contains(text(),'Save & Close')]")),"Save button");
		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}


	//Enter the Phone Details with Quick Subject
	public MemberFormPage EnterFPhoneDetailsWithQuickSubject(String subject, String duedate, String duration, String Phonenumber) throws InterruptedException, IOException   {

		selectInformationview();
		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//select[@aria-label='Quick Subject']")))),subject,"Quick Subject");
		click(((getDriver().findElement(By.xpath("//button[@aria-label='Quick Subject']")))),"subject");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+subject+"')]")),"Subject");
		
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Save & Close')]")),"Save button");
		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}


	//Enter the Phone Details with Quick Subject
	public MemberFormPage EnterEmailDetails(String subject, String To) throws InterruptedException, IOException   {

		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='to.fieldControl-LookupResultsDropdown_to_selected_tag_text']"))).build().perform();
		a.moveToElement(getDriver().findElement(By.xpath("//span[contains(@id,'cancelButton')]"))).click().build().perform();

		type(getDriver().findElement(By.xpath("//input[@data-id='to.fieldControl-LookupResultsDropdown_to_textInputBox_with_filter_new']")),To,"Send To");
		Thread.sleep(4000);
		a.moveToElement(getDriver().findElement(By.xpath("//span[contains(@id,'to.fieldControl-fullname')]"))).click().build().perform();

		Thread.sleep(200);
		click(getDriver().findElement(By.xpath("//button//span[contains(text(),'Send')]")),"Send button");
		Thread.sleep(10000);
		return this;
	}

	//Enter the Fax Details with Quick Subject
	public MemberFormPage EnterFaxDetailswithQuickSubject(String subject, String duedate, String duration, String Faxnumber, String noofPages, String coverpage,String direction, String taskdetails) throws InterruptedException, IOException   {
		selectInformationview();
		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//select[@aria-label='Quick Subject']")))),subject,"Quick Subject");
		
		click(((getDriver().findElement(By.xpath("//button[@aria-label='Quick Subject']")))),"subject");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+subject+"')]")),"Subject");
		
		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Due']")),duedate, "Due DAte");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Duration']")),duration,"Duration Dropdown");
		Thread.sleep(200);
		Actions a=new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//li[contains(text(),'"+duration+"')]"))).build().perform();

		type(getDriver().findElement(By.xpath("//input[@aria-label='Fax Number']")),Faxnumber,"Fax Number");
		click(getDriver().findElement(By.xpath("//input[@aria-label='Number of Pages']")),"No of Pages");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Number of Pages']")),noofPages,"Number of Pages");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Cover Page']")),coverpage,"Cover Page");

		selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//select[@aria-label='Direction']")))),direction,"Direction");

		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),taskdetails,"task details feild");

		click(getDriver().findElement(By.xpath("//*[contains(text(),'Save & Close')]")),"Save button");
		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}

	//Enter the Task Details with QuickSubject
	public MemberFormPage EnterTaskDetailswithQuickSubject(String subject, String duedate, String duration, String taskdetails) throws InterruptedException, IOException   {

		selectInformationview();
		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//select[@aria-label='Quick Subject']")))),subject,"Quick Subject");
		
		click(((getDriver().findElement(By.xpath("//button[@aria-label='Quick Subject']")))),"subject");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+subject+"')]")),"Subject");
		
		
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Save & Close')]")),"Save button");
		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}

	//click back in the New Activity page
	public MemberFormPage clickGoBackTaskPage() {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(160));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'navigateBackButtontab-id')]")));

		click(getDriver().findElement(By.xpath("//button[contains(@id,'navigateBackButtontab-id')]")),"Back button");
		return this;
	}

	//Verify Open Activity Associated View
	public MemberFormPage verifyOpenActivityAssocView() throws InterruptedException   {
		verifyIsEnabled(getDriver().findElement(By.xpath("//span[text()='Open Activity Associated View']")), "Open Activity Associated View");
		Thread.sleep(2000);
		return this;
	}

	//Click and Enter Due date on Task Activity
	public MemberFormPage typeDueDate() throws InterruptedException   {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String due = dateFormat.format(date);
		//click(getDriver().findElement(By.xpath("//input[@data-id='scheduledend.fieldControl-date-time-input']")),"Down Arrow to fill due date on the right hand top corner");
		Thread.sleep(3000);
		type(getDriver().findElement(By.xpath("//input[@data-id='scheduledend.fieldControl-date-time-input']")),due,"Due");

		Thread.sleep(2000);
		return this;
	}

	//Enter Subject on Task Activity
	public MemberFormPage typeSubjectOnTask(String accountname) throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//input[@data-id='subject.fieldControl-text-box-text']")),"Subject Field on Task Activity");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='subject.fieldControl-text-box-text']")),accountname,"Subject");
		Thread.sleep(2000);
		return this;
	}

	//Verify added  Task's Subject value in Open Activity Associated View 
	public MemberFormPage verifyTaskSubjectOnOpenActivityAssocView(String accountname) throws InterruptedException   {
		Thread.sleep(4000);
		verifyExactAttribute(getDriver().findElement(By.xpath("//div[@col-id='subject']//a")),"aria-label", accountname, "Task Suject on Open Activity Associatged View");
		Thread.sleep(2000);
		return this;
	}


	public MemberFormPage selectATask(String subject) throws InterruptedException{

		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//a[contains(@aria-label,'"+subject+"')]"))).doubleClick().build().perform();
		Thread.sleep(3000);

		return this;
	}

	// Select and Mark Complete	a Open Task Activity			
	public MemberFormPage selectAndMarkComplete() throws InterruptedException   {
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//span[contains(@class,'RowSelectionCheckMarkSpan')]//i[@data-icon-name='CheckMark']"))).click().build().perform();
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//span[text()='Mark Complete']")),"Mark Complete");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//span[text()='Close Task']")),"Close Task");
		Thread.sleep(3000);
		return this;
	}



	//Enter Description on Task Activity
	public MemberFormPage typeDescriptionOnTask(String accountname) throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//textarea[@data-id='description.fieldControl-text-box-text']")),"Description Field on Task Activity");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//textarea[@data-id='description.fieldControl-text-box-text']")),accountname,"Description");
		Thread.sleep(2000);			
		return this;
	}


	//Click Save And Close on Activity			
	public MemberFormPage clickSaveAndCloseOnActivity() throws InterruptedException   {

		List<WebElement> closeButton=getDriver().findElements(By.xpath("//section//span[text()='Save & Close']"));
		if(closeButton.size()>0) {
			click(getDriver().findElement(By.xpath("//section//span[text()='Save & Close']")),"Save & Close on Task Activity");
		}
		else {
			click(getDriver().findElement(By.xpath("//section//span[text()='Save and Close']")),"Save & Close on Task Activity");
		}
		Thread.sleep(4000);
		return this;
	}

	//~~~~~~~~~~~~~~~~~~~END  Activity~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//Enter End Date in Account Numbers
	public MemberFormPage typeStaticEndDateInAccountNumbers(String accNumEndDate) {
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),"End Date"); 
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),"End Date");
		type(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),accNumEndDate,"End Date"); 
		return this;
	}

	//Clear End Date in Account Numbers
	public MemberFormPage  clearEndDateInAccountNumbers() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),"End Date");
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),"End Date");
		clear(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),"End Date"); 				
		return this;
	}


	//disabled Account Number Type				
	public MemberFormPage verifyAccountNumberTypeInAccountNumbersIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Account Number Type");
		return this;
	}

	//Read Only Number in Account Numbers Entity
	public MemberFormPage verifyNumberInAccountNumbersIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text'][@readonly]")),"Number");
		return this;
	}

	//Read Only Name in Account Numbers Entity
	public MemberFormPage verifyNameInAccountNumbersIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbername.fieldControl-text-box-text'][@readonly]")),"Name");
		return this;
	}

	//Read Only Calculated Name in Account Numbers Entity
	public MemberFormPage verifyCalculatedNameInAccountNumbersIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='ix_calculatedname.fieldControl-text-box-text'][@readonly]")),"Calculated Name");
		return this;
	}

	//Disabled Start Date in Account Numbers Entity
	public MemberFormPage verifyStartDateInAccountNumbersIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_startdate.fieldControl-date-time-input']")),"Start Date");
		return this;
	}

	//Disabled End Date in Account Numbers Entity
	public MemberFormPage verifyEndDateInAccountNumbersIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_enddate.fieldControl-date-time-input']")),"End Date");
		return this;
	}


	// Read Only Account Entity Error Info Message for Base Read Only
	public MemberFormPage verifyAccountEntityIsNotEditable(String errorMessage) throws InterruptedException {
		Thread.sleep(2000);
		verifyExactText((getDriver().findElement(By.xpath("//span[@data-id='warningNotification']"))),errorMessage,"Account Entity- Member Form");
		return this;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~End Base Read Only~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Disabled Corporate Parent, Food Service Parent  ,Is FBO , Is Sponsor fields
	//Verify Is Corporate Account is Disabled
	public MemberFormPage verifyIsCorporateAccountFieldIsDisabled() {
		List<WebElement> IsCorporate= getDriver().findElements(By.xpath("//input[@aria-label='Is Corporate Account' and @readonly]"));
		verifyElementisDisplayed(IsCorporate.size(), "IsCorporate");
	//	verifyDisabledFields(getDriver().findElement(By.xpath("//input[@aria-label='Is Corporate Account']")),"Is Corporate Account");
		return this;
		//input[@aria-label='Is Corporate Account']
	}



	public MemberFormPage verifyIsFoodServiceParentFieldIsDisabled() {
		List<WebElement> IsCorporate= getDriver().findElements(By.xpath("//input[@aria-label='Is Food Service Parent' and @readonly]"));
		verifyElementisDisplayed(IsCorporate.size(), "IsCorporate");
		
		//verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_isfoodserviceparent.fieldControl-checkbox-select']")),"Is Food Service Parent");
		return this;
	}


	public MemberFormPage verifyIsFBOFieldIsDisabled() {
		List<WebElement> IsCorporate= getDriver().findElements(By.xpath("//input[@aria-label='Is FBO' and @readonly]"));
		verifyElementisDisplayed(IsCorporate.size(), "IsFBO");
		
		
		//verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_isfbo.fieldControl-checkbox-select']")),"Is FBO");
		return this;
	}


	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//Select TPR
	public MemberFormPage selectTopParentRelation(String topParentRelation) throws InterruptedException {
		Thread.sleep(1000);
		if(getDriver().findElements(By.xpath("//h2[@title='TOP PARENT']")).size()>0) {
			click(getDriver().findElement(By.xpath("//h2[@title='TOP PARENT']")),"Top Parent");
		}
		scrolldown();

		List<WebElement> entitycode= getDriver().findElements(By.xpath("//div[@data-id='TPQuickViewForm']//label"));
		if(entitycode.size()>0) {
			click(getDriver().findElement(By.xpath("//div[@data-id='TPQuickViewForm']//label")),"Entity Code");
		}
		Thread.sleep(1000);
		click(((getDriver().findElement(By.xpath("//button[@data-id='ix_topparentrelationship.fieldControl-option-set-select']")))),"Top Parent Relation");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+topParentRelation+"')]")),"topParentRelation");
		Thread.sleep(1000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_topparentrelationship.fieldControl-option-set-select']")),topParentRelation,"Top Parent Relation"); 
		return this;
	}

	//Verify TPR value

	public MemberFormPage verifyTPR(String topParentRelation) throws InterruptedException {
		Thread.sleep(1000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_topparentrelationship.fieldControl-option-set-select']")),topParentRelation,"Top Parent Relation"); 
		return this;
	}



	//Select TPRD
	public MemberFormPage selectTopParentRelationDate(String topParentRelationDate) throws InterruptedException {
		//click(getDriver().findElement(By.xpath("//*[@data-id='ix_topparentrelationdate.fieldControl-date-time-input']")),"Top Parent Relation Date");
		Thread.sleep(5000);
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Top Parent Relation Date']")),topParentRelationDate,"Top Parent Relation Date");
		return this;
	}

	//Navigate to Do Not Verify and type Address

	public MemberFormPage navigateToDoNotVerifyEnterData(String Street, String zipcode) throws InterruptedException {
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		typeStreet1(Street);
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']")),"County");
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		typeZipCode(zipcode);
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");
		click((getDriver().findElement(By.xpath("//input[@aria-label='Other Phone']"))),"Other Phone");
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Receive Direct Mail')]"))),"Receive Direct Mail");
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Do Not Verify Address')]"))),"Do Not Verify Address Key");

		return this;
	}


	public MemberFormPage navigateToDoNotVerify() throws InterruptedException {
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		if(getDriver().findElements(By.xpath("//label[contains(text(),'County')]")).size()>0){
			click(((getDriver().findElement(By.xpath("//label[contains(text(),'County')]")))), "County");
		}
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'State/Province')]")))), "State/Province");
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");	
		clickTab(4);
		click((getDriver().findElement(By.xpath("//input[@aria-label='Other Phone']"))),"Other Phone");
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Receive Direct Mail')]"))),"Direct Mail");
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Siebel Address ID')]"))),"Do not Verify Address");
		click((getDriver().findElement(By.xpath("//label[contains(text(),'FSRPT Flag')]"))),"Do not Verify Address");
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Do Not Verify Address')]"))),"Do not Verify Address");


		return this;

	}

	public MemberFormPage verifyParticipationType() {
		
		click(getDriver().findElement(By.xpath("//*[@aria-label='Participation Type']")),"Participation Type");
		
		//Create temp Array List > add  actual options  from DOM for comparison
		List<String > mylist =new ArrayList<String>();	

		List<WebElement> options=getDriver().findElements(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div"));

		for(int i=1;i<=options.size();i++) {
			mylist.add(getDriver().findElement(By.xpath("(//div[contains(@id,'pa-option-set-component')]/div/div)["+i+"]")).getText());
		}

		Actions action = new Actions(getDriver());
		action.sendKeys(Keys.ESCAPE);

		//Participation type should not have Provider Select : MD
		assertTrue(!(mylist.contains("Provider Select: MD")));
		return this;
	}

	public MemberFormPage verifyParticipationTypeOption(String participationTypeOption) {
		
		click(getDriver().findElement(By.xpath("//*[@aria-label='Participation Type']")),"Participation Type");
		
		//Create temp Array List > add  actual options  from DOM for comparison
		List<String > mylist =new ArrayList<String>();	

		List<WebElement> options=getDriver().findElements(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div"));

		for(int i=1;i<=options.size();i++) {
			mylist.add(getDriver().findElement(By.xpath("(//div[contains(@id,'pa-option-set-component')]/div/div)["+i+"]")).getText());
		}

		//Participation type should not have Provider Select : MD
		assertTrue(mylist.contains(participationTypeOption));
		return this;
	}


	public MemberFormPage verifyProviderSelectOption() throws InterruptedException {
		
click(getDriver().findElement(By.xpath("//*[@aria-label='Participation Type']")),"Participation Type");
		
		//Create temp Array List > add  actual options  from DOM for comparison
		List<String > mylist =new ArrayList<String>();	

		List<WebElement> options=getDriver().findElements(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div"));

		for(int i=1;i<=options.size();i++) {
			mylist.add(getDriver().findElement(By.xpath("(//div[contains(@id,'pa-option-set-component')]/div/div)["+i+"]")).getText());
		}
		
		List<String> expectdoption = Arrays.asList("---","Provider Select: MD","Provider Select: MD Grandfathered");		


		if(mylist.containsAll(expectdoption))
		{		
			Thread.sleep(3000);
			setReport().log(Status.PASS, "RepresentativeType- " + "   " + mylist+ "  " +  "-  Option is available to choose from the list" + " "+ expectdoption,	screenshotCapture());

		} 
		else {
			setReport().log(Status.FAIL, "RepresentativeType - "+   "   " + mylist + "  " + "- Option is not available in the list"  + " "+ expectdoption ,	screenshotCapture());
			Driver.failCount++;
		}


		return this;

	}

	//Select Participation type
	public MemberFormPage selectProviderSelectOption(String participationType) throws InterruptedException {
		//Wave 2023 update to scroll
		//		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		//		WebElement element = getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroup.fieldControl-LookupResultsDropdown_ix_affiliategroup_textInputBox_with_filter_new']"));
		//		je.executeScript("arguments[0].scrollIntoView(true);",element);f
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_providerselectmd.fieldControl-option-set-select']")),participationType,"Participation type");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_providerselectmd.fieldControl-option-set-select']")),participationType,"Participation type"); 
		return this;
	}

	public MemberFormPage navigateToDoNotVerifyMEF() {
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		if(getDriver().findElements(By.xpath("//label[contains(text(),'County')]")).size()>0){
			click(((getDriver().findElement(By.xpath("//label[contains(text(),'County')]")))), "County");
		}
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'State/Province')]")))), "State/Province");
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		click(getDriver().findElement(By.xpath("//input[@aria-label='Main Phone']")),"Main phone");		
		click((getDriver().findElement(By.xpath("//input[@aria-label='Fax']"))),"Fax");
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Do Not Verify Address')]"))),"Do not Verify Address");


		return this;

	}

	public MemberFormPage verifyDonNotverifydefault() throws IOException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-id='ix_externaladdressid']/parent::div//preceding-sibling::div/div[@data-id='ix_donotverifyaddress']")).size(), "Don not verify element");
		String defaultvale=getDriver().findElement(By.xpath("//select[@aria-label='Do Not Verify Address']/option[@data-selected='true']")).getText();
		assertTrue(defaultvale.contentEquals("No"), "Parent Entity code is not displayed");
		return this;
	}


	public MemberFormPage navigateToDoNotVerifyMemberEntryForm() {
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'State/Province')]")))), "State/Province");
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Main Phone')]"))),"Main Phone");
		click((getDriver().findElement(By.xpath("//input[@aria-label='Fax']"))),"Fax");
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Do Not Verify Address')]"))),"Do not Verify Address");
		click((getDriver().findElement(By.xpath("//input[@aria-label='Website']"))),"Website");
		click((getDriver().findElement(By.xpath("//input[@aria-label='Country/Region']"))),"Country");
		click((getDriver().findElement(By.xpath("//input[@aria-label='External Address ID']"))),"Country");


		return this;

	}

	//Navigate to State Text field
	public MemberFormPage navigateToState() {
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")))), "State");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		click((getDriver().findElement(By.xpath("//input[@aria-label='Other Phone']"))),"Other Phone");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")))), "State");
		return this;

	}


	//Navigate to Exclude from roster
	public MemberFormPage navigateToroster() throws InterruptedException {
		Thread.sleep(2000);
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']")),"County");
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");
		click((getDriver().findElement(By.xpath("//input[@aria-label='Other Phone']"))),"Other Phone");
		return this;

	}

	public MemberFormPage navigateTorosterFromAppDate()
	{

		click(getDriver().findElement(By.xpath("//label[contains(text(),'Application Date')]")),"Application Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Premier Start Date')]")),"Premier Start Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Premier End Date')]")),"Premier End Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Require Manual AG')]")),"Require Manual AG");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group')]")),"Affiliate Group");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group Effective Date')]")),"Affiliate Group Effective Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Premier Owner')]")),"Premier Owner");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Corporate')]")),"Corporate");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'CAMS Flag')]")),"CAMS Flag");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Exclude from Roster')]")),"Exclude from Roaster");

		return this;
	}
	//Navigate to Exclude from roster
	public MemberFormPage navigateTorosterMemberEntryForm() {
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Premier Owner')]")))), "Premier Owner");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Corporate')]")))), "Corporate");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'CAMS Flag')]")),"CAMS Flag");

		return this;

	}

	public MemberFormPage setExcludeFromRoster(String roster) {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Exclude from Roster']")), roster, "Roster Value");
		return this;
	}



	//Get the Value from State text field

	public MemberFormPage getStateValue() {
		State=verifIsNoTNullValue(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")),"State");
		System.out.println(State);
		return this;
	}

	//Verify State Value
	public MemberFormPage verifyStateValue(String state, boolean isTrue) {
		if(isTrue) {
			assertFalse(State.contentEquals(state));
		}else {
			assertTrue(State.contentEquals(state));
		}
		return this;
	}


	//NavigateToSystemTab
	public MemberFormPage NavigateToSystemTab() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> systemTab=getDriver().findElements(By.xpath("//li[contains(text(),'SYSTEM')]"));
		if(systemTab.size()>0) {

			click(getDriver().findElement(By.xpath("//li[contains(text(),'SYSTEM')]")),"System Tab");
			Thread.sleep(3000);

		}else {

			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
			click(getDriver().findElement(By.xpath("//div[contains(@data-id,'tablist-tab')]/span[contains(text(),'SYSTEM')]")),"System Tab");
			
			Thread.sleep(3000);

		}
		return this;
	}

	//NavigateToDocumentTab
	public MemberFormPage NavigateToDocumentTab() throws InterruptedException {
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(160));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title='Documents']")));

		click(getDriver().findElement(By.xpath("//li[@title='Documents']")),"Documents Tab");
		Thread.sleep(3000);
		return this;
	}
	public MemberFormPage verifyDocumentsTab() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//li[@title='Documents']")).size(), "Documents Tab");
		return this;
	}



	//Type street1
	public MemberFormPage typeStreet1(String street1) {
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		type(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))),street1, "Street1");
		return this;
	}

	public MemberFormPage verifystreet1(String street) {
		assertTrue(!((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']"))).getAttribute("value").contentEquals(street)));
		return this;
	}

	//Navigate To Melissa and verify Mellisa is not null
	public MemberFormPage verifyMellissa() throws InterruptedException {

		click(((getDriver().findElement(By.xpath("//input[@aria-label='Congressional District']")))), "Congressional District");

		for(int i=0;i<2;i++) {

			Actions a =new Actions(getDriver());

			a.sendKeys(Keys.TAB).build().perform();

			Thread.sleep(3000);

		}
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Melissa Address Key')]")),"Address Key");

		verifIsNoTNullValue(getDriver().findElement(By.xpath("//input[@aria-label='Melissa Address Key']")),"Melissa Address Key");


		return this;
	}

	//Navigate To Melissa and verify Mellisa is not null
	public MemberFormPage verifyMellissakeybaseisNotNull() throws InterruptedException {


		verifIsNoTNullValue(getDriver().findElement(By.xpath("//input[@data-id='ix_melissaaddresskeybase.fieldControl-text-box-text']")),"Melissa Address Key");


		return this;
	}


	//Navigate To Melissa and verify Mellisa is not null
	public MemberFormPage verifyMellissakeybaseisNull() throws InterruptedException {


		verifyNullValue(getDriver().findElement(By.xpath("//input[@data-id='ix_melissaaddresskeybase.fieldControl-text-box-text']")),"Melissa Address Key");


		return this;
	}


	//Navigate To Mellissa
	public MemberFormPage verifyMellissaisDisabled() throws InterruptedException {

		click(((getDriver().findElement(By.xpath("//input[@aria-label='Congressional District']")))), "Congressional District");

		for(int i=0;i<2;i++) {

			Actions a =new Actions(getDriver());

			a.sendKeys(Keys.TAB).build().perform();

			Thread.sleep(3000);

		}
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Melissa Address Key')]")),"Address Key");

		verifyNullValue(getDriver().findElement(By.xpath("//input[@aria-label='Melissa Address Key']")),"Melissa Address Key");


		return this;
	}

	//Verify Exclude from Roaster Label
	public MemberFormPage excludeFromRoster() {

		List<WebElement> excludeRoster=getDriver().findElements(By.xpath("//label[contains(text(),'Exclude from Roster')]"));
		verifyElementisDisplayed(excludeRoster.size(), "Exclude Roster header");

		List<WebElement> doNotRoster=getDriver().findElements(By.xpath("//label[contains(text(),'Do Not Roster')]"));
		verifyElementisNotDisplayed(doNotRoster.size(), "Do Not Roster header");

		return this;
	}

	//Type City
	public MemberFormPage typeCity(String city) {
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']"))),"City");
		 type((getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']"))),city,"City");
		return this;
	}



	//Type country
	public MemberFormPage typeCountry(String country) { 
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");
		type((getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']"))),country,"Country");
		return this;
	}

	//Type County
	public MemberFormPage typeCounty(String county) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']")),"County");
		type((getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']"))),county,"County");
		return this;
	}

	//Select record status published
	public MemberFormPage chooseRecordStatusPublished() throws InterruptedException {
		Thread.sleep(3000);
		navigateToRecordStatus();
		selectPublished();
		return this;
	}


	public MemberFormPage selectPublished() throws InterruptedException{


		click(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published status");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'Published')]")),"Account published");

		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")))),"Published", "Record Status");	
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published","Record Status"); 
		Thread.sleep(2000);
		return this;
	}

	//Select record status published
	public MemberFormPage chooseRecordStatusPublishedtillFBO() throws InterruptedException {
		Thread.sleep(3000);

		navigateToDoNotVerify();
		click(getDriver().findElement(By.xpath("//*[@title='TOP PARENT']")),"TOP Parent");//Scroll down to make the record status field visible
		click(getDriver().findElement(By.xpath("//*[@title='Sponsor']")),"Sponsor");//Scroll down to make the record status field visible
		click(getDriver().findElement(By.xpath("//*[@title='CORPORATE PARENT']")),"CP");//Scroll down to make the record status field visible
		click(getDriver().findElement(By.xpath("//*[@title='FOOD SERVICE PARENT']")),"Food Service");//Scroll down to make the record status field visible
		click(getDriver().findElement(By.xpath("//*[@title='FBO']")),"FBO");//Scroll down to make the record status field visible

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published status");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'Published')]")),"Account published");

		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")))),"Published", "Record Status");	
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published","Record Status"); 
		Thread.sleep(2000);
		return this;
	}


	//navigate to Record status
	public MemberFormPage navigateToRecordStatus() throws InterruptedException {
		Thread.sleep(6000);
		//Scroll down to make the record status field visible
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		clickTab(4);
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		if(getDriver().findElements(By.xpath("//label[contains(text(),'County')]")).size()>0){
			click(((getDriver().findElement(By.xpath("//label[contains(text(),'County')]")))), "County");
		}
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'State/Province')]")))), "State/Province");
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
	if((getDriver().findElements(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']"))).size()>0) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");
	}
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Phone')]"))),"Other Phone");
		click((getDriver().findElement(By.xpath("//input[@data-id='telephone1.fieldControl-phone-text-input']"))),"Other Phone");
		clickTab(4);
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Receive Direct Mail')]"))),"Direct Mail");
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Siebel Address ID')]"))),"Do not Verify Address");
		click(getDriver().findElement(By.xpath("//*[@title='Sponsor']")),"Sponsor");//Scroll down to make the record status field visible
		click(getDriver().findElement(By.xpath("//*[@title='CORPORATE PARENT']")),"CP");//Scroll down to make the record status field visible
		if(getDriver().findElements(By.xpath("//input[@data-id='ix_supplierrecordid.fieldControl-LookupResultsDropdown_ix_supplierrecordid_textInputBox_with_filter_new']")).size()>0){
		clickAndEsc(getDriver().findElement(By.xpath("//input[@data-id='ix_supplierrecordid.fieldControl-LookupResultsDropdown_ix_supplierrecordid_textInputBox_with_filter_new']")),"Supplier");
		}else {
			clickAndEsc(getDriver().findElement(By.xpath("//button[@aria-label='Receive Direct Mail']")),"Supplier");
		}
		clickTab(8);
		click(getDriver().findElement(By.xpath("//*[@title='FOOD SERVICE PARENT']")),"Food Service");//Scroll down to make the record status field visible

		if(getDriver().findElements(By.xpath("//*[@title='FBO']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='FBO']")),"FBO");//Scroll down to make the record status field visible
		}
		clickAndEsc(getDriver().findElement(By.xpath("//input[@data-id='ix_ofestimatedlocations.fieldControl-whole-number-text-input']")),"Supplier");
		clickTab(6);
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Safe Harbor Instructions')]")),"Safe Harbor Instructions");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'NAICS Code')]")),"NAICS Code");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Require Manual AG')]")),"Require Manual AG");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group')]")),"Affiliate Group");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group Effective Date')]")),"Affiliate Group Effective Date");
		/*
		 * 
		 * 
		 * 
		 * 
		 * click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'Require Manual AG')]")),"Require Manual AG");
		 * click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'Affiliate Group')]")),"Affiliate Group");
		 * click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'Affiliate Group Effective Date')]"))
		 * ,"Affiliate Group Effective Date"); click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'Premier Owner')]")),"Premier Owner");
		 * click(getDriver().findElement(By.xpath(
		 * "//label[contains(text(),'Corporate')]")),"Corporate");
		 * click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'CAMS Flag')]")),"CAMS Flag");
		 * click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'Exclude from Roster')]"))
		 * ,"Exclude from Roster"); click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'Receive Direct Mail')]"))
		 * ,"Receive Direct Mail"); click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'Fee Share Eligible')]")),"Fee Share Eligible"
		 * ); click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'Fee Share Eligible Date')]"))
		 * ,"Fee Share Eligible Date"); click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'Business Key')]")),"Business Key");
		 * click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'BK Active')]")),"BK Active");
		 * click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'Payment Entity')]")),"Payment Entity");
		 * click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'Corporate Rebate')]")),"Corporate Rebate");
		 * click(getDriver().findElement(By.
		 * xpath("//label[contains(text(),'Record Status')]")),"Record Status");
		 * 
		 */		return this;

	}


	//navigate to Record status
	public MemberFormPage navigateToRecordStatusMEF() throws InterruptedException {
		Thread.sleep(3000);
		//Scroll down to make the record status field visible
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		if(getDriver().findElements(By.xpath("//label[contains(text(),'County')]")).size()>0){
			click(((getDriver().findElement(By.xpath("//label[contains(text(),'County')]")))), "County");
		}
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'State/Province')]")))), "State/Province");
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		List<WebElement> country=getDriver().findElements(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']"));
		if(country.size()>0) {
			click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");
		}
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Phone')]"))),"Other Phone");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Do')]")),"Do Not Verify");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Website')]")),"Website");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Country')]")),"Country");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'External')]")),"External Address");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Supplier Record')]")),"Supplier Record");
		navigateToAGDateMEF();
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Premier Owner')]")),"Premier Owner");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Corporate')]")),"Corporate");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'CAMS Flag')]")),"CAMS Flag");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Exclude from Roster')]")),"Exclude from Roster");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Receive Direct Mail')]")),"Receive Direct Mail");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Fee Share Eligible')]")),"Fee Share Eligible");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Fee Share Start Date')]")),"Fee Share Start Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Fee Share End Date')]")),"Fee Share End Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Business Key')]")),"Business Key");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'BK Active')]")),"BK Active");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Payment Entity')]")),"Payment Entity");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Corporate Rebate')]")),"Corporate Rebate");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Record Status')]")),"Record Status");

		return this;}

	public MemberFormPage navigateToRecordStatusForMember() throws InterruptedException {
		Thread.sleep(3000);
		//Scroll down to make the record status field visible
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group')]")),"Affiliate Group");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group Effective Date')]")),"Affiliate Group Effective Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Record Status')]")),"Record Status");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Account Type')]")),"Account Type");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group')]")),"Affiliate Group");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group Effective Date')]")),"Affiliate Group Effective Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Premier Owner')]")),"Premier Owner");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Corporate')]")),"Corporate");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'CAMS Flag')]")),"CAMS Flag");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Exclude from Roster')]")),"Exclude from Roster");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Receive Direct Mail')]")),"Receive Direct Mail");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Fee Share Eligible')]")),"Fee Share Eligible");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Fee Share Eligible Date')]")),"Fee Share Eligible Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Business Key')]")),"Business Key");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'BK Active')]")),"BK Active");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Payment Entity')]")),"Payment Entity");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Corporate Rebate')]")),"Corporate Rebate");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Record Status')]")),"Record Status");

		return this;

	}

	//Select Record status published

	public MemberFormPage chooseStatusPublish() throws InterruptedException {

		selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")))),"Published", "Record Status");	
		Thread.sleep(3000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published","Record Status"); 
		return this;
	}

	//Select record status published
	public MemberFormPage clickGeneralThenChooseRecordStatusPublished() throws InterruptedException {
		clickGeneralTab();
		chooseRecordStatusPublished();
		return this;
	}

	public MemberFormPage clickGeneralTab() throws InterruptedException {
		List<WebElement> general=getDriver().findElements(By.xpath("//*[@title='GENERAL']"));
		if(general.size()>0) {
			click(getDriver().findElement(By.xpath("//*[@title='GENERAL']")),"GENERAL");	
		}else {
			click(getDriver().findElement(By.xpath("//*[@title='General']")),"General");
		}

		return this;
	}


	public MemberFormPage clickGeneralTabMEF() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@title='General']")),"GENERAL");
		return this;
	}

	//just to navigate to BC and make it visible
	public MemberFormPage navigateToBusinessClassification() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")),"Primary Contact");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")),"Street 1");
		clickTab(2);
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']"))),"City");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")),"State");
		return this;
	}

	//Navigate to AG from FBO Grid
	public MemberFormPage navigateToAG() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@title='FBO']")),"FBO");//Scroll down to make the record status field visible
		click(getDriver().findElement(By.xpath("//*[@title='MEMBERSHIP PROVIDER CONFIGURATION']")),"MEMBERSHIP PROVIDER CONFIGURATION");//Scroll down to make the record status field visible
		return this;
	}


	//Entity code is generated
	public MemberFormPage entityCodeIsDisplayed() throws InterruptedException {
		Thread.sleep(6000);
		entityCode =getTextValue(getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[1]")),"Entity Code");
		//assertNotNull(entityCode);
		return this;
	}

	public MemberFormPage getTopParentEntity() {
		topparent= entityCode;
		return this;
	}


	public MemberFormPage createSubAccount(int count) throws InterruptedException, AWTException {
		for(int i=1;i<=count;i++) {
			selectSubaccount();
			clickNewAccountInSubAccount();
			chooseMemberForm();

			//3. Account Name = Any
			typeAccountName("MemberAccount"+i);

			//Click on save 			
			clickSave() ;

			//5. Account Type = Member
			selectAccountType("Member");

			//Class of Trade =Any
			selectClassOfTrade("Auditorium/Museum");


			//Application Start Date = Today's Date
			chooseApplicationDate(TestUtils.todaysDate());

			//CAMS Flag = Yes
			changeCAMSFlagAsYes();

			//Participation Type = Standard
			selectParticipationType("Pharmacy");



			//Direct Parent Relation = Managed
			selectDirectParentRelationManaged(); 

			//Direct Parent Relation date = Today's Date
			selectDirectParentRelationDate(TestUtils.todaysDate());

			//Top Parent Relation =  OLM
			selectTopParentRelation("OLM");

			// Top Parent Relation Date = Today's Date
			selectTopParentRelationDate( TestUtils.todaysDate());

			//Click on Save 
			clickSave(); 

			//6. Street 1 = Any
			typeStreet1("Street 1");

			//City = NY
			typeCity("NY");

			//Country =USA
			typeCountry("USA");

			//Type Zip code
			typeZipCode("3444");

			//Click on Save 
			clickSave() ;

			//7.  Click the + icon on the Line of Business Grid
			clickLineOfBusinesses();

			//Click New Line Of Business
			clickAddNewLineOfBusiness();

			// Line of Business =General GPO
			selectLineOfBusiness("General GPO");

			// Classification Type = General GPO
			selectLOBfClassificationType("General GPO");

			// Start Date =Today's date
			typeLineOfBusinessStartDate(TestUtils.todaysDate());

			// Click on LOB Save 
			clickLOBSaveAndClose();

			//Click add new membership
			clickMembershipAndAddNewMembership();

			// Choose Membership type 
			selectMembershipType("Premier");
			selectMembershipProvider("National");

			//Provide any start date and click on save
			typeMembershipStartDate(TestUtils.todaysDate());

			//Click on membership save and close
			clickQuickCreateMembershipSaveAndClose();

			//8. Record Status = Published
			chooseRecordStatusPublished();;

			verifyCRMNumberIsDisplayed();
			crmList.add(CRMNumber);
			//Click on Save 
			clickSave() ;

			//9. Verify Entity code is generated 
			entityCodeIsDisplayed();
			getTopParentEntity();
			System.out.println(crmList);
		}
		return this;
	}
	//Navigate to MemberAttribute
	public MemberFormPage navigateAndVerifyMemberAttribute() throws InterruptedException {

		navigateToDoNotVerify();
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'FBO GPO')]")))), "FBO GPO");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'FBO Type')]")))), "FBO Type");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'FBO Type Effective Date')]")))), "FBO Type Effective Date");
		click(((getDriver().findElement(By.xpath("//h2[@title='MEMBERSHIP PROVIDER CONFIGURATION']")))), "MEMBERSHIP PROVIDER CONFIGURATION");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Membership Provider')]")))), "Membership Provider");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Membership Provider Type')]")))), "Membership Provider Type");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'Membership Attribute Code')]")))), "Membership Attribute Code");
		click(((getDriver().findElement(By.xpath("//h3[contains(text(),'MEMBER ATTRIBUTES')]")))), "MEMBER ATTRIBUTES");

		List<WebElement> ele=getDriver().findElements(By.xpath("//span[contains(text(),'General GPO Rebate Aggregation')]"));
		verifyElementisDisplayed(ele.size(), "General Rebate Aggregation");

		ele=getDriver().findElements(By.xpath("//span[contains(text(),'Pharmacy Rebate Aggregation')]"));
		verifyElementisDisplayed(ele.size(), "Pharmacy Rebate Aggregation");
		return this;
	}

	//Navigate to Record Change Status via Member Attributes
	public MemberFormPage navigateToMembershipProviderConfig() throws InterruptedException {
		navigateToAG();
		//click(((getDriver().findElement(By.xpath("//h2[@title='MEMBERSHIP PROVIDER CONFIGURATION']")))), "MEMBERSHIP PROVIDER CONFIGURATION");
		//click(((getDriver().findElement(By.xpath("//h3[contains(text(),'MEMBER ATTRIBUTES')]")))), "MEMBER ATTRIBUTES");
		return this;
	}

	public MemberFormPage verifyAccountTypeLocked() {
		verifyDisplayed(getDriver().findElement(By.xpath("//select[@aria-label='Account Type' and @Disabled]")),"Account type lock");
		return this;
	}


	public MemberFormPage verifyParentEntityCode(String parentEntity) throws IOException {
		String entityCode =getTextValue(getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[1]")),"Entity Code");
		System.out.println(parentEntity);
		assertTrue(entityCode.contentEquals(parentEntity), "Parent Entity code is not displayed");
		return this;

	}


	public MemberFormPage verifyParentEntityCodeisNotMatched(String parentEntity) throws IOException {
		String entityCode =getTextValue(getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[1]")),"Entity Code");
		System.out.println(parentEntity);
		assertTrue(!(entityCode.contentEquals(parentEntity)), "Parent Entity code is displayed");
		return this;

	}

	public MemberFormPage verifymembershipwidget() throws InterruptedException {
		assertTrue(getDriver().findElement(By.xpath("//div[@id='dataSetRoot_MembershipSubGrid_outer']")).isDisplayed(), "Membership widget");
		click(getDriver().findElement(By.xpath("//div[@id='dataSetRoot_MembershipSubGrid_outer']//span[contains(text(),'Premier Memberships')]")),"Premier Memberships dropdown Option");
		assertTrue(getDriver().findElement(By.xpath("//span[normalize-space()='All Memberships']")).isDisplayed(), "All Memberships Option");
		assertTrue(getDriver().findElement(By.xpath("//span[@role='presentation'][normalize-space()='Premier Memberships']")).isDisplayed(), "Premier Memberships");
		//click(getDriver().findElement(By.xpath("//div[@id='dataSetRoot_MembershipSubGrid_outer']//span[contains(text(),'All Memberships')]")),"All Memberships");
		Thread.sleep(2000);
		return this;
	}

	public MemberFormPage selectPremierMembership() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//span[normalize-space()='Premier Memberships']")),"Membership");

		Thread.sleep(5000);
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//span[contains(text(),'Premier Memberships')]"))).click().build().perform();;
		return this;
	}



	public MemberFormPage selectAllMembershipsView() throws InterruptedException {

		//click(getDriver().findElement(By.xpath("//div[@id='dataSetRoot_MembershipSubGrid_outer']//span[contains(text(),'Premier Memberships')]")),"Premier Memberships");

		//Actions a = new Actions(getDriver());
		//a.moveToElement(getDriver().findElement(By.xpath("//span[@role='presentation'][normalize-space()='All Memberships']"))).click().build().perform();
		//a.moveToElement(getDriver().findElement(By.xpath("//div[@id='dataSetRoot_MembershipSubGrid']//div[@aria-label='All Memberships']"))).click().build().perform();

		Thread.sleep(5000);
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//span[contains(text(),'All Memberships')]"))).click().build().perform();
		return this;
		//click(getDriver().findElement(By.xpath("//div[@id='dataSetRoot_MembershipSubGrid']//div[@aria-label='All Memberships']")),"sdfsd");

	}

	public MemberFormPage verifyPremierMembershipisDisplayed() {

		List<WebElement> nonPremier= getDriver().findElements(By.xpath("//div[@class='ms-List-cell']//span[contains(text(),'Aggregation Affiliation')]"));

		if(nonPremier.size()>0) {

			assertFalse(getDriver().findElement(By.xpath("//div[@class='ms-List-cell']//span[contains(text(),'Aggregation Affiliation')]")).isDisplayed(), "Classification type");
		}
		assertTrue(nonPremier.size()==0,"Non premier membership details are displayed");

		return this;
	}

	public MemberFormPage verifyAllMembershipsisDisplayed() {
		assertTrue((getDriver().findElement(By.xpath("//a[contains(@data-id,'MembershipSubGrid-ListItemContent')]/span[contains(text(),'Aggregation Affiliation')]")).isDisplayed()),"Aggregation Affiliation is displayed");
		setReport().log(Status.PASS, "All Memberships view is displayed as Expected", screenshotCapture());
		return this;

	}

	//verify the LOB Enda date has todays Date

	public MemberFormPage verifyLOBEndDate() throws IOException {

		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String date1= dateFormat.format(date); 
		String endate=getAttribute(getDriver().findElement(By.xpath("(//*[@col-id='ix_enddate']//label)[2]//div//div")),"textContent","End Date");
		System.out.println(date1+endate);
		assertTrue(date1.contentEquals(endate),"LOB End date does not match with current date");
		return this;
	}
	//Verify entity code
	public MemberFormPage verifyEntityCodeIsNotDPEntityCode(String verifyEntityCode) throws InterruptedException {
		Assert.assertFalse((getTextValue(getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[1]")),"Entity Code").toString()).equals(verifyEntityCode));
		return this;
	}

	//Verify entity code
	public MemberFormPage verifyEntityCode(String verifyEntityCode) throws InterruptedException {
		System.out.println(verifyEntityCode);
		System.out.println(getTextValue(getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[1]")),"Entity ode"));
		Assert.assertTrue((getTextValue(getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[1]")),"Entity Code").toString()).equals(verifyEntityCode));
		return this;
	}

	//Verify Premier Start Date is Auto Populated
	public MemberFormPage verifyPremierStartDateIsAutoPopulated() throws InterruptedException  {
		Thread.sleep(4000);
		getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Date of Premier Start Date']")),"Premier Start Date");
		return this;
	}

	//Click on AG Effective Date  for navigation puposes
	public MemberFormPage navigateToRecordChangeStatus() {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroupeffectivedate.fieldControl-date-time-input']")),"AG Effective Date");
		click(getDriver().findElement(By.xpath("//select[@data-id='customertypecode.fieldControl-option-set-select']")),"Account Type");
		click(getDriver().findElement(By.xpath("//select[@data-id='ix_recordchangestatus.fieldControl-option-set-select']")),"Record Change Status");

		return this;
	}

	//Click on Fee Share
	public MemberFormPage navigateToFeeShare() {
		click(getDriver().findElement(By.xpath("//h2[text()='FEE SHARE']")),"FEE SHARE");//To make fee share field visible
		return this;
	}

	//Verify AG is not null
	public MemberFormPage verifyAffiliateGroupIsNotNull() {
		click(getDriver().findElement(By.xpath("//*[@data-id='form-sectionHeader-SUMMARY_TAB_section_9']")),"SUMMARY TAB");
		List<WebElement> affiliateGroup=getDriver().findElements(By.xpath("//*[@data-id='ix_affiliategroup.fieldControl-LookupResultsDropdown_ix_affiliategroup_textInputBox_with_filter_new']"));
		if(affiliateGroup.size()>0) {
			getTextValue(getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroup.fieldControl-LookupResultsDropdown_ix_affiliategroup_textInputBox_with_filter_new']")),"Affiliate Group");
		}else {
			getTextValue(getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroup.fieldControl-LookupResultsDropdown_ix_affiliategroup_selected_tag_text']")),"Affiliate Group");
		}
		return this;
	}

	//Verify AG effective is not null
	public MemberFormPage verifyAgEffectiveDateIsNotNull() {
		getTextValueAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroupeffectivedate.fieldControl._datecontrol-date-container']//input")),"Affiliate Group Effective Date");
		return this;
	}

	//AG Effective date is null
	public MemberFormPage verifyAgEffectiveDateIsNull() {
		verifyNullValue(getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroupeffectivedate.fieldControl._datecontrol-date-container']//input")),"AG Effective Date"); 
		return this;
	}

	public MemberFormPage verifyDPRDIsNull() {
		verifyNullValue(getDriver().findElement(By.xpath("//*[@data-id='ix_directparentrelationdate.fieldControl-date-time-input']")),"AG Effective Date"); 
		return this;
	}

	//Verify Affiliate Group is Null
	public MemberFormPage verifyAffiliateGroupIsNull() {
		verifyNullValue(getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroup.fieldControl-LookupResultsDropdown_ix_affiliategroup_textInputBox_with_filter_new']")),"Affiliate Group"); 
		return this;
	}


	//Verify is corporate parent account
	public MemberFormPage verifyIsCorporateAccount(String verifyIsCorporateAccount) {
		List<WebElement> CorporateAccount=getDriver().findElements(By.xpath("//h2[@title='Corporate Parent']"));
		if(CorporateAccount.size()>0) {
			click(getDriver().findElement(By.xpath("//h2[@title='Corporate Parent']")),"Corporate Account");
		}
		verifyExactAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_iscorporateaccount.fieldControl-pcf-container-id']//input")),"value",verifyIsCorporateAccount,"Is Corporate Account"); 
		return this;
	}

	//Verify Corporate Parent Name is null
	public MemberFormPage verifyCorporateParentNameIsNull() {
		verifyNullValueWithGetText(getDriver().findElement(By.xpath("//div[@data-id='ix_corporateparentname.fieldControl-Lookup_ix_corporateparentname']")),"Corporate Parent Name"); 
		return this;
	}


	//Verify Corporate Parent Name
	public MemberFormPage verifyCorporateParentName(String verifyCorporateParentName) {
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_corporateparentname.fieldControl-LookupResultsDropdown_ix_corporateparentname_selected_tag_text']")),verifyCorporateParentName,"Corporate Parent Name"); 
		return this;
	}	

	//Verify Is Food Service Parent
	public MemberFormPage verifyIsFoodServiceParent(String verifyIsFoodServiceParent) {
		List <WebElement> CP=getDriver().findElements(By.xpath("//div[@data-id='CPEntityCode.ix_premierein-FieldSectionItemContainer']"));
		if(CP.size()>0) {
			click(getDriver().findElement(By.xpath("//div[@data-id='CPEntityCode.ix_premierein-FieldSectionItemContainer']")),"CP entity");
		}
		else {
			click(getDriver().findElement(By.xpath("//h2[@title='CORPORATE PARENT']")),"Corporate Parent Grid Title");	
		}
		if(getDriver().findElements(By.xpath("//h2[@title='FOOD SERVICE PARENT']")).size()>0) {
			click(getDriver().findElement(By.xpath("//h2[@title='FOOD SERVICE PARENT']")),"Food Service Parent Grid Title");
		}
		else {
			click(getDriver().findElement(By.xpath("//label[text()='Corporate Parent Override']")),"Corporate Parent Override");
			click(getDriver().findElement(By.xpath("//label[text()='Is Food Service Parent']")),"Is Food Service Parent");
		}
		verifyExactAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_isfoodserviceparent.fieldControl-pcf-container-id']//input")),"value",verifyIsFoodServiceParent,"Is Food Service Parent"); 
		return this;
	}

	//Verify Food Service Parent Name
	public MemberFormPage verifyFoodServiceParentName(String verifyFoodServiceParentName) {
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_foodserviceparentname.fieldControl-LookupResultsDropdown_ix_foodserviceparentname_selected_tag_text']")),verifyFoodServiceParentName,"Food Service Parent Name"); 
		return this;
	}

	//Verify Food Service Parent Name
	public MemberFormPage verifyFoodServiceParentNameIsNull() {
		verifyNullValueWithGetText(getDriver().findElement(By.xpath("//div[@data-id='ix_foodserviceparentname.fieldControl-Lookup_ix_foodserviceparentname']")),"Food Service Parent Name"); 
		return this;
	}


	//Verify Food service Start Date
	public MemberFormPage verifyFoodServiceStartDate(String verifyFoodServiceStartDate) {
		click(getDriver().findElement(By.xpath("//h2[@title='FOOD SERVICE PARENT']")),"Food Service Parent Grid Title");
		verifyExactValue(getDriver().findElement(By.xpath("//*[@data-id='ix_foodserviceparentstartdate.fieldControl-date-time-input']")),verifyFoodServiceStartDate,"Food Service Parent Start Date"); 
		return this;
	}

	//Verify FSP Override
	public MemberFormPage verifyFSPOverride(String verifyFSPOverride) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_foodserviceparentoverride.fieldControl-checkbox-container']")),verifyFSPOverride,"Food Service Parent Override"); 
		return this;
	}

	//Verify Corp Parent Start Date
	public MemberFormPage verifyCorpParenttartDate(String verifyCorpParenttartDate) {
		click(getDriver().findElement(By.xpath("//h2[@title='CORPORATE PARENT']")),"Corporate Parent Gird Title");
		verifyExactValue(getDriver().findElement(By.xpath("//*[@data-id='ix_corporateparentstartdate.fieldControl-date-time-input']")),verifyCorpParenttartDate,"Corporate Parent Start Date"); 
		return this;
	}


	//Verify Corp Parent Start Date
	public MemberFormPage verifyCorpParenttartDateisNotTodayDate( ) {

		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String todaydate= dateFormat.format(date);	
		String cpdate=getDriver().findElement(By.xpath("//*[@data-id='ix_corporateparentstartdate.fieldControl-date-time-input']")).getAttribute("value");
		Assert.assertTrue(!((cpdate.contentEquals(todaydate))));
		return this;
	}

	//Verify FBO
	public MemberFormPage verifyFBO(String verifyFBO) {
		List<WebElement>FBO=getDriver().findElements(By.xpath("//h2[contains(text(),'FBO')]"));
		if(FBO.size()>0) {
			verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_fbo.fieldControl-LookupResultsDropdown_ix_fbo_selected_tag_text']")),verifyFBO,"FBO");
		}
		return this;
	}

	//Navigate to FBORD
	public MemberFormPage navigateToFBOFromCorporate() {
		click(getDriver().findElement(By.xpath("//h2[@title='FOOD SERVICE PARENT']")),"Food Service Parent");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Is Food Service Parent')]")),"Food Service");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Entity Code')]")),"Entity Code");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Food Service Parent Start Date')]")),"Food Service Parent Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Food Service Parent Override')]")),"Food Service Override");
		click(getDriver().findElement(By.xpath("//h2[@title='FBO']")),"FBO");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Is FBO')]")),"Is FBO");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'FBO Relation Date')]")),"Is FBO Relation Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'FBO Manual Override')]")),"Is FBO Manual Override");


		return this;
	}
	//Verify FBORD
	public MemberFormPage verifyFBORD(String verifyFBORD) {
		List<WebElement>FBO=getDriver().findElements(By.xpath("//h2[contains(text(),'FBO')]"));
		if(FBO.size()>0) {
			if(getDriver().findElements(By.xpath("//div[@data-id='FSPEntityCode.ix_premierein-FieldSectionItemContainer']/div")).size()>0){
				click(getDriver().findElement(By.xpath("//div[@data-id='FSPEntityCode.ix_premierein-FieldSectionItemContainer']/div")),"Food Service");
			}
			if(getDriver().findElements(By.xpath("//*[@data-id='ix_fborelationdate.fieldControl-date-time-input']")).size()<0){
				click(getDriver().findElement(By.xpath("//h2[contains(text(),'FBO')]")),"FBO");
			}

			System.out.println(getDriver().findElement(By.xpath("//input[@aria-label='Date of FBO Relation Date']")).getAttribute("value"));
			verifyExactValue(getDriver().findElement(By.xpath("//input[@aria-label='Date of FBO Relation Date']")),verifyFBORD,"FBO Relation Date"); 
		}
		return this;
	}


	public MemberFormPage selectFBOOverride(String selectMemberOverride) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//h2[@title='DIRECT PARENT']")),"Direct Parent");
		click(getDriver().findElement(By.xpath("//h2[@title='TOP PARENT']")),"Top Parent");
		click(getDriver().findElement(By.xpath("//h2[@title='Sponsor']")),"Sponsor");
		click(getDriver().findElement(By.xpath("//h2[@title='CORPORATE PARENT']")),"Corporate PArent");
		click(getDriver().findElement(By.xpath("//h2[@title='FOOD SERVICE PARENT']")),"FOOD SERVICE PARENT");
		click(getDriver().findElement(By.xpath("//h2[@title='FBO']")),"FBO");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'FBO Manual Override')]")),"FBO Manual override");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='FBO Manual Override']")),selectMemberOverride,"MemberOverride");
		Thread.sleep(2000);
		return this;
	}

	public MemberFormPage selectFBOOverrideMemberEntryForm(String selectMemberOverride) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//label[contains(text(),'FBO Manual Override')]")),"FBO Manual override");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='FBO Manual Override']")),selectMemberOverride,"MemberOverride");
		Thread.sleep(2000);
		return this;
	}
	//Verify Is FBO
	public MemberFormPage verifyIsFBO(String verifyIsFBO) throws Exception { 

		if(!(verifyIsFBO.equalsIgnoreCase("yes"))) {
			List<WebElement>FBO=getDriver().findElements(By.xpath("//h2[contains(text(),'FBO')]"));
			if(FBO.size()>0) {
				click(getDriver().findElement(By.xpath("//h2[contains(text(),'FBO')]")),"FBO");
				System.out.println(getDriver().findElement(By.xpath("//input[@aria-label='Is FBO']")).getAttribute("value"));
				verifyExactAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Is FBO']")),"value",verifyIsFBO, "Is FBO");
				//FBORD
			}
		}
		return this;
	}
	public MemberFormPage navigateToSponsor() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Exclude from Roster')]")),"Exclude from Roster");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Receive Direct Mail')]")),"Receive Direct Mail");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Fee Share Eligible')]")),"Fee Share Eligible");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Fee Share Eligible Date')]")),"Fee Share Eligible Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Business Key')]")),"Business Key");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'BK Active')]")),"BK Active");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Payment Entity')]")),"Payment Entity");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Corporate Rebate')]")),"Corporate Rebate");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Record Status')]")),"Record Status");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Record Change Status')]")),"Record Change Status");
		clickTab(3);

		return this;
	}

	public MemberFormPage navigateToSponsorMemberForm() {
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Exclude from Roster')]")),"Exclude from Roster");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Participation Type')]")),"Participation Type");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Provider Select: MD')]")),"Provider Select: MD");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Business Key')]")),"Business Key");
		return this;
	}

	public MemberFormPage navigateToCorporateParent() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_issponsor.fieldControl-option-set-select']")),"is Sponsor");
		Actions actions = new Actions(getDriver());
		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();

		//click(getDriver().findElement(By.xpath("//h2[@title='Corporate Parent']")),"CP");


		return this;
	}

	public MemberFormPage navigateToCorporateParentMemberoForm() {
		navigateToSponsorMemberForm();
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Is Sponsor')]")),"is Sponsor");
		Actions actions = new Actions(getDriver());
		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();
		click(getDriver().findElement(By.xpath("//h2[@title='CORPORATE PARENT']")),"CP");


		return this;
	}


	public MemberFormPage navigateToFoodServices() {
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Is Corporate Account')]")),"Is Corporate Account");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Corporate Parent')]")),"Corporate Parent");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Corporate Parent Start Date')]")),"Corporate Parent Start Date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Corporate Parent Override')]")),"Corporate Parent Override");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Corporate Rebate Fee Date')]")),"Corporate Rebate Fee Date");

		return this;
	}
	//Verify Sponsor
	public MemberFormPage verifySponsor(String verifySponsor) {
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_sponsor.fieldControl-LookupResultsDropdown_ix_sponsor_selected_tag_text']")),verifySponsor,"Sponsor"); 
		return this;
	}

	//Verify if sponsor filed is blank
		public MemberFormPage verifySponsorIsNull() {
			verifyNullValueWithGetText(getDriver().findElement(By.xpath("//div[@data-id='ix_sponsor.fieldControl-Lookup_ix_sponsor']")),"Sponsor"); 
			return this;
		}



	//Verify Is sponsor
	public MemberFormPage verifyIsSponsor(String verifyIsSponsor) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_issponsor.fieldControl-option-set-select']")),verifyIsSponsor,"Is Sponsor"); 
		return this;
	}

	//Click Line of Business Tab
	public MemberFormPage clickLineOfBusinessesTab() throws InterruptedException   {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Line of Businesses']")),"LOB Tab");
		return this;
	}


	//Click Related and line of business
	public MemberFormPage clickLineOfBusinesses() throws InterruptedException   {
		Thread.sleep(2000);
		/*
		 * click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		 * Thread.sleep(2000);
		 */
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(80));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'LOB')]")));
		click(getDriver().findElement(By.xpath("//li[contains(text(),'LOB')]")),"Line Of Businessess");
		Thread.sleep(2000);
		return this;
	}

	//Click Related and line of business
	public MemberFormPage clickLineOfBusinessesMEF() throws InterruptedException   {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(2000);

		click(getDriver().findElement(By.xpath("//div[@role='menuitem']//span[contains(text(),'Line of Business')]")),"Line Of Businessess");
		//Thread.sleep(5000);
		return this;
	}
	//Verify Line of Business  Associated View
	public MemberFormPage verifyLineOfBusinesAssocView() throws InterruptedException   {
		verifyIsEnabled(getDriver().findElement(By.xpath("//span[text()='Line of Business Associated View']")), "Line of Business Associated View");
		Thread.sleep(2000);
		return this;
	}

	//Verify the food service LOB
	public MemberFormPage verifyFoodServiceLOB() {
		//~~Invalid Function -isDisplayed doesn't work~~
		//verifyDisplayed(getDriver().findElement(By.xpath("//label[@aria-label='Food Services']")), "Food Service LOB");

		//~~verifying LOB and Classification Type using get attributes. Assigning to variable (attributevalue) to be used in compare methods below
		LOB=getAttribute(getDriver().findElement(By.xpath("(//div[contains(text(),'Food Services')])[2]")), "textContent", "Food Service LOB");
		attributevalue=getAttribute(getDriver().findElement(By.xpath("//div[contains(text(),'Food Services')]/ancestor::div[@col-id='ix_portfolio']/following-sibling::div[@col-id='ix_classificationtypenew']//a//span")), "textContent", "Classtification type");
		return this;
	}

	public MemberFormPage getClassificationType() {

		Classificationbefore=attributevalue;
		return this;
	}

	public MemberFormPage getClassificationTypeAfter() {

		Classificationafter=attributevalue;
		return this;
	}

	public MemberFormPage compareClassificationtype() {

		Assert.assertTrue(!((Classificationbefore.contains(Classificationafter))));
		return this;
	}


	//Verify the food service LOB is not displayed
	public MemberFormPage verifyFoodServiceLOBisNotDisplayed() {
		verifyElementisNotDisplayed(rownumber, "FoodServiceLOB");

		return this;
	}

	//Add New Line Of Business Button
	public MemberFormPage verifyNewLineOfBusinessIsNotPresent() {
		List<WebElement> lob= getDriver().findElements(By.xpath("//span[contains(text(),'New Line of Business')]"));
		verifyElementisNotDisplayed(lob.size()," ' + New Line of Business  ' Button ");
		return this;
	}

	//Verify LOB is not present in the list
	public MemberFormPage verifyLOBIsNotPresent(String LineOfBusiness) {
		List<WebElement> LOB= getDriver().findElements(By.xpath("//span[contains(text(),'"+LineOfBusiness+"')]"));
		verifyElementisNotDisplayed(LOB.size(),"Line of Business : "+LineOfBusiness+" ");
		return this;
	}

	// Verify Save and New button is not displayed
	public MemberFormPage verifySaveAndNewButtonNotDisplayed() {
		List<WebElement> saveAndNewButton= getDriver().findElements(By.xpath("//span[contains(text(),'Save & New')]"));
		verifyElementisNotDisplayed(saveAndNewButton.size(), "Save and New Button");
		return this;
	}
	//Click on add new LineOfBusiness - 09/05/2023 WebDriver Wait
	public MemberFormPage clickAddNewLineOfBusiness() throws InterruptedException   {
		List<WebElement> addnewbutton=getDriver().findElements(By.xpath("//*[@data-id='ix_portfoliocategory|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_portfoliocategory.AddNewStandard']"));
		if(addnewbutton.size()>0) {
			click(getDriver().findElement(By.xpath("//*[@data-id='ix_portfoliocategory|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_portfoliocategory.AddNewStandard']")),"Add New LOB");
		}
		else {
			click(getDriver().findElement(By.xpath("//*[@data-id='ix_portfoliocategory|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_portfoliocategory.AddNewStandard']")),"Add New LOB");
		}
		Thread.sleep(2000);
		try
		{
			if ( getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")).isDisplayed())
			{
				click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Save and continue");
				//span[contains(text(),'Save and Close')]
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();	
		}
		Thread.sleep(5000);
		return this;
	}

	//Click on add new LineOfBusiness - 10/04/2023 WebDriver Wait
	public MemberFormPage clickAddNewLineOfBusinessMEF() throws InterruptedException   {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));


		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-id='ix_portfoliocategory|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_portfoliocategory.AddNewStandard']")));
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_portfoliocategory|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_portfoliocategory.AddNewStandard']")),"Add New LOB");
		Thread.sleep(2000);
		try
		{
			if ( getDriver().findElement(By.xpath("//span[contains(text(),'Save and continue')]")).isDisplayed())
			{
				click(getDriver().findElement(By.xpath("//span[contains(text(),'Save and continue')]")),"Save and continue");
				//span[contains(text(),'Save and Close')]
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();	
		}
		Thread.sleep(3000);
		return this;
	}


	//Click on add new LineOfBusiness - 09/05/2023 WebDriver Wait
	public MemberFormPage AddNewLineOfBusiness() throws InterruptedException   {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));


		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-id='ix_portfoliocategory|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_portfoliocategory.AddNewStandard']")));
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_portfoliocategory|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_portfoliocategory.AddNewStandard']")),"Add New LOB");
		Thread.sleep(2000);
		try
		{
			if ( getDriver().findElement(By.xpath("//span[contains(text(),'Save and continue')]")).isDisplayed())
			{
				click(getDriver().findElement(By.xpath("//span[contains(text(),'Save and continue')]")),"Save and continue");
				//span[contains(text(),'Save and Close')]
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();	
		}
		Thread.sleep(3000);
		return this;
	}
	//Select Line of business
	public MemberFormPage selectLineOfBusiness(String lineOfBusiness) throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_portfolio.fieldControl-option-set-select']")),"Select LOB");
		if(lineOfBusiness.contentEquals("General GPO")  || lineOfBusiness.contentEquals("Pharmacy")) {
			click(getDriver().findElement(By.xpath("(//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+lineOfBusiness+"')])[2]")),"LOB");
		}else {
			click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+lineOfBusiness+"')]")),"LOB");	
		}

		//button[@data-id='ix_portfolio.fieldControl-option-set-select']
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_portfolio.fieldControl-option-set-select']")),lineOfBusiness,"LOB");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_portfolio.fieldControl-option-set-select']")),lineOfBusiness,"LOB");
		//	click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"SCRIPT ERROR");
		return this;
	}


	//Select LOB Classification type
	public MemberFormPage selectLOBfClassificationType(String lineOfClassification) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_classificationtypenew.fieldControl-LookupResultsDropdown_ix_classificationtypenew_textInputBox_with_filter_new']")),"Line of classification");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_classificationtypenew.fieldControl-LookupResultsDropdown_ix_classificationtypenew_textInputBox_with_filter_new']")))),lineOfClassification,"Line of classification");
		Thread.sleep(3000);
		if(getDriver().findElements(By.xpath("//span/span[contains(text(),'"+lineOfClassification+"')][2]")).size()>0) {
			click(getDriver().findElement(By.xpath("//span/span[contains(text(),'"+lineOfClassification+"')][2]")),lineOfClassification);
		}
		click(getDriver().findElement(By.xpath("//span/span[contains(text(),'"+lineOfClassification+"')][1]")),lineOfClassification);
		return this;
	}

	//Select LOB Classification type
	public MemberFormPage selectLOBfClassificationTypeIntersectta(String lineOfClassification) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_classificationtypenew.fieldControl-LookupResultsDropdown_ix_classificationtypenew_textInputBox_with_filter_new']")),"Line of classification");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_classificationtypenew.fieldControl-LookupResultsDropdown_ix_classificationtypenew_textInputBox_with_filter_new']")))),lineOfClassification,"Line of classification");
		Thread.sleep(5000);
		Actions action=new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//span/span[contains(text(),'"+lineOfClassification+"')]"))).click().build().perform();


		return this;
	}




	//Select LOB Classification type
	public MemberFormPage selectLOBfClassificationType2(String lineOfClassification) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_classificationtypenew.fieldControl-LookupResultsDropdown_ix_classificationtypenew_textInputBox_with_filter_new']")),"Line of classification");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_classificationtypenew.fieldControl-LookupResultsDropdown_ix_classificationtypenew_textInputBox_with_filter_new']")))),lineOfClassification,"Line of classification");
		Thread.sleep(3000);

		//Actions a = new Actions(getDriver());
		click(getDriver().findElement(By.xpath("(//*[normalize-space()='"+lineOfClassification+"'])[2]")),"LOB Classification");
		//		Thread.sleep(2000);
		//		a.moveToElement(getDriver().findElement(By.xpath("//label[contains(@id,'.fieldControl-advlookup')]"))).click().build().perform();
		//		Thread.sleep(6000);
		//		click(getDriver().findElement(By.xpath("(//i[@data-icon-name='CheckMark'])[7]")),"Status CheckMark");
		//		Thread.sleep(2000);
		//		click(getDriver().findElement(By.xpath("//span[contains(text(),'Done')]")),"Done Button");
		return this;
	}


	//Select LOB Classification type
	public MemberFormPage selectLOBfClassificationTypeAcurity(String lineOfClassification) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_classificationtypenew.fieldControl-LookupResultsDropdown_ix_classificationtypenew_textInputBox_with_filter_new']")),"Line of classification");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_classificationtypenew.fieldControl-LookupResultsDropdown_ix_classificationtypenew_textInputBox_with_filter_new']")))),lineOfClassification,"Line of Business -classification");
		Thread.sleep(5000);

		click(getDriver().findElement(By.xpath("(//*[normalize-space()='"+lineOfClassification+"'])[2]")),lineOfClassification);
		//		Actions a = new Actions(getDriver());

		//		a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='ix_classificationtypenew.fieldControl-advlookup']"))).click().build().perform();
		//		Thread.sleep(3000);
		//		click(getDriver().findElement(By.xpath("//label[contains(text(),'Acurity')]")),"Acurity LOB");
		//
		//		click(getDriver().findElement(By.xpath("//*[contains(text(),'Done')]")),"Done button");
		//

		/*
		for(int i=0;i<3;i++) {
			a.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(3000);
		}
		a.sendKeys(Keys.ENTER).build().perform();

		 */
		return this;
	}

	//Select LOB Classification type
	public MemberFormPage selectLOBfClassificationTypeNational(String lineOfClassification) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_classificationtypenew.fieldControl-LookupResultsDropdown_ix_classificationtypenew_textInputBox_with_filter_new']")),"Line of classification");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_classificationtypenew.fieldControl-LookupResultsDropdown_ix_classificationtypenew_textInputBox_with_filter_new']")))),lineOfClassification,"Line of classification");
		Thread.sleep(3000);
		Actions a = new Actions(getDriver());

		a.moveToElement(getDriver().findElement(By.xpath("//label[@data-id='ix_classificationtypenew.fieldControl-advlookup']"))).click().build().perform();
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//label[contains(text(),'National')]")),"National LOB");

		click(getDriver().findElement(By.xpath("//*[contains(text(),'Done')]")),"Done button");


		/*
			for(int i=0;i<3;i++) {
				a.sendKeys(Keys.TAB).build().perform();
				Thread.sleep(3000);
			}
			a.sendKeys(Keys.ENTER).build().perform();

		 */
		return this;
	}

	public MemberFormPage deactivateLOB() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Deactivate')]")),"Deactivate Button");
		verifyDisplayed(getDriver().findElement(By.xpath("//h1[@aria-label='Confirm Deactivation']")), "Deactivation Dialog Box");
		click((getDriver().findElement(By.xpath("//button[@data-id='ok_id']"))),"Deactivate button");
		Thread.sleep(5000);
		return this;
	}

	public MemberFormPage verifyErrorMessageLOBDeactivate() throws InterruptedException{
		verifyDisplayed(getDriver().findElement(By.xpath("//h2[@aria-label='Published Member Account must have at least one active LOB']")), "Deactivation Dialog Box");
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"OK button");
		Thread.sleep(3000);
		return this;
	}


	public MemberFormPage verifyErrorMessageLOBisNotDeactivate() throws InterruptedException{
		verifyDisplayed(getDriver().findElement(By.xpath("//h2[contains(@aria-label,'deactivate the LOB from published account')]")), "Deactivation Dialog Box");
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"Okay button");
		Thread.sleep(5000);
		return this;
	}


	public MemberFormPage activateLOB() {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Activate')]")),"Activate LOB");
		verifyDisplayed(getDriver().findElement(By.xpath("//h1[@aria-label='Confirm Line of Business Activation']")), "Dialog Box");
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id' and @title='Activate']")),"Activate Button");
		//click(getDriver().findElement(By.xpath("//button[data-id='ok_id']")),"Activate Button");
		return this;
	}

	//Stale Element -Do Not use after  Wave 1 2023
	public MemberFormPage selectLOB(String lineOfBusiness) throws InterruptedException {
		Thread.sleep(3000);
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//div[contains(text(),'"+lineOfBusiness+"')]"))).doubleClick().build().perform();
		return this;
	}

	//Wave 1 2023 -new method
	public MemberFormPage doubleClickExistingLineOfBusiness(String lineOfBusiness) throws InterruptedException   {
		Thread.sleep(4000);
		WebElement table =getDriver().findElement(By.xpath("//div[@col-id='ix_portfolio']"));
		List<WebElement> rowList = table.findElements(By.xpath("//div[@col-id='ix_portfolio']//div//label"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 1; i <=rowList.size(); i++) {
			String lob = getDriver().findElement(By.xpath("(//div[@col-id='ix_portfolio']//div//label)["+i+"]")).getText();
			System.out.println(lob);					
			if (lob.equals(lineOfBusiness)) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//div[@col-id='ix_portfolio']//div//label)["+i+"]")), lineOfBusiness);
				Thread.sleep(3000);
				break;				
			}
		}		

		return this;					
	}

	public MemberFormPage selectDeactivatedLOB() throws InterruptedException {
		Thread.sleep(3000);
		if((getDriver().findElements(By.xpath("(//span[contains(@id,'ViewSelector')])[4]")).size()>0)) {
			System.out.println("Dropdown is available");
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[4]")),"Select View");
			click(getDriver().findElement(By.xpath("//*[contains(text(),'Deactivated Portfolio Category')]")),"Deactivated Portfolio");}
		else {System.out.println("Dropdown is NOT available");}
		Thread.sleep(2000);
		return this;

	}

	public MemberFormPage selectCurrentMemberShip() throws InterruptedException {
		Thread.sleep(2000);

		if((getDriver().findElements(By.xpath("(//span[contains(@id,'ViewSelector')])[6]")).size()>0)){
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[6]")),"Select View");
		}else {

			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[4]")),"Select View");
		}
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Current Memberships')]")),"Current Memberships");
		Thread.sleep(2000);
		return this;

	}

	public MemberFormPage selectCurrentMemberShipview() throws InterruptedException {
		Thread.sleep(2000);

		if((getDriver().findElements(By.xpath("(//span[contains(@id,'ViewSelector')])[6]")).size()>0)){

			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[6]")),"Select View");
		}else if(((getDriver().findElements(By.xpath("(//span[contains(@id,'ViewSelector')])[4]")).size()>0))) {
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[4]")),"Select View");
		}
		else {

			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[2]")),"Select View");
		}
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Current Memberships')]")),"Current Memberships");
		Thread.sleep(2000);
		return this;

	}




	public MemberFormPage selectactivateLOB() throws InterruptedException {

		click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[4]")),"Select View");
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Active Line of Business')]")),"Active Line of Business");
		Thread.sleep(2000);
		return this;

	}


	public MemberFormPage verifyEndDate(String endDate) {

		verifyExactValue(getDriver().findElement(By.xpath("//input[@aria-label='Date of End Date']")), endDate, "End Date");
		return this;
	}

	//Click on deactivate in membership provider window
	public MemberFormPage clickDeleteMembershipProvider() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membership|NoRelationship|Form|Mscrm.Form.ix_membership.Delete']")),"Delete");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='confirmButton']")),"Confirm Delete");
		Thread.sleep(3000);
		//	click(getDriver().findElement(By.xpath("//*[@aria-label='OK']")),"Ok");
		//Thread.sleep(3000);
		return this;	
	}

	//Click on deactivate in membership provider window
	public MemberFormPage clickDeactivateMembershipProvider() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membership|NoRelationship|Form|Mscrm.Form.ix_membership.Deactivate']")),"Deactivate");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")),"Confirm Deactivate");
		Thread.sleep(3000);
		return this;	
	}

	//Click on Deactivate member 
	public MemberFormPage clickDeactivateMember() {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Deactivate')]")),"Deactivate Member");
		getDriver().findElement(By.xpath("//div//h1[@aria-label='Confirm Deactivation']")).isDisplayed();
		getDriver().findElement(By.xpath("//button[@title='Deactivate' and @data-id='ok_id']")).isDisplayed();
		return this;
	}


	//Verify Member supervisor could not deactivate error message

		public MemberFormPage verifyDeactivateError(String Expected_Error) throws InterruptedException {
			click(getDriver().findElement(By.xpath("//button[@title='Deactivate' and @data-id='ok_id']")),"Deactivate button");
			Thread.sleep(5000);
			verifyExactText(getDriver().findElement(By.xpath("//h2[@data-id='errorDialog_subtitle']")),Expected_Error,"Deactivate Error");
			click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"OK Button");
			return this;
		}

	//Type Line Of Business Start Date
	public MemberFormPage typeLineOfBusinessStartDate(String lineOfBusinessStartDate) throws InterruptedException {
		Thread.sleep(3500);
		type(((getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date']")))),lineOfBusinessStartDate,"Line of Bussiness Start Date");
		return this;
	}

	//Click on LOB Save and close
	public MemberFormPage clickLOBSaveAndClose() throws InterruptedException {
		//click(getDriver().findElement(By.xpath("//button[contains(@id,'quickCreateSaveAndCloseBtn')]//span[contains(text(),'Save')]")),"Save and Close");
		//Wave2 Fix
		if(getDriver().findElements(By.xpath("//section[@data-id='quickCreateRoot']//button[@aria-label='Save and Close']")).size()>0){
			click(getDriver().findElement(By.xpath("//section[@data-id='quickCreateRoot']//button[@aria-label='Save and Close']")),"Save and Close");
		}else {
			click(getDriver().findElement(By.xpath("//section[@data-id='quickCreateRoot']//button[@aria-label='Save & Close']")),"Save and Close");
		}
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='GENERAL']")));	
		click(getDriver().findElement(By.xpath("//*[@title='GENERAL']")),"GENERAL");
		Thread.sleep(3000);
		return this;	
	}

	//Click on LOB Save and close without navigating to General Tab
	public MemberFormPage clickLOBSaveAndCloseWithoutGeneralTab() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@aria-label='Save & Close']")),"Save and Close");
		Thread.sleep(3000);
		return this;	
	}
	//save and Click in dev1
	public MemberFormPage clickLOBSaveAndCloseDev() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@aria-label='Save and Close']")),"Save and Close");
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='GENERAL']")));	
		click(getDriver().findElement(By.xpath("//*[@title='GENERAL']")),"GENERAL");
		Thread.sleep(5000);
		return this;	
	}

	//click Save and Close
	public MemberFormPage clickSaveAndCloseInEditPage() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//span[contains(text(),'Save & Close')]")),"Save and Close button");
		Thread.sleep(5000);
		return this;
	}

	//Click on add new membership
	public MemberFormPage clickAddNewMembership() throws InterruptedException   {
		Thread.sleep(3000);
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		click(getDriver().findElement(By.xpath("(//*[text()='Membership'])[2]")),"Membership");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membership|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_membership.AddNewStandard']")),"New Membership");
		Thread.sleep(3000);
		return this;
	}

	//Click on add new membership
	public MemberFormPage clickMembershipAndAddNewMembership() throws InterruptedException   {
		Thread.sleep(2000);
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		click(getDriver().findElement(By.xpath("//div[@role='menuitem'] //*[contains(text(),'Membership')]")),"Membership");
		Thread.sleep(3000);
		selectCurrentMemberShipview();
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membership|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_membership.AddNewStandard']")),"New Membership");
		Thread.sleep(3000);
		return this;
	}


	//Add 

	public MemberFormPage clickAddNewMembershipButton() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membership|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_membership.AddNewStandard']")),"New Membership");
		return this;
	}
	//Select Membership provider type of business
	public MemberFormPage selectMembershipType(String membershipProviderType) throws InterruptedException   {

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_membershiptype.fieldControl-option-set-select']")),"MEmbership Provided Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+membershipProviderType+"')]")),"Membership Type");


		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_membershiptype.fieldControl-option-set-select']")),membershipProviderType,"Membership Provider Type");
		Thread.sleep(3000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_membershiptype.fieldControl-option-set-select']")),membershipProviderType,"Membership Provider Type");
		return this;
	}

	//Select Membership provider
	public MemberFormPage selectMembershipProvider_2(String membershipProvider) throws InterruptedException   {	
		//click(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-LookupResultsDropdown_ix_membershipprovider_textInputBox_with_filter_new']")),"Membership Provider");
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-LookupResultsDropdown_ix_membershipprovider_textInputBox_with_filter_new']")),membershipProvider,"Membership Provider");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//li[contains(@id,'ix_membershipprovider.fieldControl-LookupResultsDropdown_ix_membershipprovider')]")),"Membership Provider");
		return this;
	}

	//Select Membership provider- 09/05/2023 WebDriver Wait
	public MemberFormPage selectMembershipProvider(String membershipProvider) throws InterruptedException   {	
		//click(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-LookupResultsDropdown_ix_membershipprovider_textInputBox_with_filter_new']")),"Membership Provider");
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-LookupResultsDropdown_ix_membershipprovider_textInputBox_with_filter_new']")),membershipProvider,"Membership Provider");
		Thread.sleep(3000);
		setReport().log(Status.PASS, "Clicked on the Membership Provider successfully", screenshotCapture());
		//Thread.sleep(10000);
		List<WebElement> membershipProvidername=getDriver().findElements(By.xpath("//span[contains(@data-id,'ix_membershipprovider.fieldControl-nam')]/span[contains(text(),'"+membershipProvider+"')]"));
		if(membershipProvidername.size()>0) {
			click(getDriver().findElement(By.xpath("//span[contains(@data-id,'ix_membershipprovider.fieldControl-nam')]/span[contains(text(),'"+membershipProvider+"')]")),"Membership Provider");	
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@data-id,'ix_membershipprovider.fieldControl-accountnumber')]/span[contains(text(),'"+membershipProvider+"')]")),"Membership Provider");
		}
		Thread.sleep(3000);
		return this;
	}

	//Select Membership provider
	public MemberFormPage TyprMembershipProvider(String membershipProvider) throws InterruptedException   {	
		//click(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-LookupResultsDropdown_ix_membershipprovider_textInputBox_with_filter_new']")),"Membership Provider");
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-LookupResultsDropdown_ix_membershipprovider_textInputBox_with_filter_new']")),membershipProvider,"Membership Provider");
		Thread.sleep(3000);
		return this;
	}

	//Type Membership Start Date
	public MemberFormPage typeMembershipStartDate(String membershipStartDate) {
		clearAllSuggestions();
		type(((getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date']")))),membershipStartDate,"Membership Start Date");
		return this;
	}

	//Click on Membership SAve and close //Quick create
	public MemberFormPage clickQuickCreateMembershipSaveAndClose() throws InterruptedException {

		//Updated on 2/12/2024 based on Ticket 1010274


		//click(getDriver().findElement(By.xpath("//*[@data-id='quickCreateSaveAndCloseBtn']")),"Save and Close");

		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membership|NoRelationship|Form|Mscrm.Form.ix_membership.SaveAndClose']")),"Save and Close");
		Thread.sleep(7000);
		click(getDriver().findElement(By.xpath("//*[@title='GENERAL']")),"GENERAL");
		Thread.sleep(5000);
		return this;	
	}

	//Click on Membership SAve and close //Quick create
	public MemberFormPage clickQuickCreateMembershipSave() throws InterruptedException {

		//Updated on 2/12/2024 based on Ticket 1010274


		//click(getDriver().findElement(By.xpath("//*[@data-id='quickCreateSaveAndCloseBtn']")),"Save and Close");

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_membership|NoRelationship|Form|Mscrm.Form.ix_membership.Save']")),"Save");
		Thread.sleep(5000);
		return this;	
	}




	//Click on Membership SAve and close //Quick create
	public MemberFormPage clickQuickCreateMembershipSave_Close() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='quickCreateSaveAndCloseBtn']")),"Save and Close");
		Thread.sleep(7000);
		return this;	
	}
	//Click on Membership SAve and close 
	public MemberFormPage clickMembershipSaveAndClose() throws InterruptedException {
		
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membership|NoRelationship|Form|Mscrm.Form.ix_membership.SaveAndClose']")),"Save and Close");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[@title='GENERAL']")),"GENERAL");
		Thread.sleep(5000);
		return this;	
	}	


	//Clcikc Membership Save and Close button
	public MemberFormPage clickMembershipSaveAndCloseButton() throws InterruptedException {

		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membership|NoRelationship|Form|Mscrm.Form.ix_membership.SaveAndClose']")),"Save and Close");
		Thread.sleep(5000);
		return this;	
	}	
	//Verify LOB required Error message
	public MemberFormPage verifyReactivateErrorMessage() throws InterruptedException {
		String errorMessage="Please re-activate the account by creating the membership";
		Thread.sleep(5000);
		verifyDisplayed(getDriver().findElement(By.xpath("//h2[contains(@aria-label,'"+errorMessage+"')]")),"LOB Required");
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"Ok Button");
		return this;

	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Verify Account Status
	public MemberFormPage verifyAccountStatusValue(String verifyAcountStatus) throws InterruptedException {
		Thread.sleep(3000);
		navigateToApplicationDate();
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//select[@data-id='ix_accountstatus.fieldControl-option-set-select']")),verifyAcountStatus,"Account Status");
		return this;
	}


	//Verify Account Status
	public MemberFormPage verifyAccountStatusValueinMEF(String verifyAcountStatus) throws InterruptedException {
		Thread.sleep(3000);
		navigateToApplicationDate();
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//select[@data-id='ix_accountstatus.fieldControl-option-set-select']")),verifyAcountStatus,"Account Status");
		return this;
	}


	//Select the Account status
	public MemberFormPage changetheAccountStatususingDropdown(String AcountStatus) throws InterruptedException {
		Thread.sleep(3000);
		Select RepresentativeType= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_accountstatus.fieldControl-option-set-select']")));
		RepresentativeType.selectByVisibleText(AcountStatus);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//select[@data-id='ix_accountstatus.fieldControl-option-set-select']")),AcountStatus,"Account Status");


		return this;
	}



	//click New Account representative button
	public MemberFormPage clickNewAccountRepresentativeButton() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[contains(text(),'New Account Representative')]")),"New Account Representative");
		return this;
	}

	//verify Representative Type dropdown
	public MemberFormPage verifyRepresentativeDropDown() throws InterruptedException {
		Select RepresentativeType= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_representativetype.fieldControl-option-set-select']")));		
		// Create Expected Array List
		List<String> expectedRepresentativeType = Arrays.asList("---","Employee Discounts Coordinator","Executive","Field","Internal","Manager");		
		//Create Actual blank Array List
		List<String> actualRepresentativeType=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =RepresentativeType.getOptions();		
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualRepresentativeType.add(data);			
			System.out.println("The Actual RepresentativeType is : "  + " " +data);				
			Thread.sleep(3000);
			if(expectedRepresentativeType.containsAll(actualRepresentativeType))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "RepresentativeType- " + "   " + data + "  " +  "-  Option is available to choose from the list" + " "+ expectedRepresentativeType,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "RepresentativeType - "+   "   " + data + "  " + "- Option is not available in the list"  + " "+ expectedRepresentativeType ,	screenshotCapture());
				Driver.failCount++;
			}

		}
		return this;

	}


	//Select related and account numbers
	public MemberFormPage selectAccountRepresentative() throws InterruptedException {	
		Thread.sleep(2000);
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Account Representatives')]")),"Account Representatives");
		Thread.sleep(2000);
		return this;
	}

	//Click on Discard changes
	public AccountsPage clickOnDiscardChangesNavigatetoAccountsPage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Discard Changes");
		Thread.sleep(3000);
		return new AccountsPage();
	}

	//Verify the Error message to end a membership when there is active child

	public MemberFormPage verifyCantTerminateMembershipError() {

		verifyDisplayed(getDriver().findElement(By.xpath("//h2[@aria-label='Account with active child accounts cannot be terminated']")), "Error message");
		click(getDriver().findElement(By.xpath("//*[@id='cancelButton']")),"Ok Button");
		return this;
	}
	//Click on Membership SAve and close 
	public MemberFormPage clickMembershipSave() throws InterruptedException {

		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Save')]")),"Save and Close");
		Thread.sleep(10000);

		return this;	
	}


	public MemberFormPage verifyTerminateStatus(String expectedString) throws InterruptedException {
		verifyExactText(getDriver().findElement(By.xpath("//div[contains(text(),'Account Status')]/preceding-sibling::div/div")),expectedString,"Terminated Status");
		Thread.sleep(5000);
		return this;

	}

	public MemberFormPage verifyChangedToIsTopParent(String expectedString) throws InterruptedException {
		verifyExactText(getDriver().findElement(By.xpath("//div[contains(text(),'Direct Parent')]/preceding-sibling::div/div")),expectedString,"Direct Parent");
		verifyExactText(getDriver().findElement(By.xpath("//div[contains(text(),'Top Parent')]/preceding-sibling::div/div")),expectedString,"Top Parent");
		Thread.sleep(5000);
		return this;

	}

	public MemberFormPage clickGoBack() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='navigateBackButtontab-id-0']")),"Go back");
		Thread.sleep(5000);
		List<WebElement> discarChanges=getDriver().findElements(By.xpath("//*[@data-id='cancelButton']"));
		if(discarChanges.size()>0) {
			click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Discard Changes");
		}
		List<WebElement> general=getDriver().findElements(By.xpath("//*[@title='GENERAL']"));
		if(general.size()>0) {
			click(getDriver().findElement(By.xpath("//*[@title='GENERAL']")),"GENERAL");	
		}else {
			click(getDriver().findElement(By.xpath("//*[@title='General']")),"General");
		}

		Thread.sleep(5000);
		return this;	
	}



	public MemberFormPage clickGoBackButton() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(180));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-id='navigateBackButtontab-id-0']")));

		click(getDriver().findElement(By.xpath("//*[@data-id='navigateBackButtontab-id-0']")),"Go back");
		Thread.sleep(5000);

		return this;	
	}



	public MemberFormPage verifyMemberAttributeDetails() {

		List<WebElement> ele=getDriver().findElements(By.xpath("//span[contains(text(),'GPO Rebate Aggregation')]"));
		verifyElementisDisplayed(ele.size(), "General Rebate Aggregation");

		ele=getDriver().findElements(By.xpath("//span[contains(text(),'Check Member Do Not Cascade')]"));
		verifyElementisDisplayed(ele.size(), "Check Member Do Not Cascaed");
		return this;
	}


	public MemberFormPage verifyMemberAttributeDetails_Pharmacy() {

		List<WebElement> ele=getDriver().findElements(By.xpath("//span[contains(text(),'Pharmacy Rebate Aggregation')]"));
		verifyElementisDisplayed(ele.size(), "Pharmacy Rebate Aggregation");

		ele=getDriver().findElements(By.xpath("//span[contains(text(),'Check Member Do Not Cascade')]"));
		verifyElementisDisplayed(ele.size(), "Check Member Do Not Cascade");
		return this;
	}

	public MemberFormPage clickGoBackandDiscardChanges() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='navigateBackButtontab-id-0']")),"Go back");
		Thread.sleep(6000);
		List<WebElement> discarChanges=getDriver().findElements(By.xpath("//button[@data-id='cancelButton']"));
		if(discarChanges.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@data-id='cancelButton']")),"Discard Changes");
		}

		Thread.sleep(5000);
		return this;	
	}



	//select Membership Entity
	public MemberFormPage goToMembershipPage(String membershipProvider) throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("//span[text()='"+membershipProvider+"'])");


		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+membershipProvider+"') and contains(@data-id,'MembershipSubGrid')]")),membershipProvider);
		Thread.sleep(3000);
		return this;
	}

	//select Specific Membership Entity
	public MemberFormPage selectMemberShipEntity(String membershipProvider) throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//div[contains(@data-id,'MembershipSubGrid')]//span[contains(text(),'"+membershipProvider+"')]")),membershipProvider);
		Thread.sleep(3000);
		return this;
	}


	//select Specific Membership Entity
	public MemberFormPage selectMembership(String membershipProvider) throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+membershipProvider+"')]")),membershipProvider);
		Thread.sleep(3000);
		return this;
	}

	//Click Deactivate button in the membership Page
	public MemberFormPage clickMembershipDeactivateButton() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//span[contains(text(),'Deactivate')]")),"Deactivate Button");
		verifyDisplayed(getDriver().findElement(By.xpath("//h1[@aria-label='Confirm Deactivation']")), "Deactivation Dialog Box");
		Thread.sleep(5000);
		return this;
	}


	//Click Confirm Deactivate Button
	public MemberFormPage clickMembershipDeactivateButtonConfirm() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")), "Deactivation button");
		Thread.sleep(6000);
		return this;
	}



	//Double Click On National Membership
	public MemberFormPage doubleClickOnNationalMembership() throws InterruptedException {	
		//verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='MscrmControls.Grid.ReadOnlyGrid|entity_control|account|7117ccdc-6223-4fd1-8e17-e210d527f53e|ix_membership|ix_account_ix_membership_AccountName|cc-grid|cc-grid-cell|cell-1-3']")),"National","Membership Provider");
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='cell-1-2']"))).doubleClick().build().perform();
		Thread.sleep(3000);
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-LookupResultsDropdown_ix_membershipprovider_selected_tag_text']")),"National","Membership Entity");
		return this;
	}

	//Double Click On National Membership
	public MemberFormPage doubleClickOnMembershi() throws InterruptedException {	
		//verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='MscrmControls.Grid.ReadOnlyGrid|entity_control|account|7117ccdc-6223-4fd1-8e17-e210d527f53e|ix_membership|ix_account_ix_membership_AccountName|cc-grid|cc-grid-cell|cell-1-3']")),"National","Membership Provider");
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='cell-1-2']"))).doubleClick().build().perform();
		Thread.sleep(3000);
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-LookupResultsDropdown_ix_membershipprovider_selected_tag_text']")),"National","Membership Entity");
		return this;
	}

	//Select TP Audit History
	public MemberFormPage selectMembershipAuditHistory() throws InterruptedException {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Audit History')]")),"Audit History");
		Thread.sleep(2000);
		return this;
	}

	public MemberFormPage verifyupdateinAudithistory(String feild, String data) throws InterruptedException{
		Thread.sleep(11000);
		getDriver().switchTo().frame("audit_iframe");
		boolean isfeildlisted=false;
		boolean isvaluelisted=false;
		List<WebElement> feildname=getDriver().findElements(By.xpath("//table[@ologicalname='audit']//tbody/tr[1]/td[5]//nobr"));
		for(int i=1;i<=feildname.size();i++) {
			System.out.println(getDriver().findElement(By.xpath("(//table[@ologicalname='audit']//tbody/tr[1]/td[5]//nobr)["+i+"]")).getText());
			if(getDriver().findElement(By.xpath("(//table[@ologicalname='audit']//tbody/tr[1]/td[5]//nobr)["+i+"]")).getText().contains(feild)) {
				isfeildlisted=true;
				break;
			}
		}

		List<WebElement> updatedvalue=getDriver().findElements(By.xpath("//table[@ologicalname='audit']//tbody/tr[1]/td[7]//nobr"));
		for(int i=1;i<=updatedvalue.size();i++) {
			System.out.println(getDriver().findElement(By.xpath("(//table[@ologicalname='audit']//tbody/tr[1]/td[5]//nobr)["+i+"]")).getText());
			if(getDriver().findElement(By.xpath("(//table[@ologicalname='audit']//tbody/tr[1]/td[7]//nobr)["+i+"]")).getText().contains(data)) {
				isvaluelisted=true;
				break;
			}
		}

		getDriver().switchTo().defaultContent();
		Assert.assertTrue("Updated feild is not displayed",isfeildlisted);
		Assert.assertTrue("Updated feild is not displayed",isfeildlisted);
		return this;
	}

	//Select Rebate Payment
	public MemberFormPage selectRebatePayment() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> rebateoption= getDriver().findElements(By.xpath("//li[@title='Rebate Payments']"));
		if(rebateoption.size()>0) {
			click(getDriver().findElement(By.xpath("//li[@title='Rebate Payments']")), "Rebate option");
		}else {
			if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
				click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
			}else {
				click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
			}
		}
		Thread.sleep(1000);
		click(getDriver().findElement(By.xpath("//*[text()='Rebate Payments']")),"Related > Rebate Payments");
		//click(getDriver().findElement(By.xpath("//*[contains(text(),'')]")),"Rebate Payments");
		Thread.sleep(2000);
		return this;
	}

	//Select Prescription Data
	public MemberFormPage selectProgramCategories() throws InterruptedException {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[text()='Program Categories']")),"Related > Program Categories");
		//click(getDriver().findElement(By.xpath("//*[contains(text(),'')]")),"Rebate Payments");
		Thread.sleep(2000);
		return this;
	}
	//Verify Program categories page is loaded

	public MemberFormPage verifyProgramCategoryPage() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[contains(text(),'Program Category Associated View')]")).size(),"Program Category view" );
		return this;
	}


	//Select Prescription Data
	public MemberFormPage selectPrescriptionData() throws InterruptedException {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[text()='Prescription Data']")),"Related > Prescription Data");
		//click(getDriver().findElement(By.xpath("//*[contains(text(),'')]")),"Rebate Payments");
		Thread.sleep(2000);
		return this;
	}


	//Select Patient Serivces
	public MemberFormPage selectPatientServices() throws InterruptedException {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//*[text()='Patient Services'])[2]")),"Related > Patient Services");
		//click(getDriver().findElement(By.xpath("//*[contains(text(),'')]")),"Rebate Payments");
		Thread.sleep(2000);
		return this;
	}


	//Select Supplier Account Number
	public MemberFormPage selectRelatedSupplierAccounts() throws InterruptedException {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[text()='Supplier Accounts']")),"Related > Supplier Accounts");
		//click(getDriver().findElement(By.xpath("//*[contains(text(),'')]")),"Rebate Payments");
		Thread.sleep(2000);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[contains(@id,'ViewSelector') and contains(text(),'Supplier Account Associated View')]")).size(), "View option");
		return this;
	}

	//Verify Market Category on COT
	public MemberFormPage verifyMarketCategoryHealthCareOnCOT() throws InterruptedException {
		Thread.sleep(2000);
		String cOT =getAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_classoftradedetail.fieldControl-LookupResultsDropdown_ix_classoftradedetail_selected_tag_text']")), "title", "COT");
		System.out.println(cOT);
		click(getDriver().findElement(By.xpath("//div[contains(@id,'ix_classoftradedetail.fieldControl-LookupResultsDropdown_ix_classoftradedetail')][@title='"+cOT+"']")),"COT");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//option[contains(@id,'ix_marketcategory.fieldControl-checkbox-select')and contains(text(),'Healthcare')][@data-selected='true']")).size(), "Market Category- Health Care ");
		return this;
	}

	//Verify Market Category-Non Health Care on COT
	public MemberFormPage verifyMarketCategoryNonHealthCareOnCOT() throws InterruptedException {
		Thread.sleep(2000);
		String cOT =getAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_classoftradedetail.fieldControl-LookupResultsDropdown_ix_classoftradedetail_selected_tag_text']")), "title", "COT");
		System.out.println(cOT);
		click(getDriver().findElement(By.xpath("//div[contains(@id,'ix_classoftradedetail.fieldControl-LookupResultsDropdown_ix_classoftradedetail')][@title='"+cOT+"']")),"COT");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//option[contains(@id,'ix_marketcategory.fieldControl-checkbox-select')and contains(text(),'Non-Healthcare')][@data-selected='true']")).size(), "Market Category- Non-Healthcare ");
		return this;
	}


	//click new Prescription data button
	public MemberFormPage clickPrescritpionDataButton() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'New Prescription Data')]")),"New Rebate Button");
		Thread.sleep(2000);
		verifIsNoTNullWithText(getDriver().findElement(By.xpath("//div[@data-id='ix_accountid.fieldControl-LookupResultsDropdown_ix_accountid_selected_tag_text']")), "Account Name");
		return this;
	}

	//click new rebate button
	public MemberFormPage clickNewRebateButton() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'New Rebate Payment')]")),"New Rebate Button");
		Thread.sleep(2000);
		verifIsNoTNullWithText(getDriver().findElement(By.xpath("//div[@data-id='ix_account.fieldControl-LookupResultsDropdown_ix_account_selected_tag_text']")), "Account Name");
		return this;
	}

	//click new 
	public MemberFormPage clickNewPatientServices() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'New Patient Services')]")),"New Patient Services");
		Thread.sleep(2000);
		return this;
	}

	//click new Supplier Account button
	public MemberFormPage clickNewSupplierAccountButton() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@aria-label='New Supplier Account. Add New Supplier Account']")),"New Aupplier Account");
		verifIsNoTNullWithText(getDriver().findElement(By.xpath("//div[@data-id='ix_accountid.fieldControl-LookupResultsDropdown_ix_accountid_selected_tag_text']")),"Account Name");
		Thread.sleep(2000);
		return this;
	}

	//Add Supplier Account
	public MemberFormPage addSupplierAccountDetails(String supplier, String supplierRep, String purchasingPreference,String memberLevel, String startdate) throws InterruptedException {

		click(getDriver().findElement(By.xpath("//input[@data-id='ix_supplierid.fieldControl-LookupResultsDropdown_ix_supplierid_textInputBox_with_filter_new']")),"Supplier");
		Thread.sleep(3000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_supplierid.fieldControl-LookupResultsDropdown_ix_supplierid_textInputBox_with_filter_new']")))),supplier,"Supplier");
		Thread.sleep(9000);
		click(getDriver().findElement(By.xpath("//span[contains(@data-id,'ix_supplierid.fieldControl-ix_businessclassification')]")),"Supplier");

		click(getDriver().findElement(By.xpath("//input[@data-id='ix_suppliercontact.fieldControl-LookupResultsDropdown_ix_suppliercontact_textInputBox_with_filter_new']")),"Supplier Rep");
		Thread.sleep(3000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_suppliercontact.fieldControl-LookupResultsDropdown_ix_suppliercontact_textInputBox_with_filter_new']")))),supplierRep,"Supplier Rep");
		Thread.sleep(9000);
		click(getDriver().findElement(By.xpath("//li[@data-id='ix_suppliercontact.fieldControl-LookupResultsDropdown_ix_suppliercontact_resultsContainer']")),"Supplier Rep");

		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Purchasing Preference']")), purchasingPreference, "Purchasing Preference");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Member Level']")), memberLevel, "Member Level");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")),startdate, "Start Date");
		click(getDriver().findElement(By.xpath("//input[@aria-label='Pharmacy']")),"Pharmacy Check box");
		click(getDriver().findElement(By.xpath("//input[@aria-label='Non-Pharmacy']")),"Non- Pharmacy Check box");
		click(getDriver().findElement(By.xpath("//input[@aria-label='Food']")),"Food Check box");
		click(getDriver().findElement(By.xpath("//input[@aria-label='Blood']")),"Blood Check box");
		click(getDriver().findElement(By.xpath("//input[@aria-label='Vaccines']")),"Vaccines Check box");
		return this;
	}

	//clickSavesupplierAccount
	public MemberFormPage saveSupplierAccount() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@aria-label='Save (CTRL+S)']")),"Save button");

		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");

		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		Thread.sleep(5000);
		return this;
	}

	//clickSavesupplierAccount
	public MemberFormPage saveAndCloseSupplierAccount() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Save & Close')]")),"Save and Close button");

		Thread.sleep(5000);
		return this;
	}



	//Navigate to Account number in the Supplier Account
	public MemberFormPage navigateToAccountNumber() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//li[@aria-label='General']/following-sibling::li[@title='Account Numbers']")),"Account Numbers");
		return this;

	}

	//Navigate to Account number in the Supplier Account
	public MemberFormPage navigateToAccountNumberTab() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//li[@aria-label='General']/following-sibling::li[contains(@title,'Account Numbers')]")),"Account Numbers");
		return this;

	}



	//Navigate to Supplier Account Tab
	public MemberFormPage navigateToSupplierAccountTab() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//li[@aria-label='Supplier Account']")),"Supplier Account Tab");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//select[@aria-label='Supplier Account Location Type']")).size(), "Supplier Account Location Type");

		return this;

	}

	//Verify Supplier Account Location Type
	public MemberFormPage verifySupplierAccountLocationType(){

		ArrayList<String> selectoptions=new ArrayList<String>();
		Select supplierLocationType= new  Select(getDriver().findElement(By.xpath("//select[@aria-label='Supplier Account Location Type']")));		

		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> option =supplierLocationType.getOptions();	

		for(int i=0;i<option.size();i++) {

			selectoptions.add(option.get(i).getText());

		}
		ArrayList<String> expectedOptions=new ArrayList<>(Arrays.asList("---","Bill To/Sold To","Ship To"));
		System.out.println(selectoptions);
		System.out.println(expectedOptions);
		assertTrue((selectoptions.equals(expectedOptions)), "Supplier Account Location");

		return this;
	}
	//Add Supplier Account details
	public MemberFormPage addSupplierAccount(String Memberlevel, String locationtype, String retailAccount, String portfolio, String Audit, String attachdate, String latestattachDate, String orderDate, String Description) throws InterruptedException {

		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Member Level']")), Memberlevel, "Memberlevel");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Supplier Account Location Type']")), locationtype, "location type");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Retail Account']")), retailAccount, "location type");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Load Pharmacy Portfolio']")), portfolio, "location type");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Audit']")), Audit, "location type");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_pharmacyportfolioattachdate.fieldControl-date-time-input']")),attachdate,"Attach Date");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_latestverifiedattachdate.fieldControl-date-time-input']")),latestattachDate,"Latest Attach Date");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_lastorderdate.fieldControl-date-time-input']")),orderDate,"Last order Date");
		click(getDriver().findElement((By.xpath("//li[@title='Description']"))),"Description Tab");
		type(getDriver().findElement(By.xpath("//textarea[@data-id='ix_notes.fieldControl-text-box-text']")),Description,"Description Box");


		return this;
	}

	//Navigate to Account number in the Supplier Account
	public MemberFormPage clickNewAccountNumber() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@aria-label='New Account Number. Add New Account Number']")),"New Account Numbers");
		verifIsNoTNullWithText(getDriver().findElement(By.xpath("//div[@data-id='ix_account.fieldControl-LookupResultsDropdown_ix_account_selected_tag_text']")),"Account auto populate");
		verifIsNoTNullWithTitleAttribute(getDriver().findElement(By.xpath("//select[@aria-label='Account Number Type']")),"Account Number Type");

		return this;

	}

	//Add General Account Number
	public MemberFormPage addNumberInAccountNumber() throws InterruptedException {
		Thread.sleep(3000);
		Random rand = new Random();
		int lowerbound =11111;
		int upperbound =99999;
		String number = rand.nextInt(lowerbound, upperbound)+"";
		System.out.println(number);		
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_number.fieldControl-text-box-text']")),number,"Number");
		//type(getDriver().findElement(By.xpath("//input[@data-id='ix_accountnumbername.fieldControl-text-box-text']")),name,"NAme");
		saveGeneralAccountnumber();
		Thread.sleep(2000);
		return this;
	}

	//Add General Activities
	public MemberFormPage AddGeneralActivities(String noOfFacilities, String noOfPhysicians, String noOfPatientServices) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@aria-label='# of Facilities']")),noOfFacilities,"No Of Facilities");
		type(getDriver().findElement(By.xpath("//input[@aria-label='# of Physicians']")),noOfPhysicians,"No Of Physicians");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Patients Serviced per Month']")),noOfPatientServices,"No Of Patients Services per Month");
		savePatientServices();
		Thread.sleep(2000);
		return this;
	}

	//Save General Activities in Account number
	public MemberFormPage saveGeneralAccountnumber() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@aria-label='Save (CTRL+S)']")),"Save button");

		Thread.sleep(10000);
		List<WebElement> ignore=getDriver().findElements(By.xpath("//span[contains(text(),'Ignore and save')]"));
		if(ignore.size()>0) {
			getDriver().findElement(By.xpath("//span[contains(text(),'Ignore and save')]")).click();
		}
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");

		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}

	//Ignore and Save from General Tab

	//Navigate to senior Living tab
	public MemberFormPage navigatetoSeniorLivingLTC() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//li[@title='Senior Living / LTC']")),"Navigate to Senior Living/LTC");
		Thread.sleep(2000);
		return this;
	}


	//Add Senior Living LTC details
	public MemberFormPage AddSeniorLivingData(String noOfNursingBeds, String livingBeds, String noOfPatientServices, String AssistedLivingBeds, String HospiceBeds, String LTAC, String MentalHealthBeds, String rehabilitation, String correctionalBeds, String MedicatrePartB, String censusBeds, String OtherBeds) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@aria-label='Skilled Nursing Beds']")),noOfNursingBeds,"Nursing Beds");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Independent Living Beds']")),livingBeds,"Living Beds");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Assisted Living Beds']")),AssistedLivingBeds,"Assisted Living Beds");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Hospice Beds']")),HospiceBeds,"HospiceBeds");
		type(getDriver().findElement(By.xpath("//input[@aria-label='LTAC Beds']")),LTAC,"LTAC Beds");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Mental Health Beds']")),MentalHealthBeds,"Mental Health Beds");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Rehabilitation Beds']")),rehabilitation,"Rehabilitation Beds");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Correctional Beds']")),correctionalBeds,"Correctional Beds");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Medicare Part B Beds']")),MedicatrePartB,"MedicatrePartB");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Census Beds']")),censusBeds,"Census Beds");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Other Beds']")),OtherBeds,"Other Beds");
		savePatientServices();
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//li[@aria-label='General']")),"General Tab");
		savePatientServices();
		pageRefresh();
		Thread.sleep(5000);
		return this;
	}
	//Verify the No of Bed verify
	public MemberFormPage verifyNoOfBeds(String count) {
		System.out.println(getDriver().findElement(By.xpath("//input[@aria-label='Total # Beds']")).getAttribute("value"));
		verifyExactValue(getDriver().findElement(By.xpath("//input[@aria-label='Total # Beds']")),count,"Total Beds");
		return this;

	}


	//Save Patient Services
	public MemberFormPage savePatientServices() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@aria-label='Save (CTRL+S)']")),"Save button");
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//button[@aria-label='Save (CTRL+S)']"))));
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}
	//Select Check Roll up
	public MemberFormPage selectCheckRollUp(String Check_Roll_Up) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Check Roll-Up']")), Check_Roll_Up, "Check Roll Up");
		Thread.sleep(2000);
		return this;
	}
	//Verify Time Stamp in Top Parent Membership Audit History
	public MemberFormPage verifyTimeStampInTPMembershipAuditHistory() { 
		getTextValue(getDriver().findElement(By.xpath("//*[@id='gridBodyTable']/tbody/tr[1]/td[2]/div")),"Time in Top Parent Membersihp Audit History"); 
		return this;
	}

	//select sendTo

	public MemberFormPage selectCheckSendTo(String Contact) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_checksendto.fieldControl-LookupResultsDropdown_ix_checksendto_textInputBox_with_filter_new']")),"Contact");
		Thread.sleep(6000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_checksendto.fieldControl-LookupResultsDropdown_ix_checksendto_textInputBox_with_filter_new']")))),Contact,"Contact");
		Thread.sleep(5000);
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//div[@data-id='ix_checksendto.fieldControl-LookupResultsDropdown_ix_checksendto_infoContainer']")),"Contact");
		return this;
	}

	//type the start date in the Prescription Data

	public MemberFormPage selectPrescriptionstartDate(String StartDate) throws InterruptedException {

		type(getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")),StartDate,"Rebate Start date");

		return this;
	}

	//Save rebate
	public MemberFormPage savePrescriptionData() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@aria-label='Save (CTRL+S)']")),"Save button");

		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");

		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;

	}

	//type the start date in the rebate payment

	public MemberFormPage selectRebatePaymentStartDate(String StartDate) throws InterruptedException {

		type(getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")),StartDate,"Rebate Start date");

		return this;
	}

	//Save rebate
	public MemberFormPage saveRebatePayment() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@aria-label='Save (CTRL+S)']")),"Save button");

		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");

		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;

	}

	//Add Prescription Data
	public MemberFormPage AddPrescriptionData(String Contact, String PharmacyData, String DataSource, String collectionreason, String startdate) throws InterruptedException {

		click(getDriver().findElement(By.xpath("//input[@aria-label='Data Contact, Lookup']")),"Contact");
		Thread.sleep(5000);
		type(((getDriver().findElement(By.xpath("//input[@aria-label='Data Contact, Lookup']")))),Contact,"Contact");
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//span[contains(@data-id,'ix_contactid.fieldControl-firstname')]")),"Contact");
		SelectPharmacySystem(PharmacyData);
		SelectDataSource(DataSource);
		SelectCollectionReason(collectionreason);
		selectPrescriptionstartDate(startdate);
		savePrescriptionData();
		return this;

	}

	public MemberFormPage PharmacySystem() throws InterruptedException {
		ArrayList<String> selectoptions=new ArrayList<String>();
		Select ACHStatusList= new  Select(getDriver().findElement(By.xpath("//select[@aria-label='Pharmacy System']")));		

		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> statuList =ACHStatusList.getOptions();	

		for(int i=0;i<statuList.size();i++) {

			selectoptions.add(statuList.get(i).getText());

		}
		ArrayList<String> expectedOptions=new ArrayList<>(Arrays.asList("---","Abacus","ADL","Applied Micro Systems","Ascend-HI","Best RX","Brightree","Carepoint","CECS","Compusolur","Computer Rx","Cortex","CPR+","DAA Enterprises","Data Doc","DataScan","Daytec","DHS","Digital Rx","Etreby","Exactmed Pharmacy System","Foundation System","FrameworkLTC (SoftWriters)","FSI","HAI","Hannson","HBS","HCC","Healthcare Automations","IV Solutions (HAI)","Kalos Inc. (CIPS)","KeyCentrix","Liberty Computer Service","MBI","McKesson Pharmacy Rx","Medical Manager","Micromerchant","New tech","Opus/ISM","Pac Ware","Pacific Pharmacy","PC5","PDX","Pharmascan","Pharmed","Pioneer","Pk Software","Prime Rx","Prism","Prodigy (ProScript)","Propriety / Internal","QS1","Renlar","Rescot","RS Software","RNA","Rx 30","Speedscript","Suite RX","Tech Rx","VIP","Visual Superscripts/DAA Enterprises","Vital Care","Web Md","Zadall Pharmacy System"));
		System.out.println(selectoptions);
		System.out.println(expectedOptions);
		assertTrue((selectoptions.equals(expectedOptions)), "Pharmacy Systems");

		return this;

	}

	//Verify Collection Status dropdown
	public MemberFormPage verifyCollectionStatus() throws InterruptedException {
		ArrayList<String> selectoptions=new ArrayList<String>();
		Select collectionStatus= new  Select(getDriver().findElement(By.xpath("//select[@aria-label='Collection Status']")));		

		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> statuList =collectionStatus.getOptions();	

		for(int i=0;i<statuList.size();i++) {

			selectoptions.add(statuList.get(i).getText());

		}
		ArrayList<String> expectedOptions=new ArrayList<>(Arrays.asList("---","Pending","Collecting Data","Not Collecting","Declined","On Hold"));
		System.out.println(selectoptions);
		System.out.println(expectedOptions);
		assertTrue((selectoptions.equals(expectedOptions)), "Collection Status");

		return this;

	}

	//Verify Collection Status dropdown
	public MemberFormPage verifyCollectionreason() throws InterruptedException {
		ArrayList<String> selectoptions=new ArrayList<String>();
		Select collectionReason= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_collectionreason.fieldControl-option-set-select']")));		

		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> statuList =collectionReason.getOptions();	

		for(int i=0;i<statuList.size();i++) {

			selectoptions.add(statuList.get(i).getText());

		}
		ArrayList<String> expectedOptions=new ArrayList<>(Arrays.asList("---","HIPAA privacy concern","Data needs to be set up","Report Development cost concern","Lack of technical skill","Don’t buy rebated item","Software Vendor cannot/will not create report","Member is with another GPO","Member will not submit data","Denied for RX data rebates","No longer using Pharmacy Portfolio"));
		System.out.println(selectoptions);
		System.out.println(expectedOptions);
		assertTrue((selectoptions.equals(expectedOptions)), "Collection Reason");

		return this;

	}

	//Verify Collection Status dropdown
	public MemberFormPage verifyDataSource() throws InterruptedException {
		ArrayList<String> selectoptions=new ArrayList<String>();
		Select collectionReason= new  Select(getDriver().findElement(By.xpath("//select[@aria-label='Data Source']")));		

		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> statuList =collectionReason.getOptions();	

		for(int i=0;i<statuList.size();i++) {

			selectoptions.add(statuList.get(i).getText());

		}
		ArrayList<String> expectedOptions=new ArrayList<>(Arrays.asList("---","Collecting from Pharmacy System","Collecting from Member"));
		System.out.println(selectoptions);
		System.out.println(expectedOptions);
		assertTrue((selectoptions.equals(expectedOptions)), "Data Source");

		return this;

	}



	// Select and Mark Complete	a Open Task Activity			
	public MemberFormPage deActivatePrescriptionData() throws InterruptedException   {
		List<WebElement> beforecount=getDriver().findElements(By.xpath("//i[@data-icon-name='CheckMark']"));
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']"))).click().build().perform();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Deactivate')]")),"Deactivate Prescription Data");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//button/span[contains(text(),'Deactivate')]")),"Deactivate Button");
		Thread.sleep(3000);
		List<WebElement> afterCount=getDriver().findElements(By.xpath("//i[@data-icon-name='CheckMark']"));
		assertTrue(beforecount.size()>afterCount.size(),"Prescription Data is not deactivated");
		return this;
	}

	// Select and Mark Complete	a Open Task Activity			
	public MemberFormPage deActivatePatientServices() throws InterruptedException   {
		List<WebElement> beforecount=getDriver().findElements(By.xpath("//div[contains(@class,'ms-Checkbox is-enabled RowSelectionCheckMarkSpan checkMark')]"));
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//div[contains(@class,'ms-Checkbox is-enabled RowSelectionCheckMarkSpan checkMark')]"))).click().build().perform();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("(//span[contains(text(),'Deactivate')])[1]")),"Deactivate Patient Services");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//button[@title='Deactivate']")),"Deactivate Button");
		Thread.sleep(5000);
		List<WebElement> afterCount=getDriver().findElements(By.xpath("//div[contains(@class,'ms-Checkbox is-enabled RowSelectionCheckMarkSpan checkMark')]"));
		assertTrue(beforecount.size()>afterCount.size(),"Patient Services is not deactivated");
		return this;
	}


	//Select Pharmacy System
	public MemberFormPage SelectPharmacySystem(String PharmacySystem) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Pharmacy System']")), PharmacySystem, "Pharmacy System");
		return this;
	}

	//Select Collection Reason
	public MemberFormPage SelectCollectionReason(String Collection_Reason) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Collection Reason']")), Collection_Reason, "Collection_Reason");
		return this;
	}

	//Select Data Source
	public MemberFormPage SelectDataSource(String DataSource) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Data Source']")), DataSource, "Data Source");
		return this;
	}



	public MemberFormPage ACHStatusList() throws InterruptedException {
		ArrayList<String> selectoptions=new ArrayList<String>();
		Select ACHStatusList= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_premierachstatus.fieldControl-option-set-select']")));		

		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> statuList =ACHStatusList.getOptions();	

		for(int i=0;i<statuList.size();i++) {

			selectoptions.add(statuList.get(i).getText());

		}
		assertTrue((selectoptions.contains("Accepted - not confirmed")), "Accepted - Not Confirmed option");
		assertTrue((selectoptions.contains("Accepted - confirmed")), "Accepted - Confirmed option");
		assertTrue((selectoptions.contains("Declined participation")), "Declined participation Option");
		return this;

	}

	//Verify Is Member Add Mail Sent
	public MemberFormPage verifyIsMemberAddMailSent() {
		switchToFrame(getDriver().findElement(By.xpath("//*[@id='audit_iframe']")));
		verifyExactText(getDriver().findElement(By.xpath("//*[@id='gridBodyTable']/tbody/tr[1]/td[5]/nobr")),"Is Member Add mail sent","Audit History"); 
		verifyExactText(getDriver().findElement(By.xpath("//*[@id='gridBodyTable']/tbody/tr[1]/td[7]/nobr")),"Yes","Is add mail sent"); 
		return this;
	}

	//Choose Location Type
	public MemberFormPage chooseLocationType(String locationType) throws InterruptedException   {	

		Thread.sleep(1000);

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_locationtype.fieldControl-option-set-select']")),"Location Type");
		
		List<String > mylist =new ArrayList<String>();	

		List<WebElement> options=getDriver().findElements(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div"));

		for(int i=1;i<=options.size();i++) {
			
			mylist.add(getDriver().findElement(By.xpath("(//div[contains(@id,'pa-option-set-component')]/div/div)["+i+"]")).getText());
		}
		
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+locationType+"')]")),"Location Type");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_locationtype.fieldControl-option-set-select']")),locationType,"Location Type");
		verifyExactValue(getDriver().findElement(By.xpath("//button[@data-id='ix_locationtype.fieldControl-option-set-select']")),locationType,"Location type"); 
		return new MemberFormPage();
	}


	//Choose Location Type
	public MemberFormPage chooseLocationTypewithOutVerifying(String locationType) throws InterruptedException   {	
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_locationtype.fieldControl-option-set-select']")),"Location Type");
		
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+locationType+"')]")),"Location Type");
		
		
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_locationtype.fieldControl-option-set-select']")),locationType,"Location Type");
		return new MemberFormPage();
	}

	//Location type null
	public MemberFormPage chooseLocationTypeNull() throws InterruptedException   {	
		Thread.sleep(1000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_locationtype.fieldControl-option-set-select']")),"Location Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'--Select--')]")),"Location Type");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_locationtype.fieldControl-option-set-select']")),locationType,"Location Type");
		return new MemberFormPage();
	}	

	//Change Top parent
	public MemberFormPage changeTopParentAsYes() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//h2[@title='TOP PARENT']")),"Top Parent Label");
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_istopparent.fieldControl-option-set-select']")),"Is TopParent");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'Yes')]")),"Location Type");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_istopparent.fieldControl-checkbox-select']")),"Yes","Is Top Parent"); 
		Thread.sleep(2000);
		verifyExactValue(getDriver().findElement(By.xpath("//button[@data-id='ix_istopparent.fieldControl-option-set-select']")),"Yes","Is Top Parent"); 
		return this;
	}


	//Change Top parent
	public MemberFormPage changeTopParentAsNo() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//h2[@title='TOP PARENT']")),"Top Parent Label");
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_istopparent.fieldControl-option-set-select']")),"Is TopParent");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'No')]")),"Location Type");

		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_istopparent.fieldControl-checkbox-select']")),"No","Is Top Parent"); 
		Thread.sleep(2000);
		verifyExactValue(getDriver().findElement(By.xpath("//button[@data-id='ix_istopparent.fieldControl-option-set-select']")),"No","Is Top Parent"); 
		return this;
	}

	//Verify direct parent relation
	public MemberFormPage verifyDirectParentRelation(String verifyDirectParentRelation) throws InterruptedException { 
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_parentrelationship.fieldControl-option-set-select']")),verifyDirectParentRelation,"Direct Parent Relation"); 		
		Thread.sleep(2000);
		return this;
	}

	//Select Region
	public MemberFormPage selectRegion(String Region) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//label[text()='BK Active']")),"BK Active");

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_regionnew.fieldControl-option-set-select']")),"Select Region");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+Region+"')]")),"Location Type");

		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_regionnew.fieldControl-option-set-select']")))),Region,"Region");
		Thread.sleep(2000);
		verifyExactValue(getDriver().findElement(By.xpath("//button[@data-id='ix_regionnew.fieldControl-option-set-select']")),Region,"Region"); 
		return this;
	}

	//Select Top Parent Classification
	public MemberFormPage selectTopParentClassification(String topParentClassification) throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_topparentclassification.fieldControl-option-set-select']")),"Select Top Parent Classification");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+topParentClassification+"')]")),"Location Type");
		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_topparentclassification.fieldControl-option-set-select']")))),topParentClassification,"Top Parent Classification");
		Thread.sleep(2000);
		verifyExactValue(getDriver().findElement(By.xpath("//button[@data-id='ix_topparentclassification.fieldControl-option-set-select']")),topParentClassification,"Top Parent Classification"); 
		return this;
	}

	//Select Fee share eligible as yes
		public MemberFormPage changeFeeShareEligibleToYes() throws InterruptedException {
			Thread.sleep(10000);
			click(getDriver().findElement(By.xpath("//*[@title='TOP PARENT']")),"TOP Parent");//Scroll down to make the record status field visible
			click(getDriver().findElement(By.xpath("//*[@title='CORPORATE PARENT']")),"CP");//Scroll down to make the record status field visible
			click(getDriver().findElement(By.xpath("//*[@title='FOOD SERVICE PARENT']")),"Food Service");//Scroll down to make the record status field visible
			click(getDriver().findElement(By.xpath("//*[@title='FBO']")),"FBO");//Scroll down to make the record status field visible
			/*
			 * if(getDriver().findElements(By.
			 * xpath("//*[@title='MEMBERSHIP PROVIDER CONFIGURATION']")).size()>0) {
			 * click(getDriver().findElement(By.
			 * xpath("//*[@title='MEMBERSHIP PROVIDER CONFIGURATION']"))
			 * ,"MEMBERSHIP PROVIDER CONFIGURATION");//To make fee share field visible }
			 */
			if(getDriver().findElements(By.xpath("//h2[text()='FEE SHARE']")).size()>0) {
				click(getDriver().findElement(By.xpath("//h2[text()='FEE SHARE']")),"FEE SHARE");//To make fee share field visible	
			}
			
			click(getDriver().findElement(By.xpath("//*[@aria-label='Fee Share Eligible']")),"Fee share Eligible");
			click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'Yes')]")),"Fee share Eligible");
			clickTab(1);
			Thread.sleep(2000);

			verifyExactValue(getDriver().findElement(By.xpath("//*[@aria-label='Fee Share Eligible']")),"Yes","Fee Share Eligible"); 
			return this;
		}


		//Fee share eligible start date
		public MemberFormPage selectFeeShareEligibleDate(String feeShareEligibleDate) throws InterruptedException {
			Thread.sleep(3000);
			type(((getDriver().findElement(By.xpath("//*[@data-id='ix_feeshareeligibledate.fieldControl._datecontrol-date-container']//input")))),feeShareEligibleDate,"Fee Share Eligible Start Date");
			return this;
		}


	//Select FBO Type
	public MemberFormPage selectFBOGPOType(String FBOType) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_fbotype.fieldControl-option-set-select']")),"FBO Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+FBOType+"')]")),"FBO Type");
		verifyExactValue((getDriver().findElement(By.xpath("//*[@data-id='ix_fbotype.fieldControl-option-set-select']"))),FBOType,"FBO  Type");
		//	verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_fbotype.fieldControl-option-set-select']")),FBOType,"FBO GPO Type"); 
		return this;
	}

	public MemberFormPage selectFBO(String FBO) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//input[contains(@id,'ix_fbo.fieldControl-LookupResultsDropdown_ix_fbo')]")),"FBO");
		Thread.sleep(5000);
		type(((getDriver().findElement(By.xpath("//input[contains(@id,'ix_fbo.fieldControl-LookupResultsDropdown_ix_fbo')]")))),FBO,"FBO");
		Thread.sleep(5000);
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[contains(@id,'fbo.fieldControl-ix_premierein')]")),"FBO");
		return this;
	}

	//Select FBO Effective date
		public MemberFormPage selectFBOEffectiveDate(String FBOEffectiveDate) {
			type(((getDriver().findElement(By.xpath("//input[@aria-label='Date of FBO Type Effective Date']")))),FBOEffectiveDate,"FBO Effective Date");
			return this;
		}

	//Select FBO Effective date
	public MemberFormPage selectFBORelationDate(String FBORelationDate) {
		type(((getDriver().findElement(By.xpath("//input[@aria-label='Date of FBO Relation Date']")))),FBORelationDate,"FBO Effective Date");
		return this;
	}
	//Verify Error Message

	public MemberFormPage verifyBusinessError(String errorMessage) {
		//Wave 1 2023 Fix
		verifyDisplayed(getDriver().findElement(By.xpath("//div[@data-id='errorDialogdialog']")), "Error pop up");
		verifyExactText((getDriver().findElement(By.xpath("//h2[@data-id='errorDialog_subtitle']"))),errorMessage,"Error Message");
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"OK button");
		return this;
	}
	//Verify top parent is null in header
	public MemberFormPage verifyTopParentIsEmpty() {
		verifyNullValueWithGetText(getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[4]")),"Top Parent in header"); 
		return this;
	}

	//Verify Direct parent is null in header
	public MemberFormPage verifyDirectParentIsEmpty() {
		verifyNullValueWithGetText(getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[3]")),"Direct Parent in header"); 
		return this;
	}


	//Verify Is Corporate Account is Locked	
	public MemberFormPage verifyIsCorporateAccountLocked() {
		verifyDisplayed(getDriver().findElement(By.xpath("//*[@data-id='ix_iscorporateaccount-locked-icon']")),"Is Corporate Account Lock");
		return this;
	}

	//Verify Is Food Service Parent is Locked
	public MemberFormPage verifyIsFoodServiceParentLocked() {
		verifyDisplayed(getDriver().findElement(By.xpath("//*[@data-id='ix_isfoodserviceparent-locked-icon']")),"Food Service Parent Lock");
		return this;
	}


	//Verify Is FBO is Locked	
	public MemberFormPage verifyIsFBOLocked() {
		verifyDisplayed(getDriver().findElement(By.xpath("//*[@data-id='ix_isfbo-locked-icon']")), "FBO lock");
		return this;
	}

	//Verify Is FBO is Locked	
	public MemberFormPage selectisFBO(String selectMemberOverride) {

		click(getDriver().findElement(By.xpath("//*[@aria-label='Is FBO']")),"FBO Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+selectMemberOverride+"')]")),"Member Override");

		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@aria-label='Is FBO']")),selectMemberOverride,"MemberOverride");

		return this;
	}

	//Verify Sponsor is Locked
	public MemberFormPage verifySponsorLocked() {
		verifyDisplayed(getDriver().findElement(By.xpath("//*[@data-id='ix_sponsor-locked-icon']")),"Sponsor lock");
		return this;
	}

	//Choose record status as Draft
		public MemberFormPage chooseRecordStatusDraft() throws InterruptedException {
			Thread.sleep(3000);
			if(getDriver().findElements(By.xpath("//*[@data-id='form-sectionHeader-MembershipProviderConfiguration']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@data-id='form-sectionHeader-MembershipProviderConfiguration']")),"Record Status");
			}
			chooseRecordStatusAsDraft();
			return this;	
		}
		
		public MemberFormPage chooseRecordStatusAsDraft() throws InterruptedException  {
			
			
			click(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Draft status");
			click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'Draft')]")),"Account published");
					Thread.sleep(3000);
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Draft","Record Status"); 
			Thread.sleep(3000);
			return this;	
		}

	//Choose record status as Draft
	public MemberFormPage chooseRecordStatusDraftfromTop() throws InterruptedException {
		Thread.sleep(3000);
		/*
		 * click(getDriver().findElement(By.xpath("//*[@title='TOP PARENT']"))
		 * ,"TOP Parent");//Scroll down to make the record status field visible
		 * click(getDriver().findElement(By.xpath("//*[@title='Sponsor']")),"Sponsor");/
		 * /Scroll down to make the record status field visible
		 * click(getDriver().findElement(By.xpath("//*[@title='CORPORATE PARENT']")),
		 * "CP");//Scroll down to make the record status field visible
		 * click(getDriver().findElement(By.xpath("//*[@title='FOOD SERVICE PARENT']"))
		 * ,"Food Service");//Scroll down to make the record status field visible
		 * click(getDriver().findElement(By.xpath("//*[@title='FBO']")),"FBO");//Scroll
		 * down to make the record status field visible if(getDriver().findElements(By.
		 * xpath("//*[@title='MEMBERSHIP PROVIDER CONFIGURATION']")).size()>0) {
		 * click(getDriver().findElement(By.
		 * xpath("//*[@title='MEMBERSHIP PROVIDER CONFIGURATION']"))
		 * ,"MEMBERSHIP PROVIDER CONFIGURATION");//Scroll down to make the record status
		 * field visible } click(getDriver().findElement(By.
		 * xpath("//*[@title='Membership Provider Type - Membership Provider Type']"))
		 * ," Membership Provider Type");
		 * if(getDriver().findElements(By.xpath("//*[text()='MEMBER ATTRIBUTES']")).size
		 * ()>0) {
		 * click(getDriver().findElement(By.xpath("//*[text()='MEMBER ATTRIBUTES']"))
		 * ,"MEMBER ATTRIBUTES"); } click(getDriver().findElement(By.xpath(
		 * "//*[@data-id='form-sectionHeader-MembershipProviderConfiguration']"))
		 * ,"Record Status");
		 */
		navigateToRecordStatus();
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Draft status");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'Draft')]")),"Account published");
				Thread.sleep(3000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Draft","Record Status"); 
		Thread.sleep(3000);
		return this;	
	}

	//Delete the bill to accout
	public MemberFormPage filterbyLocation(String locationtype) throws InterruptedException {

		click(getDriver().findElement(By.xpath("//div[contains(text(),'Store/Location Type')]")),"Location type header");
		click(getDriver().findElement(By.xpath("//button[@name='Filter by']")),"Filter by option");
		getDriver().switchTo().frame(getDriver().findElement(By.id("powerAppsAuthFlowFrame")));
		click(getDriver().findElement(By.xpath("//div[@aria-label='Filter by value']")),"Filter by Value button");
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+locationtype+"')]")),"location type option");
		click(getDriver().findElement(By.xpath("//button[@type='submit']")),"Submit button");
		getDriver().switchTo().defaultContent();

		return this;
	}

	public MemberFormPage ChangetheLocationtype() throws InterruptedException {
		List<WebElement> locationAccount=getDriver().findElements(By.xpath("//div[@class='ag-row-even ag-row-no-focus ag-row ag-row-level-0 ag-row-position-absolute ag-row-first']//a"));

		if(locationAccount.size()>0) {

			doubleClick(getDriver().findElement(By.xpath("//div[@class='ag-row-even ag-row-no-focus ag-row ag-row-level-0 ag-row-position-absolute ag-row-first']//a")), "Account");
		}

		return this;
	}

	//Verify Supplier Record below External ID
	public MemberFormPage verifySupplierRecord() throws InterruptedException {

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-id='ix_externaladdressid']//parent::div/following-sibling::div/div[@data-id='ix_supplierrecordid']")).size(), "Supplier Record");
		return this;
	}

	//Select sub accounts from Related
	public MemberFormPage selectSubaccount() throws InterruptedException {	
		Thread.sleep(3000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(4000);

		click(getDriver().findElement(By.xpath("//*[contains(text(),'Sub-Accounts')]")),"Sub Accounts");
		return this;
	}

	//Click Edit Column
	public MemberFormPage clickEditColumn() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@id='columnEditor-btn']")),"column");	
		return this;
	}


	//verify the Opputunity column with Addded Column

	public MemberFormPage verifySubAccountColumn() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@id='columnEditor-btn']")),"column");	
		ArrayList<String> actualColumn= new ArrayList<String>();
		Thread.sleep(3000);
		for(int i=1;i<=getDriver().findElements(By.xpath("//div[@draggable='true']/div[@role='listitem']/span")).size();i++) {
			actualColumn.add(getDriver().findElement(By.xpath("(//div[@draggable='true']/div[@role='listitem']/span)["+i+"]")).getText());

		}

		List<String> expectdoption = Arrays.asList("DEA","HIN","Name(Group)", "Class of Trade");

		if(actualColumn.contains(expectdoption))
		{		
			Thread.sleep(3000);
			setReport().log(Status.PASS, "RepresentativeType- " + "   " + actualColumn + "  " +  "-  Option is available to choose from the list" + " "+ expectdoption,	screenshotCapture());

		} 
		else {
			setReport().log(Status.FAIL, "RepresentativeType - "+   "   " + actualColumn + "  " + "- Option is not available in the list"  + " "+ expectdoption ,	screenshotCapture());
			Driver.failCount++;
		}

		return this;
	}

	//Verify Fields in subaccount view

	public MemberFormPage verifySubAccountView() throws InterruptedException {
		//Wave 2023  update
		click(getDriver().findElement(By.xpath("//*[@class='symbolFont SiteMap-symbol  ']")),"Hamburger Icon");
		Thread.sleep(2000);

		JavascriptExecutor je = (JavascriptExecutor) getDriver();

		WebElement element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		Actions builder = new Actions(getDriver());
		builder.moveToElement(element).clickAndHold().perform();

		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).perform();

		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).release().perform();

		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).clickAndHold().perform();

		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).perform();

		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).release().perform();

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@col-id='ix_parentrelationship']/following-sibling::div[@col-id='ix_directparentrelationdate']")).size(), "dirvetionparent relatio date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@col-id='ix_topparentrelationship']/following-sibling::div[@col-id='ix_issponsor']")).size(), "is sponsor");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@col-id='ix_issponsor']/following-sibling::div[@col-id='ix_sponsor']")).size(), "sponsor");

		return this;

	}
	//search in sub account
	public MemberFormPage searchinSubaccount(String subaccount) throws InterruptedException {

		typeAndEnter(getDriver().findElement(By.xpath("//input[contains(@data-id,'quickFind_text')]")),subaccount,"Find Criteria" );

		return this;
	}

	//Select member account from search results
	public  MemberFormPage selectAccountFromSearchResults() throws InterruptedException {	
		//Actions action = new Actions(getDriver());
		Thread.sleep(4000);
		if((getDriver().findElements(By.xpath("//div[@col-id='name']//a"))).size()>0) {
			click(getDriver().findElement(By.xpath("//div[@col-id='name']//a")),"Search Results");

		}else {
			click(getDriver().findElement(By.xpath("//div[@col-id='name']//button")),"Search Results");

		}
		//click(getDriver().findElement(By.xpath("//*[@data-id='cell-0-6']/a")),"Search Results");
		Thread.sleep(4000);
		return this;
	}	


	//click new account in sub account
	public MemberFormPage clickNewAccountInSubAccount() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='account|NoRelationship|SubGridAssociated|Mscrm.SubGrid.account.AddNewStandard']")),"New");
		return this;
	}

	//Verify direct parent
	public MemberFormPage verifyDirectParent(String verifyDirectParent) {
		verifyExactText((getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_selected_tag_text']"))),verifyDirectParent,"Direct Parent");
		return this;
	}

	//Verify Top Parent
	public MemberFormPage verifyTopParent(String verifyTopParent ) {
		verifyExactText((getDriver().findElement(By.xpath("//div[contains(text(),'Top Parent')]//preceding-sibling::div/a"))),verifyTopParent,"Top Parent");
		return this;
	}

	public MemberFormPage verifyDPExceptionReasonNotDisplayed() {
		List<WebElement> DPExcpetion= getDriver().findElements(By.xpath("//span[contains(text(),'DP Exception Reason: Required fields must be filled in.')]"));
		verifyElementisNotDisplayed(DPExcpetion.size(),"DP Excpetion reason");
		return this;
	}
	//Type Direct parent Reason
	public MemberFormPage typeDPReason(String DPReason) {
		click(((getDriver().findElement(By.xpath("//*[@data-id='ix_dpexceptionreason.fieldControl-text-box-text']")))),"DP Reason");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_dpexceptionreason.fieldControl-text-box-text']")))),DPReason,"DP Reason");
		return this;
	}

	//Click Tab
	public MemberFormPage clickTab(int number)  throws InterruptedException {
		for(int i=0;i<number;i++) {
			Actions a =new Actions(getDriver());
			a.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(3000);
		}
		return this;
	}

	//Type Zip code	
	public MemberFormPage typeZipCode(String zipCode) throws InterruptedException {
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		Thread.sleep(2000);
		type((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),zipCode, "Zip Code");
		return this;
	}

	//Type top parent reason
	public MemberFormPage typeTPReason(String TPReason) throws InterruptedException {
		clear(getDriver().findElement(By.xpath("//*[@data-id='ix_tpexceptionreason.fieldControl-text-box-text']")),"TP exception Reason");
		click(((getDriver().findElement(By.xpath("//*[@data-id='ix_tpexceptionreason.fieldControl-text-box-text']")))),"TP Exception Reason");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_tpexceptionreason.fieldControl-text-box-text']")))),TPReason,"TP Exception Reason");
		return this;
	}	

	//Choose member entry form
	public MemberFormPage chooseMemberEntryForm() throws InterruptedException {
		click(getDriver().findElement(By.xpath("(//*[@data-id='form-selector'])[1]")),"Form Selector");
		click(getDriver().findElement(By.xpath("//span[text()='Member Entry Form']")),"Member Entry Form");

		if(getDriver().findElements(By.xpath("//*[@data-id='cancelButton']")).size()>0) {
			click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Discard Changes");
		}
		Thread.sleep(2000);
		return new MemberFormPage();
	}


	//Choose member form
	public MemberFormPage chooseMemberForm() throws InterruptedException {
		click(getDriver().findElement(By.xpath("(//*[@data-id='form-selector'])[1]")),"Form Selector");
		click(getDriver().findElement(By.xpath("//span[text()='Member Form']")),"Member Form");

		if(getDriver().findElements(By.xpath("//*[@data-id='cancelButton']")).size()>0) {
			click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Discard Changes");	
		}

		Thread.sleep(2000);
		return new MemberFormPage();
	}



	//Change member form
	public MemberFormPage changeMemberForm() throws InterruptedException {
		click(getDriver().findElement(By.xpath("(//*[@data-id='form-selector'])[1]")),"Form Selector");
		click(getDriver().findElement(By.xpath("//*[text()='Member Form']")),"Member Form");
		Thread.sleep(2000);
		return new MemberFormPage();
	}


	//Verify default account status in header
	public MemberFormPage defaultAccountStatusHeader() {
		try {
			Thread.sleep(3000);
			verifyExactText((getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[2]"))),"Active","Account Status in Header");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	//Add primary contact
	public MemberFormPage addMemberPrimaryContact(String addMemberPrimaryContact) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")),"Primary Contact");
		type(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")),addMemberPrimaryContact,"Primary Contact");
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//span[contains(@id,'primarycontactid.fieldControl-firstname0_0_0')]")),"Primary Contact search");
		return this;
	}

	//Update primary contact
	public MemberFormPage updateMemberPrimaryContact(String updateMemberPrimaryContact) throws InterruptedException {
		clearPrimaryAccount();
		click(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")),"Primary Contact");
		type(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")),updateMemberPrimaryContact,"Primary Contact");
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//span[contains(@id,'primarycontactid.fieldControl-firstname0_0_0')]")),"Primary Contact search");
		return this;	
	}

	//Clear DP
	public MemberFormPage clearDP() throws InterruptedException {

		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_selected_tag_text']"))).perform();
		click(getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_selected_tag_delete']")),"Clear Icon"); 
		Thread.sleep(3000);
		return this;	
	}

	//Select DP
	public MemberFormPage selectdP() throws InterruptedException {

		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_selected_tag_text']"))).perform();
		click(getDriver().findElement(By.xpath("//div[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_selected_tag_text']")),"DP name"); 
		Thread.sleep(3000);
		return this;	
	}

	public MemberFormPage selectTP() throws InterruptedException {

		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//ul[@data-id='ix_topparent.fieldControl-LookupResultsDropdown_ix_topparent_SelectedRecordList']"))).perform();
		click(getDriver().findElement(By.xpath("//ul[@data-id='ix_topparent.fieldControl-LookupResultsDropdown_ix_topparent_SelectedRecordList']")),"TP name"); 
		Thread.sleep(3000);
		return this;	
	}



	//Verify Primary contact name
	public MemberFormPage verifyPrimaryContactValue(String verifyPrimaryContactValue) throws InterruptedException {
		verifyExactText((getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_selected_tag']"))), verifyPrimaryContactValue,"Primary Contact");
		return this;
	}


	//Verify Primary contact name is null
	public MemberFormPage verifyPrimaryContactNullValue() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//input[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")).size(), "Primary Contat Null");

		return this;
	}

	//Verify default membership provider
	public MemberFormPage verifyDefaultMembershipProvider() {
		click(getDriver().findElement(By.xpath("//*[@data-id='form-sectionHeader-MembershipProviderConfiguration']")),"Membership Provider");
		verifyExactTextWithTitleAttribute((getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-checkbox-container']"))),"No","Membership Provider");
		return this;
	}

	// Verify if membership provider is yes
	public MemberFormPage verifyMembershipProviderYes() {
		verifyExactTextWithTitleAttribute((getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-checkbox-container']"))),"Yes","Membership Provider");
		return this;
	}

	//Verify membership provider type options
	public MemberFormPage verifyMembershipProviderTypeOptions(String MembershipProviderType) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovidertype.fieldControl-option-set-select']")),"Membership Provider Type");
		String[] MPList =MembershipProviderType.split(",");
		int size=MPList.length;
		for (int i = 0; i < size; i++)
		{	 
			verifyPartialText((getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovidertype.fieldControl-option-set-select']"))),MPList[i],"Membership Provider Type");
		}
		return this;
	}

	//Select membership provider type
	public MemberFormPage selectMembershipProviderType(String membershipProviderType) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovidertype.fieldControl-option-set-select']")),membershipProviderType,"Membership Provider Type");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovidertype.fieldControl-option-set-select']")),membershipProviderType,"Membership Provider Type");
		return this;
	}

	//Select account in home page
	public AccountsPage selectAccountss() throws InterruptedException {	
		click(getDriver().findElement(By.xpath("//span[text()='Accounts']")),"Accounts");
		Thread.sleep(2000);
		return new AccountsPage();
	}

	//Type Membership provider name in account name field
	public MemberFormPage typeMPAccountName(String accountName) throws InterruptedException {
		Thread.sleep(3000);
		Random rand = new Random();
		int upperbound = 99999;
		int int_random = rand.nextInt(upperbound);
		//click(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-container']")),"Account name");
		click(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")),"Account name");
		type(((getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")))),accountName+int_random,"Account name");
		return this;
	}


	//To add existing FedTaxID
	public MemberFormPage typeRandomFedTaxID(int lowerbound, int upperbound) {

		Random rand = new Random();
		String fedTaxID = rand.nextInt(lowerbound, upperbound)+"";
		System.out.println(fedTaxID);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),fedTaxID,"Fed Tax ID Account Number");
		return this;

	}
	//Type Membership provider name in account name field
	public MemberFormPage typeMPAccountNameWithoutRandomName(String accountName) throws InterruptedException {
		//click(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-container']")),"Account name");
		click(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")),"Account name");
		type(((getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")))),accountName,"Account name");
		return this;
	}

	//Navigate to Account status from MP
	public MemberFormPage navigateToAccountStatusFromMPConfig() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//label[text()='FBO Type Effective Date']")),"Is Payment Entity");
		click(getDriver().findElement(By.xpath("//label[text()='Is Payment Entity']")),"Is Payment Entity");
		click(getDriver().findElement(By.xpath("//label[text()='Stock Symbol']")),"Stock Symbol'");
		click(getDriver().findElement(By.xpath("//label[text()='Region']")),"Region");
		click(getDriver().findElement(By.xpath("//label[text()='Participation Type']")),"Participation Type']");
		click(getDriver().findElement(By.xpath("//label[text()='Exclude from Roster']")),"Exclude from Roster']");
		return this;
	}

	//Change Account Status
	public MemberFormPage selectAccountStatus(String acountStatus) throws InterruptedException {
		Thread.sleep(2000);
		//Wave 2023 update
		click(getDriver().findElement(By.id("expandIcon")),"Account Status DropDown");
		Thread.sleep(2000);
		selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='header_ix_accountstatus.fieldControl-option-set-select']")))),acountStatus,"Account Status");
		verifyExactTextWithTitleAttribute((getDriver().findElement(By.xpath("//*[@data-id='header_ix_accountstatus.fieldControl-option-set-select']"))),acountStatus,"Account Status");
		return this;
	}

	//Change Account Status
	public MemberFormPage selectAccountStatusdropdown(String acountStatus) throws InterruptedException {
		Thread.sleep(2000);
		//Wave 2023 update
		selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//select[@aria-label='Account Status']")))),acountStatus,"Account Status");
		verifyExactTextWithTitleAttribute((getDriver().findElement(By.xpath("//select[@aria-label='Account Status']"))),acountStatus,"Account Status");
		return this;
	}



	//Verify no records found error message in add membership provider
	public MemberFormPage verifyNoRecordsFoundMsgInAddMP() throws InterruptedException {
		Thread.sleep(3000);
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-LookupResultsDropdown_ix_membershipprovider_Error_Text']")),"Required fields must be filled in.","Membership Provider Lookup"); 
		return this;
	}

	public MemberFormPage verifySupplierFormIsDisplayed() {
		System.out.println((getDriver().findElement(By.xpath("//*[@data-id='form-selector']")).getText()));
		verifyExactText((getDriver().findElement(By.xpath("//*[@data-id='form-selector']"))),"Supplier Form","Form");
		return this;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~Member Entry Form~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public MemberFormPage selectAccountTypeMEF(String accountType) throws InterruptedException{
		click(getDriver().findElement(By.xpath("//button[@data-id='customertypecode.fieldControl-option-set-select']")),"Account Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component-')]/div/div[contains(text(),'"+accountType+"')]")),"Account Type");

		Thread.sleep(2000);
		verifyExactValue(getDriver().findElement(By.xpath("//*[@data-id='customertypecode.fieldControl-option-set-select']")),accountType,"Account type"); 
		return this;
	}


	public MemberFormPage selectTopParentRelationMEF(String topParentRelation) throws InterruptedException {

		click(((getDriver().findElement(By.xpath("//button[@data-id='ix_topparentrelationship.fieldControl-option-set-select']")))),"Top Parent Relation");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+topParentRelation+"')]")),"topParentRelation");
		Thread.sleep(1000);
		verifyExactValue(getDriver().findElement(By.xpath("//*[@data-id='ix_topparentrelationship.fieldControl-option-set-select']")),topParentRelation,"Top Parent Relation");
		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_topparentrelationship.fieldControl-option-set-select']")))),topParentRelation,"Top Parent Relation");
		Thread.sleep(1000);

		return this;
	}

	public MemberFormPage clickMembershipSaveAndCloseMEF() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_membership|NoRelationship|Form|Mscrm.Form.ix_membership.SaveAndClose']")),"Save and Close");
		//Changes as part of Premier promise
		//	click(getDriver().findElement(By.xpath("//*[@data-id='quickCreateSaveAndCloseBtn']")),"Save and Close");
		Thread.sleep(7000);
		//	click(getDriver().findElement(By.xpath("//*[@title='GENERAL DEMOGRAPHIC']")),"GENERAL DEMOGRAPHIC");
		//Changes as part of Ticket 833703
		click(getDriver().findElement(By.xpath("//*[@title='General']")),"GENERAL");
		Thread.sleep(5000);
		chooseApplicationnavigateToAdditionalCriteria();
		return this;	
	}	

	public MemberFormPage verifyIsFBOMEF(String verifyIsFBO) throws InterruptedException { 
		click(getDriver().findElement(By.xpath("//*[@title='MISCELLANEOUS']")),"MISCELLANEOUS");
		Thread.sleep(5000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_isfbo.fieldControl-checkbox-container']")),verifyIsFBO, "Is FBO"); 
		return this;
	}

	public MemberFormPage NavigatetoMiscellaneousTab() throws InterruptedException { 
		click(getDriver().findElement(By.xpath("//*[@title='MISCELLANEOUS']")),"MISCELLANEOUS");
		Thread.sleep(5000);
		verifyDisplayed(getDriver().findElement(By.xpath("//label[contains(text(),'FBO Manual Override')]")),"Miscellaneous Tab");
		return this;
	}

	//Select record status published
	public MemberFormPage chooseRecordStatusPublishedMEF() throws InterruptedException {
		Thread.sleep(3000);
		navigateToRecordStatusMEF();

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published status");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'Published')]")),"Account published");

		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")))),"Published", "Record Status");	
		Thread.sleep(2000);
		verifyExactValue(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published","Record Status"); 

		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")))),"Published", "Record Status");	
		Thread.sleep(3000);
		//verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published","Record Status"); 
		return this;
	}
	
	
	public MemberFormPage chooseStatusPublished() throws InterruptedException {
		
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published status");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'Published')]")),"Account published");

		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")))),"Published", "Record Status");	
		Thread.sleep(2000);
		return this;
	}

	public MemberFormPage verifyIsCorporateAccountMEF(String verifyIsCorporateAccount) {
		verifyExactValue(getDriver().findElement(By.xpath("//input[@aria-label='Is Corporate Account']")),verifyIsCorporateAccount,"Is Corporate Account"); 
		return this;
	}

	//Change Is Corporate parent
		public MemberFormPage changeIsCorporateParent(String IsCorporateAccount) {
			selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//input[@aria-label='Is Corporate Account']")))),IsCorporateAccount,"IsCorporateAccount");
			verifyExactValue(getDriver().findElement(By.xpath("//*[@data-id='ix_iscorporateaccount.fieldControl-checkbox-container']")),IsCorporateAccount,"Is Corporate Account"); 
			return this;
		}



	public MemberFormPage noMatchforCorporateParent(String corporateparent) throws InterruptedException {	
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_corporateparentname.fieldControl-LookupResultsDropdown_ix_corporateparentname_textInputBox_with_filter_new']")),"Corporate Parent");
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_corporateparentname.fieldControl-LookupResultsDropdown_ix_corporateparentname_textInputBox_with_filter_new']")))),corporateparent,"Corporate Parent");
		Thread.sleep(5000);
		List <WebElement> dp=getDriver().findElements(By.xpath("//span[contains(text(),'"+corporateparent+"')]"));
		verifyElementisNotDisplayed(dp.size(), "Corporate Parent");
		List <WebElement> nodp=getDriver().findElements(By.xpath("//*[contains(text(),'No records found. Create a new record.')]"));
		getDriver().findElement(By.xpath("//input[@data-id='ix_corporateparentname.fieldControl-LookupResultsDropdown_ix_corporateparentname_textInputBox_with_filter_new']")).sendKeys(Keys.TAB);
		verifyElementisDisplayed(nodp.size(), "No Direct Parent ");



		return this;		
	}

	public MemberFormPage noMatchforSupplier(String supplier) throws InterruptedException {	

		click(getDriver().findElement(By.xpath("//input[@data-id='ix_supplierid.fieldControl-LookupResultsDropdown_ix_supplierid_textInputBox_with_filter_new']")),"Supplier");
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_supplierid.fieldControl-LookupResultsDropdown_ix_supplierid_textInputBox_with_filter_new']")))),supplier,"Supplier");
		Thread.sleep(10000);
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//span[contains(@data-id,'ix_supplierid.fieldControl-ix_businessclassification')]")).size(), "supplier");
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//span[contains(@data-id,'ix_parentaccountid.fieldControl-ix_businessclassification')]")).size(), "supplier");


		return this;		
	}

	public MemberFormPage noMatchforFSP(String FSP) throws InterruptedException {	
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_corporateparentname.fieldControl-LookupResultsDropdown_ix_corporateparentname_textInputBox_with_filter_new']")),"Corporate Parent");
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_corporateparentname.fieldControl-LookupResultsDropdown_ix_corporateparentname_textInputBox_with_filter_new']")))),FSP,"Corporate Parent");
		Thread.sleep(5000);
		List <WebElement> dp=getDriver().findElements(By.xpath("//span[contains(text(),'"+FSP+"')]"));
		verifyElementisNotDisplayed(dp.size(), "Corporate Parent");
		List <WebElement> nodp=getDriver().findElements(By.xpath("//*[contains(text(),'No records found. Create a new record.')]"));

		verifyElementisDisplayed(nodp.size(), "No Direct Parent ");
		getDriver().findElement(By.xpath("//input[@data-id='ix_corporateparentname.fieldControl-LookupResultsDropdown_ix_corporateparentname_textInputBox_with_filter_new']")).sendKeys(Keys.TAB);


		return this;		
	}


	//Clear Corporate parent and select new Corporate parent
	public MemberFormPage clearCP() throws InterruptedException {

		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='ix_corporateparentname.fieldControl-LookupResultsDropdown_ix_corporateparentname_selected_tag_text']"))).perform();
		click(getDriver().findElement(By.xpath("//*[contains(@id,'cancelButton')]")),"Clear Icon"); 
		Thread.sleep(3000);
		return this;
	}



	public MemberFormPage verifyAffiliateGroupIsNotNullMEF() {
		navigateToDoNotVerifyMEF();
		navigateToAGDateMEF();
		getTextValue(getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroup.fieldControl-LookupResultsDropdown_ix_affiliategroup_selected_tag_text']")),"Affiliate Group");
		return this;
	}

	public MemberFormPage navigatetoAdditionaCriteriaFromDonotVerify() {
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Do')]")),"Do Not Verify");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Website')]")),"Website");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Country')]")),"Country");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'External')]")),"External Address");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Supplier Record')]")),"Supplier Record");
		//Changed on Sep 15 after member form changes
		//click(getDriver().findElement(By.xpath("//*[@title='ADDITIONAL CRITERIA']")),"ADDITIONAL CRITERIA");

		//click(getDriver().findElement(By.xpath("//*[@title='Additional Criteria']")),"ADDITIONAL CRITERIA");
		return this;

	}
	public MemberFormPage chooseApplicationnavigateToAdditionalCriteria() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//label[contains(text(),'Street 1')]")),"Street 1");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Delivery Info')]")),"Delivery Info");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'City')]")),"City");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'State')]")),"State");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'ZIP')]")),"ZIP");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Main')]")),"Phone");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Fax')]")),"Fax");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Do')]")),"Do Not Verify");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Website')]")),"Website");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Country')]")),"Country");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'External')]")),"External Address");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Supplier Record')]")),"Supplier Record");
		//Changed on Sep 15 after member form changes
		//click(getDriver().findElement(By.xpath("//*[@title='ADDITIONAL CRITERIA']")),"ADDITIONAL CRITERIA");

		click(getDriver().findElement(By.xpath("//*[@title='Additional Criteria']")),"ADDITIONAL CRITERIA");
		return this;
	}
	//Choose application start date
	public MemberFormPage chooseApplicationDateMEF(String applicationDate) throws InterruptedException {

		chooseApplicationnavigateToAdditionalCriteria();
		type(((getDriver().findElement(By.xpath("//input[@aria-label='Date of Application Date']")))),applicationDate, "Application Start Date");
		return this;
	}


	//click additional criteria
	public MemberFormPage clickAdditionalCriteria() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@title='Additional Criteria']")),"ADDITIONAL CRITERIA");
		Thread.sleep(5000);
		return this;
	}

	//General Demographic
	public MemberFormPage clickgeneralDemographic() throws InterruptedException {
		//		click(getDriver().findElement(By.xpath("//*[@title='GENERAL DEMOGRAPHIC']")),"GENERAL DEMOGRAPHIC");
		//Changes as part of Ticket 833703
		click(getDriver().findElement(By.xpath("//*[@title='General']")),"General");
		Thread.sleep(5000);
		return this;	
	}

	//Click on LOB Save and close
	public MemberFormPage clickLOBSaveAndCloseMEF() throws InterruptedException {
		//Wave2 Update
		if(getDriver().findElements(By.xpath("//section[@data-id='quickCreateRoot']//button[@aria-label='Save and Close']")).size()>0){
			click(getDriver().findElement(By.xpath("//section[@data-id='quickCreateRoot']//button[@aria-label='Save and Close']")),"Save and Close");
		}else {
			click(getDriver().findElement(By.xpath("//section[@data-id='quickCreateRoot']//button[@aria-label='Save & Close']")),"Save and Close");
		}
		Thread.sleep(5000);
		Thread.sleep(5000);
		//	click(getDriver().findElement(By.xpath("//*[@title='GENERAL DEMOGRAPHIC']")),"GENERAL DEMOGRAPHIC");
		//Changes as part of Ticket 833703
		click(getDriver().findElement(By.xpath("//*[@title='General']")),"GENERAL");
		Thread.sleep(5000);
		return this;	
	}	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Verify Account Status
	public MemberFormPage verifyAccountStatus(String verifyAcountStatus) throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertTrue((getTextValue(getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[2]")),"Account Status")).equals(verifyAcountStatus));
		return this;
	}

	//Verify premier start date
	public MemberFormPage verifyPremierStartDate(String premierStartDate) throws InterruptedException {

		System.out.println(getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Date of Premier Start Date']")),"Premier Start Date"));
		try {
			Assert.assertTrue(getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Date of Premier Start Date']")),"Premier Start Date").equals(premierStartDate));
		}
		catch (AssertionError e) {
			System.out.println(e.getMessage());}
		return this;

	}

	//Verify premier start date is null
	public MemberFormPage verifyPremierStartDateIsNull() {
		verifyNullValue(getDriver().findElement(By.xpath("//input[@aria-label='Date of Premier Start Date']")),"Premier Start Date");
		return this;
	}

	//Select membership end date
	public MemberFormPage selectMembershipEndReason(String EndReason) {
		click((getDriver().findElement(By.xpath("//*[@data-id='ix_endreason.fieldControl-option-set-select']"))),"End reason");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+EndReason+"')]")),"Account published");
		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_endreason.fieldControl-option-set-select']")))),EndReason,"End Reason");
		return this;
	}

	//Verify whether membership is Null and if not Terminate it
	public MemberFormPage verifyAndEndMembership(String memberhip, String EndReason) throws InterruptedException {
		if(getDriver().findElements(By.xpath("//div[@col-id='ix_enddate' and @role='gridcell']//label")).size()>0){
			if(getDriver().findElement(By.xpath("//div[@col-id='ix_enddate' and @role='gridcell']//label")).getAttribute("aria-label").contentEquals(null)) {
				doubleClickMembership(memberhip);
				typeMembershipEndDate(TestUtils.todaysDate());
				selectMembershipEndReason(EndReason);
				clickMembershipSaveAndClose();
			}
		}
		return this;
	}

	//Type membership end date
	public MemberFormPage typeMembershipEndDate(String EndDate) {
		//click(getDriver().findElement(By.xpath("//*[@data-id='ix_applicationstartdate.fieldControl-date-time-input']")),"End Date");
		type(((getDriver().findElement(By.xpath("//input[@aria-label='Date of End Date'][contains(@id,'DateControlPrefix')]")))),EndDate,"End Date");
		return this;
	}

	public MemberFormPage verifyPremierEndDateIsNull() {
		verifyNullValue(getDriver().findElement(By.xpath("//input[@aria-label='Date of Premier End Date']")),"Premier End Date"); 
		return this;
	}

	public MemberFormPage verifyMembershipEndDateIsNull() {
		verifIsNoTNullWithAttribute("aria-label",getDriver().findElement(By.xpath("//div[@col-id='ix_enddate' and @role='gridcell']//label")),"Premier End Date"); 
		return this;
	}

	//Verify premier end date
	public MemberFormPage verifyPremierEndDate(String verifyPremierEndDate) {
		Assert.assertTrue((getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Date of Premier End Date']")),"Premier End Date")).equals(verifyPremierEndDate));
		return this;
	}

	//Verify direct parent relation  date is updated correctly
	public MemberFormPage verifyDirectParentRelationDate(String verifyDirectParentRelationDate) {
		verifyExactValue(((getDriver().findElement(By.xpath("//input[@aria-label='Date of Direct Parent Relation Date']")))),verifyDirectParentRelationDate, "Direct Parent Relation Date");
		return this;
	}

	//Verify premier start date is null
	public MemberFormPage verifyDPRelationtDateIsNotNull() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println(getDriver().findElement(By.xpath("//input[@aria-label='Date of Direct Parent Relation Date']")).getAttribute("value"));
		verifIsNoTNullValue(getDriver().findElement(By.xpath("//input[@aria-label='Date of Direct Parent Relation Date']")),"DP Relation Date");
		return this;
	}


	//Verify premier start date is null
	public MemberFormPage verifyDPRelationtDateIsNull() {
		verifyNullValue(getDriver().findElement(By.xpath("//input[@aria-label='Date of Direct Parent Relation Date']")),"DP Relation Date");
		return this;
	}
	//Verify top parent relation  date is updated correctly
	public MemberFormPage verifyTopParentRelationDate(String verifyTopParentRelationDate) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[contains(text(),'TOP PARENT')]")),"FBO");
		scrolldown();
		Thread.sleep(4000);
		List<WebElement> entitycode= getDriver().findElements(By.xpath("//div[@data-id='TPQuickViewForm']//label"));
		if(entitycode.size()>0) {
			click(getDriver().findElement(By.xpath("//div[@data-id='TPQuickViewForm']//label")),"Entity Code");
		}
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Is Top Parent')]")),"Is top Parent");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Top Parent Relation')]")),"Top Parent Relation");

		verifyExactValue(getDriver().findElement(By.xpath("//input[@aria-label='Date of Top Parent Relation Date']")),verifyTopParentRelationDate,"Top Parent Relation Date");
		return this;
	}


	//verify affiliate group
	public MemberFormPage verifyAffiliateGroup(String verifyAffiliateGroup) {
		if(getDriver().findElements(By.xpath("//*[@data-id='form-sectionHeader-SUMMARY_TAB_section_9']")).size()>0) {
			click(getDriver().findElement(By.xpath("//*[@data-id='form-sectionHeader-SUMMARY_TAB_section_9']")),"FBO");
		}
		String Text=getTextValue(getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroup.fieldControl-LookupResultsDropdown_ix_affiliategroup_selected_tag_text']")),"Affiliate Group");
		System.out.println(Text);
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Affiliate Group Effective Date')]")),"AG Effective Date");
		System.out.println(verifyAffiliateGroup);
		Assert.assertTrue((getTextValue(getDriver().findElement(By.xpath("//*[@data-id='ix_affiliategroup.fieldControl-LookupResultsDropdown_ix_affiliategroup_selected_tag_text']")),"Affiliate Group")).equals(verifyAffiliateGroup));
		return this;
	}

	//Verify AG effective date
	public MemberFormPage verifyAgEffectiveDate(String verifyAgEffectiveDate) {
		click(getDriver().findElement(By.xpath("//*[@data-id='form-sectionHeader-MembershipProviderConfiguration']")),"Record Status");
		System.out.println((getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Date of Affiliate Group Effective Date']")),"")));
		Assert.assertTrue((getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Date of Affiliate Group Effective Date']")),"Affiliate Group Effective Date")).equals(verifyAgEffectiveDate));
		return this;
	}

	public MemberFormPage verifyAgEffectiveDateAsCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String date1= dateFormat.format(date); 
		Assert.assertTrue((getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Date of Affiliate Group Effective Date']")),"Affiliate Group Effective Date")).equals(date1));
		return this;
	}

	public MemberFormPage verifyFBODateAsCurrentDate() {

		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String date1= dateFormat.format(date); 

		click(getDriver().findElement(By.xpath("//div[@data-id='FSPEntityCode.ix_premierein-FieldSectionItemContainer']/div")),"Food Service");
		click(getDriver().findElement(By.xpath("//h2[contains(text(),'FBO')]")),"FBO");

		System.out.println(getDriver().findElement(By.xpath("//input[@aria-label='Date of FBO Relation Date']")).getAttribute("value"));
		verifyExactValue(getDriver().findElement(By.xpath("//input[@aria-label='Date of FBO Relation Date']")),date1,"FBO Relation Date");
		return this;
	}

	public MemberFormPage typeTPRDCurrentDate() {

		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String date1= dateFormat.format(date);
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Top Parent Relation Date']")),date1,"Top Parent Relation Date");
		return this;
	}

	//Account name 2
	public MemberFormPage typeAccountName2(String AccountName2) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_hiscirostername.fieldControl-text-box-text']")),"Account name");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_hiscirostername.fieldControl-text-box-text']")))),AccountName2,"Account name2");
		return this;
	}

	//Verify Group field is populated
	public MemberFormPage verifyGroup(String verifyGroup) {
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_group.fieldControl-LookupResultsDropdown_ix_group_selected_tag_text']")),verifyGroup,"Group"); 
		return this;
	}

	//Verify Facility type
	public MemberFormPage verifyFacilityType(String verifyFacilityType) throws InterruptedException {
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_facilitytype.fieldControl-LookupResultsDropdown_ix_facilitytype_selected_tag_text']")),verifyFacilityType,"Facility Type"); 
		return this;
	}

	public MemberFormPage verifyPremierOwner(String verifyPremierOwner) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_premierowner.fieldControl-checkbox-container']")),verifyPremierOwner,"Premier Owner"); 
		return this;
	}

	//Verify CAMS flag
	public MemberFormPage verifyCAMSFlag(String verifyCAMSFlag) {
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		WebElement element = getDriver().findElement(By.xpath("//label[contains(text(),'Group')]"));
		je.executeScript("arguments[0].scrollIntoView(true);",element);

		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_camsflag.fieldControl-checkbox-container']")),verifyCAMSFlag,"CAMS Flag"); 
		return this;
	}


	//Verify CAMS flag
	public MemberFormPage verifyCAMSFlagNotMatch(String verifyCAMSFlag) {
		String CamsFlag=getDriver().findElement(By.xpath("//*[@data-id='ix_camsflag.fieldControl-checkbox-container']")).getText();
		Assert.assertTrue("CAMS flag value is not matched",!(CamsFlag.contentEquals(verifyCAMSFlag)));
		return this;
	}

	//Verify exclude from roaster
	public MemberFormPage verifyExcludeFromRoaster(String ExcludeFromRoaster) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_donotroster.fieldControl-checkbox-container']")),ExcludeFromRoaster,"Exclude From Roaster"); 
		return this;
	}

	//Verify BK active
	public MemberFormPage verifyBKActive(String verifyBKActive) throws InterruptedException {
		Thread.sleep(3000);
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		WebElement element = getDriver().findElement(By.xpath("//label[contains(text(),'Group')]"));
		je.executeScript("arguments[0].scrollIntoView(true);",element);

		WebElement element2 = getDriver().findElement(By.xpath("//label[contains(text(),'CAMS Flag')]"));
		je.executeScript("arguments[0].scrollIntoView(true);",element2);

		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_bkactive.fieldControl-checkbox-container']")),verifyBKActive,"BKActive"); 
		return this;
	}

	//select BK Active  as yes
	public MemberFormPage selectBKActiveYes() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Sponsor']")),"Sponsor"); //This is for scroll down the page to make the BK active field visible
		Thread.sleep(2000);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_bkactive.fieldControl-checkbox-select']")),"Yes","BK Active");
		return this; 
	}

	//select BK Active  as No
	public MemberFormPage selectBKActiveNo() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Sponsor']")),"Sponsor"); //This is for scroll down the page to make the BK active field visible
		Thread.sleep(2000);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_bkactive.fieldControl-checkbox-select']")),"No","BK Active");		
		return this; 
	}


	//select BK Active  as yes
	public MemberFormPage VerifyBKActive(String active) throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Sponsor']")),"Sponsor"); //This is for scroll down the page to make the BK active field visible
		Thread.sleep(2000);
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_bkactive.fieldControl-checkbox-select']/option[@data-selected='true']")), active, "BK Field");
		return this; 
	}


	//Verify Business Key
	public MemberFormPage verifyBusinessKeyvalue() throws InterruptedException {
		Thread.sleep(2000);

		getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Business Key']")), "BK Field");
		return this;
	}

	//input supplier record
	public MemberFormPage typeSupplierRecord(String supplierRecord) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_supplierrecordid.fieldControl-LookupResultsDropdown_ix_supplierrecordid_textInputBox_with_filter_new']")),"Supplier Record");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_supplierrecordid.fieldControl-LookupResultsDropdown_ix_supplierrecordid_textInputBox_with_filter_new']")))),supplierRecord,"Supplier Record");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'"+supplierRecord+"')]")),supplierRecord);
		return this;
	}

	//Type stock symbol
	public MemberFormPage typeStockSymbol(String stockSymbol) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_stocksymbol.fieldControl-text-box-text']")),"Stock Symbol");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_stocksymbol.fieldControl-text-box-text']")))),stockSymbol, "Stock Symbol");
		return this;
	}	


	//Select ownership
	public MemberFormPage selectOwnership(String ownership) throws InterruptedException{
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_ownership.fieldControl-option-set-select']")),ownership,"Ownership");
		Thread.sleep(5000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_ownership.fieldControl-option-set-container']")),ownership,"Ownership"); 
		return this;
	}

	//Type Exchange
	public MemberFormPage typeExchange(String exchange) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_exchange.fieldControl-text-box-text']")),"Exchange");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_exchange.fieldControl-text-box-text']")))),exchange, "Exchange");
		return this;
	}

	//Is payment entity
	public MemberFormPage verifyIsPaymentEntity(String isPaymentEntity) {
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_ispaymententity.fieldControl-checkbox-select")),isPaymentEntity,"Is Payment Entity"); 
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_ispaymententity.fieldControl-checkbox-container']")),isPaymentEntity,"Is Payment Entity"); 
		return this;
	}

	//Add Payment entity
	public MemberFormPage addPaymentEntity(String addPaymentEntity) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_paymententityid.fieldControl-LookupResultsDropdown_ix_paymententityid_textInputBox_with_filter_new']")),"Payment Entity");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_paymententityid.fieldControl-LookupResultsDropdown_ix_paymententityid_textInputBox_with_filter_new']")))),addPaymentEntity,"Payment Entity");
		try {
			Thread.sleep(5000);
			getDriver().findElement(By.xpath("//*[@data-id='ix_paymententityid.fieldControl-LookupResultsDropdown_ix_paymententityid_textInputBox_with_filter_new']")).sendKeys(Keys.TAB, Keys.TAB,Keys.TAB);
			WebElement activeElement=getDriver().switchTo().activeElement();
			activeElement.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;	
	}

	//Select corporate rebate
	public MemberFormPage selectCorporateRebate(String corporateRebate) {
		//click(getDriver().findElement(By.id("ix_corporaterebatefeedate_cl")),"Corporate Rebate");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_corporaterebate.fieldControl-option-set-select']")),corporateRebate,"Corporate Rebate");
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_corporaterebate.fieldControl-option-set-select']")),corporateRebate,"Corporate Rebate"); 
		return this;
	}

	//Select corporate Rebate Fee date
	public MemberFormPage selectCorporateRebateFeeDate(String corporateRebateFeeDate) {
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_corporaterebatefeedate.fieldControl-date-time-input']")))),corporateRebateFeeDate,"Corporate Rebate Fee Date");
		return this;
	}

	//Type street 2
	public MemberFormPage typeStreet2(String street2) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text']")),"Street2");
		type(((getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text']")))),street2, "Street2");
		return this;
	}

	//Type delivery information
	public MemberFormPage typeDeliveryInfo(String deliveryInfo) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line3.fieldControl-text-box-text']")),"Delivery Info");
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_line3.fieldControl-text-box-text']")),deliveryInfo, "Delivery Info");
		return this;
	}

	//State
	public MemberFormPage typeState(String state) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")),"State");
		type(((getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")))),state, "State");
		return this;
	}

	//Type Mail phone
	public MemberFormPage typeMainPhone(String mainPhone) {
		click(getDriver().findElement(By.xpath("//*[@data-id='telephone1.fieldControl-phone-text-input']")),"Main Phone");
		type(((getDriver().findElement(By.xpath("//*[@data-id='telephone1.fieldControl-phone-text-input']")))),mainPhone, "Main Phone");
		return this;
	}

	//type other phone
	public MemberFormPage typeOtherPhone(String otherPhone) {
		click(getDriver().findElement(By.xpath("//*[@data-id='telephone2.fieldControl-phone-text-input']")),"Other Phone");
		type(((getDriver().findElement(By.xpath("//*[@data-id='telephone2.fieldControl-phone-text-input']")))),otherPhone, "Other Phone");
		return this;
	}

	//Override name
	public MemberFormPage typeOverrideName(String overrideName) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_name.fieldControl-text-box-text']")),"OverrideName");
		type(((getDriver().findElement(By.xpath("//*[@data-id='address1_name.fieldControl-text-box-text']")))),overrideName, "OverrideName");
		return this;
	}

	//Type fax
	public MemberFormPage typeFax(String fax) {
		click(getDriver().findElement(By.xpath("//*[@data-id='fax.fieldControl-text-box-text']")),"Fax");
		type(((getDriver().findElement(By.xpath("//*[@data-id='fax.fieldControl-text-box-text']")))),fax, "Fax");
		return this;
	}

	//Type website
	public MemberFormPage typeWebsite(String website) {
		click(getDriver().findElement(By.xpath("//*[@data-id='websiteurl.fieldControl-url-text-input']")),"Website");
		type(((getDriver().findElement(By.xpath("//*[@data-id='websiteurl.fieldControl-url-text-input']")))),website, "Website");
		return this;
	}

	//Receive direct mail
	public MemberFormPage verifyReceiveDirectMail(String receiveDirectMail) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_receivedirectmail.fieldControl-checkbox-container']")),receiveDirectMail,"Receive Direct Mail"); 
		return this;
	}

	//FSRPT flag
	public MemberFormPage verifyFSRPTFlag(String FSRPTFlag) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_fsrptflag.fieldControl-checkbox-container']")),FSRPTFlag,"FSRPT Flag"); 
		return this;
	}

	//verify do not verify address
	public MemberFormPage verifyDoNotVerifyAddress(String doNotVerifyAddress) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_donotverifyaddress.fieldControl-checkbox-container']")),doNotVerifyAddress,"Do Not Verify Address"); 
		return this;
	}

	//Verify external address
	public MemberFormPage verifyExternalAddessID(String externalAddessID) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_externaladdressid.fieldControl-text-box-text']")),"External Addess ID"); 
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_externaladdressid.fieldControl-text-box-text']")),externalAddessID,"External Addess ID"); 
		return this;
	}

	//Verify istop parent
	public MemberFormPage verifyIsTopParent(String IsTopParent) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_istopparent.fieldControl-checkbox-container']")),IsTopParent,"Is Top Parent"); 
		return this;
	}

	// Verify corporate parent override
	public MemberFormPage verifyCorpParentOverride(String corpParentOverride) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_corporateparentoverride.fieldControl-checkbox-container']")),corpParentOverride,"Corporate Parent Override"); 
		return this;
	}


	//Change value of Corporate Parent Override
	public MemberFormPage changeCorpParentOverride(String corpParentOverride) {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_corporateparentoverride.fieldControl-checkbox-select']")),corpParentOverride,"Corporate Parent Override");

		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_corporateparentoverride.fieldControl-checkbox-container']")),corpParentOverride,"Corporate Parent Override"); 
		return this;
	}

	public MemberFormPage selectFBOManualOverride(String FBOManualOverride) {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_fbomanualoverride.fieldControl-checkbox-select']")), FBOManualOverride, "FBOManualOverride");

		return this;
	}

	//Verify FBO Manual Override
	public MemberFormPage VerifyFBOManualOverride(String FBOManualOverride) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_fbomanualoverride.fieldControl-checkbox-container']")),FBOManualOverride,"FBO Manual Override"); 
		return this;
	}

	//Verify FBO GPO
	public MemberFormPage VerifyFBOGPO(String FBOGPO) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_fbogpo.fieldControl-checkbox-container']")),FBOGPO,"FBO GPO"); 
		return this;
	}


	//Verify FBO GPO
	public MemberFormPage selectFBOGPO(String FBOGPO) {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='FBO GPO']")),FBOGPO,"FBOGPO");

		return this;
	}

	public MemberFormPage pageRefresh() throws InterruptedException {
		getDriver().navigate().refresh();
		Thread.sleep(8000);
		return this;
	}

	//Click on related and select account numbers
	public MemberFormPage clickAddNewAccountNumberInAccountNumbers() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumber|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_accountnumber.AddNewStandard']")),"Add");
		try
		{
			List<WebElement> confirmBtn= getDriver().findElements(By.xpath("//*[@data-id='confirmButton']"));
			if(confirmBtn.size()>0) {
				click(getDriver().findElement(By.xpath("//*[@data-id='confirmButton']")),"Save and continue");
			}

			else
			{
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		Thread.sleep(6000);
		return this;
	}

	//Select Account type as Federal Tax ID
	public MemberFormPage chooseAccountNumberTypeFedTaxID() {
		try {
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Account Number Type -drop down");
			click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'Federal Tax ID')]")),"Account Number Type");
		
			//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Federal Tax ID","Account Number Type");
			Thread.sleep(2000);
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Federal Tax ID","Account Numbers Type"); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	//To add existing FedTaxID
	public MemberFormPage typeStaticFedTaxID(String fedTaxID) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),fedTaxID,"Fed Tax ID Account Number");
		return this;
	}

	//To add existing Account Number
	public MemberFormPage typeStaticAccountNumber(String number) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),number,"Number");
		return this;
	}

	//Choose Existing Account Number -FedTax ID
	public MemberFormPage doubleClickExistingAccountNumberFedTaxID() throws InterruptedException   {
		Thread.sleep(4000);
		//Wave1 2023 Update
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 1; i <=rowList.size(); i++) {
			String title = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div)["+i+"]")).getText();
			System.out.println(title);					
			if (title.equals("Federal Tax ID")) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div)["+i+"]")), "Federal Tax ID");
				Thread.sleep(3000);
				break;				
			}
		}		

		return this;					
	}




	//Enter DEA account number
	public MemberFormPage typeAccountNumberDEA() {
		int min=1111111;
		int max=9999999;
		String chars = "abcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		char c = chars.charAt(rnd.nextInt(chars.length()));	
		int randomInt = (int)(Math.random() * (max - min + 1) + min);
		int num=0;
		String strSum=Integer.toString(randomInt);
		strSum=c+"f"+strSum;
		for (int i=2;i<strSum.length()-1;i+=2)
		{
			char ch=strSum.charAt(i);
			int n=Integer.parseInt(Character.toString(ch));
			num+=n;
		}
		int num1=0;
		for (int i1=3;i1<strSum.length()-1;i1+=2)
		{
			char ch1=strSum.charAt(i1);
			int n1=Integer.parseInt(Character.toString(ch1));
			num1+=n1;
		}
		int num3=num1*2;
		int num2=num+num3;
		String strDEAChaneck=Integer.toString(num2);
		char ch2=strDEAChaneck.charAt(1);
		String DEA="";
		for (int i2=0;i2<strSum.length()-1;i2++)
		{
			DEA+=strSum.charAt(i2);
		}
		String DEANum=DEA+ch2;
		AccNumNPI=DEANum;
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),DEANum,"DEA Account Number");

		randomString=DEANum;
		try {
			DataInputProvider.setCellData(DEANum.toString(), Driver.iTestCaseRowNum, "DEANumber",Driver.sCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	//Verify DEA account number
	public MemberFormPage verifyDEA(String DEA) throws InterruptedException {
		verifyExactValue((getDriver().findElement(By.xpath("//*[@data-id='ix_dea.fieldControl-text-box-text']"))),DEA,"DEA");
		return this;
	}

	//Verify HIN Account number
	public MemberFormPage verifyHIN(String HIN) {
		verifyExactValue((getDriver().findElement(By.xpath("//*[@data-id='ix_hin.fieldControl-text-box-text']"))),HIN,"HIN");
		return this;
	}

	//Select related and account numbers
	public MemberFormPage selectAccountNumbers() throws InterruptedException {	
		Thread.sleep(3000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("(//*[contains(text(),'Account Numbers')])[2]")),"Account Numbers");
		Thread.sleep(2000);
		return this;
	}

	//Verify account does not have one active premier membership error
	public MemberFormPage verifyAccountDoesNotHaveMPError(String errMsg) throws InterruptedException {
		Thread.sleep(3000);
		verifyPartialText(getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']")),errMsg,"Business Process Error");
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"Ok");
		Thread.sleep(2000);
		return this;
	}

	public MemberFormPage verifyTPexceptionreasonDisplayed() throws InterruptedException {

		List<WebElement> tpexception =getDriver().findElements(By.xpath("//span[@data-id='ix_tpexceptionreason-error-message']"));
		verifyElementisDisplayed(tpexception.size(), "Tp Exception reason mandatory message");
		return this;
	}

	public MemberFormPage verifyTPexceptionreasonnotDisplayed() throws InterruptedException {

		List<WebElement> tpexception =getDriver().findElements(By.xpath("//span[@data-id='ix_tpexceptionreason-error-message']"));
		verifyElementisNotDisplayed(tpexception.size(), "Tp Exception reason mandatory message");
		return this;
	}


	//Verify Error message with the parament
	public MemberFormPage verifyError(String errMsg) throws InterruptedException {
		Thread.sleep(3000);
		verifyPartialText(getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']")),errMsg,"Business Process Error");
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"Ok");
		Thread.sleep(2000);
		return this;
	}

	//Verify Error message is not displauyed
	public MemberFormPage verifyErrorisNotDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> error=getDriver().findElements(By.xpath("//*[@data-id='errorDialog_subtitle']"));
		verifyElementisNotDisplayed(error.size(), "Error message");
		Thread.sleep(2000);
		return this;
	}

	public MemberFormPage verifyUnsavedMEssage() {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//h1[@aria-label='Unsaved changes']")).size(), "UnsavedMessage");
		return this;

	}

	//Verify account does not have one active premier membership error
	public MemberFormPage verifyDPWithoutEntityMessage(String errMsg) throws InterruptedException {
		Thread.sleep(3000);
		verifyPartialText(getDriver().findElement(By.xpath("//h2[@data-id='errorDialog_subtitle']")),errMsg,"Business Process Error Pop-Up");
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"OK Button");
		Thread.sleep(2000);
		return this;
	}

	//Verify DP Change Sub Account confirmation
	public MemberFormPage verifyDPChangeConfirmationForSubaccounts(String errMsg) throws InterruptedException {
		Thread.sleep(3000);
		verifyPartialText(getDriver().findElement(By.xpath("//*[@data-id='dialogMessageText']")),errMsg,"DP Change Confirmation Pop-Up");
		click(getDriver().findElement(By.xpath("//span[text()='Continue']")),"Continue Button");
		Thread.sleep(2000);
		return this;
	}


	public MemberFormPage verifyDPChangeConfirmation(String errMsg) throws InterruptedException {
		Thread.sleep(3000);
		verifyPartialText(getDriver().findElement(By.xpath("//*[@data-id='dialogMessageText']")),errMsg,"DP Change Confirmation Pop-Up");
		click(getDriver().findElement(By.xpath("//button[@aria-label='Continue']")),"Continue Button");
		Thread.sleep(2000);
		return this;
	}

	public MemberFormPage verifyDPChangeConfirmationNotDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> continuebutton=getDriver().findElements(By.xpath("//button[@aria-label='Continue']"));
		verifyElementisNotDisplayed(continuebutton.size(), "DP Confirmation message is displayed");
		Thread.sleep(2000);
		return this;
	}


	public AccountsPage selectAccountsTab() throws InterruptedException {	
		click(getDriver().findElement(By.xpath("//span[text()='Accounts']")),"Accounts");
		Thread.sleep(2000);
		return new AccountsPage();
	}


	//Verify Can not have duplicate membership provider error message
	public MemberFormPage verifyAccountCanNotCreateDuplicatePMError(String errMsg) throws InterruptedException {
		Thread.sleep(3000);
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']")),errMsg,"Business Process Error");
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"Ok");
		Thread.sleep(2000);
		return this;
	}

	//Verify error message while changing BK active //
	public MemberFormPage verifyErrorMsgBKField(String ErrMsg) throws InterruptedException {
		Thread.sleep(3000);
		verifyPartialText(getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']")),ErrMsg,"Error Message"); 
		return this;
	}

	//Click ok on error message //Error window properties are same for all kind of errors. 
	public MemberFormPage clickOkInErrorMsgBKField() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"Ok");
		Thread.sleep(3000);
		return this;
	}

	//Click on Discard changes
	public MemberFormPage clickOnDiscardChanges() throws InterruptedException {
		List<WebElement> cancelbutton=getDriver().findElements(By.xpath("//*[@data-id='cancelButton']"));
		if(cancelbutton.size()>0) {
			click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Discard Changes");
		}
		Thread.sleep(3000);
		return this;
	}

	//Deactivate all attributes
	public MemberFormPage deactivateAllAttributes() throws InterruptedException {

		//Wave2023 Update
		//Locator Change Select All button removed- Adding list to deactivate line items one by one
		//				WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		//				List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='ix_emsattributetype']//a"));
		//				System.out.println("# of Rows Including Header:"+ rowList.size());
		//				for (int i = 1; i <=rowList.size(); i++) {
		//					click(getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']")), "Check Mark");
		//					Thread.sleep(5000);
		//					click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[2]")), "Deactivate Button"); 
		//					click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[3]")), "Deactivate");
		//					Thread.sleep(6000);
		//				}		
		// Wave2023 Update revert ( Missing Select All Radio button reappeared without fix in CRM Cloud. Enabling back the below code and commenting above
		//09/01/2023 -Actions implemented & explicit wait
		Thread.sleep(10000);
		//		Actions action = new Actions(getDriver());		
		//		action.moveToElement(getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']"))).click().build().perform();
		WebElement element = getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']"));
		element.click();
		/*
		 * JavascriptExecutor executor = (JavascriptExecutor) driver;
		 * executor.executeScript("arguments[0].click();", element);
		 *///click(getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']")),"Check Mark");
		click(getDriver().findElement(By.xpath("//button[@aria-label='Deactivate']//span[contains(text(),'Deactivate')]")),"Deactivate button");
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Okay Id");
		return this;
	}

	//Deactivate All Memberships - 09/05/2023 -New method for Memberships to overcome duplicate CheckMark Locators in chromedriver
	public MemberFormPage deactivateAllMemberships() throws InterruptedException {			
		// DOM was showing duplicate select all elements when its not available in UI- to avoid inconsistency implementing preceding sibling approach - 09/04/2023
		click(getDriver().findElement(By.xpath("//div[@col-id='ix_membershiptype']/preceding-sibling::div//i")),"Select All Check Mark");
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_membership|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_membership.Deactivate']")),"Deactivate button");
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Confirm Deactivate button");
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ix_membership|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_membership.AddNewStandard']")));
		//Thread.sleep(5000)	;		
		return this;
	}

	//Save And Close
	public MemberFormPage clickSystemTab() throws InterruptedException {
		if(getDriver().findElements(By.xpath("//*[contains(text(),'SYSTEM')]")).size()>0) {
			click(getDriver().findElement(By.xpath("//*[contains(text(),'SYSTEM')]")),"Click System Tab");
		}else {

			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");

			click(getDriver().findElement(By.xpath("//*[contains(text(),'SYSTEM')]")),"Click System Tab");	
		}
		return this;

	}


	//input[@aria-label='Congressional District']

	public MemberFormPage pageRefreshWithGeneral() throws InterruptedException {
		getDriver().navigate().refresh();

		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='GENERAL']")));

		return this;
	}

	//Verify Premier start date is Locked	
	public MemberFormPage verifyPremierStartDateIsLocked() {
		//Wave 2023
		//verifyDisabledFields(getDriver().findElement(By.xpath("//input[@aria-label='Date of Premier Start Date']")),"Premier Start Date Lock");
		Assert.assertTrue(getDriver().findElements(By.xpath("//input[@aria-label='Date of Premier Start Date' and @readonly]")).size()>0);
		return this;
	}

	//Verify Record Change Status
	public MemberFormPage verifyRecordChangeStatus(String verifyRecordChangeStatus) throws InterruptedException {
		Thread.sleep(10000);
		verifyExactTextWithTitleAttribute((getDriver().findElement(By.xpath("//*[@data-id='ix_recordchangestatus.fieldControl-option-set-container']"))),verifyRecordChangeStatus,"Record Change Status");
		return this;
	}


	//Enter account name
	public MemberFormPage verifyAccountNameIsEnabled() throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")),"Account name");
		Thread.sleep(2000);
		return this;
	}

	//Account name 2
	public MemberFormPage verifyAccountName2IsEnabled() {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='ix_hiscirostername.fieldControl-text-box-text']")),"Account name");
		return this;
	}

	//Select Class of trade
	public MemberFormPage verifyClassOfTradeIsEnabled() throws InterruptedException, AWTException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='ix_classoftradedetail.fieldControl-LookupResultsDropdown_ix_classoftradedetail_InputSearch']")),"Class of Trade");
		return this;
	}

	//Type street1
	public MemberFormPage verifyStreet1IsEnabled( ) {
		verifyIsEnabled(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		return this;
	}

	//Type street 2
	public MemberFormPage verifyStreet2IsEnabled( ) {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text']")),"Street2");
		return this;
	}

	//Type delivery information
	public MemberFormPage verifyDeliveryInfoIsEnabled( ) {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='address1_line3.fieldControl-text-box-text']")),"Delivery Info");
		return this;
	}

	//Type City
	public MemberFormPage verifyCityIsEnabled( ) {
		verifyIsEnabled((getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']"))),"City");
		return this;
	}

	//State
	public MemberFormPage verifyStateIsEnabled( ) {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")),"State");
		return this;
	}

	//Type County
	public MemberFormPage verifyCountyIsEnabled( ) {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']")),"County");
		return this;
	}

	//Type country
	public MemberFormPage verifyCountryIsEnabled( ) {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");
		return this;
	}

	//Type Zip code	
	public MemberFormPage verifyZipIsEnabled( ) {
		verifyIsEnabled((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		return this;
	}

	//Type Mail phone
	public MemberFormPage verifyMainPhoneIsEnabled( ) {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='telephone1.fieldControl-phone-text-input']")),"Main Phone");
		return this;
	}







	public MemberFormPage typeAccountNameEdit(String AccountName) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='OverflowButton']")),"Account name");
		type(((getDriver().findElement(By.xpath("//*[@data-id='OverflowButton']")))),AccountName,"Account name");
		return this;
	}



	public MemberFormPage clickSaveInAccountNumbers() throws InterruptedException {
		switchToDefaultContent();
		click(getDriver().findElement(By.id("savefooter_statuscontrol")),"Save");
		Thread.sleep(5000);	
		return this;
	}

	public MemberFormPage clickSaveAndClose() throws InterruptedException {
		click(getDriver().findElement(By.id("savefooter_statuscontrol")),"Save and Close");
		Thread.sleep(5000);
		return this;
	}

	public MemberFormPage clickSave_Close() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Save & Close')]")),"Save and Close");
		Thread.sleep(5000);
		return this;
	}


	public MemberFormPage clickSaveAndCloseSupplierAccountNumber() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[text()='Save & Close']")),"Save and Close");
		Thread.sleep(3000);
		try {
			List<WebElement> ignoreMessage=getDriver().findElements(By.xpath("//*[text()='Ignore and save']"));
			if(ignoreMessage.size()>0)
				click(getDriver().findElement(By.xpath("//*[text()='Ignore and save']")),"Ignore and Save");	
		} catch (Exception e) {
			e.getMessage();
		}
		Thread.sleep(5000);
		return this;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public MemberFormPage changeRecordStatus(String ChangeRecordStatus){
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Record Status')]")),"Change Record Status");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Account Type')]")),"Account type");
		click(getDriver().findElement(By.id("ix_recordchangestatus")),"Change Record Status");
		selectDropDownUsingVisibleText(((getDriver().findElement(By.id("ix_recordchangestatus_d")))),ChangeRecordStatus,"Change Record Status");
		verifyExactText(getDriver().findElement(By.id("ix_recordchangestatus")),ChangeRecordStatus,"Change Record Status"); 
		return this;
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	public MemberFormPage changeRecordStatusoption(String ChangeRecordStatus){
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Record Status')]")),"Change Record Status");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Account Type')]")),"Account type");
		click(getDriver().findElement(By.xpath("//select[@data-id='ix_recordchangestatus.fieldControl-option-set-select']")),"Change Record Status");
		selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//select[@data-id='ix_recordchangestatus.fieldControl-option-set-select']")))),ChangeRecordStatus,"Change Record Status");
		return this;
	}


	public MemberFormPage validateXMLFieldValues() {
		try{
			String filePath = "C:\\Users\\blogg\\Desktop\\input.xml";
			File file = new File(filePath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println(doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName("account");
			int tLength = nodeList.getLength();


			for(int i=0; i<tLength; i++){
				Node node = nodeList.item(i);

				if(node.getNodeType()==Node.ELEMENT_NODE){
					Element element = (Element)node;
					System.out.println("Account No: "+element.getAttribute("acn"));
					System.out.println("First Name: "+element.getElementsByTagName("firstname").item(0).getTextContent());
					System.out.println("Last Name: "+element.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Balance: "+element.getElementsByTagName("balance").item(0).getTextContent());
				}

			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public MemberFormPage chooseRoasterType() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.id("ix_donotroster_d")),"Roster Type");
		return this;
	}


	public MemberFormPage verifyMainAccountEntityCode(String verifyEntityCode) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		js.executeScript("return document.getElementById('Entity Code_label').innerHTML").toString();
		Assert.assertFalse(getTextValue(getDriver().findElement(By.id("Entity Code_label")),"Entity Code").equals(verifyEntityCode));
		return this;
	}

	public MemberFormPage doubleClickOnTopParentInMembershipWithFrame0() throws InterruptedException {	
		Thread.sleep(3000);
		switchToFrame(getDriver().findElement(By.id("contentIFrame0")));
		Thread.sleep(3000);
		switchToFrame(getDriver().findElement(By.id("area_ix_account_ix_membership_AccountNameFrame")));
		Thread.sleep(3000);
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr[5]/td[2]/nobr"))).
		doubleClick().
		build().perform();
		Thread.sleep(3000);
		return this;
	}

	public MemberFormPage doubleClickOnTopParentInMembershipLocationType() throws InterruptedException {	
		Thread.sleep(3000);
		switchToFrame(getDriver().findElement(By.id("contentIFrame1")));
		switchToFrame(getDriver().findElement(By.id("area_ix_account_ix_membership_AccountNameFrame")));
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr/td[3]/nobr"))).
		doubleClick().
		build().perform();
		Thread.sleep(3000);
		return this;
	}

	public MemberFormPage verifytopParentMembershipEntity(String TopParent) {
		switchToFrame(getDriver().findElement(By.id("contentIFrame1")));
		verifyExactText(getDriver().findElement(By.id("FormTitle")),TopParent,"Top Parent");
		return this;
	}

	public MemberFormPage verifyDirectParentRelation() { 
		getTextValue(getDriver().findElement(By.id("ix_parentrelationship")),"Direct Parent Relation"); 
		return this;
	}

	public MemberFormPage changeRequireManualAG() {
		click(getDriver().findElement(By.id("ix_requiremanualagassignment")),"Require Manual AG"); 
		return this;
	}

	public MemberFormPage selectAffiliateGroupEffectiveDate(String AffiliateGroupEffectiveDate) {
		click(getDriver().findElement(By.id("ix_affiliategroupeffectivedate")),"Affiliate Group Effective Date");
		type(getDriver().findElement(By.id("ix_affiliategroupeffectivedate_iDateInput")),AffiliateGroupEffectiveDate,"Affiliate Group Effective Date");
		return this;
	}

	public MemberFormPage selectAffiliateGroup(String AffiliateGroup) throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.id("ix_affiliategroup_ledit")),"Affiliate Group");
		typeAndChoose(getDriver().findElement(By.id("ix_affiliategroup_ledit")),AffiliateGroup,"Affiliate Group");
		return this;
	}


	//Read only Account Name in  Account number Entity
	public MemberFormPage verifyAccountNameInAccountNumbersIsNotEditable(String errorMessage1) {
		verifyExactTextWithTextContentAttribute((getDriver().findElement(By.xpath("//div[@data-id='ix_account.fieldControl-Lookup_ix_account']/div/div/div[1]"))),errorMessage1,"Account Name in Account Numbers Entity");
		return this;
	}

	//Account Representative Associated View
	public MemberFormPage verifyAccountRepAssocView() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[4]")),"Select View");
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Account Representative Associated View')]")),"Active Line of Business");
		verifyIsEnabled(getDriver().findElement(By.xpath("//span[text()='Account Representative Associated View']")), "Account Representative Associated View");
		Thread.sleep(2000);
		return this;
	}

	//Verify DEA is null in member form
	public MemberFormPage verifyDEAIsNull() {
		verifyNullValue(getDriver().findElement(By.xpath("//*[@data-id='ix_dea.fieldControl-text-box-text']")),"DEA"); 
		return this;
	}

	//Click on deactivate in Account Number Entity
	public MemberFormPage clickDeactivateInAccountNumbers() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_accountnumber|NoRelationship|Form|Mscrm.Form.ix_accountnumber.Deactivate']")),"Deactivate");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Confirm Deactivate");
		Thread.sleep(6000);
		return this;	
	}

	public MemberFormPage doubleClickSupplierAccountNumbers() throws InterruptedException {
		//Actions a = new Actions(getDriver());
		doubleClick(getDriver().findElement(By.xpath("(//*[@data-icon-name='CheckMark'])[2]")),"Supplier Account Number");
		Thread.sleep(3000);

		return this;
	}


	public MemberFormPage typeDetachDate() throws InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String detachDate= dateFormat.format(date);
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='tablist-tabsupplier']")),"Supplier Account Tab");
		doubleClick(getDriver().findElement(By.xpath("//*[@data-id='ix_pharmacyportfoliodetachdate.fieldControl-date-time-input']")),"Detach Date");
		Thread.sleep(3000);
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_pharmacyportfoliodetachdate.fieldControl-date-time-input']")),detachDate,"Detach Date");
		Thread.sleep(3000);
		return this;
	}

	public MemberFormPage clickSupplierAccountTabOnSupplierAccountNumber() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='tablist-tabsupplier']")),"Supplier Account Tab");
		Thread.sleep(2000);
		return this;
	}

	//Check Load Pharmacy Portfolio value as Yes
	public MemberFormPage verifyLoadPharmacyPortfolioYesOnSupplierAccount() throws InterruptedException {
		Thread.sleep(3000);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//option[contains(@id,'ix_loadpharmacyportfolio.fieldControl-checkbox-select')and contains(text(),'Yes')][@data-selected='true']")).size(),"Load Phmarmacy Portfolio -Yes");
		click(getDriver().findElement(By.xpath("//option[contains(@id,'ix_loadpharmacyportfolio.fieldControl-checkbox-select')and contains(text(),'Yes')][@data-selected='true']")),"Load Phmarmacy Portfolio -Yes");
		Thread.sleep(2000);
		return this;
	}

	//Check Audit as No
	public MemberFormPage verifyAuditNoOnSupplierAccount() throws InterruptedException {
		Thread.sleep(3000);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//option[contains(@id,'ix_audit.fieldControl-checkbox-select')and contains(text(),'No')][@data-selected='true']")).size(),"Audit-No");
		click(getDriver().findElement(By.xpath("//option[contains(@id,'ix_audit.fieldControl-checkbox-select')and contains(text(),'No')][@data-selected='true']")),"Audit-No");
		Thread.sleep(2000);
		return this;
	}
	//Check Audit as Yes
	public MemberFormPage verifyAuditYesOnSupplierAccount() throws InterruptedException {
		Thread.sleep(3000);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//option[contains(@id,'ix_audit.fieldControl-checkbox-select')and contains(text(),'Yes')][@data-selected='true']")).size(),"Audit-Yes");
		click(getDriver().findElement(By.xpath("//option[contains(@id,'ix_audit.fieldControl-checkbox-select')and contains(text(),'Yes')][@data-selected='true']")),"Audit-Yes");
		Thread.sleep(2000);
		return this;
	}

	public MemberFormPage verifyDetachDateInSupplierAccountNumbersView() throws InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String detachDate= dateFormat.format(date);
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@class='symbolFont SiteMap-symbol  ']")),"Hamburger Icon");
		Thread.sleep(4000);
		verifyExactAttribute(getDriver().findElement(By.xpath("//label[@aria-label='"+detachDate+"']")),"aria-label",detachDate," Detach Date");
		return this;
	}



	public MemberFormPage navigateToSupplierAccountNumbers() throws InterruptedException {

		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//*[@data-icon-name='CheckMark']"))).doubleClick().build().perform();
		Thread.sleep(3000);

		return this;
	}

	public MemberFormPage navigateToAccountNumbers() throws InterruptedException {

		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//span[contains(@class,'RowSelectionCheckMarkSpan')]//i[@data-icon-name='CheckMark']"))).click().build().perform();
		a.moveToElement(getDriver().findElement(By.xpath("//span[contains(@class,'RowSelectionCheckMarkSpan')]//i[@data-icon-name='CheckMark']"))).doubleClick().build().perform();
		Thread.sleep(3000);

		return this;
	}

	public MemberFormPage deActivateSupplierAccount() throws InterruptedException 
	{
		click(getDriver().findElement(By.xpath("(//button[@aria-label='Deactivate'])")),"Deactivate button");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//div[contains(@data-id,'dialogFooter')]//button[@aria-label='Deactivate']")),"Deactivate button");
		Thread.sleep(4000);
		return this;
	}
	//Verify HIN in  Account number Entity
	public MemberFormPage verifyHINInAccountNumbers(String HIN) {
		verifyExactValue((getDriver().findElement(By.xpath("//input[@data-id='ix_number.fieldControl-text-box-text']"))),HIN,"HIN");
		return this;
	}
	//+ New Account Representative
	public MemberFormPage verifyAddNewAccountRepresentativeIsNotPresent() throws InterruptedException {
		Thread.sleep(3000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Account Representatives')]")),"Account Representatives");
		List<WebElement> accRep= getDriver().findElements(By.xpath("//span[contains(text(),'New Account Representative')]"));
		verifyElementisNotDisplayed(accRep.size()," '+ New Account Representative  ' Button ");
		return this;
	}

	//Choose Existing Account Number -DUNS
	public MemberFormPage doubleClickExistingAccountNumberDUNS() throws InterruptedException   {
		//Wave1 2023 Update
		Thread.sleep(4000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 1; i <=rowList.size(); i++) {
			String title = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div)["+i+"]")).getText();
			System.out.println(title);					
			if (title.equals("DUNS")) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div)["+i+"]")), "DUNS");
				Thread.sleep(3000);
				break;								
			}
			else if (title.equals("---"))
			{

			}

		}		

		return this;					
	}

	//Choose Existing Account Number -DEA
	public MemberFormPage doubleClickExistingAccountNumberHIN() throws InterruptedException   {
		//Wave1 2023 Update
		Thread.sleep(4000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 1; i <=rowList.size(); i++) {
			String title = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div)["+i+"]")).getText();
			System.out.println(title);					
			if (title.equals("HIN")) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div)["+i+"]")), "HIN");
				Thread.sleep(3000);
				break;								
			}
			else if (title.equals("---"))
			{

			}

		}		

		return this;					
	}

	//Choose Existing Account Number -Remitra
	public MemberFormPage doubleClickExistingAccountNumberRemitra() throws InterruptedException   {
		//Wave1 2023 Update
		Thread.sleep(4000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 1; i <=rowList.size(); i++) {
			String title = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div)["+i+"]")).getText();
			System.out.println(title);					
			if (title.equals("Remitra")) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div)["+i+"]")), "Remitra");
				Thread.sleep(3000);
				break;								
			}
			else if (title.equals("---"))
			{

			}

		}		

		return this;					
	}

	//Choose Existing Account Number -GLN
	public MemberFormPage doubleClickExistingAccountNumberGLN() throws InterruptedException   {
		//Wave1 2023 Update
		Thread.sleep(4000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 1; i <=rowList.size(); i++) {
			String title = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div)["+i+"]")).getText();
			System.out.println(title);					
			if (title.equals("GLN")) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div)["+i+"]")), "GLN");
				Thread.sleep(3000);
				break;								
			}
			else if (title.equals("---"))
			{

			}

		}		

		return this;					
	}



	public MemberFormPage clickSaveInAccountNumbersEntity() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_accountnumber|NoRelationship|Form|Mscrm.Form.ix_accountnumber.Save']")),"Save");
		Thread.sleep(10000);
		Thread.sleep(5000);
		return this;
	}

	public MemberFormPage verifyAccountName() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_accountnumber|NoRelationship|Form|Mscrm.Form.ix_accountnumber.Save']")),"Save");
		Thread.sleep(10000);
		Thread.sleep(5000);
		return this;
	}


	//Click New Contact
	public MemberFormPage clickNewContact() throws InterruptedException   {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//button[@data-id='contact|NoRelationship|SubGridAssociated|Mscrm.SubGrid.contact.AddNewStandard']")),"New Contact");
		Thread.sleep(3000);
		return this;
	}

	//DeActivate Contact
	public MemberFormPage deactivateContact(String contactFirstName) throws InterruptedException {
		Thread.sleep(2000);
		//		type(getDriver().findElement(By.xpath("//input[contains(@data-id,'quickFind_text')]")),contactFirstName,"Contact Name");
		//		click(getDriver().findElement(By.xpath("//span[contains(@id,'quickFind_button_icon')]")),"Search Icon");
		//commenting above two rows and if statement added to change view  utilized until Bug 691304 for contact associated view  is fixed
		if((getDriver().findElements(By.xpath("//*[@aria-label='Contact Associated View']")).size()>0)) {
			System.out.println("Dropdown is available");
			click(getDriver().findElement(By.xpath("//*[@aria-label='Contact Associated View']")),"Contact Associated View");
			Thread.sleep(3000);
			click(getDriver().findElement(By.xpath("//*[contains(text(),'My Contacts')]")),"My Contacts View");}
		else {
			System.out.println("View is NOT available");
			throw new java.lang.Error("Menu Could Not be Clicked");
		}
		Thread.sleep(2000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='firstname']//label"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 2; i <=rowList.size(); i++) {
			String title = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='firstname']//label)["+i+"]")).getText();
			System.out.println(title);					
			if (title.equals(contactFirstName)) {
				Thread.sleep(5000);
				click(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='firstname']//label)["+i+"]")), "Contact First Name");
				Thread.sleep(3000);
				click(getDriver().findElement(By.xpath("//span[text()='Deactivate']")),"Deactivate");
				Thread.sleep(2000);
				click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")), "Confirm");
				Thread.sleep(4000);
				break;				
			}
		}
		return this;
	}
	//Create Contact : Quick Create
	public MemberFormPage addNewContactToMember(String contactFirstName,String contactLastName, String email2, String mainPhone) throws InterruptedException {
		type(((getDriver().findElement(By.xpath("//label[text()='First Name']/following::input[@aria-label='First Name']")))),contactFirstName,"Contact First Name");
		type(((getDriver().findElement(By.xpath("//input[@data-id='lastname.fieldControl-text-box-text']")))),contactLastName,"Contact Last Name");
		type(((getDriver().findElement(By.xpath("//input[@data-id='emailaddress1.fieldControl-mail-text-input']")))),email2,"Email");
		type(((getDriver().findElement(By.xpath("//input[@data-id='mobilephone.fieldControl-phone-text-input']")))),mainPhone,"Mobile Phone");
		type(((getDriver().findElement(By.xpath("//input[@data-id='telephone1.fieldControl-phone-text-input']")))),mainPhone,"Business Phone");
		//Changed on Sep 15 after member form changes implemented

		//click(getDriver().findElement(By.xpath("//button[text()='Save and Close']")),"Save and Close");
		click(getDriver().findElement(By.xpath("//*[text()='Save and Close']")),"Save and Close");

		try {
			List<WebElement> ignoreMessage=getDriver().findElements(By.xpath("//*[text()='Ignore and save']"));
			if(ignoreMessage.size()>0)
				click(getDriver().findElement(By.xpath("//*[text()='Ignore and save']")),"Ignore and Save");	
		} catch (Exception e) {
			e.getMessage();
		}
		Thread.sleep(11000);
		return this;
	}


	//click Additional Information
	public MemberFormPage clickAdditionalInfromation() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//li[@aria-label='Additional Information']")),"Additional information");
		return this;
	}

	//Double click the existing contact
	public MemberFormPage doubleclickexistingConact() throws InterruptedException {

		Actions actions=new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//input[@aria-label='Select or deselect the row']"))).doubleClick().build().perform();
		return this;
	}

	//Verify phone number
	public MemberFormPage verifyPhonenumber(String number) throws InterruptedException {
		assertTrue( getDriver().findElement(By.xpath("//input[@data-id='mobilephone.fieldControl-phone-text-input']")).getAttribute("title").contains(number));
		assertTrue( getDriver().findElement(By.xpath("//input[@data-id='telephone1.fieldControl-phone-text-input']")).getAttribute("title").contains(number));
		return this;
	}

	//input[@data-id="mobilephone.fieldControl-phone-text-input"]

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~COMPETITORS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Click Related and Competitors
	public MemberFormPage clickCompetitors() throws InterruptedException   {

		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Competitors')]")),"Competitors");
		Thread.sleep(2000);
		return this;
	}


	//Verify Competitor Name Associated  Associated View
	public MemberFormPage verifyCompetitorNameAssocView() throws InterruptedException   {
		verifyIsEnabled(getDriver().findElement(By.xpath("//span[text()='Competitor Name Associated View']")), "Competitor Name Associated View");
		Thread.sleep(2000);
		return this;
	}

	//Add New Competitor
	public MemberFormPage verifyNewCompetitorIsNotPresent() {
		List<WebElement> comp= getDriver().findElements(By.xpath("//span[contains(text(),'New Competitor')]"));
		verifyElementisNotDisplayed(comp.size()," ' + New Competitor  ' Button ");
		return this;
	}




	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~END  Competitors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Click Membership on existing Member Form Page
	public MemberFormPage clickExistingMembershipOnMemberForm() throws InterruptedException   {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Premier']/following-sibling::span")),"Existing Membership");
		Thread.sleep(3000);
		return this;
	}		
	//Click Go Back across all member form pages
	public MemberFormPage clickGoBackOnMemberForm() throws InterruptedException {
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[@title='Go back']")), "Go Back");
		Thread.sleep(5000);
		return this;
	}


	//Choose Existing Contact from Contact Associated View

	public ContactsPage doubleClickExistingContact(String contactFirstName) throws InterruptedException   {
		Thread.sleep(3000);
		//		type(getDriver().findElement(By.xpath("//input[contains(@data-id,'quickFind_text')]")),contactFirstName,"Contct Name");
		//		click(getDriver().findElement(By.xpath("//span[contains(@id,'quickFind_button_icon')]")),"Search Icon");
		//commented above two rows and if statement added to change view  utilized until Bug 691304 for contact associated view  is fixed
		if((getDriver().findElements(By.xpath("//*[@aria-label='Contact Associated View']")).size()>0)) {
			System.out.println("Dropdown is available");
			click(getDriver().findElement(By.xpath("//*[@aria-label='Contact Associated View']")),"Select View");
			click(getDriver().findElement(By.xpath("//*[contains(text(),'My Contacts')]")),"My Contacts View");}
		else {System.out.println("Dropdown is NOT available");}
		Thread.sleep(2000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//div[@col-id='firstname']//label"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 2; i <=rowList.size(); i++) {
			String title = getDriver().findElement(By.xpath("(//div[@col-id='firstname']//label)["+i+"]")).getText();
			System.out.println(title);					
			if (title.equals(contactFirstName)) {
				Thread.sleep(5000);
				doubleClick(getDriver().findElement(By.xpath("(//div[@col-id='firstname']//label)["+i+"]")), "Contact First Name");
				Thread.sleep(4000);
				break;				
			}
		}		

		return new ContactsPage();
	}


	//To add existing DEA
	public MemberFormPage typeStaticDEA(String DEANumber) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),DEANumber,"DEA Account Number");
		return this;
	}

	//Enter End Date as Today's Date in Account Numbers
	public MemberFormPage typeEndDateInAccountNumbers() {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String enddate= dateFormat.format(date);
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),"Start Date"); 
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),"Start Date");
		type(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),enddate,"Start Date"); 
		return this;
	}


	//Enter End Date as FutureDate Date in Account Numbers
	public MemberFormPage typeFutureEndDateInAccountNumbers() {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();

		Calendar c = Calendar.getInstance();    
		c.setTime(date);        
		// manipulate date        
		c.add(Calendar.DATE, 1); 
		// convert calendar to date      
		Date currentDatePlusOne = c.getTime();

		String enddate= dateFormat.format(currentDatePlusOne);			
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),"End Date"); 
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),"End Date");
		type(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),enddate,"End Date"); 
		return this;
	}

	//Enter End Date as Past Date in Account Numbers
	public MemberFormPage typePastEndDateInAccountNumbers() {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();

		Calendar c = Calendar.getInstance();    
		c.setTime(date);        
		// manipulate date        
		c.add(Calendar.DATE, -1); 
		// convert calendar to date      
		Date currentDatePlusOne = c.getTime();

		String enddate= dateFormat.format(currentDatePlusOne);			
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),"End Date"); 
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),"End Date");
		type(getDriver().findElement(By.xpath("//*[@aria-label='Date of End Date']")),enddate,"End Date"); 
		return this;
	}


	//To add existing HIN
	public MemberFormPage typeStaticHIN(String HIN) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),HIN,"HIN Account Number");
		return this;
	}

	//Verify Account Number Error Message
	public MemberFormPage verifyAccountNumberErrorMessage(String errormessage) throws InterruptedException {
		Thread.sleep(7000);
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']")),errormessage,"Account Number Error Message");
		Thread.sleep(2000);
		return this;	
	}
	//Click OK on Account Number Error Message
	public MemberFormPage clickOKOnAccountNumberErrorMessage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"OK");
		Thread.sleep(5000);
		return this;	
	}

	//Click Related > Account Member Attributes > Check+ New Account Member Attributes is not available
	public MemberFormPage selectRelatedMemberAttributesForLimMem() throws InterruptedException   {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		click(getDriver().findElement(By.xpath("//*[text()='Account Members Attributes']")),"Account Members Attributes");
		Thread.sleep(2000);
		return this;
	}
	public MemberFormPage verifyPrepopulatedAttributes() throws InterruptedException   
	{

		List<WebElement> ele=getDriver().findElements(By.xpath("//span[contains(text(),'General GPO Rebate Aggregation')]"));
		verifyElementisDisplayed(ele.size(), "General Rebate Aggregation");

		ele=getDriver().findElements(By.xpath("//span[contains(text(),'Pharmacy Rebate Aggregation')]"));
		verifyElementisDisplayed(ele.size(), "Pharmacy Rebate Aggregation");
		return this;
	}

	//Account Member Attributes Associated View
	public MemberFormPage verifyAccountMemberAttributesAssocView() throws InterruptedException   {
		verifyIsEnabled(getDriver().findElement(By.xpath("//span[text()='Account Member Attributes Associated View']")), "Account Member Attributes Associated View");
		Thread.sleep(5000);
		return this;
	}

	//Verify Account Name in  Account number Entity
	public MemberFormPage verifyAccountNameInAccountNumbers(String accountName2) {
		verifyExactTextWithTextContentAttribute((getDriver().findElement(By.xpath("//div[@data-id='ix_account.fieldControl-LookupResultsDropdown_ix_account_selected_tag_text']"))),accountName2,"Account Name in Account Numbers Entity");
		return this;
	}

	// LIMITED MEMBER Editable Fields check	

	//Editable account name		
	public MemberFormPage verifyAccountNameIsEditable(String accountName) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")),"Account Name");
		click(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")),"Account name");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")),accountName,"Account Name");
		Thread.sleep(2000);
		return this;
	}

	//Editable Account name 2
	public MemberFormPage verifyAccountName2IsEditable(String accountName2) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='ix_hiscirostername.fieldControl-text-box-text']")),"Account Name2");
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_hiscirostername.fieldControl-text-box-text']")),"Account Name2");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_hiscirostername.fieldControl-text-box-text']")),accountName2,"Account Name2");
		Thread.sleep(3000);			
		return this;
	}

	//Editable Primary Contact
	public MemberFormPage verifyPrimaryContactIsEditable(String primaryContact) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")),"Primary Contact");
		click(getDriver().findElement(By.xpath("//input[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")),"Primary Contact");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")),primaryContact,"Primary Contact");
		Thread.sleep(2000);	
		click(getDriver().findElement(By.xpath("//span[contains(@id,'primarycontactid.fieldControl-firstname0_0_0')]")),"Primary Contact search");
		return this;
	}	


	//Read Only Account Name
	public MemberFormPage verifyAccountNameIsNotEditable(String accountName) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text'][@readonly]")),"Account Name");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text'][@readonly]")),accountName,"Account Name");
		return this;
	}

	//Read Only Account Name2
	public MemberFormPage verifyAccountName2IsNotEditable(String accountName2) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='ix_hiscirostername.fieldControl-text-box-text'][@readonly]")),"Account Name2");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='ix_hiscirostername.fieldControl-text-box-text'][@readonly]")),accountName2,"Account Name2");

		return this;
	}

	//Read Only Primary Contact	- DO NOT USE
	public MemberFormPage verifyPrimaryContactIsReadOnly(String primaryContact) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new'][@readonly]")),"Primary Contact");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new'][@readonly]")),primaryContact,"Primary Contact");
		return this;
	}

	// PrimaryContact _Wave 1 2023 update
	public MemberFormPage verifyPrimaryContactIsNotEditable() {
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new'][@readonly]")),"Primary Contact");		


		return this;
	}

	//+ New Account Number Button
	public MemberFormPage verifyAddNewAccountNumberIsNotPresent() throws InterruptedException {
		Thread.sleep(3000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}		
		click(getDriver().findElement(By.xpath("(//*[contains(text(),'Account Numbers')])[2]")),"Account Numbers");
		List<WebElement> accNum= getDriver().findElements(By.xpath("//span[contains(text(),'New Account Number')]"));
		verifyElementisNotDisplayed(accNum.size()," '+ New Account Number  ' Button ");
		return this;
	}

	//Choose Existing Account Number -DEA
	public MemberFormPage doubleClickExistingAccountNumberDEA() throws InterruptedException   {
		Thread.sleep(2000);
		//		type(getDriver().findElement(By.xpath("//input[contains(@data-id,'quickFind_text')]")),"DEA","DEA");
		//		click(getDriver().findElement(By.xpath("//span[contains(@id,'quickFind_button_icon')]")),"Search Icon");
		Thread.sleep(2000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 0; i <rowList.size(); i++) {
			String title = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label)[2]")).getText();
			System.out.println(title);					
			if (title.equals("DEA")) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label)[2]")), "DEA");
				Thread.sleep(4000);
				break;				
			}
		}		

		return this;					
	}
	public MemberFormPage ClickDiscarSugesstion() throws InterruptedException{
		Thread.sleep(4000);
		if(getDriver().findElements(By.xpath("//button[@data-id='confirmButton']")).size()>1) {
			click(getDriver().findElement(By.xpath("//button[@data-id='confirmButton']")),"Confirm Button");
		}
		return this;
	}
	
	

	//Select Membership option
	public MemberFormPage selectMembershipoption() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("(//*[text()='Membership'])[2]")),"Membership");
		return this;

	}

	//Click Related Membership
	public MemberFormPage selectMembership() throws InterruptedException   {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//li[@data-id='tablist-nav_ix_account_ix_membership_AccountName']")).size()>0) {
			click(getDriver().findElement(By.xpath("//li[@data-id='tablist-nav_ix_account_ix_membership_AccountName']")),"Membership");
		}
		else {

			if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
				click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
			}else {
				click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
			}
			click(getDriver().findElement(By.xpath("(//*[text()='Membership'])[2]")),"Membership");
		}
		return this;
	}

	//Click Related Membership
	public MemberFormPage selectRelatedMembership() throws InterruptedException   {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		click(getDriver().findElement(By.xpath("(//*[text()='Membership'])[2]")),"Membership");
		//Explicit wait -09/01/2023
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Membership Associated View')]")));
		System.out.println("New Membership is displayed");
		//Thread.sleep(3000);
		return this;
	}

	//search in Membership
	public MemberFormPage searchinMemberShip(String membership) throws InterruptedException   {
		Thread.sleep(2000);
		//01/09/2024 -updated quick find xpath
		click(getDriver().findElement(By.xpath("//input[contains(@data-id,'quickFind_text')]")),"search textbox");
		typeAndEnter(((getDriver().findElement(By.xpath("//input[contains(@data-id,'quickFind_text')]")))),membership,"Membership");
		Thread.sleep(6000);
		return this;
	}

	//Click Related Membership
	public MemberFormPage verifyMembership(Boolean isPresent, String membership) throws InterruptedException   {
		if(isPresent) {
			Thread.sleep(4000);
			boolean isSurpass =false;
			for(int i=1;i<=getDriver().findElements(By.xpath("//*[@col-id='ix_membershipprovider']//span")).size();i++) {
				if(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)["+i+"]")).getText().equalsIgnoreCase(membership)) {
					if(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)["+i+"]/ancestor::div[@col-id='ix_membershipprovider']/following-sibling::div[@col-id='ix_enddate']//label")).getAttribute("aria-label").isEmpty())					{
						isSurpass=true;	
						break;
					}

				}
			}
			assertTrue(isSurpass);

			verifyDisplayed(getDriver().findElement(By.xpath("//div[@col-id='ix_membershipprovider']//a//span[contains(text(),'"+membership+"')]")),"Membership");

		}else {
			verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//div[@col-id='ix_membershipprovider']//a//span[contains(text(),'"+membership+"')]")).size(), "Membership");
			//verifyDisplayed(getDriver().findElement(By.xpath("//span[contains(text(),'No data available')]")),"Error Message");

		}
		return this;
	}

	/*
	 * User this method after using searchmemberShip method
	 */
	//Verify MEmbership is in EndDAted
	public MemberFormPage verifyMemebershipisEndDated(String membership) {


		assertTrue(!(getDriver().findElement(By.xpath("//*[@col-id='ix_membershipprovider']//span/ancestor::div[@col-id='ix_membershipprovider']/following-sibling::div[@col-id='ix_enddate']//label")).getAttribute("aria-label").isEmpty()));

		return this;
	}

	//Compare two dates, if both date should be equal provide isMatch boolean variable true
	public MemberFormPage verifyMembershipStartDate(boolean isMatch, String membershipstartDate) throws InterruptedException   {
		if(isMatch) {
			assertTrue((getDriver().findElement(By.xpath("//div[@col-id='ix_startdate']//label[contains(@class,'ms-Label labelRootStyles')]")).getAttribute("aria-label").contentEquals(membershipstartDate)));
		}else {
			assertTrue(!(getDriver().findElement(By.xpath("//div[@col-id='ix_startdate']//label[contains(@class,'ms-Label labelRootStyles')]")).getAttribute("aria-label").contentEquals(membershipstartDate)));
		}
		return this;
	}

	//Compare two dates, if both date should be equal provide isMatch boolean variable true
	public MemberFormPage verifyMembershipStartDate( String membershipstartDate) throws InterruptedException   {
		assertTrue((getDriver().findElement(By.xpath("//div[@col-id='ix_startdate']//label[contains(@class,'ms-Label labelRootStyles')]")).getAttribute("aria-label").contentEquals(membershipstartDate)));
		return this;
	}



	//Click Related Activities
	public MemberFormPage selectRelatedActivities() throws InterruptedException   {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		click(getDriver().findElement(By.xpath("(//*[text()='Activities'])[2]")),"Activities");
		Thread.sleep(10000);
		return this;
	}

	//Select Open Activities
	public MemberFormPage selectOpenActivitiesView() throws InterruptedException   {
		Thread.sleep(10000);
		List<WebElement> dropdown= getDriver().findElements(By.xpath("(//span[contains(@id,'ViewSelector')])[4]"));
		if(dropdown.size()>0) {
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[4]")),"Select a view");
		}else {
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[2]")),"Select a view");
		}
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Open Activity Associated View')]")),"Open Activitiy Associtated View");
		Thread.sleep(15000);
		return this;
	}


	//Select Open Activities
	public MemberFormPage selectOpenActivitiesViewAfterTask() throws InterruptedException   {
		Thread.sleep(2000);
		List<WebElement> dropdown= getDriver().findElements(By.xpath("(//span[contains(@id,'ViewSelector')])[4]"));
		if(dropdown.size()>0) {
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[4]")),"Select a view");
		}else {
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[2]")),"Select a view");
		}
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Open Activity Associated View')]")),"Open Activitiy Associtated View");
		Thread.sleep(15000);
		return this;
	}


	//Create Task under Activies option
	public MemberFormPage createTaskUnderActivities() {

		click(getDriver().findElement(By.xpath("//button[@title='Task']")),"Task Option");

		return this;
	}

	//Select Open Activities
	public MemberFormPage selectAllActivity() throws InterruptedException   {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[2]")),"Select a view");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'All Activity Associated View')]")),"All Activitiy Associtated View");
		Thread.sleep(15000);
		return this;
	}


	//Select Open Activities
	public MemberFormPage selectClosedActivity() throws InterruptedException   {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[2]")),"Select a view");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Closed Activity Associated View')]")),"Closed Activitiy Associtated View");
		Thread.sleep(15000);
		return this;
	}

	// Open Tasks
	public MemberFormPage openTasks() throws InterruptedException   {
		Thread.sleep(2000);
		Actions a=new Actions(getDriver());
		a.doubleClick(getDriver().findElement(By.xpath("//label[@aria-label='Task']"))).build().perform();;

		return this;
	}

	// Open Fax
	public MemberFormPage openFax() throws InterruptedException   {
		Thread.sleep(2000);
		Actions a=new Actions(getDriver());
		a.doubleClick(getDriver().findElement(By.xpath("//label[@aria-label='Fax']"))).build().perform();;

		return this;
	}

	// Open Phone Call
	public MemberFormPage openPhoneCall() throws InterruptedException   {
		Thread.sleep(2000);
		Actions a=new Actions(getDriver());
		a.doubleClick(getDriver().findElement(By.xpath("//label[@aria-label='Phone Call']"))).build().perform();;

		return this;
	}

	// Open Phone Call
	public MemberFormPage openAppointment() throws InterruptedException   {
		Thread.sleep(2000);
		Actions a=new Actions(getDriver());
		a.doubleClick(getDriver().findElement(By.xpath("//label[@aria-label='Appointment']"))).build().perform();;

		return this;
	}


	//verify Task page has been opened

	public MemberFormPage verifyTasksPage() throws InterruptedException   {
		Thread.sleep(2000);

		List<WebElement> elemenet=getDriver().findElements(By.xpath("//span[contains(text(),'Mark Complete')]"));
		verifyElementisDisplayed(elemenet.size(), "Tasks Page");
		return this;
	}

	//Click Mark Complete

	public MemberFormPage clickMarkComplete() throws InterruptedException   {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Mark Complete')]")),"Mark Completed");
		return this;
	}


	public MemberFormPage verifyTasksCompletionstatus(String status) throws InterruptedException, IOException   {
		Thread.sleep(2000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//div[@col-id='statecode']//label[contains(@class,'option')]/div[contains(@class,'ms-TooltipHost')]")),"Completion status");
		System.out.println(saveStatus);
		assertTrue(saveStatus.contains(status),"Status is not "+status);
		return this;
	}

	public MemberFormPage completeAllTask() throws InterruptedException {
		selectOpenActivitiesViewAfterTask();
		List<WebElement> checkmark= getDriver().findElements(By.xpath("//*[@col-id='activitytypecode']//label[contains(@class,'option')]"));
		if(checkmark.size()>0) {
			Actions actions= new Actions(getDriver());
			actions.moveToElement(getDriver().findElement(By.xpath("//div[contains(text(),'Activity Type')]/ancestor::div[@col-id='activitytypecode']/preceding-sibling::div[@col-id='__row_status']//div[@class='ag-header-cell-comp-wrapper']//input[@type='checkbox']"))).click().build().perform();
			Thread.sleep(2000);
			if(getDriver().findElements(By.xpath("//button[@data-id='activitypointer|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.activitypointer.SaveAsCompleted']")).size()>0) {
				click(getDriver().findElement(By.xpath("//button[@data-id='activitypointer|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.activitypointer.SaveAsCompleted']")),"Mark Complete");
			}else {
				click(getDriver().findElement(By.xpath("//button[@data-id='activitypointer|NoRelationship|SubGridAssociated|Mscrm.SubGrid.activitypointer.MainTab.Actions.SaveAsCompleted']")),"Delete Activity Button");
			}
			Thread.sleep(4000);
			List<WebElement> closbutton=getDriver().findElements(By.xpath("//button[contains(@title,'Close Task')]"));
			List<WebElement> closphone=getDriver().findElements(By.xpath("//button[contains(@title,'Close Phone')]"));
			List<WebElement> closletter=getDriver().findElements(By.xpath("//button[contains(@title,'Close Letter')]"));
			if(closbutton.size()>0) {
				click(getDriver().findElement(By.xpath("//button[contains(@title,'Close Task')]")),"Confirm Delete Button");	
			}else if(closphone.size()>0){
				click(getDriver().findElement(By.xpath("//button[contains(@title,'Close Phone')]")),"Confirm Delete Button");
			}else if(closletter.size()>0) {
				click(getDriver().findElement(By.xpath("//button[contains(@title,'Close Letter')]")),"Confirm Delete Button");
			}else {
				click(getDriver().findElement(By.xpath("//button[contains(@title,'Close')]")),"Confirm Delete Button");
			}
		}
		Thread.sleep(10000);
		return this;
	}

	public MemberFormPage completePhonecall() throws InterruptedException {

		List<WebElement> checkmark= getDriver().findElements(By.xpath("//div[contains(@class,'ms-Checkbox is-enabled RowSelectionCheckMarkSpan')]"));
		if(checkmark.size()>0) {
			click(getDriver().findElement(By.xpath("//i[contains(@class,'ms-Checkbox-checkmark checkmark')]")),"Checkbox");
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//button[@data-id='activitypointer|NoRelationship|SubGridAssociated|Mscrm.SubGrid.activitypointer.MainTab.Actions.SaveAsCompleted']")),"Delete Activity Button");
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//button[contains(@title,'Close Phone Call')]")),"Confirm Delete Button");

			//button[contains(@aria-label,'Close Phone')]
		}

		return this;
	}

	public MemberFormPage verifySubject(String subject) throws InterruptedException, IOException   {
		Thread.sleep(2000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//div[@col-id='subject']//span")),"Subject");
		System.out.println(saveStatus);
		assertTrue(saveStatus.contentEquals(subject),"Subject does not match "+subject);
		return this;
	}




	//Disabled # Estimated Locations
	public MemberFormPage verifyEstimatedNumLocationsIsNotEditable(String numEstLoc) throws InterruptedException {
		Thread.sleep(3000);
		//2023 Wave1 update
		//verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_ofestimatedlocations.fieldControl-whole-number-text-input']")),"# of Estimated Locations");
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='ix_ofestimatedlocations.fieldControl-whole-number-text-input'][@readonly]")),"# of Estimated Locations");
		//	typeLocked(getDriver().findElement(By.xpath("//input[@data-id='ix_ofestimatedlocations.fieldControl-whole-number-text-input'][@readonly]")),numEstLoc,"# of Estimated Locations");

		return this;
	}

	//Read Only NAICS		
	public MemberFormPage verifyNAICSIsNotEditable() throws InterruptedException {
		//Wave1 2023 Update
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='ix_naicscode.fieldControl-LookupResultsDropdown_ix_naicscode_textInputBox_with_filter_new'][@readonly]")),"NAICS");
		//		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='ix_naicscode.fieldControl-LookupResultsDropdown_ix_naicscode_textInputBox_with_filter_new'][@readonly]")),nAICS,"NAICS");
		//List<WebElement> primaryCont=getDriver().findElements(By.xpath("//input[@data-id='ix_naicscode.fieldControl-LookupResultsDropdown_ix_naicscode_textInputBox_with_filter_new']"));
		//verifyElementisNotDisplayed(primaryCont.size(), "NAICS Input");
		return this;
	}

	//Verify HIN is null in member form
	public MemberFormPage verifyHINIsNull() {
		verifyNullValue(getDriver().findElement(By.xpath("//*[@data-id='ix_dea.fieldControl-text-box-text']")),"HIN"); 
		return this;
	}

	//Membership Associated View
	public MemberFormPage verifyMembershipAssocView() throws InterruptedException   {
		verifyIsEnabled(getDriver().findElement(By.xpath("//span[text()='Membership Associated View']")), "Membership Associated View");
		Thread.sleep(2000);
		return this;
	}

	//+ New Membership Button
	public MemberFormPage verifyAddNewMembershipIsNotPresent() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'LOB')]")),"Line Of Businessess");
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		click(getDriver().findElement(By.xpath("(//*[text()='Membership'])[2]")),"Membership");
		List<WebElement> newMShip= getDriver().findElements(By.xpath("//button[@aria-label='New Membership. Add New Membership']"));
		verifyElementisNotDisplayed(newMShip.size()," '+ New Membership  ' Button ");
		return this;
	}	

	//+ New Membership Button
	public MemberFormPage verifyAddNewMembershipIsNotPresentWithoutNavigation() throws InterruptedException {

		List<WebElement> newMShip= getDriver().findElements(By.xpath("//button[@aria-label='New Membership. Add New Membership']"));
		verifyElementisNotDisplayed(newMShip.size()," '+ New Membership  ' Button ");
		return this;
	}	


	//+ New Membership Button
	public MemberFormPage navigateToMembershipPage() throws InterruptedException {
		Thread.sleep(3000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		click(getDriver().findElement(By.xpath("(//*[text()='Membership'])[2]")),"Membership");
		return this;
	}	


	// Read Only Membership Entity Error Info Message for Limited Member
	public MemberFormPage verifyMembershipEntityIsNotEditable(String errorMessage) throws InterruptedException {
		Thread.sleep(2000);
		verifyExactText((getDriver().findElement(By.xpath("//span[@data-id='warningNotification']"))),errorMessage,"Membership Entity Read Only Error Message");
		return this;
	}

	// Account is in draft status message is verified
	public MemberFormPage VerifyDraftMessage(String errorMessage) throws InterruptedException {
		Thread.sleep(2000);

		List<WebElement> draftmessage=getDriver().findElements(By.id("notificationExpandIcon"));
		if(draftmessage.size()>0) {
			click(getDriver().findElement(By.id("notificationExpandIcon")),"Multiple message");
		}
		verifyExactText((getDriver().findElement(By.xpath("//span[@data-id='warningNotification']"))),errorMessage,"This account is in Draft status message" );

		if(draftmessage.size()>0) {
			click(getDriver().findElement(By.id("notificationExpandIcon")),"Multiple message");
		}

		return this;
	}


	// Account is in draft message should not be displayed
	public MemberFormPage VerifyDraftMessageNotDisplayed(String errorMessage) throws InterruptedException {
		Thread.sleep(2000);

		List<WebElement> draftmessage=getDriver().findElements(By.xpath("//span[@data-id='warningNotification' and contains(text(),'\"+errorMessage+\"')]"));

		verifyElementisNotDisplayed(draftmessage.size(), errorMessage);
		return this;
	}


	//Click on Membership save and close and Verify Membership Error Message
	public MemberFormPage verifyMembershipErrorMessage(String errormessage) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membership|NoRelationship|Form|Mscrm.Form.ix_membership.SaveAndClose']")),"Save and Close");
		Thread.sleep(5000);
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']")),errormessage,"Membership Error Message");
		Thread.sleep(2000);
		return this;	
	}

	//Click OK on Membership Error Message
	public MemberFormPage clickOKOnMembershipErrorMessage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"OK");
		Thread.sleep(5000);
		return this;	
	}	
	//Verify Name and Calculated Name in  Account number Entity
	public MemberFormPage verifyNameAndCalculatedNameInAccountNumbers() throws InterruptedException {
		String account =(getDriver().findElement(By.xpath("//*[@data-id='ix_account.fieldControl-LookupResultsDropdown_ix_account_selected_tag_text']")).getAttribute("title"));
		System.out.println("Account field's value in Account Number Entity is  : " + account);

		String accNumType =(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")).getAttribute("title"));
		System.out.println("Account Number Type field's value in Account Number Entity is  : " + accNumType);

		String accNum =(getDriver().findElement(By.xpath("//input[@data-id='ix_number.fieldControl-text-box-text']")).getAttribute("value"));
		System.out.println("Account Number field's value in Account Number Entity is  : " + accNum);

		String name =account.concat(" - ").concat(accNumType).concat(" - ").concat(accNum);
		System.out.println(name);
		Thread.sleep(6000);
		verifyExactValue((getDriver().findElement(By.xpath("//input[@data-id='ix_calculatedname.fieldControl-text-box-text']"))),name,"Calculated Name in Account Numbers Entity");
		verifyExactValue((getDriver().findElement(By.xpath("//input[@data-id='ix_accountnumbername.fieldControl-text-box-text']"))),name,"Name in Account Numbers Entity");
		return this;
	}

	//Add New Account Members Attributes Button
	public MemberFormPage verifyNewAccountMembersAttributesIsNotPresent() {
		List<WebElement> MA= getDriver().findElements(By.xpath("//span[contains(text(),'New Account Member Attributes')]"));
		verifyElementisNotDisplayed(MA.size()," '+ New Account Members Attributes  ' Button ");
		return this;
	}


	//Add New Account Members Attributes Button
	public MemberFormPage clickNewAccountnumberMemberAttribute() throws InterruptedException {

		click( getDriver().findElement(By.xpath("//span[contains(text(),'New Account Member Attributes')]")),"New Account Member Attribute");
		List<WebElement> MA= getDriver().findElements(By.xpath("//h1[contains(text(),'New Account Member Attributes')]"));
		verifyElementisDisplayed(MA.size()," '+ New Account Members Attributes  ' Page ");

		return this;

	}

	public MemberFormPage delectMemberAttribute() throws InterruptedException {
		List<WebElement> attribute=getDriver().findElements(By.xpath("//div[contains(@class,'ag-row') and @aria-label='Press SPACE to deselect this row.']"));
		if(attribute.size()>0) {
			click( getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']")),"Check MArk");	
		}

		click( getDriver().findElement(By.xpath("//button[@data-id='ix_memberattributes|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_memberattributes.Deactivate']")),"Deactivated");
		return this;
	}


	//Add Account New Account Member Attribute
	public MemberFormPage addNewAccountMemberAttribute(String emsAttribute, String attributeValue) throws InterruptedException {

		click(getDriver().findElement(By.xpath("//input[@data-id='ix_emsattributetype.fieldControl-LookupResultsDropdown_ix_emsattributetype_textInputBox_with_filter_new']")),"Member Attribute");
		Thread.sleep(6000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_emsattributetype.fieldControl-LookupResultsDropdown_ix_emsattributetype_textInputBox_with_filter_new']")))),emsAttribute,"Member Attribute");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//span[contains(@data-id,'ix_emsattributetype.fieldControl-ix_name')]")),"EMS Attribute");

		click(getDriver().findElement(By.xpath("//input[@data-id='ix_emsattributevalue.fieldControl-LookupResultsDropdown_ix_emsattributevalue_textInputBox_with_filter_new']")),"Member Attribute Value");
		Thread.sleep(6000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_emsattributevalue.fieldControl-LookupResultsDropdown_ix_emsattributevalue_textInputBox_with_filter_new']")))),attributeValue,"Member Attribute Value");
		Thread.sleep(6000);
		click(getDriver().findElement(By.xpath("//span[contains(@data-id,'ix_emsattributevalue.fieldControl-ix_name')]")),"EMS Attribute");

		click(getDriver().findElement(By.xpath("//span[contains(text(),'Save')]")),"Save Button");
		Thread.sleep(5000);
		return  this;
	}

	//~~~~Limited Member Non Editable fields~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~XX~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// Disabled Store Premier Start Date  -Type verification cannot be made as the element is not interactable/disabled.
	public MemberFormPage verifyPremierStartDateIsNotEditable(String membershipProviderStartDate ) {
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_premiermemberstartdate.fieldControl-date-time-input']")),"Premier Start Date");
		return this;
	}

	// Disabled Store Premier End Date -Type verification cannot be made as the element is not interactable/disabled.
	public MemberFormPage verifyPremierEndDateIsNotEditable(String membershipEndDate ) {
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_premiermemberenddate.fieldControl-date-time-input']")),"Premier End Date");
		return this;
	}

	// Read Only Store Location # -Type verification possible on read only fields as they are interactable
	public MemberFormPage verifyStoreLocNumIsNotEditable(String storeLocNum ) {
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='ix_locationnumber.fieldControl-text-box-text'][@readonly]")),"Store Location#");
		typeLocked(getDriver().findElement(By.xpath("//input[@data-id='ix_locationnumber.fieldControl-text-box-text']")),storeLocNum, "Store Location #");
		return this;
	}

	// Disabled Store Location Type -Type verification cannot be made as the element is not interactable/disabled.
	public MemberFormPage verifyStoreLocTypeIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_locationtype.fieldControl-option-set-select']")),"Store Location Type");
		return this;
	}
	//Disabled Direct Parent details
	public MemberFormPage verifyDirectParentDetailsIsNotEditable(String directParent,String crmNumber1, String dPReason) throws InterruptedException {
		//DP
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='parentaccountid.fieldControl-Lookup_parentaccountid']/div/div/div[1]")),directParent,"Direct Parent");
		//EIN
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='DPQuickViewForm.ix_premierein.fieldControl-text-box-text'][@readonly]")),"Entity Code");
		typeLocked(getDriver().findElement(By.xpath("//input[@data-id='DPQuickViewForm.ix_premierein.fieldControl-text-box-text']")),crmNumber1, "Entity Code");
		//DPR
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_parentrelationship.fieldControl-option-set-select']")),"Direct Parent Relation");
		//DPRD
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_directparentrelationdate.fieldControl-date-time-input']")),"Direct Parent Relation Date");
		//DP Exception Reason
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='ix_dpexceptionreason.fieldControl-text-box-text'][@readonly]")),"Direct Parent Exception Reason");
		typeLocked(getDriver().findElement(By.xpath("//input[@data-id='ix_dpexceptionreason.fieldControl-text-box-text']")),dPReason, "DP Exception Reason");
		return this;
	}


	//Select Direct Parent details
	public MemberFormPage selectDriectParent() throws InterruptedException {
		//DP
		click(getDriver().findElement(By.xpath("//div[contains(text(),'Direct Parent')]/preceding-sibling::div")), "Direct Parent");
		return this;
	}


	//Disabled Top Parent details
	public MemberFormPage verifyTopParentDetailsIsNotEditable(String topParent,String crmNumber1, String tPReason) throws InterruptedException {
		//TP
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_topparent.fieldControl-Lookup_ix_topparent']/div/div/div[1]")),topParent,"Top Parent");
		//EIN
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='TPQuickViewForm.ix_premierein.fieldControl-text-box-text'][@readonly]")),"Entity Code");
		typeLocked(getDriver().findElement(By.xpath("//input[@data-id='TPQuickViewForm.ix_premierein.fieldControl-text-box-text']")),crmNumber1, "Entity Code");
		//IsTP
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_istopparent.fieldControl-checkbox-select']")),"Is Top Parent");
		//TPR
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_topparentrelationship.fieldControl-option-set-select']")),"Top Parent Relation");
		//TPRD
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_topparentrelationdate.fieldControl-date-time-input']")),"Top Parent Relation Date");
		//TP Exception Reason
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='ix_tpexceptionreason.fieldControl-text-box-text'][@readonly]")),"Top Parent Exception Reason");
		typeLocked(getDriver().findElement(By.xpath("//input[@data-id='ix_tpexceptionreason.fieldControl-text-box-text']")),tPReason, "TP Exception Reason");
		//TP Classification
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_topparentclassification.fieldControl-option-set-select']")),"Top Parent Classification");
		return this;
	}

	//Disabled Group field
	public MemberFormPage verifyGroupIsNotEditable(String verifyGroup) throws InterruptedException {
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_group.fieldControl-Lookup_ix_group']/div/div/div[1]")),verifyGroup,"Group");
		return this;
	}

	//Disabled Class of Trade field
	public MemberFormPage verifyClassOfTradeIsNotEditable(String classOfTrade ) throws InterruptedException {
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_classoftradedetail.fieldControl-Lookup_ix_classoftradedetail']/div/div/div[1]")),classOfTrade,"Class Of Trade");
		Thread.sleep(3000);
		return this;
	}				

	//Disabled Facility type with value populated in the field
	public MemberFormPage verifyFacilityTypeIsNotEditable(String verifyFacilityType) throws InterruptedException {
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_facilitytype.fieldControl-Lookup_ix_facilitytype']/div/div/div[1]")),verifyFacilityType,"Facility type"); 
		return this;
	}


	//Disabled Business Classification
	public MemberFormPage verifyBusinessClassificationIsNotEditable(String verifyBusinessClassification) throws InterruptedException {
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_businessclassification.fieldControl-Lookup_ix_businessclassification']/div/div/div[1]")),verifyBusinessClassification,"Business Classification"); 
		return this;
	}

	//Disabled Premier Owner
	public MemberFormPage verifyPremierOwnerIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_premierowner.fieldControl-checkbox-select']")),"Premier Owner"); 
		return this;
	}

	//Disabled Account Status
	public MemberFormPage verifyAccountStatusIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_accountstatus.fieldControl-option-set-select']")),"Account Status"); 
		return this;
	}

	//Disabled Application Date
	public MemberFormPage verifyApplicationDateIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_applicationstartdate.fieldControl-date-time-input']")),"Application Date"); 
		return this;
	}

	//Disabled CAMS flag
	public MemberFormPage verifyCAMSFlagIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_camsflag.fieldControl-checkbox-select']")),"CAMS Flag"); 
		return this;
	}

	//Disabled Exclude from Roster
	public MemberFormPage verifyExcludeFromRosterIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_donotroster.fieldControl-checkbox-select']")),"Exclude from Roster"); 
		return this;
	}

	//Disabled Participation Type
	public MemberFormPage verifyParticipationTypeIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_participationtype.fieldControl-option-set-select']")),"Participation Type"); 
		return this;
	}


	//Disabled Provider Select: MD
	public MemberFormPage verifyProviderSelectMDIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_providerselectmd.fieldControl-option-set-select']")),"Provider Select:MD "); 
		return this;
	}

	// Read Only Business Key
	public MemberFormPage verifyBusinessKeyIsNotEditable(String verifyBKActive ) {
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='ix_businesskey.fieldControl-text-box-text'][@readonly]")),"Business Key");
		typeLocked(getDriver().findElement(By.xpath("//input[@data-id='ix_businesskey.fieldControl-text-box-text']")),verifyBKActive, "Business Key");
		return this;
	}


	//Disabled BK Active
	public MemberFormPage verifyBKActiveIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_bkactive.fieldControl-checkbox-select']")),"BK Active "); 
		return this;
	}				

	// Read Only Siebel ID
	public MemberFormPage verifySiebelIDIsNotEditable(String siebelID ) {
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='ix_siebel_id.fieldControl-text-box-text'][@readonly]")),"Siebel ID");
		typeLocked(getDriver().findElement(By.xpath("//input[@data-id='ix_siebel_id.fieldControl-text-box-text']")),siebelID, "Siebel ID");
		return this;
	}

	// Disabled Region
	public MemberFormPage verifyRegionIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_regionnew.fieldControl-option-set-select']")),"Region");
		return this;
	}

	// Disabled Sponsor Details
	public MemberFormPage verifySponsorDetailsIsNotEditable(String sponsor) {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_issponsor.fieldControl-checkbox-select']")),"Is Sponsor");
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_sponsor.fieldControl-Lookup_ix_sponsor']/div/div/div[1]")),sponsor,"Sponsor");
		return this;
	}

	// Disabled Supplier Record  
	public MemberFormPage verifySupplierRecordIsNotEditable() {
		//		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='ix_supplierrecordid.fieldControl-LookupResultsDropdown_ix_supplierrecordid_textInputBox_with_filter_new'][@readonly]")),"Supplier Record");
		//		typeLocked(getDriver().findElement(By.xpath("//input[@data-id='ix_supplierrecordid.fieldControl-LookupResultsDropdown_ix_supplierrecordid_textInputBox_with_filter_new']")),supplierRecord, "Supplier Record");
		// Readonly above locators are removed from DOM in Wave 1 2023
		//		List<WebElement> supplierRecord=getDriver().findElements(By.xpath("//input[@data-id='ix_supplierrecordid.fieldControl-LookupResultsDropdown_ix_supplierrecordid_textInputBox_with_filter_new']"));
		//	verifyElementisNotDisplayed(supplierRecord.size(), "Supplier Record Input");
		return this;
	}


	// Disabled Ownership
	public MemberFormPage verifyOwnershipIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_ownership.fieldControl-option-set-select']")),"Ownership");
		click(getDriver().findElement(By.xpath("//select[@data-id='ix_ownership.fieldControl-option-set-select']")),"Ownership");
		return this;
	}


	// Read Only Stock Symbol
	public MemberFormPage verifyStockSymbolIsNotEditable(String stockSymbol) {
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='ix_stocksymbol.fieldControl-text-box-text'][@readonly]")),"Stock Symbol");
		typeLocked(getDriver().findElement(By.xpath("//input[@data-id='ix_stocksymbol.fieldControl-text-box-text']")),stockSymbol, "Stock Symbol");
		return this;
	}


	// Read Only Exchange
	public MemberFormPage verifyExchangeIsNotEditable(String exchange) {
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='ix_exchange.fieldControl-text-box-text'][@readonly]")),"Exchange");
		typeLocked(getDriver().findElement(By.xpath("//input[@data-id='ix_exchange.fieldControl-text-box-text']")),exchange, "Exchange");
		return this;
	}

	// Disabled Payment Entity Details _Wave 1 2023 update
	public MemberFormPage verifyPaymentEntityDetailsIsNotEditable(String paymentEntity) {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_ispaymententity.fieldControl-checkbox-select']")),"Is Payment Entity");
		return this;
	}

	// ReadOnly Payment Entity Details -Do not Use
	public MemberFormPage verifyPaymentEntityDetailsIsReadOnly(String paymentEntity) {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_ispaymententity.fieldControl-checkbox-select']")),"Is Payment Entity");
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='ix_paymententityid.fieldControl-LookupResultsDropdown_ix_paymententityid_textInputBox_with_filter_new'][@readonly]")),"Payment Entity");
		typeLocked(getDriver().findElement(By.xpath("//input[@data-id='ix_paymententityid.fieldControl-LookupResultsDropdown_ix_paymententityid_textInputBox_with_filter_new']")),paymentEntity, "Payment Entity");
		return this;
	}

	// Disabled Corporate Parent  Details
		public MemberFormPage verifyCorporateParentDetailsIsNotEditable(String corporateParent,String crmNumber1) {
			//CP
			verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_corporateparentname.fieldControl-Lookup_ix_corporateparentname']/div/div/div[1]")),corporateParent,"Corporate Parent");
			//EIN
			verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='CPEntityCode.ix_premierein.fieldControl-text-box-text'][@readonly]")),"Entity Code");
			typeLocked(getDriver().findElement(By.xpath("//input[@data-id='CPEntityCode.ix_premierein.fieldControl-text-box-text']")),crmNumber1, "Entity Code");
			//Is CP
			verifyDisabledFields(getDriver().findElement(By.xpath("//input[@aria-label='Is Corporate Account']")),"Is Corporate Parent");
			//CP Start Date
			verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_corporateparentstartdate.fieldControl-date-time-input']")),"Corporate Parent Start Date");
			//FSP Override
			verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_corporateparentoverride.fieldControl-checkbox-select']")),"Corporate Parent Override");
			return this;
		}

	// Disabled Corporate Rebate Details
	public MemberFormPage verifyCorporateRebateDetailsIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_corporaterebate.fieldControl-option-set-select']")),"Corporate Rebate");
		click(getDriver().findElement(By.xpath("//select[@data-id='ix_corporaterebate.fieldControl-option-set-select']")),"Corporate Rebate");
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_corporaterebatefeedate.fieldControl-date-time-input']")),"Corporate Rebate Fee Date");
		return this;
	}

	// Disabled Food Service Parent  Details
	public MemberFormPage verifyFoodServiceParentDetailsIsNotEditable(String foodServiceParent,String crmNumber1) {
		//FSP
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_foodserviceparentname.fieldControl-Lookup_ix_foodserviceparentname']/div/div/div[1]")),foodServiceParent,"Food Service Parent");
		//EIN
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@data-id='FSPEntityCode.ix_premierein.fieldControl-text-box-text'][@readonly]")),"Entity Code");
		typeLocked(getDriver().findElement(By.xpath("//input[@data-id='FSPEntityCode.ix_premierein.fieldControl-text-box-text']")),crmNumber1, "Entity Code");
		//Is FSP
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_isfoodserviceparent.fieldControl-checkbox-select']")),"Is Food Service Parent");
		//FSP Start Date
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_foodserviceparentstartdate.fieldControl-date-time-input']")),"Food Service Parent Start Date");
		//FSP Override
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_foodserviceparentoverride.fieldControl-checkbox-select']")),"Food Service Parent Override");
		return this;
	}

	// Disabled Require Manual AG Assignment
	public MemberFormPage verifyRequireManualAGAssignmentIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_requiremanualagassignment.fieldControl-checkbox-select']")),"Require Manual AG Assignment");
		return this;
	}


	// Read Only Affiliate Group
	public MemberFormPage verifyAffiliateGroupIsNotEditable(String affiliateGroup) {
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_affiliategroup.fieldControl-Lookup_ix_affiliategroup']/div/div/div[1]")), affiliateGroup,"Affiliate Group");
		return this;
	}

	// Disabled Affiliate Group Effective Date
	public MemberFormPage verifyAffiliateGroupEffectiveDateIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_affiliategroupeffectivedate.fieldControl-date-time-input']")),"Affiliate Group Efective Date ");
		return this;
	}

	// Disabled Record Status
	public MemberFormPage verifyRecordStatusIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Record Status");
		return this;
	}

	// Disabled Record Change Status
	public MemberFormPage verifyRecordChangeStatusIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_recordchangestatus.fieldControl-option-set-select']")),"Record Change Status");
		return this;
	}

	// Disabled Account Type
	public MemberFormPage verifyAccountTypeIsNotEditable() {
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='customertypecode.fieldControl-option-set-select']")),"Account Type");
		return this;
	}

	// Read Only Premier Roster
	public MemberFormPage verifyPremierRosterIsNotEditable(String premierRoster) {
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_premierrosterid.fieldControl-Lookup_ix_premierrosterid']/div/div/div[1]")), premierRoster,"Premier Roster");
		return this;
	}	


	// Disabled FBO  Details
	public MemberFormPage verifyFBOtDetailsIsNotEditable(String fBO) {
		//FBO
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_fbo.fieldControl-Lookup_ix_fbo']/div/div/div[1]")),fBO,"FBO");
		//Is FBO
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_isfbo.fieldControl-checkbox-select']")),"Is FBO");
		//FBO Relation Date
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_fborelationdate.fieldControl-date-time-input']")),"FBO Relation Date");
		//FBO Manual Override
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_fbomanualoverride.fieldControl-checkbox-select']")),"FBO Manual Override");
		//FBO GPO
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_fbogpo.fieldControl-checkbox-select']")),"FBO GPO");
		//FBO Type
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_fbotype.fieldControl-option-set-select']")),"FBO Type");
		//FBO Effective Date
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_fbotypeeffectivedate.fieldControl-date-time-input']")),"FBO Effective Date");
		return this;
	}


	// Disabled Current Internal Rep  
	public MemberFormPage verifyCurrentInternalRepIsNotEditable(String currentInternalRep) {
		//FBO
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_currentinternalrep.fieldControl-Lookup_ix_currentinternalrep']/div/div/div[1]")),currentInternalRep,"Current Internal Rep");
		return this;
	}

	// Disabled Current Field Rep  
	public MemberFormPage verifyCurrentFieldRepIsNotEditable(String currentFieldRep) {
		//FBO
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_currentfieldrep.fieldControl-Lookup_ix_currentfieldrep']/div/div/div[1]")),currentFieldRep,"Current Field Rep");
		return this;
	}

	//Disabled Fee Share Details
	public MemberFormPage verifyFeeShareDetailsIsNotEditable() {
		//Fee Share
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_feeshareeligible.fieldControl-checkbox-select']")),"Fee Share");
		//Fee Share Eligible Date
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_feeshareeligibledate.fieldControl-date-time-input']")),"Fee Share Eligible Date");
		return this;
	}


	//Read Only Referred By
	public MemberFormPage verifyReferredByIsNotEditable(String referredBy) throws InterruptedException {
		Thread.sleep(2000);
		//Wave1 2023 update
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='ix_referredby.fieldControl-LookupResultsDropdown_ix_referredby_textInputBox_with_filter_new'][@readonly]")),"Referred By");
		//		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='ix_referredby.fieldControl-LookupResultsDropdown_ix_referredby_textInputBox_with_filter_new'][@readonly]")),referredBy,"Referred By");
		//List<WebElement> primaryCont=getDriver().findElements(By.xpath("//input[@data-id='ix_referredby.fieldControl-LookupResultsDropdown_ix_referredby_textInputBox_with_filter_new']"));
		//verifyElementisNotDisplayed(primaryCont.size(), "Referred By field Input");
		return this;
	}
	// Editable Do Not Verify Address
	public MemberFormPage verifyDoNotVerifyAddresIsEditable(String doNotVerifyAddress ) throws InterruptedException {
		//verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='telephone1.fieldControl-phone-text-input']")),"Do Not Verify Address");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//select[@data-id='ix_donotverifyaddress.fieldControl-checkbox-select']")),"Do Not Verify Address");
		Thread.sleep(5000);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_donotverifyaddress.fieldControl-checkbox-select']")),doNotVerifyAddress, "Do Not Verify Address");
		Thread.sleep(2000);		
		return this;
	}


	//Disabled Membership Provider field on Membership Entity 		
	public MemberFormPage verifyMPOnMembershipIsNotEditable(String errorMessage2) throws InterruptedException {
		Thread.sleep(2000);
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_membershipprovider.fieldControl-Lookup_ix_membershipprovider']/div/div/div[1]")), errorMessage2, "Account field in Membership Entity");
		return this;
	}
	// Disabled Account field on Membership Entity
	public MemberFormPage verifyAccountOnMembershipIsNotEditable(String errorMessage1) throws InterruptedException {
		Thread.sleep(2000);
		verifyExactTextWithTextContentAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_accountname.fieldControl-Lookup_ix_accountname']/div/div/div[1]")), errorMessage1, "Account field in Membership Entity");				
		return this;
	}

	// Disabled Membership Type on Membership Entity
	public MemberFormPage verifyMembershipTypeIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_membershiptype.fieldControl-option-set-select']")),"Membership Type");
		return this;
	}

	//Disabled Stop Cascade on Membership Entity
	public MemberFormPage verifyStopCascadeIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_stopcascadeaanode.fieldControl-checkbox-select']")),"Stop Cascade");
		return this;
	}

	// Disabled Start Date on Membership Entity
	public MemberFormPage verifyStartDateIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")),"Start Date");
		return this;
	}

	// Disabled End Date on Membership Entity
	public MemberFormPage verifyEndDateIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyDisabledFields(getDriver().findElement(By.xpath("//input[@aria-label='Date of End Date']")),"End Date");
		return this;
	}

	// Disabled Membership Level on Membership Entity
	public MemberFormPage verifyMembershipLevelIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_membershiplevel.fieldControl-option-set-select']")),"Membership Level");
		return this;
	}


	// Disabled End Reason on Membership Entity
	public MemberFormPage verifyEndReasonIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyDisabledFields(getDriver().findElement(By.xpath("//select[@data-id='ix_endreason.fieldControl-option-set-select']")),"End Reason");
		return this;
	}
	//Editable Type street1
	public MemberFormPage verifyStreet1IsEditable(String street1 ) throws InterruptedException {
		verifyIsEnabled(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")),"Street1");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")),street1,"Street1");
		Thread.sleep(2000);			
		return this;
	}

	// Editable Type street 2
	public MemberFormPage verifyStreet2IsEditable(String street2 ) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text']")),"Street2");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text']")),"Street2");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text']")),street2,"Street2");
		Thread.sleep(2000);			

		return this;
	}

	//Editable delivery information
	public MemberFormPage verifyDeliveryInfoIsEditable(String deliveryInfo ) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='address1_line3.fieldControl-text-box-text']")),"Delivery Info");
		click(getDriver().findElement(By.xpath("//input[@data-id='address1_line3.fieldControl-text-box-text']")),"Delivery Info");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='address1_line3.fieldControl-text-box-text']")),deliveryInfo,"Delivery Info");
		Thread.sleep(2000);				
		return this;
	}

	//Editable City
	public MemberFormPage verifyCityIsEditable(String city ) throws InterruptedException {
		verifyIsEnabled((getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']"))),"City");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']")),"City");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']")),city,"City");
		Thread.sleep(2000);				
		return this;
	}

	//Editable State
	public MemberFormPage verifyStateIsEditable(String state ) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")),"State");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")),"State");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")),state,"State");
		Thread.sleep(2000);				
		return this;
	}

	//Editable County
	public MemberFormPage verifyCountyIsEditable(String county ) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']")),"County");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']")),"County");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']")),county,"County");
		Thread.sleep(2000);
		return this;
	}

	//Editable country
	public MemberFormPage verifyCountryIsEditable(String country ) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),country,"Country");
		Thread.sleep(2000);			
		return this;
	}

	//Editable Zip code	
	public MemberFormPage verifyZipIsEditable(String zipCode ) throws InterruptedException {
		verifyIsEnabled((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']")),"Zip Code");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']")),zipCode,"Zip Code");
		Thread.sleep(2000);			
		return this;
	}

	// Editable Override Name
	public MemberFormPage verifyOverrideNameIsEditable( String overrideName) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='address1_name.fieldControl-text-box-text']")),"Override Name");
		click(getDriver().findElement(By.xpath("//input[@data-id='address1_name.fieldControl-text-box-text']")),"Override Name");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='address1_name.fieldControl-text-box-text']")),overrideName,"Override Name");
		Thread.sleep(2000);				
		return this;
	}

	// Editable Other Phone
	public MemberFormPage verifyOtherPhoneIsEditable(String otherPhone ) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='telephone2.fieldControl-phone-text-input']")),"Other Phone");
		click(getDriver().findElement(By.xpath("//input[@data-id='telephone2.fieldControl-phone-text-input']")),"Other Phone");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='telephone2.fieldControl-phone-text-input']")),otherPhone,"Other Phone");
		Thread.sleep(2000);
		return this;
	}
	public MemberFormPage verifyFaxIsEditable(String fax ) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='fax.fieldControl-text-box-text']")),"Fax");
		click(getDriver().findElement(By.xpath("//input[@data-id='fax.fieldControl-text-box-text']")),"Fax");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='fax.fieldControl-text-box-text']")),fax,"Fax");
		Thread.sleep(2000);		
		return this;

	}

	// Editable Main Phone
	public MemberFormPage verifyMainPhoneIsEditable(String mainPhone ) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='telephone1.fieldControl-phone-text-input']")),"Main Phone");
		click(getDriver().findElement(By.xpath("//input[@data-id='telephone1.fieldControl-phone-text-input']")),"Main Phone");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='telephone1.fieldControl-phone-text-input']")),mainPhone,"Main Phone");
		Thread.sleep(2000);		
		return this;
	}

	// Editable Website
	public MemberFormPage verifyWebsiteIsEditable(String website ) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='websiteurl.fieldControl-url-text-input']")),"Website");
		click(getDriver().findElement(By.xpath("//input[@data-id='websiteurl.fieldControl-url-text-input']")),"Website");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='websiteurl.fieldControl-url-text-input']")),website,"Website");
		Thread.sleep(2000);		
		return this;
	}

	// Editable Receive Direct Mail
	public MemberFormPage verifyReceiveDirectMailIsEditable(String receiveDirectMail ) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//select[@data-id='ix_receivedirectmail.fieldControl-checkbox-select']")),"Receive Direct Mail");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//select[@data-id='ix_receivedirectmail.fieldControl-checkbox-select']")),"Receive Direct Mail");
		Thread.sleep(5000);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_receivedirectmail.fieldControl-checkbox-select']")),receiveDirectMail,"Receive Direct Mail");
		Thread.sleep(2000);
		return this;
	}


	// Editable External Address ID
	public MemberFormPage verifyExternalAddressIDIsEditable(String externalAddressID ) throws InterruptedException {
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='ix_externaladdressid.fieldControl-text-box-text']")),"External Address ID");
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_externaladdressid.fieldControl-text-box-text']")),"External Address ID");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_externaladdressid.fieldControl-text-box-text']")),externalAddressID,"External Address ID");
		Thread.sleep(2000);
		return this;
	}

	//Editable #Estimated Locations phone- NY Information Tab
	public MemberFormPage verifyEstLocIsEditable(String numEstLoc ) throws InterruptedException {

		Thread.sleep(2000);
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='ix_ofestimatedlocations.fieldControl-whole-number-text-input']")),"# of Estimated Locations");
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_ofestimatedlocations.fieldControl-whole-number-text-input']")),"# of Estimated Locations");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_ofestimatedlocations.fieldControl-whole-number-text-input']")),numEstLoc,"# of Estimated Locations");
		return this;
	}

	// Editable NAICS -NY Information Tab
	public MemberFormPage verifyNAICSIsEditable(String nAICS,String industryTitle) throws InterruptedException {
		navigatetoNAICS();
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='ix_naicscode.fieldControl-LookupResultsDropdown_ix_naicscode_textInputBox_with_filter_new']")),"NAICS");
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_naicscode.fieldControl-LookupResultsDropdown_ix_naicscode_textInputBox_with_filter_new']")),"NAICS");
		Thread.sleep(3000);
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_naicscode.fieldControl-LookupResultsDropdown_ix_naicscode_textInputBox_with_filter_new']")),nAICS,"NAICS");
		Thread.sleep(3000);
		//click(getDriver().findElement(By.xpath("//span[@data-id='ix_naicscode.fieldControl-Lookup_ix_naicscode_microsoftIcon_searchButton']")),"NAICS");
		Thread.sleep(5000);	
		click(getDriver().findElement(By.xpath("//span[@data-id='ix_naicscode.fieldControl-ix_industrytitle1_0_0']")),"NAICS Code in menu");
		Thread.sleep(5000);
		clickAndArrowDown(getDriver().findElement(By.xpath("//input[@data-id='NAICSCode.ix_industrytitle.fieldControl-text-box-text']")), "Arrow Down");
		click(getDriver().findElement(By.xpath("//input[@data-id='NAICSCode.ix_industrytitle.fieldControl-text-box-text']")),"Industry Title");
		Thread.sleep(3000);
		verifyExactValue(getDriver().findElement(By.xpath("//input[@data-id='NAICSCode.ix_industrytitle.fieldControl-text-box-text']")),industryTitle,"industryTitle");
		return this;
	}

	//Save And Close
	public MemberFormPage clickNyTabSave() throws InterruptedException {
		Thread.sleep(2000);	
		click(getDriver().findElement(By.xpath("//span[text()='Save']")),"NY Info Tab Save");
		Thread.sleep(5000);
		try {
			if(getDriver().findElement(By.xpath("//*[text()='Ignore and save']")).isDisplayed());
			click(getDriver().findElement(By.xpath("//*[text()='Ignore and save']")),"Ignore and Save");	
		} catch (Exception e) {
			e.getMessage();
		}
		Thread.sleep(3000);	
		return this;

	}

	//Remove Primary Contact on Member Form
	public MemberFormPage removeMemberPrimaryContact() throws InterruptedException {
		Thread.sleep(5000);
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_SelectedRecordList']"))).perform();
		click(getDriver().findElement(By.xpath("//button[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_selected_tag_delete']")),"Clear Icon on Existing Primary Contact"); 
		Thread.sleep(3000);
		return this;
	}

	//Read Only Street1
	public MemberFormPage verifyStreet1IsNotEditable(String street1) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text'][@readonly]")),"Street1");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text'][@readonly]")),street1,"Street1");
		return this;
	}

	//remove NAICS
	public MemberFormPage removeNAICS() throws InterruptedException {
		Thread.sleep(2000);
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//ul[@data-id='ix_naicscode.fieldControl-LookupResultsDropdown_ix_naicscode_SelectedRecordList']"))).perform();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//button[@data-id ='ix_naicscode.fieldControl-LookupResultsDropdown_ix_naicscode_selected_tag_delete']")), "Clear X icon on Existing NAICS");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save']")),"NY Info Tab Save");
		try {
			List<WebElement> ignoreMessage=getDriver().findElements(By.xpath("//button[text()='Ignore and save']"));
			if(ignoreMessage.size()>0)
				click(getDriver().findElement(By.xpath("//button[text()='Ignore and save']")),"Ignore and Save");	
		} catch (Exception e) {
			e.getMessage();
		}
		Thread.sleep(5000);
		return this;
	}



	//remove Referred By
	public MemberFormPage removeReferredBy() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Power Account')]")),"Power Account");
		Thread.sleep(2000);
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='ix_referredby.fieldControl-LookupResultsDropdown_ix_referredby_selected_tag']"))).perform();
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_referredby.fieldControl-LookupResultsDropdown_ix_referredby_selected_tag_delete']")), "Clear X icon on Existing Referred By");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save']")),"NY Info Tab Save");
		Thread.sleep(5000);
		return this;
	}

	// Editable Referred By -NY Information Tab
	public MemberFormPage verifyReferredByIsEditable(String referredBy ) throws InterruptedException {

		naivagateToReferredByFromNACS();
		verifyIsEnabled(getDriver().findElement(By.xpath("//input[@data-id='ix_referredby.fieldControl-LookupResultsDropdown_ix_referredby_textInputBox_with_filter_new']")),"Referred By");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_referredby.fieldControl-LookupResultsDropdown_ix_referredby_textInputBox_with_filter_new']")),"Referred By");
		Thread.sleep(3000);
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_referredby.fieldControl-LookupResultsDropdown_ix_referredby_textInputBox_with_filter_new']")), referredBy,"Referred By");
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@data-id,'ix_referredby.fieldControl-accountnumber')]")));

		click(getDriver().findElement(By.xpath("//span[contains(@data-id,'ix_referredby.fieldControl-accountnumber')]")),"Referred By Account");
		Thread.sleep(2000);	
		return this;
	}
	//save and close from NY Information Tab
	public MemberFormPage clickNyTabSaveAndClose() throws InterruptedException {
		Thread.sleep(2000);	
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"NY Info Tab Save and Close");
		Thread.sleep(4000);
		return this;

	}

	//Read Only Street2
	public MemberFormPage verifyStreet2IsNotEditable(String street2) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text'][@readonly]")),"Street2");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text'][@readonly]")),street2,"Street2");
		return this;
	}

	//Read Only Delivery Info
	public MemberFormPage verifyDeliveryInfoIsNotEditable(String deliveryInfo) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='address1_line3.fieldControl-text-box-text'][@readonly]")),"Delivery Info");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='address1_line3.fieldControl-text-box-text'][@readonly]")),deliveryInfo,"Delivery Info");
		return this;
	}	

	//Read Only City
	public MemberFormPage verifyCityIsNotEditable(String city) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text'][@readonly]")),"City");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text'][@readonly]")),city,"City");
		return this;
	}	

	//Read Only County
	public MemberFormPage verifyCountyIsNotEditable(String county) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text'][@readonly]")),"County");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text'][@readonly]")),county,"County");
		return this;
	}

	//Read Only State
	public MemberFormPage verifyStateIsNotEditable(String state) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text'][@readonly]")),"State");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text'][@readonly]")),state,"State");
		return this;
	}

	//Read Only ZipCode
	public MemberFormPage verifyZipCodeIsNotEditable(String zipcode) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text'][@readonly]")),"Zip Code");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text'][@readonly]")),zipcode,"Zip Code");
		return this;
	}

	//Read Only Country
	public MemberFormPage verifyCountryIsNotEditable(String country) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text'][@readonly]")),"Country");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text'][@readonly]")),country,"Country");
		return this;
	}


	//Read Only Override Name
	public MemberFormPage verifyOverrideNameIsNotEditable(String overrideName) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='address1_name.fieldControl-text-box-text'][@readonly]")),"Override Name");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='address1_name.fieldControl-text-box-text'][@readonly]")),overrideName,"Override Name");
		return this;
	}


	//Read Only Main Phone
	public MemberFormPage verifyMainPhoneIsNotEditable(String mainPhone) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='telephone1.fieldControl-phone-text-input'][@readonly]")),"Main Phone");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='telephone1.fieldControl-phone-text-input'][@readonly]")),mainPhone,"Main Phone");
		return this;
	}

	//Read Only Other Phone
	public MemberFormPage verifyOtherPhoneIsNotEditable(String otherPhone) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='telephone2.fieldControl-phone-text-input'][@readonly]")),"Other Phone");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='telephone2.fieldControl-phone-text-input'][@readonly]")),otherPhone,"Other Phone");
		return this;
	}

	//Read Only Fax
	public MemberFormPage verifyFaxIsNotEditable(String fax) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='fax.fieldControl-text-box-text'][@readonly]")),"Fax");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='fax.fieldControl-text-box-text'][@readonly]")),fax,"Fax");
		return this;
	}

	//Read Only Website
	public MemberFormPage verifyWebsiteIsNotEditable(String website) throws InterruptedException {
		Thread.sleep(2000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='websiteurl.fieldControl-url-text-input'][@readonly]")),"Website");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='websiteurl.fieldControl-url-text-input'][@readonly]")),website,"Website");
		return this;
	}

	//Read Only Receive Direct Mail
	public MemberFormPage verifyReceiveDirectMailIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_receivedirectmail.fieldControl-checkbox-select']")),"Receive Direct Mail");
		return this;
	}

	//Read Only Do Not Verify address
	public MemberFormPage verifyDoNotVerifyAddressIsNotEditable() throws InterruptedException {
		Thread.sleep(2000);
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_donotverifyaddress.fieldControl-checkbox-empty']")),"Do Not Verify Address");
		return this;
	}

	//Read Only External Address ID
	public MemberFormPage verifyExternalAddressIDIsNotEditable(String externalAddressID) throws InterruptedException {
		Thread.sleep(3000);
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='ix_externaladdressid.fieldControl-text-box-text'][@readonly]")),"External Address ID");
		typeLocked(getDriver().findElement(By.xpath("//*[@data-id='ix_externaladdressid.fieldControl-text-box-text'][@readonly]")),externalAddressID,"External Address ID");
		return this;
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//Select Accounts Tab from Member Form
	public AccountsPage selectAccountsTabFromMemberFormPage() throws InterruptedException {	
		click(getDriver().findElement(By.xpath("//span[text()='Accounts']")),"Accounts");
		Thread.sleep(2000);
		return new AccountsPage();

	}



	//get the Account Name
	public MemberFormPage getAccountName() {

		AccountName=getDriver().findElement(By.xpath("//h1[@data-id='header_title']")).getAttribute("title");
		return this;
	}

	public MemberFormPage verifyAccountNameinAccountNumberPage() {
		verifyExactText(getDriver().findElement(By.xpath("//div[@data-id='ix_account.fieldControl-LookupResultsDropdown_ix_account_selected_tag_text']")), AccountName, "Account Name");
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//select[@aria-label='Account Number Type']")),"Supplier","Account Number Type");

		return this;
	}



	//Verify the Accountnumber type is present in the dropdown

	public MemberFormPage verifyAccountnumberTypedropdown(String accountNumberType){
		Select accountnumber= new  Select(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")));
		ArrayList<String> selectoptions=new ArrayList<String>();
		List<WebElement> statuList =accountnumber.getOptions();	

		for(int i=0;i<statuList.size();i++) {

			selectoptions.add(statuList.get(i).getText());

		}
		assertTrue((selectoptions.contains(accountNumberType)), "Account number type ");

		return this;
	}
	
	//Select Account type as DUNS
		public MemberFormPage chooseAccountNumberTypeDUNS() {
			try {
				Thread.sleep(2000);
				click(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Account Number Type -drop down");
				click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'DUNS')]")),"Account Number Type");
				Thread.sleep(2000);
				verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"DUNS","Account Numbers Type"); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return this;
		}

	//Select Account number type in account numbers window
	public MemberFormPage chooseAccountNumberTypeHIN() {
		try {
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Account Number Type -drop down");
			click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'HIN')]")),"Account Number Type");
			Thread.sleep(2000);
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"HIN","Account Numbers Type"); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	//Select Account number type in account numbers window
	public MemberFormPage chooseAccountNumberTypeRemitra() {
		try {
			Thread.sleep(2000);
			selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Remitra","Account Number Type");
			Thread.sleep(2000);
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Remitra","Account Numbers Type"); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	//Select Account number type in account numbers window
	public MemberFormPage chooseAccountNumberType(String  AccountnumberType) {
		try {
			Thread.sleep(2000);
			selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),AccountnumberType,"Account Number Type");
			Thread.sleep(2000);
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),AccountnumberType,"Account Numbers Type"); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	//Select Account number type in account numbers window
	public MemberFormPage chooseAccountNumberTypeGLN() {
		try {
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Account Number Type -drop down");
			click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'GLN')]")),"Account Number Type");
			
			//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"GLN","Account Number Type");
			Thread.sleep(2000);
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"GLN","Account Numbers Type"); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}


	//Type Hin account number
	public MemberFormPage typeAccountNumberHIN() {
		int min=111111111;
		int max=999999999;
		//Random randomGenerator = new Random();
		int randomInt = (int)(Math.random() * (max - min + 1) + min);
		System.out.println(randomInt);
		String AccNumHIN=String.valueOf(randomInt);
		randomString=AccNumHIN;
		AccNumNPI=AccNumHIN;
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),AccNumHIN,"HIN Account Number");
		try {
			DataInputProvider.setCellData(AccNumHIN.toString(), Driver.iTestCaseRowNum, "HIN",Driver.sCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	//Type Remitra account number
	public MemberFormPage typeAccountNumberRemitra() {
		int min=111111111;
		int max=999999999;
		//Random randomGenerator = new Random();
		int randomInt = (int)(Math.random() * (max - min + 1) + min);
		System.out.println(randomInt);
		String AccNumRemitra=String.valueOf(randomInt);
		randomString=AccNumRemitra;

		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),AccNumRemitra,"Remitra Account Number");
		try {
			DataInputProvider.setCellData(AccNumRemitra.toString(), Driver.iTestCaseRowNum, "Remitra",Driver.sCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	//Type Remitra account number
	public MemberFormPage typeAccountNumberDUNS() {
		int min=111111111;
		int max=999999999;
		//Random randomGenerator = new Random();
		int randomInt = (int)(Math.random() * (max - min + 1) + min);
		System.out.println(randomInt);
		String AccNumRemitra=String.valueOf(randomInt);
		randomString=AccNumRemitra;

		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),AccNumRemitra,"Remitra Account Number");
		try {
			DataInputProvider.setCellData(AccNumRemitra.toString(), Driver.iTestCaseRowNum, "Remitra",Driver.sCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	//Type GLN account number
	public MemberFormPage typeAccountNumberGLN(String gln) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),gln,"GLN Account Number");
		return this;
	}


	public MemberFormPage clickSaveAndClosInAccountNumbers() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumber|NoRelationship|Form|Mscrm.Form.ix_accountnumber.SaveAndClose']")),"Save and Close");
		Thread.sleep(5000);
		//	click(getDriver().findElement(By.xpath("//*[@title='GENERAL']")),"GENERAL");
		//Thread.sleep(5000);
		return this;	
	}	

	//Select Account type as DEA
	public MemberFormPage chooseAccountNumberTypeDEA() {
		try {
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Account Number Type -drop down");
			click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'DEA')]")),"Account Number Type");
			Thread.sleep(2000);
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"DEA","Account Numbers Type");
			
			clearAllSuggestions();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}



	//Select Account type as NPI
	public MemberFormPage chooseAccountNumberTypeNPI() {
		try {
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Account Number Type -drop down");
			click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'NPI')]")),"Account Number Type");
			
			//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"NPI","Account Number Type");
			Thread.sleep(2000);
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"NPI","Account Numbers Type"); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	//Select Account type as NCPDP
	public MemberFormPage chooseAccountNumberTypeNCPDP() {
		try {
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Account Number Type -drop down");
			click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'NCPDP')]")),"Account Number Type");
			
			//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"NCPDP","Account Number Type");
			Thread.sleep(2000);
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"NCPDP","Account Numbers Type"); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}
	public MemberFormPage doubleClickMembership(String membership) throws InterruptedException {	
		Thread.sleep(3000);
		for(int i=1;i<=getDriver().findElements(By.xpath("//*[@col-id='ix_membershipprovider']//span")).size();i++) {
			if(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)["+i+"]")).getText().equalsIgnoreCase(membership)) {
				System.out.println(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)["+i+"]/ancestor::div[@col-id='ix_membershipprovider']/following-sibling::div[@col-id='ix_enddate']//label")).getAttribute("aria-label"));
				if(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)["+i+"]/ancestor::div[@col-id='ix_membershipprovider']/following-sibling::div[@col-id='ix_enddate']//label")).getAttribute("aria-label").isEmpty())
				{
					Actions a = new Actions(getDriver());
					int j=i+1;
					a.moveToElement(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershiptype']//label)["+(j)+"]"))).doubleClick().build().perform();
					break;
				}


			}
		}

		// a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='cell-0-2']"))).doubleClick().build().perform();
		Thread.sleep(3000);
		return this;
	}

	public MemberFormPage doubleClickMembershipWithEndDAte(String membership) throws InterruptedException {	

		for(int i=1;i<=getDriver().findElements(By.xpath("//*[@col-id='ix_membershipprovider']//span")).size();i++) {
			if(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)["+i+"]")).getText().equalsIgnoreCase(membership)) {
				System.out.println(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)["+i+"]/ancestor::div[@col-id='ix_membershipprovider']/following-sibling::div[@col-id='ix_enddate']//label")).getAttribute("aria-label"));
				if(!(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)["+i+"]/ancestor::div[@col-id='ix_membershipprovider']/following-sibling::div[@col-id='ix_enddate']//label")).getAttribute("aria-label").isEmpty()))
				{
					Actions a = new Actions(getDriver());
					int j=i+1;
					a.moveToElement(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershiptype']//label)["+(j)+"]"))).doubleClick().build().perform();
				}


			}
		}

		// a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='cell-0-2']"))).doubleClick().build().perform();
		Thread.sleep(3000);
		return this;
	}


	public MemberFormPage chooseTodayAsApplicationDate() throws InterruptedException {
		Thread.sleep(15000);
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String todaydate= dateFormat.format(date);
		WebElement element = getDriver().findElement(By.xpath("//label[contains(text(),'Account Status')]"));
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		je.executeScript("arguments[0].scrollIntoView();",element);

		click(getDriver().findElement(By.xpath("//label[contains(text(),'Account Status')]")),"Account Status");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Application Date')]")),"Application Date");
		Thread.sleep(5000);
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_applicationstartdate.fieldControl-date-time-input']")))),todaydate, "Application Start Date");
		return this;

	}




	//Enter End Date as past date from the given Date in Account Numbers
	public MemberFormPage getPastDate(String givenDate, int number) throws ParseException {
		SimpleDateFormat dateform=new SimpleDateFormat("M/d/yyyy");
		Date date = new SimpleDateFormat("M/d/yyyy").parse(givenDate);

		Calendar c = Calendar.getInstance();    
		c.setTime(date);        
		// manipulate date        
		c.add(Calendar.DATE, -(number+1)); 
		// convert calendar to date      
		Date currentDatePlusOne = c.getTime();

		TestUtils.enddate= dateform.format(currentDatePlusOne);			

		return this;
	}

	//Enter End Date as past date from the given Date in Account Numbers
	public MemberFormPage getFutureDate(String givenDate, int number) throws ParseException {
		SimpleDateFormat dateform=new SimpleDateFormat("M/d/yyyy");
		Date date = new SimpleDateFormat("M/d/yyyy").parse(givenDate);

		Calendar c = Calendar.getInstance();    
		c.setTime(date);        
		// manipulate date        
		c.add(Calendar.DATE, (number+1)); 
		// convert calendar to date      
		Date currentDatePlusOne = c.getTime();

		TestUtils.enddate= dateform.format(currentDatePlusOne);			

		return this;
	}

	public MemberFormPage getThePremierStartDate() {

		//*[@data-id='ix_startdate.fieldControl-date-time-input']
		if(getDriver().findElements(By.xpath("//*[@data-id='ix_startdate.fieldControl-date-time-input']")).size()>0) {
			TestUtils.date=getDriver().findElement(By.xpath("//*[@data-id='ix_startdate.fieldControl-date-time-input']")).getAttribute("Value");	
		}else {
			TestUtils.date=getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date']")).getAttribute("Value");
		}
		return this;
	}

	public MemberFormPage getDprd() {
		TestUtils.date=getDriver().findElement(By.xpath("//input[@data-id='ix_directparentrelationdate.fieldControl-date-time-input']")).getAttribute("Value");
		return this;
	}

	public MemberFormPage gettprd() {
		TestUtils.date=getDriver().findElement(By.xpath("//input[@data-id='ix_topparentrelationdate.fieldControl-date-time-input']")).getAttribute("Value");
		return this;
	}
	//Click Save Button
	public MemberFormPage clickSaveAccountNumber() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//span[contains(text(),'Save')]")),"Click Save button");
		Thread.sleep(10000);

		//Thread.sleep(10000);
		return this;
	}


	public MemberFormPage typeAccountNumberNPI() {
		int min=111111111;
		int max=999999999;
		//Random randomGenerator = new Random();
		int randomInt = (int)(Math.random() * (max - min + 1) + min);
		System.out.println(randomInt);
		AccNumNPI=String.valueOf(randomInt);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),AccNumNPI,"NPI Account Number");
		try {
			DataInputProvider.setCellData(AccNumNPI.toString(), Driver.iTestCaseRowNum, "NPI",Driver.sCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	//Select the CRM
	public MemberFormPage selectCRM(int crm) throws InterruptedException {

		selectAccountsTab();
		new AccountsPage()
		.searchAccount(crmList.get(crm))
		.selectAccountFromSearchResults()
		;
		return this;
	}

	//Click save and Close button
	public MemberFormPage clickSaveAndCloseButton() {

		click(getDriver().findElement(By.xpath("//button[@aria-label='Save & Close']")),"Click Save and Close");
		return this;
	}

	//Verify Invalid DEA Number
	public MemberFormPage verifyInvalidDEANumberMessage() {

		getDriver().findElement(By.xpath("//*[@data-id='dialogTitleText']")).isDisplayed();
		List<WebElement> InvalidDEA_Message=getDriver().findElements(By.xpath("//h2[@aria-label='Invalid DEA Number - Check Digit Algorithm Failed!']"));
		verifyElementisDisplayed(InvalidDEA_Message.size(), "Invalid DEA Number Message");
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"Click Ok Button");
		return this;
	}

	//verify New Account Number button is displayed
	public MemberFormPage verifyAccountnumberButton() throws InterruptedException {
		Thread.sleep(5000);
		getDriver().findElement(By.xpath("//span[contains(text(),'New Account Number')]")).isDisplayed();
		return this;

	}

	//Verify newly added Account Number is displayed in the Account Number page
	public MemberFormPage verifyNewlyCreatedAccountNumber(String accountnumber) {
		//Wave1 2023 Update
		List<WebElement> accountNumber=getDriver().findElements(By.xpath("//div[@data-id='entity_control-powerapps_onegrid_control_container']//div[@class='ag-center-cols-container']/div[contains(@class,'ag-row-') and @role='row']//a/div"));
		for(int i=1;1<=accountNumber.size();i++) {
			accountnumber=getDriver().findElement(By.xpath("(//div[@data-id='entity_control-powerapps_onegrid_control_container']//div[@class='ag-center-cols-container']/div[contains(@class,'ag-row-')and @role='row'])["+i+"]//a/div")).getText();
			if(accountnumber.contains(randomString)) {
				break;}
		}
		if(accountnumber.equals(randomString)) {
			setReport().log(Status.PASS, "The text :"+accountnumber+" matches with the value "+  randomString + "  in  Account Number"+" field",screenshotCapture());

		}else {
			setReport().log(Status.FAIL, "The text :"+accountnumber+" did not match with the value "+randomString+" in"+" Account Number"+" field",screenshotCapture());
			Driver.failCount++;
		}

		return this;
	}

	//get the DEA Number
	public MemberFormPage getDEA() throws InterruptedException {
		DEA=randomString;
		return this;
	}

	//get the DEA Number
	public MemberFormPage getHIN() throws InterruptedException {
		HIN=randomString;
		return this;
	}

	//Verify DEA account number
	public MemberFormPage verifyDEAInMemberForm() throws InterruptedException {
		verifyExactValue((getDriver().findElement(By.xpath("//input[@aria-label='DEA']"))),DEA,"DEA");
		return this;
	}

	public MemberFormPage randomString() {
		// create a string of all characters
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		int length = 9;

		for(int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphabet.length());

			// get character specified by index
			// from the string
			char randomChar = alphabet.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		randomString = sb.toString();

		try {
			DataInputProvider.setCellData(randomString, Driver.iTestCaseRowNum, "HIN",Driver.sCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public MemberFormPage randomStringWithNumber() {
		// create a string of all characters
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678";

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		int length = 9;

		for(int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphabet.length());

			// get character specified by index
			// from the string
			char randomChar = alphabet.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		randomString = sb.toString();

		try {
			DataInputProvider.setCellData(randomString, Driver.iTestCaseRowNum, "HIN",Driver.sCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public MemberFormPage typeAccountNumberRandomString() {

		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),randomString,"Account Number");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Name')]")),"Name Label");

		return this;

	}
	//Verify HIN Account number
	public MemberFormPage verifyHINInMemberForm() {
		verifyExactValue((getDriver().findElement(By.xpath("//input[@aria-label='HIN']"))),HIN,"HIN");
		return this;
	}

	public MemberFormPage verifyExpirationDateisNotEmpty() {
		List<WebElement> DEAExpirationDatelabel=getDriver().findElements(By.xpath("//*[contains(text(),'Expiration Date')]"));
		verifyElementisDisplayed(DEAExpirationDatelabel.size(), "DEA Expiration Date");
		List<WebElement> DEAExpirationDate=getDriver().findElements(By.xpath("(//div[contains(@col-id,'ix_expirationdate')]//label)[2]"));
		verifyElementisDisplayed(DEAExpirationDate.size(), "DEA Expiration Date");
		getTextValue(getDriver().findElement(By.xpath("(//div[contains(@col-id,'ix_expirationdate')]//label)[2]")), "DEA Expiration Date");
		return this;

	}

	//Search DEA number
	public MemberFormPage searchDEANumber(String DEA) {
		type(getDriver().findElement(By.xpath("//input[@aria-label='Account Number Filter by keyword']")),DEA,"DEA Number");
		return this;
	}

	public MemberFormPage typeDAccountNumber(String DEANum) {

		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),DEANum,"Account Number");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Name')]")),"Name Label");

		return this;

	}

	//Verify CalculatedName in the Account number page
	public MemberFormPage verifyCaclculatedName( String accountNumberType) throws IOException {

		String accountname=getDriver().findElement(By.xpath("//div[@data-id='ix_account.fieldControl-LookupResultsDropdown_ix_account_selected_tag_text']")).getText();
		System.out.println();
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Calculated Name']")), accountname+" - "+accountNumberType+" - "+AccNumNPI, "Cacluclate Name");
		return this;
	}

	//Verify Invalid GLN Number
	public MemberFormPage verifyInvalidGLNNumberMessage() {

		getDriver().findElement(By.xpath("//*[@data-id='dialogTitleText']")).isDisplayed();
		List<WebElement> InvalidDEA_Message=getDriver().findElements(By.xpath("//h2[@aria-label='Invalid GLN Number - Length should be 13 chars!']"));
		verifyElementisDisplayed(InvalidDEA_Message.size(), "Invalid GLN Number Message");
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"Click Ok Button");
		return this;
	}

	public MemberFormPage verifyExpirationDate() throws InterruptedException {
		Thread.sleep(4000);
		List<WebElement> ExpirationDateLabel=getDriver().findElements(By.xpath("//label[contains(text(),'Expiration Date')]"));
		verifyElementisDisplayed(ExpirationDateLabel.size(), "Expiration Date");
		List<WebElement> ExpirationDate=getDriver().findElements(By.xpath("//input[@aria-label='Date of Expiration Date']"));
		verifyElementisDisplayed(ExpirationDate.size(), "Expiration Date");
		return this;
	}


	//Verify if "Member's or Non-GPO Member's CAMS Flag cannot be checked if it's
	// Parent's CAMS Flag is unchecked" error msg is displayed when a Child member
	// account has Yes whereas the Parent has CAMS as No
	public MemberFormPage verifyCAMSerrorMsg() throws InterruptedException {
		Thread.sleep(3000);
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']")),
				"Member's or Non-GPO Member's CAMS Flag cannot be checked if it's Parent's CAMS Flag is unchecked",
				"CAMS Flag error");
		return this;
	}


	// Verify if date validation error msg is displayed for Member Account
	public MemberFormPage verifyAppDateValidationErrorMsg(String appDate, String startdate) throws InterruptedException {
		Thread.sleep(3000);

		String dateValidationMsg = getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']")).getText();
		System.out.println("dateValidationMsg :\n" + dateValidationMsg);

		String ExpectedDateValidationMsg = "Date Restriction Error: Application Start Date (" + appDate
				+ ") must be <=  Premier Member Start Date (" + startdate + ")";
		System.out.println("ExpectedDateValidationMsg :\n" + ExpectedDateValidationMsg);

		if (dateValidationMsg.replaceAll("\\s", "").equalsIgnoreCase(ExpectedDateValidationMsg.replaceAll("\\s", ""))) {
			setReport().log(Status.PASS,
					"Error message displayed successfully" + "Date Restriction Error: Application Start Date ("
							+ appDate + ") must be <=  Premier Member Start Date (" + startdate + ")",
							screenshotCapture());
		} else {
			setReport().log(Status.FAIL, "Date restriction error message is not displayed as expected",
					screenshotCapture());
			Driver.failCount++;
		}
		verifyExactContent(dateValidationMsg.replaceAll("\\s", "").replaceAll("\\p{P}", ""),
				ExpectedDateValidationMsg.replaceAll("\\s", "").replaceAll("\\p{P}", ""), "Date Restriction Error Message");

		return this;
	}


	//Date validation error
	public MemberFormPage verifyDateValidationError(String errorText) {
		//Wave2 Update
		verifyPartialText((getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']"))), errorText,"Date Validation Error");
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"Ok");
		return this;
	}

	//
	public MemberFormPage clickLineOfBusinessesTab(String errorText) {
		click(getDriver().findElement(By.xpath("//*[@data-id='tablist-nav_ix_account_ix_portfoliocategory_Account']")),"Line of Businesses Tab");
		return this;
	}

	//Click new on accounts page
	public NewAccountPage clickNewOnAccountsSubAccountPage() throws InterruptedException { 
		click(getDriver().findElement(By.xpath("//button[@aria-label='New Account. Add New Account']")),"New");
		Thread.sleep(3000);
		return new NewAccountPage();
	}

	public MemberFormPage createSubAccountLocationType(int count) throws InterruptedException, AWTException {
		for(int i=1;i<=count;i++) {
			clickNewOnAccountsSubAccountPage();
			chooseMemberForm();

			//3. Account Name = Any
			typeAccountName("MemberAccount1");

			//Click on save 			
			//5. Account Type = Member
			selectAccountType("Member");
			//Class of Trade =Any
			selectClassOfTrade("Auditorium/Museum");


			//Application Start Date = Today's Date
			chooseApplicationDate("3/4/2021");

			chooseLocationType("Ship To");

			//CAMS Flag = Yes
			changeCAMSFlagAsYes();

			//Participation Type = Standard
			selectParticipationType("Pharmacy");


			//Direct Parent Relation = Managed
			selectDirectParentRelationManaged(); 

			//Direct Parent Relation date = Today's Date
			selectDirectParentRelationDate("3/4/2021");

			//Top Parent Relation =  OLM
			selectTopParentRelation("OLM");

			// Top Parent Relation Date = Today's Date
			selectTopParentRelationDate( "3/4/2021");

			//Click on Save 
			clickSave(); 

			verifyCRMNumberIsDisplayed();


			//6. Street 1 = Any
			typeStreet1("Street 1");

			//City = NY
			typeCity("NY");

			//Country =USA
			typeCountry("USA");

			//Type Zip code
			typeZipCode("3444");

			//Click on Save 
			clickSave() ;

			//7.  Click the + icon on the Line of Business Grid
			clickLineOfBusinesses();

			//Click New Line Of Business
			clickAddNewLineOfBusiness();

			// Line of Business =General GPO
			selectLineOfBusiness("General GPO");

			// Classification Type = General GPO
			selectLOBfClassificationType("General GPO");

			// Start Date =Today's date
			typeLineOfBusinessStartDate("3/4/2021");

			// Click on LOB Save 
			clickLOBSaveAndClose();

			//Click add new membership
			clickMembershipAndAddNewMembership();

			// Choose Membership type 
			selectMembershipType("Premier");
			selectMembershipProvider("National");

			//Provide any start date and click on save
			typeMembershipStartDate("3/4/2021");

			//Click on membership save and close
			clickQuickCreateMembershipSaveAndClose();


			//Click on Save 
			clickSave() ;


		}
		return this;
	}

	public MemberFormPage navigatetoExternalAddress() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Receive Direct Mail')]")),"Receive Direct Mail");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'FSRPT Flag')]")),"FSRPT Flag");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Do No')]")),"Do Not Verify");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'External Address')]")),"External Address");

		return this;
	}

	public MemberFormPage navigatetNYInformation() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Ownership')]")),"Ownership");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Stock Symbol')]")),"Stock Symbol");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Exchange')]")),"Exchange");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Is Payment Entity')]")),"Payment Entity");
		click(getDriver().findElement(By.xpath("//h2[@title='NY INFORMATION']")),"NY Information");

		return this;
	}

	public MemberFormPage navigatetoNAICS() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Commissioned Representative')]")),"Commissioned Representative");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'CSA Commissioned Representative')]")),"CSA ");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Annual Revenue')]")),"Annual Revenue");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'No. of Employees')]")),"NO Of Employees");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Safe Harbor Instructions')]")),"Safe Harbor Instructions");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'NAICS Code')]")),"NAICS Code");

		return this;
	}

	//navigateto country
	public MemberFormPage navigateToCountry() {
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'County')]")),"County");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'State')]")),"State");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'ZIP')]")),"Zip");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Country')]")),"Country");
		return this;

	}

	public MemberFormPage navigateToDP() throws InterruptedException {

		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		Thread.sleep(2000);
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		return this;

	}


	public MemberFormPage navigateToPrmiaryAccount() {

		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		return this;

	}


	public MemberFormPage naivagateToReferredByFromNACS() {

		click(getDriver().findElement(By.xpath("//label[contains(text(),'Communication Instructions')]")),"Communication Instruction");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'vendor')]")),"Vendor");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'wholesaler')]")),"Wholesaler");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Follow')]")),"Follow");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Originating')]")),"Originating");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Referred')]")),"Referred By");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Power Account')]")),"Power Account");

		return this;
	}

	//Select Power account
	public MemberFormPage selectPowerAccount(String option) {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_poweraccount.fieldControl-checkbox-select']")), option, "PowerAccount");
		return this;
	}


	//Verify LOB and Account number are displayed under the account number widget
	public MemberFormPage verifyWidgetunderMembership() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='MEMBERSHIP']/following-sibling::section[@aria-label='LINE OF BUSINESS']")).size(), "LOB Widget");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='MEMBERSHIP']/following-sibling::section[@aria-label='ACCOUNT NUMBERS']")).size(), "Account Nuymber Widget");
		return this;

	}

	//Verify LOB and Account number are displayed under the account number widget
	public MemberFormPage verifyMemberAttributeUnderFBO() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='FBO Information']/following-sibling::section[@aria-label='MEMBER ATTRIBUTES']")).size(), "Member Attributes");
		return this;

	}

	//Verify LOB and Account number are displayed under the account number widget
	public MemberFormPage verifyFeildsunderAdditionalCriteria() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_applicationstartdate']")).size(), "Applicatio start date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_premiermemberstartdate']")).size(), "Premieri start date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_premiermemberenddate']")).size(), "Premieri end date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_requiremanualagassignment']")).size(), "Manual assignment");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_affiliategroup']")).size(), "Affiliate Group");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_affiliategroupeffectivedate']")).size(), "Affiliate Group effective date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_premierowner']")).size(), "Premieri Owner");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_corporate']")).size(), "Corporate");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_camsflag']")).size(), "CAMS");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_donotroster']")).size(), "Do not roaster");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_receivedirectmail']")).size(), "Received email");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_feeshareeligible']")).size(), "Fee share eligible");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_feeshareeligibledate']")).size(), "Fee share eligible date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_businesskey']")).size(), "Business key");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_bkactive']")).size(), "bk active");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_paymententityid']")).size(), "Payment Entity");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_recordstatus']")).size(), "Record status");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_recordchangestatus']")).size(), "Record change status");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_currentinternalrep']")).size(), "Current internal rep");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_currentfieldrep']")).size(), "Current feidl rep");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']//div[@data-id='ix_accountrank']")).size(), "Account Rank");

		return this;

	}

	//Verify LOB and Account number are displayed under the account number widget
	public MemberFormPage verifyNYInformationFeilds() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'# of Member')]")).size(), "# of member");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'# of Estimated')]")).size(), "# of Estimated");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'Commissioned Representative')]")).size(), "Commissioned Representative");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'CSA Commissioned Representative')]")).size(), "CSA Commissioned Representative");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'Annual Revenue')]")).size(), "Annual Revenue");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'No. of Employees')]")).size(), "No of Employees");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'Safe Harbor Instructions')]")).size(), "Safe Harbor Infromation");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'NAICS Code')]")).size(), "NAICS Code");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'Communication Instruction')]")).size(), "Communication instruction");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'include vendor data before')]")).size(), "Do not include vendor data before");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'include wholesaler data before')]")).size(), "Do not include whole sales vendor data before");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'Follow Email Activity')]")).size(), "Follow Email Activity");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'Originating Lead')]")).size(), "Originating Lead");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'Referred By')]")).size(), "Referred By");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'Power Account')]")).size(), "Power Account");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='NY INFORMATION']//label[contains(text(),'Manage Eligibility')]")).size(), "Manage Eligibilty");

		return this;

	}

	//Verify LOB and Account number are displayed under the account number widget
	public MemberFormPage navigatetoNYInformationFromAdditionalCriteria() {
		clickAndEsc(getDriver().findElement(By.xpath("//input[@aria-label='Date of Application Date']")), "Application start date");
		clickAndEsc(getDriver().findElement(By.xpath("//button[@aria-label='Require Manual AG Assignment']")), "Manual assignment");
		clickAndEsc(getDriver().findElement(By.xpath("//input[@aria-label='Date of Affiliate Group Effective Date']")), "Affiliate Group");
		clickAndEsc(getDriver().findElement(By.xpath("//button[@aria-label='Corporate']")), "Affiliate Group effective date");
		clickAndEsc(getDriver().findElement(By.xpath("//button[@aria-label='Exclude from Roster']")), "Premieri Owner");
		clickAndEsc(getDriver().findElement(By.xpath("//button[@aria-label='Receive Direct Mail']")), "Corporate");
		clickAndEsc(getDriver().findElement(By.xpath("//input[@aria-label='Business Key']")), "CAMS");
		clickAndEsc(getDriver().findElement(By.xpath("//input[@data-id='ix_paymententityid.fieldControl-LookupResultsDropdown_ix_paymententityid_textInputBox_with_filter_new']")), "Do not roaster");

		clickAndEsc(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")), "Received email");
		clickAndEsc(getDriver().findElement(By.xpath("//button[@aria-label='Record Change Status']")), "Received email");
		clickAndEsc(getDriver().findElement(By.xpath("//button[@data-id='ix_issponsor.fieldControl-checkbox-select']")), "Is sponsor");
		clickAndEsc(getDriver().findElement(By.xpath("//input[@aria-label='Date of Corporate Rebate Fee Date']")), "Corporate Rebate Fee");
		clickAndEsc(getDriver().findElement(By.xpath("//button[@aria-label='Food Service Parent Override']")), "Corporate Rebate Fee");
		clickAndEsc(getDriver().findElement(By.xpath("//button[@aria-label='FSRPT Flag']")), "Corporate Rebate Fee");

		return this;

	}


	public MemberFormPage navigatefromCorporateParentToFoodServiceMEF() {

		clickAndEsc(getDriver().findElement(By.xpath("//select[@data-id='ix_corporateparentoverride.fieldControl-checkbox-select']")), "Overrire");
		clickAndEsc(getDriver().findElement(By.xpath("//input[@data-id='ix_corporaterebatefeedate.fieldControl-date-time-input']")), "feedate");
		return this;

	}

	public MemberFormPage verifyNYInformationUnderAdditionaCriteria() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='Additional Criteria']/following-sibling::section[@aria-label='NY INFOMATION']")).size(), "NY Information");
		return this;
	}

	public MemberFormPage verifyLOBTab() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//li[@aria-label='Rebate Payments']/following-sibling::li[@title='LOB']")).size(), "LOB Tab");
		click(getDriver().findElement(By.xpath("//li[@aria-label='Rebate Payments']/following-sibling::li[@title='LOB']")), "LOB Tab");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[contains(@id,'View') and contains(text(),'Active Line of Business')]")).size(), "Active Line of Business view");
		return this;
	}

	public MemberFormPage verifyAccountRepresentative() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//li[@aria-label='Documents']/following-sibling::li[@title='Account Representatives']")).size(), "Account Representatives");
		click(getDriver().findElement(By.xpath("//li[@aria-label='Documents']/following-sibling::li[@title='Account Representatives']")), "Account Representatives");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[contains(@id,'View') and contains(text(),'Current Account Representatives')]")).size(), "Current Account Representative view");
		return this;
	}

	public MemberFormPage verifyContactAccountAssociation() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//li[@aria-label='LOB']/following-sibling::li[@title='Contact Account Associations']")).size(), "Account Representatives");
		click(getDriver().findElement(By.xpath("//li[@aria-label='LOB']/following-sibling::li[@title='Contact Account Associations']")), "Account Representatives");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[contains(@id,'View') and contains(text(),'Active Contact Account Associations')]")).size(), "Current Account Representative view");
		Thread.sleep(7000);
		Thread.sleep(7000);
		click(getDriver().findElement(By.xpath("//button[@data-lp-id='SubGridStandard:ix_contactaccountassociation-ix_contactaccountassociation|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_contactaccountassociation.RefreshButton']")), "Refresh button");
		Thread.sleep(7000);
		return this;


	}



	public MemberFormPage verifyRebatePayments() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//li[@aria-label='Account Representatives']/following-sibling::li[@title='Rebate Payments']")).size(), "Account Representatives");
		click(getDriver().findElement(By.xpath("//li[@aria-label='Account Representatives']/following-sibling::li[@title='Rebate Payments']")), "Account Representatives");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[contains(@id,'View') and contains(text(),'Current Rebate Payments')]")).size(), "Current Account Representative view");
		return this;
	}

	public MemberFormPage checkDP_TPRD(String premierStartDate) {
		navigateToApplicationDate();
		String dprd=getDriver().findElement(By.xpath("//input[@data-id='ix_directparentrelationdate.fieldControl-date-time-input']")).getAttribute("Value");
		String tprd=getDriver().findElement(By.xpath("//input[@data-id='ix_topparentrelationdate.fieldControl-date-time-input']")).getAttribute("Value");
		Assert.assertTrue(dprd.contentEquals(premierStartDate));
		Assert.assertTrue(tprd.contentEquals(premierStartDate));
		return this;
	}

	public MemberFormPage CompareDPRDTPRD() throws InterruptedException {
		navigateToDP();
		getDprd();
		String dprd=TestUtils.date;
		navigateToApplicationDate();
		gettprd();
		String tprd=TestUtils.date;


		if(dprd.compareTo(tprd)>0){          
			TestUtils.enddate= tprd;

		}else {      
			TestUtils.enddate= dprd;   
		}

		return this;
	}


	public MemberFormPage CompareStartDateandEndDate() {
		getThePremierStartDate();
		String premierstartdate=TestUtils.date;

		getThePremierEndDate();
		String premierEndDate=TestUtils.date;


		System.out.println(premierstartdate.compareTo(premierEndDate));
		Assert.assertTrue(premierstartdate.compareTo(premierEndDate)>=0);
		return this;
	}

	public MemberFormPage CompareStartDateandDPRD() throws InterruptedException {
		navigateToDP();
		getDprd();
		String dprd=TestUtils.date;

		getThePremierEndDate();
		String premierEndDate=TestUtils.date;


		System.out.println(dprd.compareTo(premierEndDate));
		Assert.assertTrue(dprd.compareTo(premierEndDate)>=0);
		return this;
	}
	//Type Membership Start Date
	public MemberFormPage getMembershipStartDate() {
		if(getDriver().findElements(By.xpath("//input[@aria-label='Date of Start Date']")).size()>0) {
			TestUtils.date=getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date']")),"Membership Startdate");
		}else {
			TestUtils.date=getAttribute(getDriver().findElement(By.xpath("//div[@col-id='ix_startdate']//label[contains(@class,'ms-Label labelRoot')]")), "aria-label", "Membership start date");

		}
		return this;
	}
	
	//Type Membership Start Date
	public MemberFormPage getMembershipStartDateForEnddate() {
		TestUtils.date=getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date']")).getAttribute("value");
		return this;
	}


	//div[@col-id='ix_startdate' and @role='gridcell']//label

	public MemberFormPage getThePremierEndDate() {
		TestUtils.date=getDriver().findElement(By.xpath("//input[@aria-label='Date of End Date']")).getAttribute("Value");
		return this;
	}

	//Get LOB Start Date
	public MemberFormPage getLOBStartDate() {
		TestUtils.date=getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date']")),"Membership Startdate");
		return this;
	}

	//Type LOB end date
	public MemberFormPage typeLOBEndDate(String EndDate) {
		type(((getDriver().findElement(By.xpath("//input[@aria-label='Date of End Date']")))),EndDate,"End Date");
		return this;
	}

	public MemberFormPage verifyrewardrebatedropdown() throws InterruptedException {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_rewardrebateoption.fieldControl-option-set-select']")), "---", "Reward rebate");
		ArrayList<String> rewardrebateoptions=new ArrayList<String>();
	//	Select reardrebate= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_rewardrebateoption.fieldControl-option-set-select']")));		

		//Create temp Array List > add  actual options  from DOM for comparison
		//List<WebElement> rewardrebateoptions =reardrebate.getOptions();	
		List<String> expectedRepresentativeType = Arrays.asList("--Select--","Direct parent level","Facility level","Third party","Top parent level");		

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_rewardrebateoption.fieldControl-option-set-select']")),"Reward Debate");

		
		List<WebElement> options=getDriver().findElements(By.xpath("(//div[contains(@id,'pa-option-set-component')]/div/div)"));

		for(int i=1;i<=options.size();i++) {
			rewardrebateoptions.add(getDriver().findElement(By.xpath("(//div[contains(@id,'pa-option-set-component')]/div/div)["+i+"]")).getText());
		}
		
		Actions action= new Actions(getDriver());
		action.sendKeys(Keys.ESCAPE).build().perform();
		
		if(rewardrebateoptions.containsAll(expectedRepresentativeType))
		{		
			Thread.sleep(3000);
			setReport().log(Status.PASS, "RepresentativeType- " + "   " + rewardrebateoptions + "  " +  "-  Option is available to choose from the list" + " "+ expectedRepresentativeType,	screenshotCapture());

		} 
		else {
			setReport().log(Status.FAIL, "RepresentativeType - "+   "   " + rewardrebateoptions + "  " + "- Option is not available in the list"  + " "+ expectedRepresentativeType ,	screenshotCapture());
			Driver.failCount++;
		}
		return this;

	}


	public MemberFormPage selectverifyrewardrebatedropdown(String rewardrebatedropdown) throws InterruptedException {
	//	selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//button[@data-id='ix_rewardrebateoption.fieldControl-option-set-select']")))),rewardrebatedropdown,"rewardrebate dropdown");
		
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_rewardrebateoption.fieldControl-option-set-select']")),"rewardrebatedropdown");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+rewardrebatedropdown+"')]")),"rewardrebatedropdown");

		
		Thread.sleep(4000);
		return this;
	}



	public MemberFormPage selectthirdPartypayeeAccount(String payee) throws InterruptedException {

		click(getDriver().findElement(By.xpath("//input[@data-id='ix_3rdpartypayee.fieldControl-LookupResultsDropdown_ix_3rdpartypayee_textInputBox_with_filter_new']")),"Third party payee account");
		Thread.sleep(1000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_3rdpartypayee.fieldControl-LookupResultsDropdown_ix_3rdpartypayee_textInputBox_with_filter_new']")))),payee,"Third party payee Account");
		//Thread.sleep(120000);
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@data-id,'ix_3rdpartypayee.fieldControl-accountnumber')]/span[contains(text(),'"+payee+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(@data-id,'ix_3rdpartypayee.fieldControl-accountnumber')]/span[contains(text(),'"+payee+"')]")),"Rebate");


		return this;

	}


	public MemberFormPage thirdverifyPayee(String payee) throws InterruptedException {

		click(getDriver().findElement(By.xpath("//input[@data-id='ix_3rdpartypayee.fieldControl-LookupResultsDropdown_ix_3rdpartypayee_textInputBox_with_filter_new']")),"Third party payee account");
		Thread.sleep(1000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_3rdpartypayee.fieldControl-LookupResultsDropdown_ix_3rdpartypayee_textInputBox_with_filter_new']")))),payee,"Third party payee Account");
		Thread.sleep(10000);
		List <WebElement> nodp=getDriver().findElements(By.xpath("//*[contains(text(),'No records found. Create a new record.')]"));
		verifyElementisDisplayed(nodp.size(), "No Direct Parent ");
		clickAndEsc(getDriver().findElement(By.xpath("//input[@data-id='ix_3rdpartypayee.fieldControl-LookupResultsDropdown_ix_3rdpartypayee_textInputBox_with_filter_new']")), "Third party payee button");
		return this;

	}

	//verify LOB sugesstions

	public MemberFormPage verifyLOBClassificationType(String LOB) {
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_classificationtypenew.fieldControl-LookupResultsDropdown_ix_classificationtypenew_textInputBox_with_filter_new']")),"Classification type");
		click(getDriver().findElement(By.xpath("//span[@data-id='ix_classificationtypenew.fieldControl-Lookup_ix_classificationtypenew_microsoftIcon_searchButton']")),"Glass Maginifier");
		List<WebElement> classificationtypecount=getDriver().findElements(By.xpath("//span[contains(@data-id,'ix_classificationtypenew.fieldControl-ix_lineofbusiness')]"));
		for(int i=1;i<classificationtypecount.size();i++) {
			assertTrue(getDriver().findElement(By.xpath("//span[contains(@data-id,'ix_classificationtypenew.fieldControl-ix_lineofbusiness')]")).getText().contentEquals(LOB));
		}
		return this;
	}

	public MemberFormPage clickCancelButton() {
		click(getDriver().findElement(By.xpath("//button[@data-id='quickCreateCancelBtn']")),"Cancel Button");
		return this;
	}

	//Select the account from the default view
	public MemberFormPage selectaccountfromdefaultView() {
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[@col-id='ix_businessclassification' and @class='ag-cell ag-cell-not-inline-editing ag-cell-normal-height ag-cell-value non-editable-cell']"))).build().perform();
		action.moveToElement(getDriver().findElement(By.xpath("//div[contains(@class,'ms-Checkbox is-enabled RowSelectionCheckMarkSpan checkMark')]"))).doubleClick().build().perform();


		return this;
	}

	//Navigate to account Rank feild
	public MemberFormPage navigateToAccountRanking() {
		clickAndTab(getDriver().findElement(By.xpath("//select[@data-id='ix_recordstatus.fieldControl-option-set-select']")), "Record status");
		clickAndTab(getDriver().findElement(By.xpath("//select[@data-id='ix_recordchangestatus.fieldControl-option-set-select']")), "Record change status");
		List<WebElement> premierroaster=getDriver().findElements(By.xpath("//label[contains(text(),'Premier Roster')]"));
		if(premierroaster.size()>0) {
			click(getDriver().findElement(By.xpath("//label[contains(text(),'Premier Roster')]")),"Premier Roaster");
		}
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Current Internal Rep')]")),"Current Internal Rep");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Current Field Rep')]")),"Current Field Rep");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Account Rank')]")),"Account Rank");
		List<WebElement> feeshare=getDriver().findElements(By.xpath("//h2[@title='FEE SHARE']"));
		if(feeshare.size()>0) {
			click(getDriver().findElement(By.xpath("//h2[@title='FEE SHARE']")),"Account Rank");

		}
		return this;
	}

	public MemberFormPage verifyAccountRankingDisabled() {
		assertTrue(getDriver().findElement(By.xpath("//select[@data-id='ix_accountrank.fieldControl-option-set-select' and @ disabled]")).isDisplayed());
		return this;
	}

	public MemberFormPage verifyAccountRankingEnabled() {
		List<WebElement> accountRankfeild=getDriver().findElements(By.xpath("//select[@data-id='ix_accountrank.fieldControl-option-set-select' and @ disabled]"));
		assertTrue(accountRankfeild.size()==0);
		return this;
	}


	public MemberFormPage navigateToSponsorMEF() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Record Status')]")),"Record Status");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Record Change Status')]")),"Record Change Status");
		clickTab(3);
		return this;
	}

	public MemberFormPage verifyAccountRankingisNotMasked() {

		String accountRanking=getAttribute(getDriver().findElement(By.xpath("//Select[@data-id='ix_accountrank.fieldControl-option-set-select']")), "title", "Account Ranking");
		assertTrue(!(accountRanking.contains("*")));

		return this;
	}


	public MemberFormPage verifyAccountRankingDropdown() throws InterruptedException {

		ArrayList<String> selectoptions=new ArrayList<String>();
		Select AccountRanking= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_accountrank.fieldControl-option-set-select']")));

		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> option =AccountRanking.getOptions();	

		List<String> expectdoption = Arrays.asList("--Select--","Gold","Silver","Bronze","House");		


		for(int i=0;i<option.size();i++) {

			selectoptions.add(option.get(i).getText());

		}

		if(selectoptions.containsAll(expectdoption))
		{		
			Thread.sleep(3000);
			setReport().log(Status.PASS, "RepresentativeType- " + "   " + selectoptions + "  " +  "-  Option is available to choose from the list" + " "+ expectdoption,	screenshotCapture());

		} 
		else {
			setReport().log(Status.FAIL, "RepresentativeType - "+   "   " + selectoptions + "  " + "- Option is not available in the list"  + " "+ expectdoption ,	screenshotCapture());
			Driver.failCount++;
		}

		return this;

	}

	public MemberFormPage selectccountRankingDropdown(String accountRanking) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_accountrank.fieldControl-option-set-select']")), accountRanking, "Accoun Ranking");
		String selectedOption=getAttribute(getDriver().findElement(By.xpath("//Select[@data-id='ix_accountrank.fieldControl-option-set-select']")), "title", "Account Ranking");
		assertTrue((selectedOption.contentEquals(accountRanking)));

		return this;

	}


	public String getPrimaryContactName() throws IOException
	{
		String accountName=getTextValue(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_selected_tag_text']")),"PrimaryContact");
		return accountName;
	}

	public MemberFormPage verifyPrimaryAccountNameinCAA() throws IOException, InterruptedException{
		Thread.sleep(200);
		String name=getPrimaryContactName();
		verifyContactAccountAssociation();
		List<WebElement> accountname=getDriver().findElements(By.xpath("//span[contains(text(),'"+name+"')]/ancestor::div[@col-id='ix_contact']"));
		assertTrue(accountname.size()>0);
		return this;
	}

	public MemberFormPage clearPrimaryAccount() throws InterruptedException {
		Actions action = new Actions(getDriver());
		if(getDriver().findElements(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_selected_tag']")).size()>0){
			action.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_selected_tag']"))).perform();
			click(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_selected_tag_delete']")),"Clear Icon"); 
			Thread.sleep(3000);
		}
		return this;

	}

	public MemberFormPage updatePrimaryAccount(String firstname, String lastnmae, String updatename, String updatelastname) throws InterruptedException, IOException {
		String previousName=getPrimaryContactName();
		clearPrimaryAccount();
		clicktheMagnifyingGlass();
		createnewContact(updatename, updatelastname);
		verifyPrimaryAccountNameinCAA();
		verifyrelationEndDate(lastnmae+", "+firstname);

		return this;
	}

	public MemberFormPage EndCAA() throws InterruptedException, IOException {


		Thread.sleep(4000);
		List<WebElement> checkbox=getDriver().findElements(By.xpath("//div[contains(@class,'ms-Checkbox is-enabled RowSelectionCheckMarkSpan checkMark')]"));
		for(int i=1;i<=checkbox.size();i++) {
			Actions actions=new Actions(getDriver());
			actions.moveToElement(getDriver().findElement(By.xpath("//div[contains(@class,'ms-Checkbox is-enabled RowSelectionCheckMarkSpan checkMark')]"))).doubleClick().build().perform();

			type(getDriver().findElement(By.xpath("//input[@data-id='ix_relationshipenddate.fieldControl-date-time-input']")),TestUtils.todaysDate(),"Relation End Date");
			click(getDriver().findElement(By.xpath("//button[@data-id='ix_contactaccountassociation|NoRelationship|Form|Mscrm.Form.ix_contactaccountassociation.SaveAndClose']")),"Save And Close button");
			Thread.sleep(4000);
		}

		return this;
	}


	public MemberFormPage EnterPrimaryAccount(String firstname, String lastnmae) throws InterruptedException, IOException {
		clicktheMagnifyingGlass();
		createnewContact(firstname, lastnmae);
		verifyPrimaryAccountNameinCAA();

		return this;
	}
	public MemberFormPage verifyrelationEndDate(String firstname) throws InterruptedException, IOException {

		String endDate=getAttribute(getDriver().findElement(By.xpath("//span[contains(text(),'"+firstname+"')]/ancestor::div[@col-id='ix_contact']/following-sibling::div[@col-id='ix_relationshipenddate']//label")), "aria-label", "End Date");
		assertTrue((endDate.isEmpty()), "No Relationshipt End Date");

		return this;

	}



	public MemberFormPage clicktheMagnifyingGlass() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//input[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")),"Primary Contact");
		Thread.sleep(4000);
		Actions actions=new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//button[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_search']"))).click().build().perform();
		click(getDriver().findElement(By.xpath("//button[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_addNewBtnContainer']")),"Add new Contact");
		Thread.sleep(5000);

		return this;
	}


	public MemberFormPage createnewContact(String firstName, String SecondName) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@data-id='firstname.fieldControl-text-box-text']")),firstName, "First NAme");
		type(getDriver().findElement(By.xpath("//input[@data-id='lastname.fieldControl-text-box-text']")),SecondName, "Last NAme");
		click(getDriver().findElement(By.xpath("//button[@data-id='quickCreateSaveAndCloseBtn']")),"Click save and Close");
		Thread.sleep(5000);
		verifyPrimaryContactValue(SecondName+", "+firstName);

		return this;
	}

	public MemberFormPage verifyActivateButton() {

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Activate']")).size(),"Activate Button");
		return this;
	}
	public MemberFormPage ActivateAccount() throws InterruptedException {
		if(getDriver().findElements(By.xpath("//button[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Activate']")).size()>0) {
			click(getDriver().findElement(By.xpath("//button[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Activate']")),"Activate Button");
			click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Ok Button");
			Thread.sleep(7000);
			System.out.println(getDriver().findElements(By.xpath("//button[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Activate']")));
			assertTrue(getDriver().findElements(By.xpath("//button[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Activate']")).size()==0);
		}
		return this;
	}


	public MemberFormPage verifyDeactivateButton() throws InterruptedException {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//span[contains(text(),'Deactivate')]")).size(), "Deactivate Button");
		return this;
	}

	public MemberFormPage verifyDeactivateButtonPresent() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[contains(text(),'Deactivate')]")).size(), "Deactivate Button");
		return this;
	}

	public MemberFormPage clickDeactivate() throws InterruptedException {

		clickDeactivateButton();
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id='warningNotification' and contains(text(),'status: Inactive')]")).size(), "Inacitve Status");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Activate']")).size(), "Activate Button");

		Thread.sleep(5000);
		return this;
	}


	public MemberFormPage clickDeactivateButton()throws InterruptedException  {
		if(getDriver().findElements(By.xpath("//span[contains(text(),'Deactivate')]")).size()>0) {
			click(getDriver().findElement(By.xpath("//span[contains(text(),'Deactivate')]")),"Deactivate Member");
		}else {
			click(getDriver().findElement(By.xpath("//button[@data-id='ix_membership|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_membership.Deactivate']")),"Deactivate Member");	
		}

		click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")),"Confirm Deactivate");
		Thread.sleep(5000);


		return this;
	}

	//Select Activity under three dot menu
	public MemberFormPage createNewTaskFromThreeDot() {

		click(getDriver().findElement(By.xpath("//button[@data-id='OverflowButton' and @title='More commands for Account']")),"More Button");

		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[normalize-space()='task']"))).click().build().perform();



		return this;
	}

	//Select Phone under three dot menu
	public MemberFormPage createNewPhoneFromThreeDot() {

		click(getDriver().findElement(By.xpath("//button[@data-id='OverflowButton' and @title='More commands for Account']")),"More Button");

		click(getDriver().findElement(By.xpath("//span[contains(text(),'phonecall')]")),"New Task Activity");

		return this;
	}

	//Select Phone under three dot menu
	public MemberFormPage createNewAppointmentFromThreeDot() {

		click(getDriver().findElement(By.xpath("//button[@data-id='OverflowButton' and @title='More commands for Account']")),"More Button");

		click(getDriver().findElement(By.xpath("//span[contains(text(),'appointment')]")),"New Task Activity");

		return this;
	}


	//Verify the Subject Feild is concatenated
	public MemberFormPage verifytheSubjectFeild(String entitycode, String accountName) {

		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")), entitycode+": "+accountName+":", "Subject is not autopou");
		return this;
	}

	//Enter the Appointment Details
	public MemberFormPage EnterAppointmentDetailsWithQuickSuject(String subject) throws InterruptedException, IOException   {

		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");

		click(getDriver().findElement(By.xpath("//button[@aria-label='Save and Close']")),"Save button");
		Thread.sleep(10000);
		return this;
	}

	//Type street1
	public MemberFormPage typeRandStreet1(String street1) {
		Random rand = new Random();
		String address = rand.nextInt(1, 99)+street1;

		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		type(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))),address, "Street1");
		return this;
	}

	//Navigate To Pipeline
	public  MemberFormPage navigateToPiepline() throws InterruptedException {

		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		click(getDriver().findElement(By.xpath("//span[@data-id='label_relatedEntity_nav_ix_account_ix_pipeline_Account']")),"Membership");
		Thread.sleep(3000);
		return this;
	}

	//Click new Pipeline button
	public  MemberFormPage clickNewPipeline() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_pipeline|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_pipeline.AddNewStandard']")),"New Pipeline button");
		Thread.sleep(3000);
		return this;
	}

	//Click new Multiple pipeline
	public  MemberFormPage clickNewMultiplePipeline() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_pipeline|NoRelationship|SubGridAssociated|ix.ix_pipeline.Button0.AddMultiplePipelines']")),"New Pipeline button");
		Thread.sleep(3000);
		getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@data-id='MscrmControls.WebResource.WebResourceHtmlControl-FullPageWebResource']")));
		return this;
	}


	//Verify Lead source in Multiple pipeline
	public  MemberFormPage verifyLeadSourcePipeline() throws InterruptedException {
		Select LeadSourcePipeline= new  Select(getDriver().findElement(By.xpath("//select[@id='selPipelineLeadSource0']")));		
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =LeadSourcePipeline.getOptions();		
		assertTrue(!(mylist.contains("Parachute")), "Parachute");
		assertTrue(!(mylist.contains("EyePro")), "EyePro");
		return this;
	}

	//Verify Lead source in Multiple pipeline
	public  MemberFormPage verifyPipelineType(String pipelineType) throws InterruptedException {
		Select pipelineTypes= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_pipelinetype.fieldControl-option-set-select']")));		
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =pipelineTypes.getOptions();		
		assertTrue(!(mylist.contains(pipelineType)), "pipelineType");

		return this;
	}



	//Verify Pipeline initiative is not displayed
	public  MemberFormPage verifyPipelineInititativeisNotDisplayed() throws InterruptedException {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//label[contains(text(),'Pipeline Initiative')]")).size(), "Pipeline Initiative");
		return this;
	}


	//Verify Pipeline initiative is not displayed
	public  MemberFormPage verifyPipelineInititativeisNotDisplayedMulti() throws InterruptedException {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//th[contains(text(),'Pipeline Initiatives')]")).size(), "Pipeline Initiative");
		return this;
	}

	//Verify Pipeline Stage in Multiple pipeline
	public  MemberFormPage verifyPipelineStageinMultipipelein(String pipelineStage) throws InterruptedException {
		Select LeadSourcePipeline= new  Select(getDriver().findElement(By.xpath("//select[@id='selPipelineLeadSource0']")));		
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =LeadSourcePipeline.getOptions();		
		assertTrue(!(mylist.contains(pipelineStage)), "pipelineStage");

		return this;
	}




	//Currently verifing pipeline stage fields.
	//verify Pipeline page
	public  MemberFormPage verifyPipelinePage() throws InterruptedException {

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@title='Pipeline Stage']")).size(), "Pipeline stage label");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//input[@data-id='ix_pipelinestage.fieldControl-LookupResultsDropdown_ix_pipelinestage_textInputBox_with_filter_new']")).size(), "Pipeline stage text box");
		return this;

	}

	//Navigate tO Advance Lookup
	public  MemberFormPage navigateToAdvanceLookupinPipelineStage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_pipelinestage.fieldControl-LookupResultsDropdown_ix_pipelinestage_textInputBox_with_filter_new']")),"Pipeline");
		Thread.sleep(1000);
		Actions actions =new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//span[@data-id='ix_pipelinestage.fieldControl-Lookup_ix_pipelinestage_microsoftIcon_searchButton']"))).click().build().perform();
		Thread.sleep(2000);
		actions.moveToElement(getDriver().findElement(By.xpath("//span[@data-id='ix_pipelinestage.fieldControl-LookupResultsDropdown_ix_pipelinestage_advlookup']"))).click().build().perform();
		Thread.sleep(3000);

		return this;	
	}
	//Enter the pipelineStage option in advance lookup
	public  MemberFormPage enterPipeleinStage(String pipelineStage) throws InterruptedException {
		navigateToAdvanceLookupinPipelineStage();
		type(getDriver().findElement(By.xpath("//input[@placeholder='Search' and contains(@aria-labelledby,'advanced_lookup')]")),pipelineStage,"PipelineStageoption");

		return this;
	}


	//verify Pipeline Stage option
	public  MemberFormPage verifyPipelineStageOption(String pipelineStage) throws InterruptedException {

		enterPipeleinStage(pipelineStage);
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[contains(@class,'ag-row-even ag-row-no-focus ag-row')]//div[@col-id='ix_name']//button"))));

		String result=getDriver().findElement(By.xpath("//div[contains(@class,'ag-row-even ag-row-no-focus ag-row')]//div[@col-id='ix_name']//button")).getAttribute("aria-label");		
		assertTrue(pipelineStage.contentEquals(result), "Pipeline Stage is displayed");		
		return this;

	}

	//verify Pipeline Stage option
	public  MemberFormPage verifyPipelineStageOptionisNotDisplayed(String pipelineStage) throws InterruptedException {

		enterPipeleinStage(pipelineStage);
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//span[contains(text(),'We didn’t find a match')]"))));

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[contains(text(),'We didn’t find a match')]")).size(), "Pipeline Stage");		
		return this;

	}

	//Enter the pipeleindetails
	public  MemberFormPage enterPipeleinDetails(String pipelineType, String anticipateddate, String pipelineStage, String category, String leadSource,String annualizedsales, String notes) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_pipelinetype.fieldControl-option-set-select']")), pipelineType, "pipelineType");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_anticipatedstartpurchasingmonth.fieldControl-date-time-input']")),anticipateddate,"Anticipated Date");
		selectPipelineStage(pipelineStage);
		selectRevenueCategory(category);
		selectPipeleinLead(leadSource);
		enterAnnualizedsales(annualizedsales);
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='ix_feeshare.fieldControl-decimal-number-text-input']")),"Feeshare");
		type(getDriver().findElement(By.xpath("//textarea[@data-id='ix_notes.fieldControl-text-box-text']")),notes,"Notes");
		return this;
	}

	//Click Sae and Close button
	public  MemberFormPage clickSaveAndCloseinPipleine() throws InterruptedException { 

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_pipeline|NoRelationship|Form|Mscrm.Form.ix_pipeline.SaveAndClose']")),"Save and Close Button");
		Thread.sleep(6000);
		return this;
	}


	//Click Sae and Close button
	public  MemberFormPage clicksaveinPipeline() throws InterruptedException { 

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_pipeline|NoRelationship|Form|Mscrm.Form.ix_pipeline.Save']")),"Save Button");
		return this;
	}


	//Select pipeline stage from the lookup
	public  MemberFormPage selectPipelineStage(String pipelineStage) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_pipelinestage.fieldControl-LookupResultsDropdown_ix_pipelinestage_textInputBox_with_filter_new']")),pipelineStage,"pipelineStage");

		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//li[@data-id='ix_pipelinestage.fieldControl-LookupResultsDropdown_ix_pipelinestage_resultsContainer'  and contains(@aria-label,'"+pipelineStage+"')]"))));

		click(getDriver().findElement(By.xpath("//li[@data-id='ix_pipelinestage.fieldControl-LookupResultsDropdown_ix_pipelinestage_resultsContainer'  and contains(@aria-label,'"+pipelineStage+"')]")),"Pipeline Stage");
		return this;
	}


	//Select pipeline Lead from the lookup
	public  MemberFormPage selectPipeleinLead(String leadSource) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_pipelineleadsources.fieldControl-LookupResultsDropdown_ix_pipelineleadsources_textInputBox_with_filter_new']")),leadSource,"leadSource");

		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@data-id='ix_pipelineleadsources.fieldControl-LookupResultsDropdown_ix_pipelineleadsources_infoContainer']//span[contains(text(),'"+leadSource+"')]"))));

		click(getDriver().findElement(By.xpath("//div[@data-id='ix_pipelineleadsources.fieldControl-LookupResultsDropdown_ix_pipelineleadsources_infoContainer']//span[contains(text(),'"+leadSource+"')]")),"leadSource");
		return this;
	}


	//Select pipeline stage from the lookup
	public  MemberFormPage selectRevenueCategory(String Category) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_textInputBox_with_filter_new']")),Category,"Category");

		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//li[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_resultsContainer']//span[contains(@data-id,'ix_revenuecategory.fieldControl-ix_name')]//span[contains(text(),'"+Category+"')]"))));

		click(getDriver().findElement(By.xpath("//li[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_resultsContainer']//span[contains(@data-id,'ix_revenuecategory.fieldControl-ix_name')]//span[contains(text(),'"+Category+"')]")),"Category");
		return this;
	}



	//Horizonatal scroll to Notes in pipline
	public  MemberFormPage navigateToNotes(String notes) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		WebElement element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		Actions builder = new Actions(getDriver());
		builder.moveToElement(element).clickAndHold().perform();


		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).perform();


		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).release().perform();

		js.executeScript("window.scrollTo(0,0)");

		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).clickAndHold().perform();


		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).perform();


		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).release().perform();


		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).clickAndHold().perform();


		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).perform();


		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).release().perform();


		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).clickAndHold().perform();


		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).perform();


		element = getDriver().findElement(By.cssSelector(".ag-body-horizontal-scroll-viewport"));
		builder = new Actions(getDriver());
		builder.moveToElement(element).release().perform();


		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@col-id='ix_notes']//label[contains(@class,'labelRoot') and @aria-label='"+notes+"']")).size(),"Notes");

		return this;
	}

	//Double click and open the existing pipelein
	public  MemberFormPage deactivatePipeLine() throws InterruptedException {

		List<WebElement> rows=getDriver().findElements(By.xpath("//div[@class='ag-center-cols-container']//div[contains(@class,'ag-row')]"));

		for(int i=1;i<=rows.size();i++) {

			Actions builder = new Actions(getDriver());
			builder.moveToElement(getDriver().findElement(By.xpath("(//div[@class='ag-center-cols-container']//div[contains(@class,'ag-row')])[1]"))).doubleClick().build().perform();
			clickDeactivateButtonInPipeline();
			Thread.sleep(2000);
			clickGoBackButton();
			Thread.sleep(2000);
		}

		return this;
	}

	//click deactivate button in pipeline
	public MemberFormPage clickDeactivateButtonInPipeline() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_pipeline|NoRelationship|Form|Mscrm.Form.ix_pipeline.Deactivate']")),"Deactivate Button");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Okay Id");
		Thread.sleep(2000);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_pipeline|NoRelationship|Form|Mscrm.Form.ix_pipeline.Activate']")).size(), "Activate Button");
		return this;
	}

	//Enter Anualised Sales
	public MemberFormPage enterAnnualizedsales(String annualizedsales) {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_annualizedsales.fieldControl-currency-text-input']")),annualizedsales,"annualizedsales");
		return this;

	}

	//verify Pipeline Stage option
	public  MemberFormPage verifyPipelineStageAllOption() throws InterruptedException {
		List<String> expectdoption = Arrays.asList("1 - Outreach","2 - Initial Discussion","3 - Proposal/Negotiations","4 - Contract Sent","Business At Risk - 50%","Business At Risk – 75%","Business At Risk – Lost","Business At Risk – Saved","Opportunity Lost","Won");
		List<String> pipelineStageMenu=new ArrayList<String>();
		List<WebElement> pipeleinStageOptions=getDriver().findElements(By.xpath("//div[@col-id='ix_name']//button//span"));
		for(int i =1;i<=pipeleinStageOptions.size();i++) {
			pipelineStageMenu.add(getDriver().findElement(By.xpath("(//div[@col-id='ix_name']//button//span)["+i+"]")).getText());
		}

		assertTrue(pipelineStageMenu.containsAll(expectdoption), "Pipeline Stage is displayed");		
		return this;

	}

	//verify probbility feild is locked
	public  MemberFormPage verifyProbabilityFieldisLocked() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//input[@data-id='ix_probabilityvalue.fieldControl-whole-number-text-input' and @readonly]")).size(), "Probability field is locked");
		return this;	
	}

	//verify Probability feild is Auto populated
	public  MemberFormPage verifyProbabilityAutopopulated(String pipelineStage) throws InterruptedException {
		HashMap<String, String> probabilityValue=new HashMap<String, String>();
		probabilityValue.put("1 - Outreach", "5");
		probabilityValue.put("2 - Initial Discussion", "20");
		probabilityValue.put("3 - Proposal/Negotiations", "50");
		probabilityValue.put("4 - Contract Sent", "90");
		probabilityValue.put("Business At Risk - 50%", "50");
		probabilityValue.put("Business At Risk - 75%", "75");
		probabilityValue.put("Business At Risk – Lost", "100");
		probabilityValue.put("Business At Risk – Saved", "0");
		probabilityValue.put("Opportunity Lost", "0");
		probabilityValue.put("Won", "100");
		selectPipelineStage(pipelineStage);
		verifyExactAttribute(getDriver().findElement(By.xpath("//input[@data-id='ix_probabilityvalue.fieldControl-whole-number-text-input' and @readonly]")), "value", probabilityValue.get(pipelineStage), "Probability feild");
		return this;
	}

	//Enter the pipeleindetails
	public  MemberFormPage enterPipeleinDetailsWithoutLeadsource(String pipelineType, String anticipateddate, String pipelineStage, String category,String annualizedsales, String notes) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_pipelinetype.fieldControl-option-set-select']")), pipelineType, "pipelineType");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_anticipatedstartpurchasingmonth.fieldControl-date-time-input']")),anticipateddate,"Anticipated Date");
		selectPipelineStage(pipelineStage);
		selectRevenueCategory(category);
		enterAnnualizedsales(annualizedsales);
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='ix_feeshare.fieldControl-decimal-number-text-input']")),"Feeshare");
		type(getDriver().findElement(By.xpath("//textarea[@data-id='ix_notes.fieldControl-text-box-text']")),notes,"Notes");
		return this;
	}

	//verifyError Message
	public  MemberFormPage verifyLeadSourceMandatory() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id='warningNotification' and contains(text(),'Pipeline Lead Source : Required fields must be filled in.')]")).size(), "Lead source is mandatory");
		return this;
	}

	//Verify Admin fee
	public  MemberFormPage verifyAdminFee(String adminfee) throws InterruptedException {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//input[@data-id='ix_adminfee.fieldControl-decimal-number-text-input']")), adminfee, "Admin Fedd");
		return this;
	}

	//Clear Revenue Category
	public MemberFormPage clearRevenueCategory() throws InterruptedException {

		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_selected_tag_text']"))).perform();
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_selected_tag_delete']")),"Clear Icon"); 
		Thread.sleep(3000);
		return this;	
	}


	//Verif NAF
	public MemberFormPage verifyNAF() throws InterruptedException {
		navigateToSysteminPipeline();
		navigateToSGeneralinPipeline();
		Thread.sleep(4000);
		String GAF =getAttribute(getDriver().findElement(By.xpath("//input[@data-id='ix_projectedgaf.fieldControl-currency-text-input']")), "title","GAF");
		GAF=GAF.replace("$", "");
		double GAFNo=Double.valueOf(GAF);

		String Feeshare =getAttribute(getDriver().findElement(By.xpath("//input[@data-id='ix_feeshare.fieldControl-decimal-number-text-input']")), "title","Fee share");
		double Feeshareno=Double.valueOf(Feeshare);
		System.out.println(Feeshareno/100.0);
		double NAF=GAFNo*(1-(Feeshareno/100.0));
		String.format("%.2f",new BigDecimal(NAF));
		String NAFString=getAttribute(getDriver().findElement(By.xpath("//input[@data-id='ix_projectednaf.fieldControl-currency-text-input']")), "title","NAF");

		assertTrue(NAFString.contentEquals("$"+NAF),NAFString+" "+NAF+"NAF calculated correctly" );
		return this;
	}

	public MemberFormPage navigateToSysteminPipeline() {
		click(getDriver().findElement(By.xpath("//li[@aria-label='System']")),"System Tab");

		return this;
	}

	public MemberFormPage navigateToSGeneralinPipeline() {
		click(getDriver().findElement(By.xpath("//li[@aria-label='General']")),"GeneralTab");

		return this;
	}

	public MemberFormPage clearFeeshare() throws InterruptedException {

		clear(getDriver().findElement(By.xpath("//input[@data-id='ix_feeshare.fieldControl-decimal-number-text-input']")),"Fee share");

		return this;
	}


	//Clear Lead Resource
	public MemberFormPage clearLeadResource() throws InterruptedException {

		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='ix_pipelineleadsources.fieldControl-LookupResultsDropdown_ix_pipelineleadsources_selected_tag']"))).perform();
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_pipelineleadsources.fieldControl-LookupResultsDropdown_ix_pipelineleadsources_selected_tag_delete']")),"Clear Icon"); 
		Thread.sleep(3000);
		return this;	
	}



	//Clear feeshare in Multiple Pipeline
	public MemberFormPage clearFeeshareInMultiplePipeline() throws InterruptedException{

		clear(getDriver().findElement(By.id("txtFeeShare0")),"Fee share");
		return this;
	}

	//Clear feeshare in Multiple Pipeline
	public MemberFormPage enterFeeshareInMultiplePipeline(String feeshare) throws InterruptedException{

		type(getDriver().findElement(By.id("txtFeeShare0")),feeshare,"Fee share");
		return this;
	}

	//Enter Multiplepipeline value
	public MemberFormPage enterMultiplePipelein() throws InterruptedException {

		return this;
	}

	//Enter Multiplepipeline value
	public MemberFormPage selectPipelineStageinMultipipeline(String pipelineStage) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@id='selPipelineStage0']")), pipelineStage, "pipelineStage");

		return this;
	}

	public MemberFormPage selectLeadSourceinMultipipeline(String leadSource) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@id='selPipelineLeadSource0']")), leadSource, "Lead source");

		return this;
	}

	public MemberFormPage clickSaveAndCloseMultipipleine() throws InterruptedException {
		Actions actions =new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//div[@id='dvTable']/following-sibling::div/button[@id='btnSave']"))).click().build().perform();
		Thread.sleep(4000);

		return this;
	}

	//vERify Alert is displayed
	public MemberFormPage verifyAlertIsDisplayed(String errormessage) throws InterruptedException {


		String alertText=getDriver().switchTo().alert().getText();
		getDriver().switchTo().alert().accept();
		System.out.println(alertText);
		System.out.println(errormessage);
		assertTrue(alertText.contentEquals(errormessage));

		return this;
	}
	// verify pipeleinStage is not displayed
	public MemberFormPage verifyPipelineStageisNotDisplayed(String pipelineStage) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_pipelinestage.fieldControl-LookupResultsDropdown_ix_pipelinestage_textInputBox_with_filter_new']")),pipelineStage,"pipelineStage");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id='ix_pipelinestage.fieldControl-LookupResultsDropdown_ix_pipelinestage_No_Records_Text']")).size(), pipelineStage);
		return this;
	}

	//Open the existign pipeline
	public MemberFormPage openExistingPipeline() throws InterruptedException {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//div[@col-id='ix_pipelinetype' and contains(@class,'ag-cell ag-cell-not-inline-editing ag-cell-normal-height ag-cell-value non-editable-cell')]"))).doubleClick().build().perform();

		return this;
	}

	//Open the existign pipeline
	public MemberFormPage enterAdminFee(String adminefee) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_adminfee.fieldControl-decimal-number-text-input']")),adminefee,"Admin Fee");
		return this;
	}


	public MemberFormPage clickFBO() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//div[@data-id='ix_fbo.fieldControl-LookupResultsDropdown_ix_fbo_selected_tag_text']")),"FBO");
		return this;
	}

	//Navigate To Pipeline
	public  MemberFormPage navigateToPieplineFeeShare() throws InterruptedException {

		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		click(getDriver().findElement(By.xpath("//span[@data-id='label_relatedEntity_nav_ix_account_ix_pipelinefeeshare_accountid']")),"Membership");
		Thread.sleep(3000);
		return this;
	}	


	//get the fee share from FBO
	public  MemberFormPage getFeeShareFromFBO() throws InterruptedException {
		feeshare=getTextValue(getDriver().findElement(By.xpath("//div[@col-id='ix_feeshare']//label[contains(@class,'ms-Label labelRootStyles')]/div[contains(@class,'ms-TooltipHost')]")),"FEE share");
		return this;
	}

	//get the fee share from FBO
	public  MemberFormPage getFeeShare() throws InterruptedException {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//input[@data-id='ix_feeshare.fieldControl-decimal-number-text-input']")),feeshare,"FEE share");
		return this;
	}

	//verify Probability feild is Auto populated
	public  MemberFormPage verifyPipeleinProbability(String pipelineStage) throws InterruptedException {
		HashMap<String, String> probabilityValue=new HashMap<String, String>();
		probabilityValue.put("1 - Outreach", "5");
		probabilityValue.put("2 - Initial Discussion", "20");
		probabilityValue.put("3 - Proposal/Negotiations", "50");
		probabilityValue.put("4 - Contract Sent", "90");
		probabilityValue.put("Business At Risk - 50%", "50");
		probabilityValue.put("Business At Risk - 75%", "75");
		probabilityValue.put("Business At Risk – Lost", "100");
		probabilityValue.put("Business At Risk – Saved", "0");
		probabilityValue.put("Opportunity Lost", "0");
		probabilityValue.put("Won", "100");
		for(String keys:probabilityValue.keySet()) {

			enterPipeleinStage(keys);
			verifyExactAttribute(getDriver().findElement(By.xpath("//input[@data-id='ix_probabilityvalue.fieldControl-whole-number-text-input' and @readonly]")), "value", probabilityValue.get(keys), "Probability feild");
			clearStageinAdvanceFind();

		}
		return this;
	}

	//ClearPipeline Stage
	public MemberFormPage clearPipeleinStage() throws InterruptedException {

		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='ix_pipelinestage.fieldControl-LookupResultsDropdown_ix_pipelinestage_selected_tag_text']"))).perform();
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_pipelinestage.fieldControl-LookupResultsDropdown_ix_pipelinestage_selected_tag_delete']")),"Clear Icon"); 
		Thread.sleep(3000);
		return this;	
	}

	//Clear Lead Resource
	public MemberFormPage clearStageinAdvanceFind() throws InterruptedException {

		clear(getDriver().findElement(By.xpath("//input[@placeholder='Search' and contains(@aria-labelledby,'advanced_lookup')]")),"Clear Icon"); 
		Thread.sleep(3000);

		return this;	
	}

	public MemberFormPage verifyNotificationisNotDisplayed() {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//div[@data-id='notificationWrapper']")).size(), "NotificationWrapper");
		return this;
	}

	public MemberFormPage verifyRevenueCategory() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_textInputBox_with_filter_new']")),"Category");
		Thread.sleep(1000);
		Actions actions =new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//button[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_search']"))).click().build().perform();
		Thread.sleep(2000);
		actions.moveToElement(getDriver().findElement(By.xpath("//a[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_advancedLookupBtnContainer']"))).click().build().perform();
		Thread.sleep(3000);


		return this;

	}

	//verify Revenue Category option
	public  MemberFormPage verifyRevenueCategorAllOption() throws InterruptedException {
		List<String> expectdoption = Arrays.asList("American Express","Blood Distribution","Blood Products","Capital Equipment","Facilities Management","Food","Laboratory","Medical Supply","Pharmacy","Rx Distribution","Technology","Vaccines");
		List<String> revenueCategory=new ArrayList<String>();
		List<WebElement> revenueCategoryOptions=getDriver().findElements(By.xpath("//div[@col-id='ix_name']//button//span"));
		for(int i =1;i<=revenueCategoryOptions.size();i++) {
			revenueCategory.add(getDriver().findElement(By.xpath("(//div[@col-id='ix_name']//button//span)["+i+"]")).getText());
		}
		System.out.println(revenueCategory);
		System.out.println(expectdoption);
		assertTrue(revenueCategory.containsAll(expectdoption), "Revenue Category is displayed");		
		return this;

	}

	//verify Revenue Category option
	public  MemberFormPage verifyRevenueOptionisNotDisplayed() throws InterruptedException {
		List<String> expectdoption = Arrays.asList("Food Distribution","Imaging","Office Supplies","Services (w/o AMEX)","Medical Supply Distribution","Enterals & Devices","Telecommunications");
		List<String> revenueCategory=new ArrayList<String>();
		List<WebElement> revenueCategoryOptions=getDriver().findElements(By.xpath("//div[@col-id='ix_name']//button//span"));
		for(int i =1;i<=revenueCategoryOptions.size();i++) {
			revenueCategory.add(getDriver().findElement(By.xpath("(//div[@col-id='ix_name']//button//span)["+i+"]")).getText());
		}
		System.out.println(expectdoption);
		System.out.println(revenueCategory);
		for(String options:expectdoption) {
			assertTrue(!(revenueCategory.contains(options)), "Revenue Category is Updated");
		}

		return this;

	}


	//Clear the revenue Category and select other value for Revenue CAtegory

	public  MemberFormPage clearandEnterOtherCategory() throws InterruptedException {
		List<String> expectdoption = Arrays.asList("American Express","Blood Distribution","Blood Products","Capital Equipment","Facilities Management","Food","Laboratory","Medical Supply","Pharmacy","Rx Distribution","Technology","Vaccines");
		Thread.sleep(4000);
		for(String options:expectdoption) {
			clearRevenueCategory();
			selectRevenueCategory(options);
			clicksaveinPipeline();
			verifyErrorisNotDisplayed();
		}
		return this;
	}


	//Click New Membership category button

	public MemberFormPage clickNewMembershipCategory() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_membershipcategory|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_membershipcategory.AddNewStandard']")),"New Membership CAtegory");

		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(180));

		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//h1[contains(text(),'Quick Create: Membership Category')]"))));



		return this;
	}

	public MemberFormPage verifyMembershipCategoryQuickForm() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-id='ix_membershipid-FieldSectionItemContainer']//label[contains(text(),'Membership')]")).size(), "Membership Title");
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//div[@data-id='ix_membershipid-FieldSectionItemContainer']//label[contains(text(),'Membership Provider')]")).size(), "Membership Provider Title");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//ul[@data-id='ix_membershipid.fieldControl-LookupResultsDropdown_ix_membershipid_SelectedRecordList']")).size(), "Membership Textbox");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//input[@data-id='ix_membershipprogramcategory.fieldControl-LookupResultsDropdown_ix_membershipprogramcategory_textInputBox_with_filter_new']")).size(), "Membership Category");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@data-id='quickCreateRoot']//input[@aria-label='Date of Start Date']")).size(), "StartDate");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[@data-id='quickCreateSaveAndCloseBtn']")).size(), "Save and Close button in the aucik form");
		return this;
	}

	//Enter the Membership program category
	public MemberFormPage enterNewMembershipCategory(String membershipCategory) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_membershipprogramcategory.fieldControl-LookupResultsDropdown_ix_membershipprogramcategory_textInputBox_with_filter_new']")),membershipCategory,"membershipCategory");

		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(180));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-id='ix_membershipprogramcategory.fieldControl-LookupResultsDropdown_ix_membershipprogramcategory_infoContainer']//span[contains(text(),'"+membershipCategory+"')]")));

		click(getDriver().findElement(By.xpath("//div[@data-id='ix_membershipprogramcategory.fieldControl-LookupResultsDropdown_ix_membershipprogramcategory_infoContainer']//span[contains(text(),'"+membershipCategory+"')]")),"membershipCategory");

		return this;

	}

	//Type membership end date
	public MemberFormPage typeMembershipCategoryStratDate(String startDate) {
		type(((getDriver().findElement(By.xpath("//section[@data-id='quickCreateRoot']//input[@aria-label='Date of Start Date']")))),startDate,"End Date");
		return this;
	}
	
	//section[@data-id="quickCreateRoot"]//input[@aria-label='Date of Start Date']


	//Click Save and Close
	public MemberFormPage clickSaveAndCloseinMembershipCategory() {
		click(getDriver().findElement(By.xpath("//button[@data-id='quickCreateSaveAndCloseBtn']")),"Save and Close");
		return this;
	}

	//Click Clcik Cancel in Quicl create Form
	public MemberFormPage clickCancelinMembershipCategory() {
		click(getDriver().findElement(By.xpath("//button[@data-id='quickCreateCancelBtn']")),"Cancel");
		return this;
	}

	//Select all membership categories checkbox
	public MemberFormPage clickSelectAllCheckBox() throws InterruptedException {
		Thread.sleep(2000);

		Actions action=new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//label/*[contains(text(),'Membership Category')]/ancestor::div[contains(@class,'pcf-grid-header ')]/preceding-sibling::div[contains(@class,'pcf-grid-header ')]//label"))).click().build().perform();
		Thread.sleep(2000);


		return this;
	}

	//click Deactivate in MEmbershipCategory
	public MemberFormPage clickDeactivateButtoninCategory() {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_membershipcategory|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_membershipcategory.Deactivate']")),"Deactivate Button");
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Deactivate Button");
		return this;
	}

	//click Edit in MEmbershipCategory
	public MemberFormPage clickEditButtoninCategory() {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_membershipcategory|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_membershipcategory.Edit']")),"Edit Button");

		return this;
	}

	//Enter End Date in MembershipCategory(){
	public MemberFormPage typeMembershipCategoryEndDate(String endDate) {
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of End Date']")),endDate,"End Date");
		return this;
	}

	//Click save and Close
	public MemberFormPage clickSaveAndCloseMembershipCategory() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_membershipcategory|NoRelationship|Form|Mscrm.Form.ix_membershipcategory.SaveAndClose']")),"Save and close button");
		Thread.sleep(4000);
		return this;
	}


	//Verify Mandatory feild error message is Displayed
	public MemberFormPage verifyMandatoryerrormessage() {

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id='ix_membershipprogramcategory-error-message' and contains(text(),'Membership Category: Required fields must be filled in.')]")).size(), "Membership Categoris  required error message");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id='ix_startdate-error-message' and contains(text(),'Start Date: Required fields must be filled in.')]")).size(), "Start date required error message");
		return this;

	}

	//Click System Tab in MembershipCategories Page
	public MemberFormPage clickSystemTabinCategory() {
		click(getDriver().findElement(By.xpath("//li[@aria-label='System' and contains(text(),'System')]")),"System tab in Membership Cateogry");
		return this;
	}

	//Verify System Tab in the MembershipCategory
	public MemberFormPage verifySystemInCategory() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[@title='Owner - Owner Id']")).size(), "Owner");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[@title='Status - Status of the Membership Category']")).size(), "Status");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[@title='Created On - Date and time when the record was created.']")).size(), "Created on Date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[@title='Created By - Unique identifier of the user who created the record.']")).size(), "Created By");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[@title='Modified On - Date and time when the record was modified.']")).size(), "Modified Date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[@title='Modified By - Unique identifier of the user who modified the record.']")).size(), "Modified By");
		return this;
	}

	//Click General Tab in the membership Category
	public MemberFormPage clickGeneralTabinCategory() {
		click(getDriver().findElement(By.xpath("//li[@aria-label='General']")),"General tab in Membership Cateogry");
		return this;
	}

	//verify MembershipCategroy is Displayed after membership has been created
	public MemberFormPage verifyMembershipCAtegory() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//h2[@data-id='form-sectionHeader-MembershipCategoriesSection']")).size(), "Memberhip CAtegories Header");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_membershipcategory|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_membershipcategory.AddNewStandard']")).size(), "New Memmbership CAtegories button");
		return this;
	}

	//verify MembershipCategroy feilds
	public MemberFormPage verifyMembershipCAtegoryFeilds() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("///div[contains(@col-id,'ix_emsattributecode')]")).size(), "EMS Attribute");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@col-id,'modifiedby')]")).size(), "Modified By");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@col-id,'modifiedon')]")).size(), "Modified on");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@col-id,'ix_startdate')]")).size(), "Start Date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@col-id,'ix_enddate')]")).size(), "Ends Date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@col-id,'ix_membershipprogramcategory')]")).size(), "Membership CAtegroy");


		return this;
	}

	//VErify Contact under Related tab
	public ContractAttachmentPage navigateToContractAttachmentOption() throws InterruptedException {
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(2000);
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//span[contains(text(),'Contract Attachments')])[2]")),"Contract Attachments");
		Thread.sleep(2000);

		return new ContractAttachmentPage();
	}

	//Add New Program Categories
	public MemberFormPage clickProgramCategories() { 
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_programcategory|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_programcategory.AddNewStandard']")),"New Program Category");
		return this;	
	}


	//Verify New progtam catagories is not displayed
	public MemberFormPage verifyAddProgramCategoriesNotDisplayed() {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_programcategory|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_programcategory.AddNewStandard']")).size(), "Add NEw categories");
		return this;
	}

	//Add NEw Category
	public MemberFormPage addProgramCategories(String categoryName, String EMSCode) {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_name.fieldControl-text-box-text']")),categoryName,"Category Name");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_emsattributecode.fieldControl-text-box-text']")),EMSCode,"EMSCode");

		return this;
	}


	//Add NEw Category
	public MemberFormPage editProgramCategories(String categoryName, String EMSCode) {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_name.fieldControl-text-box-text']")),categoryName,"Category Name");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_emsattributecode.fieldControl-text-box-text']")),EMSCode,"EMSCode");

		return this;
	}

	//Click save butoon
	public MemberFormPage saveAndCloseProgramCategories() {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_programcategory|NoRelationship|Form|Mscrm.Form.ix_programcategory.SaveAndClose']")),"Click save Button");
		return this;
	}

	//Verify Program Categories created is displayed
	public MemberFormPage verifyProgramCategoriesCreated(String categoryName) {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@col-id='ix_name']//a//span[contains(text(),'"+categoryName+"')]")).size(), "Program Categories");
		return this;
	}


	//Select ProgramCategories
	public MemberFormPage selectProgramCategories(String categoryName) {
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[@col-id='ix_name']//a//span[contains(text(),'"+categoryName+"')]"))).click().build().perform();
		return this;
	}

	//Select ProgramCategories
	public MemberFormPage openProgramCategories(String categoryName) {
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[@col-id='ix_name']//a//span[contains(text(),'"+categoryName+"')]"))).doubleClick().build().perform();
		return this;
	}

	//Verify Error message
	public MemberFormPage verifyProgramCategorieserror() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id='ix_name-error-message']")).size(), "category Name");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id='ix_emsattributecode-error-message']")).size(), "ems attribute code");
		return this;
	}

	//Delete the Program Categories
	public MemberFormPage deleteProgramCategories(String categoryName) {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_programcategory|NoRelationship|Form|Mscrm.Form.ix_programcategory.Delete']")),"Delete Button");
		click(getDriver().findElement(By.xpath("//button[@data-id='confirmButton']")),"Delete Confirm Button");
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//div[@col-id='ix_name']//a//span[contains(text(),'"+categoryName+"')]")).size(), "Program Categories");		
		return this;
	}

	//Select View
	public MemberFormPage selectProgramCategoryView(String categoryView) {
		click(getDriver().findElement(By.xpath("//span[contains(@id,'ViewSelector')]")),"View Selecotr");
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+categoryView+"')]")),categoryView+" View Selecotr");
		return this;
	}

	//Deactivate the Program Categories
	public MemberFormPage deactivateProgramCategories(String categoryName) {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_programcategory|NoRelationship|Form|Mscrm.Form.ix_programcategory.Deactivate']")),"Delete Button");
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Delete Confirm Button");
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//div[@col-id='ix_name']//a//span[contains(text(),'"+categoryName+"')]")).size(), "Program Categories");		
		return this;
	}

	//SEarch the membership
	public MemberFormPage searchmemberShip(String membership) throws InterruptedException {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//span[contains(@id,'quickFind_clear_button_icon')]")).size()>0){
			click(getDriver().findElement(By.xpath("//span[contains(@id,'quickFind_clear_button_icon')]")),"Clear Button");
		}
		type(getDriver().findElement(By.xpath("//input[@aria-label='Membership Filter by keyword']")),membership,"Memberhsip keyword");
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//button[@title='Start search']")).size()>0) {
			click(getDriver().findElement(By.xpath("//button[@title='Start search']")),"go Button");	
		}
		
		Actions action= new Actions(getDriver());
		action.sendKeys(Keys.ENTER).build().perform();
		return this;

	}

	//End membership 
	public MemberFormPage endDateandDeleteMembership(String membership) throws InterruptedException{
		if(getDriver().findElements(By.xpath("//div[@col-id='ix_membershipprovider']//a//span[contains(text(),'"+membership+"')]")).size()>0) {
			doubleClickMembership(membership);
			typeMembershipEndDate(TestUtils.todaysDate());
			selectMembershipEndReason("Change");
			clickQuickCreateMembershipSave();
			clickDeactivateMembershipProvider();
			clickGoBack();
		}

		return this;
	}

	//End membership 
	public MemberFormPage createMembership(String membership, String membershipProviderType, String membershipProvider) throws InterruptedException{
		if(getDriver().findElements(By.xpath("//div[@col-id='ix_membershipprovider']//a//span[contains(text(),'"+membership+"')]")).size()==0) {
			clickMembershipAndAddNewMembership();

			// Choose Membership type 
			selectMembershipType(membershipProviderType);
			selectMembershipProvider(membershipProvider)
			;
			//Provide any start date and click on save
			typeMembershipStartDate(TestUtils.todaysDate());

			//Click on membership save and close
			clickQuickCreateMembershipSaveAndClose();


		}

		return this;
	}

	public MemberFormPage createMembershipIfNotPresent(String membershipProviderType, String membershipProvider) throws InterruptedException, ParseException{
		selectMembership();
		searchmemberShip(membershipProvider);
		if(getDriver().findElements(By.xpath("(//*[@col-id='ix_membershipprovider']//span)")).size()>0) {
			if(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)")).getText().equalsIgnoreCase(membershipProvider)) {
				if(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)/ancestor::div[@col-id='ix_membershipprovider']/following-sibling::div[@col-id='ix_enddate']//label")).getAttribute("aria-label").isEmpty()) {
					doubleClickMembership(membershipProvider);
					typeMembershipStartDate(TestUtils.getPastDate(TestUtils.todaysDate(), 2));
					//Click on membership save and close
					clickQuickCreateMembershipSave();

					//Verify Error message is not displayed
					verifyErrorisNotDisplayed();
				}}
		}
		else {
			//Click add new membership
			clickAddNewMembershipButton();

			// Choose Membership type 
			selectMembershipType(membershipProviderType);
			selectMembershipProvider(membershipProvider);

			//Provide any start date and click on save
			typeMembershipStartDate(TestUtils.todaysDate());

			//Click on membership save and close
			clickQuickCreateMembershipSave();

			//Verify Error message is not displayed
			verifyErrorisNotDisplayed();



		}


		return this;
	}

	public MemberFormPage verifyErrorinMembershipCreation(String membershipProviderType, String membershipProvider) throws InterruptedException, ParseException{
		selectMembership();
		searchmemberShip(membershipProvider);
		if(getDriver().findElements(By.xpath("(//*[@col-id='ix_membershipprovider']//span)")).size()>0) {
			if(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)")).getText().equalsIgnoreCase(membershipProvider)) {
				if(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)/ancestor::div[@col-id='ix_membershipprovider']/following-sibling::div[@col-id='ix_enddate']//label")).getAttribute("aria-label").isEmpty()) {
					doubleClickMembership(membershipProvider);
					verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[@data-id='ix_startdate.fieldControl-date-time-input' and @disabled]")).size(), "Start date is disabled");
					verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id=\"warningNotification\" and contains(text(),\"Read-only: You don't have access to edit this record.\")]")).size(), "Read only message is displayed");
					verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_membershipcategory|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_membershipcategory.AddNewStandard']")).size(), "Read only message is displayed");


				}}
		}
		else {

			//Verify New membership button is not displayed
			verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//*[@data-id='ix_membership|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_membership.AddNewStandard']")).size(), "Create membership button");

			//Click add new membership
			/*
			 * clickAddNewMembershipButton();
			 * 
			 * // Choose Membership type selectMembershipType(membershipProviderType);
			 * selectMembershipProvider(membershipProvider);
			 * 
			 * //Provide any start date and click on save
			 * typeMembershipStartDate(TestUtils.todaysDate());
			 * 
			 * //Click on membership save and close clickQuickCreateMembershipSave();
			 * 
			 * //once defect 1028984 is fixed will update the error message //Verify Error
			 * message is not displayed verifyError("");
			 * 
			 * 
			 */
		}


		return this;
	}

	public MemberFormPage navigatetoOppurtunity() throws InterruptedException, ParseException{
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("(//*[text()='Opportunities'])[3]")),"Opportunities");
		Thread.sleep(3000);

		return this;
	}


	public MemberFormPage changetheOppurtunityView(String view) {
		click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[2]")),"Click View Selector");
		if(getDriver().findElements(By.xpath("(//span[contains(text(),'"+view+"')])[2]")).size()>0){
			click(getDriver().findElement(By.xpath("(//span[contains(text(),'"+view+"')])[2]")),"My Open Opportunities");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(text(),'"+view+"')]")),"My Open Opportunities");	
		}
		
		return this;
	}
	public MemberFormPage verifyOppurtunityCreated(String oppurtunity) {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//a//span[contains(text(),'"+oppurtunity+"')]")).size(), "Oppurtunity");
		return this;
	}


	public MemberFormPage openOppurtunityCreated(String oppurtunity) {
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//a//span[contains(text(),'"+oppurtunity+"')]"))).doubleClick().build().perform();

		return this;
	}

	public MemberFormPage clickmarkasLost() {
		click(getDriver().findElement(By.xpath("//button[@data-id='opportunity|NoRelationship|Form|Mscrm.Form.opportunity.MarkAsLost']")),"click marks as lost");	
		return this;
	}


	//Enter Start Date as Today's Date in Account Numbers
	public MemberFormPage typeStartDateInAccountNumbers() {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String startdate= dateFormat.format(date);
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of Start Date']")),"Start Date"); 
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of Start Date']")),"Start Date");
		type(getDriver().findElement(By.xpath("//*[@aria-label='Date of Start Date']")),startdate,"Start Date"); 
		return this;
	}


	
	//Select Prescription Data
		public MemberFormPage VerifyMembershipCategoriesisNotDisplayed() throws InterruptedException {
			Thread.sleep(2000);
			if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
				click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
			}else {
				click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
			}
			Thread.sleep(3000);
			List<WebElement> options= getDriver().findElements(By.xpath("//*[text()='Membership Categories']"));
			verifyElementisNotDisplayed(options.size(), "Membership Categories is not displayed");
			//click(getDriver().findElement(By.xpath("//*[contains(text(),'')]")),"Rebate Payments");
			Thread.sleep(2000);
			return this;
		}
}





