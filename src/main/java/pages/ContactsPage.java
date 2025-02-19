package pages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
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
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import dev.failsafe.internal.util.Assert;
import driver.Driver;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

public class ContactsPage extends WebDriverServiceImpl {

	public static String CRMNumber;

	// Click New on contacts page

	public ContactsPage clickNewOnContactsPage() {

		if(getDriver().findElements(By.xpath("//*[@data-id='contact|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.contact.NewRecord']")).size()>0) {
			click(getDriver().findElement(
					By.xpath("//*[@data-id='contact|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.contact.NewRecord']")),
					"New");

		}

		if(getDriver().findElements(By.xpath("//*[@data-id='contact|NoRelationship|SubGridAssociated|Mscrm.SubGrid.contact.AddNewStandard']")).size()>0) {
			click(getDriver().findElement(By.xpath("//*[@data-id='contact|NoRelationship|SubGridAssociated|Mscrm.SubGrid.contact.AddNewStandard']")),"New button");
		}
		return this;
	}


	//select existing contact
	public ContactsPage selectExistingContact(String CRMNumber) throws InterruptedException {
		Thread.sleep(3000);
		// Click Drop down
		click(getDriver().findElement(By.xpath("//span/i[@data-icon-name='ChevronDown']")),"Drop down");
		click(getDriver().findElement(By.xpath("//span[contains(text(),'All Contacts')]")), "All Contacts");
		Thread.sleep(10000);
		Thread.sleep(15000);
		WebDriverWait wait= new WebDriverWait(getDriver(),Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//*[contains(@id,'quickFind_text')]"))));
		typeAndEnter(getDriver().findElement(By.xpath("//*[contains(@id,'quickFind_text')]")),CRMNumber,"Find Criteria" );
		Thread.sleep(5000);
		return this;

	}

	//Select Contact from search results
	public  ContactsPage selectAccountFromContactSearchResults() throws InterruptedException {	

		Thread.sleep(3000);
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("(//div[contains(@col-id,'accountnumber')]//label)[2]"))).doubleClick().build().perform();
		Thread.sleep(6000);
		return this;
	}	

	//verify Contact End date
	public ContactsPage verifyContactEndDate() throws InterruptedException {
		Thread.sleep(3000);
		verifyDisplayed(getDriver().findElement(By.xpath("//div[contains(text(),'Contact End Date')]")), "Contact End Date");
		return this;
	}
	//select view type

	public ContactsPage selectAllContactView() throws InterruptedException {
		Thread.sleep(3000);
		// Click Drop down -01/09/2024
		click(getDriver().findElement(By.xpath("//*[contains(@id,'ViewSelector')]/span/i[@data-icon-name='ChevronDown']")),"Drop down");
		Thread.sleep(3000);
		//Wave2 Update
		click(getDriver().findElement(By.xpath("//label[text()='All Contacts']")), "All Contacts");
		Thread.sleep(10000);
		return this;

	}
	
	
	public ContactsPage selectViewAtContact(String View) throws InterruptedException {
		Thread.sleep(3000);
		
		if(!(getDriver().findElement(By.xpath("//span[contains(@id,'text-value')]")).getText().contentEquals(View))) {
		// Click Drop down -01/09/2024
		click(getDriver().findElement(By.xpath("//*[contains(@class,'DropdownArrow-symbol')]")),"Drop down");
		Thread.sleep(3000);
		//Wave2 Update
		click(getDriver().findElement(By.xpath("//label[text()='"+View+"']")), "All Contacts");
		Thread.sleep(10000);
		}
		return this;

	}
	// type the contact first name and last name
	public ContactsPage typeContactName(String firstName, String lastName) throws InterruptedException {
		Thread.sleep(3000);
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String executiondate = dateFormat.format(date);
		// Type first name
		click(getDriver().findElement(
				By.xpath("//*[@data-id='fullname_compositionLinkControl_firstname.fieldControl-text-box-text']"))
				,"First Name");
		Thread.sleep(2000);
		type(((getDriver().findElement(
				By.xpath("//*[@data-id='fullname_compositionLinkControl_firstname.fieldControl-text-box-text']")))),
				firstName, "First Name");

		// Type second name
		click(getDriver().findElement(
				By.xpath("//*[@data-id='fullname_compositionLinkControl_lastname.fieldControl-text-box-text']")),
				"Last Name");
		Thread.sleep(2000);
		if(lastName.isEmpty()) {
		type(((getDriver().findElement(
				By.xpath("//*[@data-id='fullname_compositionLinkControl_lastname.fieldControl-text-box-text']")))),
				lastName, "Last Name");
		}else {
			type(((getDriver().findElement(
					By.xpath("//*[@data-id='fullname_compositionLinkControl_lastname.fieldControl-text-box-text']")))),
					lastName+ "_" + executiondate, "Last Name");	
		}
		return this;
	}

	// select the primary account
	public ContactsPage selectPrimaryAccount(String primaryAccount) throws InterruptedException {
		
		click(getDriver().findElement(By.xpath("//input[@data-id='telephone1.fieldControl-phone-text-input']")),"Telephone");
		click(getDriver().findElement(By.xpath("//input[@data-id='emailaddress1.fieldControl-mail-text-input']")),"Email address");
		click(getDriver().findElement(By.xpath("//input[@data-id='mobilephone.fieldControl-phone-text-input']")),"Mobile Phone");
		click(getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_search']")),"Primary Account");
		type(((getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_textInputBox_with_filter_new']")))),primaryAccount, "Primary Account");
		//Thread.sleep(55000);

		//		Fluent Wait
		//		 Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
		//			       .withTimeout(Duration.ofSeconds(90L))
		//			       .pollingEvery(Duration.ofSeconds(10L))
		//			       .ignoring(NoSuchElementException.class ,ElementNotInteractableException.class);
		//
		//			   WebElement primaryAccountOnContact = wait.until(new Function<WebDriver, WebElement>() {
		//			     public WebElement apply(WebDriver driver) {
		//			       return getDriver().findElement(By.xpath("//*[contains(@id,'parentcustomerid.fieldControl-name0_0_0')]"));
		//			     }
		//			   });
		//		click(primaryAccountOnContact,primaryAccount);

		// Explicit Wait- 8/23/2023
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'parentcustomerid.fieldControl-name0_0_0')]")));
		click(getDriver().findElement(By.xpath("//*[contains(@id,'parentcustomerid.fieldControl-name0_0_0')]")),primaryAccount);
		return this;
	}


	//Select Primary Account through Search Icon

	public ContactsPage selectPrimaryAccountByClickingOnSearchLensIcon(String pa) throws InterruptedException {
		clickTab(4);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Department')]")),"Department");		
		click(getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_search']")),"Primary Account Search Icon");
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_infoContainer']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_infoContainer']/span[contains(@data-id,'parentcustomerid.fieldControl-name0_0_')]"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 1; i <=rowList.size(); i++) {
			String primaryacc = getDriver().findElement(By.xpath("(//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_infoContainer']/span[contains(@data-id,'parentcustomerid.fieldControl-name0_0_')])["+i+"]")).getText();
			System.out.println(primaryacc);
			if (primaryacc.equals(pa)) {
				WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(80));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'parentcustomerid.fieldControl-name0_0_')]")));
				click(getDriver().findElement(By.xpath("(//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_infoContainer']/span[contains(@data-id,'parentcustomerid.fieldControl-name0_0_')])["+i+"]")),pa);
				break;				
			}
		}
		return this;
	}

	// Click Save button in contact summary page
	public ContactsPage clickSave() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='contact|NoRelationship|Form|Mscrm.Form.contact.Save']")),
				"Save");
		Thread.sleep(5000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		clickSaveDuplicateDetected();
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}

	// Click Save button in contact summary page
	public ContactsPage clickSaveWithoutAssertion() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='contact|NoRelationship|Form|Mscrm.Form.contact.Save']")),
				"Save");
		Thread.sleep(3000);
		return this;
	}

	// check if Innovatix contact id is created
	public ContactsPage isInnovatixContactIDDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		String innovatixContactID = getAttribute(
				getDriver().findElement(By.xpath("//*[@data-id='ix_innovatixcontactid.fieldControl-text-box-text']")),
				"value", "Innovatix Contact Id");

		if (innovatixContactID==null) {
			setReport().log(Status.FAIL, "Value is blank in Innovatix Contact ID"	,screenshotCapture());
			Driver.failCount++;		
		}
		else {

			setReport().log(Status.PASS, "Innovatix Contact ID " + innovatixContactID + " is displayed",
					screenshotCapture());
		}
		CRMNumber=innovatixContactID;
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='ix_innovatixcontactid.fieldControl-text-box-text']")), "Innovatix Contact ID");
		try {
			DataInputProvider.setCellData(innovatixContactID.toString(), Driver.iTestCaseRowNumDriver, "CRMNumber",
					Driver.properties.getProperty("DriverSheetName"));
		} catch (Exception e) {
			e.printStackTrace();
		}


		return this;
	}

	// choose record status
	public ContactsPage chooseRecordStatus(String recordStatus) throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")),
				"Record Status");
				Thread.sleep(5000);
		//click(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published status");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+recordStatus+"')]")),"Contact published");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),recordStatus,"Record Status");
		Thread.sleep(2000);
		return this;

	}

	// verify contact record status
	public ContactsPage verifyRecordStatus(String recordStatus) throws InterruptedException {
		String recordStatusinUI = getAttribute(
				getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")),
				"title", "Record Status");
		if (recordStatusinUI.equalsIgnoreCase(recordStatus)) {

			setReport().log(Status.PASS, "Record Status " + recordStatusinUI + " is displayed right",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL, "Record Status " + recordStatusinUI + " is not displayed right",
					screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}

	// verify contact record status
	public ContactsPage verifyRecordChangeStatus(String recordChangeStatus) throws InterruptedException {
		String recordStatusinUI = getAttribute(
				getDriver().findElement(By.xpath("//*[@data-id='ix_recordchangestatus.fieldControl-option-set-select']")),
				"title", "Record Change Status");
		if (recordStatusinUI.equalsIgnoreCase(recordChangeStatus)) {

			setReport().log(Status.PASS, "Record Change Status " + recordStatusinUI + " is displayed as expected",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL, "Record Change Status " + recordStatusinUI + " is NOT displayed as expected",
					screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}
	//Select Record change status
	public ContactsPage selectRecordChangeStatus(String status) {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_recordchangestatus.fieldControl-option-set-select']")),status,"Record Change Status");
		return this;
	}

	// Click on Activate button on CAA
	public ContactsPage clickOnCAAActivate() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contactaccountassociation|NoRelationship|Form|Mscrm.Form.ix_contactaccountassociation.Activate']")), "Activate");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")), "Confirm Activate Button");
		return this;
	}

	// Click on Activate button on Job Function
	public ContactsPage clickOnJobFunctionActivate() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contactjobfunction|NoRelationship|Form|Mscrm.Form.ix_contactjobfunction.Activate']")), "Activate");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")), "Confirm Activate Button");
		return this;
	}

	// Click on Activate button on Job Function
	public ContactsPage clickOnCommunicationPublicationActivate() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contactcommunication|NoRelationship|Form|Mscrm.Form.ix_contactcommunication.Activate']")), "Activate");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")), "Confirm Activate Button");
		return this;
	}



	// Click on deactivate button
	public ContactsPage clickOnContactDeactivate() throws InterruptedException {
		click(getDriver().findElement(
				By.xpath("//*[@data-id='contact|NoRelationship|Form|Mscrm.Form.contact.Deactivate']")), "Deactivate");
		Thread.sleep(10000);
		return this;
	}

	// Confirm deactivate button
	public ContactsPage clickConfirmDeactivation() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")), "Confirm");
		Thread.sleep(10000);
		return this;
	}

	// Click save after terminating CAA
	public ContactsPage clickSaveAfterTerminationCAA() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath(
				"//*[@data-id='ix_contactaccountassociation|NoRelationship|Form|Mscrm.Form.ix_contactaccountassociation.Save']")),
				"Save");
		Thread.sleep(5000);
		return this;
	}

	// type the contact end date in the summary page
	public ContactsPage typeContactEndDate(String contactEndDate) throws InterruptedException {
		Thread.sleep(500);
		// alternate action for scrolling down to contact end date
		click(getDriver().findElement(By.xpath("//*[@data-id='fullname_compositionLinkControl_lastname.fieldControl-text-box-text']")),
				"Last Name");
		click(getDriver().findElement(By.xpath("//input[@data-id='telephone1.fieldControl-phone-text-input']")),"Telephone");
		click(getDriver().findElement(By.xpath("//input[@data-id='emailaddress1.fieldControl-mail-text-input']")),"Email address");
		click(getDriver().findElement(By.xpath("//input[@data-id='mobilephone.fieldControl-phone-text-input']")),"Mobile Phone");
		clickTab(3);
		click(getDriver().findElement(By.xpath("//*[@data-id='jobtitle.fieldControl-text-box-text']")), "Job Title");
		clickTab(4);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_employeeflag.fieldControl-option-set-select']")),
				"Employee flag");
		clickTab(3);
		// click contact end date field twice and then type value to it
		click((getDriver().findElement(By.xpath("//*[@aria-label='Date of Contact End Date']"))),"Contact End Date");
		click((getDriver().findElement(By.xpath("//*[@aria-label='Date of Contact End Date']"))),"Contact End Date");
		type(getDriver().findElement(By.xpath("//*[@aria-label='Date of Contact End Date']")),contactEndDate, "Contact End Date");
		return this;
	}

	//Click Tab
	public ContactsPage clickTab(int number)  throws InterruptedException {
		for(int i=0;i<number;i++) {
			Actions a =new Actions(getDriver());
			a.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(3000);
		}
		return this;
	}


	// choose Receive All Communications on CAA
	public ContactsPage chooseReceiveAllCommunicationsOnCAA(String receiveAllComm) throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_receiveallcommunications.fieldControl-checkbox-select']")),
				"Record Status");
		selectDropDownUsingVisibleText(
				((getDriver().findElement(By.xpath("//*[@data-id='ix_receiveallcommunications.fieldControl-checkbox-select']")))),
				receiveAllComm, "Receive All Communications");
		Thread.sleep(3000);
		//			verifyExactText(getDriver().findElement(By.xpath("//*[contains(@id,'ix_receiveallcommunications.fieldControl-checkbox-select')][@value='"+receiveAllComm+"']")),
		//					receiveAllComm, "Receive All Communications");
		return this;

	}


	//Choose Communication termination 

	public ContactsPage chooseCommunicationReason(String communicatioTermination) throws InterruptedException {
		Thread.sleep(3000);
		selectDropDownUsingVisibleText(
				((getDriver().findElement(By.xpath("//select[@data-id='ix_terminationreason.fieldControl-option-set-select']")))),
				communicatioTermination, "Communications Termination Reason");
		return this;

	}



	// Click back arrow across all pages
	public ContactsPage clickGoBack() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Go back']")), "Go Back");
		Thread.sleep(6000);
		return this;
	}

	// Click summary tab
	public ContactsPage clickSummaryTab() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@data-id='tablist-SUMMARY_TAB']")), "Summary tab");
		Thread.sleep(5000);
		return this;
	}

	// Click Address Details
	public ContactsPage clickAddresDetails() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='ADDRESS DETAILS']")), "ADDRESS DETAILS");
		Thread.sleep(2000);
		return this;
	}

	// Click Address Details
	public ContactsPage fillAddressOnContact(String street1 ,String city, String zipcode ) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")),street1, "Street 1 on ADDRESS DETAILS");
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']")), city,"City on ADDRESS DETAILS");
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']")),zipcode, "Zip Code on ADDRESS DETAILS");
		Thread.sleep(2000);
		return this;
	}

	// perform page refresh
	public ContactsPage pageRefresh() throws InterruptedException {
		getDriver().navigate().refresh();
		Thread.sleep(5000);
		return this;
	}



	//Refresh CAA
	public ContactsPage refreshCAA() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[contains(@id,'View') and contains(text(),'Active Contact Account Associations')]")).size(), "Current Account Representative view");
		Thread.sleep(7000);
		click(getDriver().findElement(By.xpath("//button[@data-lp-id='SubGridStandard:ix_contactaccountassociation-ix_contactaccountassociation|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_contactaccountassociation.RefreshButton']")), "Refresh button");
		Thread.sleep(7000);
		return this;


	}
	// to choose an existing Active contact
	public ContactsPage chooseActiveContact(String CrmNumber1) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[contains(@data-id,'quickFind_text')]")), "Find Criteria");
		typeAndEnter(getDriver().findElement(By.xpath("//*[contains(@data-id,'quickFind_text')]")), CrmNumber1,
				"Find Criteria");
		Thread.sleep(6000);
		//Actions a = new Actions(getDriver());
		//Wave2 Update
		//a.moveToElement(getDriver().findElement(By.xpath("//span[contains(@class,'RowSelectionCheckMarkSpan')]//i[@data-icon-name='StatusCircleCheckmark']"))).doubleClick().build().perform();
		//a.moveToElement(
		doubleClick(getDriver().findElement(By.xpath("(//*[@data-icon-name='CheckMark'])[2]")),"Check Mark");
		Thread.sleep(6000);
		return this;
	}

	//go back and select the account

	// to choose an existing Active contact
	public ContactsPage goBackandSelectAccount() throws InterruptedException {
		Thread.sleep(3000);
		clickGoBack();
		Actions a = new Actions(getDriver());
		//Wave2 Update 
		//a.moveToElement(getDriver().findElement(By.xpath("//span[contains(@class,'RowSelectionCheckMarkSpan')]//i[@data-icon-name='StatusCircleCheckmark']"))).doubleClick().build().perform();
		a.moveToElement(getDriver().findElement(By.xpath("//input[@aria-label='Select or deselect the row']"))).doubleClick().build().perform();
		Thread.sleep(6000);
		return this;
	}
	// update primary account with a new account details
	public ContactsPage addAnotherPrimaryAccount(String PrimaryAccount1) throws InterruptedException {
		Thread.sleep(6000);
		click(getDriver().findElement(By.xpath("//input[@data-id='suffix.fieldControl-text-box-text']")),"Suffix textbox");
		click(getDriver().findElement(By.xpath("//input[@data-id='fullname_compositionLinkControl_firstname.fieldControl-text-box-text']")),"Fullname textbox");
		click(getDriver().findElement(By.xpath("//input[@data-id='fullname_compositionLinkControl_middlename.fieldControl-text-box-text']")),"Middle name textbox");
		click(getDriver().findElement(By.xpath("//input[@data-id='fullname_compositionLinkControl_lastname.fieldControl-text-box-text']")),"last name");
		click(getDriver().findElement(By.xpath("//input[@data-id='telephone1.fieldControl-phone-text-input']")),"Phone text box");
		click(getDriver().findElement(By.xpath("//input[@data-id='emailaddress1.fieldControl-mail-text-input']")),"email text box");
		click(getDriver().findElement(By.xpath("//input[@data-id='mobilephone.fieldControl-phone-text-input']")),"Phone text box");
		clickTab(3);
		clearPrimaryAccount();
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_textInputBox_with_filter_new']")),"Primary Contact");
		type(((getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_textInputBox_with_filter_new']")))),PrimaryAccount1, "Primary Account");
		//Thread.sleep(6000);
		// Explicit Wait- 8/23/2023
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'parentcustomerid.fieldControl-name0_0_0')]")));
		click(getDriver().findElement(By.xpath("//*[contains(@id,'parentcustomerid.fieldControl-name0_0_0')]")),PrimaryAccount1);
		//click(getDriver().findElement(By.xpath("//*[contains(@id,'parentcustomerid.fieldControl-name0_0_0')]")),PrimaryAccount1);
		return this;
	}

	
	public ContactsPage clearPrimaryAccount() throws InterruptedException {
		
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_selected_tag_text']"))).build().perform();
		click(getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_selected_tag_delete']")),"Delete"); 
		Thread.sleep(3000);
		
		return this;
	}
	
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Contact Account Association~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

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
		return this ;
	}

	// Click Add Contact Account Association
	public ContactsPage clickAddCAA() throws InterruptedException {
		clickCAAFromRelated();
		click(getDriver().findElement(By.xpath(
				"//*[@data-id='ix_contactaccountassociation|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contactaccountassociation.AddNewStandard']")),
				"Add");
		Thread.sleep(5000);
		return this;
	}

	// type Account in CAA
	public ContactsPage typeAccountInCAA(String account) throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_account.fieldControl-LookupResultsDropdown_ix_account_textInputBox_with_filter_new']")),"Account");
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_account.fieldControl-LookupResultsDropdown_ix_account_textInputBox_with_filter_new']")),account, "Account");
		List<WebElement> accountid=getDriver().findElements(By.xpath("//span[contains(@data-id,'ix_account.fieldControl-accountnumber')]//span[contains(text(),'"+account+"')]"));
		if(accountid.size()>0) {
			click(getDriver().findElement(By.xpath("//span[contains(@data-id,'ix_account.fieldControl-accountnumber')]//span[contains(text(),'"+account+"')]")),account);
		}else {
			click(getDriver().findElement(By.xpath("//*[contains(@id,'ix_account.fieldControl-name0_0_0')]")),account);
		}
		return this;
	}

	// click save in CAA 
	public ContactsPage clickSaveInCAA() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contactaccountassociation|NoRelationship|Form|Mscrm.Form.ix_contactaccountassociation.Save']")),
				"Save");
		Thread.sleep(5000);
		return this;
	}

	// click save & Close in CAA 
	public ContactsPage clickSaveAndCloseInCAA() throws InterruptedException {
		if(getDriver().findElements(By.xpath("//*[@data-id='ix_contactaccountassociation|NoRelationship|Form|Mscrm.Form.ix_contactaccountassociation.SaveAndClose']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@data-id='ix_contactaccountassociation|NoRelationship|Form|Mscrm.Form.ix_contactaccountassociation.SaveAndClose']")),
					"Save");
		}
		Thread.sleep(5000);
		return this;
	}


	// choose contact termination reason
	public ContactsPage chooseContactTerminationReason(String contactTerminationReason) throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_terminationreason.fieldControl-option-set-select']")),"Termination Reason");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'pa-option-set-component')]/div/div[contains(text(),'"+contactTerminationReason+"')]")),"Termination Reason");
		Thread.sleep(3000);
		Thread.sleep(3000);
		return this;
	}
	// verify Warning Notification bar
	public ContactsPage verifyWarningNotification(String warningNotification) throws InterruptedException {
		Thread.sleep(6000);
		verifyExactText(((getDriver().findElement(By.xpath("//*[@data-id='warningNotification']")))),warningNotification, "Warning Notification");
		Thread.sleep(3000);
		return this;
	}

	// provide contact relationship end date 
	public ContactsPage typeContactRelationshipEndDate() {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String enddate = dateFormat.format(date);
		click(((getDriver().findElement(By.xpath("//input[@aria-label='Date of Relationship End Date']")))),"Relationship End Date");
		type(((getDriver().findElement(By.xpath("//*[@aria-label='Date of Relationship End Date']")))),enddate, "Relationship End Date");
		return this;
	}

	// Selecting Inactive view under CAA views
	public ContactsPage clickInactiveCAAView() throws InterruptedException {
		Thread.sleep(3000);
		if (getDriver().findElement(By.xpath("//span[contains(text(),'Contact Account Association Associated View')]"))
				.isDisplayed()) {
			click(getDriver()
					.findElement(By.xpath("//span[contains(text(),'Contact Account Association Associated View')]")),
					"Contact Account Association Associated View");
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//*[contains(text(),'Inactive Contact Account Associations')]")),
					"Inactive Contact Account Associations");
			Thread.sleep(5000);
		}
		return this;
	}

	// validate the relationship end date has current date under CAA Inactive view
	public ContactsPage verifyCAARelationEndDateInInactiveView() throws InterruptedException, ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String curDate = formatter.format(date);
		System.out.println(curDate);

		Thread.sleep(3500);
		//Wave2 Update
		//Wave 2023 -1
		String endDate = getTextValue(getDriver().findElement(By.xpath("(//*[@col-id='ix_relationshipenddate'])[2]//label")),"Relationship End Date in Contact Account Association Inactive View");
		Date diffdate = formatter.parse(endDate);
		String finalDate = formatter.format(diffdate);
		System.out.println(finalDate);

		if (finalDate.equalsIgnoreCase(curDate)) {

			setReport().log(Status.PASS, "Relationship End Date in Contact Account Association Inactive View "
					+ finalDate + " matches with the value : "+ curDate, screenshotCapture());

		} else {
			setReport().log(Status.FAIL, "Relationship End Date in Contact Account Association Inactive View "
					+ finalDate + " does not match with the value :"+curDate, screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}

	// double click on CAA record in CAA Inactive view
	public ContactsPage doubleClickOnCAARecordInactiveView() throws InterruptedException {
		Thread.sleep(3000);
		//Actions a = new Actions(getDriver());
		//Wave2 update
		//Wave 2023 1 update
		//a.moveToElement(
		doubleClick(getDriver().findElement(By.xpath("//label[text()='Yes']"))," CAA Inactive View");
		Thread.sleep(3000);
		return this;
	}

	// Select Sub grid view under CAA views
	public ContactsPage clickContactSubGridViewInCAA() throws InterruptedException {
		Thread.sleep(3000);
		if (getDriver().findElement(By.xpath("//span[contains(text(),'Contact Account Association Associated View')]"))
				.isDisplayed()) {
			click(getDriver()
					.findElement(By.xpath("//span[contains(text(),'Contact Account Association Associated View')]")),
					"Contact Account Association Associated View");
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//*[contains(text(),'Contact Subgrid View')]")),
					"Contact Subgrid View");
			Thread.sleep(3000);
		}
		return this;
	}

	// verify CAA termination reason in Subgrid view
	public ContactsPage verifyCAATerminationReasonInSubGridView(String terminationReason) {
		//Wave2 Update aria-label
		String termReason = getAttribute(getDriver().findElement(By.xpath("(//div[@col-id='ix_terminationreason']//label)[2]")), "aria-label",
				"Termination Reason in Contact Account Association Sub Grid View");
		if (termReason.equalsIgnoreCase(terminationReason)) {

			setReport().log(Status.PASS, "Termination Reason in Contact Account Association Sub Grid View " + termReason
					+ " is displayed as expected", screenshotCapture());

		} else {
			setReport().log(Status.FAIL, "Termination Reason in Contact Account Association Sub Grid View " + termReason
					+ " is NOT displayed as expected", screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}

	// validate CAA relation end date under subgrid view
	public ContactsPage verifyCAARelationEndDateInSubGridView(String relatioshipEndDate) {
		//Wave2 Update
		String endDate = getAttribute(getDriver().findElement(By.xpath("(//div[@col-id='ix_relationshipenddate']//label)[2]")), "aria-label",
				"Relationship End Date in Contact Account Association Sub Grid View");
		System.out.println(endDate);
		if (endDate.equalsIgnoreCase(relatioshipEndDate)) {

			setReport().log(Status.PASS, "Relationship End Date in Contact Account Association Sub Grid View " + endDate
					+ " is displayed as expected " + relatioshipEndDate  ,  screenshotCapture());

		} else {
			setReport().log(Status.FAIL, "Relationship End Date in Contact Account Association Sub Grid View " + endDate
					+ " is NOT displayed as expected" + relatioshipEndDate, screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}

	// double click on the entry under CAA subgrid view
	public ContactsPage doubleClickOnCAARecordInSubGridView() throws InterruptedException {
		//Actions a = new Actions(getDriver());
		//Wave2 update
		//a.moveToElement(
		doubleClick(getDriver().findElement(By.xpath("(//i[@data-icon-name='CheckMark'])[2]")),"CAA Rec");
		Thread.sleep(3000);
		return this;
	}

	//Navigate to Contact subgrid view
	public ContactsPage NavigatetoContactSubgridView() throws InterruptedException {

		List<WebElement> viewDropdown = getDriver().findElements(By.xpath("(//div[@title='Select a view'])[2]"));
		if(viewDropdown.size()>0) {
			click(getDriver().findElement(By.xpath("(//div[@title='Select a view'])[2]")),"select view");
		}else {
			click(getDriver().findElement(By.xpath("//div[@title='Select a view']")),"select view");
		}
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Contact Subgrid View')]")),"select view");
		Thread.sleep(5000);

		return this;	
	}
	// double click on the CAA record
	public ContactsPage doubleClickOnCAARecord() throws InterruptedException {
		clickCAAFromRelated();

		Actions a = new Actions(getDriver());
		//a.moveToElement(getDriver().findElement(By.xpath("//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']//i[@data-icon-name='StatusCircleCheckmark']"))).doubleClick().build().perform();
		//Wave2 Update
		Thread.sleep(2000);
		a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='grid-container']//div[@col-id='a_87bc6554cf2fe91180f1005056b97654.ix_innovatixcontactid']//label[contains(@class,'ms-Label labelRootStyles')]"))).doubleClick().build().perform();
		Thread.sleep(3000);
		return this;
	}
	
	//Double Click CAA under Member
	public ContactsPage doubleClickOnCAARecordUnderMember() throws InterruptedException {
		
		Actions a = new Actions(getDriver());
		
		a.moveToElement(getDriver().findElement(By.xpath("//*[@col-id='ix_contact']//a/ancestor::div[contains(@class,'ag-row-even')]//input"))).doubleClick().build().perform();
		Thread.sleep(3000);
		
		return this;
		
	}
	
	//dobule clickCAA under Member
	
	
	// double click on the CAA record
	public ContactsPage doubleClickOnCAARecordunderContact(String lastname) throws InterruptedException {
		clickCAAFromRelated();
		NavigatetoContactSubgridView();
		Actions a = new Actions(getDriver());
		//a.moveToElement(getDriver().findElement(By.xpath("//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']//i[@data-icon-name='StatusCircleCheckmark']"))).doubleClick().build().perform();
		//Wave2 Update
		Thread.sleep(2000);
		System.out.println("((//span[contains(text(),'"+lastname+"')]//preceding::div[@col-id='__row_status']//div[contains(@class,'ms-Checkbox is-enabled RowSelectionCheckMarkSpan')])[2]");
		List<WebElement> checkmark=getDriver().findElements(By.xpath("(//span[contains(text(),'"+lastname+","+"')]//preceding::div[@col-id='__row_status']//div[contains(@class,'ms-Checkbox is-enabled RowSelectionCheckMarkSpan')])"));
		a.moveToElement(getDriver().findElement(By.xpath("(//span[contains(text(),'"+lastname+","+"')]//preceding::div[@col-id='__row_status']//div[contains(@class,'ms-Checkbox is-enabled RowSelectionCheckMarkSpan')])["+checkmark.size()+"]"))).doubleClick().build().perform();
		Thread.sleep(3000);
		return this;
	}

	// save the detected duplicate record
	public ContactsPage clickSaveDuplicateDetected() {
		try {
			String duplicateText=null ;
			Thread.sleep(5000);
			if( getDriver().findElements(By.xpath("//*[@data-id='manageDuplicatesTitle']")).size()>0) {
				duplicateText = getDriver().findElement(By.xpath("//*[@data-id='manageDuplicatesTitle']")).getText();
				if (duplicateText.equalsIgnoreCase("Duplicate records found")) {
					click(getDriver().findElement(By.xpath("//*[@data-id='ignore_save']")), "Ignore and Save");
					Thread.sleep(10000);
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		try {
			if (getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")).isDisplayed()) {
				click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"SCRIPT ERROR");
			}
		}
		catch(Exception e){
		}
		return this;
	}

	// Verify if status of CAA is Active/Inactive under Administration tab
	public ContactsPage verifyStatusInCAA(String status) throws InterruptedException {
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//li[contains(text(),'Administration')]")), "Administration tab");

		String conRecordStatus = getAttribute(getDriver().findElement(By.xpath("//*[@aria-label='Status']")), "value",
				"Status under Admin tab ");
		if (conRecordStatus.equalsIgnoreCase(status)) {

			setReport().log(Status.PASS, "Status under Admin tab ' " + conRecordStatus + " 'is displayed right",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL, "Status under Admin tab ' " + conRecordStatus + " ' is not displayed right",
					screenshotCapture());
			Driver.failCount++;
		}

		return this;
	}

	// Verify if error msg is displayed for duplicate CAA
	public ContactsPage verifyErrorMsgForDuplicateCAA(String ErrMsg) throws InterruptedException {
		verifyPartialText(getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']")), ErrMsg,
				"Error Message");
		Thread.sleep(3000);
		return this;
	}

	// Click ok on the duplicate CAA error msg popup
	public ContactsPage clickOkErrorMsgForDuplicateCAA() throws InterruptedException {
		//Wave2 Update
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")), "OK");
		Thread.sleep(3000);
		return this;
	}

	// double click on the old CAA record
	public ContactsPage doubleClickOnOldCAARecord(String OldCAA) throws InterruptedException {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//li[@title='Contact Account Associations']")).size()==0) {
			click(getDriver().findElement(By.xpath("//*[@title='Related']")), "Related");
			Thread.sleep(3000);
			click(getDriver().findElement(By.xpath("//li[@title='Contact Account Associations']")),
					"Contact Account Associations");
		}else {
			click(getDriver().findElement(By.xpath("//*[contains(text(),'Contact Account Associations')]")),
					"Contact Account Associations");
		}
				
		Thread.sleep(2000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@tabindex='-1'][@aria-colindex='2']//div//label"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 2; i <=rowList.size(); i++) {
			String title = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@tabindex='-1'][@aria-colindex='2']//div//label)["+i+"]")).getText();
			System.out.println(title);
			if (title.equals(OldCAA)) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@tabindex='-1'][@aria-colindex='2']//div//label)["+i+"]")), OldCAA);
				Thread.sleep(3000);
				break;				
			}
		}		

		return this;					
	}

	// double click on the old CAA record
	public ContactsPage verifyNewCAA(String PrimaryAccount) throws InterruptedException {

		Thread.sleep(2000);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@role='row' and contains(@class,'ag-row-no-focus')]//div[contains(@class,'ms-TooltipHost') and contains(text(),'"+PrimaryAccount+"')]")).size(), "Primary Account");

		return this;					
	}

	// double click on the old CAA record
	public ContactsPage verifyNewCAAContactname(String PrimaryAccount) throws InterruptedException {
		Actions actions =new Actions(getDriver());	
		actions.moveToElement(getDriver().findElement(By.xpath("//div[@role='row' and contains(@class,'ag-row-no-focus')]//div[contains(@class,'ms-TooltipHost') ]//span[contains(text(),'"+PrimaryAccount+"')]"))).build().perform();
		actions.moveToElement(getDriver().findElement(By.xpath("//input[@aria-label='Select or deselect the row']"))).doubleClick().build().perform();
		return this;
	}

	// choose contact termination reason
	public ContactsPage verifyNullinCaaTerminationReason(String contactTerminationReason) throws InterruptedException {
		Thread.sleep(3000);
		String terminationReason = getAttribute(
				getDriver()
				.findElement(By.xpath("//*[@data-id='ix_terminationreason.fieldControl-option-set-select']")),
				"title", "Termination Reason");		

		if(terminationReason.equalsIgnoreCase(contactTerminationReason))
		{

			setReport().log(Status.PASS, "Termination reason under CAA is blank",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL, "Termination reason under CAA is not blank",
					screenshotCapture());
			Driver.failCount++;
		}
		Thread.sleep(3000);

		return this;
	}

	// provide contact relationship end date
	public ContactsPage verifyNullinCaaRelationshipEndDate(String contactRelationshipEndDate) throws InterruptedException {
		Thread.sleep(3000);
		String relEndDate = getAttribute(getDriver().findElement(By.xpath("//input[contains(@id,'DateControlPrefix')]")),
				"placeholder", "Relationship End Date");		

		if(relEndDate.equalsIgnoreCase(contactRelationshipEndDate))
		{

			setReport().log(Status.PASS, "Contact Relationship EndDate under CAA is blank",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL, "Contact Relationship EndDate under CAA is not blank",
					screenshotCapture());
			Driver.failCount++;
		}
		Thread.sleep(3000);
		return this;
	}



	// Verify CAA Relation end date is not blank
	public ContactsPage verifyCaaRelationshipEndDateIsNotNull(String relationshipEndDate) throws InterruptedException {
		Thread.sleep(3000);
		String relEndDate = getAttribute(
				getDriver()
				.findElement(By.xpath("//*[@data-id='ix_relationshipenddate.fieldControl-date-time-input']")),
				"value", "Relationship End Date");		

		assertTrue(relEndDate.contains(relationshipEndDate), relationshipEndDate+" is not displayed");
		Thread.sleep(3000);
		return this;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Job Function~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	

	// Click Related and Contact Job Function
	public ContactsPage clickContactJobFunctionFromRelated() throws InterruptedException {
		Thread.sleep(7000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")), "Related");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Contact Job Functions')]")),"Contact job function");

		return this;
	}

	// Click Related and Contact Job Function and New Contact Job Function
	public ContactsPage clickAddJobFunction() throws InterruptedException {
		clickContactJobFunctionFromRelated();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath(
				"//*[@data-id='ix_contactjobfunction|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contactjobfunction.AddNewStandard']")),
				"New Contact Job Function");
		return this;
	}

	// Verify Business Process Error Message
	public ContactsPage verifyErrorMessage(String errormessage) throws InterruptedException {
		Thread.sleep(5000);
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']")),errormessage,"Job Function Error Message");
		Thread.sleep(3000);
		return this;	
	}

	// Account is in draft message should not be displayed
	public ContactsPage VerifyErrorMessageNotDisplayed() throws InterruptedException {
		Thread.sleep(2000);

		List<WebElement> message=getDriver().findElements(By.xpath("//*[@data-id='errorDialog_subtitle']"));

		verifyElementisNotDisplayed(message.size(), "error message");
		return this;
	}

	//Click OK on Error Message
	public ContactsPage clickOKOnErrorMessage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"OK");
		Thread.sleep(5000);
		return this;	
	}

	//Click On Discard Changes
	public ContactsPage clickDiscardChanges() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Discard Changes");
		Thread.sleep(5000);
		return this;	
	}
	//click save button in Job Function without Script Error Check
	public ContactsPage clickSaveInJobFunctionWithoutScriptError() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contactjobfunction|NoRelationship|Form|Mscrm.Form.ix_contactjobfunction.Save']")),"Save");
		Thread.sleep(10000);
		//Duplicate Job Function Ignore and Save
		try {
			if (getDriver().findElement(By.xpath("//*[@data-id='ignore_save']")).isDisplayed()) {
				click(getDriver().findElement(By.xpath("//*[@data-id='ignore_save']")),"Duplicate Job Function- Ignore and Save");
			}
		}
		catch(Exception e){

		}
		return this;}
	
	//click Ok button in error message
	public ContactsPage clickOkInErrormessage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"OK");
		return this;
	}
	
	//Select Contact from search results
	public  ContactsPage deactivateContactAllContactsView() throws InterruptedException {	
		Thread.sleep(4000);
		Actions a = new Actions(getDriver());
		//a.moveToElement(getDriver().findElement(By.xpath("//span[contains(@class,'RowSelectionCheckMarkSpan')]//i[@data-icon-name='StatusCircleCheckmark']"))).click().build().perform();
		a.moveToElement(getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']"))).click().build().perform();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Deactivate']")),"Deactivate");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")), "Confirm");
		Thread.sleep(10000);
		return this;
	}	
	// Provide value for Job function as provided in the datasheet
	public ContactsPage typeJobFunction(String jobFunction) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//*[@data-id='ix_jobfunctionnew.fieldControl-LookupResultsDropdown_ix_jobfunctionnew_textInputBox_with_filter_new']"))));
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_jobfunctionnew.fieldControl-LookupResultsDropdown_ix_jobfunctionnew_textInputBox_with_filter_new']")),"Job Function");
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_jobfunctionnew.fieldControl-LookupResultsDropdown_ix_jobfunctionnew_textInputBox_with_filter_new']")),jobFunction, "Job Function");
		Thread.sleep(5000);
		/*
		 * Actions a =new Actions(getDriver()); Thread.sleep(2000);
		 * a.sendKeys(Keys.TAB).perform(); Thread.sleep(2000);
		 * a.sendKeys(Keys.TAB).perform(); Thread.sleep(2000);
		 * a.sendKeys(Keys.ENTER).perform();
		 */
		click(getDriver().findElement(By.xpath("//span[contains(@class,'pa') and contains(text(),'"+jobFunction+"')]")),"Bid Propsal Team");
		return this;
	}

	public ContactsPage typeJobFunction1(String jobFunction) throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_jobfunctionnew.fieldControl-LookupResultsDropdown_ix_jobfunctionnew_textInputBox_with_filter_new']")),"Job Function");
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_jobfunctionnew.fieldControl-LookupResultsDropdown_ix_jobfunctionnew_textInputBox_with_filter_new']")),jobFunction, "Job Function");
		return this;
	}

	
	public ContactsPage clickSavebuttonInJobFunction() throws InterruptedException {
		click(getDriver().findElement(By.xpath(
				"//*[@data-id='ix_contactjobfunction|NoRelationship|Form|Mscrm.Form.ix_contactjobfunction.Save']")),
				"Save");
		Thread.sleep(7000);
		return this;
	}
	// Click save button in the job function page
	public ContactsPage clickSaveInJobFunction() throws InterruptedException {
		click(getDriver().findElement(By.xpath(
				"//*[@data-id='ix_contactjobfunction|NoRelationship|Form|Mscrm.Form.ix_contactjobfunction.Save']")),
				"Save");
		Thread.sleep(7000);
		try {
			if (getDriver().findElements(By.xpath("//*[@data-id='errorOkButton']")).size()>0) {
				click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"OK");
			}
		}
		catch(Exception e){

		}

		try {
			if(getDriver().findElements(By.xpath("//span[text()='Ignore and save']")).size()>0);
			click(getDriver().findElement(By.xpath("//span[text()='Ignore and save']")),"Ignore and Save");	
		} catch (Exception e) {
			e.getMessage();
		}

		return this;
	}

	// Click save& close button in the job function page
	public ContactsPage clickSaveAndCloseInJobFunction() throws InterruptedException {
		click(getDriver().findElement(By.xpath(
				"//*[@data-id='ix_contactjobfunction|NoRelationship|Form|Mscrm.Form.ix_contactjobfunction.SaveAndClose']")),
				"Save & Close");
		Thread.sleep(7000);
		try {
			if (getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")).isDisplayed()) {
				click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"OK");
			}
		}
		catch(Exception e){

		}

		try {
			if(getDriver().findElement(By.xpath("//span[text()='Ignore and save']")).isDisplayed());
			click(getDriver().findElement(By.xpath("//span[text()='Ignore and save']")),"Ignore and Save");	
		} catch (Exception e) {
			e.getMessage();
		}

		return this;
	}

	// Click save& close button in the Contact Communications Page
	public ContactsPage clickSaveAndCloseInCommunicationPublications() throws InterruptedException {
		click(getDriver().findElement(By.xpath(
				"//*[@data-id='ix_contactcommunication|NoRelationship|Form|Mscrm.Form.ix_contactcommunication.SaveAndClose']")),
				"Save & Close");
		Thread.sleep(3000);
		try {
			if (getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")).isDisplayed()) {
				click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"OK");
			}
		}
		catch(Exception e){

		}

		try {
			if(getDriver().findElement(By.xpath("//span[text()='Ignore and save']")).isDisplayed());
			click(getDriver().findElement(By.xpath("//span[text()='Ignore and save']")),"Ignore and Save");	
		} catch (Exception e) {
			e.getMessage();
		}

		return this;
	}

	//Deactivate all communications
	public ContactsPage deactivateAllCommunications() throws InterruptedException {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//input[@aria-label='Toggle selection of all rows']"))).click().build().perform();
		if(getDriver().findElements(By.xpath("//button[@data-id='ix_contactcommunication|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contactcommunication.Deactivate']")).size()>0) {
			click(getDriver().findElement(By.xpath("//button[@data-id='ix_contactcommunication|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contactcommunication.Deactivate']")),"Deactivate");
			click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Click Ok");
			Thread.sleep(7000);
		}
		return this;
	}
	//Click Log out
	public LoginPage clickLogout()
	{
		click(getDriver().findElement(By.xpath("//*[@id='mectrl_headerPicture']")),"User Name button");
		click(getDriver().findElement(By.xpath("//button[contains(text(),'Sign out')]")),"Sign Out button");
		if(getDriver().findElements(By.xpath("//span[contains(text(),'Discard changes')]")).size()>0) {
			click(getDriver().findElement(By.xpath("//span[contains(text(),'Discard changes')]")),"Discard button");
		}

		return new LoginPage();
	}
	//Click on summary tab
	public ContactsPage clickSummaryTab(String jobFunction) throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@title='Summary']")), "Summary");
		Thread.sleep(5000);
		return this;
	}

	// Verify if job function is created by validating the created date with the
	// current date
	public ContactsPage verifyJobFunctionIsCreated(String jobFunction) throws InterruptedException, ParseException {

		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Administration']")), "Administration");
		Thread.sleep(2000);

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String curDate = formatter.format(date);

		//Thread.sleep(3000);
		//Explicit wait 08/25/2023
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(getDriver().findElement(By.xpath("//*[@aria-label='Date of Created On']")),"value"));
		String dateInUI = getAttribute(getDriver().findElement(By.xpath("//*[@aria-label='Date of Created On']")), "value","Created On");
		Date diffdate = formatter.parse(dateInUI);
		String finalDate = formatter.format(diffdate);

		Thread.sleep(4000);
		if (finalDate.equalsIgnoreCase(curDate)) {
			setReport().log(Status.PASS, "An entry is added with current date " + finalDate, screenshotCapture());

		} else {
			setReport().log(Status.FAIL, "No entry is added with current date " + finalDate, screenshotCapture());
			Driver.failCount++;

		}
		clickGoBack();
		clickGoBack();
		return this;
	}

	// double click the created Job function entry from view
	public ContactsPage doubleClickOnActiveJobFunction() throws InterruptedException {
		Thread.sleep(3000);
		//Actions a = new Actions(getDriver());
		//Wave2 Update
		//a.moveToElement(
		doubleClick(getDriver().findElement(By.xpath("//div[text()='Active']")),"Job Func");
		Thread.sleep(3000);
		return this;
	}

	// double click Active Communication Publication  from view
	public ContactsPage doubleClickOnActiveCommunicationPublication() throws InterruptedException {
		Thread.sleep(3000);
		doubleClick(getDriver().findElement(By.xpath("//div[text()='Active']")),"Comm/Pub");
		Thread.sleep(3000);
		return this;
	}

	// double click the Inactive Job function entry from view
	public ContactsPage doubleClickOnInActiveJobFunction() throws InterruptedException {
		Thread.sleep(3000);
		doubleClick(getDriver().findElement(By.xpath("//div[text()='Inactive']")),"Job Func");
		Thread.sleep(3000);
		return this;
	}

	// double click Inactive Communication Publication entry from view
	public ContactsPage doubleClickOnInActiveCommunicationPublication() throws InterruptedException {
		Thread.sleep(3000);
		doubleClick(getDriver().findElement(By.xpath("//div[text()='Inactive']")),"Communication Publication");
		Thread.sleep(3000);
		return this;
	}

	// verify if user is able to create duplicate job function
	public ContactsPage verifyDuplicateJobFunctionIsCreated(String jobFunction) throws InterruptedException {
		clickGoBack();

		//		String jobFunc1 = getAttribute(getDriver().findElement(By.xpath("(//div[contains(@class,'ag-row-')]//div[@col-id='ix_jobfunctionnew']//a)[1]")), "title",
		//				"Job Function");
		//
		//		String jobFunc2 = getAttribute(getDriver().findElement(By.xpath("(//div[contains(@class,'ag-row-')]//div[@col-id='ix_jobfunctionnew']//a)[2]")), "title",
		//				"Job Function");
		Thread.sleep(5000);
		String jobFunc1 = getTextValue(getDriver().findElement(By.xpath("//*[@col-id ='ix_jobfunctionnew']//span")), "Job Function");

		String jobFunc2 = getTextValue(getDriver().findElement(By.xpath("(//*[@col-id ='ix_jobfunctionnew']//span)[2]")),"Job Function");


		System.out.println(jobFunc1);
		System.out.println(jobFunc2);

		if (jobFunc1.equalsIgnoreCase(jobFunc2) && jobFunc1.equalsIgnoreCase(jobFunction)) {

			setReport().log(Status.PASS, "Duplicate job function created " + jobFunction, screenshotCapture());
			System.out.println("Duplicate job function created " + jobFunction);

		} else {
			setReport().log(Status.FAIL, "Duplicate job function is not created " + jobFunction, screenshotCapture());
			Driver.failCount++;
			System.out.println("Duplicate job function is not created " + jobFunction);
		}

		try {
			if (getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")).isDisplayed()) {
				click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"SCRIPT ERROR");
			}
		}
		catch(Exception e){
		}

		return this;
	}

	// Verify if job function is in terminated status
	public ContactsPage verifyJobFunctionTerminationReason(String terminationReason) {
		String jobFunction = getAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_terminationreason.fieldControl-option-set-select']")),
				"title", "Termination Reason");
		if (jobFunction.equalsIgnoreCase(terminationReason)) {
			setReport().log(Status.PASS,
					"Termination reason for the job Function  ' " + jobFunction + " ' matches with expected reason",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL,
					"Termination reason for the job Function ' " + jobFunction + " ' does not match with expected reason",
					screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}


	// Verify if job function is in terminated status in subgrid
	public ContactsPage verifyJobFunctionTerminationReasoninSubgrid(String terminationReason) {
		String jobFunction = getAttribute(
				getDriver()
				.findElement(By.xpath("(//div[contains(@id,'ContactJobFunctionSubgrid-pcf_grid_control_container')]//div[@col-id='ix_terminationreason']//label)[2]")),
				"aria-label", "Termination Reason");
		if (jobFunction.equalsIgnoreCase(terminationReason)) {
			setReport().log(Status.PASS,
					"Termination reason for the job Function  ' " + jobFunction + " ' matches with expected reason",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL,
					"Termination reason for the job Function ' " + jobFunction + " ' does not match with expected reason",
					screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}


	// Verify if job function is in terminated status in subgrid
	public ContactsPage verifyCommunicationTerminationReasoninSubgrid(String terminationReason) {
		String jobFunction = getAttribute(
				getDriver()
				.findElement(By.xpath("(//div[contains(@id,'ContactCommunicationSubgrid-pcf_grid_control_container')]//div[@col-id='ix_terminationreason'])[2]//label")),
				"aria-label", "Termination Reason");
		if (jobFunction.equalsIgnoreCase(terminationReason)) {
			setReport().log(Status.PASS,
					"Termination reason for the job Function  ' " + jobFunction + " ' matches with expected reason",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL,
					"Termination reason for the job Function ' " + jobFunction + " ' does not match with expected reason",
					screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}


	// verify if correct error msg is displayed when providing balnk value for the
	// job function
	public ContactsPage verifyErrorInJobFunction(String errorInJobFunction) {
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_jobfunctionnew-error-message']")),
				errorInJobFunction, "Error in Job Function");
		return this;
	}

	// Click CAA record from job function page
	public ContactsPage clickCAArecordFromJobFunction() throws InterruptedException {
		//		click(getDriver().findElement(By.xpath(
		//				"//*[@data-id='ix_contactaccountassociationid.fieldControl-LookupResultsDropdown_ix_contactaccountassociationid_selected_tag_text']")),
		//				"CAA record");
		//Wave 2023 update
		Thread.sleep(3000);
		//doubleClick(getDriver().findElement(By.xpath("//*[@data-icon-name='CheckMark']")),"CAA on Job Func");
		doubleClick(getDriver().findElement(By.xpath("//*[@col-id='statecode']//label[@aria-label='Active']")),"CAA on Job Func");
		Thread.sleep(4000);
		return this;
	}

	// Selecting Inactive view under Job function
	public ContactsPage clickInactiveJobFunctionView() throws InterruptedException {
		Thread.sleep(3000);
		if (getDriver().findElement(By.xpath("//span[contains(text(),'Contact Job Function Associated View')]"))
				.isDisplayed()) {
			click(getDriver().findElement(By.xpath("//span[contains(text(),'Contact Job Function Associated View')]")),
					"Contact Job Function Associated View");
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//*[contains(text(),'Inactive Contact Job Functions')]")),
					"Inactive Contact Job Functions");
			Thread.sleep(3000);
		}
		return this;
	}

	// verify Job function termination reason in Inactive view
	public ContactsPage verifyJobFunctTerminationReasonInInactiveView(String terminationReason) {
		//Wave2 Update aria-label
		String termReason = getTextValue(getDriver().findElement(By.xpath("(//*[@col-id='ix_terminationreason'])[2]//label")),"Termination Reason in Inactive Contact Job Function View");
		if (termReason.equalsIgnoreCase(terminationReason)) {

			setReport().log(Status.PASS,
					"Termination Reason in Inactive Contact Job Function View ' " + termReason + "  ' matches the expected reason",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL, "Termination Reason in Inactive Contact Job Function View ' " + termReason
					+ " does NOT  match the expected reason", screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}

	// verify Job function status in Inactive view
	public ContactsPage verifyJobFunctStatusInInactiveView(String jobStatus) {
		//Wave2 Update aria-label
		String status = getTextValue(getDriver().findElement(By.xpath("(//div[@col-id='statecode'])[2]//label")),"Status in Inactive Contact Job Function View");
		if (status.equalsIgnoreCase(jobStatus)) {

			setReport().log(Status.PASS,
					"Status in Inactive Contact Job Function View " + status + " is displayed right",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL,
					"Status in Inactive Contact Job Function View " + status + " is not displayed right",
					screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Communication/Publication~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	

	// Click Related and Contact Communication
	public ContactsPage clickContactCommunicationFromRelated() throws InterruptedException {
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")), "Related");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Contact Communications')]")),
				"Contact Communications");
		return this;
	}

	// Click Related and Contact Communication and New Contact Communication
	public ContactsPage clickAddContactCommunication() throws InterruptedException {
		clickContactCommunicationFromRelated();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath(
				"//*[@data-id='ix_contactcommunication|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contactcommunication.AddNewStandard']")),
				"New Contact Communications");
		return this;
	}

	// Provide value for Contact Communication as provided in the datasheet
	public ContactsPage typeContactCommunication(String contactCommunication) throws InterruptedException {
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath(	"//*[@data-id='ix_communicationpublication.fieldControl-LookupResultsDropdown_ix_communicationpublication_textInputBox_with_filter_new']")),"Contact Communication Publication");
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_communicationpublication.fieldControl-LookupResultsDropdown_ix_communicationpublication_textInputBox_with_filter_new']")),contactCommunication, "Contact Communication Publication");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[contains(@id,'ix_communicationpublication.fieldControl-createdon1_0_0')]")),contactCommunication);
		return this;
	}

	// Click save button in the Contact Communication page Without Script Error
	public ContactsPage clickSaveInContactCommunicationWithoutScriptError() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contactcommunication|NoRelationship|Form|Mscrm.Form.ix_contactcommunication.Save']")),"Save");
		Thread.sleep(5000);
		return this;
	}


	// Click save button in the Contact Communication page Without Script Error
	public ContactsPage clickSaveAndCloseInContactCommunicationWithoutScriptError() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contactcommunication|NoRelationship|Form|Mscrm.Form.ix_contactcommunication.SaveAndClose']")),"Save and Save");
		Thread.sleep(5000);
		return this;
	}


	// Provide value for Contact Communication as provided in the datasheet
	public ContactsPage typeContactCommunication1(String contactCommunication) throws InterruptedException {
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath(	"//*[@data-id='ix_communicationpublication.fieldControl-LookupResultsDropdown_ix_communicationpublication_textInputBox_with_filter_new']")),"Contact Communication Publication");
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_communicationpublication.fieldControl-LookupResultsDropdown_ix_communicationpublication_textInputBox_with_filter_new']")),contactCommunication, "Contact Communication Publication");
		return this;
	}

	// Click save button in the Contact Communication page
	public ContactsPage clickSaveInContactCommunication() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath(
				"//*[@data-id='ix_contactcommunication|NoRelationship|Form|Mscrm.Form.ix_contactcommunication.Save']")),
				"Save");
		Thread.sleep(5000);
		try {
			if (getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")).isDisplayed()) {
				click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"SCRIPT ERROR");
			}
		}
		catch(Exception e){
		}
		return this;
	}

	// Verify if Contact Communication is created by validating the created date
	// with the current date
	public ContactsPage verifyContactCommunicationIsCreated() throws InterruptedException, ParseException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='ADMINISTRATION']")), "ADMINISTRATION");
		Thread.sleep(2000);

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String curDate = formatter.format(date);
		//Thread.sleep(3000);
		//Explicit wait -08/25/2023
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(getDriver().findElement(By.xpath("//*[@aria-label='Date of Created On']")),"value"));
		String dateInUI = getAttribute(getDriver().findElement(By.xpath("//*[@aria-label='Date of Created On']")), "value","Created On");
		Date diffdate = formatter.parse(dateInUI);
		String finalDate = formatter.format(diffdate);
		Thread.sleep(4000);
		if (finalDate.equalsIgnoreCase(curDate)) {
			setReport().log(Status.PASS, "An entry is added with current date " + finalDate, screenshotCapture());
		} else {
			setReport().log(Status.FAIL, "No entry is added with current date " + finalDate, screenshotCapture());
			Driver.failCount++;
		}
		Thread.sleep(2000);
		clickGoBack();
		clickGoBack();
		return this;
	}

	// double click the created Contact Communication entry from view
	public ContactsPage doubleClickOnContactCommunication() throws InterruptedException {
		Thread.sleep(3000);
		//Actions a = new Actions(getDriver());
		//Wave2 Update
		//a.moveToElement( 
		//Wave 2023
		//doubleClick(getDriver().findElement(By.xpath("//*[@data-icon-name='CheckMark']")),"CAA on Comm/Pub");
		doubleClick(getDriver().findElement(By.xpath("(//*[@col-id='statecode']//label)[2]")),"CAA on Comm/Pub");
		//doubleClick(getDriver().findElement(By.xpath("//*[@col-id='statecode']//label[@aria-label='Read only']")),"CAA on Comm/Pub");

		Thread.sleep(3000);
		return this;
	}

	// verify if user is able to create duplicate Contact Communication
	public ContactsPage verifyDuplicateContactCommunicationIsCreated(String communicationPublication)
			throws InterruptedException {
		clickGoBack();
		//String commPubli1 = getAttribute(getDriver().findElement(By.xpath("//*[@data-id='cell-0-3']/a")), "title",
		//		"Communication Publication");

		//String commPubli2 = getAttribute(getDriver().findElement(By.xpath("//*[@data-id='cell-1-3']/a")), "title",
		//		"Communication Publication");
		Thread.sleep(3000);
		String commPubli1 = getTextValue(getDriver().findElement(By.xpath("//*[@col-id ='ix_communicationpublication']//span")),"Communication Publication");


		String commPubli2 = getTextValue(getDriver().findElement(By.xpath("(//*[@col-id ='ix_communicationpublication']//span)[2]")),"Communication Publication");

		System.out.println(commPubli1);
		System.out.println(commPubli2);


		if (commPubli1.equalsIgnoreCase(commPubli2) && commPubli1.equalsIgnoreCase(communicationPublication)) {

			setReport().log(Status.PASS, "Duplicate communication Publication created " + communicationPublication,
					screenshotCapture());
			System.out.println("Duplicate communication Publication created " + communicationPublication);

		} else {
			setReport().log(Status.FAIL,
					"Duplicate communication Publication is not created " + communicationPublication,
					screenshotCapture());
			System.out.println("Duplicate communication Publication NOT created " + communicationPublication);

			Driver.failCount++;
		}
		return this;
	}

	// Verify if Contact Communication is in terminated status
	public ContactsPage verifyContactCommunicationTerminationReason(String terminationReason) {
		String contactComm = getAttribute(getDriver().findElement(By.xpath("//*[@aria-label='Termination Reason']")),
				"title", "Termination Reason");
		if (contactComm.equalsIgnoreCase(terminationReason)) {

			setReport().log(Status.PASS,
					"Termination reason for the Contact Communication ' " + contactComm + " ' is displayed right",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL,
					"Termination reason for the Contact Communication '" + contactComm + " ' is not displayed right",
					screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}

	// verify if correct error msg is displayed when providing blank value for the
	// Contact Communication
	public ContactsPage verifyErrorInContactCommunication(String errorInContactCommunication) {
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='ix_communicationpublication-error-message']")),
				errorInContactCommunication, "Error in Communication Publication");
		return this;
	}

	// Selecting Inactive view under Contact Communication
	public ContactsPage clickInactiveContactCommunicationView() throws InterruptedException {
		Thread.sleep(3000);
		if (getDriver().findElement(By.xpath("//span[contains(text(),'Contact Communication Associated View')]"))
				.isDisplayed()) {
			click(getDriver().findElement(By.xpath("//span[contains(text(),'Contact Communication Associated View')]")),
					"Contact Communication Associated View");
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//*[contains(text(),'Inactive Contact Communications')]")),
					"Inactive Contact Communications");
			Thread.sleep(3000);
		}
		return this;
	}

	// verify Contact Communication status in Inactive view
	public ContactsPage verifyContactCommunicationStatusInInactiveView(String contactCommStatus) {
		//Wave2 Update
		//Wave 2023 -1
		String status = getTextValue(getDriver().findElement(By.xpath("(//div[@col-id='statecode'])[2]//label")),"Status in Inactive Contact Communication View");
		if (status.equalsIgnoreCase(contactCommStatus)) {

			setReport().log(Status.PASS,
					"Status in Inactive Contact Contact Communication View " + status + " is displayed right",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL,
					"Status in Inactive Contact Communication View " + status + " is not displayed right",
					screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}

	//clikc signout
	public ContactsPage clickSignout() {
		click(getDriver().findElement(By.id("mectrl_headerPicture")), "Signout Icon");
		click(getDriver().findElement(By.id("mectrl_body_signOut")), "Signout button");
		return this;
	}

	// Click Save and Close button in contact summary page
	public MemberFormPage clickSaveAndClose() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");
		Thread.sleep(7000);
		try {
			if(getDriver().findElement(By.xpath("//span[text()='Ignore and save']")).isDisplayed());
			click(getDriver().findElement(By.xpath("//span[text()='Ignore and save']")),"Ignore and Save");	
		} catch (Exception e) {
			e.getMessage();
		}
		return new MemberFormPage();


	}

	// Click back from Contact To Member Form
	public MemberFormPage clickGoBackToMemberForm() throws InterruptedException {
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//*[@title='Go back']")), "Go Back");
		Thread.sleep(6000);
		return new MemberFormPage();
	}

	// Click back from Contact To Member Form
	public MemberFormPage verifyCAA() throws InterruptedException {
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//*[@title='Go back']")), "Go Back");
		Thread.sleep(6000);
		return new MemberFormPage();
	}

	//Deactivate the contact

	public ContactsPage clickDeactivateButton() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='contact|NoRelationship|Form|Mscrm.Form.contact.Deactivate']")), "Deactivate Button");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")), "Deactivate Button");
		Thread.sleep(4000);

		return this;
	}


	//Verify the Deactivated contact

	public ContactsPage verifyContactDeactivated() throws InterruptedException {
		Thread.sleep(2000);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id='warningNotification' and contains(text(),'status: Inactive')]")).size(), "Inacitve Status");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[@data-id='contact|NoRelationship|Form|Mscrm.Form.contact.Activate']")).size(), "Activate Button");
		return this;
	}
	//Activate Account
	public ContactsPage Activateccount() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='contact|NoRelationship|Form|Mscrm.Form.contact.Activate']")), "Activate Button");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")), "Deacivate Button");
		Thread.sleep(4000);
		return this;
	}

	public ContactsPage verifyDeactivatedAcocunt(String account) throws InterruptedException {
		String pagination=getDriver().findElement(By.xpath("//span[contains(@class,'statusContainer')]")).getText();
		int start=(pagination.indexOf('f')+2);
		int last=(pagination.indexOf('f')+3);
		int totalcount=Integer.parseInt(pagination.substring(start,last));

		clickCAAFromRelated();

		DeactivaeAccount(account);
		clickSummaryTab();
		pagination=getDriver().findElement(By.xpath("//span[contains(@class,'statusContainer')]")).getText();

		start=(pagination.indexOf('f')+2);
		last=(pagination.indexOf('f')+3);
		int aftercount=Integer.parseInt(pagination.substring(start,last));

		assertTrue(aftercount<totalcount);

		return this;
	}

	public ContactsPage doubleClickAccount(String account) throws InterruptedException{
		Actions actions=new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//div[@role='row' and contains(@class,'ag-row-no-focus')]//div[contains(@class,'ms-TooltipHost') and contains(text(),'"+account+"')]"))).doubleClick().build().perform();
		return this;
	}
	public ContactsPage doubleClickcotactName(String account) throws InterruptedException{
		Actions actions=new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//div[@role='row' and contains(@class,'ag-row-no-focus')]//div[contains(@class,'ms-TooltipHost') ]//span[contains(text(),'"+account+"')]"))).build().perform();
		actions.moveToElement(getDriver().findElement(By.xpath("//input[@aria-label='Select or deselect the row']"))).doubleClick().build().perform();
		return this;
	}

	//
	public ContactsPage DeactivaeAccount(String account) throws InterruptedException

	{
		doubleClickAccount(account);
		typeContactRelationshipEndDate();
		clickSaveAndCloseInCAA();
		return this;
	}
	public ContactsPage verifyActivatedAcocunt(String account) throws InterruptedException {
		String pagination=getDriver().findElement(By.xpath("//span[contains(@class,'statusContainer')]")).getText();

		int start=(pagination.indexOf('f')+2);
		int last=(pagination.indexOf('f')+3);
		int totalcount=Integer.parseInt(pagination.substring(start,last));


		clickCAAFromRelated();
		clickAddCAA();
		typeAccountInCAA(account);
		clickSaveAndCloseInCAA();
		clickSummaryTab();

		pagination=getDriver().findElement(By.xpath("//span[contains(@class,'statusContainer')]")).getText();
		start=(pagination.indexOf('f')+2);
		last=(pagination.indexOf('f')+3);
		int aftercount=Integer.parseInt(pagination.substring(start,last));


		assertTrue(aftercount>totalcount);


		return this;
	}


	public ContactsPage addnewCommunicationPublications() {

		click(getDriver().findElement(By.xpath("//*[@aria-label='Contact Communication Commands']")),"Contact Communication Commands");
		click(getDriver().findElement(By.xpath("//span[contains(text(),'New Contact Communication')]")),"New Contact Communication");

		return this;
	}

	public ContactsPage clickandsaveCAA() throws InterruptedException{
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contactaccountassociation|NoRelationship|Form|Mscrm.Form.ix_contactaccountassociation.SaveAndClose']")),"Contact Communication Commands");
		Thread.sleep(2000);
		return this;
	}

	public ContactsPage verifyNoRowinCAA() throws InterruptedException{
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//div[contains(@col-id,'.accountnumber')]//label[contains(@class,'label')]")).size(), "Rows in CAA");
		return this;

	}


	public ContactsPage verifyNoCAAinCAAgrid() throws InterruptedException{
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//section[@aria-label='CONTACT ACCOUNT ASSOCIATION']//div[contains(text(),'No data available')]")).size(), "Rows in CAA");
		return this;

	}

	public ContactsPage verifyFirstName(String firstname){

		verifyPartialAttribute(getDriver().findElement(By.xpath("//*[@data-id='fullname_compositionLinkControl_firstname.fieldControl-text-box-text']")),"Value",firstname, "FirstName");
		return this;

	}


	public ContactsPage verifyLastName(String lastName){
		verifyPartialAttribute(getDriver().findElement(By.xpath("//*[@data-id='fullname_compositionLinkControl_lastname.fieldControl-text-box-text']")),"Value",lastName, "FirstName");
		return this;

	}


	// type the job title in the summary page
	public ContactsPage typeJobTitle(String jobTitle) throws InterruptedException {
		Thread.sleep(500);
		// alternate action for scrolling down to Job Titile
		click(getDriver().findElement(
				By.xpath("//*[@data-id='fullname_compositionLinkControl_lastname.fieldControl-text-box-text']")),
				"Last Name");
		click(getDriver().findElement(By.xpath("//*[@data-id='jobtitle.fieldControl-text-box-text']")), "Job Title");


		type(((getDriver().findElement(By.xpath("//*[@data-id='jobtitle.fieldControl-text-box-text']")))),
				jobTitle, "Job Title");
		return this;
	}

	public ContactsPage verifyJobTitile(String jobTitle) throws InterruptedException {
		Thread.sleep(500);
		// alternate action for scrolling down to Job Titile
		click(getDriver().findElement(
				By.xpath("//*[@data-id='fullname_compositionLinkControl_lastname.fieldControl-text-box-text']")),
				"Last Name");
		click(getDriver().findElement(By.xpath("//*[@data-id='jobtitle.fieldControl-text-box-text']")), "Job Title");


		verifyExactValue(((getDriver().findElement(By.xpath("//*[@data-id='jobtitle.fieldControl-text-box-text']")))),
				jobTitle, "Job Title");
		return this;
	}


	//Double click CAA from CAA subgrid in the summary page 
	public ContactsPage doubleclickCAAinSummaryGrid() throws InterruptedException {

		Thread.sleep(500);
		doubleClick(getDriver().findElement(By.xpath("//div[@data-id='dataSetRoot_ContactAccountAssociation']//span[contains(@class,'headerText')]")),"Contact Account Assiocation");
		return this;

	}

	//Enter the CAA end date

	public ContactsPage enterCAAEndDAteAndSave() throws InterruptedException {

		Thread.sleep(500);
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_relationshipenddate.fieldControl-date-time-input']")),TestUtils.todaysDate(),"Relation End Date");
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contactaccountassociation|NoRelationship|Form|Mscrm.Form.ix_contactaccountassociation.Save']")),"Save button");
		Thread.sleep(500);
		return this;

	}





	//Double click CAA from CAA subgrid in the summary page 
	public ContactsPage verifyCAAisnotPresent() throws InterruptedException {

		Thread.sleep(500);
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//div[@data-id='dataSetRoot_ContactAccountAssociation']//span[contains(@class,'headerText')]")).size(),"Contact Account Assiocation");
		return this;

	}

	//Click more command

	public ContactsPage clickSeeAssociatedRecordsinCAA() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@aria-label='More commands for Contact Account Association']")),"More Command button");
		WebDriverWait wait= new WebDriverWait(getDriver(),Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//span[contains(text(),'See associated records')]"))));

		Actions actions=new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//span[contains(text(),'See associated records')]"))).click().build().perform();	

		return this;

	}


	//Verify Termination status in JobFunction
	public ContactsPage verifyTerminationStatusinJobFunction() throws InterruptedException {

		String terminationStatus=getAttribute(getDriver().findElement(By.xpath("//div[@data-control-name='ContactJobFunctionSubgrid']//div[@col-id='ix_terminationreason']//label[contains(@class,'Label option')]")), "aria-label", "Termination Reason");
		assertTrue(terminationStatus.contains("Terminated"),"Terminated");
		return this;
	}

	//Verify Termination status in JobFunction
	public ContactsPage verifyTerminationStatusinCommunication() throws InterruptedException {

		String terminationStatus=getAttribute(getDriver().findElement(By.xpath("//div[@data-control-name='ContactCommunicationSubgrid']//div[@col-id='ix_terminationreason']//label[contains(@class,'Label option')]")), "aria-label", "Termination Reason");
		assertTrue(terminationStatus.contains("Terminated"),"Terminated");
		return this;
	}

	public ContactsPage verifyTerminationDateinCAA(String endDate) {
		String date=getAttribute(getDriver().findElement(By.xpath("//input[@data-id='ix_relationshipenddate.fieldControl-date-time-input']")), "Value", "Termination Reason");	
		assertTrue(date.contentEquals(endDate),"Date");
		return this;
	}


	public ContactsPage createnewContact(String firstName, String SecondName) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@data-id='firstname.fieldControl-text-box-text']")),firstName, "First NAme");
		type(getDriver().findElement(By.xpath("//input[@data-id='lastname.fieldControl-text-box-text']")),SecondName, "Last NAme");
		click(getDriver().findElement(By.xpath("//button[@data-id='quickCreateSaveAndCloseBtn']")),"Click save and Close");
		Thread.sleep(5000);

		return this;
	}

	public ContactsPage verifyCAAinSummarySubGrid(boolean isdisplayed) throws InterruptedException {
		if(isdisplayed) {
			verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-id='ContactAccountAssociation-pcf_grid_control_container']//div[contains(@class,'detailsContainer')]")).size(), "CAA in summary Tab");

		}else {
			verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//div[@data-id='ContactAccountAssociation-pcf_grid_control_container']//div[contains(@class,'detailsContainer')]")).size(), "CAA in summary Tab");
		}
		return this;
	}

	//Click Three Dot under Communication publication
	public ContactsPage clickThreeDotinCommunication() {
		click(getDriver().findElement(By.xpath("//button[@title='More commands for Contact Communication']")),"More commands button");
		click(getDriver().findElement(By.xpath("//span[contains(text(),'New Contact Communication')]")),"Add New button");
		return this;
	}

	//clear Communication

	public ContactsPage clearCommunicationpublicationValue() throws InterruptedException {
		Actions actions= new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='ix_communicationpublication.fieldControl-LookupResultsDropdown_ix_communicationpublication_selected_tag_text']"))).build().perform();
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//button[contains(@aria-label,'Delete')]")),"Delete Button");
		return this;
	}

	//Verify CAA is autopopulated
	public ContactsPage verifyCAAAutopopulated() {
		String CAA=getDriver().findElement(By.xpath("//*[@data-id='ix_contactaccountassociationid.fieldControl-LookupResultsDropdown_ix_contactaccountassociationid_selected_tag_text']")).getAttribute("title");
		assertTrue(!CAA.isEmpty());
		return this;
	}

	//Verify error message for blank first name and Last Name
	
	public ContactsPage verifyErrorMessageinName() throws InterruptedException {
		clickTab(1);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id='fullname_compositionLinkControl_firstname-error-message']")).size(), "First Name");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id='fullname_compositionLinkControl_lastname-error-message']")).size(), "Last Name");
		
		return this;
	}
	
	//Verify Error message for Primary account
	
	public ContactsPage verifyErrorMessageinPrimaryAccount() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id='parentcustomerid-error-message']")).size(), "First Name");
		
		return this;
	}
	
	
	//Verify Error message for Job function
	
		public ContactsPage verifyJobFunctionMandatory() {
			verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@data-id='ix_jobfunctionnew-error-message']")).size(), "Job Function");
			
			return this;
		}
	
	
	//clear Job Functions

		public ContactsPage clearJobFunctions() throws InterruptedException {
			Actions actions= new Actions(getDriver());
			actions.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='ix_jobfunctionnew.fieldControl-LookupResultsDropdown_ix_jobfunctionnew_selected_tag_text']"))).build().perform();
			Thread.sleep(3000);
			click(getDriver().findElement(By.xpath("//button[@data-id='ix_jobfunctionnew.fieldControl-LookupResultsDropdown_ix_jobfunctionnew_selected_tag_delete']")),"Delete Button");
			return this;
		}


		//click Refresh
		public ContactsPage clicRefreshButton() throws InterruptedException {
			click(getDriver().findElement(By.xpath("//button[@data-id='contact|NoRelationship|Form|Mscrm.Form.contact.RefreshModernButton']")),"Refresh Button");
			return this;
		}
		
		
		
		
}


