package testcases.AlithyaCPRM;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1089766:_1089766:Verify Opportunity Channel Partner form is accessible for Channel partner Regional manager



public class TestCase_1089766 {


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
		.changeViewinOppurtunityPage("CP - Open Opportunities")
		.verifyGutFeelOptioninView()
		.ClickNewOppurtunity()
		.changeOppurtunityFormType("Opportunity Channel Partner")
		;					
	}
}
