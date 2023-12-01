package testcases.AccountNumbersFedID;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_821998:_821998:Verify whether if country='USA' , user should allow to add Federal Tax ID with 9 digits -only numeric.

public class TestCase_821998 {


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
		
		//Create New FED ID
		.clickAddNewAccountNumberInAccountNumbers()
		.verifyAccountName()
		.chooseAccountNumberTypeFedTaxID()

		.typeRandomFedTaxID(99999999,999999999)
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()
		
		//Verify Error message is not displayed
				.verifyErrorisNotDisplayed()

		//Data Reset
				.clickDeactivateInAccountNumbers();


		
		;


	}
}
