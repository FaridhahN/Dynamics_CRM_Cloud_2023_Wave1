package testcases.MemberEntryForm;

import org.testng.annotations.Test;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_964305:_964305:Automation Verify whether Member attribute grid is found below FBO Info in Member Entry Form.


public class TestCase_964305 {


	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
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
		.navigateToDoNotVerifyMemberEntryForm()
		.navigatetoAdditionaCriteriaFromDonotVerify()	
		.verifyMemberAttributeUnderFBO()
		;

	}

}
