package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;

import utils.DataInputProvider;

//TFS ID_948620:_Verify the Contracts options available in Related Tab on Supplier Form

public class TestCase_948620 {

	@Test
	public void VerifyContractsOptionOnRelatedTab(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		//Search and select a Supplier

		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		//Verify Contracts Option on Related Tab.
		
		.verifyContractsOption();
		
		//Data Reset
		//Not Required

	}
}
