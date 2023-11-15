package pages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Assert; 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import driver.Driver;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

public class SupplierFormPage extends WebDriverServiceImpl{

	public SupplierFormPage defaultAccountStatus(String defaultAccountStatus) throws InterruptedException {
		//	driver.navigate().refresh();
		Thread.sleep(5000);
		verifyExactText((getDriver().findElement(By.xpath("(//*[@data-id='form-header']/div[2]/div/div/div/div/div)[2]"))),defaultAccountStatus,"Account Status");
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
		
		//Enter the Task Details
		public SupplierFormPage EnterTaskDetails(String entitycode, String accountName, String subject, String duedate, String duration, String taskdetails) throws InterruptedException, IOException   {
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//input[@aria-label='Subject']")), entitycode+": "+accountName+":", "Subject");
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
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")))),diversityStartDate,"Start Date");
		Thread.sleep(5000);
		selectDropDownUsingValue(getDriver().findElement(By.xpath("//select[@data-id='ix_diversitytype.fieldControl-option-set-select']")), diversityType);
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");	
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
		//click(getDriver().findElement(By.xpath("(//span[contains(@class,'pa-cs pa-cr')]//span)[3]")), "Diversity Information");
		//click(getDriver().findElement(By.xpath("(//span[contains(@class,'pa-cn pa-cm')]//span)[3]")), "Diversity Information");
		//click(getDriver().findElement(By.xpath("(//span[contains(@class,'pa-fd pa-fc')]//span)[3]")), "Diversity Information");
		//Wave2 Update 
		click(getDriver().findElement(By.xpath("//*[@data-lp-id='SubGridStandard:ix_diversityinformation-OverflowButton']")), "Diversity Information");
		Thread.sleep(5000);
		Actions a = new Actions(getDriver());
		////span[text()='New Diversity Information']
		a.moveToElement(getDriver().findElement(By.xpath("//button[@data-id='ix_diversityinformation|NoRelationship|SubGridStandard|Mscrm.SubGrid.ix_diversityinformation.AddNewStandard']"))).click().build().perform();				
		Thread.sleep(5000);		
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='Account']")), "Account", "Account");
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='Diversity Type']")), "Diversity Type", "Diversity Type");
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='Certifying Agency']")), "Certifying Agency", "Certifying Agency");
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='Start Date']")), "Start Date", "Start Date");
		verifyExactText(getDriver().findElement(By.xpath("//label[text()='End Date']")), "End Date", "End Date");
		Thread.sleep(3000);
		Select diversity= new  Select(getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")));		
		// Create Expected Array List
		List<String> expectedDiversityType = Arrays.asList("---","Minority Owned","Women Owned","Veteran Owned","Small Business","LGBT Owned","Service Disabled Veteran","Disabled Business Enterprise");		
		//Create Actual blank Array List
		List<String> actualDiversityType=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =diversity.getOptions();		
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualDiversityType.add(data);			
			System.out.println("The Actual Diversity Type is : "  + " " +data);				
			Thread.sleep(3000);
			if(expectedDiversityType.containsAll(actualDiversityType))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "Diversity Type- " + "   " + data + "  " +  "-  Option is available to choose from the list" + " "+ expectedDiversityType,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "Diversity Type - "+   "   " + data + "  " + "- Option is not available in the list"  + " "+ expectedDiversityType ,	screenshotCapture());
				Driver.failCount++;
			}

		}

		return this;
	}

	//Verify Diversity Information Associated view
	public SupplierFormPage diversityInfoAssociatedView() throws InterruptedException {

		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//*[@title='Related']")),"Related");
		click(getDriver().findElement(By.xpath("(//span[text()='Diversity Information'])[2]")),"Related > Diversity Information");
		Thread.sleep(10000);			
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

	public SupplierFormPage typeAccountName(String accountName) {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String executiondate = dateFormat.format(date);
		click(getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")),"Account Name");
		type(((getDriver().findElement(By.xpath("//*[@data-id='name.fieldControl-text-box-text']")))),accountName+ "_" + executiondate,"Account Name");
		return this;
	}

	public SupplierFormPage defaultAccountType(String defaultAccountType) {
		verifyExactTextWithTitleAttribute((getDriver().findElement(By.xpath("//*[@data-id='customertypecode.fieldControl-option-set-select']"))),defaultAccountType,"Account Type");
		return this;
	}
	public SupplierFormPage pickPremierStartDate(String premierStartDate) throws InterruptedException {
		Thread.sleep(1000);
		type(getDriver().findElement(By.xpath("//*[@data-id='ix_premiermemberstartdate.fieldControl-date-time-input']")),premierStartDate,"Premier Start Date");
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
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_accountstatus.fieldControl-option-set-select']")),"Inactive","Account Status");
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
		verifyExactValue(getDriver().findElement(By.xpath("//*[@data-id='ix_premiermemberstartdate.fieldControl-date-time-input']")),date1,"Premier Start Date");
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
		click(getDriver().findElement(By.xpath("//select[@aria-label='Account Type']")),"Account Type");
		selectDropDownUsingVisibleText((((getDriver().findElement(By.xpath("//select[@aria-label='Account Type']"))))),accountType,"Account Type") ;Thread.sleep(2000);
		verifyTextDoesNotMatchTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='customertypecode.fieldControl-option-set-select']")),accountType,"Account type"); 
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
		try
		{
			if ( getDriver().findElement(By.xpath("//span[contains(text(),'Save and Continue')]")).isDisplayed())
			{
				click(getDriver().findElement(By.xpath("//span[contains(text(),'Save and Continue')]")),"Save and continue");
			}
			else {

			}
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
		List<WebElement> dpmatch=getDriver().findElements(By.xpath("//h3[contains(text(),'MEMBER ATTRIBUTES')]"));
		verifyElementisNotDisplayed(dpmatch.size(), "Member Attributes");
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

	public SupplierFormPage crmNumberIsDisplayed() throws InterruptedException {
		Thread.sleep(5000);

		click(getDriver().findElement(By.xpath("//h2[contains(text(),'DIVERSITY INFORMATION')]")),"DIVERSITY INFORMATION");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'HIBCC Subsc')]")),"HIBCC Subsc");

		click(getDriver().findElement(By.xpath("//label[contains(text(),'No New Products')]")),"No New Products");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Ownership')]")),"Ownership");
		click(getDriver().findElement(By.xpath("//label[contains(text(),'Exchange')]")),"Exchange");
		click(getDriver().findElement(By.xpath("//h3[contains(text(),'Timeline')]")),"Timeline");


		click(getDriver().findElement(By.xpath("//*[@title='NY INFORMATION']")),"My Information Label");
		String sCRMNumber = getAttribute(getDriver().findElement(By.xpath("//*[@data-id='accountnumber.fieldControl-text-box-text']")),"value","CRM Number");
		verifyDisplayed(getDriver().findElement(By.xpath("//input[@data-id='accountnumber.fieldControl-text-box-text']")), "CRM Lock symbol");
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
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_istopparent.fieldControl-checkbox-select']")), "Yes","Is Top Parent");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_istopparent.fieldControl-checkbox-container']")), "Yes","Is Top Parent");
		return this;
	}

	public SupplierFormPage clickIsTPNo() throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_istopparent.fieldControl-checkbox-select']")), "Yes","Is Top Parent");
		Thread.sleep(2000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_istopparent.fieldControl-checkbox-container']")), "Yes","Is Top Parent");
		return this;
	}

	public SupplierFormPage pickTPRDClear() throws InterruptedException {
		getDriver().findElement(By.xpath("//*[@data-id='ix_topparentrelationdate.fieldControl-date-time-input']")).click();
		getDriver().findElement(By.xpath("//*[@data-id='ix_topparentrelationdate.fieldControl-date-time-input']")).clear();
		return this;
	} 
	public SupplierFormPage pickTPRD(String selectTPRelationDate) throws InterruptedException {
		Thread.sleep(2000);
		//click(getDriver().findElement(By.xpath("//*[@data-id='ix_topparentrelationdate.fieldControl-date-time-input']")),"Top Parent Relation Date");
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_topparentrelationdate.fieldControl-date-time-input']")))),selectTPRelationDate,"Top Parent Relation Date");
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
		List <WebElement> nodp=getDriver().findElements(By.xpath("//*[contains(text(),'No records found. Create a new record.')]"));
		verifyElementisDisplayed(nodp.size(), "No Direct Parent ");
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
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_directparentrelationdate.fieldControl-date-time-input']")))),selectDPRelationDate,"Direct Parent Relation Date");
		return this;
	}


	public SupplierFormPage storeLocationType(String storeLocationType) throws InterruptedException {
		selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_locationtype.fieldControl-option-set-select']")))),storeLocationType,"Store Location Type");
		Thread.sleep(3000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_locationtype.fieldControl-option-set-select']")),storeLocationType,"Location type"); 	
		return this;
	}

	public SupplierFormPage storeLocationTypeBlank() {
		selectDropDownUsingIndex(((getDriver().findElement(By.xpath("//*[@data-id='ix_locationtype.fieldControl-option-set-select']")))),0,"Store Location Type");
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
		click(getDriver().findElement(By.xpath("//*[@data-id='address1_postalcode.fieldControl-text-box-text']")),"Zip Code");
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
		Thread.sleep(3000);
		selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")))),recordStatusPublished,"Record Status");
		Thread.sleep(3000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-container']")),recordStatusPublished,"Record Status"); 
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
		selectDropDownUsingIndex(((getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")))),0,"Record Status");
		Thread.sleep(3000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-container']")),"Draft","Record Status"); 
		Thread.sleep(2000);
		return this;
	}

	public SupplierFormPage recordStatusLock() throws InterruptedException {
		Thread.sleep(3000);		
		//verifyDisplayed(getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus-locked-icon']")),"Record Status Lock");
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")),"Record Status");
		return this;
	}

	public SupplierFormPage recordChangeStatusLock() throws InterruptedException {
		Thread.sleep(3000);		
		//verifyDisplayed(getDriver().findElement(By.xpath("//*[@data-id='ix_recordchangestatus-locked-icon']")),"Record Change Status Lock");
		verifyDisabledFields(getDriver().findElement(By.xpath("//*[@data-id='ix_recordchangestatus.fieldControl-option-set-select']")),"Record Change Status");
		return this;
	}

	public SupplierFormPage existingRecordStatusDraftToPublished(String recordStatusPublished) throws InterruptedException {
		Thread.sleep(3000);
		verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-container']")),"Draft","Record Status"); 
		Thread.sleep(2000);
		selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_recordstatus.fieldControl-option-set-select']")))),recordStatusPublished,"Record Status");
		Thread.sleep(3000);
		return this;
	}

	public SupplierFormPage verifyPremierEndDate(String premierEndDate) {
		verifyExactValue(getDriver().findElement(By.xpath("//*[@data-id='ix_premiermemberenddate.fieldControl-date-time-input']")),premierEndDate,"Premier End Date");
		return this;
	}

	public SupplierFormPage verifyPremierEndDateIsNull() {
		verifyNullValue(getDriver().findElement(By.xpath("//*[@data-id='ix_premiermemberenddate.fieldControl-date-time-input']")),"Premier End Date");
		return this;
	}

	public SupplierFormPage clickAddNewMembershipProviderSave() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//*[@data-id='quickCreateSaveAndCloseBtn']")),"Save and Close");
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
		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_startdate.fieldControl-date-time-input']")))),membershipProviderStartDate,"Membership Provider Start Date");
		return this;
	}

	public SupplierFormPage selectMembershipProviderType(String membershipProviderType) throws InterruptedException{
		Thread.sleep(3000);
		selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_membershiptype.fieldControl-option-set-select']")))),membershipProviderType,"Membership Provider Type");
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
		verifyExactText(getDriver().findElement(By.xpath("//*[@col-id='ix_membershipprovider']//span")),"National","Membership Provider");
		//verifyExactText(getDriver().findElement(By.xpath("//*[@data-id='cell-0-4']")),membershipStartDate,"Membership Start Date");
		//verifyExactText(getDriver().findElement(By.xpath("(//*[@col-id='ix_startdate']//label)[2]")),membershipStartDate,"Start Date");
		//Wave2 update Start Date Locator Update		
		verifyExactText(getDriver().findElement(By.xpath("//*[@col-id='ix_startdate']//label[contains(@class,'ms-Label labelRootStyles') and @aria-label]")),membershipStartDate,"Membership Start Date");
		//verifyExactAttribute(getDriver().findElement(By.xpath("//*[@col-id='ix_startdate']//label[contains(@class,'ms-Label labelRootStyles') and @aria-label]")), "aria-label",membershipStartDate,"Membership Start Date");
		Actions a = new Actions(getDriver());
		// a.moveToElement(getDriver().findElement(By.xpath("//*[@data-id='cell-0-2']"))).doubleClick().build().perform();
		a.moveToElement(getDriver().findElement(By.xpath("(//*[@col-id='ix_membershiptype']//label)[2]"))).doubleClick().build().perform();	    
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
		selectDropDownUsingVisibleText(((getDriver().findElement(By.xpath("//*[@data-id='ix_endreason.fieldControl-option-set-select']")))),EndReason,"End Reason");
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
		String automaticreason=getAttribute(getDriver().findElement(By.xpath("//select[contains(@id,'ix_endreason.fieldControl-option-set-select')]")), "title", EndReason);
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

		type(((getDriver().findElement(By.xpath("//*[@data-id='ix_enddate.fieldControl-date-time-input']")))),membershipEndDate,"End Date");
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

		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_diversitytype.fieldControl-option-set-select']")), "Women Owned","Women Owned");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")))),diversityStartDate,"Start Date");
		Thread.sleep(5000);
		//Req change- Sub Classification field introduced for this diversity type
		verifySubClassficationIsOptional();
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_subclassification.fieldControl-option-set-select']")), subClassification,"Sub Classification");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");
		Thread.sleep(3000);
		setReport().log(Status.PASS, "Diversity Type- " + "   " + "Women Owned" + "  " +  "-  is added successfully" + " ",	screenshotCapture());
		return this;	

	}

	//Verify Women Owned Diversity Type's Sub Classification options
	public SupplierFormPage verifyWomenOwndSubClassificationOptions(String diversityType) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")),diversityType,"Diversity Type");
		Select subClass= new  Select(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")));		
		// Create Expected Array List
		List<String> expectedWomOwnSubClass = Arrays.asList("---","Asian-Indian","Asian-Pacific","Black","Hispanic","Native American","White Non-Hispanic");		
		//Create Actual blank Array List
		List<String> actualWomOwnSubClass=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =subClass.getOptions();	
		System.out.println("The Number of Women Owned Diversity Type's Sub Classifications available : "  + " " + mylist.size());
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualWomOwnSubClass.add(data);								
			System.out.println("The Actual Women Owned Diversity Type's Sub Classifications available : "  + " " +data);				
			Thread.sleep(3000);
			if(expectedWomOwnSubClass.containsAll(actualWomOwnSubClass))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "Diversity Type- " + "   " + data + "  " +  "-  Option is available to choose from the list" + " "+ expectedWomOwnSubClass,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "Diversity Type - "+   "   " + data + "  " + "- Option is not available in the list"  + " "+ expectedWomOwnSubClass ,	screenshotCapture());
				Driver.failCount++;
			}


		}
		return this;

	}

	//Add Service Disabled Veteran Diversity Info

	public SupplierFormPage addSerDisVetDiversityType(String diversityType,String certifyingAgency, String diversityStartDate,String subClassification) throws InterruptedException {		

		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_diversitytype.fieldControl-option-set-select']")), diversityType,"Service Disabled Veteran");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")))),diversityStartDate,"Start Date");
		Thread.sleep(5000);
		//Req change- Sub Classification field introduced for this diversity type
		verifySubClassficationIsOptional();
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_subclassification.fieldControl-option-set-select']")), subClassification,"Sub Classification");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");
		Thread.sleep(3000);
		setReport().log(Status.PASS, "Diversity Type- " + "   " + "Service Disabled Veteran" + "  " +  "-  is added successfully" + " ",	screenshotCapture());
		return this;	

	}

	//Verify Service Disabled Veteran Diversity Type's Sub Classification options
	public SupplierFormPage verifySerDisVetSubClassificationOptions(String diversityType) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")),diversityType,"Diversity Type");
		Select subClass= new  Select(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")));		
		// Create Expected Array List
		List<String> expectedSerDisVetSubClass = Arrays.asList("---","Asian-Indian","Asian-Pacific","Black","Hispanic","Native American","White Non-Hispanic");		
		//Create Actual blank Array List
		List<String> actualSerDisVetSubClass=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =subClass.getOptions();	
		System.out.println("The Number of Minority Owned Diversity Type's Sub Classifications available : "  + " " + mylist.size());
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualSerDisVetSubClass.add(data);								
			System.out.println("The Actual Service Disabled Veteran Diversity Type's Sub Classifications available are : "  + " " +data);				
			Thread.sleep(3000);
			if(expectedSerDisVetSubClass.containsAll(actualSerDisVetSubClass))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "Diversity Type- " + "   " + data + "  " +  "-  Option is available to choose from the list" + " "+ expectedSerDisVetSubClass,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "Diversity Type - "+   "   " + data + "  " + "- Option is not available in the list"  + " "+ expectedSerDisVetSubClass ,	screenshotCapture());
				Driver.failCount++;
			}


		}
		return this;

	}

	//Add Disabled Business Enterprise Diversity Info

	public SupplierFormPage addDisBusEntDiversityType(String diversityType,String certifyingAgency, String diversityStartDate,String subClassification) throws InterruptedException {		

		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_diversitytype.fieldControl-option-set-select']")), diversityType,"Disbaled Business Enterprise");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_certifyingagency.fieldControl-text-box-text']")))),certifyingAgency,"Certifying Agency");
		Thread.sleep(2000);
		type(((getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")))),diversityStartDate,"Start Date");
		Thread.sleep(5000);
		//Req change- Sub Classification field introduced for this diversity type
		verifySubClassficationIsOptional();
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_subclassification.fieldControl-option-set-select']")), subClassification,"Sub Classification");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[text()='Save & Close']")),"Save & Close");
		Thread.sleep(3000);
		setReport().log(Status.PASS, "Diversity Type- " + "   " + "Disabled Business Enterprise" + "  " +  "-  is added successfully" + " ",	screenshotCapture());
		return this;	

	}

	//Verify Disabled Business Enterprise Diversity Type's Sub Classification options
	public SupplierFormPage verifyDisBusEntSubClassificationOptions(String diversityType) throws InterruptedException {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_diversitytype.fieldControl-option-set-select']")),diversityType,"Diversity Type");
		Select subClass= new  Select(getDriver().findElement(By.xpath("//*[@data-id='ix_subclassification.fieldControl-option-set-select']")));		
		// Create Expected Array List
		List<String> expectedDisBusEntSubClass = Arrays.asList("---","Asian-Indian","Asian-Pacific","Black","Hispanic","Native American","White Non-Hispanic");		
		//Create Actual blank Array List
		List<String> actualDisBusEntSubClass=new ArrayList<String>();	
		//Create temp Array List > add  actual options  from DOM for comparison
		List<WebElement> mylist =subClass.getOptions();	
		System.out.println("The Number of Minority Owned Diversity Type's Sub Classifications available : "  + " " + mylist.size());
		//loop through DOM and add dropdown values into mylist for comparison
		for (WebElement ele:mylist) {			
			String data =ele.getText();
			actualDisBusEntSubClass.add(data);								
			System.out.println("The Actual Disabled Business Enterprise Diversity Type's Sub Classifications available are : "  + " " + data);				
			Thread.sleep(3000);
			if(expectedDisBusEntSubClass.containsAll(actualDisBusEntSubClass))
			{		
				Thread.sleep(3000);
				setReport().log(Status.PASS, "Diversity Type- " + "   " + data + "  " +  "-  Option is available to choose from the list" + " "+ expectedDisBusEntSubClass,	screenshotCapture());

			} 
			else {
				setReport().log(Status.FAIL, "Diversity Type - "+   "   " + data + "  " + "- Option is not available in the list"  + " "+ expectedDisBusEntSubClass ,	screenshotCapture());
				Driver.failCount++;
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
		//click(getDriver().findElement(By.xpath("//div[@data-id='btnheaderselectcolumn']//div[1]")), " Select All check mark ");
		click(getDriver().findElement(By.xpath("//i[@data-icon-name='CheckMark']")), "Select All Radio Button");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[2]")), "Deactivate Button"); 
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("(//span[text()='Deactivate'])[3]")), "Deactivate");
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
	public SupplierFormPage chooseAccountNumberTypeFedTaxID() {
		try {
			Thread.sleep(2000);
			selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Federal Tax ID","Account Number Type");
			Thread.sleep(2000);
			verifyExactTextWithTitleAttribute(getDriver().findElement(By.xpath("//*[@data-id='ix_accountnumbertype.fieldControl-option-set-select']")),"Federal Tax ID","Account Numbers Type"); 
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

	//Enter Start Date as Today's Date in Account Numbers
	public SupplierFormPage typeStartDateInAccountNumbers() {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String startdate= dateFormat.format(date);
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")),"Start Date"); 
		click(getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")),"Start Date");
		type(getDriver().findElement(By.xpath("//input[@data-id='ix_startdate.fieldControl-date-time-input']")),startdate,"Start Date"); 
		return this;
	}


	public SupplierFormPage clickSaveInAccountNumbersEntity() throws InterruptedException {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_accountnumber|NoRelationship|Form|Mscrm.Form.ix_accountnumber.Save']")),"Save");
		Thread.sleep(10000);
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
		verifyExactValue((getDriver().findElement(By.xpath("//input[@data-id='ix_accountnumbername.fieldControl-text-box-text']"))),name,"Name in Account Numbers Entity");
		return this;
	}


	//Click on deactivate in Account Number Entity
	public SupplierFormPage clickDeactivateInAccountNumbers() throws InterruptedException {
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_accountnumber|NoRelationship|Form|Mscrm.Form.ix_accountnumber.Deactivate']")),"Deactivate");
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//button[@data-id='ok_id']")),"Confirm Deactivate");
		Thread.sleep(3000);
		return this;	
	}


	//Go to Accounts Page
	public  AccountsPage selectAccountsTabFromSupplierPage() throws InterruptedException {	
		Thread.sleep(3000);
		click(getDriver().findElement(By.xpath("//span[text()='Accounts']")),"Accounts");
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

}





