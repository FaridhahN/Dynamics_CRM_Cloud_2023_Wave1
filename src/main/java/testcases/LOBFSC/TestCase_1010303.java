package testcases.LOBFSC;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_1010303:_1010303:Verify date restriction logic is removed for LOB Start date against membership start date for existing user


public class TestCase_1010303 {


	@Test
	public void LOBDateValidation(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		.selectRelatedMembership()
		.doubleClickMembership(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
		.getThePremierStartDate()
		.clickMembershipSaveAndClose()
		
		//Navigate to LOB
		.clickLineOfBusinesses()
		.doubleClickExistingLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

		.getPastDate(TestUtils.date, TestUtils.getRandomNumber(9))
		// Start Date =Today's date
		.typeLineOfBusinessStartDate(TestUtils.enddate)

		// Click on LOB Save 
		.clickLOBSaveAndClose()



		//Click on Save 
		.clickSave() 
		.verifyErrorisNotDisplayed()
		;
	}
}
