package testcases.AccountNumbersRemitra;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_629252:_629252:Verify "Remitra" Account Number Type is available for selection in Account Number Entity


public class TestCase_629252 {

	  	
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

		//3.Verify HIN is displayed on Member Form  
		
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.verifyAccountnumberTypedropdown("Remitra")
		.chooseAccountNumberTypeRemitra()
		.typeAccountNumberRemitra()
		.typeStartDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.clickGoBack()
		
							
		//Data Reset
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberRemitra()
		.clickDeactivateInAccountNumbers()
		;
			
	}
}
