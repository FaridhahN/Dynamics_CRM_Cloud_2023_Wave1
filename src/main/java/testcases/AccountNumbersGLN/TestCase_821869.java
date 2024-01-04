package testcases.AccountNumbersGLN;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_821869:_Validate Check Digit on GLN Account Number


public class TestCase_821869 {


	@Test
	public void verifyGLNcheckDigit(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Member
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to WorkPlace >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Add Invalid GLN to Account and verify Check Digit Error
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.verifyAccountnumberTypedropdown("GLN")
		.chooseAccountNumberTypeGLN()
		.typeAccountNumberGLN("AB26769989876")			
		.clickSaveAccountNumber()
		.verifyError("Invalid GLN Number - Check Digit Algorithm Failed!")	

		//Data Reset- Required
		;

	}
}
