package testcases.AccountNumbersRemitra;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_10778:_36595:Cloud : Verify whether Only one Active Remitra should be available on Member, Supplier, Prospect.

public class TestCase_10778 {


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
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3. Navigate to the Account number Tab

		.selectAccountNumbers()
		.getAccountName()

		//Create New Remitra
		.clickAddNewAccountNumberInAccountNumbers()

		//verifying auto populated Account name
		.verifyAccountName()
		.chooseAccountNumberTypeRemitra()

		.typeAccountNumberRemitra()
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()

		.clickGoBackButton()
		//Add the same Remitra again
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

		//Repeat the teps for Prospect Account
		new DashboardPage()
		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber2", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber2", sDataSheetName))

		//3. Navigate to the Account number Tab

		.selectAccountNumbers()
		.getAccountName()

		//Create New RemitraID 
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

		//Repeat the teps for Supplier Account
		new DashboardPage()
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
