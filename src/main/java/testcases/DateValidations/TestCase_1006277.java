package testcases.DateValidations;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1006277:_1006277:Verify whether user able to update Direct Parent Relation Date < or <= Termination Date. 

public class TestCase_1006277 {


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
		.navigateToDP()
		.getDprd()
		.chooseRecordStatusDraftfromTop()
		.clickSave()
		.selectRelatedMembership()
		.doubleClickMembership(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		
		.getFutureDate(TestUtils.date, 2)

		.typeMembershipEndDate(TestUtils.enddate)
		.selectMembershipEndReason(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndReason", sDataSheetName))
		.clickMembershipSaveAndClose()
		.chooseRecordStatusPublished()
		.clickSave()
		.verifyErrorisNotDisplayed()
		.verifyAccountStatus("Terminated")
		.CompareStartDateandDPRD()
		//Date reset

		//Change to draft
		.chooseRecordStatusDraft()
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
		.typeLineOfBusinessStartDate(TestUtils.getPastDate(TestUtils.todaysDate(), 2))

		// Click on LOB Save 
		//.clickLOBSaveAndClose()
		.clickLOBSaveAndCloseDev()
		//Click add new membership
		.clickMembershipAndAddNewMembership()

		// Choose Membership type 
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		//Provide any start date and click on save
		.typeMembershipStartDate(TestUtils.getPastDate(TestUtils.todaysDate(), 2))

		//Click on membership save and close
		.clickQuickCreateMembershipSaveAndClose()
		.chooseRecordStatusPublished()
		.clickSave()

		;
	}
}
