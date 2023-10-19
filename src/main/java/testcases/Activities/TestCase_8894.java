package testcases.Activities;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8894:Cloud : Add a email activity and type subject

public class TestCase_8894 {


	@Test
	public void PhoneActivity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromSearchResults()


		//Click Activities Tab
		.selectRelatedActivities()

		//Select Open Activities View
		.selectOpenActivitiesView()

		//Click New Phone Activity
		.clickNewEmailActivity()

		//Verify Auto populated Feilds
		.verfiyAutopopulatedFieldsEmail()


		//Enter the details in the new tasks window
		.EnterEmailDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "To", sDataSheetName))
		.selectAllActivity()
		.verifyTasksCompletionstatus("Completed")

		//Data reset
		.completeAllTask()
		;
	}
}
