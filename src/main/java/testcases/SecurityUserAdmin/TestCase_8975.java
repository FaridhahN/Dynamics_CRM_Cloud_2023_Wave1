package testcases.SecurityUserAdmin;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_8975:_32860_Cloud - Verify Power Account field is editable for User admin



public class TestCase_8975 {


	@Test
	public void Datevalidation(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {



		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()
		//Search the account
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

//Navigate to NY infromation
		.navigateToDoNotVerify()
		.navigatetNYInformation()
		.navigatetoNAICS()
		.naivagateToReferredByFromNACS()
		.selectPowerAccount("Yes")
		.clickSave()
		.verifyErrorisNotDisplayed()
		
		.selectPowerAccount("No")
		.clickSave()
		.verifyErrorisNotDisplayed()
		;
	}
}
