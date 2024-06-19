package testcases.SecurityMemberSupervisor;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1030852:_1030852:Verify whether Member Supervisor able to add and update Membership Category in existing  Member account


public class TestCase_1030852 {


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

		

		.createMembershipIfNotPresent(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))		//Click New Membership Category
		
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
