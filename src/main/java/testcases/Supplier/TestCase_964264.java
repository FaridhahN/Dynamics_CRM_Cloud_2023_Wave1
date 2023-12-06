package testcases.Supplier;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

public class TestCase_964264  {
	
	//Verify ‘DOCUMENTS’ Tab is placed right of ‘Activities’ Tab and left of ‘TAX DOCUMENTS’ Tab on Supplier Form.
	
	@Test
	public void documentsTab(int iRowNumber, String sDataSheetName)throws Exception,InterruptedException {
		
		//Access Login Page
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
		
		//Verify Documents Tab Order
		.verifyDocumentsTabOrder();	
		

	}
	
}