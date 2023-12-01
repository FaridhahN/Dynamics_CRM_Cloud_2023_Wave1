package testcases.AccountNumbersFedID;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_959826:_959826:Verify Calculated name is populated for FedTAX account number when created


public class TestCase_959826 {


	@Test
	public void verifyAccountNumberEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		try {
			//1. Login as a Supplier Supervisor
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
			.chooseAccountNumberTypeFedTaxID()
			.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
			.typeStartDateInAccountNumbers()
			.clickSaveInAccountNumbersEntity()
			.verifyAccountNameInAccountNumbers(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
			.verifyNameAndCalculatedNameInAccountNumbers()
			.clickGoBackButton()


			.doubleClickExistingAccountNumberFedTaxID()
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
			.doubleClickExistingAccountNumberFedTaxID()
			.clickDeactivateInAccountNumbers();
			;
		}

	}
}
