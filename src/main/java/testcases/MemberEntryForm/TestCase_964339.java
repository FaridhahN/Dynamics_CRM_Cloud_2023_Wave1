package testcases.MemberEntryForm;

import org.testng.annotations.Test;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_964339:_964339:Verify whether 'Additional Criteria' and Fields inside 'Miscellaneous' Tab  are available in the 'General' Tab of  the Member Entry Form.


public class TestCase_964339 {


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
		.verifyFeildsunderAdditionalCriteria()
		.verifyMemberAttributeUnderFBO()
		.verifyWidgetunderMembership()
		.navigatetNYInformation()
		;

	}

}
