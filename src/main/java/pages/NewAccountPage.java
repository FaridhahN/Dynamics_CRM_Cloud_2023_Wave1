package pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import services.WebDriverServiceImpl;

public class NewAccountPage extends WebDriverServiceImpl {
	
//Choose member form
	public MemberFormPage chooseMemberForm() throws InterruptedException {
		click(getDriver().findElement(By.xpath("(//*[@data-id='form-selector'])[1]")),"Form Selector");
		click(getDriver().findElement(By.xpath("//span[text()='Member Form']")),"Member Form");
		List<WebElement> element=getDriver().findElements(By.xpath("//*[@data-id='cancelButton']"));
		if(element.size()>0) {
			click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Discard Changes");
		}
		Thread.sleep(2000);
		return new MemberFormPage();
	}

	
	
		
//Choose supplier form
	public SupplierFormPage chooseSupplierForm() throws InterruptedException {
		click(getDriver().findElement(By.xpath("(//*[@data-id='form-selector'])[1]")),"Form Selector");
		click(getDriver().findElement(By.xpath("//span[text()='Supplier Form']")),"Supplier Form");
		click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Discard Changes");
		Thread.sleep(2000);
		return new SupplierFormPage();
	}
	
//Choose member entry form
	public MemberFormPage chooseMemberEntryForm() throws InterruptedException {
		click(getDriver().findElement(By.xpath("(//*[@data-id='form-selector'])[1]")),"Form Selector");
		click(getDriver().findElement(By.xpath("//span[text()='Member Entry Form']")),"Member Entry Form");
		click(getDriver().findElement(By.xpath("//*[@data-id='cancelButton']")),"Discard Changes");
		Thread.sleep(2000);
		return new MemberFormPage();
	}
		
public AccountsPage chooseActiveMember() {
	switchToFrame(getDriver().findElement(By.id("contentIFrame0")));
	click(getDriver().findElement(By.xpath("//span[@title()='Select a view']")),"Select a view");
	return new AccountsPage();
}

}
