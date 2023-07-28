package pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import driver.Driver;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

public class ContactsPage extends WebDriverServiceImpl {

	// Click New on contacts page
	public ContactsPage clickNewOnContactsPage() {
		click(getDriver().findElement(
				By.xpath("//*[@data-id='contact|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.contact.NewRecord']")),
				"New");
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
		WebDriverWait wait= new WebDriverWait(getDriver(),20);
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
		verifyDisplayed(getDriver().findElement(By.xpath("//label[@title='Contact End Date']")), "Contact End Date");
		return this;
	}
	//select view type

	public ContactsPage selectAllContactView() throws InterruptedException {
		Thread.sleep(3000);
		// Click Drop down
		click(getDriver().findElement(By.xpath("//span/i[@data-icon-name='ChevronDown']")),"Drop down");
		Thread.sleep(3000);
		//Wave2 Update
		click(getDriver().findElement(By.xpath("//label[contains(text(),'All Contacts')]")), "All Contacts");
		Thread.sleep(10000);
		return this;

	}
	// type the contact first name and last name
	public ContactsPage typeContactName(String firstName, String lastName) throws InterruptedException {
		Thread.sleep(3000);
		// Type first name
		click(getDriver().findElement(
				By.xpath("//*[@data-id='fullname_compositionLinkControl_firstname.fieldControl-text-box-text']")),
				"First Name");
		Thread.sleep(2000);
		type(((getDriver().findElement(
				By.xpath("//*[@data-id='fullname_compositionLinkControl_firstname.fieldControl-text-box-text']")))),
				firstName, "First Name");

		// Type second name
		click(getDriver().findElement(
				By.xpath("//*[@data-id='fullname_compositionLinkControl_lastname.fieldControl-text-box-text']")),
				"Last Name");
		Thread.sleep(2000);
		type(((getDriver().findElement(
				By.xpath("//*[@data-id='fullname_compositionLinkControl_lastname.fieldControl-text-box-text']")))),
				lastName, "Last Name");

		return this;
	}

	// select the primary account
	public ContactsPage selectPrimaryAccount(String primaryAccount) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Department')]")),"Department");		
		click(getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_textInputBox_with_filter_new']")),"Primary Contact");
		type(((getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_textInputBox_with_filter_new']")))),primaryAccount, "Primary Account");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[contains(@id,'parentcustomerid.fieldControl-name0_0_0')]")),primaryAccount);
		return this;
	}

	// Click Save button in contact summary page
	public ContactsPage clickSave() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='contact|NoRelationship|Form|Mscrm.Form.contact.Save']")),
				"Save");
		Thread.sleep(5000);
		Thread.sleep(5000);
		return this;
	}

	// check if Innovatix contact id is created
	public ContactsPage isInnovatixContactIDDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		String innovatixContactID = getAttribute(
				getDriver().findElement(By.xpath("//*[@data-id='ix_innovatixcontactid.fieldControl-text-box-text']")),
				"value", "Innovatix Contact Id");

		setReport().log(Status.PASS, "Innovatix Contact ID " + innovatixContactID + " is displayed",
				screenshotCapture());
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
		selectDropDownUsingVisibleText(
				((getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")))),
				recordStatus, "Record Status");
		Thread.sleep(3000);
		verifyExactTextWithTitleAttribute(
				getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")),
				recordStatus, "Record Status");
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
		click(getDriver().findElement(
				By.xpath("//*[@data-id='fullname_compositionLinkControl_lastname.fieldControl-text-box-text']")),
				"Last Name");
		click(getDriver().findElement(By.xpath("//*[@data-id='jobtitle.fieldControl-text-box-text']")), "Job Title");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_employeeflag.fieldControl-checkbox-select']")),
				"Employee flag");

		// click contact end date field twice and then type value to it
		click((getDriver().findElement(By.xpath("//*[@data-id='ix_contactenddate.fieldControl-date-time-input']"))),
				"Contact End Date");
		click((getDriver().findElement(By.xpath("//*[@data-id='ix_contactenddate.fieldControl-date-time-input']"))),
				"Contact End Date");

		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_contactenddate.fieldControl-date-time-input']")))),
				contactEndDate, "Contact End Date");
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

	// perform page refresh
	public ContactsPage pageRefresh() throws InterruptedException {
		getDriver().navigate().refresh();
		Thread.sleep(5000);
		return this;
	}

