package pages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import driver.Driver;
import services.WebDriverServiceImpl;

public class ContractAttachmentPage extends WebDriverServiceImpl{


	//Select All Contract Attachments view
	public ContractAttachmentPage selectAllContractAttachentsView() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("(//*[@data-icon-name='ChevronDown'])[2]")),"Select a view");
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'All Contract Attachments')]")),"All Contract Attachments");
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("(//*[contains(@col-id,'ix_premierein')])[2]"))));
		return this;
	} 	

	//Double Click on First CA in All ContractAttachmentsView
	public ContractAttachmentPage openFirstContractAttachment() {
		Actions a=new Actions(getDriver());
		a.doubleClick(getDriver().findElement(By.xpath("(//*[contains(@col-id,'ix_premierein')])[2]"))).build().perform();;
		return this;

	}


	//Click New Contract Button
	public ContractAttachmentPage createNewCotnractAttachment(String member, String contract, String AttachmentStatus, String AttachmentStatusDate) throws InterruptedException, ParseException, AWTException {

		click(getDriver().findElement(By.xpath("//input[@data-id='ix_accountid.fieldControl-LookupResultsDropdown_ix_accountid_textInputBox_with_filter_new']")),"Member");

		Thread.sleep(2000);

		type(getDriver().findElement(By.xpath("//input[@data-id='ix_accountid.fieldControl-LookupResultsDropdown_ix_accountid_textInputBox_with_filter_new']")),member,"Member");
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+member+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+member+"')]")),"member");

		click(getDriver().findElement(By.xpath("//input[@data-id='ix_contractid.fieldControl-LookupResultsDropdown_ix_contractid_textInputBox_with_filter_new']")),"Contract");

		Thread.sleep(2000);

		type(getDriver().findElement(By.xpath("//input[@data-id='ix_contractid.fieldControl-LookupResultsDropdown_ix_contractid_textInputBox_with_filter_new']")),contract,"Contract");
		wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+contract+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+contract+"')]")),"member");

		selectAttachmentStatusOnCA(AttachmentStatus);

		enterContractAttahmentstatusDate(AttachmentStatusDate);

		return this;	
	}
	//Click Suppliers Tab on CA

	public ContractAttachmentPage clickSuppliersTabOnCA() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//li[@title='Suppliers']")),"Supplier Tab");
		Thread.sleep(3000);
		return this;

	}

	//+New Contract Attachment Supplier
	public ContractAttachmentPage clickNewContractAttachmentSupplierButton() {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contractattachmentsupplier|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_contractattachmentsupplier.AddNewStandard']")),"+ New Contract Attachment Supplier");
		return this;

	}

	//choose Existing Supplier Account on CA Attachment Supplier
	public ContractAttachmentPage chooseSupplierAccountOnCASupplier() {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_supplieraccount.fieldControl-LookupResultsDropdown_ix_supplieraccount_textInputBox_with_filter_new']")),"Supplier Account");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_supplieraccount.fieldControl-LookupResultsDropdown_ix_supplieraccount_search']")),"Supplier Account Search Icon");
		click(getDriver().findElement(By.xpath("//*[contains(@data-id,'ix_supplieraccount.fieldControl-ix_supplierid')]")),"Supplier Account");
		return this;
	}


	//choose Attachment Status Date on CA Attachment Supplier
	public ContractAttachmentPage chooseAttachmentStatusDateOnCASupplier() {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String attachStatusDate= dateFormat.format(date);			
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_attachmentstatusdate.fieldControl-date-time-input']")),"Attachment Status Date"); 
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_attachmentstatusdate.fieldControl-date-time-input']")),"Attachment Status Date");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_attachmentstatusdate.fieldControl-date-time-input']")),attachStatusDate,"Attachment Status Date"); 
		return this;
	}

	//type Tier on CA Supplier
	public ContractAttachmentPage typeTierOnCASupplier(String tier) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_tier.fieldControl-text-box-text']")),"Tier");
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_tier.fieldControl-text-box-text']")),tier,"Tier");
		return this;
	}

	//type Tier on CA Supplier
	public ContractAttachmentPage typeCommentsOnCASupplier(String comments) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_comments.fieldControl-text-box-text']")),"Comments");
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_comments.fieldControl-text-box-text']")),comments,"Comments");
		return this;
	}

	//Default Value  on Attachment Status field is not 'Attachment Requested'
	public ContractAttachmentPage verifyAttachmentStatusDefaultValueOnCASupplierIsNotAttachmentRequested() {
		verifyTextDoesNotMatchTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_supplierattachmentstatus.fieldControl-option-set-select']")),"Attachment Requested" ,"Attachment Status");
		return this;
	}

	//Default Value  on Attachment Status field is '--Select--'
	public ContractAttachmentPage verifyAttachmentStatusDefaultValueOnCASupplierIsSelect() {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_supplierattachmentstatus.fieldControl-option-set-select']")),"---" ,"Attachment Status");
		return this;

	}

	//Verify Attachment Status field drop down options on CA Supplier
	public ContractAttachmentPage verifyAttachmentStatusFieldOptionsOnCASupplier() throws InterruptedException {
		Select attachmentStatus = new Select(getDriver().findElement(By.xpath("//*[@data-id='ix_supplierattachmentstatus.fieldControl-option-set-select']")));
		// Create Expected Array List
		List<String> expectedAttachmentStatus = Arrays.asList("---","Attachment Requested","Do not attach at Supplier","Confirmed by Distributor","Not Authorized Supplier","Aligned with another GPO",
				"Not Approved by Manufacturer","Not Purchasing through Distributor" , "Requested Eligibility from Supplier", "Pending Request","Price Activation Required","Ineligible Class of Trade","Expired Contract","Premier Roster Update Required");		
		//Create Actual blank Array List
		List<String> actualAttachmentStatus=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =attachmentStatus.getOptions();		
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualAttachmentStatus.add(data);			
			System.out.println("The Actual Attachment Status is : "  + " " +data);				
			Thread.sleep(3000);
			if(expectedAttachmentStatus.containsAll(actualAttachmentStatus))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "Attachment Status ' " + "   " + data + "  " +  " '  Option is available to choose from the list" + " ::::= > "+ expectedAttachmentStatus,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "Attachment Status ' "+   "   " + data + "  " + " ' Option is not available in the list"  + "::::= > "+ expectedAttachmentStatus ,	screenshotCapture());
				Driver.failCount++;
			}

		}

		return this;
	}

	//Click +New on CA
	public ContractAttachmentPage clickNewOnCA() {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contractattachment|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.ix_contractattachment.NewRecord']")),"+New");
		return this;

	}	



	//Verify Attachment Status field drop down options on CA Supplier
	public ContractAttachmentPage verifyAttachmentStatusFieldOptionsOnCA() throws InterruptedException {
		Select attachmentStatus = new Select(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatus.fieldControl-option-set-select']")));
		// Create Expected Array List
		List<String> expectedAttachmentStatus = Arrays.asList("---","Generated/Sent Paperwork","Sent to Contract Attachment","Sent to Vendor","Sent to Distributor",
				"Approved","Declined by Member","Denied by Vendor","Canceled","Offered to Member - On Hold","Information Required - On Hold",
				"Pending Paperwork","Price Activation Required","Request from Vendor", "Compliance Required");		
		//Create Actual blank Array List
		List<String> actualAttachmentStatus=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =attachmentStatus.getOptions();		
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualAttachmentStatus.add(data);			
			System.out.println("The Actual Attachment Status is : "  + " " +data);				
			Thread.sleep(3000);
			if(expectedAttachmentStatus.containsAll(actualAttachmentStatus))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "Attachment Status ' " + "   " + data + "  " +  " '  Option is available to choose from the list" + " ::::= > "+ expectedAttachmentStatus,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "Attachment Status ' "+   "   " + data + "  " + " ' Option is not available in the list"  + "::::= > "+ expectedAttachmentStatus ,	screenshotCapture());
				Driver.failCount++;
			}

		}

		return this;
	}

	//Select Attachment Status on CA Supplier
	public ContractAttachmentPage selectAttachmentStatusOnCASupplier(String caSupplierAttachmentStatus) {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_supplierattachmentstatus.fieldControl-option-set-select']")),caSupplierAttachmentStatus," Attachment Status on CA Supplier");
		return this;

	}

	//Click Save on CA Supplier
	public ContractAttachmentPage clickSaveOnCASupplier() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contractattachmentsupplier|NoRelationship|Form|Mscrm.Form.ix_contractattachmentsupplier.Save']")),"Save CA Supplier");
		Thread.sleep(2000);
		return this;

	}

	//Click Deactivate on CA Supplier
	public ContractAttachmentPage clickDeactivateOnCASupplier() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contractattachmentsupplier|NoRelationship|Form|Mscrm.Form.ix_contractattachmentsupplier.Deactivate']")),"Deactivate On CA Supplier");
		click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")),"Confirm Deactivate On CA Supplier");
		Thread.sleep(3000);
		return this;
	}


	//Click Activate on CA Supplier
	public ContractAttachmentPage clickActivateOnCASupplier() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contractattachmentsupplier|NoRelationship|Form|Mscrm.Form.ix_contractattachmentsupplier.Activate']")),"Activate On CA Supplier");
		click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")),"Confirm Activate On CA Supplier");
		Thread.sleep(3000);
		return this;
	}
	
	public ContractAttachmentPage searchinfilter(String crmNumberInput) throws InterruptedException {
		
		WebDriverWait wait= new WebDriverWait(getDriver(),Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//*[contains(@id,'quickFind_text')]"))));
		Thread.sleep(3000);
		typeAndEnter(getDriver().findElement(By.xpath("//*[contains(@id,'quickFind_text')]")),crmNumberInput,"Find Criteria" );
		//06/14/2023 -Due to Active Member Taking Long Time to load
		Thread.sleep(5000);
		return this;

	}

	//Choose Existing CA using Contract Number
	public ContractAttachmentPage doubleClickExistingContractAttachment(String contractNumber) throws InterruptedException   {
		Thread.sleep(4000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[contains(@col-id,'ix_idmownercontractnumber')]//label"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 2; i <=rowList.size(); i++) {
			String contractNum = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[contains(@col-id,'ix_idmownercontractnumber')]//label)["+i+"]")).getText();
			System.out.println(contractNum);
			if (contractNum.equals(contractNumber)) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[contains(@col-id,'ix_idmownercontractnumber')]//label)["+i+"]")), "Contract Attachment" +" " + contractNumber);
				Thread.sleep(3000);
				break;				
			}
		}		

		return this;					
	}

	//Double click on Existing Contract Attachment Supplier 

	public ContractAttachmentPage doubleClickCASupplier() throws InterruptedException {
		doubleClick(getDriver().findElement(By.xpath("(//*[@data-icon-name='CheckMark'])[2]")),"Existing CA Supplier");
		Thread.sleep(3000);
		return this;
	}

	//Verify Warning Notification on CA.
	public ContractAttachmentPage verifyWarningNotification() throws InterruptedException {
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='warningNotification']")),"Read-only: You don't have access to edit this record.","Warning Notification");
		Thread.sleep(3000);
		return this;
	}
	//Verify LastResponse Date Disabled
	public ContractAttachmentPage verifyLastResponseDateIsDisabled() throws InterruptedException {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[contains(@data-id,'tabpanel')]//h2")),"Don't forget to change the Status Date when changing the Attachment Status!","Header Section of Last Response Date");
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_lastresponsedate.fieldControl-date-time-input']")),"Last Response Date");
		Thread.sleep(2000);
		return this;
	}


	//Verify LastResponse Date is Locked read only
	public ContractAttachmentPage verifyLastResponseDateIsLockedAndReadOnly() throws InterruptedException {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[contains(@data-id,'tabpanel')]//h2")),"Don't forget to change the Status Date when changing the Attachment Status!","Header Section of Last Response Date");
		verifyDisplayed(getDriver().findElement(By.xpath("//*[@data-id='ix_lastresponsedate-locked-icon']")),"Lock Icon on Last Response Date Label");
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_lastresponsedate.fieldControl-date-time-input']")),"Last Response Date");
		Thread.sleep(2000);
		return this;
	}

	//Verify Initiated By and Termination Reason fields are disabled. -
	public ContractAttachmentPage verifyInitiatiedByTerminationReasonAreDisabled() throws InterruptedException {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='PriceActivationDetails_Sec']//h2")),"Price Activation Details","Header Section of Initiated By and Termination Reason Fields");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_negotiationstatus.fieldControl-text-box-text']")),"Negotiation Status");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_priceexpirationdate.fieldControl-date-time-input']")),"Price Expiration Date");
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='ix_initiatedby.fieldControl-text-box-text']")),"Initiated By");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_tierdescriptionfromsca.fieldControl-text-box-text']")),"Tier");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_customtierdescription.fieldControl-text-box-text']")),"Custom Tier Description");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_suppliercomments.fieldControl-text-box-text']")),"Custom Tier Description");
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='ix_terminationreason.fieldControl-text-box-text']")),"Termination Reason");
		Thread.sleep(2000);
		return this;
	}

	//Scroll to Price Activation Details

	public ContractAttachmentPage scrollToPriceActivationDetails() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatus.fieldControl-option-set-select-container']")),"Attachment Status");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")),"Attachment Status Reason");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatusdate.fieldControl-date-time-input']")),"Attachment Status Date");
		Thread.sleep(2000);
		return this;
	}
	//Select Attachment Status on CA 
	public ContractAttachmentPage selectAttachmentStatusOnCA(String caAttachmentStatus) {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatus.fieldControl-option-set-select']")),caAttachmentStatus," Attachment Status on CA");
		return this;

	}

	//Select Attachment Status Reason on CA 
	public ContractAttachmentPage selectAttachmentStatusReasonOnCA(String caAttachmentStatusReason) {
		click(getDriver().findElement(By.xpath("//*[@title='Attachment Status Date']"))," Attachment Status Date Title on CA");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")),caAttachmentStatusReason," Attachment Status Reason on CA");
		return this;

	}	
	//	

	//Click Save on CA
	public ContractAttachmentPage clickSaveOnCA() {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contractattachment|NoRelationship|Form|Mscrm.Form.ix_contractattachment.Save']"))," Save on CA");
		return this;

	}

	//Verify Attachment Status Reason 'Successor contract Update'
	@SuppressWarnings("unlikely-arg-type")
	public ContractAttachmentPage verifyAttachmentStatusReasonSuccesorContractUpdateIsNotPresent() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@title='Attachment Status Date']"))," Attachment Status Date Title on CA");
		Select attachmentStatusReason = new Select(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")));
		//Create Actual blank Array List
		List<String> actualAttachmentStatusReason=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =attachmentStatusReason.getOptions();		
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualAttachmentStatusReason.add(data);			
			System.out.println("The Actual Attachment Status Reason is : "  + " " +data);				
			Thread.sleep(3000);
			if(actualAttachmentStatusReason.equals("Successor Contract Update"))
			{	Thread.sleep(2000);	
			setReport().log(Status.FAIL, "Attachment Status Reason' "+   " Successor Contract Update  " +  "  " + " ' Option is NOT available in the list"  + "::::= > "+ actualAttachmentStatusReason ,	screenshotCapture());
			Driver.failCount++;

			} 
			else {
				setReport().log(Status.PASS, "Attachment Status Reason ' " + " Successor Contract Update " +  " '  Option is NOT available to choose from the list" + " ::::= > "+ actualAttachmentStatusReason,	screenshotCapture());
			}

		}

		return this;
	}

	//Verify Attachment Status reason
	public ContractAttachmentPage verifyAttachmentStatusReason() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@title='Attachment Status Date']"))," Attachment Status Date Title on CA");
		Select attachmentStatusReason = new Select(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")));
		//Create Actual blank Array List
		List<String> option=new ArrayList<String>();	

		List<WebElement> statusoption =attachmentStatusReason.getOptions();
		List<String> expectdoption = Arrays.asList("---","New Supplier Account(s) added","Document(s) Updated","Re-engagement","New/Reconciled Child Sites","Confirmed by Distributor","Successor Contract Update");
		for(int i=0;i<statusoption.size();i++) {

			option.add(statusoption.get(i).getText());

		}
System.out.println(option);
System.out.println(expectdoption);
		assertTrue(option.containsAll(expectdoption));

		return this;
	}
	
	//Verify Attachment Status reason
		public ContractAttachmentPage verifyAttachmentStatusReasonApproved() throws InterruptedException {
			click(getDriver().findElement(By.xpath("//*[@title='Attachment Status Date']"))," Attachment Status Date Title on CA");
			Select attachmentStatusReason = new Select(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")));
			//Create Actual blank Array List
			List<String> option=new ArrayList<String>();	

			List<WebElement> statusoption =attachmentStatusReason.getOptions();
			List<String> expectdoption = Arrays.asList("---","Revenue received; no approval on record","Vendor account set up required","Approved from previous contract","Confirmed by Distributor","Spend on Direct Parent","Approved per Vendor List","Approved by Vendor","Member is Secondary","Not Purchasing","Successor Contract Update","Approved as Primary","Approved as Secondary");
			for(int i=0;i<statusoption.size();i++) {

				option.add(statusoption.get(i).getText());

			}
	System.out.println(option);
	System.out.println(expectdoption);
			assertTrue(option.containsAll(expectdoption));

			return this;
		}
		
		//Verify Attachment Status reason
				public ContractAttachmentPage verifyAttachmentStatusReasonDeclinedByMember() throws InterruptedException {
					click(getDriver().findElement(By.xpath("//*[@title='Attachment Status Date']"))," Attachment Status Date Title on CA");
					Select attachmentStatusReason = new Select(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")));
					//Create Actual blank Array List
					List<String> option=new ArrayList<String>();	

					List<WebElement> statusoption =attachmentStatusReason.getOptions();
					List<String> expectdoption = Arrays.asList("---","Does not use product/contract not applicable","Private Contract/Agreement","Uses non-contracted competitive product","Uses competitor contract with us","Uses another GPO's contract","Other tier selected","Confirmed by Distributor");
					for(int i=0;i<statusoption.size();i++) {

						option.add(statusoption.get(i).getText());

					}
			System.out.println(option);
			System.out.println(expectdoption);
					assertTrue(option.containsAll(expectdoption));

					return this;
				}
				
				//Verify Attachment Status reason
				public ContractAttachmentPage verifyAttachmentStatusReasonDeniedByVendor() throws InterruptedException {
					click(getDriver().findElement(By.xpath("//*[@title='Attachment Status Date']"))," Attachment Status Date Title on CA");
					Select attachmentStatusReason = new Select(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")));
					//Create Actual blank Array List
					List<String> option=new ArrayList<String>();	

					List<WebElement> statusoption =attachmentStatusReason.getOptions();
					List<String> expectdoption = Arrays.asList("---","Using another GPO's contract","Uses private agreement","Not on vendor approved list","Approved for different tier","Confirmed by Distributor","Ineligible Class of Trade","Successor Contract Update");
					for(int i=0;i<statusoption.size();i++) {

						option.add(statusoption.get(i).getText());

					}
			System.out.println(option);
			System.out.println(expectdoption);
					assertTrue(option.containsAll(expectdoption));

					return this;
				}

	//Verify Attachment Status reason
		public ContractAttachmentPage verifyAttachmentStatusReasonSentToVendor() throws InterruptedException {
			click(getDriver().findElement(By.xpath("//*[@title='Attachment Status Date']"))," Attachment Status Date Title on CA");
			Select attachmentStatusReason = new Select(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")));
			//Create Actual blank Array List
			List<String> option=new ArrayList<String>();	

			List<WebElement> statusoption =attachmentStatusReason.getOptions();
			List<String> expectdoption = Arrays.asList("---","Member purchasing required","No response from vendor","Confirmed by Distributor","Successor Contract Update");
			for(int i=0;i<statusoption.size();i++) {

				option.add(statusoption.get(i).getText());

			}

			assertTrue(option.containsAll(expectdoption));

			return this;
		}
		




	//Verify Attachment Status Reason when Attachment Status is Approved.
	public ContractAttachmentPage verifyAttachmentStatusReasonApprovedAsPrimarySecondaryIsPresent() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@title='Attachment Status Date']"))," Attachment Status Date Title on CA");
		Select attachmentStatusReason = new Select(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")));
		// Create Expected Array List
		List<String> expectedAttachmentStatusReason = Arrays.asList("Approved as Primary","Approved as Secondary");		
		//Create Actual blank Array List
		List<String> actualAttachmentStatusReason=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =attachmentStatusReason.getOptions();		
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualAttachmentStatusReason.add(data);			
			System.out.println("The Actual Attachment Status Reason is : "  + " " +data);				
			Thread.sleep(3000);
		}
		if(actualAttachmentStatusReason.containsAll(expectedAttachmentStatusReason))
		{		
			Thread.sleep(3000);
			setReport().log(Status.PASS, "Attachment Status Reason ' " + "   " + expectedAttachmentStatusReason + "  " +  " '  Option is available to choose from the list" + " ::::= > "+ actualAttachmentStatusReason,	screenshotCapture());

		} 
		else {
			setReport().log(Status.FAIL, "Attachment Status Reason' "+   "   " + expectedAttachmentStatusReason + "  " + " ' Option is not available in the list"  + "::::= > "+ actualAttachmentStatusReason ,	screenshotCapture());
			Driver.failCount++;
		}
		return this;
	}


	//Verify Attachment Status Reasons (Approved as Primary","Approved as Secondary) is NOT present when Attachment Status is OTHER THAN Approved.
	public ContractAttachmentPage verifyAttachmentStatusReasonApprovedAsPrimarySecondaryIsNotPresent() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@title='Attachment Status Date']"))," Attachment Status Date Title on CA");
		Select attachmentStatusReason = new Select(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")));
		// Create Expected Array List
		List<String> expectedAttachmentStatusReason = Arrays.asList("Approved as Primary","Approved as Secondary");		
		//Create Actual blank Array List
		List<String> actualAttachmentStatusReason=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =attachmentStatusReason.getOptions();		
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualAttachmentStatusReason.add(data);			
			System.out.println("The Actual Attachment Status is Reason : "  + " " +data);				
			Thread.sleep(3000);
		}
		if(actualAttachmentStatusReason.containsAll(expectedAttachmentStatusReason))
		{		

			setReport().log(Status.FAIL, "Attachment Status Reason' "+   "   " + expectedAttachmentStatusReason + "  " + " ' Option is not available in the list"  + "::::= > "+ actualAttachmentStatusReason ,	screenshotCapture());
			Driver.failCount++;
		} 
		else {
			Thread.sleep(3000);
			setReport().log(Status.PASS, "Attachment Status Reason' " + "   " + expectedAttachmentStatusReason + "  " +  " '  Option is available to choose from the list" + " ::::= > "+ actualAttachmentStatusReason,	screenshotCapture());

		}
		return this;
	}

	//Verify Active only View is displayed
	public ContractAttachmentPage verifyDefaultViewinContractAttachment() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[contains(@id,'View') and contains(text(),'Contract Attachments - Active Contracts Only')]")).size(), "Active Contracts Only");

		return this;
	}

	//Verify New Contract Attachment Header
	public ContractAttachmentPage verifyContractAttachmentHeader() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//h1[@title='New Contract Attachment']")).size(), "New Contract Attachment");

		return this;
	}


	//Click New Contract Attachment
	public ContractAttachmentPage clickNewContractAttachmentButton() {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractattachment|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contractattachment.AddNewStandard']"))," New Contract Attachment Supplier");
		return this;

	}

	//Click New Contract Attachment
	public ContractAttachmentPage addContractAttachmentButton() {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractattachment|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.ix_contractattachment.NewRecord']"))," New Contract Attachment");
		return this;

	}

	//


	//Enter new Contract
	//Select Class of trade
	public ContractAttachmentPage selectContractID(String contract) throws InterruptedException, AWTException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_contractid.fieldControl-LookupResultsDropdown_ix_contractid_textInputBox_with_filter_new']")),"Class of Trade");
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_contractid.fieldControl-LookupResultsDropdown_ix_contractid_textInputBox_with_filter_new']")))),contract,"contract");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//span[contains(@data-id,'ix_contractid.fieldControl-ix_number')]/span[contains(text(),'"+contract+"')]")),"Contract");
		Thread.sleep(3000);
		return this;
	}


	//Select Attachment sStatus
	public ContractAttachmentPage selectAttachmentStatus(String attachmentStatus) throws InterruptedException, AWTException {

		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_attachmentstatus.fieldControl-option-set-select']")), attachmentStatus, "attachmentStatus");
		return this;
	}

	//Click Tab
	public ContractAttachmentPage clickTab(int number)  throws InterruptedException {
		for(int i=0;i<number;i++) {
			Actions a =new Actions(getDriver());
			a.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(3000);
		}
		return this;
	}

	//enter contract attachment status date
	public ContractAttachmentPage enterContractAttahmentstatusDate(String attachmentStatus) throws InterruptedException, AWTException { 
		clickTab(2);
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_attachmentstatusdate.fieldControl-date-time-input']")),attachmentStatus,"Attachment Status");
		clickTab(1);
		return this;

	}

	//enter Price Activation ID
	public ContractAttachmentPage enterPriceActivationID(String priceactivationID) throws InterruptedException, AWTException { 
		clickTab(2);
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_priceactivationid.fieldControl-text-box-text']")),priceactivationID,"Attachment Status");
		clickTab(12);
		return this;

	}


	//Verify Tire Requested
	public ContractAttachmentPage verifyTireRequested() throws InterruptedException, AWTException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[contains(text(),'Tier Requested')]")).size(), "Tire REquested");
		click(getDriver().findElement(By.xpath("//input[@aria-label='Tier Requested']")),"Tier Requested");
		return this;
	}


	//Verify Price Activation Section
	public ContractAttachmentPage verifyPriceActivationSection() throws InterruptedException, AWTException {

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-id='ix_tierdescriptionfromsca-FieldSectionItemContainer']")).size(), "Tire ");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@title='Price Activation ID']")).size(), "Price Activation Id");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@title='Initiated By']")).size(), "Initiated By ");

		return this;
	}
	//enter requester
	public ContractAttachmentPage enterrequesterid(String requester) throws InterruptedException, AWTException { 
		clickTab(2);
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_requesterid.fieldControl-LookupResultsDropdown_ix_requesterid_textInputBox_with_filter_new']")),"request id");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_requesterid.fieldControl-LookupResultsDropdown_ix_requesterid_textInputBox_with_filter_new']")),requester,"Attachment Status");
		click(getDriver().findElement(By.xpath("//span[@data-id='ix_requesterid.fieldControl-fullname0_0_0']/span[contains(text(),'"+requester+"')]")),"Requester");
		clickTab(1);
		return this;

	}	

	//Enter Request Comment
	public ContractAttachmentPage enterRequesterComments(String Comments) throws InterruptedException, AWTException {
		type(getDriver().findElement(By.xpath("//textarea[@data-id='ix_requestercomments.fieldControl-text-box-text']")),Comments,"Comments");
		return this;
	}

	//Enter Request Comment
	public ContractAttachmentPage enterAttachmentTeamComment(String Comments) throws InterruptedException, AWTException {
		type(getDriver().findElement(By.xpath("//textarea[@data-id='ix_comments.fieldControl-text-box-text']")),Comments,"Comments");
		return this;
	}

	//click save button
	public ContractAttachmentPage clickSaveButtonContractAttachment() throws InterruptedException, AWTException { 
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractattachment|NoRelationship|Form|Mscrm.Form.ix_contractattachment.Save']")),"Save Button");
		Thread.sleep(2000);
		clickIgnoreAndSave();
		Thread.sleep(5000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;

	}	
	
	public ContractAttachmentPage clickIgnoreAndSave() throws InterruptedException, AWTException { 
	List<WebElement> ignoreMessage=getDriver().findElements(By.xpath("//*[text()='Ignore and save']"));

	if(ignoreMessage.size()>0)

		click(getDriver().findElement(By.xpath("//*[text()='Ignore and save']")),"Ignore and Save"); 
return this;
	}
	//Click save and close button

	public ContractAttachmentPage clickSaveAndCloseButtonContractAttachment() throws InterruptedException, AWTException {

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractattachment|NoRelationship|Form|Mscrm.Form.ix_contractattachment.SaveAndClose']")),"Save and close Button");
		Thread.sleep(7000);
		return this;

	}

	//Select All existing Contract Attachment
	public ContractAttachmentPage selectAllExistingContractAttachment() throws InterruptedException, AWTException { 
		if(getDriver().findElements(By.xpath("//input[@aria-label='Toggle selection of all rows']")).size()>0) {
			Actions actions=new Actions(getDriver());
			actions.moveToElement(getDriver().findElement(By.xpath("//input[@aria-label='Toggle selection of all rows']"))).click().build().perform();
		}
		return this;
	}

	//Click Deactivate for Contract Attachment	
	public ContractAttachmentPage clickDeactivateonContractAttachment() throws InterruptedException, AWTException {
		if(getDriver().findElements(By.xpath("//button[@data-id='ix_contractattachment|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contractattachment.Deactivate']")).size()>0) {
			click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractattachment|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contractattachment.Deactivate']")),"Deactivate Button");
			click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Ok Button");
			Thread.sleep(7000);
		}

		return this;

	}

	//Verify Error message is not displauyed
	public ContractAttachmentPage verifyErrorisNotDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> error=getDriver().findElements(By.xpath("//*[@data-id='errorDialog_subtitle']"));
		verifyElementisNotDisplayed(error.size(), "Error message");
		Thread.sleep(2000);
		return this;
	}

	//Verify name in Supplier
	public ContractAttachmentPage verifyAccountInSupplierContractAttachment() {

		verifIsNoTNullWithTitleAttribute(getDriver().findElement(By.xpath("//div[@data-id='ix_accountid.fieldControl-LookupResultsDropdown_ix_accountid_selected_tag_text']")), "Account Nmae");
		return this;
	}

	//Verify Supplier Account Sugesstion list
	public ContractAttachmentPage verifyAccountinSupplierAccount() throws InterruptedException {
		Thread.sleep(500);
		String Aname= getDriver().findElement(By.xpath("//div[@data-id='ix_accountid.fieldControl-LookupResultsDropdown_ix_accountid_selected_tag_text']")).getAttribute("title");				
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_supplieraccount.fieldControl-LookupResultsDropdown_ix_supplieraccount_textInputBox_with_filter_new']")),"Supplier Account");
		Actions actions =new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//button[@aria-label='Search records for Supplier Account, Lookup field']"))).click().build().perform();

		List<WebElement> accountname=getDriver().findElements(By.xpath("//li[@data-id='ix_supplieraccount.fieldControl-LookupResultsDropdown_ix_supplieraccount_resultsContainer']//span[@data-id='ix_supplieraccount.fieldControl-ix_accountid0_0_0']"));

		ArrayList<String> name= new ArrayList<String>();
		for(int i=1;i<=accountname.size();i++) {
			name.add(getDriver().findElement(By.xpath("(//li[@data-id='ix_supplieraccount.fieldControl-LookupResultsDropdown_ix_supplieraccount_resultsContainer']//span[@data-id='ix_supplieraccount.fieldControl-ix_accountid0_0_0'])["+i+"]")).getText());
		}
		System.out.println(Aname);
		for(String eachname:name) {
			System.out.println(eachname);
			Assert.assertTrue(eachname.equalsIgnoreCase(Aname));
		}

		return this;
	}

	//Verify Attachment Status field drop down options on CA Supplier
	public ContractAttachmentPage verifyAttachmentStatusFieldOptionsOnCASupplierwithSpecificValue(String Option) throws InterruptedException {
		Select attachmentStatus = new Select(getDriver().findElement(By.xpath("//*[@data-id='ix_supplierattachmentstatus.fieldControl-option-set-select']")));
		// Create Expected Array List
		//Create Actual blank Array List
		List<String> actualAttachmentStatus=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =attachmentStatus.getOptions();		
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualAttachmentStatus.add(data);			
			System.out.println("The Actual Attachment Status is : "  + " " +data);				
		}
		Assert.assertTrue(actualAttachmentStatus.contains(Option));
		return this;
	}
	//Verify LOB required Error message
	public ContractAttachmentPage verifyErrorMessage(String errorMessage) throws InterruptedException {

		Thread.sleep(5000);
		verifyDisplayed(getDriver().findElement(By.xpath("//h2[contains(@aria-label,'"+errorMessage+"')]")),errorMessage);
		click(getDriver().findElement(By.xpath("//span[contains(@id,'okButtonText')]")),"Ok Button");
		return this;

	}

	public ContractAttachmentPage clickGoBackandDiscardChanges() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='navigateBackButtontab-id-0']")),"Go back");
		Thread.sleep(6000);
		List<WebElement> discarChanges=getDriver().findElements(By.xpath("//*[contains(@id,'cancelButtonTextName')]"));
		if(discarChanges.size()>0) {
			click(getDriver().findElement(By.xpath("//*[contains(@id,'cancelButtonTextName')]")),"Discard Changes");
		}

		Thread.sleep(5000);
		return this;	
	}

	public ContractAttachmentPage clickGeneralTabInContractAttachment() {

		click(getDriver().findElement(By.xpath("//li[@aria-label='General']")),"General Tab");
		return this;
	}


	//Verify Attachment Reasons
	public ContractAttachmentPage verifyAttachmentReasons() throws InterruptedException, AWTException {

		click(getDriver().findElement(By.xpath("//input[@aria-label='Tier Requested']")),"Tier Requested");
		clickTab(5);
		Select AccountType= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")));		
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =AccountType.getOptions();

		List<String> selectoptions =new ArrayList<String>();

		for(int i=0;i<mylist.size();i++) {

			selectoptions.add(mylist.get(i).getText());

		}

		System.out.println(selectoptions);
		assertTrue((selectoptions.contains("Successor Contract Update")), "Sucessor Contract Updated");

		return this;
	}

	//Select Successor Contract Update as Attachment Reason
	public ContractAttachmentPage selectAttachmentReasons(String Attachment_Reason) throws InterruptedException, AWTException {

		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")), Attachment_Reason, "Attachment_Reason");
		return this;
	}



	//Verify Attachment Reasons
	public ContractAttachmentPage verifyAttachmentReasonsisDeleted() throws InterruptedException, AWTException {

		String attachmentReason = getDriver().findElement(By.xpath("//select[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")).getAttribute("title");
		assertTrue(attachmentReason.contentEquals("---"));
		return this;
	}

	//Verify Attachment Reasons
	public ContractAttachmentPage verifySucessorAttachmentReason() throws InterruptedException, AWTException {


		Select AccountType= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select']")));		
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =AccountType.getOptions();		
		assertTrue(!(mylist.contains("Successor Contract Update")), "Sucessor Contract Updated");
		return this;
	}

	//Verify Vendor status and Vendor status date are not displayed
	public ContractAttachmentPage verifyVendorStatusIsNotDisplayed() throws InterruptedException, AWTException {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//label[contains(text(),'Vendor Status')]")).size(), "Vendor Status");
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//label[contains(text(),'Vendor Status Date')]")).size(), "Vendor Status Date");
		return this;
	}


	//Verify Request ID status date are not displayed
	public ContractAttachmentPage verifyRequestIDIsNotDisplayed() throws InterruptedException, AWTException {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//label[contains(text(),'Request ID')]")).size(), "Request ID");

		return this;
	}

	//Select Open All Contract Attachment View
	public ContractAttachmentPage openAllContractAttachmentView() throws InterruptedException   {

		Thread.sleep(10000);
		List<WebElement> dropdown= getDriver().findElements(By.xpath("(//span[contains(@id,'ViewSelector')])[4]"));
		if(dropdown.size()>0) {
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[4]")),"Select a view");
		}else {
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[2]")),"Select a view");
		}
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'All Contract Attachment')]")),"All Contract Attachment");
		Thread.sleep(15000);


		Thread.sleep(2000);
		return this;
	}

	//Click the Edit button for the contract Attachment
	public ContractAttachmentPage clickEditButton() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractattachment|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contractattachment.Edit']")),"Edit Button");
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(180));

		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//ul[@aria-label='Contract Attachment Form']"))));



		return this;
	}



	//Select the Existing Contract Attachment
	public ContractAttachmentPage selectExistingContractAttachment() throws InterruptedException   {
		Actions actions= new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//input[@aria-label='Select or deselect the row']"))).click().build().perform();
		Thread.sleep(2000);
		clickEditButton();

		return this;
	}

	//Click Suppliers Tab on CA

	public ContractAttachmentPage clickSystemTabOnCA() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//li[@aria-label='System']")),"Sysyem Tab");
		Thread.sleep(3000);
		return this;

	}

	//Click Verify the Assigned To Field

	public ContractAttachmentPage verifyAssginedToFeild(String AssignedToFeild) throws InterruptedException {
		String AssignedType;
		if(getDriver().findElements(By.xpath("(//div[@data-id='ownerid.fieldControl-LookupResultsDropdown_ownerid_selected_tag_text'])[2]")).size()>0) {
			AssignedType=getDriver().findElement(By.xpath("(//div[@data-id='ownerid.fieldControl-LookupResultsDropdown_ownerid_selected_tag_text'])[2]")).getText();

		}else{
			AssignedType=getDriver().findElement(By.xpath("//div[@data-id='ownerid.fieldControl-LookupResultsDropdown_ownerid_selected_tag_text']")).getAttribute("title");
		}

		System.out.println(AssignedType);
		System.out.println(AssignedToFeild);
		assertTrue(AssignedType.equalsIgnoreCase(AssignedToFeild));
		return this;


	}

	public ContractAttachmentPage verifyAssginedToFeildUser(String AssignedToFeild) throws InterruptedException {
		String AssignedType;
		if(getDriver().findElements(By.xpath("(//div[@data-id='ownerid.fieldControl-LookupResultsDropdown_ownerid_selected_tag_text'])[2]")).size()>0) {
			AssignedType=getDriver().findElement(By.xpath("(//div[@data-id='ownerid.fieldControl-LookupResultsDropdown_ownerid_selected_tag_text'])[2]")).getText();

		}else{
			AssignedType=getDriver().findElement(By.xpath("//div[@data-id='ownerid.fieldControl-LookupResultsDropdown_ownerid_selected_tag_text']")).getText();
		}

		System.out.println(AssignedType);
		System.out.println(AssignedToFeild);
		assertTrue(AssignedType.equalsIgnoreCase(AssignedToFeild));
		return this;


	}

	//Attachment Status REason is locked
	public ContractAttachmentPage verifyattachmentStatusReasonisLocked() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//select[@data-id='ix_attachmentstatusreason.fieldControl-option-set-select' and @disabled]")).size(), "AttachmentStatusreason");

		return this;
	}
	
	
	
	
}
