package pages;

import java.time.Duration;

 

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import services.WebDriverServiceImpl;

public class DashboardPage extends WebDriverServiceImpl {

	static String mainwindow;
	
	
	//Change from workplace to Marketing
	public MarketingPage changeToMarketing() throws InterruptedException {
		
		click(getDriver().findElement(By.xpath("//button[@id='areaSwitcherId']/span[contains(text(),'Workplace')]")),"Workplace");
		Thread.sleep(2000);
		click(getDriver().findElement(By.xpath("//span[contains(text(),'Marketing')]")),"Marketing");
		Thread.sleep(2000);
		return new MarketingPage();
		
	}
	
	//Click on Accounts in My work
	public AccountsPage selectAccountsTab() throws InterruptedException {

		List<WebElement> copilotclosbutton= getDriver().findElements(By.xpath("//button[@aria-label='Copilot menu' and @data-pa-landmark-active-element='true']"));

		List<WebElement> copilotclosebutton= getDriver().findElements(By.xpath("//button[@aria-label='Copilot' and @aria-expanded='true']"));


		if(copilotclosbutton.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Press to close copilot pane']")),"co pilot Close button");
		}

		if(copilotclosebutton.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Copilot' and @aria-expanded='true']")),"co pilot Close button");
		}
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Accounts']")));

		click(getDriver().findElement(By.xpath("//span[text()='Accounts']")),"Accounts");
		return new AccountsPage();
	}

	public AccountsPage selectContactsTab() throws InterruptedException {
		Thread.sleep(6000);
		List<WebElement> copilotclosbutton= getDriver().findElements(By.xpath("//button[@aria-label='Copilot menu' and @data-pa-landmark-active-element='true']"));

		if(copilotclosbutton.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Press to close copilot pane']")),"co pilot Close button");
		}
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Contacts']")));

		click(getDriver().findElement(By.xpath("//span[text()='Contacts']")),"Contacts");
		return new AccountsPage();
	}

	public ContractAttachmentPage selectContractAttachmentsTab() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> copilotclosebutton= getDriver().findElements(By.xpath("//button[@aria-label='Copilot menu' and @data-pa-landmark-active-element='true']"));
		List<WebElement> copilotclosebutton1= getDriver().findElements(By.xpath("//button[@aria-label='Copilot' and @aria-expanded='true']"));

		if(copilotclosebutton.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Press to close copilot pane']")),"co pilot Close button");
		}
		if(copilotclosebutton1.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Copilot' and @aria-expanded='true']")),"co pilot Close button");
		}
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Contract Attachments']")));

		click(getDriver().findElement(By.xpath("//span[text()='Contract Attachments']")),"Contract Attachments");
		return new ContractAttachmentPage();
	}



	//Navigate To Activities option in the Dashboard
	public SupplierFormPage navigateToActivitiesOption() throws InterruptedException {

		Thread.sleep(5000);
		List<WebElement> copilotclosbutton= getDriver().findElements(By.xpath("//button[@aria-label='Copilot menu' and @data-pa-landmark-active-element='true']"));

		if(copilotclosbutton.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Press to close copilot pane']")),"co pilot Close button");
		}
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='Activities']")));

		click(getDriver().findElement(By.xpath("//li[@aria-label='Activities']")),"Activities Tab");
		Thread.sleep(15000);
		return new SupplierFormPage();
	}
	public AccountsPage pageRefresh() throws InterruptedException {
		getDriver().navigate().refresh();

		Thread.sleep(15000);

		List<WebElement> copilotclosbutton= getDriver().findElements(By.xpath("//button[@aria-label='Press to close copilot pane']"));

		if(copilotclosbutton.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Press to close copilot pane']")),"co pilot Close button");
		}
		return new AccountsPage();
	}

	public ContactsPage selectContacts() throws InterruptedException {	
		Thread.sleep(6000);
		List<WebElement> copilotclosbutton= getDriver().findElements(By.xpath("//button[@aria-label='Copilot menu' and @data-pa-landmark-active-element='true']"));

		List<WebElement> copilotclosebutton= getDriver().findElements(By.xpath("//button[@aria-label='Copilot' and @aria-expanded='true']"));


		if(copilotclosbutton.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Press to close copilot pane']")),"co pilot Close button");
		}

		if(copilotclosebutton.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Copilot' and @aria-expanded='true']")),"co pilot Close button");
		}

		click(getDriver().findElement(By.xpath("//span[text()='Contacts']")),"Contacts");


		return new ContactsPage();
	}

	
	public AdvanceSettingPage selectAdvanceSetting() throws InterruptedException {	
		Thread.sleep(6000);
		List<WebElement> copilotclosbutton= getDriver().findElements(By.xpath("//button[@aria-label='Copilot menu' and @data-pa-landmark-active-element='true']"));

		List<WebElement> copilotclosebutton= getDriver().findElements(By.xpath("//button[@aria-label='Copilot' and @aria-expanded='true']"));


		if(copilotclosbutton.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Press to close copilot pane']")),"co pilot Close button");
		}

		if(copilotclosebutton.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Copilot' and @aria-expanded='true']")),"co pilot Close button");
		}

		click(getDriver().findElement(By.xpath("//button[@data-id='personalSettingsLauncher']")),"Settings Launcher");
		Thread.sleep(2000);
		Actions actions=new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//button[@data-id='SettingsMenu.AdvancedSettings']"))).click().build().perform();
	
		//click(getDriver().findElement(By.xpath("//button[@data-id='SettingsMenu.AdvancedSettings']")),"Advance Settings");

		
		return new AdvanceSettingPage();
	}

	
	public BulkImportPage selectDataImports() throws InterruptedException {	
		click(getDriver().findElement(By.xpath("//span[text()='Data Imports']")),"Accounts");
		Thread.sleep(2000);
		return new BulkImportPage();
	}
}
