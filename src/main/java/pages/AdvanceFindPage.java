package pages;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import services.WebDriverServiceImpl;

public class AdvanceFindPage extends WebDriverServiceImpl {

	public static String mainwindow;
	public static String childWindow;


	public AdvanceFindPage navigatetoAdvanceFind(String pageName) throws InterruptedException {
		Thread.sleep(5000);
		Thread.sleep(5000);
		mainwindow=getDriver().getWindowHandle();
		switchtoPAge(pageName);
		getDriver().manage().window().maximize();
		return this;
	}

	public AdvanceFindPage switchtoPAge(String pagename) {
		Set<String> windows=getDriver().getWindowHandles();
		for(String win:windows) {
			getDriver().switchTo().window(win);
			String windowtitle=getDriver().getTitle();
			if(windowtitle.contains(pagename)) {
				break;
			}
		}
		return this;
	}

	//Click New button
	public AdvanceFindPage clickNewButton() {
		click(getDriver().findElement(By.xpath("//a[@aria-label='Create a new query to find records and activities.']")),"New butotn");
		return this;
	}

	//Select Look for
	public AdvanceFindPage selectLookfor(String lookFor) {
		switchToFrame(getDriver().findElement(By.id("contentIFrame0")));
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@id='slctPrimaryEntity']")), lookFor, "lookFor");
		switchToDefaultContent();
		return this;
	}

	//Click New results button
	public AdvanceFindPage clickResultIcon() {
		click(getDriver().findElement(By.xpath("//a[@aria-label='See the results of your query.']")),"Result butotn");
		return this;
	}

	//Click any first row in the result page

	public AdvanceFindPage clickfirstResult() {
		switchToFrame(getDriver().findElement(By.id("contentIFrame0")));
		switchToFrame(getDriver().findElement(By.id("resultFrame")));
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//tr[@class='ms-crm-List-Row']/td"))).doubleClick().build().perform();
		switchToDefaultContent();
		return this;
	}
	
	//open the COT
	public AdvanceFindPage openFirstCOT() {
		switchToFrame(getDriver().findElement(By.id("contentIFrame0")));
		switchToFrame(getDriver().findElement(By.id("resultFrame")));
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("(//tr[@otypename='ix_classoftradebusinessclassification']//td)[3]//a"))).click().build().perform();
		switchToDefaultContent();
		return this;
	}

	//Verify the fields are editable

	public AdvanceFindPage verifyFieldsareEditable() {
		verifyElementisNotDisplayed(getDriver().findElements(By.xpath("//span[@data-id='warningNotification' and contains(text(),\"Read-only: You don't have access to edit this record.\")]")).size(), "Read only button");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_classoftradebusinessclassification|NoRelationship|Form|Mscrm.Form.ix_classoftradebusinessclassification.Save']")).size(), "Save button");
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//button[@data-id='ix_classoftradebusinessclassification|NoRelationship|Form|Mscrm.Form.ix_classoftradebusinessclassification.SaveAndClose']")).size(), "Save and close button");
		return this;
	}
	
	public AdvanceFindPage selectadditionalInformation(String additionalfield) {
		switchToFrame(getDriver().findElement(By.id("contentIFrame0")));
		click(getDriver().findElement(By.xpath("//div[@id='advFindE_fieldListFLDLBL']")),"selectbutton");
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@id='advFindE_fieldListFLDCTL']")),additionalfield , "additionalfield");
		click(getDriver().findElement(By.xpath("//div[@title='Enter Value']")),"click enter value");
		click(getDriver().findElement(By.xpath("//td[@class='Lookup_RenderButton_td']")),"Search button");
		switchToDefaultContent();
		return this;
		
	}
	
	public AdvanceFindPage enterTheValue(String value) {
		switchToFrame(getDriver().findElement(By.id("InlineDialog_Iframe")));
		click(getDriver().findElement(By.xpath("//input[@id='crmGrid_findCriteria']")),"Find criteria textbox");
		type(getDriver().findElement(By.xpath("//input[@id='crmGrid_findCriteria']")),value,"Find criteria textbox");
		click(getDriver().findElement(By.xpath("//a[@id='crmGrid_findCriteriaButton']")),"Find criteria button");
		switchToDefaultContent();
		return this;
	}
	
	public AdvanceFindPage selectResultinLookupSearch(String value) {
		switchToFrame(getDriver().findElement(By.id("InlineDialog_Iframe")));
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath("//*[@title='"+value+"']/parent::nobr/parent::td/preceding-sibling::td"))).doubleClick().build().perform();
		switchToDefaultContent();
		return this;
	}
		
	
	public AdvanceFindPage clickAddButton() {
		switchToFrame(getDriver().findElement(By.id("InlineDialog_Iframe")));
	click(getDriver().findElement(By.id("butBegin")),"click Add button");
	switchToDefaultContent();
	return this;
	}
	
	public AdvanceFindPage clickResultButton() {
		click(getDriver().findElement(By.xpath("//a[@aria-label='See the results of your query.']")),"See the results");
		return this;
	}
	
	public AdvanceFindPage verifyResult(String value) {
		switchToFrame(getDriver().findElement(By.id("contentIFrame0")));
		switchToFrame(getDriver().findElement(By.id("resultFrame")));
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//span[@title='"+value+"']//span[contains(text(),'"+value+"')]")).size(), value);
		switchToDefaultContent();
		return this;
		
	}
	
	//Update PAS Vertical
	
	public AdvanceFindPage selectPAS(String value) {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@data-id='ix_pasvertical.fieldControl-option-set-select']")), value, "pasvertical");
		return this;
	}
	
	public AdvanceFindPage verifyPASVerticalFiledvalue(String value) {
		String PASVerticalValue=getDriver().findElement(By.xpath("//select[@data-id='ix_pasvertical.fieldControl-option-set-select']")).getAttribute("title");
		assertTrue(PASVerticalValue.contentEquals(value), "Value in the PAS vertical field is not Infusion Pharmacy");
		return this;
	}
	
	//Click save button
	public AdvanceFindPage clickSaveCOT() {
		click(getDriver().findElement(By.xpath("//button[@data-id='ix_classoftradedetail|NoRelationship|Form|Mscrm.Form.ix_classoftradedetail.Save']")),"click save button");
		return this;
	}
	
	// Account is in draft message should not be displayed
	public AdvanceFindPage VerifyErrorMessageNotDisplayed() throws InterruptedException {
		Thread.sleep(2000);

		List<WebElement> message=getDriver().findElements(By.xpath("//*[@data-id='errorDialog_subtitle']"));

		verifyElementisNotDisplayed(message.size(), "error message");
		return this;
	}

	//Verify PAS Vertical field is available
	public AdvanceFindPage verifyPASVerticalField() throws InterruptedException {
		verifyElementisDisplayed(getDriver().findElements(By.xpath("//div[@data-id='ix_pasvertical-FieldSectionItemContainer']")).size(), "PAS Vertical field");
		return this;
	}
	
	//Click Parent for sorting Descending
	
	public AdvanceFindPage clickParentField() throws InterruptedException {
		switchToFrame(getDriver().findElement(By.id("InlineDialog_Iframe")));
		click(getDriver().findElement(By.xpath("//th[@fieldname='ix_parent']")),"Sorting the column");
		switchToDefaultContent();
		
		return this;
	}
	
	
}




