package testcases.SecurityLimitedMember;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1030847:_1030847:Verify whether Limited Member role should not be able to add or update Premier Promise Membership


public class TestCase_1030847 {


	@Test
	public void premierPromise(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {



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

		
		.verifyErrorinMembershipCreation(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		



		;
	}
}
