package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_8601:_828598:Verify whether "Provider Select: MD" is removed from Participation Type in Member and Member Entry Forms.

public class TestCase_8601 {

	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		.verifyParticipationType()
		
		.clickSystemTab()
		.clickGeneralTab()
		.chooseMemberEntryForm()
		
		//Verify Participation TYpe Drop down in member entry form
		.navigateToDoNotVerifyMEF()
		.verifyParticipationType()
		
		;
	}
}
