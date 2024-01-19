package testcases.Membership;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_980324:_980324:Verify whether user able to End date a Program - 'Premier Promise'.


public class TestCase_980324 {


	@Test
	public void Datevalidation(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {



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
		.clickQuickCreateMembershipSaveAndClose()
		.chooseRecordStatusPublished()
		.clickSave()

		.verifyErrorisNotDisplayed()

		//Change the status to the draft
		.chooseRecordStatusDraftfromTop()
		.clickSave()

		//Navigate to Membership
		.selectMembership()

		.doubleClickMembership(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		.getMembershipStartDate()
		//4. On Membership Entity, Verify by End dating any Premier Membership's in the taken Published Account
		//Any future date
		.typeMembershipEndDate(TestUtils.date)

		// End reason = Anything from dropdown,
		.selectMembershipEndReason("Change")

		// then save
		.clickMembershipSaveAndClose()
		.chooseRecordStatusPublished()
		.clickSave()





		;
	}
}
