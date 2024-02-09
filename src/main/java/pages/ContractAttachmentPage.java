package pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
				"Not Approved by Manufacturer","Not Purchasing through Distributor" , "Requested Eligibility from Supplier", "Pending Request","Ineligible Class of Trade","Expired Contract","Premier Roster Update Required");		
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
				"Pending Paperwork","Request from Vendor");		
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
		
		

}