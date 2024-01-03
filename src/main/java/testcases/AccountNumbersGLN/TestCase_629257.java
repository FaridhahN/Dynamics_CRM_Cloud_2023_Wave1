package testcases.AccountNumbersGLN;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_629257:_ Verify GLN account number's end date restriction.


public class TestCase_629257 {


	@Test
	public void verifyNumberfieldGLN(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		//Check End Date logics

		.selectAccountNumbers()
		.doubleClickExistingAccountNumberGLN()
		
		//Enter today's date as end date
		.typeEndDateInAccountNumbers()
		.clickSaveAccountNumber()
		.verifyError("End date can't be today or future date")
		
		//Enter Future date as end date
		.typeFutureEndDateInAccountNumbers()
		.clickSaveAccountNumber()
		.verifyError("End date can't be today or future date")

		//Enter Past Date as end date
		.typePastEndDateInAccountNumbers()
		.clickSaveAccountNumber()
		.verifyErrorisNotDisplayed()

		//Data Reset
		.clearEndDateInAccountNumbers()
		.clickSaveAccountNumber()

		;

	}
}
