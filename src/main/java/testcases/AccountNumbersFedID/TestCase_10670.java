package testcases.AccountNumbersFedID;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_10670:_36610_Cloud : Verify whether Only one Active Federal Tax ID should be available on Member, Supplier, Prospect.


public class TestCase_10670 {


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

		//Create New FedID 
		.clickAddNewAccountNumberInAccountNumbers()

		//verifying auto populated Account name
		.verifyAccountName()
		.chooseAccountNumberTypeRemitra()

		.typeStaticAccountNumber(DataInputProvider.getCellData_ColName(iRowNumber, "remitra", sDataSheetName))
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()

		.clickGoBackButton()
		//Add the same FED ID again
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeRemitra()
		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "remitra", sDataSheetName))
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()

		//Verify ONly one Active Fed id of each account error 
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()

		//Data Reset
		.clickGoBackandDiscardChanges()
		.doubleClickExistingAccountNumberFedTaxID()
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

		//Create New FedID 
		.clickAddNewAccountNumberInAccountNumbers()

		//verifying auto populated Account name
		.verifyAccountName()
		.chooseAccountNumberTypeFedTaxID()

		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()

		.clickGoBackButton()
		//Add the same FED ID again
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeFedTaxID()
		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()

		//Verify ONly one Active Fed id of each account error 
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()

		//Data Reset
		.clickGoBackandDiscardChanges()
		.doubleClickExistingAccountNumberFedTaxID()
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
		.chooseAccountNumberTypeFedTaxID()

		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()

		.clickGoBackButton()
		//Add the same FED ID again
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeFedTaxID()
		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()

		//Verify ONly one Active Fed id of each account error 
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()

		//Data Reset
		.clickGoBackandDiscardChanges()
		.doubleClickExistingAccountNumberFedTaxID()
		.clickDeactivateInAccountNumbers()
		;


	}
}
