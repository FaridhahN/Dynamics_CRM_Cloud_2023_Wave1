package testcases.BrandingApprovals;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_802152:_Verify Supplier cannot update Branding Approvals
public class TestCase_802152 {

	@Test
	public void verifyBrandingApprovalIsNotEditable(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		//1. Login to CRM 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Branding Approvals
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectRelatedBrandingApprovals()

		//Select Existing Branding Approvals.
		.doubleClickAnyExistingBrandingApproval()

		//Verify Read-only Banner 
		.verifyBannerErrorText("Read-only: You don't have access to edit this record.")

		//Verify Branding Approval Fields are not Editable
		.verifyBrandingApprovalFieldsNotEditable("Account, Lookup, Readonly")

		;

		//Data Reset
		//Not required

	}
}
