package testcases.AccountNumbersRemitra;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_959824:_959824:Verify Remitra account number can be created when already an existing Remitra account number is present active in the account

public class TestCase_959824 {


	@Test
	public void verifyAccountNumberEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Member
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber1", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber1", sDataSheetName))

		//3. Navigate to the Account number Tab

		.selectAccountNumbers()
		.getAccountName()

		//Create New FedID 
		.clickAddNewAccountNumberInAccountNumbers()

		//verifying auto populated Account name
		.verifyAccountName()
		.chooseAccountNumberTypeRemitra()

		.typeAccountNumberRemitra()
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()

		.clickGoBackButton()
		//Add the same Remitra ID again
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeRemitra()
		.typeAccountNumberRemitra()
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()

		//Verify ONly one Active Remitra id of each account error 
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()

		//Data Reset
		.clickGoBackandDiscardChanges()
		.doubleClickExistingAccountNumberRemitra()
		.clickDeactivateInAccountNumbers()
		;


	}
}
