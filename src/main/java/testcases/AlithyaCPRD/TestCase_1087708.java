package testcases.AlithyaCPRD;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087708:_1087708:Verify 'Projected NAF' and ‘Est. Close Date’ fields on the opportunity form for Channel Partner Regional Director



public class TestCase_1087708 {


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
		.verifyFeildsinOppurtunitiesForm()
			;					
	}
}
