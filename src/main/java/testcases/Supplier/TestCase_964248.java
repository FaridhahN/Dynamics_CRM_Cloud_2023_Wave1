package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_10516:Cloud -Replace existing primary contact with new one on a supplier


public class TestCase_964248 {


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
		
		.openExistingContract()
		
		.verifyVendorName("Airgas, Inc.")
		;		//				
	}
}
