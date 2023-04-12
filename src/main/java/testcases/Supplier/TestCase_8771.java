package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
// Test Case 8771:Cloud : Supplier Form - DP & TP Search Look up should have only Supplier Accounts

public class TestCase_8771 {


	@Test
	public void createMember(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		
		//Type the Account Name
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))		
		
		
		//Choose Direct Parent
		.noMatchforDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))
		
		//Choose Direct Parent -Ship To -Added step at Wave2 review to match test steps
		.noMatchforDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		
		//Advanced Lookup -not required as per test case
		//.searchDPinAdvanceLookup(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))
		;
		
	}
}
