package pages;


import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import services.WebDriverServiceImpl;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesFormPage extends WebDriverServiceImpl {

	public SalesFormPage changeView(String viewType) throws InterruptedException {

		click(getDriver().findElement(By.xpath("//*[@data-id='form-selector']")),"view selector");

		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+viewType+"')]")),"view selector");
		List<WebElement> element=getDriver().findElements(By.xpath("//*[@data-id='cancelButton']"));
		if(element.size()>0) {
			click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Discard Changes");
		}
		Thread.sleep(2000);
		
		
		return this;
	}

	public SalesFormPage clickAccounts() {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Accounts')]")),"Account button");
		return this;
	}
	
	public SalesFormPage clickOppurtunitiesPage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//li[@aria-label='Opportunities']")),"Oppurtunities button");
		Thread.sleep(5000);
		return this;
	}
	
	
	public SalesFormPage ClickNewOppurtunity() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='opportunity|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.opportunity.NewRecord']")),"ClickNewOppurtunity");
		Thread.sleep(5000);
		return this;
	}
	
	
	public SalesFormPage navigateToRevenueCategory() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")),"click Account Name");
		click(getDriver().findElement(By.xpath("//textarea[@data-id='description.fieldControl-text-box-text']")),"Description Feild");
		clickTab(2);
		click(getDriver().findElement(By.xpath("//input[@data-id='estimatedclosedate.fieldControl-date-time-input']")),"Estimated close date");
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_anticipatedstartpurchasedate.fieldControl-date-time-input']")),"Anticipated Purchase date");
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_pipelineinitiative.fieldControl-LookupResultsDropdown_ix_pipelineinitiative_textInputBox_with_filter_new']")),"Pipeline intiative");
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_pipelineinitiative.fieldControl-LookupResultsDropdown_ix_pipelineinitiative_textInputBox_with_filter_new']")),"Pipeline intiative");
		Thread.sleep(5000);
		return this;
	}
	
	
	
	//Verify Admin fee
		public  SalesFormPage verifyAdminFee(String adminfee) throws InterruptedException {
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//input[@data-id='ix_adminfee.fieldControl-decimal-number-text-input']")), adminfee, "Admin Fedd");
			return this;
		}
		
		

		//Clear Revenue Category
		public SalesFormPage clearRevenueCategory() throws InterruptedException {

			Actions action = new Actions(getDriver());
			action.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_selected_tag_text']"))).perform();
			click(getDriver().findElement(By.xpath("//button[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_selected_tag_delete']")),"Clear Icon"); 
			Thread.sleep(3000);
			return this;	
		}

	//Select pipeline stage from the lookup
		public  SalesFormPage selectRevenueCategory(String Category) throws InterruptedException {
			type(getDriver().findElement(By.xpath("//input[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_textInputBox_with_filter_new']")),Category,"Category");

			WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));

			wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//li[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_resultsContainer']//span[contains(@data-id,'ix_revenuecategory.fieldControl-ix_name')]//span[contains(text(),'"+Category+"')]"))));

			click(getDriver().findElement(By.xpath("//li[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_resultsContainer']//span[contains(@data-id,'ix_revenuecategory.fieldControl-ix_name')]//span[contains(text(),'"+Category+"')]")),"Category");
			return this;
		}
	
	//
	
	public SalesFormPage verifyOppurtunitiesOption() throws InterruptedException
	{
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(2000);
	
		assertTrue((getDriver().findElements(By.xpath("//div[@role='menuitem']//*[contains(text(),'Opportunities - Member')]")).size()==1));
		return this;
	}


	public SalesFormPage clickNewAccounts() {
		click(getDriver().findElement(By.xpath("//button[@data-id='account|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.account.NewRecord']")),"New Account page");
		return this;
	}

	public SalesFormPage enterAccountName(String accountName) {
		type(getDriver().findElement(By.xpath("//input[@aria-label='Account Name']")), accountName,"accountName");


		return this;
	}

	//Click Tab
	public SalesFormPage clickTab(int number)  throws InterruptedException {
		for(int i=0;i<number;i++) {
			Actions a =new Actions(getDriver());
			a.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(3000);
		}
		return this;
	}

	public SalesFormPage clickSave() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Save']")),"");

		Thread.sleep(5000);
		return this;
	}


	public SalesFormPage navigatetoFeeshare() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//input[@aria-label='Account Name 2']")),"Account name");
		clickTab(11);
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_ofestimatedlocations.fieldControl-whole-number-text-input']")),"Account name");
		clickTab(8);
		return this;
	}
	
	public SalesFormPage verifyFeeShare() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//input[@aria-label='Fee Share %']")).size(),"Fee share");
		return this;
	}

	public SalesFormPage clickOppurtunitiesButton() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//li[contains(@id,'tab') and @title='Opportunities']")),"Oppurtunities");
		
		return this;
	}
	
	
	public SalesFormPage verifyMemberAccountGridIsNotDisplayed() throws InterruptedException {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//*[contains(text(),'Member Account')]")).size(),"Member Account Grid");
		return this;
	}
	
	
	public SalesFormPage updateFeeShare(String feeshare) throws InterruptedException {
		typeAndEnter(getDriver().findElement(By.xpath("//input[@aria-label='Fee Share %']")),feeshare,"feeshare");
		return this;
	}

	//Verify Subline Error message
	public SalesFormPage verifysubLineErrorMessage(String errorMessage) throws InterruptedException {

		Thread.sleep(5000);
		verifyDisplayed(getDriver().findElement(By.xpath("//span[contains(text(),'"+errorMessage+"')]")),errorMessage);

		return this;

	}

	//Verify Subline Error message
	public SalesFormPage verifyErrormessage(String errorMessage) throws InterruptedException {

		Thread.sleep(5000);
		verifyDisplayed(getDriver().findElement(By.xpath("//span[@data-id='warningNotification' and contains(text(),'"+errorMessage+"')]")),errorMessage);

		return this;

	}



	//Verify Subline Error message
	public SalesFormPage verifysubLineErrorMessageNotDisplayed(String errorMessage) throws InterruptedException {

		Thread.sleep(5000);
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//span[contains(text(),'"+errorMessage+"')]")).size(),errorMessage);

		return this;

	}

	//Verify Subline Error message
	public SalesFormPage verifyErrormessageisNotDisplayed(String errorMessage) throws InterruptedException {

		Thread.sleep(5000);
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//span[@data-id='warningNotification' and contains(text(),'"+errorMessage+"')]")).size(),errorMessage);

		return this;

	}
	
	//Click Activities
	public SalesFormPage clickActivities() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//li[@aria-label='Activities']")),"Activities button");
		Thread.sleep(4000);
		return this;
	}

	//Click Activities
		public SalesFormPage clickAppointment() throws InterruptedException {
			click(getDriver().findElement(By.xpath("//button[@data-id='activitypointer|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.activitypointer.NewAppointment.modern.Appointment']")),"Click Appointment button");
			WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));

			wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//h1[contains(text(),'New Appointment')]"))));
			Thread.sleep(4000);
			return this;
		}

	
		//Type Subject
		public SalesFormPage typeSubject(String subject) throws InterruptedException {
			type(getDriver().findElement(By.xpath("//input[@data-id='subject.fieldControl-text-box-text']")),subject,"Activities button");
			Thread.sleep(4000);
			return this;
		}
		
		//Select Regarding 
		public SalesFormPage selectRagrding( String entityCode) throws InterruptedException, IOException   {
			click(getDriver().findElement(By.xpath("//input[@aria-label='Regarding, Lookup']")),"Click the REgarding text box");
			type(getDriver().findElement(By.xpath("//input[@aria-label='Regarding, Lookup']")),entityCode, "Entity code");
			WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@data-id,'regardingobjectid.fieldControl-ix_premierein')]")));
			click(getDriver().findElement(By.xpath("//span[contains(text(),'"+entityCode+"')]")),"Direct Parent");
			return this;
		}
		
		//verify Internal notes
				public SalesFormPage verifyInternalNotes() throws InterruptedException {
					verifyElementisDisplayed(getDriver().findElements(By.xpath("//h2[contains(text(),'Internal Notes')]")).size(),"Internal notes scetion");
					verifyElementisDisplayed(getDriver().findElements(By.xpath("//textarea[@data-id='ix_internalnotes.fieldControl-text-box-text']")).size(),"Internal notes test area");
					Thread.sleep(4000);
					return this;
				}
				
				//Type internal notes
				public SalesFormPage typeInternalNotes(String notes) throws InterruptedException {
					type(getDriver().findElement(By.xpath("//textarea[@data-id='ix_internalnotes.fieldControl-text-box-text']")),notes,"Internal notes test area");
					Thread.sleep(4000);
					return this;
				}
				
				//verify SalesHub option
				public SalesFormPage verifySalesHubOption() throws InterruptedException {
					click(getDriver().findElement(By.xpath("//li[@aria-label='Sales accelerator']")),"Sales accelerator");
					click(getDriver().findElement(By.xpath("//li[@aria-label='Activities']")),"Activities button");	
					click(getDriver().findElement(By.xpath("//li[@aria-label='Dashboards']")),"Dashboards button");
					click(getDriver().findElement(By.xpath("//li[@aria-label='Accounts']")),"Accounts button");
					click(getDriver().findElement(By.xpath("//li[@aria-label='Contacts']")),"contacts button");
					click(getDriver().findElement(By.xpath("//li[@aria-label='Contract Attachments']]")),"Contract Attachments");
					click(getDriver().findElement(By.xpath("//li[@aria-label='Leads']")),"Leads");
					click(getDriver().findElement(By.xpath("//li[@aria-label='Opportunities']")),"Opportunities");
					click(getDriver().findElement(By.xpath("//li[@aria-label='Forecasts']")),"Forecasts");
					click(getDriver().findElement(By.xpath("//li[@aria-label='Roster']")),"Roster");
					return this;
				}
		
}