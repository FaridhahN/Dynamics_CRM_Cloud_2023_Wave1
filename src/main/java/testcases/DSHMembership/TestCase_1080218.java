package testcases.DSHMembership;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_1080218:_1080218:Verify DSH is created Child shipto  


public class TestCase_1080218 {


	@Test
	public void createMember(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()


		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		//Choose Draft
		.chooseRecordStatusDraftfromTop()

		//Click Save
		.clickSave()

		//Click add new membership
		.clickMembershipAndAddNewMembership()

		// Choose Membership type 
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		//Provide any start date and click on save
		.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

		//Click on membership save and close
		.clickQuickCreateMembershipSaveAndClose()

		.clickSave()
		//navigate to membership
		.selectMembership()


		//10.Go to membership and Open the Premier National membership ***** Premier National membership should be opened 

		.searchmemberShip(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
		.doubleClickMembership(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		//11.Provide end date = Any future date **** Account should be saved successfully 
		.typeMembershipEndDate(TestUtils.todaysDate())

		// End reason = Anything from dropdown,
		.selectMembershipEndReason("Change")

		// then save
		.clickMembershipSave()

		.clickDeactivateButton()

		//verify no error message
		.verifyErrorisNotDisplayed()

		.clickGoBackButton()
		
		.clickGeneralTab()
		
		.chooseRecordStatusPublished()
		
		.clickSave()
		//Data reset

		;


	}
}
