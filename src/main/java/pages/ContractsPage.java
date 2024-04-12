package pages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
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
import utils.TestUtils;

public class ContractsPage extends WebDriverServiceImpl {

	public static String CRMNumber;

	// Click New on contacts page

	//Change the view
			public ContractsPage changeTheContractView(String view) throws InterruptedException {
				click(getDriver().findElement(By.xpath("//div[@title='Select a view']")),"Select a View");
				click(getDriver().findElement(By.xpath("//span[contains(text(),'"+view+"')]")),"Select a view");
				Thread.sleep(4000);
				return this;
			}
			
			
			//verify end date are old
			public ContractsPage verifyOlderEndDate() throws InterruptedException, ParseException {
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
}


