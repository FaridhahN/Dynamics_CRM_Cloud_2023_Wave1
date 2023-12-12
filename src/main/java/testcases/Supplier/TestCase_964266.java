package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;

import utils.DataInputProvider;

//TFS ID_964266:_Verify Related Tab >Documents Option is removed

public class TestCase_964266 {

	@Test
	public void verifyDocumentsOptionRemovedOnRelatedTab(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		//Search and select a Supplier

		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		
		//Verify Documents Option Removed on Related Tab.		
		.verifyDocumentsOptionRemovedOnRelatedTab();
		
		//Data Reset
		//Not Required

	}
}
