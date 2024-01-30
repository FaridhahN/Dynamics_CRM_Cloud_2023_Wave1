package testcases.AccountNumbersDUNS;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_ 1004136:_1004136_Verify whether 'DUNS' Account number type is available in Account Number Type.

public class TestCase_1004136 {

	  	
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
		.typeAccountNumberDUNS()
		.typeStartDateInAccountNumbers()
		.verifyNameAndCalculatedNameInAccountNumbers()
		.clickGoBack()
		
							
		//Data Reset
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberDUNS()
		.clickDeactivateInAccountNumbers()
		;
			
	}
}
