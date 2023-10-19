package testcases.Activities;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8900:Automation Cloud : Verify whether value : "ASCD-Portfolio Advisement" is available in Quick Subject-Task Entity.

public class TestCase_8900 {


	@Test
	public void TaskActivity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
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

		//Verify Autom populated Feilds
		.verfiyAutopopulatedFields()

		//Enter the details in the new tasks window
		.EnterTaskDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) ,
				DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		//Update the task
		.selectATask(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName))

		.selectInformationview()
		//Enter the details in the new tasks window
		.EnterTaskDetailswithQuickSubject(DataInputProvider.getCellData_ColName(iRowNumber, "QuickSubject", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) , DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		//Verify the completion status
		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Open")

		//Data reset
		.completeAllTask();
	}
}
