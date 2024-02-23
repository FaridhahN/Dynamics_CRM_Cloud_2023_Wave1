package testcases.SecurityLimitedMember;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;

//******COMPLETED******

//TFD ID_5141:_32863_Cloud: Limited Members should be able to Create Primary Contact for Prospect and Member

public class TestCase_5141 {

	@Test
	public void validateContactCAA(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		//Verify CAA is created for Prospect
		// 1. Login to CRM as member supervisor
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		.chooseMemberForm()
		.navigateToPrmiaryAccount()
		.updateMemberPrimaryContact(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))
		.clickSave()
		.verifyPrimaryAccountNameinCAA()
		.clickSave()
		.EndCAA()
		.clickSave()
		;


		//Verify CAA is created for Member

		//2. Go to Workplace >> Take Any Member Account 
		new DashboardPage()
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber1", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber1", sDataSheetName))

		.chooseMemberForm()
		.navigateToPrmiaryAccount()
		.updateMemberPrimaryContact(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))
		.clickSave()
		.verifyPrimaryAccountNameinCAA()
		.clickSave()
		.EndCAA()
		.clickSave()
		;
	}
}
