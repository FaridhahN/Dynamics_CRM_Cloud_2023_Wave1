package testcases.AccountNumbers;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_ 10759 :Cloud:Verify whether newly added active HIN ,should appear on Member form.



public class TestCase_10759 {

	  	
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
		.chooseMemberForm()
		//3.Verify HIN is displayed on Member Form  
		
		.verifyHINIsNull()
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeHIN()
		.typeAccountNumberHIN()
		.clickSaveInAccountNumbersEntity()
		.clickGoBack()
		.clickGeneralTab()
		.verifyHIN(DataInputProvider.getCellData_ColName(iRowNumber, "HIN", sDataSheetName))
		
							
		//Data Reset
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberHIN()
		.clickDeactivateInAccountNumbers()
		.clickGoBackOnMemberForm()
		.clickGeneralTab()
		.verifyHINIsNull();
		
			
	}
}
