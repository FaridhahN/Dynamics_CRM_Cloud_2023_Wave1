package testcases.AlithyaCPRM;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID:_1087265:_1087265:Verify the updated sales account form is displayed for the Channel partner Regional manager


public class TestCase_1087265 {


	@Test
	public void Oppurtunities(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.staysignedinforOtherApp()

		.selectSalesHubAccount()
		
		.clickAccounts()
		.clickNewAccounts()
		.changeView("Sales Form")
		
		.verifyNewAccountsPage()
		.verifyAccountsPageTabs()
		;					
	}
}
