package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_9314 :_30810_Cloud : Verify active supplier can not have DP without EIN

public class TestCase_9314 {


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
		
		//Choose Direct Parent -Without EIN
		.noMatchforDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput1", sDataSheetName))
		
		;
		
	}
}
