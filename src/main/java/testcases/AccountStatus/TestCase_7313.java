package testcases.AccountStatus;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_7312: Add and update Primary contact to a Member account

public class TestCase_7313 {

	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Limited member 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Access Workplace> Accounts >+New
		.selectAccountsTab()
		.clickNewOnAccountsPage()
		.chooseMemberForm()


		//3. Verify Account status field value at the top
		.defaultAccountStatusHeader()



		//4. Verify Account status field in the form

		.verifyDefaultAccountStatus()

		//5. Change the form view to Member entry form
		.chooseMemberEntryForm()

		//6. Verify Account status field value at the top 
		.defaultAccountStatusHeader()

		//7. Verify Account status field in the form

		.verifyDefaultAccountStatusinMEF()
		;

	}
}