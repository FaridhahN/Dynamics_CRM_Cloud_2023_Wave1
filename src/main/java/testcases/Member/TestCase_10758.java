package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_ 10758 :Cloud:Verify on  deactivating active HIN ,should get removed from Member form.



public class TestCase_10758 {

	  	
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
		.selectAccountFromSearchResults()

		//3.Verify HIN when Deactivated is removed from Member Form  
		
		.verifyDEAIsNull()
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeHIN()
		.typeAccountNumberHIN()
		.clickSaveInAccountNumbersEntity()
		.clickGoBack()
		.clickGeneralTab()
		.verifyHIN(DataInputProvider.getCellData_ColName(iRowNumber, "HIN", sDataSheetName))
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberHIN()
		.clickDeactivateInAccountNumbers()
		.clickGoBackOnMemberForm()
		.clickGeneralTab()
		.verifyHINIsNull();
			
					
		//Data Reset	- Not required
		
			
	}
}
