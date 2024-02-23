package testcases.SecurityLimitedMember;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;

//******COMPLETED******

//TFS ID_5036:_32866_cloud : On changing Address fields by Limited member should change the rec status to Needs approval

public class TestCase_5036 {

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
		.navigateToRecordStatus()
		.navigateToRecordChangeStatus()
		.verifyRecordChangeStatus("Approved")
		.clickSave()
		.clickSystemTab()
		.clickGeneralTab()

		.typeRandStreet1("717 W Johnson St")
		.clickSave()
		.navigateToRecordStatus()
		.navigateToRecordChangeStatus()
		.verifyRecordChangeStatus("Needs Approval")
		.clickLogout()
		.refreshPageChooseSignInUser()
		;


		//Data Reset

		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.navigateToRecordStatus()
		.navigateToRecordChangeStatus()
		.changeRecordStatusoption("Approved")
		.clickSave()
		.verifyRecordChangeStatus("Approved")
		;
	}
}
