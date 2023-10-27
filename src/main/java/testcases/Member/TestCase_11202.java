package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_11202:_828836_Cloud: Verify user should not be allowed to change the account status to Active manually when Premier End date is present



public class TestCase_11202 {

	@Test
	public void ChangeTerminateStatusToActive(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to the CRM application
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//SEarch an account with the terminated status
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Change the account status to Active from terminated
		.navigateToApplicationDate()

		.changetheAccountStatususingDropdown(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountStatus", sDataSheetName))
		.clickSave()

		//Verify the error message
		.verifyErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))

		//Chnage the status back to Terminated
		.changetheAccountStatususingDropdown("Terminated")

		.clickSave()


		//Change the record status to Draft
		.chooseRecordStatusDraft()


		.NavigateToDocumentTab()
		.clickGeneralTab()

		.navigateToApplicationDate()

		//Change the status to Active
		.changetheAccountStatususingDropdown(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountStatus", sDataSheetName))
		.clickSave()

		//Verify the error message
		.verifyErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))

		//Data Reset

		//Change the status back to Terminated
		.changetheAccountStatususingDropdown("Terminated")

		.clickSave()
		//Publish the account
		.chooseRecordStatusPublished()
		.clickSave()



		;

	}	
}