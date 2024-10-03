package testcases.SalesMangerDashboard;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1037302_1037302:Verify Field Rep Dashboard Views


public class TestCase_1037331 {


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

		//Clcik Dashboard
		.clickDashboard()
		
		//Change the view
		.changeViewinDashboard("Sales Manager Dashboard")
		
		
		//Verify Sub topics in the Sales MAnager Dashboard
		
		.verifySalesManagerDashboard()
		;					
	}
}