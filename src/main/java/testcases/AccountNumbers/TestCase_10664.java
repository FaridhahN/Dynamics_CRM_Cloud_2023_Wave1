package testcases.AccountNumbers;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10664-Cloud:Verify whether user able to add same "Federal Tax ID" on different "Member"  accounts


public class TestCase_10664 {


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

		//3.Add FedTaxID on Member Account 1
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeFedTaxID()
		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
		.typeStartDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.verifyAccountNameInAccountNumbers(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
		.verifyNameAndCalculatedNameInAccountNumbers()
		
		
		//4.Select Accounts Entity
		.selectAccountsTabFromMemberFormPage()

		//5.Search and Select another Member Account 2 using CRM#
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber1", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber1", sDataSheetName))
		
		//6.Add the same Fed Tax ID (from step 3 )  on to the Member Account 2
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeFedTaxID()
		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
		.typeStartDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.verifyAccountNameInAccountNumbers(DataInputProvider.getCellData_ColName(iRowNumber, "accountName2", sDataSheetName))
		.verifyNameAndCalculatedNameInAccountNumbers()


		//Data Reset~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//Deactivate the Fed Tax ID on the Member Account 2
		.clickDeactivateInAccountNumbers()
		
		
		//Select the same Member Account 1 on which the Fed Tax ID was added in Step 3 
		.selectAccountsTabFromMemberFormPage()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountNumbers()
		
		//Deactivate Fed Tax ID from the Member Account 1
		.doubleClickExistingAccountNumberFedTaxID()
		.clickDeactivateInAccountNumbers();


	}
}
