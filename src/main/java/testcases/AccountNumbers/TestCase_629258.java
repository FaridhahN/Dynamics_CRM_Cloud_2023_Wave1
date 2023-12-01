package testcases.AccountNumbers;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_629258:_629258:Verify HIN value is removed on the Member Form when the end date is past due.


public class TestCase_629258 {


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

		//Create a HIN
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeHIN()

		//Verify DEA number is mandatory

		.typeAccountNumberHIN()
		.clickSaveInAccountNumbersEntity()
		.getHIN()
		//Naivgate to General Tab and verify HIN is displayed
		.clickGoBackButton()
		.clickGeneralTab()
		.verifyHINInMemberForm()

		//Enter end date for the HIN
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberHIN()
		.typePastEndDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		//Verify HIN is not displayed		
		.clickGoBackButton()
		.clickGeneralTab()
		.verifyHINIsNull()
		//Data Reset	-Not Required

		;	
	}
}
