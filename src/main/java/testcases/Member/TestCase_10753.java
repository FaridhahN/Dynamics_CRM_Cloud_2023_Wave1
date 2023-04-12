package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_10753:Cloud: Automation: Verify on End dating active HIN ,should get removed from Member form.


public class TestCase_10753 {

	  	
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

		//3.Verify HIN when End dated is removed from Member Form  
		
		.verifyHIN(DataInputProvider.getCellData_ColName(iRowNumber, "HIN", sDataSheetName))
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberHIN()
		.typeStaticEndDateInAccountNumbers(DataInputProvider.getCellData_ColName(iRowNumber, "accNumEndDate", sDataSheetName))
		.clickSaveInAccountNumbersEntity()
		.clickGoBackOnMemberForm()
		.clickGeneralTab()
		.verifyHINIsNull()
					
					
		//Data Reset	
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberHIN()
		.clearEndDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity();
		
		
			
	}
}
