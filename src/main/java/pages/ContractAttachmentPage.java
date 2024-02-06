package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
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
	public ContractAttachmentPage clickSaveOnCASupplier() {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contractattachmentsupplier|NoRelationship|Form|Mscrm.Form.ix_contractattachmentsupplier.Save']")),"Save CA Supplier");
		return this;

	}

	//Click Save on CA Supplier
	public ContractAttachmentPage clickDeactivateOnCASupplier() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contractattachmentsupplier|NoRelationship|Form|Mscrm.Form.ix_contractattachmentsupplier.Deactivate']")),"Deactivate On CA Supplier");
		click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")),"Confirm Deactivate On CA Supplier");
		Thread.sleep(3000);
		return this;
	}
}