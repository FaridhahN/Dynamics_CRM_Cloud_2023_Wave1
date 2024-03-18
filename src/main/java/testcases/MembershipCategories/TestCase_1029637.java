package testcases.MembershipCategories;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1029637:_1029637_Verify whether user should not be able to add Membership Categories for the Membership other than Premier Promise Program.

public class TestCase_1029637 {


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
		.verifyErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))


		//Clcik Cancel in Quick Create
		.clickCancelinMembershipCategory()

		.clickOnDiscardChanges()
		
		//Click add new membership
				.clickMembershipSaveAndCloseButton()
		.doubleClickMembership(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
		.clickDeactivateButton()	
		
		.clickGoBackButton()
		.clickGeneralTab()
		.chooseRecordStatusPublished()
		.clickSave()





		;
	}
}
