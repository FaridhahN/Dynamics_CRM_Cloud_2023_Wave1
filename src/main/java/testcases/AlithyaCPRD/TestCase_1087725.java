package testcases.AlithyaCPRD;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087725:_1087725:Verify 'Channel Partner Accounts Created in Last 7 Days' view is available for Channel Partner Regional Director


public class TestCase_1087725 {


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
