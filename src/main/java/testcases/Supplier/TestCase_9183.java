package testcases.Supplier;
import org.testng.annotations.Test;

import driver.Driver;
import pages.LoginPage;
import utils.DataInputProvider;

public class TestCase_9183 {

		//Test Case 9183:Cloud-Create a supplier Top Parent_Save and Publish as a Supplier Supervisor
	
	@Test()
	public void createSupplierTPAsSupplier(int iRowNumber, String sDataSheetName)throws Exception,InterruptedException {
		//Access Login Page and login as Supplier
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
  	    .clicSignin()
  	    .clicYesInStaySignedin()
		
		//Select Accounts Entity
		.selectAccountsTab()
		
		//Click on +New ( goes to Accounts Page)
		.clickNewOnAccountsPage()
		
		//Choose 'Supplier Form' Option
		.chooseSupplierForm()
		
		//Verify Default Account Status on Supplier Form
		.defaultAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountStatus", sDataSheetName))	
		
		//Verify Default Account Type on Supplier Form
		.defaultAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountType", sDataSheetName))		
		
		//Type the Account Name
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))		
		
		//Enter Premier Start Date
		.pickPremierStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "premierStartDate", sDataSheetName))		
		
		//Choose the Business Classification
		.selectBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "businessClassification", sDataSheetName))
		
		//Choose Is TP as Yes
		.clickIsTPYes()
		
		//Enter Top Parent Relation Date
		.pickTPRD(DataInputProvider.getCellData_ColName(iRowNumber, "selectTPRelationDate", sDataSheetName))	
		
		//Enter the Street1 address info
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))		
		
		//Enter the Zip Code address info
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))		
		
		//Save the information
		.clickSave()
		
		//Verify Record Status field is not editable for Supplier Login
		.recordStatusLock()
		
		//Verify if CRM# is generated.
		.crmNumberIsDisplayed()
		
		//Log out as a Supplier
		.clickLogout()
		.refreshPageChooseSignInUser()
		
		//Login as Supplier Supervisor
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
		.clickNext()
  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
  	    .clicSignin()
  	    .clicYesInStaySignedin()
		
		//Select Accounts Entity
		.selectAccountsTab()
		
		//Access the  same Supplier record created by the Supplier
		.searchAccount(DataInputProvider.getCellData_ColName(Driver.iTestCaseRowNumDriver, "CRMNumber", "Driver"))		
		.selectSupplierAccountFromSearchResults()	
		
		//Publish the SUpplier record as a Supplier Supervisor
		.recordStatusPublished(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatusPublished", sDataSheetName))
		.clickSave()
		
		//Verify Entity Code is generated.		
		.entityCodeIsDisplayed();	
			
		
		
	}
	
}