package testcases.ProgramMembership;

import org.testng.annotations.Test;

import pages.AccountsPage;
import pages.DashboardPage;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_11041:_827933:Verify Program membership is inherited when dp change within same hierarchy

public class TestCase_11042 {


	@Test
	public void verifyProgramMembership(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{

		new WebDriverServiceImpl();
		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber1", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber1", sDataSheetName))

		//Delete Surpass core if it is presetn

		//Select Membership
		.selectMembership()

		//Searchfor Surpass
		.searchinMemberShip("SURPASS Core")

		.endDateandDeleteMembership("SURPASS Core")

		;

		//Search A Account and perform DP change
		new DashboardPage()

		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Select membership
				.selectMembership()
				
				//Search MEmbership Core
				.searchinMemberShip("SURPASS Core")
				//Get MEmbership Date

				//get Membership startDAte
				.getMembershipStartDate()

				//Navigate to General Tab
				.clickGeneralTab()


		//Change the DP
		.navigateToDP()

		//change DP
		.clearDP()

		//update DP
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//Enter DPR
		.selectDirectParentRelationManaged()

		//Enter DPRD
		.selectDirectParentRelationDate(TestUtils.todaysDate())

		//Direct parent Reason
		.typeDPReason("Test")

		//Enter TDPRD
		.selectTopParentRelation("Affiliate")

		//Enter the TPDRD
		.selectTopParentRelationDate(TestUtils.todaysDate())

		//TP Reason
		.typeTPReason("Test")

		//click save
		.clickSave()

		//Navigate to Membership
		.selectMembership()

		//SEarch for SURPASS
		.searchmemberShip("SURPASS Core")

		//Verify Membership surpass is present
				.verifyMembership(true, "SURPASS Core")
				
				//Verify Membership Start Date
				.verifyMembershipStartDate(true, TestUtils.date)
				
				
		//Data Reset

		//General Tab
		.clickGeneralTab()

		.navigateToDP()

		//change DP
		.clearDP()

		//update DP
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent1", sDataSheetName))

		//Enter DPR
		.selectDirectParentRelationManaged()

		//Enter DPRD
		.selectDirectParentRelationDate(TestUtils.todaysDate())

		//Direct parent Reason
		.typeDPReason("Test")

		//Enter TDPRD
		.selectTopParentRelation("Affiliate")

		//Enter the TPDRD
		.selectTopParentRelationDate(TestUtils.todaysDate())

		//TP Reason
		.typeTPReason("Test")


		//click save
		.clickSave()



		;


	}

}
