package testcases.Activities;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8888:Cloud : Add a fax activity and type subject

public class TestCase_8888 {


	@Test
	public void FaxActivity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		.clickNewFaxActivity()

		//Verify Autom populated Feilds
		.verfiyAutopopulatedFieldsFax()


		//Select the information view 
		.selectInformationview()


		//Enter the details in the new tasks window
		.EnterFaxDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Faxnumber", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "noofPages", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Coverpage", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Direction", sDataSheetName) , DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		//Select Open Activities View and completed the activity
		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Open")
		.openFax()
		.verifyTasksPage()
		.clickMarkComplete()
		.selectAllActivity()
		.verifyTasksCompletionstatus("Completed")

		//Data reset
		.completeAllTask()
		;
	}
}
