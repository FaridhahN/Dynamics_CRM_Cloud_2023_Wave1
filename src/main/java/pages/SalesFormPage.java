package pages;


import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import services.WebDriverServiceImpl;
import utils.TestUtils;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import driver.Driver;

public class SalesFormPage extends WebDriverServiceImpl {

	public SalesFormPage changeViewinOppurtunityPage(String viewType) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[contains(@data-id,'ViewSelector')]//i[@data-icon-name='ChevronDown']")),"View Selector Button");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'"+viewType+"')]")),"View Selector Button");
		Thread.sleep(7000);
		return this;
	}
	//Verify Error message is not displauyed
		public SalesFormPage verifyErrorisNotDisplayed() throws InterruptedException {
			Thread.sleep(3000);
			List<WebElement> error=getDriver().findElements(By.xpath("//*[@data-id='errorDialog_subtitle']"));
			verifyElementisNotDisplayed(error.size(), "Error message");
			Thread.sleep(2000);
			return this;
		}

	public SalesFormPage changeViewinAccountsPage(String viewType) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[contains(@data-id,'ViewSelector')]//i[@data-icon-name='ChevronDown']")),"View Selector Button");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'"+viewType+"')]")),"View Selector Button");
		Thread.sleep(7000);
		return this;
	}
	
	//Verify Views are not displayed in Accounts View
	public SalesFormPage verifyViewNotinAccountsPage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[contains(@data-id,'ViewSelector')]//i[@data-icon-name='ChevronDown']")),"View Selector Button");
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'Accounts - Membership Providers')]")).size()==0);
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'Affiliate Group Lookup View')]")).size()==0);
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'Excluded Account Campaigns')]")).size()==0);
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'Membership Providers')]")).size()==0);
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'Membership Providers Lookup view')]")).size()==0);
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'My Active Members')]")).size()==0);
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'My Current Members (all)')]")).size()==0);
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'My Prospects')]")).size()==0);
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),\"My Sales Team's Prospects\")]")).size()==0);
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'Selected Account Campaigns')]")).size()==0);
		Thread.sleep(7000);
		return this;
	}
	
	//Verify Default View in Oppurtunity page
	
	public SalesFormPage verifyDefaultViewOppurtunityPage() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[contains(@id,'ViewSelector')]//span[contains(text(),'All Opportunities')]")).size(), "Default View");
		return this;
	}
	
	//Verify Views are not displayed in Accounts View
		public SalesFormPage verifyViewNotinOppurtunityPage() throws InterruptedException {
			click(getDriver().findElement(By.xpath("//button[contains(@data-id,'ViewSelector')]//i[@data-icon-name='ChevronDown']")),"View Selector Button");
			assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'Fiscal')]")).size()==0);
			assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'My Connections')]")).size()==0);
			assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'My Opportunities without Sequences')]")).size()==0);
			assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'Open Opportunities without Sequences')]")).size()==0);
			Thread.sleep(7000);
			return this;
		}
	public SalesFormPage changeViewInContactssPage(String viewType) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[contains(@data-id,'ViewSelector')]//i[@data-icon-name='ChevronDown']")),"View Selector Button");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'"+viewType+"')]")),"View Selector Button");
		Thread.sleep(7000);
		return this;
	}

	public SalesFormPage verifyColumnNameinContactView() throws InterruptedException {
		ArrayList<String> actualColumn= new ArrayList<String>();
		Thread.sleep(3000);
		for(int i=1;i<=getDriver().findElements(By.xpath("//div[@data-testid='columnHeader']//label/div")).size();i++) {
		actualColumn.add(getDriver().findElement(By.xpath("(//div[@data-testid='columnHeader']//label/div)["+i+"]")).getText());
		if(i==7) {
			click(getDriver().findElement(By.xpath("//div[@data-id='data-set-quickFind-container']/input")),"search in filter button");
			clickTab(2);
			clickshiftTab(1);
			
		}
		}
		
		List<String> expectdoption = Arrays.asList("First Name","Last Name","Primary Account", "Job Title","Email","Phone #","Street 1","City","State/Province","ZIP/Postal Code");		

		if(actualColumn.containsAll(expectdoption))
		{		
			Thread.sleep(3000);
			setReport().log(Status.PASS, "RepresentativeType- " + "   " + actualColumn + "  " +  "-  Option is available to choose from the list" + " "+ expectdoption,	screenshotCapture());

		} 
		else {
			setReport().log(Status.FAIL, "RepresentativeType - "+   "   " + actualColumn + "  " + "- Option is not available in the list"  + " "+ expectdoption ,	screenshotCapture());
			Driver.failCount++;
		}

		return this;	
	}

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
	
	
	public SalesFormPage clickContacts() {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Contacts')]")),"Contact button");
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


	public SalesFormPage clickSaveinOpportunities() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='opportunity|NoRelationship|Form|Mscrm.Form.opportunity.Save']")),"Save button");

		Thread.sleep(5000);
		return this;
	}

	public SalesFormPage assignToUser(boolean selfOrUser, String User) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='OverflowButton']")),"Overflow Button");
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[contains(text(),'Assign')]"))).click().build().perform();
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-id='Assign']")).size(), "Assign");
		if(selfOrUser) {
			selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='rdoMe_id.fieldControl-checkbox-select']")), "ME", "Self");

		}else {
			selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='rdoMe_id.fieldControl-checkbox-select']")), "User or team", "other user");
			click(getDriver().findElement(By.xpath("//input[@data-id='systemuserview_id.fieldControl-LookupResultsDropdown_systemuserview_id_textInputBox_with_filter_new']")),"User Textbox");
			type(getDriver().findElement(By.xpath("//input[@data-id='systemuserview_id.fieldControl-LookupResultsDropdown_systemuserview_id_textInputBox_with_filter_new']")),User,"User Texbox");
			action.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='systemuserview_id.fieldControl-LookupResultsDropdown_systemuserview_id_infoContainer']//span[contains(text(),'"+User+"')]"))).click().build().perform();

		}

		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Click Assign");

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


	//Verify Subline Error message
	public SalesFormPage verifygeneralErrormessageisNotDisplayed() throws InterruptedException {

		Thread.sleep(5000);
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//span[@data-id='warningNotification']")).size(),"errorMessage");

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

	public SalesFormPage verifyGutFeelOptioninView() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//div[@data-id='data-set-quickFind-container']/input")),"search in filter button");
		clickTab(2);
		clickshiftTab(1);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(text(),'Gut Feel')]")).size(),"Gut Fell option");					
		return this;
	}

	
	//Click Add Stage button
	public SalesFormPage clickAddColumn() throws InterruptedException {
	click(getDriver().findElement(By.xpath("//button[@id='columnEditor-btn']")),"column");	
	click(getDriver().findElement(By.xpath("//button[@id='AddColumnsBtn']")),"Add Columns");
	return this;
	}
	
	//Add Stage in the column
	public SalesFormPage addColumnInTheView(String column) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//div[@role='dialog']//input[@placeholder='Search' and contains(@class,'ms-SearchBox-field')]")),column,"Active Stage");
		if(getDriver().findElements(By.xpath("//label[contains(text(),'"+column+"')]")).size()>0) {
			doubleClick(getDriver().findElement(By.xpath("//label[contains(text(),'"+column+"')]")),"Click Active Stage");
		}
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//i[@data-icon-name='Clear']"))).click().build().perform();
		return this;
	}



	//Add Stage in the column
	public SalesFormPage clickApplyinEditColumn() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button//span[contains(text(),'Close')]")),"Close button");
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Apply')]")),"Apply Button");
		return this;
	}

	public SalesFormPage verifyOptionsInAccountsView() throws InterruptedException, ParseException {

		click(getDriver().findElement(By.xpath("//div[@data-id='data-set-quickFind-container']/input")),"search in filter button");
		clickTab(2);
		clickshiftTab(1);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(text(),'Account Type')]")).size(),"Account Type");
		for(int i=1;i<getDriver().findElements(By.xpath("((//div[@col-id='customertypecode'])//label[contains(@class,'ms-Label option')])")).size();i++) {
		
			assertTrue(getDriver().findElement(By.xpath("((//div[@col-id='customertypecode'])//label[contains(@class,'ms-Label option')])["+i+"]")).getAttribute("aria-label").contentEquals("Member"));
		}

		for(int i=1;i<(getDriver().findElements(By.xpath("//div[@col-id='ix_issponsor']//label[contains(@class,'ms-Label two')]")).size())-1;i++) {

			assertTrue(getDriver().findElement(By.xpath("(//div[@col-id='ix_issponsor']//label[contains(@class,'ms-Label two')])["+i+"]")).getAttribute("aria-label").contentEquals("Yes"));
		}

		for(int i=1;i<(getDriver().findElements(By.xpath("//div[@col-id='createdon']//label[contains(@class,'ms-Label label')]")).size())-1;i++) {

			String[] createdOn=getDriver().findElement(By.xpath("(//div[@col-id='createdon']//label[contains(@class,'ms-Label label')])["+i+"]")).getAttribute("aria-label").split("\\s+");
			assertTrue(TestUtils.differencebetweenDates(TestUtils.todaysDate(),createdOn[0])<=7);


		}
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(text(),'Is Sponsor')]")).size(),"Is Sponsor");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(text(),'Created On')]")).size(),"Created On");
		return this;
	}

	//Click shift Tab
	public SalesFormPage clickshiftTab(int number)  throws InterruptedException {
		for(int i=0;i<number;i++) {
			Actions a =new Actions(getDriver());
			a.keyDown(Keys.SHIFT).sendKeys(Keys.TAB).keyUp(Keys.SHIFT).build().perform();
			Thread.sleep(3000);
		}
		return this;
	}

	public SalesFormPage enterTopic(String topic)  throws InterruptedException {

		type(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")),topic,"Topic");
		return this;
	}

	//Verify Gut Feel options	
	public SalesFormPage verifyGutFeel()  throws InterruptedException {

		ArrayList<String> selectoptions=new ArrayList<String>();
		Select gutFeel= new  Select(getDriver().findElement(By.xpath("//select[@data-id='ix_gutfeel.fieldControl-option-set-select']")));

		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> option =gutFeel.getOptions();	

		List<String> expectdoption = Arrays.asList("---","Very Likely","Likely","Neutral","Not Likely");		


		for(int i=0;i<option.size();i++) {

			selectoptions.add(option.get(i).getText());

		}

		if(selectoptions.containsAll(expectdoption))
		{		
			Thread.sleep(3000);
			setReport().log(Status.PASS, "gutFeel- " + "   " + selectoptions + "  " +  "-  Option is available to choose from the list" + " "+ expectdoption,	screenshotCapture());

		} 
		else {
			setReport().log(Status.FAIL, "gutFeel - "+   "   " + selectoptions + "  " + "- Option is not available in the list"  + " "+ expectdoption ,	screenshotCapture());
			Driver.failCount++;
		}

		return this;
	}

	//Select Gut feel
	public SalesFormPage selectGutFeel(String gutfeel)  throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_gutfeel.fieldControl-option-set-select']")), gutfeel, "gutfeel");
		return this;
	}

	//Navigating to Member
	public SalesFormPage navigateToMemberField()  throws InterruptedException {
		clickTab(1);
		clickAndEsc(getDriver().findElement(By.xpath("//input[@data-id='ix_revenuecategory.fieldControl-LookupResultsDropdown_ix_revenuecategory_textInputBox_with_filter_new']")),"Revenue Category");
		clickTab(4);
		return this;	
	}
	
	
	//Entering Select Member
		public SalesFormPage selectMember(String member)  throws InterruptedException {
			click(getDriver().findElement(By.xpath("//input[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")),"Member accoiunt");
			type(getDriver().findElement(By.xpath("//input[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")),member,"Member");
			
			Actions action = new Actions(getDriver());
			action.moveToElement(getDriver().findElement(By.xpath("//span[@data-id='parentaccountid.fieldControl-accountnumber1_0_0']/span[contains(text(),'"+member+"')]"))).perform();
			click(getDriver().findElement(By.xpath("//span[@data-id='parentaccountid.fieldControl-accountnumber1_0_0']/span[contains(text(),'"+member+"')]")),"Member"); 
			
			
			return this;	
		}
		
		//Verify MEmber Details pulled from CRM
		
		public SalesFormPage verifyMemberDetails()  throws InterruptedException {
			clickTab(3);
			assertTrue(!(getDriver().findElement(By.xpath("//input[@data-id='AccountQuickview.accountnumber.fieldControl-text-box-text']")).getAttribute("value").isEmpty()));
			assertTrue(!(getDriver().findElement(By.xpath("//input[@data-id='AccountQuickview.ix_dea.fieldControl-text-box-text']")).getAttribute("value").isEmpty()));
			assertTrue(!(getDriver().findElement(By.xpath("//input[@data-id='AccountQuickview.ix_hin.fieldControl-text-box-text']")).getAttribute("value").isEmpty()));
			clickTab(2);
			assertTrue(!(getDriver().findElement(By.xpath("//input[@data-id='AccountQuickview.ix_premierein.fieldControl-text-box-text']")).getAttribute("value").isEmpty()));
			assertTrue(!(getDriver().findElement(By.xpath("//div[@data-id='AccountQuickview.ix_sponsor.fieldControl-LookupResultsDropdown_ix_sponsor_selected_tag_text']")).getText().isEmpty()));
			assertTrue(!(getDriver().findElement(By.xpath("//div[@data-id='AccountQuickview.parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_selected_tag_text']")).getText().isEmpty()));
			
			return this;
		}

	//Verify Project NAF and Estimated close date in opputrunity form
	public SalesFormPage verifyFeildsinOppurtunitiesForm()  throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[contains(text(),'Est. close date')]")).size(), "Es close Date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[contains(text(),'Projected NAF')]")).size(), "Projected NAF");
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//label[contains(text(),'Estimated Revenue')]")).size(), "Estimated Revenue");
		return this;
	}
	
	//Enter Estimated Close date
	public SalesFormPage typeEstimatedCloseDate(String closeDate)  throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@data-id='estimatedclosedate.fieldControl-date-time-input']")),closeDate,"Estimated Close Date");
		
		return this;
		
	}
	
	//Enter anticipated start date
		public SalesFormPage typeanticipatedPurchaseStarDate(String anticipatedPurchaseStarDate)  throws InterruptedException {
			type(getDriver().findElement(By.xpath("//input[@data-id='ix_anticipatedstartpurchasedate.fieldControl-date-time-input']")),anticipatedPurchaseStarDate,"anticipatedPurchaseStarDate");
			
			return this;
			
		}
		
		//Enter anticipated start date
				public SalesFormPage typeProjectedNAF(String ProjectedNaf)  throws InterruptedException {
					type(getDriver().findElement(By.xpath("//input[@data-id='estimatedvalue.fieldControl-currency-text-input']")),ProjectedNaf,"ProjectedNaf");
					
					return this;
					
				}
		//Click Go back
				public SalesFormPage clickgoBack()  throws InterruptedException {
					click(getDriver().findElement(By.xpath("//button[@title='Go back']")),"back button");
					return this;
					
				}
		
