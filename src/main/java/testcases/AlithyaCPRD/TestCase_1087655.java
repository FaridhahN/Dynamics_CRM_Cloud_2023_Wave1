package testcases.AlithyaCPRD;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1087655:_1087655:Verify Status Reason Options for Close as Won and Close as Lost opportunities -Channel Partner Regional Director


public class TestCase_1087655 {


	@Test
	public void Oppurtunities(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.staysignedinforOtherApp()

		//Navigate to the Sales hub
		.selectSalesHubAccount()
		
		//Navigate to the opportunity  page
		.clickOppurtunitiesPage()
		
		//Click new opportunity button
		.ClickNewOppurtunity()
		
		//Enter the topic
		.enterTopic(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName)+TestUtils.todaysDatewithTime())
		
		//Select the gut feel
		.selectGutFeel(DataInputProvider.getCellData_ColName(iRowNumber, "gutFeel", sDataSheetName))
		
		//Type the est close date
		.typeEstimatedCloseDate(TestUtils.FutureEndDate(5))
		
		//type anticipated purchase date
		.typeanticipatedPurchaseStarDate(TestUtils.todaysDate())
		
		//Select revenuew
		.selectRevenueCategory(DataInputProvider.getCellData_ColName(iRowNumber, "category", sDataSheetName))
		
		//type the projected NAF
		.typeProjectedNAF(DataInputProvider.getCellData_ColName(iRowNumber, "projectedNAF", sDataSheetName))
		
		.navigateToMemberField()
		.selectMember(DataInputProvider.getCellData_ColName(iRowNumber, "member", sDataSheetName))
		
		//save opportunities
		.clickSaveinOpportunities()
		
		//click close as won button
		.clickAsWon()
		
		//Verify the status reason 
		.verifyWonCloseOppurtunityStatusReason()
		
		//click cancel button
		.clickCancelinCloseOpportunity()
		
		//click close as lost button
		.clickAsLost()
		
		//Verify the status reason 
		.verifyLostCloseOppurtunityStatusReason()
		;					
	}
}
