package testcases.MemberEntryForm;

import org.testng.annotations.Test;

import pages.ContactsPage;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_964341:_964341:Verify whether "Activities" are available in the 'Related' Tabs of the Member Entry Form.


public class TestCase_964341 {


	@Test
	public void verifyActivities(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{
		//1. Login to CRM using member supervisor / member credentials
		WebDriverServiceImpl.isMemberForm=true;
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		
		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
		.chooseMemberEntryForm()
		.clickSave()
		.verifyActivitiesOption()
		//Click Activities Tab
		.selectRelatedActivities()

		//Select Open Activities View
		.selectOpenActivitiesView()

		//Click New Task Activity		
		.clickNewTaskActivity()

		//Verify Auto populated Fields
		.verfiyAutopopulatedFields()
		//Changed as part of new change


		//Enter the details in the new tasks window
		.EnterTaskDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) ,
				DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		//Verify the activity is created sucessfully

		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Open")

		//DataReset
		.completeAllTask()
		;

	}

}
