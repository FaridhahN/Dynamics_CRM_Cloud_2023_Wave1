package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087564:_1087564:Verify whether 'CP-My Opportunity' view is available for the user - Channel Partner Senior Director.

public class TestCase_1087564 {


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
		.clickOppurtunitiesPage()
		.verifyDefaultViewOppurtunityPage()
		.changeViewinOppurtunityPage("CP - My Opportunities")
		.verifyErrorisNotDisplayed()
		;					
	}
}
