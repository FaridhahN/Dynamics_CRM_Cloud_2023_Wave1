package testcases.Supplier;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import pages.MemberFormPage;
import utils.DataInputProvider;

// TFS ID_964268:_Verify ‘Contract Effective Date’ is a date input field.

public class TestCase_964268 {

	@Test
	public void verifyContractEffDateIsDateInputfield(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		//Select Accounts Entity
		//Search and select a Supplier

		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))

		//Verify Contract Effective Date is Accepts only Date values.
		.contractEffectiveDateIsDateInputOnly(DataInputProvider.getCellData_ColName(iRowNumber, "accountname", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "expectedErrorText1", sDataSheetName))

		.pickContractEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "contractEffectiveDate", sDataSheetName))
		.clickSave();
			}
}
