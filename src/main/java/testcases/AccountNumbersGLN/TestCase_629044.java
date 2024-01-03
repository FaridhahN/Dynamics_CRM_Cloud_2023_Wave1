package testcases.AccountNumbersGLN;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_629044:_ Verify User 'Number' field value is mandatory for creating GLN Account Number


public class TestCase_629044 {

	  	
	@Test
	public void verifyNumberfieldGLN(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
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

		//Add GLN to Account
		
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.verifyAccountnumberTypedropdown("GLN")
		.chooseAccountNumberTypeGLN()
		.clickSaveAccountNumber()
		.verifyError("Number can not be empty.")
		.typeAccountNumberGLN("1234567890111")
		.typeStartDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.clickGoBack()
		
							
		//Data Reset
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberGLN()
		.clickDeactivateInAccountNumbers()
		;
			
	}
}
