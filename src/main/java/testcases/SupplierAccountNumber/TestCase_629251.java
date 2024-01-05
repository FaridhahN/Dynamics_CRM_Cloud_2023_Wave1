package testcases.SupplierAccountNumber;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_629251:_Verify when COT "Market Category"!= "Healthcare", default "Load Pharmacy Portfolio" = "YES" and "Audit" = "NO" in UI-Supplier Account Entity.
public class TestCase_629251 {

	@Test
	public void SupplierAccountNumberCOTMktCategoryNonHealthCare(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{


		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for  
		.selectAccountsTab()

		//Search  Active Member
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.getAccountName()

		//Check Market Category for COT as Non-Healthcare
		.navigateToBusinessClassification()
		.verifyMarketCategoryNonHealthCareOnCOT()

		//Add New Supplier Account
		.clickGoBack()
		.selectRelatedSupplierAccounts()
		.clickNewSupplierAccountButton()
		.addSupplierAccountDetails(DataInputProvider.getCellData_ColName(iRowNumber, "supplier", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "supplierRep", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "purchasingPreference", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "memberLevel", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "startdate", sDataSheetName))
		.saveSupplierAccount()

		//Add Supplier Account Number
		.navigateToAccountNumberTab()
		.clickNewAccountNumber()
		.addNumberInAccountNumber()
		.clickSaveAccountNumber()		
		.verifyAccountNameinAccountNumberPage()

		//Verify Load Pharmacy Portfolio and Audit		
		.clickSupplierAccountTabOnSupplierAccountNumber()
		.verifyLoadPharmacyPortfolioYesOnSupplierAccount()
		.verifyAuditNoOnSupplierAccount()

		//Data Reset		
		.clickSaveAndCloseSupplierAccountNumber()		
		.deActivateSupplierAccount()


		;
	}

}
