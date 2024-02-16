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
import org.openqa.selenium.Keys;
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

public class SecurityUserPage extends WebDriverServiceImpl {

	
public SecurityUserPage clickUsers() {
	
	click(getDriver().findElement(By.xpath("//table//td/a[contains(text(),'Users')]")),"User option");
	
	return this;
}


public SecurityUserPage selectUsers() {
	getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@id='contentIFrame0']")));
	
	getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@title='ViewArea']")));
	Actions actions=new Actions(getDriver());
	actions.moveToElement(getDriver().findElement(By.xpath("//*[@otypename='systemuser']/td[3]//a"))).doubleClick().build().perform();
	getDriver().switchTo().defaultContent();
	getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@id='contentIFrame0']")));
	
	WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(120));

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@name='DETAILS_TAB']")));

	return this;
}

public SecurityUserPage verifyId_HireFeild() {

	List<WebElement> detailstab=getDriver().findElements(By.xpath("//div[@name='DETAILS_TAB']/div[@aria-expanded='true']"));
	if(detailstab.size()>0) {
		Actions actions =new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(By.xpath("//img[@id='DETAILS_TAB_Expander']"))).click().build().perform();
		
	}
	List<WebElement> adminTab=getDriver().findElements(By.xpath("//div[@name='ADMINISTRATION_TAB']/div[@aria-expanded='true']"));
	
	if(adminTab.size()>0) {
		click(getDriver().findElement(By.xpath("//img[@id='ADMINISTRATION_TAB_Expander']")),"Details Tab");
	}
	navigateToID();
	
	List<WebElement> id=getDriver().findElements(By.xpath("//div[@id='employeeid']//div[contains(@class,'Locked')]"));
	assertTrue(id.size()>0);
	
	List<WebElement> hiredate=getDriver().findElements(By.xpath("//div[@id='ix_hiredate']//div[contains(@class,'Locked')]"));
	assertTrue(id.size()>0);
	return this;
}


public SecurityUserPage navigateToID() {
	Actions actions =new Actions(getDriver());
	actions.moveToElement(getDriver().findElement(By.xpath("//a[contains(text(),'POSTS')]"))).click().build().perform();
	actions.sendKeys(Keys.TAB);
	actions.sendKeys(Keys.TAB);
	actions.sendKeys(Keys.TAB);
	actions.moveToElement(getDriver().findElement(By.xpath("//div[@name='SUMMARY_TAB']//span[contains(text(),'Full Name')]"))).click().build().perform();
	actions.sendKeys(Keys.TAB);
	actions.sendKeys(Keys.TAB);
	
	actions.moveToElement(getDriver().findElement(By.xpath("//div[@name='SUMMARY_TAB']//span[contains(text(),'Nickname')]"))).click().build().perform();
	actions.sendKeys(Keys.TAB);
	
	
	actions.moveToElement(getDriver().findElement(By.xpath("//div[@name='SUMMARY_TAB']//span[contains(text(),'Primary Email')]"))).click().build().perform();
	actions.sendKeys(Keys.TAB);
	
	actions.moveToElement(getDriver().findElement(By.xpath("//div[@name='SUMMARY_TAB']//span[contains(text(),'Mobile Phone')]"))).click().build().perform();
	actions.sendKeys(Keys.TAB);
	
	actions.moveToElement(getDriver().findElement(By.xpath("//div[@name='SUMMARY_TAB']//span[contains(text(),'Employee Id')]"))).click().build().perform();
	actions.sendKeys(Keys.TAB);
	
		return this;
}
//









//


}
