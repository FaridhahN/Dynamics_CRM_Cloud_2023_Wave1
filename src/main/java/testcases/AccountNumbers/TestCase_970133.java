package testcases.AccountNumbers;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_970133:_970133_Cloud: Verify User 'Number' field value is mandatory for creating DEA Account Number


public class TestCase_970133 {


	@Test
	public void verifyAccountNumberNumberFeild(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		.chooseAccountNumberTypeDEA()
		.clickSaveAccountNumber()
		
		//Verify DEA number is mandatory
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()

		.typeAccountNumberDEA()
		.clickSaveInAccountNumbersEntity()
		.clickGoBack()
		.clickGeneralTab()

		//Data Reset
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberDEA()
		.clickDeactivateInAccountNumbers()
		.clickGoBackOnMemberForm()
		.clickGeneralTab()
		.verifyDEAIsNull();

		;


	}
}
