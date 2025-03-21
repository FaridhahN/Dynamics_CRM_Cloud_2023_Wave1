package pages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Assert; 
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import driver.Driver;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

public class SupplierFormPage extends WebDriverServiceImpl{


	//clear Suggestion
	public SupplierFormPage clearAllSuggestions() {
		List<WebElement> clearsugestion=getDriver().findElements(By.xpath("//button[@aria-label='Clear all suggestions']/span"));
		if(clearsugestion.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Clear all suggestions']/span")),"Clear sugesstion");
		}

		return this;
	}

	public SupplierFormPage defaultAccountStatus(String defaultAccountStatus) throws InterruptedException {
		//	driver.navigate().refresh();
		Thread.sleep(5000);
		verifyExactText((getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[2]"))),defaultAccountStatus,"Account Status");
		return this;
	}

	//Verify Documents Tab Order
	public SupplierFormPage verifyDocumentsTabOrder() throws InterruptedException {
		verifyDisplayed(getDriver().findElement(By.cssSelector("ul[aria-label='Account Form']>li[aria-label='ACTIVITIES']:nth-of-type(2)")),"second tab");
		verifyDisplayed(getDriver().findElement(By.cssSelector("ul[aria-label='Account Form']>li[aria-label='DOCUMENTS']:nth-of-type(3)"))," after ACTIVITIES tab");
		verifyDisplayed(getDriver().findElement(By.cssSelector("ul[aria-label='Account Form']>li[aria-label='TAX DOCUMENTS']:nth-of-type(4)"))," after DOCUMENTS tab");
		Thread.sleep(2000);
		return this;
	}

	public SupplierFormPage doubleClickMembership(String membership) throws InterruptedException {	

		for(int i=1;i<=getDriver().findElements(By.xpath("//*[@col-id='ix_membershipprovider']//span")).size();i++) {
			if(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershipprovider']//span)["+i+"]")).getText().equalsIgnoreCase(membership)) {
				Actions a = new Actions(getDriver());	
				int j=i+1;
				a.moveToElement(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershiptype']//label)["+(j)+"]"))).doubleClick().build().perform();	    

			}
		}

		// a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='cell-0-2']"))).doubleClick().build().perform();
		Thread.sleep(3000);
		return this;
	}

	public SupplierFormPage verifyDocumentsOptionRemovedOnRelatedTab() throws InterruptedException {
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("(//span[text()='Documents'])[2]")).size(), "Documents Option");
		Thread.sleep(2000);
		return this;
	}

	public SupplierFormPage verifyDocumentsTab() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//*[@data-id='tablist-DOCUMENTS']")).size(), "DOCUMENTS TAB");
		click(getDriver().findElement(By.xpath("//*[@data-id='tablist-DOCUMENTS']")), "DOCUMENTS TAB");
		return this;
	}



	//Click Related and Activities
	public SupplierFormPage clickRelatedActivities() throws InterruptedException   {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//span[text()='Activities'])[2]")),"Activities");
		Thread.sleep(2000);
		return this;
	}

	//Click New Activity- Task
	public SupplierFormPage clickNewTaskActivity() throws InterruptedException   {
		click(getDriver().findElement(By.xpath("//button[contains(@title,'New Activity')]")),"New Activity");
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//span[text()='Task']")),"Task");
		Thread.sleep(2000);
		return this;
	}

	//Select Activity under three dot menu
	public SupplierFormPage createNewTaskFromThreeDot() {

		click(getDriver().findElement(By.xpath("//button[@data-id='OverflowButton']")),"More Button");

		click(getDriver().findElement(By.xpath("//*[normalize-space()='task']")),"New Task Activity");

		return this;
	}


	//Select Information view
	public SupplierFormPage selectInformationview() throws InterruptedException   {
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//span[contains(@class,'symbolFont DropdownArrow-symbol')]")),"Dropdown Button");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[@role='presentation'][normalize-space()='Information']")),"Information Dropdown Button");
		Thread.sleep(4000);
		List<WebElement> element=getDriver().findElements(By.xpath("//button/span[contains(text(),'Discard changes')]"));
		if(element.size()>0) {
			click(getDriver().findElement(By.xpath("//button/span[contains(text(),'Discard changes')]")),"Discard Changes");
		}
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Quick Subject')]")));

		return this;
	}


	//Select Information view
	public SupplierFormPage selectTaskview() throws InterruptedException   {
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//span[contains(@class,'symbolFont DropdownArrow-symbol')]")),"Dropdown Button");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[@role='presentation'][normalize-space()='Task']")),"Inforamtion Dropdown Button");
		Thread.sleep(4000);
		List<WebElement> element=getDriver().findElements(By.xpath("//button/span[contains(text(),'Discard changes')]"));
		if(element.size()>0) {
			click(getDriver().findElement(By.xpath("//button/span[contains(text(),'Discard changes')]")),"Discard Changes");
		}
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Subject']")));

		return this;
	}


	//Create Task under Activies option
	public SupplierFormPage createTaskUnderActivities() {

		click(getDriver().findElement(By.xpath("//button[@title='Task']")),"Task Option");

		return this;
	}


	//Delete activities in the Activities tab
	public SupplierFormPage deleteAllActivities() {
		click(getDriver().findElement(By.xpath("//*[@col-id='activitytypecode']/preceding-sibling::div[@col-id='__row_status']")),"Select All Checkbox");
		click(getDriver().findElement(By.xpath("//button[contains(@id,'activitypointer|NoRelationship|SubGridStandard|Mscrm.DeleteSelectedRecord')]")),"Delete Button");
		click(getDriver().findElement(By.xpath("//button[@aria-label='Delete']")),"Confirm Delete Button");
		return this;
	}


	//Select Open Activities
	public SupplierFormPage selectOpenActivitiesViewAfterTask() throws InterruptedException   {
		Thread.sleep(2000);
		List<WebElement> dropdown= getDriver().findElements(By.xpath("(//span[contains(@id,'ViewSelector')])[4]"));
		if(dropdown.size()>0) {
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[4]")),"Select a view");
		}else {
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[2]")),"Select a view");
		}
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Open Activity Associated View')]")),"Open Activitiy Associtated View");
		Thread.sleep(15000);
		return this;
	}

	//Verify the subject of the task
	public SupplierFormPage verifySupbjectAfterCreation(String entitycode, String accountName, String subject) throws InterruptedException   {

		verifyExactText(getDriver().findElement(By.xpath("//div[@col-id='subject']//span")), entitycode+": "+accountName+":"+subject, "Subject");

		return this;
	}


