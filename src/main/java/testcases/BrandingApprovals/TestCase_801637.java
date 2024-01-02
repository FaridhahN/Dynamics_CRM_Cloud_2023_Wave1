package testcases.BrandingApprovals;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_801637:__Verify Supplier cannot create Branding Approvals
public class TestCase_801637 {

	@Test
	public void verifyNewBrandingApproval(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		//1. Login to CRM  
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Branding Approvals
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		
		//Verify New Branding Approval Button
		.selectRelatedBrandingApprovals()
		.verifyAddNewBrandingApprovalButtonIsNotDisplayed()

		;}
}
