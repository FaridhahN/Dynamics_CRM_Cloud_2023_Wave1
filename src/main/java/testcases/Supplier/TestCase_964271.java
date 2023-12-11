package testcases.Supplier;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import pages.MemberFormPage;
import utils.DataInputProvider;

//TFS ID_ 964271:Verify ‘Contract Effective Date’ is NOT editable for Supplier.(locked and read only).

public class TestCase_964271 {

	@Test
	public void VerifyInActiveContractsView(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		//Select Accounts Entity
		.selectAccountsTab()

		//Click on +New ( goes to Accounts Page)
		.clickNewOnAccountsPage()

		//Choose 'Supplier Form' Option
		.chooseSupplierForm()
		
		//Verify Contract Effective Date is Locked in New Supplier Form
		.contractEffectiveDateLock();

	}
}
