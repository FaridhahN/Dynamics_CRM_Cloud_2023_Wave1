package testcases.LimitedMember;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//******COMPLETED******

//TFD ID_4546:_828060_Cloud : Verify if Limited member can edit the primary account of a contact

public class TestCase_4546 {

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

		// 2. Go to any active contact which has an active contact account association
		.chooseActiveContact(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		.addAnotherPrimaryAccount(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))

		.clickSave()
		.clickCAAFromRelated()
		.verifyNewCAA(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))
		.DeactivaeAccount(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))	
		//Data Reset

		;
	}
}
