package testcases.SecurityMemberSupervisor;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_8973:_32858: Cloud - Verify Member Supervisor only should be able to deactivate member accounts

public class TestCase_8973 {


	@Test
	public void activateMember(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{


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
		.verifyDeactivateButton()
		.clickLogout()
		.refreshPageChooseSignInUser()
		;
		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.clickDeactivate()
		
		;

		//Data Reset
		//1. Login to CRM using member supervisor / member credentials
		new DashboardPage()
		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.selectInActiveMembers()
		.searchinfilter(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectInactiveAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.verifyActivateButton()
		.ActivateAccount()
		.verifyDeactivateButtonPresent()
		.clickLogout()
		.refreshPageChooseSignInUser()

		;


	}

}
