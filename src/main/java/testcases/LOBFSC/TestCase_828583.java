package testcases.LOBFSC;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 6960:Verify Food Services LOB is added automatically when Non-GPO Premier Membership is converted to GPO Premier Membership.
//Test Case 8791:Cloud - Verify when selecting any "Class of trade" , "Not Found" Error window should not get displayed.
import utils.TestUtils;


public class TestCase_828583 {
	//TFS ID_828688:_828688:Verify whether "Third Party Payee" Field is available , on selecting Third Party Level in "Reward/Rebate" Option


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
		//Navigate to LOB verify with Supplier account
		.clickLineOfBusinesses()
		.doubleClickExistingLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))
		.selectverifyrewardrebatedropdown(DataInputProvider.getCellData_ColName(iRowNumber, "rewardrebate", sDataSheetName))
		.thirdverifyPayee(DataInputProvider.getCellData_ColName(iRowNumber, "thirdPartyPayee", sDataSheetName))
		
		.clickGoBack()


		//Navigate to LOB verify with Inative account
		.clickLineOfBusinesses()
		.doubleClickExistingLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))
		.selectverifyrewardrebatedropdown(DataInputProvider.getCellData_ColName(iRowNumber, "rewardrebate", sDataSheetName))
		.thirdverifyPayee(DataInputProvider.getCellData_ColName(iRowNumber, "thirdPartyPayee1", sDataSheetName))
		
		.clickGoBack()


		//Navigate to LOB verify with Location type account
		.clickLineOfBusinesses()
		.doubleClickExistingLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))
		.selectverifyrewardrebatedropdown(DataInputProvider.getCellData_ColName(iRowNumber, "rewardrebate", sDataSheetName))
		.thirdverifyPayee(DataInputProvider.getCellData_ColName(iRowNumber, "thirdPartyPayee1", sDataSheetName))
		
		.clickGoBack()

		.chooseRecordStatusPublished()
		.clickSave()
		;
	}
}
