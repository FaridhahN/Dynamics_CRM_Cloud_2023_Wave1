package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087740:_1087740:Verify mentioned Contact Views are available for Channel Partner Senior Director

public class TestCase_1087740 {


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
		
		.clickAccounts()
		.changeViewinAccountsPage("Channel Partner Sales Accounts Created in Last 7 Days")
		.clickAddColumn()
		.addColumnInTheView("Account Type")
		.addColumnInTheView("Is Sponsor")
		.addColumnInTheView("Created On")
		.clickApplyinEditColumn()
		.verifyOptionsInAccountsView()
		
		;					
	}
}
