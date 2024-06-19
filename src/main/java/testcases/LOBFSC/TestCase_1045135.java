package testcases.LOBFSC;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 6960:Verify Food Services LOB is added automatically when Non-GPO Premier Membership is converted to GPO Premier Membership.
//Test Case 8791:Cloud - Verify when selecting any "Class of trade" , "Not Found" Error window should not get displayed.
import utils.TestUtils;


public class TestCase_1045135 {
	//TFS ID_1045135:_1045135:Verify whether 'All underlying level' option is available in 'Reward/Rebate' option in Line of Business in existing Member Account. 


	@Test
	public void verifynewRewardrebate(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()
		//Search the account
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.chooseRecordStatusDraftfromTop()
		.clickSave()
		//Navigate to LOB
		.clickLineOfBusinesses()
		.doubleClickExistingLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))
		.verifyrewardrebatedropdown()
		.selectverifyrewardrebatedropdown(DataInputProvider.getCellData_ColName(iRowNumber, "rewardrebate", sDataSheetName))
		.selectverifyrewardrebatedropdown(DataInputProvider.getCellData_ColName(iRowNumber, "rewardrebate1", sDataSheetName))
		.selectverifyrewardrebatedropdown("--Select--")	
		.clickLOBSaveAndCloseDev()

		.chooseRecordStatusPublished()
		.clickSave()
		;
	}
}
