package testcases.AlithyaCPRM;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1089784:_1089784:Verify Channel partner regional manager can see CP - Open Opportunities funnel chart under Channel Partner Salesperson Dashboard


public class TestCase_1089784 {


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
		.clickDashboard()
		
		.changeViewinDashboard("Channel Partner Salesperson Dashboard")
		
		.verifyCPOpenOpportunityChart()
		;					
	}
}
