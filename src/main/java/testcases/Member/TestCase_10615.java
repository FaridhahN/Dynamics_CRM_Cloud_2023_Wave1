package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10615:Cloud : Verify whether Base Read only role cannot create "Member" Account.

public class TestCase_10615 {


	@Test
	public void verifyBaseReadonlyMemberAccount(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Base-Read Only User
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()
		
		
		//3.Verify +New Button on AccountsTab for Base Read Only User 

		.verifyNewButtonIsNotPresent()
		
		
		//4.Logout
		.clickSignout();
		
		
		//Data Reset-Not Required


	}
}
