package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10661-Cloud:Verify whether user able to add same "Federal Tax ID" on different "Supplier" and "Member" accounts.



public class TestCase_10661 {


	@Test
	public void verifyAccountNumberEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Member Supervisor
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

		//3.Add FedTaxID on Member 
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeFedTaxID()
		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
		.typeStartDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.verifyAccountNameInAccountNumbers(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
		.verifyNameAndCalculatedNameInAccountNumbers()
		
		//4.Logout as Member
		.clickLogout()
		.refreshPageChooseSignInUser()
		
		//5.Login as a Supplier Supervisor
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()


		//6.Select Accounts Entity
		.selectAccountsTab()

		//7.Search and Select Supplier Account using CRM#
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber1", sDataSheetName))
		.selectSupplierAccountFromSearchResults()
		
		//8.Add the same Fed Tax ID (from step 3 )  on to the Supplier
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeFedTaxID()
		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
		.typeStartDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.verifyAccountNameInAccountNumbers(DataInputProvider.getCellData_ColName(iRowNumber, "accountName2", sDataSheetName))
		.verifyNameAndCalculatedNameInAccountNumbers()


		//Data Reset~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//Deactivate the Fed Tax ID on the Supplier Account
		.clickDeactivateInAccountNumbers()
		
		//Log out as a Supplier Supervisor
		.clickLogout()
		.refreshPageChooseSignInUser()

		//Login as a Member Supervisor
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//Select the same member account on which the Fed Tax ID was added in Step 3 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromSearchResults()
		.selectAccountNumbers()
		
		//Deactivate Fed Tax ID from the Member Account
		.doubleClickExistingAccountNumberFedTaxID()
		.clickDeactivateInAccountNumbers();


	}
}
