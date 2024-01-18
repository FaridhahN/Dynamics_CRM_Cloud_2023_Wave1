package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_11199:_828833:Cloud: Verify Date validation happen when reactivating a terminated account

public class TestCase_11199 {


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
		.chooseRecordStatusDraftfromTop()
		.clickSave()
		.selectRelatedMembership()

		.verifyMembershipEndDateIsNull()

		.clickGeneralTab()
		.navigateToDP()
		.getDprd()

		.getFutureDate(TestUtils.date, 2)
		//7.  Click the + icon on the Line of Business Grid

		//Click add new membership
		.clickMembershipAndAddNewMembership()

		// Choose Membership type 
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		//Provide any start date and click on save
		.typeMembershipStartDate(TestUtils.enddate)

		//Click on membership save and close
		.clickQuickCreateMembershipSaveAndClose()
		.chooseRecordStatusPublished()
		.clickSave()

		.verifyError(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage2", sDataSheetName))

		.clickLineOfBusinesses()

		//Click New Line Of Business
		.clickAddNewLineOfBusiness()

		// Line of Business =General GPO
		.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

		// Classification Type = General GPO
		.selectLOBfClassificationType(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification", sDataSheetName))

		// Start Date =Today's date
		.typeLineOfBusinessStartDate(TestUtils.enddate)

		// Click on LOB Save 
		//.clickLOBSaveAndClose()
		.clickLOBSaveAndCloseDev()

		.chooseRecordStatusPublished()
		.clickSave()
		.verifyErrorisNotDisplayed()
		.NavigateToSystemTab()
		.clickGeneralTab()
		.getThePremierStartDate()
		.navigateToDP()
		.getFutureDate(TestUtils.date, 2)
		.selectDirectParentRelationDate(TestUtils.enddate)
		.clickSave()
		.verifyError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		.selectDirectParentRelationDate(TestUtils.date)
		.clickSave()
		.navigateToApplicationDate()
		.selectTopParentRelationDate(TestUtils.enddate)
		.clickSave()
		.verifyError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage1", sDataSheetName))
		.selectTopParentRelationDate(TestUtils.date)
		.clickSave()



		;
	}
}
