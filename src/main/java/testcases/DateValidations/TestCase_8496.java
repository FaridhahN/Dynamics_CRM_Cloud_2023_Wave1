package testcases.DateValidations;


import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_8496:_828406:Verify "Error Message" should display date's when Premier End date < TPRD for Supplier Accounts.

public class TestCase_8496{


	@Test
	public void DateValidation(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()
		//Search the account
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectSupplierAccountFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		.selectMembership()

		
		.doubleClickMembership(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		.typeMembershipEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))
		.selectMembershipEndReason(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndReason", sDataSheetName))
		.clickMembershipSave()
		//Date Restriction Error: Top Parent Relation Date 
		.verifyDateValidationError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		

	//Data reset not required
		;
	}
}
