package testcases.BrandingApprovals;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_ 801639:_Verify Supplier Supervisor can create Branding Approvals
public class TestCase_801639 {

	@Test
	public void addNewBrandingApproval(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		//1. Login to CRM  
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Select Accounts Tab
		.selectAccountsTab()
		
		//In case of rerun using same CRM# as input ,this TC could fail until 1004519 bug is fixed
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		
		//Add New Branding Approval
		.selectRelatedBrandingApprovals()
		.clickAddNewBrandingApprovalButton()
		.selectApprovalType(DataInputProvider.getCellData_ColName(iRowNumber, "approvalType", sDataSheetName))
		.typeStartDateOfUse()
		.typeApprovalDate()
		.clickSaveAndCloseBrandingApprovals()
		
		//Verify added Branding Approval is Listed in Branding Approvals Associated View
		.verifyAddNewBrandingApprovalButtonIsDisplayed()
		.verifyBrandingApprvlAssocView()
		.verifyAddedBrandingApprvlIsDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "approvalType", sDataSheetName))
		
		//Data  Reset
		.deactivateAllBrandingApprovals()	
		

		;
		}
}
