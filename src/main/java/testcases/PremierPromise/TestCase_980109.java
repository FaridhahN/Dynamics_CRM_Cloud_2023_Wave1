package testcases.PremierPromise;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_980109:_980109_Verify whether user able to add Program - 'Premier Promise' in existing 'Member' account.


public class TestCase_980109 {


	@Test
	public void premierPromise(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {



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

		//Change the status to the draft
		.chooseRecordStatusDraftfromTop()
		.clickSave()

		

		//Click add new membership
		.clickMembershipAndAddNewMembership()

		// Choose Membership type 
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		//Provide any start date and click on save
		.typeMembershipStartDate(TestUtils.todaysDate())

		//Click on membership save and close
		.clickQuickCreateMembershipSave()
		
		//Verify Error message is not displayed
		.verifyErrorisNotDisplayed()
		
		//Data reset
		//4. On Membership Entity, Verify by End dating any Premier Membership's in the taken Published Account
		//Any future date
		.typeMembershipEndDate(TestUtils.todaysDate())

		// End reason = Anything from dropdown,
		.selectMembershipEndReason("Change")

		// then save and deactivate the membership
				.clickMembershipSave()
				.clickDeactivateButton()	
				.clickGoBackButton()
				.clickGeneralTab()
				.chooseRecordStatusPublished()
				.clickSave()





		;
	}
}
