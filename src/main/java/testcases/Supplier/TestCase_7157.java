package testcases.Supplier;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

public class TestCase_7157 {
	
	//Automation-Modify Address in an existing Supplier Account as a Supplier Supervisor and Verify Record Change Status
	
	@Test()

	public void modifyAddressAsSupplierSupervisor(int iRowNumber, String sDataSheetName) throws Exception {
		//Access Login Page
		new LoginPage()	
		
		//Type the Username
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
		
		//Verify Default Account Type on Supplier Form
		.defaultAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountType", sDataSheetName))
		
		//Choose the Record Change Status
		.recordChangeStatus(DataInputProvider.getCellData_ColName(iRowNumber, "recordChangeStatus", sDataSheetName))
		
		//Move the Record Status to Draft.
		.recordStatusDraft()
		
		//Save the information
		.clickSave()	
		
		//Update the Street1 Address Info
		.updateStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))
		
		//Update the Zip Code Address Info
		.updateZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))
		
		//Save the information
		.clickSave()
		
		//Move the Record Status to Draft.
		.recordStatusPublished(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatusPublished", sDataSheetName))
		
		//Verify Record Change Status
		.verifyRecordChangeStatus(DataInputProvider.getCellData_ColName(iRowNumber, "verifyRecordChangeStatus", sDataSheetName))
		
		//Verify if CRM# is generated.
		.crmNumberIsDisplayed()
		
		//Save the information
		.clickSave()
		
		//Verify if Entity Code is generated.
		.entityCodeIsDisplayed()
		
		//Verify Entity Code remains the same after Publish
		.verifyEntityCode(DataInputProvider.getCellData_ColName(iRowNumber, "verifyEntityCode", sDataSheetName))
		
	
	//Data Reset
	//Reset the Record Change Status to Approved
	.chooseRecordChangeStatus(DataInputProvider.getCellData_ColName(iRowNumber, "recordChangeStatus", sDataSheetName));

	}
}