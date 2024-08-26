package testcases.AlithyaCPRM;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1087662:_1087662:Verify whether Close date due for Opportunity is available in Assistant-lightbulb button for the user Channel Partner Regional Manager.

public class TestCase_1087662 {


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
		.typeEstimatedCloseDate(TestUtils.todaysDate())
		.typeanticipatedPurchaseStarDate(TestUtils.todaysDate())
		.selectRevenueCategory(DataInputProvider.getCellData_ColName(iRowNumber, "category", sDataSheetName))
		.typeProjectedNAF(DataInputProvider.getCellData_ColName(iRowNumber, "projectedNAF", sDataSheetName))
		
		.navigateToMemberField()
		.selectMember(DataInputProvider.getCellData_ColName(iRowNumber, "member", sDataSheetName))
		
		
		.clickSaveinOpportunities()
		.clicklightassistantBulp()
		
		.verifyOppurtunityinLightAssistant(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName)+TestUtils.todaysDatewithTime())
		;					
	}
}
