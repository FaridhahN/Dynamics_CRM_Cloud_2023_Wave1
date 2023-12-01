package testcases.Activities;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_964299:_Verify in Three dot 'Activities' whether Task - 'Subject' field is concatenated with Entity code and Supplier Account Name for Supplier Accounts in Task view.  
 
public class TestCase_964299 {


	@Test()

	public void VerifyActivitesinSupplier(int iRowNumber, String sDataSheetName) throws Exception {

		//Access Login Page


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

		//Create Task activity from three dot option
		.createNewTaskFromThreeDot()
		.selectTaskview()
		
		//Verify subject field is auto populated
		.verifytheSubjectFeild(DataInputProvider.getCellData_ColName(iRowNumber, "verifyEntityCode", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "verifyAccountName", sDataSheetName))
		
		//Create new Task
		.EnterTaskDetailsWithSave(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) ,
				DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		
		
		
		;

	}


}