package pages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.AddHasCasting;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import dev.failsafe.internal.util.Assert;
import driver.Driver;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

public class ContractsPage extends WebDriverServiceImpl {

	public static String CRMNumber;

	// Click New on contacts page

	//Change the view
	public ContractsPage changeTheContractView(String view) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//div[@title='Select a view']")),"Select a View");
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+view+"')]")),"Select a view");
		Thread.sleep(4000);
		return this;
	}

	//click New button
	public ContractsPage clickNewContractButton() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractcontact|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contractcontact.AddNewStandard']")),"Add new Contract");

		return this;
	}


	//verify end date are old
	public ContractsPage verifyOlderEndDate() throws InterruptedException, ParseException {
		List<WebElement> endDate=getDriver().findElements(By.xpath("//div[@col-id='ix_contractend' and @role='gridcell']//label/div"));
		List<String> dates=new ArrayList<String>();
		for(int i=1;i<endDate.size();i++) {
			dates.add(getDriver().findElement(By.xpath("(//div[@col-id='ix_contractend' and @role='gridcell']//label/div)["+i+"]")).getText());
		}
		String todayDate=TestUtils.todaysDate();
		for(String date:dates) {
			assertTrue(TestUtils.compareDate(date,todayDate )<0);
		}

		return this;
	}

	//add new Contract
	public ContractsPage addNewContract(String CotnactName, String ContactType) throws InterruptedException, ParseException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_contact.fieldControl-LookupResultsDropdown_ix_contact_textInputBox_with_filter_new']")),"ContactName");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_contact.fieldControl-LookupResultsDropdown_ix_contact_textInputBox_with_filter_new']")))),CotnactName,"CotnactName");
		//Thread.sleep(120000);
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+CotnactName+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+CotnactName+"')]")),"Direct Parent");

		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_contractcontacttype.fieldControl-option-set-select']")), ContactType, "ContactType");

		return this;
	}

	//Click New Contract Button
	public ContractsPage clickaddContract(String contractNumber, String crmNumber) throws InterruptedException, ParseException {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_number.fieldControl-text-box-text']")),contractNumber,"Contract number");
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_vendor.fieldControl-LookupResultsDropdown_ix_vendor_textInputBox_with_filter_new']")),"Vendor");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_vendor.fieldControl-LookupResultsDropdown_ix_vendor_textInputBox_with_filter_new']")),crmNumber,"Vendor");
		//Thread.sleep(120000);
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+crmNumber+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+crmNumber+"')]")),"Direct Parent");
		return this;
	}

	//Click New Contract Button
	public ContractsPage clicknewContractAttahcment(String member, String contract, String AttachmentStatus, String AttachmentStatusDate) throws InterruptedException, ParseException {

		click(getDriver().findElement(By.xpath("//input[@data-id='ix_accountid.fieldControl-LookupResultsDropdown_ix_accountid_textInputBox_with_filter_new']")),"Member");

		Thread.sleep(2000);

		type(getDriver().findElement(By.xpath("//input[@data-id='ix_accountid.fieldControl-LookupResultsDropdown_ix_accountid_textInputBox_with_filter_new']")),member,"Member");
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+member+"')]")));


		return this;	
	}
	//input[@data-id='ix_accountid.fieldControl-LookupResultsDropdown_ix_accountid_textInputBox_with_filter_new']

	public ContractsPage verifyContaactType() {
		ArrayList<String> selectoptions=new ArrayList<String>();
		Select contactType= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_contractcontacttype.fieldControl-option-set-select']")));		
		List<String> expectedRepresentativeType = Arrays.asList("Attachment","Audit","Legal","Membership","Price Activation Responder");		

		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> contactTypelist =contactType.getOptions();	

		for(int i=0;i<contactTypelist.size();i++) {

			selectoptions.add(contactTypelist.get(i).getText());

		}
		assertTrue(expectedRepresentativeType.containsAll(selectoptions));
		return this;
	}

	//Click save button
	public ContractsPage clickSave() throws InterruptedException, ParseException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contract|NoRelationship|Form|Mscrm.Form.ix_contract.Save']")),"Save Button");
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//li[@title='History']/preceding-sibling::li[@title='Tier Enhancements']"))));
		return this;
	}

	//Click New Contract Button
	public ContractsPage enterNewContracttdetails() throws InterruptedException, ParseException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contract|NoRelationship|Form|Mscrm.Form.ix_contract.NewRecord']")),"New Contract");
		return this;
	} 



	//input[@data-id='ix_number.fieldControl-text-box-text']

	//Click Save and Close Button
	public ContractsPage clickSaveAndClose() throws InterruptedException, ParseException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractcontact|NoRelationship|Form|Mscrm.Form.ix_contractcontact.SaveAndClose']")),"Save and Close");
		return this;
	}

	//Verify Error message is not displauyed
	public ContractsPage verifyErrorisNotDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> error=getDriver().findElements(By.xpath("//*[@data-id='errorDialog_subtitle']"));
		verifyElementisNotDisplayed(error.size(), "Error message");
		Thread.sleep(2000);
		return this;
	}

	//Double Click on First CA in All ContractAttachmentsView
	public ContractsPage openFirstContract() {
		Actions a=new Actions(getDriver());
		a.doubleClick(getDriver().findElement(By.xpath("//input[@aria-label='Select or deselect the row']"))).build().perform();;
		return this;

	}

	//Open Contract Contact
	public ContractsPage navigateToContractContact() throws InterruptedException {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		click(getDriver().findElement(By.xpath("(//*[text()='Contract Contacts'])")),"Contract Contact");
		Thread.sleep(3000);

		return this;
	}


	//Open Contract Contact
	public ContractsPage addnewContractButton() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contract|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.ix_contract.NewRecord']")),"Add New Contract Button");

		return this;
	}

	//Click Search button in ContractManager

	public ContractsPage clickSearchbuttoninContractManager() throws InterruptedException {


		click(getDriver().findElement(By.xpath("//input[@data-id='ix_contractmanager.fieldControl-LookupResultsDropdown_ix_contractmanager_textInputBox_with_filter_new']")),"Member");

		Thread.sleep(2000);
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//button[@data-id='ix_contractmanager.fieldControl-LookupResultsDropdown_ix_contractmanager_search']"))).click().build().perform();
		return this;

	}

	//Select Contract Manage
	public ContractsPage searchcontractManagerInAdvanceLookup(String ContactID) throws InterruptedException {
		clickSearchbuttoninContractManager();
		clickAdvanceLookupAndSearch(ContactID);


		return this;

	}

	//Click new contact button
	public ContractsPage AddNewContractMAnager() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractmanager.fieldControl-LookupResultsDropdown_ix_contractmanager_addNewBtnContainer']")),"Add New Contract Manager");
		return this;
	}
	//Click Advance Lookup

	public ContractsPage clickAdvanceLookupAndSearch(String ContactID) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//a[@data-id='ix_contractmanager.fieldControl-LookupResultsDropdown_ix_contractmanager_advancedLookupBtnContainer']")),"Advance Lookup");
		WebDriverWait wait= new WebDriverWait(getDriver(), Duration.ofSeconds(10000));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//input[contains(@aria-labelledby,'advanced')and contains(@id,'SearchBox')]"))));

		click(getDriver().findElement(By.xpath("//input[contains(@aria-labelledby,'advanced')and contains(@id,'SearchBox')]")),"Search in Advance Lookup");
		type(getDriver().findElement(By.xpath("//input[contains(@aria-labelledby,'advanced')and contains(@id,'SearchBox')]")), ContactID,"ContactID");
		wait= new WebDriverWait(getDriver(), Duration.ofSeconds(10000));
		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@col-id='ix_innovatixcontactid']//label[@aria-label='"+ContactID+"']"))));
		click(getDriver().findElement(By.xpath("//div[@col-id='ix_innovatixcontactid']//label[@aria-label='"+ContactID+"']")),"ContactID");
		click(getDriver().findElement(By.xpath("//button[@title='Done']")),"Done button");

		return this;

	}

	//Verify Contract Manager is not null
	public ContractsPage verifyContractManagerisNotnull() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-id='ix_contractmanager.fieldControl-LookupResultsDropdown_ix_contractmanager_selected_tag']")).size(), "Contact Name");
		return this;
	}

	//clear contract manager
	public ContractsPage clearContractManager() {
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='ix_contractmanager.fieldControl-LookupResultsDropdown_ix_contractmanager_SelectedRecordList']"))).build().perform();
		action.moveToElement(getDriver().findElement(By.xpath("//button[@data-id='ix_contractmanager.fieldControl-LookupResultsDropdown_ix_contractmanager_selected_tag_delete']"))).click().build().perform();
		return this;
	}


	//Add New Contact
	public ContractsPage createnewContact(String firstName, String SecondName, String primaryAccount) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@data-id='firstname.fieldControl-text-box-text']")),firstName, "First NAme");
		type(getDriver().findElement(By.xpath("//input[@data-id='lastname.fieldControl-text-box-text']")),SecondName, "Last NAme");

		click(getDriver().findElement(By.xpath("//input[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_textInputBox_with_filter_new']")),"Primary Contact");
		type(getDriver().findElement(By.xpath("//input[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_textInputBox_with_filter_new']")),primaryAccount, "Parent ID");
		WebDriverWait wait= new WebDriverWait(getDriver(), Duration.ofSeconds(10000));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//div[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_infoContainer']"))));

		click(getDriver().findElement(By.xpath("//div[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_infoContainer']")),"Parent Account");
		click(getDriver().findElement(By.xpath("//button[@data-id='quickCreateSaveAndCloseBtn']")),"Click save and Close");
		Thread.sleep(5000);

		return this;
	}

	//Verify Tire Enhancement
	public ContractsPage verifyTierEnchancement() {

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//li[@title='History']/preceding-sibling::li[@title='Tier Enhancements']")).size(), "Tier Enchancement");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//li[@title='Tier Descriptions']/following-sibling::li[@title='Tier Enhancements']")).size(), "Tier Enchancement");
		return this;
	}

	//Click Tier Enhancement

	public ContractsPage clickTierEnhancement() {

		click(getDriver().findElement(By.xpath("//li[@title='Tier Enhancements']")),"Tier Enchancement");

		return this;
	}
	//Verify New Tier Enhancement button

	public ContractsPage verifyNewTierEnhancementButton() {

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_contracttierenhancement|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_contracttierenhancement.AddNewStandard']")).size(), "Tier Enchancement");

		return this;
	}

	//click New Tier enhancement button
	public ContractsPage clickNewTierEnhancementButton() throws InterruptedException {
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contracttierenhancement|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_contracttierenhancement.AddNewStandard']")),"New Tier Enhancement button");
		return this;
	}

	//Verify New Tier Enhanement Page
	public ContractsPage veriyfNewTierEnhancementPage() {

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//li[@aria-label='General']")).size(), "General Tab");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//li[@aria-label='General']/following-sibling::li[@aria-label='System']")).size(), "System Tab");

		return this;

	}

	//Verify New Tier Enhanement Page
	public ContractsPage verifyGeneralTabinNewTierEnhancement(String sponsor, String entityCode) {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@title='Sponsor Name']")).size(), "Sponsor Name");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@title='Tier Enhancement']")).size(), "Tier Enhancement");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@title='Doc Requirements']")).size(), "Docs REquirements");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@title='Contract']")).size(), "Contract");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@title='GPO Designation Form']")).size(), "GPO Designation Form");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@title='Account Number']")).size(), "Account Number");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@title='COT Eligibility']")).size(), "COT Eligibility");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@title='Notes']")).size(), "Notes");
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_sponsor.fieldControl-LookupResultsDropdown_ix_sponsor_textInputBox_with_filter_new']")),"Sponsor");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_sponsor.fieldControl-LookupResultsDropdown_ix_sponsor_textInputBox_with_filter_new']")),sponsor,"Sponsor");

		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+sponsor+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+sponsor+"')]")),"Sponsor");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[contains(text(),'Sponsor Entity Code')]")).size(), "Sponsor Entity code");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//input[@data-id='sponsorentitycode.ix_premierein.fieldControl-text-box-text' and contains(@value,'"+entityCode+"')]")).size(), "Sponsor Entity code");

		return this;

	}	

	//Click Tab
	public ContractsPage clickTab(int number)  throws InterruptedException {
		for(int i=0;i<number;i++) {
			Actions a =new Actions(getDriver());
			a.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(3000);
		}
		return this;
	}


	//Verify Tier Enhancement fields in General Tab
	public ContractsPage verifyTierEnhancementFieldLength(String sponsor, String tierEnhancement, String cotEligibility, String entityCode) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_sponsor.fieldControl-LookupResultsDropdown_ix_sponsor_textInputBox_with_filter_new']")),"Sponsor");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_sponsor.fieldControl-LookupResultsDropdown_ix_sponsor_textInputBox_with_filter_new']")),sponsor,"Sponsor");

		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+sponsor+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+sponsor+"')]")),"Sponsor");

		Thread.sleep(4000);

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[contains(text(),'Sponsor Entity Code')]")).size(), "Sponsor Entity code");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//input[@data-id='sponsorentitycode.ix_premierein.fieldControl-text-box-text' and contains(@value,'"+entityCode+"')]")).size(), "Sponsor Entity code");

		type(getDriver().findElement(By.xpath("//input[@aria-label='Tier Enhancement']")),tierEnhancement,"tierEnhancement");
		clickTab(2);
		String tierEnhancementvalue=getDriver().findElement(By.xpath("//input[@aria-label='Tier Enhancement']")).getAttribute("title");
		assertTrue(tierEnhancementvalue.contentEquals(tierEnhancement));

		verifyDocRequirementsDropdown();
		verifyGPODesignationDropdown();
		verifyAccountNumberDropdown();

		type(getDriver().findElement(By.xpath("//input[@data-id='ix_coteligibility.fieldControl-text-box-text']")),cotEligibility,"cotEligibility");
		String cotEligibilityvalue=getDriver().findElement(By.xpath("//input[@aria-label='Tier Enhancement']")).getAttribute("title");
		assertTrue(tierEnhancementvalue.contentEquals(tierEnhancement));


		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[@data-id='ix_contract.fieldControl-LookupResultsDropdown_ix_contract_SelectedRecordList']")).size(), "Selected Record List");

		return this;
	}

	public ContractsPage verifyGPODesignationDropdown() throws InterruptedException {


		Select RepresentativeType= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_gpodesignationform.fieldControl-option-set-select']")));		
		// Create Expected Array List
		List<String> expectedRepresentativeType = Arrays.asList("---","Yes","No");		
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



	public ContractsPage verifyAccountNumberDropdown() throws InterruptedException {


		Select RepresentativeType= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_accountnumber.fieldControl-option-set-select']")));		
		// Create Expected Array List
		List<String> expectedRepresentativeType = Arrays.asList("---","Yes","No");		
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

	public ContractsPage verifyDocRequirementsDropdown() throws InterruptedException {


		Select RepresentativeType= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_docrequirements.fieldControl-option-set-select']")));		
		// Create Expected Array List
		List<String> expectedRepresentativeType = Arrays.asList("---","Yes","No");		
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

	//Verify Contract field is auto fields
	public ContractsPage verifyContractFieldisNotNull() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[@data-id='ix_contract.fieldControl-LookupResultsDropdown_ix_contract_SelectedRecordList']")).size(), "Selected Record List");
		return this;
	}

	//Click save and close button
	public ContractsPage clickSaveAndCloseInContractTierEnhancement() {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contracttierenhancement|NoRelationship|Form|Mscrm.Form.ix_contracttierenhancement.SaveAndClose']")),"Save and Close Button");
		return this;
	}

	//Verify Tier enhancement is displayed
	public ContractsPage verifyTierEnhancementCreated(String sponsor) { 
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@aria-label='"+sponsor+"']")).size(), "Sponsor");
		return this;
	}


	//Click Addition records

	//Verify Add Contact Tier Enchancement Button Displayed under see additional Record option
	public ContractsPage clickAdditionalRecords() {
		click(getDriver().findElement(By.xpath("//button[@data-id='OverflowButton' and @aria-label='More commands for Contract Tier Enhancement']")),"Overflow Button");
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//span[contains(text(),'See associated records')]"))).click().build().perform();

		return this;
	}


	//Verify Add Contact Tier Enchancement Button Displayed under see additional Record option
	public ContractsPage verifyAddContactTierEnchancementButtonDisplayed() { 
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_contracttierenhancement|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contracttierenhancement.AddNewStandard']")).size(), "Sponsor");
		return this;
	}

	//Verify Add Contact Tier Enchancement Button is not Displayed under see additional Record option
	public ContractsPage verifyAddContactTierEnchancementButtonDisplayedisNotDisplayed() { 
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_contracttierenhancement|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contracttierenhancement.AddNewStandard']")).size(), "Contract Tier Enhancement");
		return this;
	}

	//Verify creation fields are not available for Member/Member supervisor/Supplier/Supplier supervisor
	public ContractsPage verifyReadonlyFields(){
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_contract|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.ix_contract.NewRecord']")).size(), "Selected Record List");
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[contains(@class,'ag-cell ag-cell-not-inline-editing ag-cell') and @col-id='ix_number']"))).doubleClick().build().perform();
		//Click Tier Enhancement button is displayed
		clickTierEnhancement();
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_contracttierenhancement|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_contracttierenhancement.AddNewStandard']")).size(), "Tier Enchancement");
		clickAdditionalRecords();
		verifyAddContactTierEnchancementButtonDisplayedisNotDisplayed();
		return this;
	}

	public ContractsPage verifyContractTierEnhancementFields(){
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-testid='ix_sponsor']//div[contains(text(),'Sponsor Name')]")).size(), "Sponsor");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-testid='columnHeader']//div[contains(text(),'Entity Code (Sponsor Name)')]")).size(), "Entity Code");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-testid='columnHeader']//div[contains(text(),'Tier Enhancement')]")).size(), "Tier Enhancement");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-testid='columnHeader']//div[contains(text(),'Doc Requirement')]")).size(), "Doc Requirement");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-testid='columnHeader']//div[contains(text(),'GPO Designation Form')]")).size(), "GPO Designation Form");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-testid='columnHeader']//div[contains(text(),'COT Eligibility')]")).size(), "COT Eligibility");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-testid='columnHeader']//div[contains(text(),'Account Number')]")).size(), "Account Number");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-testid='columnHeader']//div[contains(text(),'Notes')]")).size(), "Notes");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-testid='columnHeader']//div[contains(text(),'Created On')]")).size(), "Created On");
		
		return this;
	}
	
	

}


