package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import services.WebDriverServiceImpl;

public class EmailList extends WebDriverServiceImpl{
	
	//Click New Email List
		public EmailList clickNewEmailList() throws InterruptedException {
		
			click(getDriver().findElement(By.xpath("//button[@data-id='ix_emaillist|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.ix_emaillist.NewRecord']")),"Click new Email List");
			return this;
		}
	
		//Enter Email List details
		public EmailList enterEmailDetails(String name, String type) {
			
			type(getDriver().findElement(By.xpath("//input[@data-id='ix_name.fieldControl-text-box-text']")),name,"Name");
			selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_type.fieldControl-option-set-select']")), type, "Type");
			click(getDriver().findElement(By.xpath("//button[@data-id='ix_emaillist|NoRelationship|Form|Mscrm.Form.ix_emaillist.SaveAndClose']")),"SaveAndClose");
			return this;
		}
		
		// Account is in draft message should not be displayed
		public EmailList VerifyErrorMessageNotDisplayed() throws InterruptedException {
			Thread.sleep(2000);

			List<WebElement> message=getDriver().findElements(By.xpath("//*[@data-id='errorDialog_subtitle']"));

			verifyElementisNotDisplayed(message.size(), "error message");
			return this;
		}

}
