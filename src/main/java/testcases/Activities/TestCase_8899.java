package testcases.Activities;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8885:Cloud : Add a new task activity and type subject

public class TestCase_8899 {


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

		//Search the account
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))


		//Create new task from the three dot option
		.createNewTaskFromThreeDot()

		//Change the view to Information
		.selectInformationview()

		//Verify subject field is auto populated
		.verifytheSubjectFeild(DataInputProvider.getCellData_ColName(iRowNumber, "verifyEntityCode", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "verifyAccountName", sDataSheetName))

		.EnterTaskDetailswithQuickSubject(DataInputProvider.getCellData_ColName(iRowNumber, "QuickSubject", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) ,
				DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))
		
		.clickRelatedActivities()
		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Open")

		//DataReset
		.completeAllTask()


		//Create new task from the three dot option
		.createNewPhoneFromThreeDot()

		//Change the view to Information
		.selectInformationview()

		//Enter the details in the new tasks window
		.EnterFPhoneDetailsWithQuickSubject(DataInputProvider.getCellData_ColName(iRowNumber, "QuickSubject", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Phonenumber", sDataSheetName))

		.clickRelatedActivities()
		
		//Select Open Activities View and complete the phone activity
		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Open")
		.completePhonecall()

		.createNewAppointmentFromThreeDot()
		
		.EnterAppointmentDetailsWithQuickSubject(DataInputProvider.getCellData_ColName(iRowNumber, "QuickSubject", sDataSheetName), sDataSheetName)
		
.clickRelatedActivities()
		
		//Select Open Activities View and complete the phone activity
		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Open")
		.completeAllTask()
		;
	}
}
