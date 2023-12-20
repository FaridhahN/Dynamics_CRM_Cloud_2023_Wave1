package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

// TFS ID_828700:_Test case Test Case 1140:Cloud : Validate auto-generated CRM Number (a.k.a. Innovatix Contact ID) for Contacts
public class TestCase_828700 {

	@Test
	public void verifyInnovatixIDIsDisplayed(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// 2. Go to Contacts and Click on New
		.selectContacts().clickNewOnContactsPage()

		// 3. Provide First name = Any & Last name = Any
		.typeContactName((DataInputProvider.getCellData_ColName(iRowNumber, "firstName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "lastName", sDataSheetName)))

		// Primary Account
		.selectPrimaryAccountByClickingOnSearchLensIcon("Augustus Gay")

		// Save the record
		.clickSave()

		// 4. Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		// 5. Change the record start from Draft to Published
		.chooseRecordStatus(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatus", sDataSheetName))

		// Click on Save
		.clickSave()

		.verifyRecordChangeStatus("Not Applicable")	;			


		//Data Reset Not Required

	}
}
