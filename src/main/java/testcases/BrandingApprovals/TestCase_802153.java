package testcases.BrandingApprovals;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_ 802153:_Verify Supplier Supervisor can update Branding Approvals
public class TestCase_802153 {

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
		
		//Choose Existing Branding Approval
		.doubleClickAnyExistingBrandingApproval()
		
		//Update Values in Existing Branding Approval
		.typeApprovalDate()
		.selectApprovalType(DataInputProvider.getCellData_ColName(iRowNumber, "approvalType", sDataSheetName))
		.typeApprovedBy("TestUserName")
		.typeStartDateOfUse()
		.typeEndDateOfUse()
		.clickSaveOnBrandingApprovals()
		
				
		//Data Reset 
		.clearApprovedBy()
		.clearEndDateOfUse()
		.clickSaveAndCloseBrandingApprovals()

		;
		}
}
