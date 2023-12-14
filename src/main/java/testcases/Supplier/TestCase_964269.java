package testcases.Supplier;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import pages.MemberFormPage;
import utils.DataInputProvider;

// TFS ID_964269:_Verify ‘Contract Effective Date’ is editable for Supplier Supervisor.

public class TestCase_964269 {

	@Test
	public void verifyContractEffDateIsEditableForSupSupervisor(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

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
		
		//Verify Contract Effective Date is Editable in New Supplier Form
		.contractEffectiveDateIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "contractEffectiveDate", sDataSheetName))
		
		.clickSave();

	}
}
