package testcases.Activities;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

//Test Case: 964296 Automation Verify in 'Related' Tab Activities whether Task - 'Subject' field is concatenated with Entity code and Supplier Account Name for Supplier Accounts. 
public class TestCase_964296 {


	@Test()

	public void VerifyActivitesinSupplier(int iRowNumber, String sDataSheetName) throws Exception {

		//Login to the application
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//Search an account
		.selectAccountsTab()

		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Navigate to Activities via related
		.clickRelatedActivities()

		.selectOpenActivitiesView()

		//Click new Task Activity
		.clickNewTaskActivity()

		//Verify subject field is auto populated
		.verifytheSubjectFeild(DataInputProvider.getCellData_ColName(iRowNumber, "verifyEntityCode", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "verifyAccountName", sDataSheetName))


		//Create new Task
		.EnterTaskDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) ,
				DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		.selectOpenActivitiesViewAfterTask()

		//Verify the created task

		.verifyTasksCompletionstatus("Open")
		.verifySupbjectAfterCreation(DataInputProvider.getCellData_ColName(iRowNumber, "verifyEntityCode", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "verifyAccountName", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName))

		//DataReset
		.completeAllTask()

		;

	}


}