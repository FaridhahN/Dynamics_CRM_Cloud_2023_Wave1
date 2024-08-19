package testcases.AlithyaCPSD;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1087579:_1087579:Verify whether following fields : "Topic, Estimated Closed Date, Channel Partner Revenue Category ,Projected NAF" are editable in Opportunity form for the Channel Partner Senior Director.
 
public class TestCase_1087579 {


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
		.navigateToMemberField()
		.selectMember(DataInputProvider.getCellData_ColName(iRowNumber, "member", sDataSheetName))
		.clickSaveinOpportunities()
		.verifyErrorisNotDisplayed()
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
