package testcases.MembershipCategories;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1033269:_1033269_Verify the membership category End date can not be later than the Membership End date


public class TestCase_1033269 {


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
		.clickQuickCreateMembershipSave()

		//Click New Membership Category
		.clickNewMembershipCategory()

		//Select New MEmbership Category
		.enterNewMembershipCategory(DataInputProvider.getCellData_ColName(iRowNumber, "membershipCategory", sDataSheetName))

		//Enter the start date
		.typeMembershipCategoryStratDate(TestUtils.todaysDate())

		//Click Save and Close
		.clickSaveAndCloseinMembershipCategory()

		//Verify Error message is not Displayed
		.verifyErrorisNotDisplayed()

		.clickSelectAllCheckBox()

		//Click Membership Category Edit Button 
		.clickEditButtoninCategory()

		//Enter the Membership category End Date
		.typeMembershipCategoryEndDate(TestUtils.FutureEndDate(4))

		//Click Save and Close
		.clickSaveAndCloseMembershipCategory()

		//4. On Membership Entity, Verify by End dating any Premier Membership's in the taken Published Account
		//Any future date
		.typeMembershipEndDate(TestUtils.todaysDate())

		// End reason = Anything from dropdown,
		.selectMembershipEndReason("Change")

		.clickMembershipSave()

		.verifyError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))

		//Any future date
		.typeMembershipEndDate(TestUtils.FutureEndDate(4))

		.clickMembershipSave()

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