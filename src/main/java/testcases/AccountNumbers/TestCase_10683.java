package testcases.AccountNumbers;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_ 10683:Cloud: Automation: Verify on End dating active DEA ,should get removed from Member form.



public class TestCase_10683 {


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
		//3.Verify DEA when End dated is removed from Member Form  

		.verifyDEA(DataInputProvider.getCellData_ColName(iRowNumber, "DEANumber", sDataSheetName))
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberDEA()
		.typeStaticEndDateInAccountNumbers(DataInputProvider.getCellData_ColName(iRowNumber, "accNumEndDate", sDataSheetName))
		.clickSaveInAccountNumbersEntity()
		.clickGoBackOnMemberForm()
		.clickGeneralTab()
		.verifyDEAIsNull()



		//Data Reset	
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberDEA()
		.clearEndDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity();



	}
}
