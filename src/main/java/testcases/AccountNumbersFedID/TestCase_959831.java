package testcases.AccountNumbersFedID;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;


//TFS ID_959831:_959831:Verify Calculated name is populated for FedTAX account number when created when already an existing FedTAX account number is present active



public class TestCase_959831 {


	@Test
	public void verifyAccountNumberEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		try {
			//1. Login to CRM as Member
			new LoginPage()
			.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
			.clickNext()
			.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
			.clicSignin()
			.clicYesInStaySignedin()

			//Search a Supplier
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
			.clickDeactivateInAccountNumbers();
		}catch(Exception e){
			e.printStackTrace();
			//Data reset if something goes wrong in the try part
			new DashboardPage()//2.Select Accounts Entity

			.selectAccountsTab()

			//.Search and Select Supplier Account 1 using CRM#
			.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
			.selectSupplierAccountFromSearchResults()

			//Deactivate the Fed ID
			.selectAccountNumbers()
			.doubleClickExistingAccountNumberFedTaxID()
			.clickDeactivateInAccountNumbers();
			;
		}


	}
}
