package pages;


import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Entities.EscapeMode;
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
		click(getDriver().findElement(By.xpath("//*[contains(text(),\""+viewType+"\")]")),"View Selector Button");
		Thread.sleep(10000);
		return this;
	}
	
	public SalesFormPage changeViewinAccountsPageAfterSearch(String viewType) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[contains(@data-id,'ViewSelector')]//i[@data-icon-name='ChevronDown']")),"View Selector Button");
		type(getDriver().findElement(By.xpath("//input[@placeholder='Search views']")), viewType, "View type");
		click(getDriver().findElement(By.xpath("//*[contains(text(),'"+viewType+"')]")),"View Selector Button");
		Thread.sleep(7000);
		return this;
	}
	
	
	
	public SalesFormPage changeViewinDashboard(String viewType) throws InterruptedException {
		if(getDriver().findElements(By.xpath("(//*[contains(text(),'"+viewType+"')])")).size()==0) {
		click(getDriver().findElement(By.xpath("//div[@title='Dashboard Selector']")),"View Selector Button");
		click(getDriver().findElement(By.xpath("(//*[contains(text(),'"+viewType+"')])")),"View Selector Button");
		}
		Thread.sleep(7000);
		return this;
	}


	public SalesFormPage openFirstAccount() throws InterruptedException {
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[@col-id='name']//a//span"))).doubleClick().build().perform();
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


	//Verify Views are not displayed in Accounts View
	public SalesFormPage verifySystemViewNotinAccountsPage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[contains(@data-id,'ViewSelector')]//i[@data-icon-name='ChevronDown']")),"View Selector Button");
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'Influenced Deals that We Won')]")).size()==0);
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'Accounts: No Activities in Last 3 Months')]")).size()==0);
		assertTrue(getDriver().findElements(By.xpath("//label[contains(text(),'Accounts: Responded to Campaigns')]")).size()==0);
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
		Thread.sleep(10000);
		return this;
	}

	public SalesFormPage verifyColumnNameinContactView() throws InterruptedException {
		ArrayList<String> actualColumn= new ArrayList<String>();
		Thread.sleep(3000);
		
		click(getDriver().findElement(By.xpath("//button[@id='columnEditor-btn']")),"column");	
		
		Thread.sleep(3000);
		for(int i=1;i<=getDriver().findElements(By.xpath("//div[@draggable='true']/div[@role='listitem']/span")).size();i++) {
			actualColumn.add(getDriver().findElement(By.xpath("(//div[@draggable='true']/div[@role='listitem']/span)["+i+"]")).getText());

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


	
	public SalesFormPage clickAccounts() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Accounts')]")),"Account button");
		Thread.sleep(6000);
		return this;
	}


	public SalesFormPage clickDashboard() {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Dashboards')]")),"Dashboards button");
		return this;
	}


	public SalesFormPage clickActivitiesInLeftPane() {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Activities')]")),"Dashboards button");
		return this;
	}

	public SalesFormPage clickEmailUnderActivity() {
		click(getDriver().findElement(By.xpath("//button[@data-id='activitypointer|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.activitypointer.NewEmail']")),"Email");
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

	//Change the form type
	public SalesFormPage changeOppurtunityFormType(String FormType) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//div[@data-id='form-selector']")),"click Form Selector");
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+FormType+"')]")),"click FormType");
		Thread.sleep(2000);
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


	//Click Tab
	public SalesFormPage clickEsc()  throws InterruptedException {
		Actions a =new Actions(getDriver());
		a.sendKeys(Keys.ESCAPE).build().perform();
		Thread.sleep(3000);
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
			selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='rdoMe_id.fieldControl-checkbox-select']")), "Me", "Self");

		}else {
			selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='rdoMe_id.fieldControl-checkbox-select']")), "User or team", "other user");
			click(getDriver().findElement(By.xpath("//input[@data-id='systemuserview_id.fieldControl-LookupResultsDropdown_systemuserview_id_textInputBox_with_filter_new']")),"User Textbox");
			type(getDriver().findElement(By.xpath("//input[@data-id='systemuserview_id.fieldControl-LookupResultsDropdown_systemuserview_id_textInputBox_with_filter_new']")),User,"User Texbox");
			action.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='systemuserview_id.fieldControl-LookupResultsDropdown_systemuserview_id_infoContainer']//span[contains(text(),'"+User+"')]"))).click().build().perform();

		}

		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Click Assign");
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
	public SalesFormPage selectRagrding( String regardingName) throws InterruptedException, IOException   {
		click(getDriver().findElement(By.xpath("//input[@aria-label='Regarding, Lookup']")),"Click the REgarding text box");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Regarding, Lookup']")),entityCode, "Entity code");
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-id='regardingobjectid.fieldControl-LookupResultsDropdown_regardingobjectid_resultsContainer']")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+regardingName+"')]")),"Direct Parent");
		return this;
	}

	//verify Internal notes
	public SalesFormPage verifyInternalNotes() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[contains(text(),'Internal Notes')]")).size(),"Internal notes scetion");
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
		click(getDriver().findElement(By.xpath("//li[@aria-label='Contract Attachments']")),"Contract Attachments");
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
		if(column.contentEquals("Est. Revenue (Base)")) {
			type(getDriver().findElement(By.xpath("//div[@role='dialog']//input[@placeholder='Search' and contains(@class,'ms-SearchBox-field')]")),"Revenue","Active Stage");
		}else {
			type(getDriver().findElement(By.xpath("//div[@role='dialog']//input[@placeholder='Search' and contains(@class,'ms-SearchBox-field')]")),column,"Active Stage");	
		}

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
		clickAndEsc(getDriver().findElement(By.xpath("//input[@data-id='ix_pipelineinitiative.fieldControl-LookupResultsDropdown_ix_pipelineinitiative_textInputBox_with_filter_new']")),"Pipeline Inititative");
		clickTab(6);
		return this;	
	}


	//Entering Select Member
	public SalesFormPage selectMember(String member)  throws InterruptedException {
		click(getDriver().findElement(By.xpath("//input[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")),"Member accoiunt");
		type(getDriver().findElement(By.xpath("//input[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")),member,"Member");

		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//span[@data-id='parentaccountid.fieldControl-accountnumber1_0_0']/span[contains(text(),'"+member+"')]"))).sendKeys(Keys.ENTER).perform();
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
		Thread.sleep(8000);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@col-id='name']//a//span[contains(text(),'"+opportunity+"')]")).size(), opportunity);
		return this;
	}


	//open Opportunity
	public SalesFormPage openOpportunity(String opportunity) throws InterruptedException {

		doubleClick(getDriver().findElement(By.xpath("//div[@col-id='name']//a//span[contains(text(),'"+opportunity+"')]")), opportunity);

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


	//verify the Opputunity column with Addded Column

	public SalesFormPage verifyOppurtunityviewColumnafternewColumnAdded() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@id='columnEditor-btn']")),"column");	
		ArrayList<String> actualColumn= new ArrayList<String>();
		Thread.sleep(3000);
		for(int i=1;i<=getDriver().findElements(By.xpath("//div[@draggable='true']/div[@role='listitem']/span")).size();i++) {
			actualColumn.add(getDriver().findElement(By.xpath("(//div[@draggable='true']/div[@role='listitem']/span)["+i+"]")).getText());

		}

		List<String> expectdoption = Arrays.asList("Topic","Description","Top Parent (Member Account)", "Member Account","Entity Code (Member Account)","Channel Partner Rev Category","Projected NAF","Est. close date","Gut Feel","Modified On","Modified By","Owner","Est. Revenue (Base)","Potential Customer","Active Stage");		

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


	//verify the Opputunity column with Addded Column

	public SalesFormPage verifyContactviewColumnafternewColumnAdded() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@id='columnEditor-btn']")),"column");	
		ArrayList<String> actualColumn= new ArrayList<String>();
		Thread.sleep(3000);
		for(int i=1;i<=getDriver().findElements(By.xpath("//div[@draggable='true']/div[@role='listitem']/span")).size();i++) {
			actualColumn.add(getDriver().findElement(By.xpath("(//div[@draggable='true']/div[@role='listitem']/span)["+i+"]")).getText());

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

	//verify the Opputunity column with Addded Column

	public SalesFormPage verifyAccountviewColumn() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@id='columnEditor-btn']")),"column");	
		ArrayList<String> actualColumn= new ArrayList<String>();
		Thread.sleep(3000);
		for(int i=1;i<=getDriver().findElements(By.xpath("//div[@draggable='true']/div[@role='listitem']/span")).size();i++) {
			actualColumn.add(getDriver().findElement(By.xpath("(//div[@draggable='true']/div[@role='listitem']/span)["+i+"]")).getText());

		}


		List<String> expectdoption = Arrays.asList("Account Name", "Class of Trade", "Entity Code" ,"Business Classification", "Premier Start Date", "Account Status", "Direct Parent", "Top Parent", "Corporate Parent Name", "Current Field Rep" , "Current Internal Rep","Street 1", "City", "State/Province", "ZIP/Postal Code");		

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


	//verify the Opputunity column with Addded Column

	public SalesFormPage verifyRelatedContactviewColumnafternewColumnAdded() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//button[@id='columnEditor-btn']")),"column");	
		ArrayList<String> actualColumn= new ArrayList<String>();
		Thread.sleep(3000);
		for(int i=1;i<=getDriver().findElements(By.xpath("//div[@draggable='true']/div[@role='listitem']/span")).size();i++) {
			actualColumn.add(getDriver().findElement(By.xpath("(//div[@draggable='true']/div[@role='listitem']/span)["+i+"]")).getText());

		}

		List<String> expectdoption = Arrays.asList("First Name","Last Name","Job Title","Email","Phone #","Street 1","City","State/Province","ZIP/Postal Code");		

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

	//Add Stage in the column
	public SalesFormPage clikCancelButton() throws InterruptedException {

		if(getDriver().findElements(By.xpath("//span[contains(text(),'Cancel')]")).size()>0) {
			click(getDriver().findElement(By.xpath("//span[contains(text(),'Cancel')]")),"Cancel Button");	
		}
		Thread.sleep(4000);

		return this;
	}

	//click Ligh Assistant
	public SalesFormPage clicklightassistantBulp() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='cardFeedContainerLauncher']")),"assistantBulp");
		return this;
	}


	//Verify Oppurtunity in the Light Assitant
	public SalesFormPage verifyOppurtunityinLightAssistant(String opportunity) throws InterruptedException {
		System.out.println(opportunity);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[normalize-space()='"+opportunity+"']")).size(), "opportunity");
		return this;
	}

	//VErify BPF

	public SalesFormPage verifyBPF() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(text(),'Channel Partner Opportunity')]")).size(), "Channel partner opportunity");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@id,'processHeaderStageName') and contains(text(),'Quantify')]")).size(), "Quantify");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@id,'processHeaderStageName') and contains(text(),'Present To Channel Partner')]")).size(), "Present To Channel Partner");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@id,'processHeaderStageName') and contains(text(),'In Progress')]")).size(), "In Progress");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@id,'processHeaderStageName') and contains(text(),'Close')]")).size(), "Close");


		return this;
	}

	//clikc signout
	public SalesFormPage clickSignout() {
		click(getDriver().findElement(By.id("mectrl_headerPicture")), "Signout Icon");
		click(getDriver().findElement(By.id("mectrl_body_signOut")), "Signout button");
		return this;
	}


	// perform page refresh
	public SalesFormPage pageRefresh() throws InterruptedException {
		getDriver().navigate().refresh();
		Thread.sleep(5000);
		return this;
	}


	public SalesFormPage clickAsWon() {
		click(getDriver().findElement(By.xpath("//button[@data-id='opportunity|NoRelationship|Form|Mscrm.Form.opportunity.MarkAsWon']")),"Close as won");
		return this;
	}

	public SalesFormPage verifyWonCloseOppurtunityStatusReason() {

		ArrayList<String> selectoptions=new ArrayList<String>();
		Select gutFeel= new  Select(getDriver().findElement(By.xpath("//select[@data-id='statusreason_id.fieldControl-option-set-select']")));

		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> option =gutFeel.getOptions();	


		for(int i=0;i<option.size();i++) {

			selectoptions.add(option.get(i).getText());

		}

		assertTrue(selectoptions.contains("Won"));

		return this;
	}


	//Click cancel button in Close opportunity popu up
	public SalesFormPage clickCancelinCloseOpportunity () {
		click(getDriver().findElement(By.xpath("//button[@data-id='cancel_id']")),"Cancel button");
		return this;
	}

	public SalesFormPage clickAsLost() {
		click(getDriver().findElement(By.xpath("//button[@data-id='opportunity|NoRelationship|Form|Mscrm.Form.opportunity.MarkAsLost']")),"Close as lost");
		return this;
	}

	public SalesFormPage verifyLostCloseOppurtunityStatusReason() {

		ArrayList<String> selectoptions=new ArrayList<String>();
		Select gutFeel= new  Select(getDriver().findElement(By.xpath("//select[@data-id='statusreason_id.fieldControl-option-set-select']")));

		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> option =gutFeel.getOptions();	

		/*
		 * All other options 
		 * "---","Loss to competitor","Loss due to lack of interest from member","Loss due to lack of interest from sponsor","Loss due to no contact/bad contact at member",,"Reason not specified","Private Agreement","Not applicable to member","Centralized purchasing","Canceled","Competitor","Pricing","Unable to Integrate","Other","--Select--"
		 */
		List<String> expectdoption = Arrays.asList("Loss to Competitive GPO","Loss due to Supplier (Denials, No Response)","Premier Price Not Competitive","Non-Authorized Distributor","Sponsor Local Contract","Member Local Contract","Member Closed","Member Part of Another Mgmt Company","Premier Contracting Gap");		

		for(int i=0;i<option.size();i++) {

			selectoptions.add(option.get(i).getText());

		}
		System.out.println(selectoptions);
		System.out.println(expectdoption);
		assertTrue(selectoptions.containsAll(expectdoption));

		return this;
	}

	//Enter the Phone Details with Subject
	public SalesFormPage EnterEmailDetails(String subject, String To) throws InterruptedException, IOException   {

		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");

		Actions a = new Actions(getDriver());
		type(getDriver().findElement(By.xpath("//input[@data-id='to.fieldControl-LookupResultsDropdown_to_textInputBox_with_filter_new']")),To,"Send To");
		Thread.sleep(4000);
		a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='to.fieldControl-LookupResultsDropdown_to_resultsContainer']"))).click().build().perform();

		Thread.sleep(200);
		click(getDriver().findElement(By.xpath("//button[@data-id='email|NoRelationship|Form|Mscrm.Form.email.Save']")),"Save button");
		Thread.sleep(10000);
		return this;
	}


	//Enter the Phone Details with Subject
	public SalesFormPage EnterEmailDetailsWithRegardingAndDueDate(String subject, String To, String regarding, String date, String time) throws InterruptedException, IOException   {

		
		Actions a = new Actions(getDriver());
		type(getDriver().findElement(By.xpath("//input[@data-id='to.fieldControl-LookupResultsDropdown_to_textInputBox_with_filter_new']")),To,"Send To");
		Thread.sleep(4000);
		a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='to.fieldControl-LookupResultsDropdown_to_resultsContainer']"))).click().build().perform();
		clickEsc();
		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");

		clickTab(4);
		
		//next to line is to reach Regarding field
		click(getDriver().findElement(By.xpath("//button[@data-id='email|NoRelationship|Form|Mscrm.Form.email.Save']")),"Save button");
		a.moveToElement(getDriver().findElement(By.xpath("//span[@data-id='warningNotification' and contains(text(),'Regarding : Required fields must be filled in.')]"))).doubleClick().build().perform();
		type(getDriver().findElement(By.xpath("//input[@data-id='regardingobjectid.fieldControl-LookupResultsDropdown_regardingobjectid_textInputBox_with_filter_new']")),regarding,"Regarding");
		Thread.sleep(4000);
		a.moveToElement(getDriver().findElement(By.xpath("//li[@data-id='regardingobjectid.fieldControl-LookupResultsDropdown_regardingobjectid_resultsContainer']"))).click().build().perform();

		Thread.sleep(200);
		click(getDriver().findElement(By.xpath("//button[@data-id='email|NoRelationship|Form|Mscrm.Form.email.Save']")),"Save button");
		Thread.sleep(10000);
		
		click(getDriver().findElement(By.xpath("//button[@data-id='header_overflowButton']")),"Oerflow Button");
		type(getDriver().findElement(By.xpath("//input[@data-id='scheduledend.fieldControl-date-time-input']")),date,"date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Due')]")),"Due label");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Time of Due']")),time,"time");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Due')]")),"Due label");
		clickEsc();
		click(getDriver().findElement(By.xpath("//button[@data-id='email|NoRelationship|Form|Mscrm.Form.email.SaveAndClose']")),"Save button");
		Thread.sleep(4000);
		return this;
	}
	
	//Verify My activities for Today
	
	public SalesFormPage verifyActivityUnderTodayActivity(String activitySubject) throws InterruptedException, IOException   {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//ul[@aria-label='My Activities for Today']//a/span[contains(text(),'"+activitySubject+"')]")).size(), "Activity");
		return this;
	}
	
	//Verify My Past activities 
	
		public SalesFormPage verifyActivityUnderPastActivity(String activitySubject) throws InterruptedException, IOException   {
			verifyElementisDisplayed(getDriver().findElements(By.xpath("//ul[@aria-label='My Past Due Activities']//span[contains(text(),'"+activitySubject+"')]")).size(), "Activity");
			return this;
		}
		
	
		
		//Verify My Upcoming activities 
		
			public SalesFormPage verifyActivityUnderUpcomingActivity(String activitySubject) throws InterruptedException, IOException   {
				verifyElementisDisplayed(getDriver().findElements(By.xpath("//ul[@aria-label='My Upcoming Activities']//span[contains(text(),'"+activitySubject+"')]")).size(), "Activity");
				return this;
			}
			
	
	
	//Delete My Activities under Upcoming
	public SalesFormPage deleteActivitiesFromDashboardUpcoming(String activitySubject) throws InterruptedException, IOException   {
		click(getDriver().findElement(By.xpath("//ul[@aria-label='My Upcoming Activities']//a/span[contains(text(),'"+activitySubject+"')]/parent::a/following-sibling::div/button[@aria-label='More options']")),"More Commands");
		click(getDriver().findElement(By.xpath("//li[@role='menuitem']//*[contains(text(),'Delete')]")),"Delete Button");
		click(getDriver().findElement(By.xpath("//button[@data-id='confirmButton']")),"Confirm Button");
		Thread.sleep(5000);
		return this;
	}
	
	//Delete My Activities under Past
		public SalesFormPage deleteActivitiesFromDashboardPast(String activitySubject) throws InterruptedException, IOException   {
			click(getDriver().findElement(By.xpath("//ul[@aria-label='My Past Due Activities']//a/span[contains(text(),'"+activitySubject+"')]/parent::a/following-sibling::div/button[@aria-label='More options']")),"More Commands");
			click(getDriver().findElement(By.xpath("//li[@role='menuitem']//*[contains(text(),'Delete')]")),"Delete Button");
			click(getDriver().findElement(By.xpath("//button[@data-id='confirmButton']")),"Confirm Button");
			Thread.sleep(5000);
			return this;
		}
		
		public SalesFormPage deleteActivitiesFromDashboardCPSalesView(String activitySubject) throws InterruptedException, IOException   {
			Actions a= new Actions (getDriver());
			a.moveToElement(getDriver().findElement(By.xpath("//h3[contains(text(),'My Past Due Activities')]/ancestor::div[@data-id='DataSetHostContainer']//div/a[contains(@title,'"+activitySubject+"')]/parent::div//preceding-sibling::div[contains(@class,'data-selectable')]"))).click().build().perform();
			click(getDriver().findElement(By.xpath("//button[@title='More commands for Activity']")),"More Commands");
			a.moveToElement(getDriver().findElement(By.xpath("//button[@data-id='activitypointer|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.activitypointer.DeleteMenu']"))).click().build().perform();
			click(getDriver().findElement(By.xpath("//button[@data-id='confirmButton']")),"Confirm Button");
			
			return this;	
		}
		
