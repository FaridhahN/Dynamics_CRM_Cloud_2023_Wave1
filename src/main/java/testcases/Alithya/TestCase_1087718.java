package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087718:_1087718:Verify mentioned fields are pulled from account record on to opportunity form for Channel Partner Senior Director


public class TestCase_1087718 {


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
		.enterTopic(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName))
		.selectGutFeel(DataInputProvider.getCellData_ColName(iRowNumber, "gutFeel", sDataSheetName))
		.navigateToMemberField()
		.selectMember(DataInputProvider.getCellData_ColName(iRowNumber, "member", sDataSheetName))
		.verifyMemberDetails()
		;					
	}
}
