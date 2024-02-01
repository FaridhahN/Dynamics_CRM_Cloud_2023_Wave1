package testcases.AccountNumbersDUNS;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_1004163:_1004163_Verify validation check of 'DUNS' Account number type.
 

public class TestCase_1004163 {


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

		//Type less than 9 digits
		.typeStaticAccountNumber("12345678")

		//Clcik Save button
		.clickSaveAccountNumber()

		//Verify Error message is displayed when less than 9 digit
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()
		//Type special characters
		.typeStaticAccountNumber("1234&5@#$")

		//Clcik Save button
		.clickSaveAccountNumber()

		//Verify Error message is displayed when less than 9 digit
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage1", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()
		
		.typeStaticAccountNumber("12  &5@#$")

		//Clcik Save button
		.clickSaveAccountNumber()

		//Verify Error message is displayed when less than 9 digit
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage1", sDataSheetName))
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
