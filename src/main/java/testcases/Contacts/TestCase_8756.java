package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//******COMPLETED******

//Test Case 8756:Cloud : Restrict when user enters blank communication record in Contact account associations

public class TestCase_8756 {

	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM as member supervisor
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContacts()

		//Navigate to All Account

		.selectAllContactView()

		// 2. Go to any active contact which has an active contact account association
		.chooseActiveContact(DataInputProvider.getCellData_ColName(iRowNumber, "activeContact", sDataSheetName))

		// Click on Contact Account association record created
		.doubleClickOnCAARecord()

		// Click on add new Communication/Publications
		.clickAddContactCommunication()

		// 3.Try to enter blank Job function in the contact account association and save
		// Select "" as Communication then save
		.typeContactCommunication1(
				DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication", sDataSheetName))

		// Click on Save
		.clickSaveInContactCommunication()

		// Verify error message
		.verifyErrorInContactCommunication(
				DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))

		.clickSignout();
	}
}
