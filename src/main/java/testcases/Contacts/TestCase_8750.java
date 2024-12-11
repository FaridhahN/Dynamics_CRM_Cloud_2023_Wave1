package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//******COMPLETED******

//Test Case 8750:Cloud : Verify if duplicate contact account association cannot be created for any contact

public class TestCase_8750 {

	@Test
	public void verifyDupCAA(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM using member supervisor / member credentials
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// 2. Go to Contacts and Click on New
		.selectContacts().clickNewOnContactsPage()

		// Provide First name = Any
		// Last name = Any
		.typeContactName((DataInputProvider.getCellData_ColName(iRowNumber, "firstName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "lastName", sDataSheetName)))

		// Primary Account = 1000155094
		.selectPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount", sDataSheetName))

		// Save the record
		.clickSave()

		// Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		// Change the record start from Draft to Published then save
		.chooseRecordStatus(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatus", sDataSheetName))

		// Click on Save
		.clickSave()


		// 3. Add a new contact account association with any member for that contact

		// Click on Add contact account association
		.clickAddCAA()

		// Enter account name CAA
		.typeAccountInCAA(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		// Click on save
		.clickSaveInCAA()


		// 4.Try to add same account to the contact account association

		.clickGoBack()

		// Click on Add contact account association
		.clickAddCAA()

		// Enter account name CAA
		.typeAccountInCAA(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		// Click on save
		.clickSaveInCAA()

		// Verify error message
		.verifyErrorMsgForDuplicateCAA(
				DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))

		// Click on ok
		.clickOkErrorMsgForDuplicateCAA()

		.clickSignout();
	}
}
