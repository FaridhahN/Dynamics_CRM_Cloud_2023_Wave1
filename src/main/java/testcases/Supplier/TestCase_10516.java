package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_10516:Cloud -Replace existing primary contact with new one on a supplier


public class TestCase_10516 {


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
		
		//Clear Co pilot suggestions
		
		.clearAllSuggestions()

		.navigateToTPRD()
		//verify Existing Primary Contact value 
		.verifyPrimaryContactValue(DataInputProvider.getCellData_ColName(iRowNumber,"verifyPrimaryContactValue",sDataSheetName))


		//Add New Primary Contact on a Supplier Form -commenting due to data reset issue -Inactive CAA -Bug 10528

		//	.replaceSupplierPrimaryContact(DataInputProvider.getCellData_ColName(iRowNumber, "replaceSupplierPrimaryContact"+ "",sDataSheetName),(DataInputProvider.getCellData_ColName(iRowNumber, "verifyReplacedPrimaryContactValue"+ "",sDataSheetName)))

		//Go to Contact Account Associations

		.selectCAA()

		//Select the Old Primary Contact's Association
		.chooseOldPrimaryContactCAA(DataInputProvider.getCellData_ColName(iRowNumber,"addSupplierPrimaryContact",sDataSheetName))


		.verifyNullinCaaRelationshipEndDate(DataInputProvider.getCellData_ColName(iRowNumber,"contactRelationshipEndDate",sDataSheetName))

		;



		//				
		//				//Data Reset -Deactivate the created Primary Contact
		//				.selectCAA()
		//				.clickOnCAAandDeactivate()
		//				.clickConfirmCAADeactivation();
		//				
	}
}
