package testcases.AccountNumbersDUNS;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_1004170:_1004170_Verify Duplicate check of 'DUNS' Account number type.


public class TestCase_1004170 {


	@Test
	public void verifyAccountNumberDUNS(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		//Verify DUNS is displayed in the Account Nunmber drop down
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.verifyAccountnumberTypedropdown("DUNS")
		.chooseAccountNumberType("DUNS")
		
		.typeStaticAccountNumber("546543785")
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()

		.verifyNameAndCalculatedNameInAccountNumbers()
		.clickGoBack()
		
		
		.selectAccountNumbers()
		
		.clickAddNewAccountNumberInAccountNumbers()
		.verifyAccountnumberTypedropdown("DUNS")
		.chooseAccountNumberType("DUNS")
		
		.typeStaticAccountNumber("546543785")
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()
		//Verify Error message is displayed when the account number feild is left blank
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()

		
		.clickGoBack()


		//Data Reset
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberDUNS()
		.clickDeactivateInAccountNumbers()
		;

	}
}
