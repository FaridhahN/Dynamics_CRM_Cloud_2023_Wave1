package testcases.AccountNumbersRemitra;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;
// //TFS ID_11023:_629041: Verify whether user should not be able to add same Remitra account number on to two different Supplier Accounts.

public class TestCase_11023 {


	@Test
	public void verifyAccountNumberEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {


		//1. Login as a Supplier Supervisor
		try {
			new LoginPage()

			.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
			.clickNext()
			.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
			.clicSignin()
			.clicYesInStaySignedin()


			//2.Select Accounts Entity
			.selectAccountsTab()

			//3.Search and Select Supplier Account 1 using CRM#
			.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
			.selectSupplierAccountFromSearchResults()

			//4.Add the Fed Tax ID to the Supplier 1
			.selectAccountNumbers()
			.clickAddNewAccountNumberInAccountNumbers()
			.chooseAccountNumberTypeRemitra()
			.typeStaticAccountNumber(DataInputProvider.getCellData_ColName(iRowNumber, "Remitra", sDataSheetName))
			.typeStartDateInAccountNumbers()
			.clickSaveInAccountNumbersEntity()
			.verifyAccountNameInAccountNumbers(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
			.verifyNameAndCalculatedNameInAccountNumbers()



			//5.Select Accounts Entity from the Left Panel
			.selectAccountsTabFromSupplierPage()

			//6.Search and Select Supplier Account using CRM#
			.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber1", sDataSheetName))
			.selectSupplierAccountFromSearchResults()

			//7.Add the same Fed Tax ID to the Supplier 2
			.selectAccountNumbers()
			.clickAddNewAccountNumberInAccountNumbers()
			.chooseAccountNumberTypeRemitra()
			.typeStaticAccountNumber(DataInputProvider.getCellData_ColName(iRowNumber, "Remitra", sDataSheetName))
			.typeStartDateInAccountNumbers()
			.clickSaveInAccountNumbersEntity()

			//Verify DEA number is mandatory
			.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName)+DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
			.clickOKOnAccountNumberErrorMessage()

			//Select Accounts Entity from the Left Panel
			.selectAccountsTabFromSupplierPage()	

			//Search and Select Supplier Account 1 using CRM#
			.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
			.selectSupplierAccountFromSearchResults()

			//Select Account Number Entity  on Supplier 1 and Deactivate Fed Tax ID from the Supplier Account
			.selectAccountNumbers()
			.doubleClickExistingAccountNumberRemitra()
			.clickDeactivateInAccountNumbers();
		}catch(Exception e){
			//Data reset if something goes wrong in the try part
			new DashboardPage()//2.Select Accounts Entity

			.selectAccountsTab()

			//.Search and Select Supplier Account 1 using CRM#
			.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
			.selectSupplierAccountFromSearchResults()

			//Deactivate the Fed ID
			.selectAccountNumbers()
			.doubleClickExistingAccountNumberRemitra()
			.clickDeactivateInAccountNumbers();
			;
		}

	}
}
