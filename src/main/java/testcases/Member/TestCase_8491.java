package testcases.Member;

import org.testng.annotations.Test;

import pages.AccountsPage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_8491:_828589:Verify whether "Cater" is available in the "Participation Type" Field in Member Form and Member Entry Form.


public class TestCase_8491 {

	@Test
	public void verifyParticipationType(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.clickNewOnAccountsPage()
		.chooseMemberForm()

		//Verify Participation TYpe Drop down in member form
		.navigateToDoNotVerify()
		.verifyParticipationTypeOption(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))

		.clickSystemTab()
		.clickGeneralTab()
		.chooseMemberEntryForm()

		//Verify Participation TYpe Drop down in member entry form
		.navigateToDoNotVerifyMEF()
		.verifyParticipationTypeOption(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))


		;

		new DashboardPage()
		.selectAccountsTab()
		.clickOnDiscardChanges()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.chooseMemberForm()
		//Verify Participation TYpe Drop down in member form
		.navigateToDoNotVerify()
		.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))

		;
	}
}
