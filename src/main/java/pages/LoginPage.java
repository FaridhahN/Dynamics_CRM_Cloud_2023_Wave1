package pages;

import java.awt.AWTException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PreAndPost;
import services.WebDriverServiceImpl;

public class LoginPage extends WebDriverServiceImpl{
	PreAndPost obj=new PreAndPost();

	
	
	//Verify Notification page
	public LoginPage verifyNotificationpage() throws InterruptedException {

		if(getDriver().findElements(By.xpath("//h1[contains(text(),'Notifications')]")).size()>0)
		{
			getDriver().findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
			refreshPageChooseSignInUser();

		}

		Thread.sleep(3000);
		return this;
	}

	public LoginPage refreshPageChooseSignInUser() {
		//Wave 2023 
		obj.getUrl();
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[contains(text(),'Use another account')]"))));
		getDriver().findElement(By.xpath("//div[contains(text(),'Use another account')]")).click();

		return this;
	}


	//Enter Email Id to Login
	public LoginPage typeEmail(String email) throws InterruptedException, AWTException {
		Thread.sleep(3000);
		System.out.println("Entering the User Id");
		List<WebElement> emailid=getDriver().findElements(By.xpath("//*[@name='loginfmt']"));
		if(emailid.size()==0) {
			List<WebElement> useanother=getDriver().findElements(By.xpath("//div[@id='otherTileText']"));
			if(useanother.size()>0) {
				click(getDriver().findElement(By.xpath("//div[@id='otherTileText']")),"User another Account");
			}
			verifyNotificationpage();
			Thread.sleep(2000);
		}
		type(getDriver().findElement(By.xpath("//*[@name='loginfmt']")),email,"Email");
		return this;
	}

	//Enter Password 
	public LoginPage typePassword(String password) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//*[@name='passwd']"))));
		type(getDriver().findElement(By.xpath("//*[@name='passwd']")),password,"Password");

		return this;
	}

	//Click on Next
	public LoginPage clickNext() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.id("idSIButton9"))));
		click(getDriver().findElement(By.id("idSIButton9")),"Next");
		Thread.sleep(3000);
		return new LoginPage();
	}	
	//Click on Signin
	public LoginPage clicSignin() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.id("idSIButton9"))));
		click(getDriver().findElement(By.id("idSIButton9")),"Signin");
		//Thread.sleep(2000);
		return new LoginPage();
	}	

	//Click on Yes in stay signed in window
	public DashboardPage clicYesInStaySignedin() throws InterruptedException {
		Thread.sleep(5000);
		
		/*
		 * Below code is for MFA setup 
		 */
		
		//if(getDriver().findElements(By.xpath("//div[contains(text(),'More information required')]")).size()>0)	{
			//click(getDriver().findElement(By.xpath("//input[@id='idSubmit_ProofUp_Redirect']")),"Next Button");
	//	}
	//	Thread.sleep(5000);
	//	if(getDriver().findElements(By.xpath("//h2[contains(text(),'Microsoft Authenticator')]")).size()>0) {
		//	click(getDriver().findElement(By.xpath("//a[contains(text(),'Skip setup')]")),"Skip Setup");
		//}
			//	Thread.sleep(5000);
		if(getDriver().findElements(By.id("idSIButton9")).size()>0){
			click(getDriver().findElement(By.id("idSIButton9")),"Yes in Stay Signed In");
		}
		Thread.sleep(7000);
		verifyOldLook();
		return new DashboardPage();
	}	

	public LoginPage verifyOldLook() throws InterruptedException{
		List<WebElement> newlook=getDriver().findElements(By.xpath("//button[@aria-checked='true']"));
		if(newlook.size()>0) {
			click(getDriver().findElement(By.xpath("//button[@aria-checked='true']")),"New look toggle");
			click(getDriver().findElement(By.xpath("//span[contains(text(),'Skip feedback')]")),"Skip Feedback");
			
		}
		
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(35));
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//button[@aria-checked='false']"))));
		//Thread.sleep(000);
		return this;
	}
	//Click on Yes in stay signed in window
	public void selectPremierAccount() throws InterruptedException {
		switchToFrame(1);
		if(verifyIsDisplayed(getDriver().findElement(By.xpath("//div[@id='AppDetailsSec_1_Item_3']/div[2]")))) {
			click(getDriver().findElement(By.xpath("")),"Premier");
		}
		switchToDefaultContent();
	}	



}
