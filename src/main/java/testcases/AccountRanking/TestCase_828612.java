package testcases.AccountRanking;

import org.testng.annotations.Test;

import pages.AccountsPage;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_828612:_828612:Verify whether Account Ranking Field is  Editable/Not Locked for Limited Member+ Innovatix  Supervisor.

public class TestCase_828612 {


	@Test
	public void verifyAccountRanking(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{
		//1. Login to CRM using member supervisor / member credentials
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

		//3. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Click on save 			
		.clickSave() 

		.navigateToRecordStatus()
		.navigateToAccountRanking()

		.verifyAccountRankingEnabled()
		.verifyAccountRankingDropdown()
		.selectccountRankingDropdown(DataInputProvider.getCellData_ColName(iRowNumber, "AccountRank", sDataSheetName))
		.verifyAccountRankingisNotMasked()
		.clickSave()

		.chooseMemberEntryForm()
		//3. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Click on save 			
		.clickSave() 

		.navigateToRecordStatusMEF()
		.navigateToAccountRanking()

		.verifyAccountRankingEnabled()
		.verifyAccountRankingDropdown()
		.selectccountRankingDropdown(DataInputProvider.getCellData_ColName(iRowNumber, "AccountRank", sDataSheetName))
		.verifyAccountRankingisNotMasked()
		.clickSave()
;

		new AccountsPage()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.chooseMemberForm()

		.navigateToRecordStatus()
		.navigateToAccountRanking()

		.verifyAccountRankingEnabled()
		.verifyAccountRankingDropdown()
		.selectccountRankingDropdown(DataInputProvider.getCellData_ColName(iRowNumber, "AccountRank", sDataSheetName))
		.clickSave()
		;
		
		new AccountsPage()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.chooseMemberEntryForm()

		.navigateToRecordStatusMEF()
		.navigateToAccountRanking()

		.verifyAccountRankingEnabled()
		.verifyAccountRankingDropdown()
		.selectccountRankingDropdown(DataInputProvider.getCellData_ColName(iRowNumber, "AccountRank", sDataSheetName))
		.clickSave()
		;
		;
	}

}
