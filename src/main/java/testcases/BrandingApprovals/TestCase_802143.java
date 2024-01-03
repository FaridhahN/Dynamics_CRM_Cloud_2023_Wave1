package testcases.BrandingApprovals;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_ 802143:_Verify Supplier Supervisor can activate/deactivate Branding Approvals
public class TestCase_802143 {

	@Test
	public void activateAndDeactivateBrandingApprovals(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		//1. Login to CRM  
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Select Accounts Tab
		.selectAccountsTab()
		
		//Search Account
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		
		//Go to Branding Approvals Entity
		.selectRelatedBrandingApprovals()
				
		//Verify Branding Approvals are Listed in Branding Approvals Associated View
		.verifyBrandingApprvlAssocView()
		.verifyAddedBrandingApprvlIsDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "approvalType", sDataSheetName))
		.verifyAddedBrandingApprvlIsDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "approvalType1", sDataSheetName))

		//Select and deactivate all Branding Approvals.
		.deactivateMultipleBrandingApprovals()
		
		//Choose Inactive Branding Approvals View and verify deactivated Branding approvals are listed.
		.clickBrandingApprvlAssocView()
		.selectInactiveBrandingApprvlsView()
		.verifyAddedBrandingApprvlIsDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "approvalType", sDataSheetName))
		.verifyAddedBrandingApprvlIsDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "approvalType1", sDataSheetName))
		
		//Activate All Branding Approvals
		.activateMultipleBrandingApprovals()
		
		//Choose Branding Approval Associated View
		.selectInactiveBrandingApprvlsView()
		.clickBrandingApprvlAssocView()
		
		//Verify All branding Approvals are activated and listed under Branding Approvals Associated View
		.verifyAddedBrandingApprvlIsDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "approvalType", sDataSheetName))
		.verifyAddedBrandingApprvlIsDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "approvalType1", sDataSheetName))
		
		
		
		//Data Reset Not Required

		;
		}
}
