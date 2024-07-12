package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1037382_1037382:Verify "My Accounts - Sales" view is available in Account System View

public class TestCase_1037382 {


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
				
				.changeViewinAccountsPage("My Accounts - Sales")

		//Verify columns in contact view
		.verifyAccountviewColumn()
		.clikCancelButton()
		.clickAddColumn()
		
		//Add Owner column
		.addColumnInTheView("Owner")
		.clickApplyinEditColumn()
		.verifyOwnerDate(DataInputProvider.getCellData_ColName(iRowNumber, "user", sDataSheetName))
		
		;					
	}
}