	public SupplierFormPage verifyTasksCompletionstatus(String status) throws InterruptedException, IOException   {
		Thread.sleep(2000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//div[@col-id='statecode']//label[contains(@class,'option')]/div[contains(@class,'ms-TooltipHost')]")),"Completion status");
		System.out.println(saveStatus);
		assertTrue(saveStatus.contains(status),"Status is not "+status);
		return this;
	}

	public SupplierFormPage completeAllTask() throws InterruptedException {

		List<WebElement> checkmark= getDriver().findElements(By.xpath("//div[contains(@class,'ms-Checkbox is-enabled RowSelectionCheckMarkSpan')]"));
		if(checkmark.size()>0) {
			List<WebElement> checkbox=  getDriver().findElements(By.xpath("(//div[@role='columnheader']//input[@aria-label='Toggle selection of all rows'])[2]"));
			if(checkbox.size()>0) {
				Actions action = new Actions(getDriver());
				action.moveToElement(getDriver().findElement(By.xpath("(//div[@role='columnheader']//input[@aria-label='Toggle selection of all rows'])[2]"))).click().build().perform();

			}else {

				Actions action = new Actions(getDriver());
				action.moveToElement(getDriver().findElement(By.xpath("//div[@role='columnheader']//input[@aria-label='Toggle selection of all rows']"))).click().build().perform();

			}
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//button[@data-id='activitypointer|NoRelationship|SubGridAssociated|Mscrm.SubGrid.activitypointer.MainTab.Actions.SaveAsCompleted']")),"Delete Activity Button");
			Thread.sleep(4000);
			List<WebElement> closbutton=getDriver().findElements(By.xpath("//button[contains(@aria-label,'Close Task')]"));
			List<WebElement> closphone=getDriver().findElements(By.xpath("//button[contains(@aria-label,'Close Phone')]"));
			List<WebElement> closletter=getDriver().findElements(By.xpath("//button[contains(@aria-label,'Close Letter')]"));
			if(closbutton.size()>0) {
				click(getDriver().findElement(By.xpath("//button[contains(@aria-label,'Close Task')]")),"Confirm Delete Button");	
			}else if(closphone.size()>0){
				click(getDriver().findElement(By.xpath("//button[contains(@aria-label,'Close Phone')]")),"Confirm Delete Button");
			}else if(closletter.size()>0) {
				click(getDriver().findElement(By.xpath("//button[contains(@aria-label,'Close Letter')]")),"Confirm Delete Button");
			}
		}

		return this;
	}


	//verify the subject feild is null
	public SupplierFormPage verifySubjectFieldisNull() {
		verifIsNullWithTitleAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")), "Subject feild is not null");
		return this;

	}

	//Verify the Subject Feild is concatenated
	public SupplierFormPage verifytheSubjectFeild(String entitycode, String accountName) {

		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")), entitycode+": "+accountName+":", "Subject is not autopou");
		return this;
	}
	//Enter the Task Details
	public SupplierFormPage EnterTaskDetails( String subject, String duedate, String duration, String taskdetails) throws InterruptedException, IOException   {
		typewithoutClear(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Due']")),duedate, "Due DAte");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Duration']")),duration,"Duration Dropdown");
		Actions a=new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//li[contains(text(),'"+duration+"')]"))).build().perform();
		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),taskdetails,"Task details");
		//Changed on 10/11/2023
		//click(getDriver().findElement(By.xpath("//button[@aria-label='Save (CTRL+S)']")),"Save button");
		click(getDriver().findElement(By.xpath("//button[@aria-label='Save and Close']")),"Save button");

		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		return this;
	}

	//Enter the Task Details
	public SupplierFormPage EnterTaskDetailsWithSave(  String subject, String duedate, String duration, String taskdetails) throws InterruptedException, IOException   {
		typewithoutClear(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");

		type(getDriver().findElement(By.xpath("//input[@aria-label='Duration']")),duration,"Duration Dropdown");
		Actions a=new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//li[contains(text(),'"+duration+"')]"))).build().perform();
		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),taskdetails,"Task details");
		//Changed on 10/11/2023
		//click(getDriver().findElement(By.xpath("//button[@aria-label='Save (CTRL+S)']")),"Save button");
		click(getDriver().findElement(By.xpath("//button[@data-id='task|NoRelationship|Form|Mscrm.Form.task.Save']")),"Save button");

		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		click(getDriver().findElement(By.xpath("//button[@data-id='task|NoRelationship|Form|Mscrm.Form.task.SaveAsComplete']")),"Save button");
		return this;
	}

	//Enter the Task Details
	public SupplierFormPage EnterTaskDetailsFromActivitiesPage( String entitycode, String subject, String duedate, String duration, String taskdetails) throws InterruptedException, IOException   {
		typewithoutClear(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")),subject, "subject field");
		selectRagrding(entitycode);
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Due']")),duedate, "Due DAte");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Duration']")),duration,"Duration Dropdown");
		Actions a=new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//li[contains(text(),'"+duration+"')]"))).build().perform();
		type(getDriver().findElement(By.xpath("//textarea[@aria-label='Description']")),taskdetails,"Task details");
		//Changed on 10/11/2023
		//click(getDriver().findElement(By.xpath("//button[@aria-label='Save (CTRL+S)']")),"Save button");
		click(getDriver().findElement(By.xpath("//button[@data-id='task|NoRelationship|Form|Mscrm.Form.task.Save']")),"Save button");

		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		click(getDriver().findElement(By.xpath("//button[@data-id='task|NoRelationship|Form|Mscrm.Form.task.SaveAsComplete']")),"Save button");
		return this;
	}

	public SupplierFormPage selectRagrding( String entityCode) throws InterruptedException, IOException   {
		click(getDriver().findElement(By.xpath("//input[@aria-label='Regarding, Lookup']")),"Click the REgarding text box");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Regarding, Lookup']")),entityCode, "Entity code");
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@data-id,'regardingobjectid.fieldControl-ix_premierein')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+entityCode+"')]")),"Direct Parent");
		return this;
	}


	public SupplierFormPage openActivitiesTab() {
		click(getDriver().findElement(By.xpath("//li[contains(text(),'ACTIVITIES')]")),"Open Activitiy TAB ");	
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='New Activity']")));
		return this;
	}



	//Select Open Activities
	public SupplierFormPage selectOpenActivitiesView() throws InterruptedException   {
		Thread.sleep(2000);
		Thread.sleep(2000);
		List<WebElement> dropdown= getDriver().findElements(By.xpath("(//span[contains(@id,'ViewSelector')])[4]"));
		if(dropdown.size()>0) {
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[4]")),"Select a view");
		}else {
			click(getDriver().findElement(By.xpath("(//span[contains(@id,'ViewSelector')])[2]")),"Select a view");
		}
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Open Activity Associated View')]")),"Open Activitiy Associtated View");
		Thread.sleep(15000);
		return this;

	}


	//Select Open Activities
	public SupplierFormPage selectDeactivatedAccountNumber() throws InterruptedException   {
		Thread.sleep(2000);
		Thread.sleep(2000);

		click(getDriver().findElement(By.xpath("//span[contains(@id,'ViewSelector')]")),"Select a view");

		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Deactivated Account Numbers')]")),"Deactivated Account Numbers");
		Thread.sleep(15000);
		return this;

	}

	//
	public SupplierFormPage navigateToTimeline()throws InterruptedException {
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")),"Account Name");	
		Thread.sleep(2000);
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='address1_postalcode.fieldControl-text-box-text']")),"Zip Code");
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='ix_tollfreeno.fieldControl-text-box-text']")),"Toll Free");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Fax')]")), "Fax");
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Receive Direct Mail')]")), "Recieve Direct Mail");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Do Not Verify Address')]")), "Do not Verify Address");
		Thread.sleep(2500);
		//Wave2 Update 
		click(getDriver().findElement(By.xpath("//h3[contains(text(),'Timeline')]")), "Time Line");
		return this;
	}

	public SupplierFormPage clickNewActivityunderTimeline() {

		click(getDriver().findElement(By.xpath("//button[@title='Create a timeline record.']")),"Click + button");
		click(getDriver().findElement(By.xpath("//div[normalize-space()='Task']")),"Click Task button");
		return this;
	}



	//Verify Diversity Info fields
	public SupplierFormPage verifyDiversityInfoFields() throws InterruptedException {
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")),"Account Name");	
		Thread.sleep(2000);
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='address1_postalcode.fieldControl-text-box-text']")),"Zip Code");
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='ix_tollfreeno.fieldControl-text-box-text']")),"Toll Free");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Fax')]")), "Fax");
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Receive Direct Mail')]")), "Recieve Direct Mail");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Do Not Verify Address')]")), "Do not Verify Address");
		Thread.sleep(2500);
		//Wave2 Update 
		click(getDriver().findElement(By.xpath("//*[@data-lp-id='SubGridStandard:ix_diversityinformation-OverflowButton']")), "Diversity Information");
		Thread.sleep(5000);
		Actions a = new Actions(getDriver());
		//Wave2 Update
		a.moveToElement(getDriver().findElement(By.xpath("//button[@data-id='ix_diversityinformation|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_diversityinformation.AddNewStandard']"))).click().build().perform();
		Thread.sleep(4000);
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='Account']")), "Account", "Account");
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='Diversity Type']")), "Diversity Type", "Diversity Type");
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='Certifying Agency']")), "Certifying Agency", "Certifying Agency");
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='Start Date']")), "Start Date", "Start Date");
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='End Date']")), "End Date", "End Date");
		Thread.sleep(2000);
		return this;		
	}

	//Add New Diversity Info
	public SupplierFormPage addNewDiversityInfo(String certifyingAgency,String diversityType, String diversityStartDate) throws InterruptedException {
		Thread.sleep(3000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")))),diversityStartDate,"Start Date");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_diversitytype.fieldControl-option-set-select']")),"Diversity Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+diversityType+"')]")),diversityType);
		verifyExactValue(getDriver().findElement(By.xpath("//button[@data-id='ix_diversitytype.fieldControl-option-set-select']")),diversityType,"Diversity Type");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");	
		Thread.sleep(2000);
		return this;
	}


	public SupplierFormPage verifyMinorityOwnedOption() throws InterruptedException {

		//List<WebElement> ele=getDriver().findElements(By.xpath("//div[contains(@class,'last ag-after-created')]//div//label[@aria-label='Minority Owned']"));
		//Wave2 Update
		List<WebElement> ele=getDriver().findElements(By.xpath("//div[@col-id='ix_diversitytype']//div//label[@aria-label='Minority Owned']"));
		verifyElementisDisplayed(ele.size(), "Minority Owned");

		return this;
	}

	public SupplierFormPage deactivatenewlyaddedDiversity() throws InterruptedException {

		Actions a = new Actions(getDriver());
		//Wave2 update
		a.moveToElement(getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']"))).click().build().perform();
		click(getDriver().findElement(By.xpath("(//button//span[contains(text(),'Deactivate')])[2]")),"Deactivate");
		click(getDriver().findElement(By.xpath("(//button//span[contains(text(),'Deactivate')])[3]")),"Deactivate Button");
		return this;

	}

	//*********************************Diversity Information*****************

	//Verify New Diversity Type Options 

	public SupplierFormPage verifyNewDiversityTypesAndFields() throws InterruptedException {

		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")),"Account Name");	
		Thread.sleep(2000);
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='address1_postalcode.fieldControl-text-box-text']")),"Zip Code");
		//clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='ix_tollfreeno.fieldControl-text-box-text']")),"Toll Free");
		Thread.sleep(2000);
		//Wave2 Update 
		click(getDriver().findElement(By.xpath("//*[@data-lp-id='SubGridStandard:ix_diversityinformation-OverflowButton']")), "Diversity Information");
		Thread.sleep(5000);
		Actions a = new Actions(getDriver());
		//span[text()='New Diversity Information']
		a.moveToElement(getDriver().findElement(By.xpath("//button[@data-id='ix_diversityinformation|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_diversityinformation.AddNewStandard']"))).click().build().perform();				
		Thread.sleep(5000);		
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='Account']")), "Account", "Account");
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='Diversity Type']")), "Diversity Type", "Diversity Type");
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='Certifying Agency']")), "Certifying Agency", "Certifying Agency");
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='Start Date']")), "Start Date", "Start Date");
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='End Date']")), "End Date", "End Date");
		Thread.sleep(3000);
		//Wave2 2024 Update
		// Create Expected Array List
		List<String> expectedDiversityType = Arrays.asList("--Select--","Minority Owned","Women Owned","Veteran Owned","Small Business","LGBT Owned","Service Disabled Veteran","Disabled Business Enterprise");	 	
		//Create Actual blank Array List
		List<String> actualDiversityType=new ArrayList<String>();
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")),"Diversity Type DropDown");
		getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")).sendKeys(Keys.ENTER);
		for (int i = 1; i <=8;i++ ) {
			String title = getDriver().findElement(By.xpath("//button[@data-id='ix_diversitytype.fieldControl-option-set-select']")).getText();
			actualDiversityType.add(title);			
			System.out.println("The Actual Diversity Type is : "  + " " +title);
			enterArrowDownAndEnter(getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")),"Diversity Type :" + title);
			if(expectedDiversityType.containsAll(actualDiversityType))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "Diversity Type- " + "   " + title + "  " +  "-  Option is available to choose from the list" + " "+ expectedDiversityType,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "Diversity Type - "+   "   " + title + "  " + "- Option is not available in the list"  + " "+ expectedDiversityType ,	screenshotCapture());
				Driver.failCount++;
			} 
			Thread.sleep(3000);

		if (title.equals("Disabled Business Enterprise")) {
				Thread.sleep(1000);		
				break;	
			}
		}

		return this;

	}


	//choose related>Diversity Information
	public SupplierFormPage chooseRelatedDiversityInformation() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		click(getDriver().findElement(By.xpath("(//span[text()='Diversity Information'])[2]")),"Related > Diversity Information");
		Thread.sleep(4000);
		return this;
	}

	//Verify Diversity Information Associated view
	public SupplierFormPage diversityInfoAssociatedView() throws InterruptedException {

		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		click(getDriver().findElement(By.xpath("(//span[text()='Diversity Information'])[2]")),"Related > Diversity Information");
		Thread.sleep(6000);			
		//List<WebElement> colmns = getDriver().findElements(By.xpath("//div[@class='wj-colheaders']/div[@class='wj-row']/div/div/div/div/div[1]"));
		List<WebElement> colmns = getDriver().findElements(By.xpath("//*[@role='columnheader']//label"));
		//Wave2 Update
		List<String> expectedcolumns =Arrays.asList("Diversity Type","Start Date","End Date");
		List<String> actualcolumns=new ArrayList<String>();
		for(WebElement col : colmns)
		{
			String data = col.getText();
			//Array fix
			if (data.isBlank()) {
				System.out.println("Blank Value found in Column Header");
			}else{
				actualcolumns.add(data);
				System.out.println(data);
			}
		}
		if(actualcolumns.equals(expectedcolumns))
		{ 
			setReport().log(Status.PASS, "Actual Columns :" + actualcolumns +" in 'Diveristy Information Associated View' matches all the expected columns :" +expectedcolumns,screenshotCapture()); }

		else {
			setReport().log(Status.FAIL, "Actual Columns :" + actualcolumns +" in ' Diveristy Information Associated View' doesn't match all the expected columns :" + expectedcolumns ,screenshotCapture()); }

		return this;
	}

	public SupplierFormPage navigateToDiversity() throws InterruptedException 
	{
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		click(getDriver().findElement(By.xpath("(//span[text()='Diversity Information'])[2]")),"Related > Diversity Information");
		Thread.sleep(10000);
		return this;
	}

	//Verify Active Diversity Information Views
	public SupplierFormPage activeDiversityView() throws InterruptedException {

		if (getDriver().findElement(By.xpath("//span[@class='symbolFont ChevronDownMed-symbol  ']")).isDisplayed())
		{
			click(getDriver().findElement(By.xpath("//span[@class='symbolFont ChevronDownMed-symbol  ']")),"Diversity Information Associated View -Down Arrow");
			Thread.sleep(10000);
			click(getDriver().findElement(By.xpath("//span[text()='Active Diversity Informations']")),"Active Diversity Information View");
			Thread.sleep(5000);	
			//Wave2 Update		
			click(getDriver().findElement(By.xpath("//span[@class='symbolFont SiteMap-symbol  ']")), "HamBurger Site Map Icon");
			//List<WebElement> colmns = getDriver().findElements(By.xpath("//div[@class='wj-colheaders']/div[@class='wj-row']/div/div/div/div/div[1]"));
			//List<WebElement> colmns = getDriver().findElements(By.xpath("//*[@role='columnheader']//label"));
			//Wave2 Update
			List<WebElement> colmns = getDriver().findElements(By.xpath("//label[contains(@class,'ms-Label headerText')]"));
			List<String> expectedcolumns =Arrays.asList("Diversity Type","Start Date","End Date","Account", "Entity Code (Account)", "CRM Account # (Account)","Account Name (Account)","Account Status (Account)","Store/Location Type (Account)");
			List<String> actualcolumns=new ArrayList<String>();
			for(WebElement col : colmns)
			{
				String data = col.getText();
				//Array fix
				if (data.isBlank()) {
					System.out.println("Blank Value found in Column Header");
				}else {
					actualcolumns.add(data);
					System.out.println("The activeDiversityView Actual column name is  : " +data);

				}}
			if(actualcolumns.equals(expectedcolumns))
			{ 
				setReport().log(Status.PASS, "The Actual columns : " + actualcolumns + " in 'Active Diveristy Information View' matches all the expected columns  : " + expectedcolumns,	screenshotCapture()); }

			else {
				setReport().log(Status.FAIL, "The Actual columns : " + actualcolumns + " in 'Active Diveristy Information View' doesn't match all the expected columns : " + expectedcolumns,screenshotCapture()); }

		}
		return this;
	}

	//Click Related and Contracts
	public SupplierFormPage clickRelatedContracts() throws InterruptedException   {
		Thread.sleep(5000);

		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//span[text()='Contracts'])[2]")),"Contracts");
		Thread.sleep(2000);
		return this;
	}


	//Choose Active Contracts View
	public SupplierFormPage chooseActiveContractsView() throws InterruptedException {

		if (getDriver().findElement(By.xpath("//span[@class='symbolFont ChevronDownMed-symbol  ']")).isDisplayed())
		{
			click(getDriver().findElement(By.xpath("//span[@class='symbolFont ChevronDownMed-symbol  ']")),"Contracts View -Down Arrow");
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//span[text()='Active Contracts']")),"Active Contracts View");
			Thread.sleep(2000);
		}
		else {
			//Do Nothing
		}
		return this;
	}


	//Verify Active Contracts View
	public SupplierFormPage activeContractsView() throws InterruptedException {

		if (getDriver().findElement(By.xpath("//span[@class='symbolFont ChevronDownMed-symbol  ']")).isDisplayed())
		{
			click(getDriver().findElement(By.xpath("//span[@class='symbolFont ChevronDownMed-symbol  ']")),"Contracts View -Down Arrow");
			Thread.sleep(5000);
			click(getDriver().findElement(By.xpath("//span[text()='Active Contracts']")),"Active Contracts View");
			Thread.sleep(3000);			
			click(getDriver().findElement(By.xpath("//span[@class='symbolFont SiteMap-symbol  ']")), "HamBurger Site Map Icon");
			List<WebElement> colmns = getDriver().findElements(By.xpath("//label[contains(@class,'ms-Label headerText')]"));
			List<String> expectedcolumns =Arrays.asList("Contract Number","Description","Activation Method","Start Date","End Date");
			List<String> actualcolumns=new ArrayList<String>();
			for(WebElement col : colmns)
			{
				String data = col.getText();
				if (data.isBlank()) {
					System.out.println("Blank Value found in Column Header");
				}else {
					actualcolumns.add(data);
					System.out.println("The Active Contracts View Actual column name is  : " +data);

				}}
			if(actualcolumns.equals(expectedcolumns))
			{ 
				setReport().log(Status.PASS, "The Actual columns : " + actualcolumns + " in 'Active Contracts View' matches all the expected columns  : " + expectedcolumns,	screenshotCapture()); }

			else {
				setReport().log(Status.FAIL, "The Actual columns : " + actualcolumns + " in 'Active Contracts View' doesn't match all the expected columns : " + expectedcolumns,screenshotCapture()); }

		}
		return this;
	}

	//Verify inactive Contracts View
	public SupplierFormPage inactiveContractsView() throws InterruptedException {

		if (getDriver().findElement(By.xpath("//span[@class='symbolFont ChevronDownMed-symbol  ']")).isDisplayed())
		{
			click(getDriver().findElement(By.xpath("//span[@class='symbolFont ChevronDownMed-symbol  ']")),"Contracts View -Down Arrow");
			Thread.sleep(5000);
			click(getDriver().findElement(By.xpath("//span[text()='Inactive Contracts']")),"Inactive Contracts View");
			Thread.sleep(3000);			
			click(getDriver().findElement(By.xpath("//span[@class='symbolFont SiteMap-symbol  ']")), "HamBurger Site Map Icon");
			List<WebElement> colmns = getDriver().findElements(By.xpath("//label[contains(@class,'ms-Label headerText')]"));
			List<String> expectedcolumns =Arrays.asList("Contract Number","Description","Activation Method","Start Date","End Date");
			List<String> actualcolumns=new ArrayList<String>();
			for(WebElement col : colmns)
			{
				String data = col.getText();
				//Array fix
				if (data.isBlank()) {
					System.out.println("Blank Value found in Column Header");
				}else {
					actualcolumns.add(data);
					System.out.println("The Inactive Contracts View Actual column name is  : " +data);

				}}
			if(actualcolumns.equals(expectedcolumns))
			{ 
				setReport().log(Status.PASS, "The Actual columns : " + actualcolumns + " in 'Inactive Contracts View' matches all the expected columns  : " + expectedcolumns,	screenshotCapture()); }

			else {
				setReport().log(Status.FAIL, "The Actual columns : " + actualcolumns + " in 'Inactive Contracts View' doesn't match all the expected columns : " + expectedcolumns,screenshotCapture()); }

		}
		return this;
	}


	//Choose CAA Click on deactivate button
	public SupplierFormPage clickOnCAAandDeactivate() throws InterruptedException {
		//click(getDriver().findElement(By.xpath("//div[@data-id='cell-0-1']")), " CAA ");
		//click(getDriver().findElement(By.xpath("//i[@data-icon-name='StatusCircleCheckmark']")), " CAA ");
		//Wave2 Update
		click(getDriver().findElement(By.xpath("(//i[@data-icon-name='CheckMark'])[3]")), " CAA ");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[2]")), "Deactivate Button");
		return this;
	}

	// Confirm deactivate button
	public SupplierFormPage clickConfirmCAADeactivation() throws InterruptedException {
		click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[3]")), "Deactivate");
		Thread.sleep(10000);
		return this;
	}

	//Verify  Inactive Diversity Information Views
	public SupplierFormPage inactiveDiversityView() throws InterruptedException {


		if (getDriver().findElement(By.xpath("//span[@class='symbolFont ChevronDownMed-symbol  ']")).isDisplayed())
		{
			click(getDriver().findElement(By.xpath("//span[@class='symbolFont ChevronDownMed-symbol  ']")),"Active Diversity Information Associated View -Down Arrow");
			Thread.sleep(5000);
			click(getDriver().findElement(By.xpath("//*[text()='Inactive Diversity Informations']")),"InActive Diversity Information View");
			Thread.sleep(3000);	
			//Wave2 Update		
			click(getDriver().findElement(By.xpath("//span[@class='symbolFont SiteMap-symbol  ']")), "HamBurger Site Map Icon");
			click(getDriver().findElement(By.xpath("//span[@class='symbolFont SiteMap-symbol  ']")), "HamBurger Site Map Icon");
			//List<WebElement> colmns = getDriver().findElements(By.xpath("//div[@class='wj-colheaders']/div[@class='wj-row']/div/div/div/div/div[1]"));
			List<WebElement> colmns = getDriver().findElements(By.xpath("//*[@role='columnheader']//label"));
			//Wave2 Update
			List<String> expectedcolumns =Arrays.asList("Diversity Type","Start Date","End Date","Account", "Entity Code (Account)", "CRM Account # (Account)","Account Name (Account)","Account Status (Account)","Store/Location Type (Account)");
			List<String> actualcolumns=new ArrayList<String>();
			for(WebElement col : colmns)
			{
				String data = col.getText();
				//Array fix
				if (data.isBlank()) {
					System.out.println("Blank Value found in Column Header");
				}else {
					actualcolumns.add(data);
					System.out.println("The inactiveDiversityView Actual Column Name is : " + data);

				}}
			if(actualcolumns.equals(expectedcolumns))
			{ 
				setReport().log(Status.PASS,"The Actual columns : " + actualcolumns + " in 'InActive Diveristy Information View' matches all the expected columns :" + expectedcolumns,screenshotCapture()); }

			else {
				setReport().log(Status.FAIL,"The Actual columns : " + actualcolumns + " in 'InActive Diveristy Information View' doesn't match all the expected columns :" + expectedcolumns,screenshotCapture()); }

		}
		return this;
	}


	//Certifying Agency field validation
	public SupplierFormPage verifyCertifyingAgency(String verifyCertifyingAgencyError,String certifyingAgency) throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		click(getDriver().findElement(By.xpath("(//span[text()='Diversity Information'])[2]")),"Related > Diversity Information");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='New Diversity Information']"))," + New Diversity Information");
		Thread.sleep(1500);
		click(getDriver().findElement(By.xpath("//span[text()='Save']")),"Save Button");
		Thread.sleep(2000);
		verifyExactText(getDriver().findElement(By.xpath("//span[text()='Certifying Agency: Required fields must be filled in.']")), verifyCertifyingAgencyError, "Certifying Agency Mandatory Error");
		Thread.sleep(5000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(3000);
		//Wave2 Update
		click(getDriver().findElement(By.xpath("//span[text()='Save']")),"Save");	
		Thread.sleep(10000);
		return this;
	}

	//Deactivate Diversity Info from Diversity Info Associated View
	public SupplierFormPage deactivateDiversityInfoDivAssociatedView() throws InterruptedException {
		Thread.sleep(2000);
		//click(getDriver().findElement(By.xpath("//div[@data-id='cell-0-1']")), "Check Mark on Diversity Info");
		//click(getDriver().findElement(By.xpath("//i[@data-icon-name='StatusCircleCheckmark']")), "Check Mark on Radio Button to Select All Diversity Info");					
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//span[text()='Deactivate']")), "Deactivate Button");
		Thread.sleep(6000);
		click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[2]")), "Confirm Deactivate");
		Thread.sleep(3000);
		return this;

	}

	//Verify Minority Owned Diversity Type's Sub Classification options
	public SupplierFormPage verifyMinorityOwndSubClassificationOptions() throws InterruptedException {
		Select subClass= new  Select(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")));		
		// Create Expected Array List
		List<String> expectedMinOwnSubClass = Arrays.asList("---","Asian-Indian","Asian-Pacific","Black","Hispanic","Native American");		
		//Create Actual blank Array List
		List<String> actualMinOwnSubClass=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =subClass.getOptions();	
		System.out.println("The Number of Minority Owned Diversity Type's Sub Classifications available : "  + " " + mylist.size());
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualMinOwnSubClass.add(data);								
			System.out.println("The Actual Minority Owned Diversity Type's Sub Classifications available : "  + " " +data);				
			Thread.sleep(3000);
			if(expectedMinOwnSubClass.containsAll(actualMinOwnSubClass))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "Diversity Type- " + "   " + data + "  " +  "-  Option is available to choose from the list" + " "+ expectedMinOwnSubClass,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "Diversity Type - "+   "   " + data + "  " + "- Option is not available in the list"  + " "+ expectedMinOwnSubClass ,	screenshotCapture());
				Driver.failCount++;
			}


		}
		return this;

	}

	//Select Diversity type
	public SupplierFormPage selectDiversityType(String diversityType) throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		click(getDriver().findElement(By.xpath("(//span[text()='Diversity Information'])[2]")),"Related > Diversity Information");
		Thread.sleep(6000);
		click(getDriver().findElement(By.xpath("//span[text()='New Diversity Information']")),"+ New Diversity Information");
		Thread.sleep(3000);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")),diversityType,"Diversity Type");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")),diversityType,"Diversity Type");
		return this;
	}


	//Verify Duplicate Diversity Info Error
	public SupplierFormPage verifyduplicateDiversityInfoError(String dupDiversityErrorText) throws InterruptedException {
		Thread.sleep(3500);
		//Wave2 Update -errorDialog_subtitle
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']")), dupDiversityErrorText, "Duplicate Diversity Info Error");
		//Wave2 Update errorOkButton
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")), "OK Button");				
		click(getDriver().findElement(By.xpath("//span[contains(@class,'symbolFont BackButton-symbol')]")), "Back <-- Button");
		//Wave2 Update cancelButton
		click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")), "Discard Changes");
		return this;
	}

	//Deactivate Minority Owned Diversity Info through Supplier Form
	public SupplierFormPage deactivateDiversityInfo() throws InterruptedException {
		Thread.sleep(5000);
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")),"Account Name");	
		Thread.sleep(2000);
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='address1_postalcode.fieldControl-text-box-text']")),"Zip Code");
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='ix_tollfreeno.fieldControl-text-box-text']")),"Toll Free");
		click(getDriver().findElement(By.xpath("//span[text()='Minority Owned']")), "Minority Owned");
		Thread.sleep(3000);
		//Wave2 Update
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_diversityinformation|NoRelationship|Form|Mscrm.Form.ix_diversityinformation.Deactivate']")), "Deactivate Button");
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ok_id']")), "Confirm Deactivate");
		return this;

	}

	//Add New Primary Contact on Supplier Form
	public SupplierFormPage addNewSupplierPrimaryContact(String contactFirstName , String contactLastName) throws InterruptedException {	
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='name.fieldControl-text-box-text']")),"Account Name");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")),"Primary Contact");
		Thread.sleep(5000);
		Actions a = new Actions(getDriver());	
		//Wave2 Update
		a.moveToElement(getDriver().findElement(By.xpath("//span[text()='New Contact']"))).click().build().perform();
		Thread.sleep(5000);
		//Wave2 Update
		click(getDriver().findElement(By.xpath("//input[@data-id='firstname.fieldControl-text-box-text']")),"Contact First Name");
		type(((getDriver().findElement(By.xpath("//input[@data-id='firstname.fieldControl-text-box-text']")))),contactFirstName,"Contact First Name");
		//click(getDriver().findElement(By.xpath("//label[text()='First Name']/following::input[@aria-label='First Name']")),"Contact First Name");
		//type(((getDriver().findElement(By.xpath("//label[text()='First Name']/following::input[@aria-label='First Name']")))),contactFirstName,"Contact First Name");
		click(getDriver().findElement(By.xpath("//input[@data-id='lastname.fieldControl-text-box-text']")),"Contact Last Name");
		type(((getDriver().findElement(By.xpath("//input[@data-id='lastname.fieldControl-text-box-text']")))),contactLastName,"Contact Last Name");
		click(getDriver().findElement(By.xpath("//button[@data-id='quickCreateSaveAndCloseBtn']")),"Save and Close");
		Thread.sleep(6000);
		return this;
	} 


	//+New Diversity Info
	public SupplierFormPage addNewDiversityInfo() throws InterruptedException {
		Thread.sleep(2500);
		click(getDriver().findElement(By.xpath("//span[text()='New Diversity Information']")),"+ New Diversity Information");
		Thread.sleep(4000);	
		return this;
	}

	//Choose Existing Branding Approval
	public SupplierFormPage doubleClickAnyExistingBrandingApproval() throws InterruptedException   {
		Thread.sleep(4000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='ix_approvaltype']//label"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 1; i <=rowList.size(); i++) {
			String text = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_approvaltype']//label)["+i+"]")).getText();
			System.out.println(text);
			if (text.equals("Use of Company Logo")) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_approvaltype']//label)["+i+"]")), "Use of Company Logo");
				Thread.sleep(3000);
				break;
			}
			else if (text.equals("Use of Product Images")) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label)["+i+"]")), "Use of Product Images");
				Thread.sleep(3000);	
				break;
			}						
		}					

		return this;					
	}
	//Verify Branding Approvals Associated View is Displayed
	public SupplierFormPage verifyBrandingApprvlAssocView() {
		List<WebElement> brandingAprvlAssocView=getDriver().findElements(By.xpath("//span[text()='Branding Approvals Associated View']"));
		verifyElementisDisplayed(brandingAprvlAssocView.size(), "Branding Approval Associated View");
		return this;
	}

	//click on Branding Approvals Associated View Drop Down
	public SupplierFormPage clickBrandingApprvlAssocView() {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Branding Approvals Associated View')]")));			
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Branding Approvals Associated View')]")),"Branding Approval Associated View Drop Down");
		return this;
	}

	//click on Branding Approvals Associated View Drop Down
	public SupplierFormPage selectActiveBrandingApprvlsView() {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Active Branding Approvals')]")));	
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Active Branding Approvals')]")),"Active Branding Approvals");
		return this;
	}

	//click on Branding Approvals Associated View Drop Down
	public SupplierFormPage selectInactiveBrandingApprvlsView() {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Inactive Branding Approvals')]")));	
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Inactive Branding Approvals')]")),"Inactive Branding Approvals");
		return this;
	}



	//Verify Added Branding Approval is Displayed
	public SupplierFormPage verifyAddedBrandingApprvlIsDisplayed(String approvaltype) {
		List<WebElement> brandingAprvl=getDriver().findElements(By.xpath("//div[text()='" + approvaltype +"']"));
		verifyElementisDisplayed(brandingAprvl.size(), approvaltype );
		return this;
	}

	public SupplierFormPage selectRelatedBrandingApprovals() throws InterruptedException {
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Branding Approvals']")),"Branding Approvals");
		Thread.sleep(4000);
		return this;
	}
	//Branding Approval Button is NOT displayed
	public SupplierFormPage verifyAddNewBrandingApprovalButtonIsNotDisplayed() {
		List<WebElement> addNewBrandingApprvl=getDriver().findElements(By.xpath("//button[@data-id='ix_branding|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_branding.AddNewStandard']"));
		verifyElementisNotDisplayed(addNewBrandingApprvl.size(), "+ New Branding Approval");
		return this;
	}

	//Branding Approval Button is displayed
	public SupplierFormPage verifyAddNewBrandingApprovalButtonIsDisplayed() {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ix_branding|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_branding.AddNewStandard']")));
		List<WebElement> addNewBrandingApprvl=getDriver().findElements(By.xpath("//button[@data-id='ix_branding|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_branding.AddNewStandard']"));
		verifyElementisDisplayed(addNewBrandingApprvl.size(), "+ New Branding Approval");
		return this;
	}

	//Add New Branding Approval
	public SupplierFormPage clickAddNewBrandingApprovalButton() {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_branding|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_branding.AddNewStandard']")),"Branding Approval");
		return this;
	}

	//Choose Start Date of Use
	public SupplierFormPage typeStartDateOfUse() throws InterruptedException {
		Thread.sleep(2000);
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String date1= dateFormat.format(date); 
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_startdateofuse.fieldControl-date-time-input']")),date1,"Start Date of Use");
		return this;
	}

	//Choose End Date of Use
	public SupplierFormPage typeEndDateOfUse() throws InterruptedException {
		Thread.sleep(2000);
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String date1= dateFormat.format(date); 
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_enddateofuse.fieldControl-date-time-input']")),date1,"End Date of Use");
		return this;
	}

	//Choose End Date of Use
	public SupplierFormPage clearEndDateOfUse() throws InterruptedException {
		clear(getDriver().findElement(By.xpath("//*[@data-id='ix_enddateofuse.fieldControl-date-time-input']")),"End Date of Use");
		return this;
	}

	//Choose Approval Date
	public SupplierFormPage typeApprovalDate() throws InterruptedException {
		Thread.sleep(2000);
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String date2= dateFormat.format(date); 
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_approvaldate.fieldControl-date-time-input']")),date2,"Approval Date");
		return this;
	}

	//Enter Approval By
	public SupplierFormPage typeApprovedBy(String name) throws InterruptedException {
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_name.fieldControl-text-box-text']")),name,"Approved By");
		return this;
	}

	//Clear Approval By
	public SupplierFormPage clearApprovedBy() throws InterruptedException {
		clear(getDriver().findElement(By.xpath("//*[@data-id='ix_name.fieldControl-text-box-text']")),"Approved By");
		return this;
	}

	//Choose Approval Type
	public SupplierFormPage selectApprovalType(String approvaltype) {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_approvaltype.fieldControl-option-set-select']")),approvaltype,"Approval Type");
		return this;
	}

	//Save & Close On Branding Approvals
	public SupplierFormPage clickSaveAndCloseBrandingApprovals() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close Button");
		Thread.sleep(3000);
		return this;
	}

	//Save  On Branding Approvals
	public SupplierFormPage clickSaveOnBrandingApprovals() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_branding|NoRelationship|Form|Mscrm.Form.ix_branding.Save']")),"Save Button");
		Thread.sleep(3000);
		return this;
	}


	//Deactivate All Branding Approvals
	public SupplierFormPage deactivateAllBrandingApprovals() throws InterruptedException {
		Thread.sleep(5000);
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']"))).click().build().perform();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[2]")), "Deactivate Button"); 
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[3]")), "Deactivate");
		Thread.sleep(3000);
		return this;
	}

	//Deactivate All Branding Approvals
	public SupplierFormPage deactivateMultipleBrandingApprovals() throws InterruptedException {
		Thread.sleep(5000);
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("(//i[@data-icon-name='CheckMark'])[5]"))).click().build().perform();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[2]")), "Deactivate Button"); 
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[3]")), "Deactivate");
		Thread.sleep(3000);
		return this;
	}

	//Activate All Branding Approvals
	public SupplierFormPage activateAllBrandingApprovals() throws InterruptedException {
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']"))).click().build().perform();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Activate']")), "Activate Button"); 
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//span[text()='Activate'])[2]")), "Confirm pop up Activate button");
		Thread.sleep(3000);
		return this;
	}


	//Activate Multiple Branding Approvals
	public SupplierFormPage activateMultipleBrandingApprovals() throws InterruptedException {
		Thread.sleep(3000);
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("(//i[@data-icon-name='CheckMark'])[5]"))).click().build().perform();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Activate']")), "Activate Button"); 
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//span[text()='Activate'])[2]")), "Confirm pop up Activate button");
		Thread.sleep(3000);
		return this;
	}

	//Select All Radio Button
	public SupplierFormPage clickOnSelectAllRadioButton() throws InterruptedException {
		Thread.sleep(2500);
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("(//i[@data-icon-name='CheckMark'])[3]"))).click().build().perform();
		Thread.sleep(2000);
		return this;
	}

	//Deactivate Button on Branding Approval
	public SupplierFormPage verifyDeactivateButtonIsNotDisplayedBrandingApproval() throws InterruptedException {
		Thread.sleep(2000);
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_branding|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_branding.Deactivate']")).size(),"Deactivate Button");
		Thread.sleep(2000);
		return this;
	}


	public SupplierFormPage typeAccountName(String accountName) {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String executiondate = dateFormat.format(date);
		click(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")),"Account Name");
		type(((getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")))),accountName+ "_" + executiondate,"Account Name");
		return this;
	}

	public SupplierFormPage defaultAccountType(String defaultAccountType) {
		if(getDriver().findElements(By.xpath("//*[@data-id='customertypecode.fieldControl-option-set-select']")).size()>0) {
		verifyExactValue((getDriver().findElement(By.xpath("//*[@data-id='customertypecode.fieldControl-option-set-select']"))),defaultAccountType,"Account Type");
		}
		else {
		verifyExactValue((getDriver().findElement(By.xpath("//*[@aria-label='Account Type'][@readonly]"))),defaultAccountType,"Account Type");
		}
		return this;
	}
	

	public SupplierFormPage defaultAccountTypeSupplier(String defaultAccountType) {
		verifyExactValue((getDriver().findElement(By.xpath("//*[@aria-label='Account Type'][@readonly]"))),defaultAccountType,"Account Type");
		return this;
	}
	public SupplierFormPage pickPremierStartDate(String premierStartDate) throws InterruptedException {
		Thread.sleep(1000);
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Premier Start Date']")),premierStartDate,"Premier Start Date");
		return this;
	}

	public SupplierFormPage pickContractEffectiveDate(String contractEffectiveDate) throws InterruptedException {
		navigateToPrimaryContact();
		Thread.sleep(3000);
		type(getDriver().findElement(By.xpath("//input[contains(@id,'DateControlPrefix')][@aria-label='Date of Contract Effective Date']")),contractEffectiveDate,"Contract Effective Date");
		return this;
	}

	public SupplierFormPage verifyContractsOption() throws InterruptedException {
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		verifyElementisDisplayed(getDriver().findElements(By.xpath("(//span[text()='Contracts'])[2]")).size(), "Contracts Option");
		Thread.sleep(2000);
		return this;
	}

	public SupplierFormPage clearContractEffectiveDate() throws InterruptedException {
		Thread.sleep(1000);
		click(getDriver().findElement(By.xpath("//*[@aria-label='Date of Contract Effective date']")),"Clear Contract Effective Date");
		WebElement ele =getDriver().findElement(By.xpath("//input[@aria-label='Date of Contract Effective date']"));
		ele.sendKeys(Keys.CONTROL, Keys.chord("a"));
		Thread.sleep(3000);
		ele.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		clickSave();
		return this;
	}	


	public SupplierFormPage recordChangeStatus(String recordChangeStatus) {
		verifyExactTextWithTitleAttribute((getDriver().findElement(By.xpath("//*[@data-id='ix_recordchangestatus.fieldControl-option-set-select']"))),recordChangeStatus," Record Change Status");
		return this;
	}

	public SupplierFormPage verifyRecordChangeStatus(String verifyRecordChangeStatus) {
		verifyExactTextWithTitleAttribute((getDriver().findElement(By.xpath("//*[@data-id='ix_recordchangestatus.fieldControl-option-set-select']"))),verifyRecordChangeStatus," Record Change Status");
		return this;
	}

	public SupplierFormPage verifyDefaultRecordChangeStatus() {
		System.out.println(getDriver().findElement(By.xpath("//*[@data-id='ix_recordchangestatus.fieldControl-option-set-select']")).getAttribute("title"));
		verifyExactTextWithTitleAttribute((getDriver().findElement(By.xpath("//*[@data-id='ix_recordchangestatus.fieldControl-option-set-select']"))),"Approved"," Record Change Status");
		return this;
	}


	public SupplierFormPage chooseAccountStatus() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@title='ADMINISTRATION']")),"ADMINISTRATION");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_accountstatus.fieldControl-option-set-select']")),"Account Status");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'Inactive')]")),"Account Status");
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountstatus.fieldControl-option-set-select']")),"Inactive","Account Status");
		Thread.sleep(3000);
		return this;
	}

	public SupplierFormPage chooseRecordChangeStatus(String Status) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_recordchangestatus.fieldControl-option-set-select']")),Status,"Record change Status");
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_recordchangestatus.fieldControl-option-set-select']")),Status,"Account Status");
		Thread.sleep(3000);
		return this;
	}
	//*****Method to verify Business Process Error when Supplier Account Status is made Inactive*****//	
	public SupplierFormPage verifyInactiveAccountStatusError(String expectedAccountStatusErrorText) {
		//Wave2 update- updated data-id=errorDialog_subtitle
		verifyPartialText((getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']"))), expectedAccountStatusErrorText,"' Account Status Error' ");
		//Wave2 update- updated data-id=errorOkButton
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"Ok");
		return this;
	}

	//Update member record field
	public SupplierFormPage clearMemberRecordId() throws InterruptedException {
		Thread.sleep(3000);
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='ix_memberrecordid.fieldControl-LookupResultsDropdown_ix_memberrecordid_selected_tag']"))).perform();
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_memberrecordid.fieldControl-LookupResultsDropdown_ix_memberrecordid_selected_tag_delete']")),"Clear Icon"); 
		Thread.sleep(3000);
		return this;
	}


	public SupplierFormPage verifyPremierStartDateAsCurrentDate() throws InterruptedException {
		Thread.sleep(4000);
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String date1= dateFormat.format(date); 
		verifyExactValue(getDriver().findElement(By.xpath("//*[@aria-label='Date of Premier Start Date']")),date1,"Premier Start Date");
		return this;
	}


	public SupplierFormPage selectBusinessClassification(String businessClassification) throws InterruptedException {
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification_textInputBox_with_filter_new']")),"Business Classification");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_businessclassification.fieldControl-LookupResultsDropdown_ix_businessclassification_textInputBox_with_filter_new']")))),businessClassification,"Business Classification");
		Thread.sleep(4000);
		System.out.println("(//span[normalize-space()='"+businessClassification+"'])[2]");
		click(getDriver().findElement(By.xpath("(//span[normalize-space()='"+businessClassification+"'])[2]")),"Business Classification");
		Thread.sleep(4000);
		return this;
	}

	//select Account Type
	public SupplierFormPage verifyselectAccountTypeIsReadOnly(String accountType) throws InterruptedException{
		String value=getDriver().findElement(By.xpath("//select[@aria-label='Account Type']")).getAttribute("disabled");
		assertTrue(value.contains("true"));
		return this;
	}


	public SupplierFormPage pageRefresh() throws InterruptedException {
		getDriver().navigate().refresh();
		Thread.sleep(10000);
		return this;
	}

	public SupplierFormPage gridRefresh() throws InterruptedException {
		click(getDriver().findElement(By.xpath("(//span[text()='Refresh'])[2]")),"Grid Refresh");
		Thread.sleep(4000);
		return this;
	}


	public SupplierFormPage addSupplierPrimaryContact(String addSupplierPrimaryContact) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")),"Primary Contact");
		type(((getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_textInputBox_with_filter_new']")))),addSupplierPrimaryContact,"Primary Contact");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[contains(@id,'primarycontactid.fieldControl-firstname0_0_0')]")),addSupplierPrimaryContact);
		return this;
	}

	public SupplierFormPage verifyPrimaryContactValue(String verifyPrimaryContactValue) throws InterruptedException {
		Thread.sleep(3000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='primarycontactid.fieldControl-LookupResultsDropdown_primarycontactid_selected_tag_text']")), verifyPrimaryContactValue,"Primary Contact");
		return this;
	}

	//Select Related and then CAA
	public SupplierFormPage selectCAA() throws InterruptedException {	
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//span[text()='Contact Account Associations']")),"Contact Account Associations");
		Thread.sleep(2000);
		return this;
	}

	//Select sub accounts from Related
	public SupplierFormPage selectSubaccount() throws InterruptedException {	
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Sub-Accounts')]")),"Sub Accounts");
		return this;
	}

	//click new account in sub account
	public SupplierFormPage clickNewAccountInSubAccount() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='account|NoRelationship|SubGridAssociated|Mscrm.SubGrid.account.AddNewStandard']")),"New");
		return this;
	}


	//Choose Old Primary Contact's  CAA				
	public ContactsPage chooseOldPrimaryContactCAA(String addSupplierPrimaryContact) throws InterruptedException {

		Thread.sleep(2000);
		//Wave2 Update
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='a_87bc6554cf2fe91180f1005056b97654.ix_innovatixcontactid']//label"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 1; i <=rowList.size(); i++) {
			String title = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='a_87bc6554cf2fe91180f1005056b97654.ix_innovatixcontactid']//label[contains(@class,'ms-Label labelRootStyles')])["+i+"]")).getText();
			System.out.println(title);
			if (title.equals(addSupplierPrimaryContact)) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='a_87bc6554cf2fe91180f1005056b97654.ix_innovatixcontactid']//label[contains(@class,'ms-Label labelRootStyles')])["+i+"]")), "Primary Contact's CAA");
				Thread.sleep(3000);
				break;				
			}
		}	

		return new ContactsPage();
	}

	public SupplierFormPage clickSave() throws InterruptedException {
		//click(getDriver().findElement(By.xpath("//*[@data-id='edit-form-save-btn']")),"Save");
		click(getDriver().findElement(By.xpath("//*[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Save']")),"Save");
		Thread.sleep(3000);

		List<WebElement> ignoreMessage=getDriver().findElements(By.xpath("//*[text()='Ignore and save']"));

		if(ignoreMessage.size()>0) {

			click(getDriver().findElement(By.xpath("//*[text()='Ignore and save']")),"Ignore and Save");  }  
		try
		{
			if ( getDriver().findElement(By.xpath("//span[contains(text(),'Save and Continue')]")).isDisplayed())
			{
				click(getDriver().findElement(By.xpath("//span[contains(text(),'Save and Continue')]")),"Save and continue");
			}
			else {		


			}

			WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(180));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-id='account|NoRelationship|Form|Mscrm.Form.account.Save']")));
			Thread.sleep(5000);
			String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
			System.out.println(saveStatus);
			assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
			return this;    


		}
		catch(Exception e)
		{
			//e.printStackTrace();	
		}
		Thread.sleep(3000);
		Thread.sleep(5000);
		return this;

	}

	public SupplierFormPage clickDiversityInformation() {
		click(getDriver().findElement(By.xpath("//h2[contains(text(),'DIVERSITY INFORMATION')]")),"Click Diversity information");
		return this;
	}

	public SupplierFormPage verifyMemberAttribute() {
		List<WebElement> memAttributes=getDriver().findElements(By.xpath("//h3[contains(text(),'MEMBER ATTRIBUTES')]"));
		verifyElementisNotDisplayed(memAttributes.size(), "Member Attributes");
		return this;
	}

	public SupplierFormPage clickVerticalButton() {
		List<WebElement> count=getDriver().findElements(By.xpath("//span[contains(text(),'Refresh')]"));
		if(count.size()>0)
			click(getDriver().findElement(By.xpath("//span[contains(text(),'Refresh')]")),"Click Refresh");
		else {
			click(getDriver().findElement(By.xpath("//span[contains(@class,'MoreVertical-symbol')]")),"Vertical Button");
			click(getDriver().findElement(By.xpath("//button[contains(text(),'Refresh')]")),"Refresh");
		}
		return this;
	}


	public SupplierFormPage navigateToCRM() throws InterruptedException {

		click(getDriver().findElement(By.xpath("//h2[contains(text(),'DIVERSITY INFORMATION')]")),"DIVERSITY INFORMATION");
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='address1_postalcode.fieldControl-text-box-text']")),"Zip Code");
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='ix_tollfreeno.fieldControl-text-box-text']")),"Toll Free");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Fax')]")), "Fax");
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Receive Direct Mail')]")), "Recieve Direct Mail");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Do Not Verify Address')]")), "Do not Verify Address");
		click(getDriver().findElement(By.xpath("//h3[contains(text(),'Timeline')]")),"Timeline");
		click(getDriver().findElement(By.xpath("//*[@title='NY INFORMATION']")),"My Information Label");

		return this;
	}

	public SupplierFormPage crmNumberIsDisplayed() throws InterruptedException {

		Thread.sleep(5000);
		navigateToCRM();
		String sCRMNumber = getAttribute(getDriver().findElement(By.xpath("//*[@data-id='accountnumber.fieldControl-text-box-text']")),"value","CRM Number");
		verifyDisplayed(getDriver().findElement(By.xpath("//div[@data-id='accountnumber-locked-icon']")), "CRM Lock symbol");

		try {
			DataInputProvider.setCellData(sCRMNumber.toString(), Driver.iTestCaseRowNumDriver, "CRMNumber",Driver.properties.getProperty("DriverSheetName"));
			assertNotNull(sCRMNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}



	//Select TP Audit History
	public SupplierFormPage selectMembershipAuditHistory() throws InterruptedException {
		Thread.sleep(2000);
		if(getDriver().findElements(By.xpath("//*[@title='Related']")).size()>0){	
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		}else {
			click(getDriver().findElement(By.xpath("//span[contains(@id,'icon_more_tab')]")),"More Tab");
		}
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Audit History')]")),"Audit History");
		Thread.sleep(2000);
		return this;
	}
	public SupplierFormPage entityCodeIsDisplayed() throws InterruptedException {
		Thread.sleep(6000);
		String entityCode =getTextValue(getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[1]")),"Entity Code");
		assertNotNull(entityCode);
		return this;
	}

	public SupplierFormPage verifyAccountTypeLocked() {
		verifyDisplayed(getDriver().findElement(By.xpath("//select[@aria-label='Account Type' and @Disabled]")),"Account type lock");
		return this;
	}
	public LoginPage clickLogout() {

		click(getDriver().findElement(By.xpath("//*[@id='mectrl_headerPicture']")),"User Name button");
		click(getDriver().findElement(By.xpath("//button[contains(text(),'Sign out')]")),"Sign Out button");
		if(getDriver().findElements(By.xpath("//span[contains(text(),'Discard changes')]")).size()>0) {
			click(getDriver().findElement(By.xpath("//span[contains(text(),'Discard changes')]")),"Discard button");
		}

		return new LoginPage();


	}

	public SupplierFormPage verifyEntityCode(String verifyEntityCode) throws InterruptedException {
		verifyExactText(getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[1]")), verifyEntityCode,"Entity code");
		return this;
	}
	//Click on Accounts in My work
	public AccountsPage selectAccountsTab() throws InterruptedException {	
		click(getDriver().findElement(By.xpath("//span[text()='Accounts']")),"Accounts");
		Thread.sleep(2000);
		return new AccountsPage();
	}

	public SupplierFormPage clickDiscardChanges() throws InterruptedException {
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Discard Changes");
		Thread.sleep(2000);
		return new SupplierFormPage();
	}

	public SupplierFormPage verifyMainAccountEntityCode(String verifyEntityCode) throws InterruptedException {
		Assert.assertFalse(getTextValue(getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[1]")),"Entity Code").equals(verifyEntityCode));
		return this;
	}
	public SupplierFormPage verifyAccountName(String verifyAccountName) throws InterruptedException {		
		verifyExactTextWithTitleAttribute(((getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")))),verifyAccountName,"Account Name");
		return this;
	}

	public SupplierFormPage clickIsTPYes() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_istopparent.fieldControl-option-set-select']")),"Is Top Parent Drop Down");
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Yes')]")),"Is TP as Yes");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_istopparent.fieldControl-option-set-select']")), "Yes","Is Top Parent");
		return this;
	}

	public SupplierFormPage clickIsTPNo() throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_istopparent.fieldControl-checkbox-select']")), "Yes","Is Top Parent");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_istopparent.fieldControl-checkbox-container']")), "Yes","Is Top Parent");
		return this;
	}

	public SupplierFormPage pickTPRDClear() throws InterruptedException {
		getDriver().findElement(By.xpath("//input[contains(@id,'DateControlPrefix')][@aria-label='Date of Top Parent Relation Date']")).click();
		getDriver().findElement(By.xpath("//input[contains(@id,'DateControlPrefix')][@aria-label='Date of Top Parent Relation Date']")).clear();
		return this;
	} 
	public SupplierFormPage pickTPRD(String selectTPRelationDate) throws InterruptedException {
		Thread.sleep(2000);
		//click(getDriver().findElement(By.xpath("//*[@data-id='ix_topparentrelationdate.fieldControl-date-time-input']")),"Top Parent Relation Date");
		type(((getDriver().findElement(By.xpath("//input[contains(@id,'DateControlPrefix')][@aria-label='Date of Top Parent Relation Date']")))),selectTPRelationDate,"Top Parent Relation Date");
		return this;
	}


	//Update DP
	public SupplierFormPage clearDP() throws InterruptedException {

		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//div[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_selected_tag_text']"))).perform();
		click(getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_selected_tag_delete']")),"Clear Icon"); 
		Thread.sleep(3000);
		return this;	
	}

	public SupplierFormPage selectDirectParent(String directParent) throws InterruptedException {	
		click(getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")),"Direct Parent");
		type(((getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")))),directParent,"Direct Parent");
		Thread.sleep(7000);

		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+directParent+"')]")));
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+directParent+"')]")),"Direct Parent");

		//		click(getDriver().findElement(By.xpath("//*[contains(@id,'parentaccountid.fieldControl-ix_premierein')]")),"Direct Parent");
		return this;		
	}





	public SupplierFormPage noMatchforDirectParent(String directParent) throws InterruptedException {	
		click(getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")),"Direct Parent");
		type(((getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")))),directParent,"Direct Parent");
		Thread.sleep(5000);
		List <WebElement> dp=getDriver().findElements(By.xpath("//*[contains(@id,'parentaccountid.fieldControl-ix_premierein')]"));
		verifyElementisNotDisplayed(dp.size(), "Direct Parent");
		/*
		 * List <WebElement> nodp=getDriver().findElements(By.
		 * xpath("//*[contains(text(),'No records found. Create a new record.')]"));
		 * verifyElementisDisplayed(nodp.size(), "No Direct Parent ");
		 */
		return this;		
	}


	public SupplierFormPage searchDPinAdvanceLookup(String directParent) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")),"Street");
		click(getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_textInputBox_with_filter_new']")),"Direct Parent");
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//*[@aria-label='Search records for Direct Parent, Lookup field']"))).click().build().perform();
		Thread.sleep(4000);
		//Wave2 Update  -need to fix Advanced Lookup locator below	
		//a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-advlookup']"))).click().build().perform();
		a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-AdvancedLookup']"))).click().build().perform();
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//input[contains(@aria-labelledby,'advanced_lookup')]")),"Direct Parent");
		type(getDriver().findElement(By.xpath("//input[contains(@aria-labelledby,'advanced_lookup')]")),directParent,"Direct Parent");
		List<WebElement> dpmatch=getDriver().findElements(By.xpath("//span[contains(text(),\"We didn’t find a match\")]"));
		verifyElementisDisplayed(dpmatch.size(), "Direct Parent");
		return this;
	}

	public SupplierFormPage verifyDPValue(String verifyDPValue) throws InterruptedException {
		verifyExactText(((getDriver().findElement(By.xpath("//*[@data-id='parentaccountid.fieldControl-LookupResultsDropdown_parentaccountid_selected_tag_text']")))),verifyDPValue,"Direct Parent");
		return this;
	}

	public SupplierFormPage selectDPParentRelationDate(String selectDPRelationDate) throws InterruptedException {
		type(((getDriver().findElement(By.xpath("//input[contains(@id,'DateControlPrefix')][@aria-label='Date of Direct Parent Relation Date']")))),selectDPRelationDate,"Direct Parent Relation Date");
		return this;
	}


	public SupplierFormPage storeLocationType(String storeLocationType) throws InterruptedException {
		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_locationtype.fieldControl-option-set-select']")))),storeLocationType,"Store Location Type");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_locationtype.fieldControl-option-set-select']")),"Location Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+storeLocationType+"')]")),"Location Type");
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_locationtype.fieldControl-option-set-select']")),storeLocationType,"Location type"); 	
		return this;
	}

	public SupplierFormPage storeLocationTypeBlank() {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_locationtype.fieldControl-option-set-select']")),"Location Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'--Select--')]")),"Location Type as  Blank");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_locationtype.fieldControl-option-set-select']")),locationType,"Location Type");
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_locationtype.fieldControl-option-set-select']")),"--Select--","Location type");
		//selectDropDownUsingIndex(((getDriver().findElement(By.xpath("//*[@data-id='ix_locationtype.fieldControl-option-set-select']")))),0,"Store Location Type");
		return this;
	}

	public SupplierFormPage typeStreet1(String street1) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")),"Street");
		type((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']"))), street1,"Street");
		return this;
	}

	//navigate to TPRD via Street1
	public SupplierFormPage navigateToTPRD() {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")),"Street");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text']")),"Street 2");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']")),"City");

		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text']")),"Delivery Info");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")),"State");

		click(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']")),"County");

		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='address1_postalcode.fieldControl-text-box-text']")),"Zip Code");
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='ix_tollfreeno.fieldControl-text-box-text']")),"Toll Free");
		click(getDriver().findElement(By.xpath("//*[contains(text(),'Fax')]")), "Fax");
		return this;
	}

	public SupplierFormPage typeZipCode(String zipCode) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']")),"Zip Code");
		type((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))), zipCode,"Zip Code");
		return this;

	}

	public SupplierFormPage updateStreet1(String street1) {
		// update random numbers to change address
		Random rand = new Random();
		int upperbound = 99999;
		int int_random = rand.nextInt(upperbound);
		type((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']"))), street1+int_random,"Street1");
		return this;
	}

	public SupplierFormPage updateZipCode(String zipCode) {
		type((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))), zipCode,"Zip code");
		return this;
	}

	public SupplierFormPage recordStatusPublished(String recordStatusPublished) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='tablist-SUMMARY_TAB']")),"General Tab");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published status");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'Published')]")),"Account published");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published","Record Status");
		Thread.sleep(2000);
		return this;

	}


	public SupplierFormPage verifyRecordStatusPublished() throws InterruptedException {
		Thread.sleep(3000);
		//String rs=getTextValue(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Record Status");
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-container']")),"Published","Record Status"); 
		return this;
	}

	public SupplierFormPage recordStatusDraft() throws InterruptedException {
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Record status");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'Draft')]")),"Account published");
		//selectDropDownUsingIndex(((getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")))),0,"Record Status");
		Thread.sleep(3000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Draft","Record Status");
		Thread.sleep(2000);
		return this;
	}

	public SupplierFormPage contractEffectiveDateLock() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")),"Account Name");
		click(getDriver().findElement(By.xpath("//*[@title='Premier Start Date - Premier Member Start Date']")),"Premier Start Date");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contracteffectivedate-locked-icon']")),"Contract Effective Date Lock");
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@aria-label='Date of Contract Effective date']")),"Contract Effective Date");
		return this;
	}

	public SupplierFormPage contractEffectiveDateIsEditable(String contractEffectiveDate) throws InterruptedException {
		Thread.sleep(1500);
		click(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")),"Account Name");
		click(getDriver().findElement(By.xpath("//*[@title='Premier Start Date - Premier Member Start Date']")),"Premier Start Date");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contracteffectivedate.fieldControl-date-time-input']")),"Contract Effective Date");
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='ix_contracteffectivedate.fieldControl-date-time-input']")),"Contract Effective Date");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_contracteffectivedate.fieldControl-date-time-input']")),contractEffectiveDate,"Contract Effective Date");
		Thread.sleep(3000);	
		return this;
	}

	public SupplierFormPage contractEffectiveDateIsDateInputOnly(String invalidInputText, String errorMessage) throws InterruptedException {
		Thread.sleep(1500);
		click(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")),"Account Name");
		click(getDriver().findElement(By.xpath("//*[@title='Premier Start Date - Premier Member Start Date']")),"Premier Start Date");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_contracteffectivedate.fieldControl-date-time-input']")),"Contract Effective Date");
		navigateToPrimaryContact();
		verifyIsEnabled(getDriver().findElement(By.xpath("//*[@data-id='ix_contracteffectivedate.fieldControl-date-time-input']")),"Contract Effective Date");
		Thread.sleep(2000);
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_contracteffectivedate.fieldControl-date-time-input']")),invalidInputText,"Contract Effective Date");
		clickSave();
		verifyPartialText(getDriver().findElement(By.xpath("//div[contains(text(),'Date of Contract Effective date was invalid.')]")), errorMessage, "Error Message");
		Thread.sleep(2000);
		return this;
	}

	public SupplierFormPage openExistingContract() throws InterruptedException {
		Actions actions=new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//div[@col-id='ix_vendor']//a/ancestor::div[@class='ag-cell ag-cell-not-inline-editing ag-cell-normal-height ag-cell-value non-editable-cell']/preceding-sibling::div//input[@type='checkbox']"))).doubleClick().build().perform();
		return this;
	}

	public SupplierFormPage verifyVendorName(String vendorname) throws InterruptedException {
		String vendor= getDriver().findElement(By.xpath("	//div[@data-id='ix_vendor.fieldControl-LookupResultsDropdown_ix_vendor_selected_tag_text']")).getAttribute("title");
		assertTrue(vendor.contentEquals(vendorname));
		return this;
	}


	public SupplierFormPage recordStatusLock() throws InterruptedException {
		Thread.sleep(3000);		
		//verifyDisplayed(getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus-locked-icon']")),"Record Status Lock");
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@aria-label='Record Status'][@readonly]")),"Record Status");
		return this;
	}

	public SupplierFormPage recordChangeStatusLock() throws InterruptedException {
		Thread.sleep(3000);		
		//verifyDisplayed(getDriver().findElement(By.xpath("//*[@data-id='ix_recordchangestatus-locked-icon']")),"Record Change Status Lock");
		verifyReadonlyFields(getDriver().findElement(By.xpath("//input[@aria-label='Record Change Status'][@readonly]")),"Record Change Status");
		return this;
	}

	public SupplierFormPage existingRecordStatusDraftToPublished(String recordStatusPublished) throws InterruptedException {
		Thread.sleep(3000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Draft","Record Status");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Published status");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'Published')]")),"Record Status -Published");
		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")))),recordStatusPublished,"Record Status");
		Thread.sleep(3000);
		return this;
	}

	public SupplierFormPage verifyPremierEndDate(String premierEndDate) {
		verifyExactValue(getDriver().findElement(By.xpath("//*[@aria-label='Date of Premier End Date']")),premierEndDate,"Premier End Date");
		return this;
	}

	public SupplierFormPage verifyPremierEndDateIsNull() {
		verifyNullValue(getDriver().findElement(By.xpath("//*[@data-id='ix_premiermemberenddate.fieldControl-date-time-input']")),"Premier End Date");
		return this;
	}

	public SupplierFormPage clickAddNewMembershipProviderSave() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_membership|NoRelationship|Form|Mscrm.Form.ix_membership.SaveAndClose']")),"Save and Close");
		Thread.sleep(10000);
		click(getDriver().findElement(By.xpath("//*[@title='GENERAL']")),"GENERAL");

		Thread.sleep(5000);
		return this;
	}

	public SupplierFormPage clickAddNewMembershipProviderSaveButton() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='quickCreateSaveAndCloseBtn']")),"Save and Close");
		Thread.sleep(10000);

		Thread.sleep(5000);
		return this;
	}

	public SupplierFormPage selectMembershipProviderStartDate(String membershipProviderStartDate) {
		click(((getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")))),"Membership Provider Start Date");
		type(((getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")))),membershipProviderStartDate,"Membership Provider Start Date");
		return this;
	}

	public SupplierFormPage selectMembershipProviderType(String membershipProviderType) throws InterruptedException{
		Thread.sleep(3000);
		clearAllSuggestions();
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_membershiptype.fieldControl-option-set-select']")),"Membership Provider Type Drop DOwn");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+membershipProviderType+"')]")),"Membership Provider Option"); 
		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_membershiptype.fieldControl-option-set-select']")))),membershipProviderType,"Membership Provider Type");
		return this;
	}
	public SupplierFormPage typeMembershipProvider(String membershipProvider) throws InterruptedException {
		//click(getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-LookupResultsDropdown_ix_membershipprovider_textInputBox_with_filter_new']")),"Membership Provider");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_membershipprovider.fieldControl-LookupResultsDropdown_ix_membershipprovider_textInputBox_with_filter_new']")))),membershipProvider,"Membership Provider");
		//click(getDriver().findElement(By.xpath("//span[contains(text(),'"+membershipProvider+"')]")),"Membership Provider");
		click(getDriver().findElement(By.xpath("//span[@data-id='ix_membershipprovider.fieldControl-name0_0_0']//span[1]")),"Membership Provider");
		return this;	
	}

	public SupplierFormPage clickAddNewMembershipProvider() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membership|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_membership.AddNewStandard']")),"New Membership");
		return this;
	}

	public SupplierFormPage selectMembership() throws InterruptedException {
		Thread.sleep(3000);
		clearAllSuggestions();
		List<WebElement> membershiptab=getDriver().findElements(By.xpath("//li[@title='Membership' and @role='tab']"));
		if(membershiptab.size()>0) {
			click(getDriver().findElement(By.xpath("//li[@title='Membership' and @role='tab']")),"Membership");
		}else {
			click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
			click(getDriver().findElement(By.xpath("(//*[text()='Membership'])[2]")),"Membership");
		}
		Thread.sleep(3000);
		return this;
	}
	public SupplierFormPage doubleClickOnNationalMembership(String membershipStartDate) throws InterruptedException {	
		//verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='cell-0-3']")),"National","Membership Provider");
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='cell-0-3']//span")),"National","Membership Provider");
		//verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='cell-0-4']")),membershipStartDate,"Membership Start Date");
		//verifyExactText(getDriver().findElement(By.xpath("(//*[@col-id='ix_startdate']//label)[2]")),membershipStartDate,"Start Date");
		//Wave2 update Start Date Locator Update		
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='cell-0-4']//span")),membershipStartDate,"Membership Start Date");
		//verifyExactAttribute(getDriver().findElement(By.xpath("//*[@col-id='ix_startdate']//label[contains(@class,'ms-Label labelRootStyles') and @aria-label]")), "aria-label",membershipStartDate,"Membership Start Date");
		Actions a = new Actions(getDriver());
		// a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='cell-0-2']"))).doubleClick().build().perform();
		a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='cell-0-3']//span"))).doubleClick().build().perform();	    
		Thread.sleep(3000);
		return this;
	}

	public SupplierFormPage verifyNationalMembership(String membershipStartDate) throws InterruptedException {	
		//verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='cell-0-3']")),"National","Membership Provider");
		verifyExactText(getDriver().findElement(By.xpath("//*[@col-id='ix_membershipprovider']//span")),"National","Membership Provider");
		//verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='cell-0-4']")),membershipStartDate,"Membership Start Date");
		//Wave2 update Start Date Locator Update	
		verifyExactText(getDriver().findElement(By.xpath("//*[@col-id='ix_startdate']//label[contains(@class,'ms-Label labelRootStyles') and @aria-label]")),membershipStartDate,"Membership Start Date");
		Thread.sleep(3000);
		return this;
	}

	//Double click on national membership which does not have end date
	public SupplierFormPage doubleClickOnNewNationalMembership() throws InterruptedException {	
		Thread.sleep(6000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='ix_enddate']//label"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 2; i <=rowList.size(); i++) {
			String label = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_enddate']//label)["+i+"]")).getText();
			System.out.println(label);					
			if (label.equals("")) {
				System.out.println("Choosing the Premier Membership with End Date value as Blank");
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_enddate']//label)["+i+"]")), "End Date");
				Thread.sleep(3000);
				break;				
			}
		}		
		return this;
	}

	public SupplierFormPage selectMembershipEndReason(String EndReason) throws InterruptedException {
		//Wave2 update
		//click(getDriver().findElement(By.xpath("//*[@data-id='ix_endreason.fieldControl-option-set-select-container']")),"End Reason");
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_endreason.fieldControl-option-set-select']")),"End Reason");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+EndReason+"')]")),"End Reason");
		//selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_endreason.fieldControl-option-set-select']")))),EndReason,"End Reason");
		return this; 	
	}
	public SupplierFormPage clickMembershipSaveAndClose() throws InterruptedException {
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membership|NoRelationship|Form|Mscrm.Form.ix_membership.SaveAndClose']")),"Save and Close");
		Thread.sleep(5000);
		click(getDriver().findElement(By.xpath("//*[@title='GENERAL']")),"GENERAL");
		Thread.sleep(5000);
		return this;
	}
	//Verify the Membership end reason
	public SupplierFormPage verifyEndreason(String EndReason) throws InterruptedException {
		//Wave2 update
		//click(getDriver().findElement(By.xpath("//*[@data-id='ix_endreason.fieldControl-option-set-select-container']")),"End Reason");
		String automaticreason=getAttribute(getDriver().findElement(By.xpath("//input[@readonly][@aria-label='End Reason']")), "value", EndReason);
		Assert.assertTrue(automaticreason.contentEquals(EndReason));
		return this; 	
	}

	public SupplierFormPage clickMembershipSave() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_membership|NoRelationship|Form|Mscrm.Form.ix_membership.Save']")),"Save");
		Thread.sleep(8000);
		return this;
	}


	//End reason can not be blank error validation
	public SupplierFormPage verifyEndReasonCanNotBeBlankError(String expectedAccountStatusErrorText) {
		//	verifyExactText((getDriver().findElement(By.xpath("//*[@id='subtitle']"))), expectedAccountStatusErrorText,"End Reason can not be blank Error");
		//Wave2 Update
		verifyPartialText((getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']"))), expectedAccountStatusErrorText,"End Reason can not be blank Error");
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"Ok");

		return this;
	}

	//Date validation error in premier memvalidationError
	public SupplierFormPage verifyDateValidationError(String expectedAccountStatusErrorText) {
		//Wave2 Update
		verifyPartialText((getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']"))), expectedAccountStatusErrorText,"Date Validation Error");
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"Ok");
		return this;
	}

	//Account can not be terminated error in premier memvalidationError
	public SupplierFormPage verifyAccountCanNotBeTerminatedError(String expectedAccountStatusErrorText) {
		//Wave2 Update
		verifyPartialText((getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']"))), expectedAccountStatusErrorText,"Account Can not be terminated Error");
		click(getDriver().findElement(By.xpath("//*[@data-id='errorOkButton']")),"Ok");
		return this;
	}

	// Existing Bug :  Not Defaulting  to Supplier .Hence Choose Manually.
	public SupplierFormPage selectAccountType() {
		click(getDriver().findElement(By.xpath("//*[@data-id='statecode.fieldControl-option-set-select']")),"Account Type");
		selectDropDownUsingValue((((getDriver().findElement(By.xpath("//*[@data-id='cancelButton']"))))),"Account Type");
		return this;
	}

	// Existing Bug :  Not Defaulting  to Supplier .Hence Choose Manually.
	public SupplierFormPage selectAccountType(String accounttype) {
		click(getDriver().findElement(By.xpath("//select[@aria-label='Account Type']")),"Account Type");
		selectDropDownUsingVisibleText((((getDriver().findElement(By.xpath("//select[@aria-label='Account Type']"))))),accounttype,"Account Type") ;
		return this;
	}

	//Verify Premier start date
	public SupplierFormPage verifyPremierStartDate(String premierStartDate) {
		verifyExactValue(getDriver().findElement(By.xpath("//*[@data-id='ix_premiermemberstartdate.fieldControl-date-time-input']")),premierStartDate,"Premier Start Date");
		return this;
	}



	//Account name 2
	public SupplierFormPage typeAccountName2(String AccountName2) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_hiscirostername.fieldControl-text-box-text']")),"AccountName2");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_hiscirostername.fieldControl-text-box-text']")))),AccountName2,"Account name2");
		return this;
	}

	public SupplierFormPage verifyCAMSFlag(String VerifyCAMSFlag) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_camsflag.fieldControl-checkbox-container']")),VerifyCAMSFlag,"CAMS Flag"); 
		return this;
	}



	//Change CAMS flag As Yes
	public SupplierFormPage changeCAMSFlagAsYes() {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_camsflag.fieldControl-checkbox-select']")),"Yes","CAMS Flag"); 
		return this;
	}

	//Change the CAMS flag as No
	public SupplierFormPage changeCAMSFlagAsNo() {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_camsflag.fieldControl-checkbox-select']")),"No","CAMS Flag"); 
		return this;
	}

	public SupplierFormPage selectOwnership(String Ownership) throws InterruptedException{
		selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_ownership.fieldControl-option-set-select']")))),Ownership,"Ownership");
		Thread.sleep(2000);

		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_ownership.fieldControl-option-set-container']")),Ownership,"Ownership"); 
		return this;
	}

	public SupplierFormPage typeStockSymbol(String StockSymbol) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_stocksymbol.fieldControl-text-box-text']")),"Stock Symbol");
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_stocksymbol.fieldControl-text-box-text']")),StockSymbol, "Stock Symbol");
		return this;
	}

	public SupplierFormPage typeExchange(String Exchange) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_exchange.fieldControl-text-box-text']")),Exchange);
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_exchange.fieldControl-text-box-text']")),Exchange, "Exchange");
		return this;
	}

	public SupplierFormPage typeOverrideName(String OverrideName) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_name.fieldControl-text-box-text']")),"OverrideName");
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_name.fieldControl-text-box-text']")),OverrideName, "OverrideName");
		return this;
	}

	public SupplierFormPage typeStreet2(String Street2) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text']")),"Street2");
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text']")),Street2, "Street2");
		return this;
	}

	public SupplierFormPage typeDeliveryInfo(String DeliveryInfo) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line3.fieldControl-text-box-text']")),"Delivery Info");
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_line3.fieldControl-text-box-text']")),DeliveryInfo, "Delivery Info");
		return this;
	}
	public SupplierFormPage typeState(String State) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")),"State");
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")),State, "State");
		return this;
	}


	public SupplierFormPage typeMainPhone(String MainPhone) {
		click(getDriver().findElement(By.xpath("//*[@data-id='telephone1.fieldControl-phone-text-input']")),"Teephone");
		type(getDriver().findElement(By.xpath("//*[@data-id='telephone1.fieldControl-phone-text-input']")),MainPhone, "Main Phone");
		return this;
	}

	public SupplierFormPage typeCity(String City) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']")),"City");
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']")),City,"City");
		return this;
	}

	public SupplierFormPage navigateToZipCode() {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text']")),"Street2");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']")),"City");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']")),"Country");
		return this;
	}

	public SupplierFormPage navigateToPrimaryContact() {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line2.fieldControl-text-box-text']")),"Street2");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_city.fieldControl-text-box-text']")),"City");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_line3.fieldControl-text-box-text']")),"Delivery Info");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_stateorprovince.fieldControl-text-box-text']")),"State");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']")),"Country");
		clickAndTab(getDriver().findElement(By.xpath("//input[@data-id='address1_postalcode.fieldControl-text-box-text']")),"Zip Code");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Primary Contact')]")),"Primary Contact Label");
		return this;
	}



	public SupplierFormPage typeCounty(String County) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']")),"Country");
		type(getDriver().findElement(By.xpath("//*[@data-id='address1_county.fieldControl-text-box-text']")),County,"County");
		return this;
	}

	public SupplierFormPage typeCountry(String Country) {
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"County");
		type((getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']"))),Country,"Country");
		return this;
	}

	public SupplierFormPage typeFax(String Fax) {
		click(getDriver().findElement(By.xpath("//*[@data-id='fax.fieldControl-text-box-text']")),"Fax");
		type(getDriver().findElement(By.xpath("//*[@data-id='fax.fieldControl-text-box-text']")),Fax, "Fax");
		return this;
	}
	public SupplierFormPage typeWebsite(String Website) {
		click(getDriver().findElement(By.xpath("//*[@data-id='websiteurl.fieldControl-url-text-input']")),"Website");
		type(((getDriver().findElement(By.xpath("//*[@data-id='websiteurl.fieldControl-url-text-input']")))),Website, "Website");
		return this;
	}
	public SupplierFormPage verifyReceiveDirectMail(String ReceiveDirectMail) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_receivedirectmail.fieldControl-checkbox-container']")),ReceiveDirectMail,"Receive Direct Mail"); 
		return this;
	}

	public SupplierFormPage verifyDoNotVerifyAddress(String DoNotVerifyAddress) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_donotverifyaddress.fieldControl-checkbox-container']")),DoNotVerifyAddress,"Do Not Verify Address"); 
		return this;
	}
	public SupplierFormPage verifyIsTopParent(String IsTopParent) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_istopparent.fieldControl-checkbox-container']")),IsTopParent,"Is Top Parent"); 
		return this;
	}
	public SupplierFormPage verifyTopParent(String TopParent) {
		verifyExactText((getDriver().findElement(By.xpath("//*[@data-id='ix_topparent.fieldControl-LookupResultsDropdown_ix_topparent_selected_tag']"))),TopParent,"Top Parent");
		return this;
	}

	public SupplierFormPage verifyDonNotverifydefault() throws IOException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-id='ix_externaladdressid']/parent::div//preceding-sibling::div/div[@data-id='ix_donotverifyaddress']")).size(), "Don not verify element");
		String defaultvale=getText(getDriver().findElement(By.xpath("//select[@aria-label='Do Not Verify Address']/option[@data-selected='true']")));
		assertTrue(defaultvale.contentEquals("No"), "Parent Entity code is not displayed");
		return this;
	}
	
	//Click Tab
		public SupplierFormPage clickTab(int number)  throws InterruptedException {
			for(int i=0;i<number;i++) {
				Actions a =new Actions(getDriver());
				a.sendKeys(Keys.TAB).build().perform();
				Thread.sleep(3000);
			}
			return this;
		}
	public SupplierFormPage navigateToDoNotVerify() throws InterruptedException {
		click(((getDriver().findElement(By.xpath("//*[@data-id='address1_line1.fieldControl-text-box-text']")))), "Street1");
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'City')]")))), "City");
		if(getDriver().findElements(By.xpath("//label[contains(text(),'County')]")).size()>0){
			click(((getDriver().findElement(By.xpath("//label[contains(text(),'County')]")))), "County");
		}
		click(((getDriver().findElement(By.xpath("//label[contains(text(),'State/Province')]")))), "State/Province");
		click((getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']"))),"Zip Code");
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_country.fieldControl-text-box-text']")),"Country");	
		
		if(getDriver().findElements(By.xpath("//input[@aria-label='Other Phone']")).size()>0){
			click(((getDriver().findElement(By.xpath("//input[@aria-label='Other Phone']")))), "County");
			clickTab(5);
		}else {
			click((getDriver().findElement(By.xpath("//input[@aria-label='Phone']")))," Phone");	
			clickTab(5);
		}
		
		
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Receive Direct Mail')]"))),"Direct Mail");
		
		if(getDriver().findElements(By.xpath("//label[contains(text(),'Siebel Address ID')]")).size()>0){
			click(((getDriver().findElement(By.xpath("//label[contains(text(),'Siebel Address ID')]")))), "Siebel");
		}
		
		if(getDriver().findElements(By.xpath("//label[contains(text(),'FSRPT Flag')]")).size()>0){
			click(((getDriver().findElement(By.xpath("//label[contains(text(),'FSRPT Flag')]")))), "FSRPT");
		}
		click((getDriver().findElement(By.xpath("//label[contains(text(),'Do Not Verify Address')]"))),"Do not Verify Address");


		return this;

	}

	public SupplierFormPage addMemberRecord(String memberRecord) throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_memberrecordid.fieldControl-LookupResultsDropdown_ix_memberrecordid_textInputBox_with_filter_new']")),"Member Record");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_memberrecordid.fieldControl-LookupResultsDropdown_ix_memberrecordid_textInputBox_with_filter_new']")))),memberRecord,"Member Record");
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+memberRecord+"')]")),"Business Classification");
		return this;	
	}

	public SupplierFormPage verifyHIBCC(String VerifyHIBCC) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_hibccsubsc.fieldControl-checkbox-container']")),VerifyHIBCC,"HIBCC Subsec"); 
		return this;
	}

	public SupplierFormPage verifyNoNewProducts(String VerifyNoNewProducts) {
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_nonewproducts.fieldControl-checkbox-container']")),VerifyNoNewProducts,"No New Products"); 
		return this;
	}

	public SupplierFormPage typeMembershipEndDate(String membershipEndDate) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//input[@aria-label='Date of End Date'][contains(@id,'DateControlPrefix')]")),"End Date");
		type(((getDriver().findElement(By.xpath("//input[@aria-label='Date of End Date'][contains(@id,'DateControlPrefix')]")))),membershipEndDate,"End Date");
		return this;
	}

	//Add Minority Owned Diversity Type
	public SupplierFormPage addMinorityOwndDiversityType(String diversityType,String certifyingAgency, String diversityStartDate,String subClassification) throws InterruptedException {

		Thread.sleep(3000);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_diversitytype.fieldControl-option-set-select']")), diversityType ,"Diversity Type");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")))),diversityStartDate,"Start Date");
		Thread.sleep(5000);
		verifySubClassficationIsOptional();
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_subclassification.fieldControl-option-set-select']")), subClassification,"Sub Classification");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");					
		Thread.sleep(4000);									
		setReport().log(Status.PASS, "Diversity Type- " + "   " + diversityType + "  " +  "- is added successfully" + " ",	screenshotCapture());
		return this;
	} 
	//Add Veteran Owned Diversity Type			
	public SupplierFormPage addVeteranOwndDiversityType(String diversityType1,String certifyingAgency, String diversityStartDate,String subClassification1) throws InterruptedException {
		Thread.sleep(3000);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_diversitytype.fieldControl-option-set-select']")), diversityType1,"Diversity Type");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")))),diversityStartDate,"Start Date");
		Thread.sleep(5000);
		verifySubClassficationIsOptional();
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_subclassification.fieldControl-option-set-select']")), subClassification1,"Sub Classification");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");
		Thread.sleep(3000);
		setReport().log(Status.PASS, "Diversity Type- " + "   " + diversityType1 + "  " +  "-  is added successfully" + " ",screenshotCapture());
		return this;	

	}

	//Sub-Classification Field is Optional
	public SupplierFormPage verifySubClassficationIsOptional() {
		List<WebElement> optionalSubClass= getDriver().findElements(By.xpath("//*[@data-id='ix_subclassification-required-icon']"));
		verifyElementisNotDisplayed(optionalSubClass.size()," ' * ' Required Asterisk next to Sub-Classification Field ");
		return this;
	}
	//Add LGBT Owned		
	public SupplierFormPage addLGBTOwndDiversityType(String certifyingAgency, String diversityStartDate) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_diversitytype.fieldControl-option-set-select']")), "LGBT Owned","LGBT Owned");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")))),diversityStartDate,"Start Date");
		Thread.sleep(5000);
		verifySubClassficationIsNotPresent();
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");
		Thread.sleep(3000);
		setReport().log(Status.PASS, "Diversity Type- " + "   " +  " LGBT Owned " +  "- is added successfully" + " ",	screenshotCapture());
		return this;					
	}

	//Add Women Owned Diversity Info

	public SupplierFormPage addWomenOwndDiversityType(String diversityType,String certifyingAgency, String diversityStartDate,String subClassification) throws InterruptedException {		

		click(getDriver().findElement(By.xpath("//button[@data-id='ix_diversitytype.fieldControl-option-set-select']")),"Women Owned");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String startDate= dateFormat.format(date);
		click(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")),"Start Date"); 
		click(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")),"Start Date");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")),startDate,"Start Date"); 
		Thread.sleep(5000);
		//Req change- Sub Classification field introduced for this diversity type
		verifySubClassficationIsOptional();
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_subclassification.fieldControl-option-set-select']")),"Sub Classification");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+subClassification+"')]")),"Sub Classification");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");
		Thread.sleep(3000);
		setReport().log(Status.PASS, "Diversity Type- " + "   " + "Women Owned" + "  " +  "-  is added successfully" + " ",	screenshotCapture());
		return this;	

	}

	//Verify Women Owned Diversity Type's Sub Classification options
	public SupplierFormPage verifyWomenOwndSubClassificationOptions(String diversityType) throws InterruptedException {
		clearAllSuggestions();
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_diversitytype.fieldControl-option-set-select']")),"Diversity Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+diversityType+"')]")),"Diversity Type");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")),diversityType,"Diversity Type");
		//Select subClass= new  Select(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")));		
		// Create Expected Array List
		List<String> expectedWomOwnSubClass = Arrays.asList("--Select--","Asian-Indian","Asian-Pacific","Black","Hispanic","Native American","White Non-Hispanic");		
		//Create Actual blank Array List
		List<String> actualWomOwnSubClass=new ArrayList<String>();	
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")),"Diversity Type Sub Classification");
		getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")).sendKeys(Keys.ENTER);
		for (int i = 1; i <=7;i++ ) {
			String title = getDriver().findElement(By.xpath("//button[@data-id='ix_subclassification.fieldControl-option-set-select']")).getText();
			actualWomOwnSubClass.add(title);			
			System.out.println("The Actual Diversity Type is : "  + " " +title);
			enterArrowDownAndEnter(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")),"Diversity Type :" + title);
			if(expectedWomOwnSubClass.containsAll(actualWomOwnSubClass))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "Diversity Type- " + "   " + title + "  " +  "-  Option is available to choose from the list" + " "+ expectedWomOwnSubClass,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "Diversity Type - "+   "   " + title + "  " + "- Option is not available in the list"  + " "+ expectedWomOwnSubClass ,	screenshotCapture());
				Driver.failCount++;
			} 
			Thread.sleep(3000);

		if (title.equals("White Non-Hispanic")) {
				Thread.sleep(1000);		
				break;	
			}
		}
				return this;

	}

	//Add Service Disabled Veteran Diversity Info

	public SupplierFormPage addSerDisVetDiversityType(String diversityType,String certifyingAgency,String subClassification) throws InterruptedException {		
		Thread.sleep(2500);
		clearAllSuggestions();
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_diversitytype.fieldControl-option-set-select']")),"Diversity Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+diversityType+"')]")),"Diversity Type");	
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		//type(((getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")))),diversityStartDate,"Start Date");
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String startDate= dateFormat.format(date);
		click(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")),"Start Date"); 
		click(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")),"Start Date");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")),startDate,"Start Date"); 
		Thread.sleep(5000);
		//Req change- Sub Classification field introduced for this diversity type
		verifySubClassficationIsOptional();
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_subclassification.fieldControl-option-set-select']")),"Sub Classification");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+subClassification+"')]")),"Diversity Type -Sub Classification");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close"); 
		Thread.sleep(3000);
		setReport().log(Status.PASS, "Diversity Type- " + "   " + "Service Disabled Veteran" + "  " +  "-  is added successfully" + " ",	screenshotCapture());
		return this;	

	}

	//Verify Service Disabled Veteran Diversity Type's Sub Classification options
	public SupplierFormPage verifySerDisVetSubClassificationOptions(String diversityType) throws InterruptedException {
		clearAllSuggestions();
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_diversitytype.fieldControl-option-set-select']")),"Diversity Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+diversityType+"')]")),"Diversity Type");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")),diversityType,"Diversity Type");
		//Select subClass= new  Select(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")));		
		// Create Expected Array List
		List<String> expectedWomOwnSubClass = Arrays.asList("--Select--","Asian-Indian","Asian-Pacific","Black","Hispanic","Native American","White Non-Hispanic");		
		//Create Actual blank Array List
		List<String> actualWomOwnSubClass=new ArrayList<String>();	
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")),"Diversity Type Sub Classification");
		getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")).sendKeys(Keys.ENTER);
		for (int i = 1; i <=7;i++ ) {
			String title = getDriver().findElement(By.xpath("//button[@data-id='ix_subclassification.fieldControl-option-set-select']")).getText();
			actualWomOwnSubClass.add(title);			
			System.out.println("The Actual Diversity Type is : "  + " " +title);
			enterArrowDownAndEnter(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")),"Diversity Type :" + title);
			if(expectedWomOwnSubClass.containsAll(actualWomOwnSubClass))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "Diversity Type- " + "   " + title + "  " +  "-  Option is available to choose from the list" + " "+ expectedWomOwnSubClass,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "Diversity Type - "+   "   " + title + "  " + "- Option is not available in the list"  + " "+ expectedWomOwnSubClass ,	screenshotCapture());
				Driver.failCount++;
			} 
			Thread.sleep(3000);

		if (title.equals("White Non-Hispanic")) {
				Thread.sleep(1000);		
				break;	
			}
		}
		return this;

	}

	//Add Disabled Business Enterprise Diversity Info

	public SupplierFormPage addDisBusEntDiversityType(String diversityType,String certifyingAgency, String diversityStartDate,String subClassification) throws InterruptedException {		
		Thread.sleep(2500);
		clearAllSuggestions();
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_diversitytype.fieldControl-option-set-select']")),"Diversity Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+diversityType+"')]")),"Diversity Type");	
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_diversitytype.fieldControl-option-set-select']")), diversityType,"Divesrty Type");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		//type(((getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")))),diversityStartDate,"Start Date");
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String startDate= dateFormat.format(date);
		click(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")),"Start Date"); 
		click(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")),"Start Date");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")),startDate,"Start Date"); 
		Thread.sleep(5000);
		//Req change- Sub Classification field introduced for this diversity type
		verifySubClassficationIsOptional();		
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_subclassification.fieldControl-option-set-select']")),"Sub Classification");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+subClassification+"')]")),"Diversity Type -Sub Classification");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_subclassification.fieldControl-option-set-select']")), subClassification,"Sub Classification");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");
		Thread.sleep(3000);
		Thread.sleep(10000);
		String saveStatus=getTextValue(getDriver().findElement(By.xpath("//h1[contains(@id,'formHeaderTitle')]/span")),"Save status");
		System.out.println(saveStatus);
		assertFalse(saveStatus.contains("Unsaved"),"Details are not saved");
		setReport().log(Status.PASS, "Diversity Type- " + "   " + "Disabled Business Enterprise" + "  " +  "-  is added successfully" + " ",	screenshotCapture());
		return this;	

	}

	//Verify Disabled Business Enterprise Diversity Type's Sub Classification options
	public SupplierFormPage verifyDisBusEntSubClassificationOptions(String diversityType) throws InterruptedException {
		clearAllSuggestions();
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_diversitytype.fieldControl-option-set-select']")),"Diversity Type");
		click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+diversityType+"')]")),"Diversity Type");
		//selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")),diversityType,"Diversity Type");
		//Select subClass= new  Select(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")));		
		// Create Expected Array List
		List<String> expectedWomOwnSubClass = Arrays.asList("--Select--","Asian-Indian","Asian-Pacific","Black","Hispanic","Native American","White Non-Hispanic");		
		//Create Actual blank Array List
		List<String> actualWomOwnSubClass=new ArrayList<String>();	
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")),"Diversity Type Sub Classification");
		getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")).sendKeys(Keys.ENTER);
		for (int i = 1; i <=7;i++ ) {
			String title = getDriver().findElement(By.xpath("//button[@data-id='ix_subclassification.fieldControl-option-set-select']")).getText();
			actualWomOwnSubClass.add(title);			
			System.out.println("The Actual Diversity Type is : "  + " " +title);
			enterArrowDownAndEnter(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")),"Diversity Type :" + title);
			if(expectedWomOwnSubClass.containsAll(actualWomOwnSubClass))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "Diversity Type- " + "   " + title + "  " +  "-  Option is available to choose from the list" + " "+ expectedWomOwnSubClass,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "Diversity Type - "+   "   " + title + "  " + "- Option is not available in the list"  + " "+ expectedWomOwnSubClass ,	screenshotCapture());
				Driver.failCount++;
			} 
			Thread.sleep(3000);

		if (title.equals("White Non-Hispanic")) {
				Thread.sleep(1000);		
				break;	
			}
		}
		return this;

	}


	//Sub-Classification Field is not present
	public SupplierFormPage verifySubClassficationIsNotPresent() {
		List<WebElement> subClass= getDriver().findElements(By.xpath("//label[contains(text(),'Sub-Classification')]"));
		verifyElementisNotDisplayed(subClass.size()," ' Sub-Classification  ' Field ");
		return this;
	}
	//Verify Diversity Type's Veteran Owned Sub Classification options
	public SupplierFormPage verifyVeteranOwndSubClassificationOptions(String diversityType1) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")),diversityType1,"Diversity Type");
		Select subClass= new  Select(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")));	

		// Create Expected Array List
		List<String> expectedVetOwnSubClass = Arrays.asList("---","Service Disabled");		
		//Create Actual blank Array List
		List<String> actualVetOwnSubClass=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =subClass.getOptions();	
		System.out.println("The Total Number of Veteran Owned Diversity Type's Sub Classifications available : "  + " " + mylist.size());
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualVetOwnSubClass.add(data);			
			//System.out.println("The Actual Diversity Type is : "  + " " +data);						
			System.out.println("The Actual Veteran Owned Diversity Type's Sub Classifications available : "  + " " +data);	
			Thread.sleep(3000);
			if(expectedVetOwnSubClass.containsAll(actualVetOwnSubClass))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "Sub Classification - " + "   " + data + "  " +  "-  Option is available to choose from the list" + " "+ expectedVetOwnSubClass,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "Sub Classification - "+   "   " + data + "  " + "- Option is not available in the list"  + " "+ expectedVetOwnSubClass ,	screenshotCapture());
				Driver.failCount++;
			}


		}
		return this;

	}



	//Add Minority Owned subClassification
	public SupplierFormPage selectMinOwnSubClassification(String certifyingAgency,String subClassification, String diversityStartDate) throws InterruptedException {
		Thread.sleep(3000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")))),diversityStartDate,"Start Date");
		Thread.sleep(5000);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_subclassification.fieldControl-option-set-select']")), subClassification,"Sub Classification");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");	
		return this;

	}

	//Add Veteran Owned subClassification
	public SupplierFormPage selectVetOwnSubClassification(String certifyingAgency,String subClassification1, String diversityStartDate) throws InterruptedException {
		Thread.sleep(3000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")))),diversityStartDate,"Start Date");
		Thread.sleep(5000);
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_subclassification.fieldControl-option-set-select']")), subClassification1,"Sub Classification");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");	
		return this;

	}

	//Add Small Business Diversity Info

	public SupplierFormPage addSmallBusinesDiversityType(String certifyingAgency, String diversityStartDate) throws InterruptedException {		

		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_diversitytype.fieldControl-option-set-select']")), "Small Business","Small Business");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")))),diversityStartDate,"Start Date");
		Thread.sleep(5000);
		verifySubClassficationIsNotPresent();
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");
		Thread.sleep(3000);
		setReport().log(Status.PASS, "Diversity Type- " + "   " + "Small Business" + "  " +  "-  is added successfully" + " ", 	screenshotCapture());
		return this;					
	}

	//Deactivate All Diversity Info
	public SupplierFormPage deactivateAllDiversityInfo() throws InterruptedException {
		//		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(60));
		//		wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//i[@data-icon-name='CheckMark']")));
		Thread.sleep(2000);
		Actions a = new Actions(getDriver());
		a.moveToElement(getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']"))).click().build().perform();
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[2]")), "Deactivate Button"); 
		Thread.sleep(4000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")), "Confirm Deactivate Button");
		Thread.sleep(2000);

		//Wave2 Update - 08/21/2023 -Reverting back as the Select All button is available back 
		//Locator Change Select All button removed- Adding list to deactivate line items one by one
		//		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		//		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='ix_diversitytype']//label"));
		//		System.out.println("# of Rows Including Header:"+ rowList.size());
		//		for (int i = 2; i <=rowList.size(); i++) {
		//			click(getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']")), "Check Mark");
		//			Thread.sleep(5000);
		//			click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[2]")), "Deactivate Button"); 
		//			click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[3]")), "Deactivate");
		//			Thread.sleep(6000);
		//		}		

		return this;					
	}



	// Confirm deactivate button
	public SupplierFormPage clickConfirmDiversityDeactivation() throws InterruptedException {
		click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[3]")), "Deactivate");
		Thread.sleep(10000);
		return this;
	}


	//ACCOUNT NUMBERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//Select related and account numbers
	public SupplierFormPage selectAccountNumbers() throws InterruptedException {	
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("(//*[contains(text(),'Account Numbers')])[2]")),"Account Numbers");
		Thread.sleep(2000);

		List<WebElement> clearsugestion=getDriver().findElements(By.xpath("//button[@aria-label='Clear all suggestions']/span"));
		if(clearsugestion.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Clear all suggestions']/span")),"Clear sugesstion");
		}

		return this;
	}
	
	

	//Select related and account numbers
	public SupplierFormPage selectAccountNumbersTab() throws InterruptedException {	
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//li[@data-id='tablist-nav_ix_account_ix_accountnumber_Account']")),"Account Numbers");
		Thread.sleep(2000);
		return this;
	}




	//Click on related and select account numbers
	public SupplierFormPage clickAddNewAccountNumberInAccountNumbers() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumber|NoRelationship|SubGridAssociated|Mscrm.SubGrid.ix_accountnumber.AddNewStandard']")),"Add");
		try
		{
			List<WebElement> confirmBtn= getDriver().findElements(By.xpath("//*[@data-id='confirmButton']"));
			if(confirmBtn.size()>0) {
				click(getDriver().findElement(By.xpath("//*[@data-id='confirmButton']")),"Save and continue");
			}
			else {

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		Thread.sleep(6000);
		return this;
	}

	//Select Account type as Federal Tax ID
	public SupplierFormPage chooseAccountNumberTypeFedTaxID(String accountNumType) {
		try {
			Thread.sleep(2000);
			click(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Account Number Type");
			click(getDriver().findElement(By.xpath("//div[contains(@id,'fluent-listbox')]/div/div[contains(text(),'"+accountNumType+"')]")),"Account Number Type");
			Thread.sleep(2000);
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Federal Tax ID","Account Numbers Type"); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	//To add existing Account Number
	public SupplierFormPage typeStaticAccountNumber(String number) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),number,"Number");
		return this;
	}

	//Choose Existing Account Number -Remitra
	public SupplierFormPage doubleClickExistingAccountNumberRemitra() throws InterruptedException   {
		//Wave1 2023 Update
		Thread.sleep(4000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 1; i <=rowList.size(); i++) {
			String title = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div)["+i+"]")).getText();
			System.out.println(title);					
			if (title.equals("Remitra")) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label/div)["+i+"]")), "Remitra");
				Thread.sleep(3000);
				break;								
			}
			else if (title.equals("---"))
			{

			}

		}		

		return this;					
	}

	//Type Remitra account number
	public SupplierFormPage typeAccountNumberRemitra() {
		int min=111111111;
		int max=999999999;
		//Random randomGenerator = new Random();
		int randomInt = (int)(Math.random() * (max - min + 1) + min);
		System.out.println(randomInt);
		String AccNumRemitra=String.valueOf(randomInt);
		randomString=AccNumRemitra;

		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),AccNumRemitra,"Remitra Account Number");
		try {
			DataInputProvider.setCellData(AccNumRemitra.toString(), Driver.iTestCaseRowNum, "Remitra",Driver.sCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	//Select Account number type in account numbers window
	public SupplierFormPage chooseAccountNumberTypeRemitra() {
		try {
			Thread.sleep(2000);
			selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Remitra","Account Number Type");
			Thread.sleep(2000);
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Remitra","Account Numbers Type"); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}
	//To add existing FedTaxID
	public SupplierFormPage typeStaticFedTaxID(String fedTaxID) {
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),fedTaxID,"Fed Tax ID Account Number");
		return this;
	}

	//To add existing FedTaxID
	public SupplierFormPage typeRandomFedTaxID() {

		Random rand = new Random();
		int lowerbound = 99999999;
		int upperbound = 999999999;
		String fedTaxID = rand.nextInt(lowerbound, upperbound)+"";
		System.out.println(fedTaxID);
		click(getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")),"Number");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_number.fieldControl-text-box-text']")))),fedTaxID,"Fed Tax ID Account Number");
		return this;

	}


	// update random numbers to change address


	//Enter Start Date as Today's Date in Account Numbers
	public SupplierFormPage typeStartDateInAccountNumbers() {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String startdate= dateFormat.format(date);
		click(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")),"Start Date"); 
		click(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")),"Start Date");
		type(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date'][contains(@id,'DateControlPrefix')]")),startdate,"Start Date"); 
		return this;
	}


	public SupplierFormPage clickSaveInAccountNumbersEntity() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_accountnumber|NoRelationship|Form|Mscrm.Form.ix_accountnumber.Save']")),"Save");
		Thread.sleep(10000);
		Thread.sleep(5000);
		return this;
	}

	//Verify Account Number Error Message
	public SupplierFormPage verifyAccountNumberErrorMessage(String errormessage) throws InterruptedException {
		Thread.sleep(7000);
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='errorDialog_subtitle']")),errormessage,"Account Number Error Message");
		Thread.sleep(2000);
		return this;	
	}

	//Verify Error message is not displayed
	public SupplierFormPage verifyErrorisNotDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> error=getDriver().findElements(By.xpath("//*[@data-id='errorDialog_subtitle']"));
		verifyElementisNotDisplayed(error.size(), "Error message");
		Thread.sleep(2000);
		return this;
	}

	//Verify Error message is displayed
	public SupplierFormPage verifyBannerErrorText(String errormsg) throws InterruptedException {
		Thread.sleep(3000);
		verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='warningNotification']")),errormsg,"Error Banner on Top");
		System.out.println(errormsg);
		Thread.sleep(2000);
		return this;
	}

	//Verify Branding Approvals fields not Editable
	public SupplierFormPage verifyBrandingApprovalFieldsNotEditable(String accproperty) throws InterruptedException {
		Thread.sleep(3000);
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_startdateofuse.fieldControl-date-time-input']")),"Start Date of Use");
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_approvaldate.fieldControl-date-time-input']")),"Approval Date");
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_approvaltype.fieldControl-option-set-select']")),"Approval Type");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[contains(text(),'"+accproperty+"')]")).size(),"Account Field Readonly");		
		verifyReadonlyFields(getDriver().findElement(By.xpath("//*[@data-id='ix_name.fieldControl-text-box-text'][@readonly]")),"Approved By");
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_enddateofuse.fieldControl-date-time-input']")),"End Date of Use");
		Thread.sleep(2000);
		return this;
	}

	//Click OK on Account Number Error Message
	public SupplierFormPage clickOKOnAccountNumberErrorMessage() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='errorOkButton']")),"OK");
		Thread.sleep(5000);
		return this;	
	}

	//Verify Account Name in  Account number Entity
	public SupplierFormPage verifyAccountNameInAccountNumbers(String accountName2) {
		verifyExactTextWithTextContentAttribute((getDriver().findElement(By.xpath("//div[@data-id='ix_account.fieldControl-LookupResultsDropdown_ix_account_selected_tag_text']"))),accountName2,"Account Name in Account Numbers Entity");
		return this;
	}


	//Verify Name and Calculated Name in  Account number Entity
	public SupplierFormPage verifyNameAndCalculatedNameInAccountNumbers() throws InterruptedException {
		String account =(getDriver().findElement(By.xpath("//*[@data-id='ix_account.fieldControl-LookupResultsDropdown_ix_account_selected_tag_text']")).getAttribute("title"));
		System.out.println("Account field's value in Account Number Entity is  : " + account);

		String accNumType =(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")).getAttribute("title"));
		System.out.println("Account Number Type field's value in Account Number Entity is  : " + accNumType);

		String accNum =(getDriver().findElement(By.xpath("//input[@data-id='ix_number.fieldControl-text-box-text']")).getAttribute("value"));
		System.out.println("Account Number field's value in Account Number Entity is  : " + accNum);

		String name =account.concat(" - ").concat(accNumType).concat(" - ").concat(accNum);
		System.out.println(name);
		Thread.sleep(10000);
		verifyExactValue((getDriver().findElement(By.xpath("//input[@data-id='ix_calculatedname.fieldControl-text-box-text']"))),name,"Calculated Name in Account Numbers Entity");
		//verifyExactValue((getDriver().findElement(By.xpath("//input[@data-id='ix_accountnumbername.fieldControl-text-box-text']"))),name,"Name in Account Numbers Entity");
		return this;
	}


	public SupplierFormPage clickGoBackButton() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='navigateBackButtontab-id-0']")),"Go back");
		Thread.sleep(5000);

		return this;	
	}

	//Click on deactivate in Account Number Entity
	public SupplierFormPage clickDeactivateInAccountNumbers() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_accountnumber|NoRelationship|Form|Mscrm.Form.ix_accountnumber.Deactivate']")),"Deactivate");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Confirm Deactivate");
		Thread.sleep(5000);
		return this;	
	}

	//Enter End Date as Today's Date in Account Numbers
	public SupplierFormPage typeEndDateInAccountNumbers() {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String enddate= dateFormat.format(date);			
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_enddate.fieldControl-date-time-input']")),"End Date"); 
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_enddate.fieldControl-date-time-input']")),"End Date");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_enddate.fieldControl-date-time-input']")),enddate,"End Date"); 
		return this;
	}

	//Click on activate in Account Number Entity
	public SupplierFormPage clickActivateInAccountNumbers() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_accountnumber|NoRelationship|Form|Mscrm.Form.ix_accountnumber.Activate']")),"Deactivate");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Confirm Activate");
		Thread.sleep(5000);
		return this;	
	}

	//Click General Tab
	public SupplierFormPage clickGeneralTab() throws InterruptedException {
		List<WebElement> general=getDriver().findElements(By.xpath("//*[@title='GENERAL']"));
		if(general.size()>0) {
			click(getDriver().findElement(By.xpath("//*[@title='GENERAL']")),"GENERAL");	
		}else {
			click(getDriver().findElement(By.xpath("//*[@title='General']")),"General");
		}

		return this;
	}

	//Go to Accounts Page
	public  AccountsPage selectAccountsTabFromSupplierPage() throws InterruptedException {	
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//span[text()='Accounts']")),"Accounts");
		List<WebElement> element=getDriver().findElements(By.xpath("//button/span[contains(text(),'Discard changes')]"));
		if(element.size()>0) {
			click(getDriver().findElement(By.xpath("//button/span[contains(text(),'Discard changes')]")),"Discard Changes");
		}
		Thread.sleep(2000);
		return new AccountsPage();
	}	

	//Choose Existing Account Number -FedTax ID
	public SupplierFormPage doubleClickExistingAccountNumberFedTaxID() throws InterruptedException   {
		Thread.sleep(4000);
		WebElement table =getDriver().findElement(By.xpath("//*[@data-id='grid-container']"));
		List<WebElement> rowList = table.findElements(By.xpath("//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label"));
		System.out.println("# of Rows Including Header:"+ rowList.size());
		for (int i = 2; i <=rowList.size(); i++) {
			String title = getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label)["+i+"]")).getText();
			System.out.println(title);
			if (title.equals("Federal Tax ID")) {
				Thread.sleep(3000);
				doubleClick(getDriver().findElement(By.xpath("(//*[@data-id='grid-container']//div[@col-id='ix_accountnumbertype']//label)["+i+"]")), "Federal Tax ID");
				Thread.sleep(3000);
				break;				
			}
		}		

		return this;					
	}

	//Verify Contract Effective Date

	public SupplierFormPage verifyContractEffectiveDate() {

		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-id='ix_premiermemberenddate']/parent::div/following-sibling::div//div[@data-id='ix_contracteffectivedate']")).size(), "Contract Effective Date");	
		return this;
	}


	//Verify Add new button is not displayed
	public SupplierFormPage verifyAddNewButtonisNotDisplayed() {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//button[contains(text(),'New')]")).size(), "New Button");
		return this;
	}

	//Verify Member # is not displayed
	public SupplierFormPage verifyMemberisnotDisplayed() {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//label[contains(text(),'# of Member')]")).size(), "# of Member");
		return this;
	}

	//Change the view
	public SupplierFormPage changeTheContractView(String view) throws InterruptedException {
		click(getDriver().findElement(By.xpath("//div[@title='Select a view']")),"Select a View");
		click(getDriver().findElement(By.xpath("//span[contains(text(),'"+view+"')]")),"Select a view");
		Thread.sleep(4000);
		return this;
	}

	//verify the Column Header
	public SupplierFormPage verifyColumnHeader() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label//*[contains(text(),'Contract Number')]")).size(), "Contract Number");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label//*[contains(text(),'Description')]")).size(), "Description");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label//*[contains(text(),'Activation Method')]")).size(), "Activation Method");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label//*[contains(text(),'Start Date')]")).size(), "Start Date");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//label//*[contains(text(),'End Date')]")).size(), "End Date");
		Thread.sleep(4000);
		return this;
	}


	//verify Value in the contract
	public SupplierFormPage verifyValueinContract() throws InterruptedException {
		String contractNumber= getDriver().findElement(By.xpath("(//div[@col-id='ix_number' and @role='gridcell']//label/div)[2]")).getText();
		String contractDescripition= getDriver().findElement(By.xpath("(//div[@col-id='ix_contractdescription' and @role='gridcell']//label/div)[2]")).getText();
		String activationMethod= getDriver().findElement(By.xpath("(//div[@col-id='ix_vendoractivationmethod' and @role='gridcell']//label/div)[2]")).getText();
		String startDate= getDriver().findElement(By.xpath("(//div[@col-id='ix_contractstart' and @role='gridcell']//label/div)[2]")).getText();
		String EndDate= getDriver().findElement(By.xpath("(//div[@col-id='ix_contractend' and @role='gridcell']//label/div)[2]")).getText();

		Actions actions=new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("(//div[@col-id='ix_number' and @role='gridcell']//label/div)[2]"))).doubleClick().build().perform();
		assertTrue(getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Contract Number']")), "contractNumber").contentEquals(contractNumber));
		assertTrue(getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Description']")), "Description").contentEquals(contractDescripition));
		assertTrue(verifIsNoTNullWithTitleAttribute(getDriver().findElement(By.xpath("//select[@aria-label='Activation Method']")), "activationMethod").contentEquals(activationMethod));
		assertTrue(getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Date of Start Date']")), "Start of the Date").contentEquals(startDate));
		assertTrue(getTextValueAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Date of End Date']")), "Date of End Date").contentEquals(EndDate));
		Thread.sleep(4000);
		return this;
	}


	//verify end date are old
	public SupplierFormPage verifyOlderEndDate() throws InterruptedException, ParseException {
		List<WebElement> endDate=getDriver().findElements(By.xpath("//div[@col-id='ix_contractend' and @role='gridcell']//label/div"));
		List<String> dates=new ArrayList<String>();
		for(int i=1;i<endDate.size();i++) {
			dates.add(getDriver().findElement(By.xpath("(//div[@col-id='ix_contractend' and @role='gridcell']//label/div)["+i+"]")).getText());
		}
		String todayDate=TestUtils.todaysDate();
		for(String date:dates) {
			assertTrue(TestUtils.compareDate(date,todayDate )<0);
		}

		return this;
	}

	//verify end date are old
	public SupplierFormPage verifyNewerEndDate() throws InterruptedException, ParseException {
		List<WebElement> endDate=getDriver().findElements(By.xpath("//div[@col-id='ix_contractend' and @role='gridcell']//label/div"));
		List<String> dates=new ArrayList<String>();
		for(int i=1;i<endDate.size();i++) {
			dates.add(getDriver().findElement(By.xpath("(//div[@col-id='ix_contractend' and @role='gridcell']//label/div)["+i+"]")).getText());
		}
		String todayDate=TestUtils.todaysDate();
		for(String date:dates) {
			assertTrue(TestUtils.compareDate(date,todayDate )>=0);
		}

		return this;
	}


	//Navigate to System Tab in the contract page
	public SupplierFormPage navigateToContractSystemTab() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//li[@title='System']")),"System Tab");
		Thread.sleep(4000);
		return this;
	}



	//verify Status of the Contract is not deactive
	public SupplierFormPage verifyStatusOfContract() throws InterruptedException {
		changeTheContractView("Inactive Contracts");
		List<WebElement> endDate=getDriver().findElements(By.xpath("//div[@col-id='ix_contractend' and @role='gridcell']//label/div"));
		for(int i=1;i<endDate.size();i++) {
			Actions actions=new Actions(getDriver());
			actions.moveToElement(getDriver().findElement(By.xpath("(//div[@col-id='ix_number' and @role='gridcell']//label/div)["+i+"]"))).doubleClick().build().perform();
			navigateToContractSystemTab();
			assertTrue((getDriver().findElement(By.xpath("//select[@aria-label='Status']")).getAttribute("title").contentEquals("Active")));
			clickGoBackButton();
			changeTheContractView("Inactive Contracts");
			Thread.sleep(4000);
		}

		return this;
	}

	//Click Related Membership
	public SupplierFormPage verifyMembership(Boolean isPresent, String membership) throws InterruptedException   {
		if(isPresent) {

			verifyDisplayed(getDriver().findElement(By.xpath("//div[@col-id='ix_membershipprovider']//a//span[contains(text(),'"+membership+"')]")),"Membership");

		}else {

			verifyDisplayed(getDriver().findElement(By.xpath("//span[contains(text(),'No data available')]")),"Error Message");

		}
		return this;
	}

	//Verify no extra membership is created

	public SupplierFormPage verifyNoAddOnInMemberShip() throws InterruptedException   {
		assertTrue(getDriver().findElements(By.xpath("//div[@col-id='ix_membershipprovider']//a")).size()==1);
		return this;
	}

}





