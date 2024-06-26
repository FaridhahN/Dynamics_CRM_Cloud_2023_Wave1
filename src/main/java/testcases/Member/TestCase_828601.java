package testcases.Member;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
// TFS ID_828601:_828601:Verify on change Member to Member Entry form without any changes on the account ,Unsaved warning message should not be thrown.

public class TestCase_828601 {


	@Test
	public void verifyMemberformChanges(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{
		//1. Login to CRM using member supervisor / member credentials
		WebDriverServiceImpl.isMemberForm=true;
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
		.chooseMemberEntryForm()
		
		
		;
		new DashboardPage()
		.selectAccountsTab()
		.verifyUnsavedMEssage()
		
		;

	}

}
