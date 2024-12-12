package testcases.AccountNumbers;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_10746:Cloud:Verify whether user should not allow Duplicate HIN in an account.

public class TestCase_10746 {


	@Test
	public void verifyDupHINOnAccountNumberEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		//3.Verify Duplicate HIN

		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeHIN()
		.typeStaticHIN(DataInputProvider.getCellData_ColName(iRowNumber, "HIN", sDataSheetName))
		.typeStartDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()
		.clickGoBackOnMemberForm()
		.clickOnDiscardChanges();

		//Data Reset	-Not Required



	}
}
