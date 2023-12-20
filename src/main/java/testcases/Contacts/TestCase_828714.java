package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

// TFS ID_828714:_Verify proper error is displayed when contact is saved with record status as null
public class TestCase_828714 {

	@Test
	public void verifyInnovatixIDIsDisplayed(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContactsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "activeContact", sDataSheetName))
		.selectContactFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "activeContact", sDataSheetName))
		
//		.clickNewOnContactsPage()
//
//		// 3. Provide First name = Any & Last name = Any
//		.typeContactName((DataInputProvider.getCellData_ColName(iRowNumber, "firstName", sDataSheetName)),
//				(DataInputProvider.getCellData_ColName(iRowNumber, "lastName", sDataSheetName)))
//
//		// Primary Account
//		.selectPrimaryAccountByClickingOnSearchLensIcon("Augustus Gay")


							
		// 5. Change the record Status
		.chooseRecordStatus("--Select--")

		// Click on Save
		.clickSaveWithoutAssertion()

		//Verify Error Message -Record status can't be empty

		.verifyErrorMessage("Record status can't be empty")

		.clickOKOnErrorMessage()

		//Choose Record Status= Draft
		.chooseRecordStatus("Draft")

		// Click on Save
		.clickSave()

		// Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		;			


		//Data Reset Not Required

	}
}
