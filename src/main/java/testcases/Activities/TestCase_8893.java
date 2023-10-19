package testcases.Activities;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8893:Cloud : Complete a phone activity

public class TestCase_8893 {


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

		//Click New Fax Activity
		.clickNewPhoneActivity()

		//Verify Autom populated Feilds
		.verfiyAutopopulatedFieldsFax()

		//Select the information view
		//.selectInformationview()

		//Enter the details in the new tasks window
		.EnterFPhoneDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Phonenumber", sDataSheetName))

		//Select Open Activities View and completed the phone activity
		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Open")
		.openPhoneCall()

		.verifyTasksPage()
		.clickMarkComplete()
		.selectAllActivity()
		.verifyTasksCompletionstatus("Completed")

		//Data reset
		.completeAllTask()
		;
	}
}
