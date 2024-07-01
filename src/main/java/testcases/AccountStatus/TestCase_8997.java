package testcases.AccountStatus;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_ 8997:Cloud: Verify user should not be allowed to change the account status to Active manually when Premier End date is present

public class TestCase_8997 {

	  	
	@Test
	public void VerifyTerminateAccount(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.chooseRecordStatusDraft()
		.selectAccountStatus("Active")
		.clickSave()
		.verifyReactivateErrorMessage()
		.clickGoBackButton()
		.clickOnDiscardChangesNavigatetoAccountsPage()
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.verifyAccountStatusValueinMEF("Terminated")
		;
	}
}
