package testcases.DateValidations;


import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_1004972:_1004972:Verify whether user able to update Start Date < or<= TPRD.

public class TestCase_1004972{


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
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.navigateToApplicationDate()
		.gettprd()
		.getPastDate(TestUtils.date, TestUtils.getRandomNumber(1))

		.selectRelatedMembership()
		.doubleClickMembership(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
		.typeMembershipStartDate(TestUtils.enddate)
		.clickMembershipSave()
		.clickGeneralTab()
		.verifyPremierStartDate(TestUtils.enddate)
		.clickSave()
		.verifyErrorisNotDisplayed()
		//Data reset not required
		;
	}
}
