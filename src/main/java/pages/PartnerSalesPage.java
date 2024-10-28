package pages;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import services.WebDriverServiceImpl;

public class PartnerSalesPage extends WebDriverServiceImpl {

	public static String mainwindow;
	public static String childWindow;


	public PartnerSalesPage clickOppurtunities() throws InterruptedException {
		Thread.sleep(5000);
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//li[@aria-label='Opportunities']")),"Oppurtunities tab");
		return this;
	}

	public PartnerSalesPage clickNewOppurtunitiesbutton() {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//button[@data-id='opportunity|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.opportunity.NewRecord']"))));

		click(getDriver().findElement(By.xpath("//button[@data-id='opportunity|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.opportunity.NewRecord']")),"New Oppurtunities");
		return this;	
	}

	//Click New button
	public PartnerSalesPage clickNewButton() {
		click(getDriver().findElement(By.xpath("//a[@aria-label='Create a new query to find records and activities.']")),"New butotn");
		return this;
	}

	//Click Tab
	public PartnerSalesPage clickTab(int number)  throws InterruptedException {
		for(int i=0;i<number;i++) {
			Actions a =new Actions(getDriver());
			a.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(3000);
		}
		return this;
	}





	//Click shift Tab
	public PartnerSalesPage clickshiftTab(int number)  throws InterruptedException {
		for(int i=0;i<number;i++) {
			Actions a =new Actions(getDriver());
			a.keyDown(Keys.SHIFT).sendKeys(Keys.TAB).keyUp(Keys.SHIFT).build().perform();
			Thread.sleep(3000);
		}
		return this;
	}
	//navigate to Member Account field
	public PartnerSalesPage navigateToMemberAccount() throws InterruptedException {
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")), "Topic");
		clickTab(10);
		return this;
	}
	public PartnerSalesPage selectMemberAccount(String memberaccount) throws InterruptedException {

		click(getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")),"memberaccount");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")))),memberaccount,"memberaccount");
		//Thread.sleep(120000);
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+memberaccount+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+memberaccount+"')]")),"memberaccount");

		return this;
	}

	public PartnerSalesPage enterTopic(String topic) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")),topic,"topic");
		return this;
	}
	//input[@data-id='name.fieldControl-text-box-text']

	public PartnerSalesPage selectMemberContact(String contact) throws InterruptedException {
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//input[@data-id='parentcontactid.fieldControl-LookupResultsDropdown_parentcontactid_textInputBox_with_filter_new']")),"//input[@data-id=\"parentcontactid.fieldControl-LookupResultsDropdown_parentcontactid_textInputBox_with_filter_new\"]");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='parentcontactid.fieldControl-LookupResultsDropdown_parentcontactid_textInputBox_with_filter_new']")))),contact,"memberaccount");
		//Thread.sleep(120000);
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+contact+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+contact+"')]")),"contact");

		return this;
	}



	public PartnerSalesPage addMemberContact(String fName, String Lname, String primaryAccount) throws InterruptedException {

		click(getDriver().findElement(By.xpath("//input[@data-id='parentcontactid.fieldControl-LookupResultsDropdown_parentcontactid_textInputBox_with_filter_new']")),"//input[@data-id=\"parentcontactid.fieldControl-LookupResultsDropdown_parentcontactid_textInputBox_with_filter_new\"]");
		click(getDriver().findElement(By.xpath("//button[@data-id='parentcontactid.fieldControl-LookupResultsDropdown_parentcontactid_addNewBtnContainer']")),"add new button");

		type(getDriver().findElement(By.xpath("//input[@data-id='firstname.fieldControl-text-box-text']")),fName, "First NAme");
		type(getDriver().findElement(By.xpath("//input[@data-id='lastname.fieldControl-text-box-text']")),Lname, "Last NAme");


		// select the primary account

		click(getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_search']")),"Primary Account");
		type(((getDriver().findElement(By.xpath("//*[@data-id='parentcustomerid.fieldControl-LookupResultsDropdown_parentcustomerid_textInputBox_with_filter_new']")))),primaryAccount, "Primary Account");
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(

				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'parentcustomerid.fieldControl-name0_0_0')]")));
		click(getDriver().findElement(By.xpath("//*[contains(@id,'parentcustomerid.fieldControl-name0_0_0')]")),primaryAccount);
		click(getDriver().findElement(By.xpath("//button[@data-id='quickCreateSaveAndCloseBtn']")),"Click save and Close");

		return this;	
	}

	public PartnerSalesPage verifyMemberContactQuickView(String contact) throws InterruptedException {

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-id='MemberContactQuickview-QuickFormContainer']")).size(), "Contact Widget");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//input[@data-id='MemberContactQuickview.mobilephone.fieldControl-phone-text-input']")).size(), "Phone number");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//input[@data-id='MemberContactQuickview.emailaddress1.fieldControl-mail-text-input']")).size(), "Email ID");
		return this;
	}


	public PartnerSalesPage verifyEstimatedBudgetIsnotDisplayed() throws InterruptedException {

		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//*[contains(text(),'Estimated budget')]")).size(), "Estimated budget");
		return this;
	}

	public PartnerSalesPage clickDemonstratestatus() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//div[@title='Demonstrate']")),"Click demonstrate status");
		return this;
	}

	public PartnerSalesPage verifyEstNoOfLicenses() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@id='header_process_ix_estoflicenses-header_process_ix_estoflicenses-field-label']")).size(), "Est # of license");
		return this;
	}

	public PartnerSalesPage clickCloseButtonProcessStage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='MscrmControls.Containers.ProcessStageControl-stageContentClose']")),"close button");
		return this;
	}

	public PartnerSalesPage verifyBPFStages() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-id='MscrmControls.Containers.ProcessBreadCrumb-processHeaderNameLabel' and contains(text(),'Sales Process')]")).size(), "Sales Process status");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@data-id,'MscrmControls.Containers.ProcessBreadCrumb-processHeaderStageName') and contains(text(),'Pre-Qualified')]")).size(), "Pre Qualified");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@data-id,'MscrmControls.Containers.ProcessBreadCrumb-processHeaderStageName') and contains(text(),'Qualify')]")).size(), "Qualify");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@data-id,'MscrmControls.Containers.ProcessBreadCrumb-processHeaderStageName') and contains(text(),'Discover')]")).size(), "Discover");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@data-id,'MscrmControls.Containers.ProcessBreadCrumb-processHeaderStageName') and contains(text(),'Demonstrate')]")).size(), "Demonstrate");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@data-id,'MscrmControls.Containers.ProcessBreadCrumb-processHeaderStageName') and contains(text(),'Propose')]")).size(), "Propose");
		click(getDriver().findElement(By.xpath("//div[contains(@data-id,'MscrmControls.Containers.ProcessBreadCrumb-processHeaderStageName') and contains(text(),'Demonstrate')]")),"Click Next button");
		click(getDriver().findElement(By.xpath("//button[@title='Move to the next stage']")),"Click Next button");
		click(getDriver().findElement(By.xpath("//button[@title='Move to the next stage']")),"Click Next button");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(@data-id,'MscrmControls.Containers.ProcessBreadCrumb-processHeaderStageName') and contains(text(),'Close')]")).size(), "Close");
		return this;
	}

	public PartnerSalesPage verifyPrequalifiedStage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//div[contains(@data-id,'MscrmControls.Containers.ProcessBreadCrumb-processHeaderStageName') and contains(text(),'Pre-Qualified')]")),"Pre Qualified stage");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@id='header_process_ix_introducedpremiersmartposolution-header_process_ix_introducedpremiersmartposolution-field-label']")).size(), "Introduced Premier smart po solution");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@id='header_process_ix_memberexpressedinterestordesiretolearn-header_process_ix_memberexpressedinterestordesiretolearn-field-label']")).size(), "Member expressed interest or desire to learn more");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@id='header_process_ix_initialcallsetfor-header_process_ix_initialcallsetfor-field-label']")).size(), "Initial Call Set For");
		return this;
	}

	public PartnerSalesPage clickSaveButton() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='opportunity|NoRelationship|Form|Mscrm.Form.opportunity.Save']")),"Save button");
		Thread.sleep(10000);
		return this;
	}

	public PartnerSalesPage clickBackButton() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='navigateBackButtontab-id-0']")),"Back button");
		clickTab(4);

		return this;
	}

	public MemberFormPage navigateToMemberAccountPage() throws InterruptedException {
		Thread.sleep(4000);
		Actions action= new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_selected_tag_text']"))).click().build().perform();
		WebDriverWait wait=new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='GENERAL']")));
		return new MemberFormPage();
	}

	public PartnerSalesPage verifyQualifyStage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//div[contains(@id,'ProcessBreadCrum') and @title='Qualify']")),"Qualify");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@id='header_process_ix_accessagreementsentdate-header_process_ix_accessagreementsentdate-field-label']")).size(), "Assessment Agreement");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@id='header_process_identifypursuitteam-header_process_identifypursuitteam-field-label']")).size(), "Identify Pursuit Team");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@id='header_process_developproposal-header_process_developproposal-field-label']")).size(), "Developer Proposal");
		clickCloseButtonProcessStage();
		return this;

	}
	public PartnerSalesPage verifyProposeStage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//div[contains(text(),'Demonstrate')]")),"Demonstrate stage");
		click(getDriver().findElement(By.xpath("//button[@title='Move to the next stage']")),"Click Next button");
		click(getDriver().findElement(By.xpath("//button[@title='Move to the next stage']")),"Click Next button");
		clickCloseButtonProcessStage();
		click(getDriver().findElement(By.xpath("//div[contains(text(),'Propose')]")),"Propose stage");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@id='header_process_ix_accessagreementsentdate-header_process_ix_accessagreementsentdate-field-label']")).size(), "Assessment Agreement");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@id='header_process_identifypursuitteam-header_process_identifypursuitteam-field-label']")).size(), "Identify Pursuit Team");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@id='header_process_developproposal-header_process_developproposal-field-label']")).size(), "Developer Proposal");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@id='header_process_completeinternalreview-header_process_completeinternalreview-field-label']")).size(), "Complete Internal review");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label[@id='header_process_ix_proposalpresenteddate-header_process_ix_proposalpresenteddate-field-label']")).size(), "Proposal Present Date");
		return this;
	}

	//Add Stage in the column
	public PartnerSalesPage addStageinTheColumn() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@id='columnEditor-btn']")),"column");	
		click(getDriver().findElement(By.xpath("//button[@id='AddColumnsBtn']")),"Add Columns");
		type(getDriver().findElement(By.xpath("//div[@role='dialog']//input[@placeholder='Search' and contains(@class,'ms-SearchBox-field')]")),"Active Stage","Active Stage");
		if(getDriver().findElements(By.xpath("//label[contains(text(),'Active Stage')]")).size()>0) {
			doubleClick(getDriver().findElement(By.xpath("//label[contains(text(),'Active Stage')]")),"Click Active Stage");
		}
		click(getDriver().findElement(By.xpath("//button//span[contains(text(),'Close')]")),"Close button");
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Apply')]")),"Apply Button");
		return this;
	}

	//Search for Oppurtunity
	public PartnerSalesPage searchForOppurtunity(String oppurtunity) throws InterruptedException {
		typeAndEnter(getDriver().findElement(By.xpath("//input[@placeholder='Filter by keyword']")),oppurtunity,"Oppurtunity");
		return this;
	}

	//Verify Stage
	public PartnerSalesPage verifyStageintheView() throws InterruptedException {
		clickTab(4);
		clickshiftTab(1);

		return this;
	}

	public PartnerSalesPage enterSummraysection(String topic, String referringPartner, String partnerType, String memberAccount, String contact, String purchaseTime, String estLicense, String actualLicense, String purchaseProcess) throws InterruptedException{
		type(getDriver().findElement(By.xpath("//input[@aria-label='Topic']")), topic,"Topic");

		click(getDriver().findElement(By.xpath("//input[@data-id='ix_referringpartner.fieldControl-LookupResultsDropdown_ix_referringpartner_textInputBox_with_filter_new']")), "referringPartner");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_referringpartner.fieldControl-LookupResultsDropdown_ix_referringpartner_textInputBox_with_filter_new']")))),referringPartner,"referringPartner");
		//Thread.sleep(120000);
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+referringPartner+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+referringPartner+"')]")),"referringPartner");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_partnertype.fieldControl-option-set-select']")), partnerType, "partnerType");
		selectMemberAccount(memberAccount);
		addMemberContact("Fname","lname",memberAccount);
		clickTab(6);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='purchasetimeframe.fieldControl-option-set-select']")), purchaseTime, "purchaseTime");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_estoflicenses.fieldControl-decimal-number-text-input']")),estLicense,"estLicense");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_actualoflicensessold.fieldControl-decimal-number-text-input']")),actualLicense,"actualLicense");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='purchaseprocess.fieldControl-option-set-select']")), purchaseProcess, "purchaseProcess");
		return this;



	}

	public PartnerSalesPage enterMoreDetails(String description, String currentSituation, String customerneed, String proposedSolution) throws InterruptedException{
		clickTab(4);
		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),description,"description");
		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Current situation']")),currentSituation,"currentSituation");
		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Customer need']")),customerneed,"customerneed");
		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Proposed solution']")),proposedSolution,"proposedSolution");
		return this;
	}


	//Verify Error message is not displauyed
	public PartnerSalesPage verifyErrorisNotDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> error=getDriver().findElements(By.xpath("//*[@data-id='errorDialog_subtitle']"));
		verifyElementisNotDisplayed(error.size(), "Error message");
		Thread.sleep(2000);
		return this;
	}

	//search fir Oppurtunity

	public PartnerSalesPage searchOppurtunity(String oppurtunity, String stage) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//input[@aria-label='Opportunity Filter by keyword']")),"Searchbutton");
		typeAndEnter(getDriver().findElement(By.xpath("//input[@aria-label='Opportunity Filter by keyword']")), oppurtunity, "oppurtunity");
		int size=getDriver().findElements(By.xpath("//div[@class='ag-center-cols-container']/div[@aria-label='Press SPACE to select this row.']//div[@col-id='name']")).size();
		for(int i=1;i<=size;i++) {
			System.out.println(getDriver().findElement(By.xpath("(//div[@class='ag-center-cols-container']/div[@aria-label='Press SPACE to select this row.']//div[@col-id='name']//a//span)["+i+"]")).getText());
			if(getDriver().findElement(By.xpath("(//div[@class='ag-center-cols-container']/div[@aria-label='Press SPACE to select this row.']//div[@col-id='name']//a//span)["+i+"]")).getText().contentEquals(oppurtunity)) {
				System.out.println(stage);
				System.out.println(getDriver().findElement(By.xpath("(//div[@class='ag-center-cols-container']/div[@aria-label='Press SPACE to select this row.']//div[@col-id='ix_activestage']//label/div)["+i+"]")).getText());
				assertTrue(getDriver().findElement(By.xpath("(//div[@class='ag-center-cols-container']/div[@aria-label='Press SPACE to select this row.']//div[@col-id='ix_activestage']//label/div)["+i+"]")).getText().contentEquals(stage));	
			}
		}
		return this;
	}


	//update Prequalified

	public PartnerSalesPage updatePrequalified() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//div[contains(@data-id,'MscrmControls.Containers.ProcessBreadCrumb-processHeaderStageName') and contains(text(),'Pre-Qualified')]")),"Pre Qualified stage");
		click(getDriver().findElement(By.xpath("//label[@data-id='header_process_ix_introducedpremiersmartposolution.fieldControl-checkbox-toggle']")),"intro premier smart");
		click(getDriver().findElement(By.xpath("//label[@data-id='header_process_ix_memberexpressedinterestordesiretolearn.fieldControl-checkbox-toggle']")),"member express");
		click(getDriver().findElement(By.xpath("//button[@data-id='MscrmControls.Containers.ProcessStageControl-nextButtonContainer']")),"Process next stage");
		clickCloseButtonProcessStage();		
		return this;
	}

	//Update Qualify
	public PartnerSalesPage updatequalify(String contact, String memberaccount, String purchasetime, String purchaseprocess, String summary) throws InterruptedException {
		Thread.sleep(6000);	
		click(getDriver().findElement(By.xpath("//div[contains(@id,'ProcessBreadCrum') and @title='Qualify']")),"Qualify");
		click(getDriver().findElement(By.xpath("//input[@data-id='header_process_parentcontactid.fieldControl-LookupResultsDropdown_parentcontactid_textInputBox_with_filter_new']")),"textbox");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='header_process_parentcontactid.fieldControl-LookupResultsDropdown_parentcontactid_textInputBox_with_filter_new']")))),contact,"memberaccount");
		//Thread.sleep(120000);
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+contact+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+contact+"')]")),"contact");

		if(getDriver().findElements(By.xpath("//input[@data-id='header_process_parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")).size()>0) {
		click(getDriver().findElement(By.xpath("//input[@data-id='header_process_parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")),"textbox");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='header_process_parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")))),memberaccount,"memberaccount");
		//Thread.sleep(120000);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+memberaccount+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+memberaccount+"')]")),"contact");
		}
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='header_process_purchasetimeframe.fieldControl-option-set-select']")), purchasetime, "purchasetime");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='header_process_purchaseprocess.fieldControl-option-set-select']")), purchaseprocess, "purchaseprocess");
		type(getDriver().findElement(By.xpath("//textarea[@data-id='header_process_description.fieldControl-text-box-text']")),summary,"summary");
		click(getDriver().findElement(By.xpath("//button[@data-id='MscrmControls.Containers.ProcessStageControl-nextButtonContainer']")),"Process next stage");
		clickCloseButtonProcessStage();		
		Thread.sleep(4000);
		return this;
	}

	//Update smart po section
	public PartnerSalesPage updatesmartpoSection(String intreset, String goalsleft, String solution, String purchaseprocess, String time, String task, String timeline, String department, String contractupDate, String apssystem, String apversion) throws InterruptedException {

		type(getDriver().findElement(By.xpath("//textarea[@data-id='ix_whatpiquedyourinterest.fieldControl-text-box-text']")),intreset,"intreset");
		type(getDriver().findElement(By.xpath("//textarea[@data-id='ix_whataremaingoalstryingtosolvefor.fieldControl-text-box-text']")),goalsleft,"intreset");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_taskedwithfindingsolution.fieldControl-option-set-select']")), solution, "solution");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_taskedsolutiondate.fieldControl-date-time-input']")),time,"time");
		type(getDriver().findElement(By.xpath("//textarea[@data-id='ix_anyoneelsetaskedwithproject.fieldControl-text-box-text']")),task,"timeline");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_timelineforthisproject.fieldControl-date-time-input']")),timeline,"timeline");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@aria-label='Do your locations/departments have the same GL chart of accounts?']")), department, "department");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_whenisyourcontractup.fieldControl-date-time-input']")),contractupDate,"contractupDate");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_apsystemnameandlocation.fieldControl-text-box-text']")),apssystem,"apssystem");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_apversion.fieldControl-text-box-text']")),apversion,"apversion");
		return this;
	}
	
	public PartnerSalesPage updatefinancials(String listCost, String listImplementationcost, String memberlandedcosmo, String memberlandedimplementedcosmo, String premiercosmo, String premierimplementedcost, String sponsorcost, String sponsorimplement  ) throws InterruptedException {
		assertTrue(!(getDriver().findElement(By.xpath("//div[@data-id='transactioncurrencyid.fieldControl-LookupResultsDropdown_transactioncurrencyid_selected_tag_text']")).getText().isEmpty()));
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_listcostmo.fieldControl-currency-text-input']")),listCost,"listCost" );		
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_listimplementationcost.fieldControl-currency-text-input']")),listImplementationcost,"listImplementationcost" );		
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_memberlandedcostmo.fieldControl-currency-text-input']")),memberlandedcosmo,"memberlandedcosmo" );
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_memberlandedimplementationcost.fieldControl-currency-text-input']")),memberlandedimplementedcosmo,"memberlandedimplementedcosmo" );
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_premiercostmo.fieldControl-currency-text-input']")),premiercosmo,"premiercosmo" );
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_premierimplementationcost.fieldControl-currency-text-input']")),premierimplementedcost,"premierimplementedcost" );
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_sponsorcostmo.fieldControl-currency-text-input']")),sponsorcost,"sponsorcost" );
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_sponsorimplementationcost.fieldControl-currency-text-input']")),sponsorimplement,"sponsorimplement" );
		
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_gpoagreementconcession.fieldControl-checkbox-toggle']")),"Agreement");
		return this;
	}

	public PartnerSalesPage verifyUpdateDiscoverStage(String customerneed,String processsolution, String identifyCustomer, String identityCompetitors) throws InterruptedException {
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//div[contains(@id,'ProcessBreadCrumb') and contains(text(),'Discover')]")),"Discover Stage");
		type(getDriver().findElement(By.xpath("//textarea[@data-id='header_process_customerneed.fieldControl-text-box-text']")),customerneed,"Discover Stage");
		type(getDriver().findElement(By.xpath("//textarea[@data-id='header_process_proposedsolution.fieldControl-text-box-text']")),processsolution,processsolution);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='header_process_identifycustomercontacts.fieldControl-checkbox-select']")), identifyCustomer, identifyCustomer);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='header_process_identifycompetitors.fieldControl-checkbox-select']")), identityCompetitors, identityCompetitors);
		click(getDriver().findElement(By.xpath("//button[@data-id='MscrmControls.Containers.ProcessStageControl-nextButtonContainer']")),"Process next stage");
		clickCloseButtonProcessStage();
		Thread.sleep(4000);
		return this;
	}

	public PartnerSalesPage verifyUpdateDemonstrateStage(String estlicense) throws InterruptedException {
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//div[contains(@id,'ProcessBreadCrumb') and contains(text(),'Demonstrate')]")),"Demonstrate Stage");
		type(getDriver().findElement(By.xpath("//input[@data-id='header_process_ix_estoflicenses.fieldControl-decimal-number-text-input']")),estlicense,"estlicense");
		click(getDriver().findElement(By.xpath("//button[@data-id='MscrmControls.Containers.ProcessStageControl-nextButtonContainer']")),"Process next stage");
		clickCloseButtonProcessStage();
		Thread.sleep(4000);
	return this;	
	}
	
	public PartnerSalesPage verifyUpdateProposeStage(String agreementDate, String identifysalesteam, String developerProposal, String internalreview, String proposalpresentDate) throws InterruptedException {
		Thread.sleep(8000);
		click(getDriver().findElement(By.xpath("//button[@title='Move to the next stage']")),"Click Next button");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//div[contains(@id,'ProcessBreadCrumb') and contains(text(),'Propose')]")),"Propose Stage");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_accessagreementsentdate.fieldControl-date-time-input']")),agreementDate,"agreementDate");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='header_process_identifypursuitteam.fieldControl-checkbox-select']")), identifysalesteam, identifysalesteam);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='header_process_developproposal.fieldControl-checkbox-select']")), developerProposal, developerProposal);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='header_process_completeinternalreview.fieldControl-checkbox-select']")), internalreview, internalreview);
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_proposalpresenteddate.fieldControl-date-time-input']")),proposalpresentDate,"proposalpresentDate");
		click(getDriver().findElement(By.xpath("//button[@data-id='MscrmControls.Containers.ProcessStageControl-nextButtonContainer']")),"Process next stage");
		clickCloseButtonProcessStage();
		Thread.sleep(4000);
	return this;	
	}
	
	public PartnerSalesPage verifyUpdateCloseStage(String financialdecision, String finalproposal, String presentfinalproposal, String sendthankyou, String filedebrief) throws InterruptedException {
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//button[@title='Move to the next stage']")),"Click Next button");
		click(getDriver().findElement(By.xpath("//button[@title='Move to the next stage']")),"Click Next button");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'ProcessBreadCrumb') and contains(text(),'Close')]")),"Close Stage");
		type(getDriver().findElement(By.xpath("//input[@data-id='finaldecisiondate.fieldControl-date-time-input']")),financialdecision,"financialdecision");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='header_process_completefinalproposal.fieldControl-checkbox-select']")), finalproposal, finalproposal);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='header_process_presentfinalproposal.fieldControl-checkbox-select']")), presentfinalproposal, presentfinalproposal);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='header_process_sendthankyounote.fieldControl-checkbox-select']")), sendthankyou, sendthankyou);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='header_process_filedebrief.fieldControl-checkbox-select']")), filedebrief, filedebrief);
		click(getDriver().findElement(By.xpath("//button[@data-id='MscrmControls.Containers.ProcessStageControl-finishButtonContainer']")),"finish button");
		Thread.sleep(4000);
	return this;	
	}
	
	
	

}





