package testcases.AlithyaCPRD;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087557:_1087557:Verify whether 'CP-My Opportunity' view is available for the user - Channel Partner Regional Director.

public class TestCase_1087557 {


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
