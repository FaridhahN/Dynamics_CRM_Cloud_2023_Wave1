package testcases.AlithyaCPRM;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1087578_1087578: Verify whether following fields : "Topic, Estimated Closed Date, Channel Partner Revenue Category ,Projected NAF" are editable in Opportunity form for the Channel Partner Regional Manager
 
public class TestCase_1087578 {


	@Test
	public void Oppurtunities(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.staysignedinforOtherApp()

		.selectSalesHubAccount()
		.clickOppurtunitiesPage()
		.ClickNewOppurtunity()
		.enterTopic(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName)+TestUtils.todaysDatewithTime())
		.selectGutFeel(DataInputProvider.getCellData_ColName(iRowNumber, "gutFeel", sDataSheetName))
		.typeEstimatedCloseDate(TestUtils.FutureEndDate(5))
		.typeanticipatedPurchaseStarDate(TestUtils.todaysDate())
		.selectRevenueCategory(DataInputProvider.getCellData_ColName(iRowNumber, "category", sDataSheetName))
		.typeProjectedNAF(DataInputProvider.getCellData_ColName(iRowNumber, "projectedNAF", sDataSheetName))
		.clickSaveinOpportunities()
		.verifyErrorisNotDisplayed()
		.clickgoBack()
		.searchOpportunity(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName)+TestUtils.todaysDatewithTime())
		.openOpportunity(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName)+TestUtils.todaysDatewithTime())
		
		.enterTopic(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName)+"Updatd"+TestUtils.todaysDatewithTime())
		.typeEstimatedCloseDate(TestUtils.FutureEndDate(3))
		.typeanticipatedPurchaseStarDate(TestUtils.todaysDate())
		.clearRevenueCategory()
		.selectRevenueCategory(DataInputProvider.getCellData_ColName(iRowNumber, "category1", sDataSheetName))
		.typeProjectedNAF(DataInputProvider.getCellData_ColName(iRowNumber, "projectedNAF", sDataSheetName)+1)
		.clickSaveinOpportunities()
		.verifyErrorisNotDisplayed()
		
		;					
	}
}
