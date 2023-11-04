package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_9313:_30811_Cloud : Verify terminated Supplier location can have contracted Supplier DP

public class TestCase_9313 {


	@Test
	public void verifySupplierDP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
  	    .clicSignin()
  	    .clicYesInStaySignedin()
		
		//Select Accounts Entity
		.selectAccountsTab()
		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		
		.clearDP()
		
		//Choose Direct Parent -Contracted status
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput1", sDataSheetName));
		
	}
}
