package testcases.ExcludeFromRoster;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//Test Case 11372:Cloud: Verify Whether "Exclude from Roster" field is defaulted to 'No' for Member Entry Form.


public class TestCase_11372 {


	@Test
	public void ExcludeRoster(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{

		new WebDriverServiceImpl();
		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for  
		.selectAccountsTab()

		//4. Click on Add new account 
		.clickNewOnAccountsPage()
		.chooseMemberEntryForm()
		.clickAdditionalCriteria()
		.navigateTorosterFromAppDate()
		.excludeFromRoster()

		;

	}

}
