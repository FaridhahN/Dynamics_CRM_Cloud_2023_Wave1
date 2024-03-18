package testcases.MembershipCategories;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1033325:_1033325_Verify whether Membership Provider is renamed to Membership in Quick Create window of Membership Categories. 


public class TestCase_1033325 {


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
		
		.verifyMembershipCategoryQuickForm()
		
		//Select New MEmbership Category
		.enterNewMembershipCategory(DataInputProvider.getCellData_ColName(iRowNumber, "membershipCategory", sDataSheetName))
		
		//Enter the start date
		.typeMembershipCategoryStratDate(TestUtils.todaysDate())
		
		//Click Save and Close
		.clickSaveAndCloseinMembershipCategory()
		
		.clickSelectAllCheckBox()
		
		.clickDeactivateButtoninCategory()
		
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
