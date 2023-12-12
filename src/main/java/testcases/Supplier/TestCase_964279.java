package testcases.Supplier;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import pages.MemberFormPage;
import utils.DataInputProvider;

//TFS ID_964279:_Verify Supplier Supervisor can remove ‘Contract Effective Date’ value on Supplier Form in draft state, save and publish.

public class TestCase_964279 {

	@Test
	public void removeContractEffectiveDateDraftAndPublish(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		//Search and select a Supplier

		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		
		//Move Account to Draft and Save
		
		.recordStatusDraft()
		.clickSave()
		
		//Enter Contract Effective Date
		.pickContractEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "contractEffectiveDate", sDataSheetName))
		.recordStatusPublished("Published")
		.clickSave()
		
		//Data Reset
		.pickContractEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "premierStartDate", sDataSheetName))
		.clickSave();

	}
}
