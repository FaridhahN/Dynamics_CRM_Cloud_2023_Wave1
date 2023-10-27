package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import services.WebDriverServiceImpl;

public class DashboardPage extends WebDriverServiceImpl {

	//Click on Accounts in My work
	public AccountsPage selectAccountsTab() throws InterruptedException {

		List<WebElement> copilotclosbutton= getDriver().findElements(By.xpath("//button[@aria-label='Press to close copilot pane']"));

		if(copilotclosbutton.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-label='Press to close copilot pane']")),"co pilot Close button");
		}
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Accounts']")));

		click(getDriver().findElement(By.xpath("//span[text()='Accounts']")),"Accounts");
		//Thread.sleep(15000);
		return new AccountsPage();
	}

	public AccountsPage pageRefresh() throws InterruptedException {
		getDriver().navigate().refresh();
		return new AccountsPage();
	}

	public ContactsPage selectContacts() {	
		click(getDriver().findElement(By.xpath("//span[text()='Contacts']")),"Contacts");
		return new ContactsPage();
	}

	public BulkImportPage selectDataImports() throws InterruptedException {	
		click(getDriver().findElement(By.xpath("//span[text()='Data Imports']")),"Accounts");
		Thread.sleep(2000);
		return new BulkImportPage();
	}
}
