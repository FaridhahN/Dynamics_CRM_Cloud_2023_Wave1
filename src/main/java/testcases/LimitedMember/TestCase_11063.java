package testcases.LimitedMember;

import org.testng.annotations.Test;

import pages.ContactsPage;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//******COMPLETED******

//TFS ID_11063 :_828061_Cloud : Verify Limited Member can terminate a CAA from multiple CAAs when at least one CAA has Owner's Distributions related Communication/Publication 

public class TestCase_11063 {

	@Test
	public void validateContactCAA(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM as member supervisor
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContacts()

		//Navigate to All Account

		.selectAllContactView()

		.clickNewOnContactsPage()


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

		.clickCAAFromRelated()

		.verifyNewCAA(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))

		.doubleClickAccount(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))

		.addnewCommunicationPublications()
		.typeContactCommunication(DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication1", sDataSheetName))
		.clickSaveAndCloseInContactCommunicationWithoutScriptError()
		.clickGoBack()
		.clickSummaryTab()

		.addAnotherPrimaryAccount(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact1", sDataSheetName))

		// Click on Save
		.clickSave()

		.clickCAAFromRelated()

		.verifyNewCAA(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact1", sDataSheetName))

		.doubleClickAccount(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact1", sDataSheetName))


		.addnewCommunicationPublications()
		.typeContactCommunication(DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication", sDataSheetName))
		.clickSaveInContactCommunicationWithoutScriptError()
		.verifyErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		.clickOKOnErrorMessage()
		.clickLogout()
		.refreshPageChooseSignInUser()
		;

		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContacts()

		.selectAllContactView()

		// 2. Go to any active contact which has an active contact account association
		.chooseActiveContact(ContactsPage.CRMNumber)

		.clickCAAFromRelated()


		.doubleClickAccount(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact1", sDataSheetName))

		.addnewCommunicationPublications()
		.typeContactCommunication(DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication", sDataSheetName))
		.clickSaveInContactCommunicationWithoutScriptError()

		.VerifyErrorMessageNotDisplayed()
		.clickLogout()
		.refreshPageChooseSignInUser()
		;

		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContacts()

		//Navigate to All Account

		.selectAllContactView()

		// 2. Go to any active contact which has an active contact account association
		.chooseActiveContact(ContactsPage.CRMNumber)

		.clickCAAFromRelated()


		.doubleClickAccount(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))
		.typeContactRelationshipEndDate()
		.clickandsaveCAA()
		.VerifyErrorMessageNotDisplayed()
		;

	}
}
