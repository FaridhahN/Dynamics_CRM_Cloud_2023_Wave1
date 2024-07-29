package testcases.AlithyaCPRM;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TF ID_1086032:_1086032:Verify mentioned Account Views are removed from Sales Hub for Channel Partner Regional Manager



public class TestCase_1087738 {


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
