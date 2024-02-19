package testcases.SecurityInnovatixUser;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

// ******COMPLETED******

//TFS ID_10539 :812466_Cloud: Verify using Innovatix user - Create a New Contact


public class TestCase_10539 {

	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM as member supervisor
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
				.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
				.clicSignin().clicYesInStaySignedin()

				// 2. Go to Contacts and Click on New
				.selectContacts().clickNewOnContactsPage()

				// 3. Provide First name = Any & Last name = Any
				.typeContactName((DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName)),
						(DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName", sDataSheetName)))

				// Primary Account = 1000155094
				.selectPrimaryAccount(
						DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))

				// Save the record
				.clickSave()

				// 4. Observe the Innovatix Contact ID field
				.isInnovatixContactIDDisplayed()

				// 5. Change the record start from Draft to Published
				.chooseRecordStatus(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatus", sDataSheetName))

				// Click on Save
				.clickSave()

				.VerifyErrorMessageNotDisplayed()
				
				;
	}
}
