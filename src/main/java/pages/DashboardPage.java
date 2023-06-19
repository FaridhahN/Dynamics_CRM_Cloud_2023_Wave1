package pages;

import org.openqa.selenium.By;
import services.WebDriverServiceImpl;

public class DashboardPage extends WebDriverServiceImpl {

//Click on Accounts in My work
	public AccountsPage selectAccountsTab() throws InterruptedException {	
		click(getDriver().findElement(By.xpath("//span[text()='Accounts']")),"Accounts");
		Thread.sleep(35000);
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
