package testcases.AlithyaCPRD;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087767:_1087767:Verify columns for the mentioned Opportunity Views for Channel Partner Senior Director


public class TestCase_1087766 {


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
		.changeViewinOppurtunityPage("CP - All Opportunities")
		.verifyOppurtunityviewColumn()
		.clikCancelButton()
		.changeViewinOppurtunityPage("CP - My Opportunities")
		.verifyOppurtunityviewColumn()
		.clikCancelButton()
		.changeViewinOppurtunityPage("CP - Open Opportunities")
		.verifyOppurtunityviewColumn()
		;					
	}
}
