package testcases.Supplier;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import pages.MemberFormPage;
import utils.DataInputProvider;

/************** COMPLETED ****/

//TFS ID_8519:_629476_Cloud -Verify whether user should be able to update Premier Start date for the "Supplier" via Membership Entity

public class TestCase_8519 {

	@Test
	public void VerifyPremierStartDate(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM using member supervisor / member credentials
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		//Search and select a memeber


		.selectAccountsTab()


		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectMembership()

		.doubleClickOnNewNationalMembership()
		.selectMembershipProviderStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipStartDate", sDataSheetName))
		.clickMembershipSaveAndClose()

		.verifyPremierStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipStartDate", sDataSheetName))

		//Data Reset

		.selectMembership()

		.doubleClickOnNewNationalMembership()
		.selectMembershipProviderStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipStartDate", sDataSheetName))
		.clickMembershipSaveAndClose()




		;

	}
}