//Search oppurtunity
				
				public SalesFormPage searchOpportunity(String opportunity) throws InterruptedException {
					click(getDriver().findElement(By.xpath("//input[@aria-label='Opportunity Filter by keyword']")),"Filter Text box");
					typeAndEnter(getDriver().findElement(By.xpath("//input[@aria-label='Opportunity Filter by keyword']")), opportunity, opportunity);
					WebDriverWait wait=new WebDriverWait(getDriver(), Duration.ofSeconds(10));
					wait.until(ExpectedConditions.invisibilityOf(getDriver().findElement(By.xpath("//div[@id='datasethost-progress-indicator']"))));
					verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@col-id='name']//a//span[contains(text(),'"+opportunity+"')]")).size(), opportunity);
					return this;
				}
				
				//verify the Opputunity column
				
				public SalesFormPage verifyOppurtunityviewColumn() throws InterruptedException {
				
					click(getDriver().findElement(By.xpath("//button[@id='columnEditor-btn']")),"column");	
					ArrayList<String> actualColumn= new ArrayList<String>();
					Thread.sleep(3000);
					for(int i=1;i<=getDriver().findElements(By.xpath("//div[@draggable='true']/div[@role='listitem']/span")).size();i++) {
					actualColumn.add(getDriver().findElement(By.xpath("(//div[@draggable='true']/div[@role='listitem']/span)["+i+"]")).getText());

					}
					
					List<String> expectdoption = Arrays.asList("Topic","Description","Top Parent (Member Account)", "Member Account","Entity Code (Member Account)","Channel Partner Rev Category","Projected NAF","Est. close date","Gut Feel","Modified On","Modified By","Owner");		

					if(actualColumn.containsAll(expectdoption))
					{		
						Thread.sleep(3000);
						setReport().log(Status.PASS, "RepresentativeType- " + "   " + actualColumn + "  " +  "-  Option is available to choose from the list" + " "+ expectdoption,	screenshotCapture());

					} 
					else {
						setReport().log(Status.FAIL, "RepresentativeType - "+   "   " + actualColumn + "  " + "- Option is not available in the list"  + " "+ expectdoption ,	screenshotCapture());
						Driver.failCount++;
					}

					assertTrue(!(expectdoption.contains("Fax")));
					assertTrue(!(expectdoption.contains("Active Stage")));
					assertTrue(!(expectdoption.contains("Revenue Category")));
					return this;
				}
				
				//Add Stage in the column
				public SalesFormPage clikCancelButton() throws InterruptedException {

					click(getDriver().findElement(By.xpath("//span[contains(text(),'Cancel')]")),"Apply Button");
					return this;
				}

}

