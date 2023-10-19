package testcases.Activities;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8891:Cloud : Add a phone activity


public class TestCase_8891 {


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

		.completeAllTask()

		//Click New Fax Activity
		.clickNewPhoneActivity()

		//Verify Auto populated Feilds
		.verfiyAutopopulatedFieldsFax()


		//Enter the details in the new tasks window
		.EnterFPhoneDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Phonenumber", sDataSheetName))

		//Select Open Activities View and verify the status
		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Open")

		//Data reset
		.completeAllTask()
		;
	}
}
