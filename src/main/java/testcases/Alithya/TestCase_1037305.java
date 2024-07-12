package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1037305:_1037305:Verify "My Past Due Activities" view on Field Rep Dashboard


public class TestCase_1037305 {


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
		.changeViewinDashboard("Field Rep Dashboard")
		
		//Verify the Activity
		.verifyActivityUnderPastActivity(DataInputProvider.getCellData_ColName(iRowNumber, "subject", sDataSheetName))
		
		//Delete the activity
		.deleteActivitiesFromDashboardPast(DataInputProvider.getCellData_ColName(iRowNumber, "subject", sDataSheetName))
		;
	}
}
