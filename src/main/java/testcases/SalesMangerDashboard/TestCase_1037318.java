package testcases.SalesMangerDashboard;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1087652:_1087652:Verify whether Email due for today is available in Assistant -lightbulb button for the user Channel Partner Regional Director.

public class TestCase_1037318 {


	@Test
	public void Oppurtunities(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.staysignedinforOtherApp()

		//Navigate to the Sales hub
		.selectSalesHubAccount()
		
		.clickActivitiesInLeftPane()
		
		//Click Email under activity
				.clickEmailUnderActivity()
				
				//Enter the details in the Email activitiy
				.EnterEmailDetailsWithRegardingAndDueDate(DataInputProvider.getCellData_ColName(iRowNumber, "subject", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "To", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "regarding", sDataSheetName), TestUtils.todaysDate(), DataInputProvider.getCellData_ColName(iRowNumber, "time", sDataSheetName))
				
				//Clcik Dashboard
				.clickDashboard()
				
				//Change the view
				.changeViewinDashboard("Sales Manager Dashboard")
				
				//Verify the Activity
				.verifyActivitiesFromDashboardCPSalesView(DataInputProvider.getCellData_ColName(iRowNumber, "subject", sDataSheetName))
				
				//Delete the activity
				.deleteActivitiesFromDashboardCPSalesView(DataInputProvider.getCellData_ColName(iRowNumber, "subject", sDataSheetName))
		;					
	}
}
