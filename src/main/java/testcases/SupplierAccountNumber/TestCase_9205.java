package testcases.SupplierAccountNumber;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//Test Case 9205:Cloud - Deactivate a supplier account number


public class TestCase_9205 {


	@Test
	public void SupplierAccountNumber(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{

		new WebDriverServiceImpl();
		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for  
		.selectAccountsTab()

		//Search GPO Active Member
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectRelatedSupplierAccounts()
		.clickNewSupplierAccountButton()
		.addSupplierAccountDetails(DataInputProvider.getCellData_ColName(iRowNumber, "supplier", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "supplierRep", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "purchasingPreference", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "memberLevel", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "startdate", sDataSheetName))
		.saveAndCloseSupplierAccount()
		.doubleClickSupplierAccountNumbers()
		.deActivateSupplierAccount()
		;
	}

}
