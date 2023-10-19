package testcases.Activities;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8886:Cloud: Add a task activity and select from quick subject

public class TestCase_8886 {


	@Test
	public void TaskActivity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		// Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()

		//Search the account
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		//Click Activities Tab
		.selectRelatedActivities()

		//Select Open Activities View
		.selectOpenActivitiesView()


		//Click New Task Activity
		.clickNewTaskActivity()

		//Verify Autom populated Feilds
		.verfiyAutopopulatedFields()

		//Enter the details in the new tasks window
		.EnterTaskDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) ,
				DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))


		//Update the task
		.selectATask(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName))

		.EnterTaskDetailswithQuickSubject(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) , DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		//Verify the Completion status of the task
		.selectOpenActivitiesViewAfterTask()

		.verifyTasksCompletionstatus("Open")

		//Data Reset
		.completeAllTask()
		;
	}
}
