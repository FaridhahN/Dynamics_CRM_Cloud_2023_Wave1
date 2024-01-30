package testcases.Supplier;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

public class TestCase_7158 {

	//Automation-Modify Address in an existing Supplier Account as a Supplier and Verify Record Change Status

	@Test()

	public void modifyAddressAsSupplier(int iRowNumber, String sDataSheetName) throws Exception {
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

		//Verify Default Account Status on Supplier Form
		.defaultAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountStatus", sDataSheetName))

		//Verify Default Account Type on Supplier Form
		.defaultAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountType", sDataSheetName))

		//.recordChangeStatus(DataInputProvider.getCellData_ColName(iRowNumber, "recordChangeStatus", sDataSheetName))

		//Verify Record Status
		.verifyRecordStatusPublished()

		//Verify the Record Change Status
		.verifyDefaultRecordChangeStatus()

		//Save the information
		.clickSave()

		//Update the Street1 Address Info
		.updateStreet1(TestUtils.generateRandomChars("abcdefghi",5))
		
		//
		.navigateToZipCode()

		//Update the Zip Code Address Info
		.updateZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))

		//Save the information
		.clickSave()
		.pageRefresh()

		//Verify the Record Change Status
		.verifyRecordChangeStatus(DataInputProvider.getCellData_ColName(iRowNumber, "verifyRecordChangeStatus", sDataSheetName))

		//Verify if CRM# is generated.
		.crmNumberIsDisplayed()

		//Save the information
		.clickSave()	
		.pageRefresh()
		//Verify if Entity Code is generated.
		.entityCodeIsDisplayed()

		//Verify Entity Code remains the same after Publish
		.verifyEntityCode(DataInputProvider.getCellData_ColName(iRowNumber, "verifyEntityCode", sDataSheetName))

		//Data Reset
		.clickLogout()
		.refreshPageChooseSignInUser()
		//Type the Username
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
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
		.chooseRecordChangeStatus("Approved")
		.clickSave();
	}




}