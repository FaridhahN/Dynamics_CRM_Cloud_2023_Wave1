package testcases.AccountNumbers;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10707 Cloud:Verify whether DEA digit check- accepts only 9 digits


public class TestCase_10707 {

	@Test
	public void verifyDEA9digitsCheckAccountNumberEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		//3.Verify  DEA accepts only numerics in the last 7 digits

		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeDEA()
		.typeStaticDEA(DataInputProvider.getCellData_ColName(iRowNumber, "DEANumber", sDataSheetName))
		.typeStartDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()
		.clickGoBackOnMemberForm()
		.clickOnDiscardChanges();


		//Data Reset	-Not Required



	}
}
