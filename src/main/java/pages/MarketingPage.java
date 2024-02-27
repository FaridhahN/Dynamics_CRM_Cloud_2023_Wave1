package pages;

import org.openqa.selenium.By;

import services.WebDriverServiceImpl;

public class MarketingPage extends WebDriverServiceImpl{
	
	//Change from workplace to Marketing
		public EmailList clickEmailList() throws InterruptedException {
			click(getDriver().findElement(By.xpath("//li[@id='sitemap-entity-ix_emaillist']")),"Email List");
			return new EmailList();
		}

}
