package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_964247:_964247_:Verify if a “+New Contract” or “+Add Existing Contract” button should NOT be available in the Contracts Associated View for Supplier Supervisor.


public class TestCase_964247 {


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

		//Navigate to Contracts
		.clickRelatedContracts()
		
		//Verify Add New Contract button is not displayed
		.verifyAddNewButtonisNotDisplayed()
		;		//				
	}
}
