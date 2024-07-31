package testcases.AlithyaCPRD;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087705:_1087705:Verify Gut Feel is available for the Channel Partner Regional Director


public class TestCase_1087705 {


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
		.changeViewinOppurtunityPage("CP - Open Opportunities")
		.verifyGutFeelOptioninView()
		.ClickNewOppurtunity()
		.enterTopic(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName))
		.verifyGutFeel()
		.selectGutFeel(DataInputProvider.getCellData_ColName(iRowNumber, "gutFeel", sDataSheetName))
		.clickSaveinOpportunities()
		.verifyErrorisNotDisplayed()
		;					
	}
}
