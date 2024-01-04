package testcases.DateValidations;


import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_5112:_828403:Verify date restriction works when reactivating a terminated account

public class TestCase_5112{

	
	@Test
	public void DateValidation(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
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
				
				.chooseRecordStatusDraftfromTop()
				.clickSave()
				.NavigateToSystemTab()
				.clickGeneralTab()
				
				//Click add new membership
				.clickMembershipAndAddNewMembership()

				// Choose Membership type 
				.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
				.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
				//Provide any start date and click on save
				.typeMembershipStartDate(TestUtils.FutureEndDate(3))

				//Click on membership save and close
				.clickQuickCreateMembershipSaveAndClose()
				
				//7.  Click the + icon on the Line of Business Grid
				.clickLineOfBusinesses()

				//Click New Line Of Business
				.clickAddNewLineOfBusiness()

				// Line of Business =General GPO
				.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

				// Classification Type = General GPO
				.selectLOBfClassificationType(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification", sDataSheetName))

				// Start Date =Today's date
				.typeLineOfBusinessStartDate(TestUtils.todaysDate())

				// Click on LOB Save 
				//.clickLOBSaveAndClose()
				.clickLOBSaveAndCloseDev()
				

				//8. Record Status = Published
				.chooseRecordStatusPublished()

				.clickSave()
				.verifyErrorisNotDisplayed()
				.NavigateToSystemTab()
				.clickGeneralTab()
				//verify DPRd and TPRD
				.checkDP_TPRD(TestUtils.FutureEndDate(3))
				.chooseRecordStatusPublished()
				.clickSave()
				
				.selectRelatedMembership()


				.doubleClickMembership(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

				.typeMembershipEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))
				.selectMembershipEndReason(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndReason", sDataSheetName))
				.clickMembershipSave()
				.clickSave()
				;
	}
}
