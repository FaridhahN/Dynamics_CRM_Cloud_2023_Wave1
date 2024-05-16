package testcases.ProgramMembership;

import org.testng.annotations.Test;

import pages.AccountsPage;
import pages.DashboardPage;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_11007:_827924:Verify Program membership is inherited when dp change on a terminated account

public class TestCase_11007 {


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
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		////Navigate to Membership
		.selectMembership()

		//SEarch for SURPASS
		.searchmemberShip("SURPASS Core")

		//navigate to General Tab
		.clickGeneralTab()
		
		//Get Premier End Date
		.getThePremierEndDate()
		//Change the DP
		.navigateToDP()
		

		//change DP
		.clearDP()

		//update DP
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//Enter DPR
		.selectDirectParentRelationManaged()

		//Enter DPRD
		.selectDirectParentRelationDate(TestUtils.date)

		//Direct parent Reason
		.typeDPReason("Test")

		//Enter TDPRD
		.selectTopParentRelation("Affiliate")

		//Enter the TPDRD
		.selectTopParentRelationDate(TestUtils.date)

		//TP Reason
		.typeTPReason("Test")

		//click save
		.clickSave()

		//Navigate to Membership
		.selectMembership()

		//SEarch for SURPASS
		.searchmemberShip("SURPASS Core")

		.verifyMemebershipisEndDated("SURPASS Core")

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
		.selectDirectParentRelationDate(TestUtils.date)

		//Direct parent Reason
		.typeDPReason("Test")

		//Enter TDPRD
		.selectTopParentRelation("Affiliate")

		//Enter the TPDRD
		.selectTopParentRelationDate(TestUtils.date)

		//TP Reason
		.typeTPReason("Test")


		//click save
		.clickSave()



		;


	}

}
