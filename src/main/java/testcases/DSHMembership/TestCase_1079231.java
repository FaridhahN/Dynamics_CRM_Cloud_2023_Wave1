package testcases.DSHMembership;
				  
import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;
//

public class TestCase_1079231 {


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
		.chooseRecordStatusDraft()
		
		//Click Save
		.clickSave()

		
		//navigate to membership
				.selectMembership()
				
				
				//10.Go to membership and Open the Premier National membership ***** Premier National membership should be opened 
				
				.searchmemberShip(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
				.doubleClickMembership(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

				//11.Provide end date = Any future date **** Account should be saved successfully 
				.typeMembershipEndDate(TestUtils.todaysDate())

				// End reason = Anything from dropdown,
				.selectMembershipEndReason(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndReason", sDataSheetName))

				// then save
				.clickMembershipSaveAndClose()

				//navigate to membership
				.selectMembership()
				
				
				//Double click DSH membership
				.doubleClickMembershipWithEndDAte(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider1", sDataSheetName))
				
//Verify End date
				.verifyEndDate(TestUtils.todaysDate())
				
				.deactivateAllMemberships()
				
				//Data reset
				.clickSave_Close()
				
				//Click on Save 
				.clickSave() 

				//7.  Click the + icon on the Line of Business Grid
				.clickLineOfBusinesses()

				//Click New Line Of Business
				.clickAddNewLineOfBusiness()

				// Line of Business =General GPO
				.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

				// Classification Type = General GPO
				.selectLOBfClassificationType(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification", sDataSheetName))

				// Start Date =Today's date
				.typeLineOfBusinessStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusinessStartDate", sDataSheetName))

				// Click on LOB Save 
				//.clickLOBSaveAndClose()
				.clickLOBSaveAndCloseDev()
				//Click add new membership
				.clickMembershipAndAddNewMembership()

				// Choose Membership type 
				.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
				.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

				//Provide any start date and click on save
				.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

				//Click on membership save and close
				.clickQuickCreateMembershipSaveAndClose()

				//clickGeneral Tab
				.clickGeneralTab()
				
				//Click add new membership
				.clickMembershipAndAddNewMembership()

				// Choose Membership type 
				.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType1", sDataSheetName))
				.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider1", sDataSheetName))

				//Provide any start date and click on save
				.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate1", sDataSheetName))

				//Click on membership save and close
				.clickQuickCreateMembershipSaveAndClose()
				
				//navigate to membership
							;


	}
}
