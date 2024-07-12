package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1037384_1037384:Verify "My Sales Team's Prospects" view is available in Account System View

public class TestCase_1037384 {


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

		//Navigate to Accounts page and select My Accounts - sales view
		//Navigate to the opportunity  page
		.clickAccounts()
		.changeViewinAccountsPage("My Sales Team's Prospects")

		//Verify columns in contact view
		.verifyAccountviewColumn()
		.clikCancelButton()
		.clickAddColumn()

		//Add Owner column
		.addColumnInTheView("Owner")

		.addColumnInTheView("Account Type")
		.clickApplyinEditColumn()
		.verifyOwnerDate(DataInputProvider.getCellData_ColName(iRowNumber, "user", sDataSheetName))
		.verifyOwnerDate("Prospect")

		;					
	}
}
