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

public class ContractVolume extends WebDriverServiceImpl{


		//Verify New Contract attachment Button is displayed
	public ContractVolume verifyNewContractAttachmentButton() {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_contractvolume|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contractvolume.AddNewStandard']")).size() ,"Attachment Status");
		return this;
	}
	
	//Verify New Contract attachment Button is not displayed
		public ContractVolume verifyNewContractAttachmentButtonisnotDisplayed() {
			verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_contractvolume|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contractvolume.AddNewStandard']")).size() ,"Attachment Status");
			return this;
		}

		
		
		//Click Contract attachment
				public ContractVolume clickNewContractAttachment() {
					click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractvolume|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contractvolume.AddNewStandard']")) ,"Attachment Status");
					return this;
				}

	//clikc signout
		public ContractVolume clickSignout() {
			click(getDriver().findElement(By.id("mectrl_headerPicture")), "Signout Icon");
			click(getDriver().findElement(By.id("mectrl_body_signOut")), "Signout button");
			return this;
		}
		
		// perform page refresh
		public ContractVolume pageRefresh() throws InterruptedException {
			getDriver().navigate().refresh();
			Thread.sleep(5000);
			return this;
		}
		
		//Add ne Contract Volume
		public ContractVolume addContractVolume(String contractnumber, String purchasePeriod, String purchaseMonth) {
			click(getDriver().findElement(By.xpath("//input[@data-id='ix_contractid.fieldControl-LookupResultsDropdown_ix_contractid_textInputBox_with_filter_new']")),"Contract Volumne");
			type(getDriver().findElement(By.xpath("//input[@data-id='ix_contractid.fieldControl-LookupResultsDropdown_ix_contractid_textInputBox_with_filter_new']")),contractnumber,"contractnumber");
			
			WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+contractnumber+"')]")));
			
			click(getDriver().findElement(By.xpath("//span[contains(text(),'"+contractnumber+"')]")),"member");

			type(getDriver().findElement(By.xpath("//input[@aria-label='Purchase Period']")),purchasePeriod,"purchasePeriod");
			
			clickSystemTab();
			
			type(getDriver().findElement(By.xpath("//input[@data-id='ix_purchaseperiodstart.fieldControl-date-time-input']")),purchaseMonth,"purchaseMonth");
			
			return this;
		}
		
		//Verify Error message is not displauyed
		public ContractVolume verifyErrorisNotDisplayed() throws InterruptedException {
			Thread.sleep(3000);
			List<WebElement> error=getDriver().findElements(By.xpath("//*[@data-id='errorDialog_subtitle']"));
			verifyElementisNotDisplayed(error.size(), "Error message");
			Thread.sleep(2000);
			return this;
		}
		
		//click system tab
				public ContractVolume clickSystemTab() {
					click(getDriver().findElement(By.xpath("//li[@aria-label='System']")),"System button");
					return this;
				} 
		
		//click save button
		public ContractVolume clicksaveButton() {
			click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractvolume|NoRelationship|Form|Mscrm.Form.ix_contractvolume.Save']")),"Save button");
			return this;
		}

		
		//click save and close button
				public ContractVolume clicksaveAndCloseButton() {
					click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractvolume|NoRelationship|Form|Mscrm.Form.ix_contractvolume.SaveAndClose']")),"Save and close button");
					return this;
				}

		//select the contract volume
				public ContractVolume selectfirstContractVolume() {
					Actions action = new Actions(getDriver());
					action.moveToElement(getDriver().findElement(By.xpath("//*[@aria-label='Select or deselect the row']"))).click().build().perform();
					return this;
				}
				
				//Click on deactivate in Contract volume
				public ContractVolume clickDeactivateContractVolume() throws InterruptedException {
					Thread.sleep(3000);
					click(getDriver().findElement(By.xpath("//button[@data-id='ix_contractvolume|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_contractvolume.Deactivate']")),"Deactivate");
					Thread.sleep(3000);
					click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")),"Confirm Deactivate");
					Thread.sleep(3000);
					return this;	
				}
				
}
