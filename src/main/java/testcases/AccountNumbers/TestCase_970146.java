package testcases.AccountNumbers;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_970146:_970146_Verify DEA account number's end date restriction.


public class TestCase_970146 {


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

		//3.Verify  DEA End Date cannot be Today or Future Date

		.selectAccountNumbers()
		.doubleClickExistingAccountNumberDEA()
		.typeEndDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()
		.typeFutureEndDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()
		.typePastEndDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.clickDeactivateInAccountNumbers()
		.clickGoBackButton()


		//Data Reset	-Not Required

		//Create New DEA
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeDEA()

		//Verify DEA number is mandatory

		.typeAccountNumberDEA()
		.clickSaveInAccountNumbersEntity()
		;	
	}
}