public SalesFormPage verifyActivitiesFromDashboardCPSalesView(String activitySubject) throws InterruptedException, IOException   {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//h3[contains(text(),'My Past Due Activities')]/ancestor::div[@data-id='DataSetHostContainer']//div[contains(@class,'data-selectable')]//following-sibling::div/a[contains(@title,'"+activitySubject+"')]")).size(), activitySubject);
			
			return this;	
		}
		
		
		
		
		
		
		//Delete My Activities under Today
				public SalesFormPage deleteActivitiesFromDashboardToday(String activitySubject) throws InterruptedException, IOException   {
					click(getDriver().findElement(By.xpath("//ul[@aria-label='My Activities for Today']//a/span[contains(text(),'"+activitySubject+"')]/parent::a/following-sibling::div/button[@aria-label='More options']")),"More Commands");
					click(getDriver().findElement(By.xpath("//li[@role='menuitem']//*[contains(text(),'Delete')]")),"Delete Button");
					click(getDriver().findElement(By.xpath("//button[@data-id='confirmButton']")),"Confirm Button");
					Thread.sleep(5000);
					return this;
				}
			
	
	


	//Enter the Email Details with Subject
	public SalesFormPage enterEmailDueDate	(String date, String time) throws InterruptedException, IOException   {
		click(getDriver().findElement(By.xpath("//button[@data-id='header_overflowButton']")),"Oerflow Button");
		type(getDriver().findElement(By.xpath("//input[@data-id='scheduledend.fieldControl-date-time-input']")),date,"date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Due')]")),"Due label");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Time of Due']")),time,"time");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Due')]")),"Due label");
		clickEsc();
		click(getDriver().findElement(By.xpath("//button[@data-id='email|NoRelationship|Form|Mscrm.Form.email.Save']")),"Save button");
		Thread.sleep(4000);
		return this;
	}


	//Click Related Activities
	public SalesFormPage selectRelatedActivities() throws InterruptedException   {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");

		click(getDriver().findElement(By.xpath("(//*[text()='Activities'])[2]")),"Activities");
		Thread.sleep(5000);
		return this;
	}


	//Click  Activities
	public SalesFormPage clickActivitiesTab() throws InterruptedException   {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[text()='ACTIVITIES']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[text()='ACTIVITIES']")),"ACTIVITIES");	
		}
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//span[text()='Activities'])[2]")),"Activities");

		Thread.sleep(5000);
		return this;
	}

	//Click New Activity- Email
	public SalesFormPage clickNewEmailActivity() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[@data-id='activitypointer|NoRelationship|SubGridAssociated|Mscrm.SubGrid.activitypointer.NewActivitiesMenuV2']")),"New EmaikActivity");
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//span[text()='Email']")),"Email Activity");
		Thread.sleep(2000);
		return this;
	}


	//Click New Activity- Appointment
	public SalesFormPage clickNewAppointmentActivity() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[@data-id='activitypointer|NoRelationship|SubGridAssociated|Mscrm.SubGrid.activitypointer.NewActivitiesMenuV2']")),"New EmaikActivity");
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//span[text()='Appointment']")),"Appointment Activity");
		Thread.sleep(2000);
		return this;
	}


	//Click New Activity- Phone call
	public SalesFormPage clickNewPhoneActivity() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[@data-id='activitypointer|NoRelationship|SubGridAssociated|Mscrm.SubGrid.activitypointer.NewActivitiesMenuV2']")),"New EmaikActivity");
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//span[text()='Phone Call']")),"Phone Call Activity");
		Thread.sleep(2000);
		return this;
	}

	//Enter the Phone Details with Subject
	public SalesFormPage EnterPhoneDetails(String subject, String To, String date, String time) throws InterruptedException, IOException   {

		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");

		Actions a = new Actions(getDriver());
		type(getDriver().findElement(By.xpath("//input[@aria-label='To, Multiple Selection Lookup']")),To,"Send To");
		Thread.sleep(4000);
		a.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='to.fieldControl-LookupResultsDropdown_to_infoContainer']"))).click().build().perform();
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Duration')]")),"Duration");
		clickTab(2);
		Thread.sleep(200);
		type(getDriver().findElement(By.xpath("//input[@data-id='scheduledend.fieldControl-date-time-input']")),date,"date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Due Date')]")),"Due Date");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Time of Due Date']")),time,"time");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Due Date')]")),"Due Date");
		click(getDriver().findElement(By.xpath("//button[@data-id='quickCreateSaveAndCloseBtn']")),"Save and Close button");
		Thread.sleep(10000);
		return this;
	}


	public SalesFormPage clickQucikCreateSaveAndCloseButton() throws InterruptedException, IOException   {
		click(getDriver().findElement(By.xpath("//button[@data-id='quickCreateSaveAndCloseBtn']")),"Save and Close button");
		return this;
	}

	public SalesFormPage verifyRosterOption() throws InterruptedException, IOException   {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[contains(text(),'Roster')]")).size(), "Premier Roster");
		return this;
	}


	//click roaster option
	public SalesFormPage clickRosterOption() throws InterruptedException, IOException   {
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Roster')]")),"Roster");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//h1[@title='Full Premier Roster']")).size(), "Premier Roster");
		return this;
	}
	
	//Verify User is navigatd to th rostterpage
	public SalesFormPage verifyRosterPage() throws InterruptedException, IOException   {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//h1[@title='Full Premier Roster']")).size(), "Premier Roster");
		return this;
	}
	


	//Click New Activity- Task
	public SalesFormPage clickNewTaskActivity() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[@data-id='activitypointer|NoRelationship|SubGridAssociated|Mscrm.SubGrid.activitypointer.NewActivitiesMenuV2']")),"New EmaikActivity");
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//span[text()='Task']")),"Phone Call Activity");
		Thread.sleep(2000);
		return this;
	}

	//Enter the Phone Details with Subject
	public SalesFormPage EnterTaskDetails(String subject,  String date, String time) throws InterruptedException, IOException   {

		type(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");

		type(getDriver().findElement(By.xpath("//input[@data-id='scheduledend.fieldControl-date-time-input']")),date,"date");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Due')]")),"Due Date");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Time of Due']")),time,"time");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Due')]")),"Due Date");
		click(getDriver().findElement(By.xpath("//button[@data-id='quickCreateSaveAndCloseBtn']")),"Save and Close button");
		Thread.sleep(10000);
		return this;
	}



	//Verify Owner data in the contact view
	public SalesFormPage verifyOwnerDate(String username) throws InterruptedException, IOException   {

		click(getDriver().findElement(By.xpath("//div[@data-id='data-set-quickFind-container']/input")),"search in filter button");
		clickTab(2);
		clickshiftTab(1);
		for(int i=1;i<getDriver().findElements(By.xpath("//div[@col-id='ownerid']//button//span")).size();i++) {
			assertTrue(getDriver().findElement(By.xpath("(//div[@col-id='ownerid']//button//span)["+i+"]")).getText().contentEquals(username));
		}
		return this;
	}


	//Verify Owner data in the contact view
	public SalesFormPage verifyAccountTypeData(String accountType) throws InterruptedException, IOException   {

		for(int i=2;i<getDriver().findElements(By.xpath("//div[@col-id='ownerid']//button//span")).size();i++) {
			assertTrue(getDriver().findElement(By.xpath("(//div[@col-id='customertypecode']//label/div)["+i+"]")).getText().contentEquals(accountType));
		}
		return this;
	}

	//Enter the Contract Volumne

	public SalesFormPage typeContractVolume(String contractVolume) throws InterruptedException, IOException   {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_annualizedsales.fieldControl-currency-text-input']")),contractVolume,"contractVolume");	
		return this;	
	}


	//Enter the Admin fee 

	public SalesFormPage typeAdminFee(String AdminFee) throws InterruptedException, IOException   {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_adminfee.fieldControl-decimal-number-text-input']")),AdminFee,"AdminFee");	
		return this;	
	}


	//Enter the  fee share

	public SalesFormPage typefeeShare(String feeShare) throws InterruptedException, IOException   {
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_feeshare.fieldControl-decimal-number-text-input']")),feeShare,"feeShare");	
		return this;	
	}


	//Verify GAF
	public SalesFormPage verifyGAF(String contractVolumne, String adminFee) throws InterruptedException, IOException   {
		int contractvolume=Integer.parseInt(contractVolumne);
		double adminFeeint= Double.valueOf(adminFee);
		double gaf=(contractvolume*adminFeeint)/100;
		int gafvalue=(int)Math.round(gaf);
		String ActualgafValue=getDriver().findElement(By.xpath("//input[@data-id='ix_projectedgafvalue.fieldControl-currency-text-input']")).getAttribute("title").substring(1);

		assertTrue(Integer.parseInt(ActualgafValue)==gafvalue);

		return this;
	}

	//Verify NAF
	public SalesFormPage verifyNAF(String contractVolumne, String adminFee, String feeshare) throws InterruptedException, IOException   {
		int contractvolume=Integer.parseInt(contractVolumne);
		double adminFeeint= Double.valueOf(adminFee);
		double feeShareint= Double.valueOf(feeshare);
		double gaf=(contractvolume*adminFeeint)/100;
		int gafvalue=(int)Math.round(gaf);
		double naf=gafvalue*(1-(feeShareint/100));
		int expectednaf=(int)Math.round(naf);
		String actualnaf=getDriver().findElement(By.xpath("//input[@data-id='estimatedvalue.fieldControl-currency-text-input']")).getAttribute("title").substring(1);
		assertTrue(Integer.parseInt(actualnaf)==expectednaf);

		return this;
	}


	public SalesFormPage verifyFieldRepDashboard() throws InterruptedException, IOException   {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[contains(text(),'My Activities for Today')]")).size(), "My Activities for Today");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[contains(text(),'My Past Due Activities')]")).size(), "My Past Due Activities");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[contains(text(),'My Upcoming Activities')]")).size(), "My Upcoming Activities");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[contains(text(),'My Open Pipelines')]")).size(), "My Open Pipelines");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[contains(text(),'My Accounts w/Signed Contracts & No Revenue Yet')]")).size(), "My Accounts w/Signed Contracts & No Revenue Yet");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[contains(text(),'My Prospects')]")).size(), "My Prospects");
		return this;
	}
	
	
	public SalesFormPage verifyCPOpenOpportunityChart() throws InterruptedException, IOException   {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//h2[@aria-label='CP - Open Opportunities']")).size(), "CP Open oppurtunity Funnel chart");
		assertTrue(!(getDriver().findElement(By.xpath("//*[contains(@class,'highcharts-point highcharts-color')]")).getAttribute("aria-label").isEmpty()));
		return this;
	}
	
	public SalesFormPage verifyCPSeniorDirectorDashboard() throws InterruptedException, IOException   {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//h2[@aria-label='CP - Open Opportunities']")).size(), "CP Open oppurtunity Funnel chart");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//h2[@aria-label=\"CP - My Team's Open Opportunities\"]")).size(), "CP - My Team's Open Opportunities");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//h2[@aria-label=\"My Team's Past Due Activities\"]")).size(), "My Team's Past Due Activities");
		return this;
	}
	
	
}





