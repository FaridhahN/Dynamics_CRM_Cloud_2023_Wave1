package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import services.WebDriverServiceImpl;

public class ContractAttachmentPage extends WebDriverServiceImpl{


	//Select All Contract Attachments view
	public ContractAttachmentPage selectAllContractAttachentsView() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("(//*[@data-icon-name='ChevronDown'])[2]")),"Select a view");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'All Contract Attachments')]")),"All Contract Attachments");
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("(//*[contains(@col-id,'ix_premierein')])[2]"))));
		return this;
	} 	

	//Double Click on First CA in All ContractAttachmentsView
	public ContractAttachmentPage openFirstContractAttachment() {
		Actions a=new Actions(getDriver());
		a.doubleClick(getDriver().findElement(By.xpath("(//*[contains(@col-id,'ix_premierein')])[2]"))).build().perform();;
		return new ContractAttachmentPage();

	}

	//Click Suppliers Tab on CA

	public ContractAttachmentPage clickSuppliersTabOnCA() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//li[@title='Suppliers']")),"Supplier Tab");
		Thread.sleep(3000);
		return new ContractAttachmentPage();

	}

	//+New Contract Attachment Supplier
	public ContractAttachmentPage clickNewContractAttachmentSupplierButton() {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contractattachmentsupplier|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_contractattachmentsupplier.AddNewStandard']")),"+ New Contract Attachment Supplier");
		return new ContractAttachmentPage();

	}
	
	//Default Value  on Attachment Status field is not 'Attachment Requested'
		public ContractAttachmentPage verifyAttachmentStatusDefaultValueOnCASupplierIsNotAttachmentRequested() {
			verifyTextDoesNotMatchTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_supplierattachmentstatus.fieldControl-option-set-select']")),"Attachment Requested" ,"Attachment Status");
			return new ContractAttachmentPage();

		}
		
		//Default Value  on Attachment Status field is '--Select--'
				public ContractAttachmentPage verifyAttachmentStatusDefaultValueOnCASupplierIsSelect() {
					verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_supplierattachmentstatus.fieldControl-option-set-select']")),"---" ,"Attachment Status");
					return new ContractAttachmentPage();

				}


}
