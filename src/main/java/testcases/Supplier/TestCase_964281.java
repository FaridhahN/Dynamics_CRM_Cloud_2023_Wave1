package testcases.Supplier;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import pages.MemberFormPage;
import utils.DataInputProvider;

//TFS ID_ 964281:_Verify Supplier Supervisor can update ‘Contract Effective Date’ on existing account.

public class TestCase_964281 {

	@Test
	public void VerifyInActiveContractsView(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		//Search and select a Supplier

		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		//Enter Contract Effective Date
		.pickContractEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "contractEffectiveDate", sDataSheetName))
		.clickSave()
		
		//Data Reset
		.clearContractEffectiveDate();

	}
}
