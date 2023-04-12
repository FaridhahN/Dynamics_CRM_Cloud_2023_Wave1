package testcases.ExcludeFromRoaster;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//Test Case 11375:Cloud: Verify for a Direct Parent which as Child accounts ,whose "Exclude from Roster" can not be changed from No to Yes


public class TestCase_11375 {


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

		//Search GPO Active Member with Child
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromSearchResults()

		.navigateToroster()
		.setExcludeFromRoster("Yes")
		//Click on save 
		.clickSave() 
		.verifyError(DataInputProvider.getCellData_ColName(iRowNumber, "Error", sDataSheetName))
		;



	}

}
