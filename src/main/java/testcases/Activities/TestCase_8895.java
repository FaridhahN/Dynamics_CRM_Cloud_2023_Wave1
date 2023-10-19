package testcases.Activities;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8895:Cloud : Add a letter activity and select quick subject


public class TestCase_8895 {


	@Test
	public void LetterActivity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		.clickNewLetterActivity()

		//Verify Automatic populated Feilds
		.verfiyAutopopulatedFieldsFax()

		//Select the information view
		.selectInformationview()

		//Enter the details in the new tasks window
		.EnterLetterDetailsWithQuickSubject(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		//Select Open Activities View and verify the status
		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Open")

		//Data reset
		.completeAllTask()

		;
	}
}
