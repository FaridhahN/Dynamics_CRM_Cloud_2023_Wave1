package testcases.ProgramMembership;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_11039:_827931: Verify Program membership is inherited to existing prospect accounts
import utils.TestUtils;


public class TestCase_11039 {


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

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Check whether SURPASS is End Dated
		.selectMembership()

		//Search for Surpass
		.searchinMemberShip("SURPASS Core")

		//End Date Surpass
		.endDateandDeleteMembership("SURPASS Core")

		.selectSubaccount()

		//4. Click on Add new account 
		.clickNewAccountInSubAccount()

		//6. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		//Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( TestUtils.todaysDate())

		//type TP reason
		.typeTPReason("Change")

		//Click on save 
		.clickSave() 


		//7. Verify CRM Account # is generated 
		.verifyCRMNumberIsDisplayed()


		;

		new DashboardPage()

		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//change to Draft
		.chooseRecordStatusDraftfromTop()

		//click save button
		.clickSave()

		//Add a new membership
		//Navigate to membership page
		//Click add new membership
		.clickMembershipAndAddNewMembership()

		// Choose Membership type 
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType1", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider1", sDataSheetName))

		//Provide any start date and click on save
		.typeMembershipStartDate(TestUtils.todaysDate())

		//Click on membership save and close
		.clickQuickCreateMembershipSaveAndClose()

		.chooseRecordStatusPublished()

		.clickSave()

		;

		new DashboardPage()

		.selectAccountsTab()
		.searchAccount(WebDriverServiceImpl.CRMNumber)

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(WebDriverServiceImpl.CRMNumber)

		//Verify Surpass membership is displayed
		.selectMembership()
		.verifyMembership(false, "SURPASS Core");




	}
}