	// to choose an existing Active contact
	public ContactsPage chooseActiveContact(String CrmNumber1) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='quickFind_text_1']")), "Find Criteria");
		typeAndEnter(getDriver().findElement(By.xpath("//*[@data-id='quickFind_text_1']")), CrmNumber1,
				"Find Criteria");
		Thread.sleep(6000);
		//Actions a = new Actions(getDriver());
		//Wave2 Update
		//a.moveToElement(getDriver().findElement(By.xpath("//span[contains(@class,'RowSelectionCheckMarkSpan')]//i[@data-icon-name='StatusCircleCheckmark']"))).doubleClick().build().perform();
		//a.moveToElement(
		doubleClick(getDriver().findElement(By.xpath("//*[@data-icon-name='CheckMark']")),"Check Mark");
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
		a.moveToElement(getDriver().findElement(By.xpath("//*[@data-icon-name='CheckMark']"))).doubleClick().build().perform();
		Thread.sleep(6000);
		return this;
	}
	// update primary account with a new account details
	public ContactsPage addAnotherPrimaryAccount(String PrimaryAccount1) throws InterruptedException {
		Thread.sleep(5000);
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_selected_tag_text']"))).build().perform();
		click(getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_selected_tag_delete']")),"Delete"); 
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_textInputBox_with_filter_new']")),"Primary Contact");
		type(((getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_textInputBox_with_filter_new']")))),PrimaryAccount1, "Primary Account");
		Thread.sleep(6000);
		click(getDriver().findElement(By.xpath("//*[contains(@id,'parentcustomerid.fieldControl-name0_0_0')]")),PrimaryAccount1);
		return this;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Contact Account Association~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// Click Contact Account Association from Related header field
	public ContactsPage clickCAAFromRelated() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")), "Related");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Contact Account Associations')]")),
				"Contact Account Associations");
		Thread.sleep(2000);
		return this;
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
		click(getDriver().findElement(By.xpath("//*[contains(@id,'ix_account.fieldControl-name0_0_0')]")),account);
		return this;
	}

	// click save in CAA
	public ContactsPage clickSaveInCAA() throws InterruptedException {
		click(getDriver().findElement(By.xpath(
				"//*[@data-id='ix_contactaccountassociation|NoRelationship|Form|Mscrm.Form.ix_contactaccountassociation.Save']")),
				"Save");
		Thread.sleep(5000);
		return this;
	}

	// choose contact termination reason
	public ContactsPage chooseContactTerminationReason(String contactTerminationReason) throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_terminationreason.fieldControl-option-set-select']")),
				"Termination Reason");
		selectDropDownUsingVisibleText(
				((getDriver()
						.findElement(By.xpath("//*[@data-id='ix_terminationreason.fieldControl-option-set-select']")))),
				contactTerminationReason, "Termination Reason");
		Thread.sleep(3000);
		Thread.sleep(3000);
		return this;
	}

	// provide contact relationship end date
	public ContactsPage typeContactRelationshipEndDate(String contactRelationshipEndDate) {
		type(((getDriver()
				.findElement(By.xpath("//*[@data-id='ix_relationshipenddate.fieldControl-date-time-input']")))),
				contactRelationshipEndDate, "Relationship End Date");
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
				doubleClick(getDriver().findElement(By.xpath("(//i[@data-icon-name='CheckMark'])[1]")),"CAA Rec");
		Thread.sleep(3000);
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

	// save the detected duplicate record
	public ContactsPage clickSaveDuplicateDetected() {
		try {
			Thread.sleep(5000);
			String duplicateText = getDriver().findElement(By.xpath("//*[@data-id='manageDuplicatesTitle']")).getText();
			if (duplicateText.equalsIgnoreCase("Duplicate records found")) {
				click(getDriver().findElement(By.xpath("//*[@data-id='ignore_save']")), "Ignore and Save");
				Thread.sleep(10000);
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
		click(getDriver().findElement(By.xpath("//li[contains(text(),'Administration')]")), "Administration tab");

		String conRecordStatus = getAttribute(
				getDriver().findElement(By.xpath("//*[@data-id='statecode.fieldControl-option-set-select']")), "title",
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
		click(getDriver().findElement(By.xpath("//*[@title='Related']")), "Related");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Contact Account Associations')]")),
				"Contact Account Associations");
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
		String relEndDate = getAttribute(
				getDriver()
				.findElement(By.xpath("//*[@data-id='ix_relationshipenddate.fieldControl-date-time-input']")),
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
		Thread.sleep(3000);
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

	// Click save button in the job function page
	public ContactsPage clickSaveInJobFunction() throws InterruptedException {
		click(getDriver().findElement(By.xpath(
				"//*[@data-id='ix_contactjobfunction|NoRelationship|Form|Mscrm.Form.ix_contactjobfunction.Save']")),
				"Save");
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

		Thread.sleep(3000);

		String dateInUI = getAttribute(
				getDriver().findElement(By.xpath("//*[@data-id='createdon.fieldControl-date-time-input']")), "value",
				"Created On");
		Date diffdate = formatter.parse(dateInUI);
		String finalDate = formatter.format(diffdate);

		Thread.sleep(3000);
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
	public ContactsPage doubleClickOnJobFunction() throws InterruptedException {
		Thread.sleep(3000);
		//Actions a = new Actions(getDriver());
		//Wave2 Update
		//a.moveToElement(
		doubleClick(getDriver().findElement(By.xpath("//div[text()='Active']")),"Job Func");
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
		String jobFunction = getAttribute(
				getDriver()
				.findElement(By.xpath("//*[@data-id='ix_terminationreason.fieldControl-option-set-select']")),
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
		
		doubleClick(getDriver().findElement(By.xpath("//*[@col-id='statecode']//label[@aria-label='Read only']")),"CAA on Job Func");
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
		Thread.sleep(3000);

		String dateInUI = getAttribute(
				getDriver().findElement(By.xpath("//*[@data-id='createdon.fieldControl-date-time-input']")), "value",
				"Created On");
		Date diffdate = formatter.parse(dateInUI);
		String finalDate = formatter.format(diffdate);

		Thread.sleep(3000);

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
				doubleClick(getDriver().findElement(By.xpath("//*[@data-icon-name='CheckMark']")),"CAA on Comm/Pub");
				
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
		String contactComm = getAttribute(
				getDriver()
				.findElement(By.xpath("//*[@data-id='ix_terminationreason.fieldControl-option-set-select']")),
				"title", "Termination Reason");
		if (contactComm.equalsIgnoreCase(terminationReason)) {

			setReport().log(Status.PASS,
					"Termination reason for the Contact Communication " + contactComm + " is displayed right",
					screenshotCapture());

		} else {
			setReport().log(Status.FAIL,
					"Termination reason for the Contact Communication " + contactComm + " is not displayed right",
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


}
