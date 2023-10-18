package testcases.DateValidations;


import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;
//Test Case 716715: Verify Application start date validation is removed when terminating an account

public class TestCase_716715{

	
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
