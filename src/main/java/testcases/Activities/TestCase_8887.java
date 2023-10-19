package testcases.Activities;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8887:Cloud : Complete a task


public class TestCase_8887 {


	@Test
	public void TaskActivity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		//Click New Task Activity
		.clickNewTaskActivity()

		//Verify Auto populated Feilds
		.verfiyAutopopulatedFields()

		//Enter the details in the new tasks window
		.EnterTaskDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) ,
				DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))
		//.clickGoBackTaskPage()

		//Select Open Activities View and complete the activity
		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Open")
		.openTasks()
		.verifyTasksPage()
		.clickMarkComplete()
		.selectAllActivity()
		.verifyTasksCompletionstatus("Completed")

		//Data reset
		.completeAllTask()

		;
	}
}
