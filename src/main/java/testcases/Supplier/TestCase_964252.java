package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_964252:_964252_:Verify Column Values are populated from the related Contracts.


public class TestCase_964252 {


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
		
		.changeTheContractView("Active Contracts")
		
		.verifyColumnHeader()
		.verifyValueinContract()
		
		
		;		//				
	}
}
