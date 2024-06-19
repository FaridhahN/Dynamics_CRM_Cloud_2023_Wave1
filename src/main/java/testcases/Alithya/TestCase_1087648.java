package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087272:_1087272:Verify the Fee Share % field accepts only numbers




public class TestCase_1087648 {


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
		.clickOppurtunitiesButton()	
		.verifyMemberAccountGridIsNotDisplayed()
		
		;
	}
}
