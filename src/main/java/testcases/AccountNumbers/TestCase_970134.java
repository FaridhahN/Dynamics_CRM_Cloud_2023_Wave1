package testcases.AccountNumbers;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_970134 Verify User 'Number' field value is mandatory for creating HIN Account Number


public class TestCase_970134 {


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

		//3. Navigate to the Account number Tab

		.selectAccountNumbers()
		.getAccountName()
		
		//Create New DEA
		.clickAddNewAccountNumberInAccountNumbers()
		.verifyAccountName()
		.chooseAccountNumberTypeHIN()
		.clickSaveAccountNumber()
		
		//Verify DEA number is mandatory
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()

		.typeAccountNumberHIN()
		.clickSaveInAccountNumbersEntity()
		.clickGoBack()
		.clickGeneralTab()

		//Data Reset
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberHIN()
		.clickDeactivateInAccountNumbers()
		.clickGoBackOnMemberForm()
		.clickGeneralTab()
		.verifyHINIsNull()

		;


	}
}
