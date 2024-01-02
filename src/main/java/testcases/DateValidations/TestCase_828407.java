package testcases.DateValidations;


import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_8496:_828406:Verify "Error Message" should display date's when Premier End date < TPRD for Supplier Accounts.

public class TestCase_828407{

	
	@Test
	public void DateValidation(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()
		//Search the account
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.navigateToApplicationDate()
		.chooseApplicationDate(TestUtils.getDate())
		.navigateToRecordStatus()
		.changeRecordStatus("Draft")
		.changeRecordStatus("Published")
		.clickSave()
		.verifyErrorisNotDisplayed()
		;
	}
}
