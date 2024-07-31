package testcases.AlithyaCPRM;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1087650:_1087650:Verify whether Task due for today is available in Assistant -lightbulb button for the user Channel Partner Regional Manager.

public class TestCase_1087650 {


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

		//save opportunities
		.clickSaveinOpportunities()

		//navigate to Acitvities
		.selectRelatedActivities()

		//click new Email activity
		.clickNewTaskActivity()
		
		//enter the email details
		.EnterTaskDetails(DataInputProvider.getCellData_ColName(iRowNumber, "subject", sDataSheetName),  TestUtils.todaysDate(),DataInputProvider.getCellData_ColName(iRowNumber, "time", sDataSheetName))
		
		.clickSaveinOpportunities()
		
		.clickgoBack()
		
		//verify in the ligh bulp
		.clicklightassistantBulp()
		
		.verifyOppurtunityinLightAssistant(DataInputProvider.getCellData_ColName(iRowNumber, "subject", sDataSheetName))
		;					
	}
}
