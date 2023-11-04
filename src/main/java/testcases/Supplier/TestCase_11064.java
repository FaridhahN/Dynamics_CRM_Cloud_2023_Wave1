package testcases.Supplier;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import pages.MemberFormPage;
import utils.DataInputProvider;

/************** COMPLETED ****/

//TFS ID_11064 :_30799_Cloud -Verify when a Supplier is end dated, the End Reason  defaults to 'Change' automatically


public class TestCase_11064 {

	@Test
	public void VerifyEndReason(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

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
		.typeMembershipEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))
		.clickMembershipSave()
		.verifyEndreason("Change")
		.clickMembershipSaveAndClose()
		.selectMembership()
		
		
		//Data Reset

		.clickAddNewMembershipProvider()
		.selectMembershipProviderType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.typeMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
		.selectMembershipProviderStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipStartDate", sDataSheetName))
		.clickAddNewMembershipProviderSaveButton()
		

		;

	}
}
