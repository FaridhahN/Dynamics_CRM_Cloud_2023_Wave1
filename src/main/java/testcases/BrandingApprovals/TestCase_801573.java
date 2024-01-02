package testcases.BrandingApprovals;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_801573:_Verify Supplier cannot deactivate Branding Approvals
public class TestCase_801573 {

	@Test
	public void verifyDeactivateButtonOnBrandingApproval(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		//1. Login to CRM 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Branding Approvals
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectRelatedBrandingApprovals()

		//Verify Deactivate Button is not available for Supplier
		.clickOnSelectAllRadioButton()
		.verifyDeactivateButtonIsNotDisplayedBrandingApproval()

		;


		//Data Reset
		//Not required

	}
}
