package testcases.Membership;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_980325:_980325:Verify whether user able to Deactivate a Program - 'Premier Promise'.



public class TestCase_980325 {


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
		.clickGeneralTab()

		.chooseRecordStatusPublished()
		.clickSave()


		//Change the status to the draft
		.chooseRecordStatusDraftfromTop()
		.clickSave()

		.selectMembership()


		.doubleClickMembership(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
		.clickMembershipDeactivateButton()
		.clickMembershipDeactivateButtonConfirm()
		.clickGoBackButton()
		.clickSave()
		.clickGeneralTab()

		.chooseRecordStatusPublished()
		.clickSave()

		;
	}
}
