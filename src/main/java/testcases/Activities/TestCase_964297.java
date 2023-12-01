package testcases.Activities;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_964297:_964297_Cloud:Automation Verify in 'Activities' Tab whether Task - 'Subject' field is concatenated with Entity code and Supplier Account Name for Supplier Accounts. 
 
public class TestCase_964297 {


	@Test()

	public void VerifyActivitesinSupplier(int iRowNumber, String sDataSheetName) throws Exception {

		
		new LoginPage()
		//Login to the application
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//Search an account
		.selectAccountsTab()

		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
		//Open Activities Tab
		.openActivitiesTab()
		
		//Click new Task Activity
		.clickNewTaskActivity()

		//Verify subject field is auto populated
		.verifytheSubjectFeild(DataInputProvider.getCellData_ColName(iRowNumber, "verifyEntityCode", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "verifyAccountName", sDataSheetName))
		
		//Create new Task
		.EnterTaskDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) ,
				DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		//Verify the created task
		.verifySupbjectAfterCreation(DataInputProvider.getCellData_ColName(iRowNumber, "verifyEntityCode", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "verifyAccountName", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName))
		
		//DataReset could not be performed due to the application issue where Member and supplier supervisor could not delete the activities, 
		//only user admin could delete, but we are not raising the bug since we could complete the task

		//.deleteAllActivities()
		
		;

	}


}