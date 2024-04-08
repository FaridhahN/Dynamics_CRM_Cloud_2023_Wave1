package testcases.Supplier;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_6064 :_30838_:Cloud: #Members field should be available in Member Form and removed from Supplier Form


public class TestCase_6064 {


	@Test
	public void replacePrimaryContactOnSupplier(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()


		//Select Accounts Entity
		.selectAccountsTab()

		//Search Existing Account using CRM#
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))

		//Choose the desired account from the search results
		.selectSupplierAccountFromSearchResults()

		.navigateToDoNotVerify()
		.navigateToCRM()
		
		//Verify Member # is not displayed
		.verifyMemberisnotDisplayed()
		
		
		
	;
		//Verify Member # is not displayed for new form
		new DashboardPage()
		//Select Accounts Entity
		.selectAccountsTab()
		
		//Click on +New ( goes to Accounts Page)
		.clickNewOnAccountsPage()
		
		//Choose 'Supplier Form' Option
		.chooseSupplierForm()
		
		.navigateToDoNotVerify()
		.navigateToCRM()
		
		//Verify Member # is not displayed
		.verifyMemberisnotDisplayed()
		
		;

	}
}
