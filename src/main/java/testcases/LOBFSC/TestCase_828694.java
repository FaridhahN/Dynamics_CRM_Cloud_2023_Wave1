package testcases.LOBFSC;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 6960:Verify Food Services LOB is added automatically when Non-GPO Premier Membership is converted to GPO Premier Membership.
//Test Case 8791:Cloud - Verify when selecting any "Class of trade" , "Not Found" Error window should not get displayed.
import utils.TestUtils;


public class TestCase_828694 {
//TFS ID_828694:_828694:Verify on End date any LOB,  "End date" should not be lesser than "Start date".

	@Test
	public void verifyFSLOBAddedautomatically(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		//Navigate to membership
		.selectRelatedMembership()
		.doubleClickMembership(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		//get the membership date
		.getMembershipStartDate()
		
		.getFutureDate(TestUtils.date, 1)

		.typeMembershipEndDate(TestUtils.enddate)
		.selectMembershipEndReason(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndReason", sDataSheetName))
		.clickMembershipSave()
		
		//verify Error message
		.verifyDateValidationError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		;
	}
}
