package testcases.AccountRanking;

import org.testng.annotations.Test;

import pages.AccountsPage;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_828608:_828608:Verify whether Account Ranking Field is Non Editable/Locked/Not Masked for Limited Member.

public class TestCase_828608 {


	@Test
	public void verifyAccountRanking(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{
		//1. Login to CRM using member supervisor / member credentials
		WebDriverServiceImpl.isMemberForm=true;
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()
		
		.clickNewOnAccountsPage()
		.chooseMemberForm()

		.navigateToRecordStatus()
		.navigateToAccountRanking()
		.verifyAccountRankingDisabled()
		;
		new AccountsPage()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.chooseMemberForm()

		.navigateToRecordStatus()
		.navigateToAccountRanking()

		.verifyAccountRankingDisabled()
		.verifyAccountRankingisNotMasked()
		;

	}

}
