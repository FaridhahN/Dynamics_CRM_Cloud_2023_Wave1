package testcases.AlithyaCPRM;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1089787:_1089787:Verify Channel partner regional manager can see My past due activities under Channel Partner Salesperson Dashboard


public class TestCase_1089787 {


	@Test
	public void Oppurtunities(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.staysignedinforOtherApp()

		//Select Sals Hub
		.selectSalesHubAccount()

		//Click Activities in the left pane
		.clickActivitiesInLeftPane()
		
		//Click Email under activity
		.clickEmailUnderActivity()
		
		//Enter the details in the Email activitiy
		.EnterEmailDetailsWithRegardingAndDueDate(DataInputProvider.getCellData_ColName(iRowNumber, "subject", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "To", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "regarding", sDataSheetName), TestUtils.getPastDate(TestUtils.todaysDate(),5), DataInputProvider.getCellData_ColName(iRowNumber, "time", sDataSheetName))
		
		//Clcik Dashboard
		.clickDashboard()
		
		//Change the view
		.changeViewinDashboard("Channel Partner Salesperson Dashboard")
		
		//Verify the Activity
		.verifyActivitiesFromDashboardCPSalesView(DataInputProvider.getCellData_ColName(iRowNumber, "subject", sDataSheetName))
		
		//Delete the activity
		.deleteActivitiesFromDashboardCPSalesView(DataInputProvider.getCellData_ColName(iRowNumber, "subject", sDataSheetName))
		
		;
	}
}
