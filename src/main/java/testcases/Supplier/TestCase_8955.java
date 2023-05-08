package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_8955:Cloud -Update a primary contact to a supplier

public class TestCase_8955 {


	@Test
	public void updatePrimaryContactOnSupplier(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		//Add New Primary Contact on a Supplier Form

		.addNewSupplierPrimaryContact(DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName",sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName",sDataSheetName))


		//verify Primary Contact value matches as created above
		.verifyPrimaryContactValue(DataInputProvider.getCellData_ColName(iRowNumber,"verifyPrimaryContactValue",sDataSheetName))

		//Data Reset -Deactivate the created Primary Contact
		.selectCAA()
		.gridRefresh()
		.clickOnCAAandDeactivate()
		.clickConfirmCAADeactivation()
		;

	}
}
