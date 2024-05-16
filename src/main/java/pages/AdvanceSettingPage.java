package pages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.AddHasCasting;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import dev.failsafe.internal.util.Assert;
import driver.Driver;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

public class AdvanceSettingPage extends WebDriverServiceImpl {

	public static String mainwindow;
	public static String childWindow;
	//select existing contact
	
	public AdvanceSettingPage navigatetoAdvanceSettings(String pageName) throws InterruptedException {
		Thread.sleep(5000);
		Thread.sleep(5000);
		mainwindow=getDriver().getWindowHandle();
		switchtoPAge(pageName);
		return this;

	}
	
	
public AdvanceSettingPage switchtoPAge(String pagename) {
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

public AdvanceSettingPage clickSettings() {
	
	click(getDriver().findElement(By.xpath("//span[@id='TabSettings']/a[@class='navTabButtonLink']")),"Settings Launcher");
	
	return this;
}

public AdvanceSettingPage clicksecurity() {
	
	click(getDriver().findElement(By.xpath("a[@id='nav_security']")),"Security");
	
	return this;
}

public SecurityPage navigateToUserOption() {
	clickSettings();
	click(getDriver().findElement(By.xpath("//a[@id='nav_security']")),"Security");
	getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@id='contentIFrame0']")));
	WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table//td/a[contains(text(),'Users')]")));
	

	return new SecurityPage();
}
public AdvanceFindPage clickAdvancefindButton() {
	click(getDriver().findElement(By.xpath("//span[@id='advancedFindImage']")),"Advance Find button");
	return new AdvanceFindPage();
}


//


}
