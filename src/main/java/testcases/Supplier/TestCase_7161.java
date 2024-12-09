package testcases.Supplier;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

public class TestCase_7161 {
	
	//Automation -Convert a Supplier Ship To Location Type to Main Account using Supplier  Supervisor Login
	
	@Test()

	public void convertShipToToMainUsingSupplierSupervisor(int iRowNumber, String sDataSheetName) throws Exception {
		
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
		
		//Verify Account Name of the Selected record
		.verifyAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "verifyAccountName", sDataSheetName))		
		
		//Verify Default Account Status on Supplier Form
		.defaultAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountStatus", sDataSheetName))
		
		
		//Choose Record Status as Draft.
		.recordStatusDraft()
		
		//Save the information
		.clickSave()
		
		//Choose Store Location Type as Blank
		.storeLocationTypeBlank()
		
		//Verify if CRM# is generated.
		.crmNumberIsDisplayed()
		
		//Save the information
		.clickSave()
		
		//Choose Record Status as Published
		.recordStatusPublished(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatusPublished", sDataSheetName))
		
		//Save the information
		.clickSave()
		
		//Verify Entity code is same as Direct Parent Account Entity Code
		.entityCodeIsDisplayed()
		
		//Verify new Entity Code is generated.
		.verifyMainAccountEntityCode(DataInputProvider.getCellData_ColName(iRowNumber, "verifyEntityCode", sDataSheetName))
		
		//Data Reset ---		//Revert Store LocationType
		.storeLocationType(DataInputProvider.getCellData_ColName(iRowNumber, "storeLocationType", sDataSheetName))
		
		//Save the information
		.clickSave();
		
	}


}