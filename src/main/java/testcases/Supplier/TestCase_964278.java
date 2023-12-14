package testcases.Supplier;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import pages.MemberFormPage;
import utils.DataInputProvider;

// TFS ID_964278:_Verify Supplier Supervisor can remove ‘Contract Effective Date’ value on Supplier Form in published state without moving to draft.
public class TestCase_964278 {

	@Test
	public void removeContractEffectiveDateInPublishedState(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		//Search and select a Supplier

		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		
				
		//Enter Contract Effective Date without moving the account to Draft
		.pickContractEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "contractEffectiveDate", sDataSheetName))
		.clickSave()
		
		//Data Reset
		.pickContractEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "premierStartDate", sDataSheetName))
		.clickSave();

	}
}
