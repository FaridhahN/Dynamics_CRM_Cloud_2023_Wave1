package testcases.Activities;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

public class TestCase_959142 {


	@Test()

	public void VerifyActivitesinSupplier(int iRowNumber, String sDataSheetName) throws Exception {

		//Access Login Page


		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//Select Accounts Entity
		.selectAccountsTab()

		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.clickRelatedActivities()
		.EnterTaskDetails(DataInputProvider.getCellData_ColName(iRowNumber, "verifyEntityCode", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "verifyAccountName", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) ,
				DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Open")

		//DataReset
		.completeAllTask()

		;

	}


}