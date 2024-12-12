package testcases.AccountNumbersDUNS;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_1004175:_1004175_Verify whether 'Number' field value is mandatory for creating DUNS Account Number 


public class TestCase_1004175 {


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
		//.verifyAccountnumberTypedropdown("DUNS")
		.chooseAccountNumberTypeDUNS()

		//Clcik Save button
		.clickSaveAccountNumber()

		//Verify Error message is displayed when the account number feild is left blank
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()

		.typeAccountNumberDUNS()
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()

		.verifyNameAndCalculatedNameInAccountNumbers()
		.clickGoBack()


		//Data Reset
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberDUNS()
		.clickDeactivateInAccountNumbers()
		;

	}
}
