package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8799:Cloud - Verify whether Lengthy Account Name giving error while creating account number

public class TestCase_8799 {


	@Test
	public void verifyAccountName(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()

		.clickNewOnAccountsPage()
		.chooseMemberForm()

		//3. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Click on save 			
		.clickSave() 

		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeDEA()
		.typeAccountNumberDEA()
		.clickSaveInAccountNumbersEntity()
		.verifyCaclculatedName("DEA")
		.clickGoBackButton()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeHIN()
		.typeAccountNumberHIN()
		.clickSaveInAccountNumbersEntity()
		.verifyCaclculatedName("HIN")
		
		;
	}
}
