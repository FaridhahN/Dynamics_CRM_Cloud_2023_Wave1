package testcases.AccountNumbersFedID;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_822001:_822001:Verify whether Error msg is populated on Provide Federal tax id more than 9 chars if country is USA.

public class TestCase_822001 {


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
		
		//Create New FED ID
		.clickAddNewAccountNumberInAccountNumbers()
		.verifyAccountName()
		.chooseAccountNumberTypeFedTaxID()

		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
		.typeStartDateInAccountNumbers()

		.clickSaveAccountNumber()
		
		//Verify More than 30 characters is not allowed in the FED ID
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()

		
		;


	}
}
