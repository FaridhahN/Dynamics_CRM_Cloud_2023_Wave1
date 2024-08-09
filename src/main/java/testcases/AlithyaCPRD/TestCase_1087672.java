package testcases.AlithyaCPRD;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087672:_1087672:Verify Channel Partner Regional Director can Assign his/her Opportunity to another user


public class TestCase_1087672 {


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
		.ClickNewOppurtunity()
		.enterTopic(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName))
		
		.navigateToMemberField()
		.selectMember(DataInputProvider.getCellData_ColName(iRowNumber, "member", sDataSheetName))
		
		.clickSaveinOpportunities()
		.assignToUser(false, DataInputProvider.getCellData_ColName(iRowNumber, "user", sDataSheetName))
		.verifygeneralErrormessageisNotDisplayed()
		.assignToUser(false, DataInputProvider.getCellData_ColName(iRowNumber, "user1", sDataSheetName))
		.verifygeneralErrormessageisNotDisplayed()
		
		;					
	}
}
