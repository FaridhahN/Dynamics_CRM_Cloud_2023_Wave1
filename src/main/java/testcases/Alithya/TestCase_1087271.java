package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087271:_1087271:Verify Fee share % new field is added in the sales account form



public class TestCase_1087271 {


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
		.enterAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
		.navigatetoFeeshare()
		.verifyFeeShare()
		
		;					
	}
}
