package testcases.Supplier;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10662-Cloud :Verify whether user able to add same "Federal Tax ID" on different "Supplier" ,if they have the same Top Parent.



public class TestCase_10662 {


	@Test
	public void verifyAccountNumberEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
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



		//5.Select Accounts Entity from the Left Panel
		.selectAccountsTabFromSupplierPage()

		//6.Search and Select Supplier Account using CRM#
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput1", sDataSheetName))
		.selectSupplierAccountFromSearchResults()

		//7.Add the same Fed Tax ID to the Supplier 2
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeFedTaxID()
		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
		.typeStartDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.verifyAccountNameInAccountNumbers(DataInputProvider.getCellData_ColName(iRowNumber, "accountName2", sDataSheetName))
		.verifyNameAndCalculatedNameInAccountNumbers()


		//Data Reset~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//Deactivate the Fed Tax ID on the Supplier Account 2			
		.clickDeactivateInAccountNumbers()

		//Select Accounts Entity from the Left Panel
		.selectAccountsTabFromSupplierPage()	

		//Search and Select Supplier Account 1 using CRM#
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults()

		//Select Account Number Entity  on Supplier 1 and Deactivate Fed Tax ID from the Supplier Account
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberFedTaxID()
		.clickDeactivateInAccountNumbers();


	}
}
