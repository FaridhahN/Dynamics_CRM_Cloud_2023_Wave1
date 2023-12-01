package testcases.Activities;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_964300: Automation Verify in Activities whether Task - 'Subject' field should not be concatenated with Entity code and Supplier Account Name for Member Accounts.   

public class TestCase_964300 {


	@Test()

	public void VerifyActivitesinSupplier(int iRowNumber, String sDataSheetName) throws Exception {

		//Login to the application
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//Search and select an account
		.selectAccountsTab()

		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
		//Navigate to Activities via related
		.clickRelatedActivities()

		.selectOpenActivitiesView()
		
		//Click new Task Activity
		.clickNewTaskActivity()

		//Verify subject field is empty
		.verifySubjectFieldisNull()
		
		//Create new Task
		.EnterTaskDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) ,
				DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		.selectOpenActivitiesViewAfterTask()

		//Verify the created task
		.verifyTasksCompletionstatus("Open")

		//DataReset
		.completeAllTask()





		;

	}


}