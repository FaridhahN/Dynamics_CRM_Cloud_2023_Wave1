package testcases.AccountNumbersRemitra;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_959829:_959829:Verify Calculated name is populated for FedTAX account number when created on an existing supplier account


public class TestCase_959822 {


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

			//4.Add the Fed Tax ID to the Supplier  and update the name
			.selectAccountNumbers()
			.clickAddNewAccountNumberInAccountNumbers()
			.chooseAccountNumberTypeRemitra()
			.typeAccountNumberRemitra()
			.typeStartDateInAccountNumbers()
			.clickSaveInAccountNumbersEntity()
			.verifyAccountNameInAccountNumbers(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
			.verifyNameAndCalculatedNameInAccountNumbers()
			
			//Data Reset

			.clickGoBackButton()
			.doubleClickExistingAccountNumberRemitra()
		
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
			.doubleClickExistingAccountNumberRemitra()
			.clickDeactivateInAccountNumbers();
			;
		}

	}
}
