package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

// TFS ID_828699:_Test Case 1134:Cloud : Validate Contact account association becomes inactive when termination reason is "Entered in Error"

public class TestCase_828699 {


	@Test
	public void verifyCAAStatusWhenEnteredinError(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContactsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "activeContact", sDataSheetName))
		.selectContactFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "activeContact", sDataSheetName))
		.clickCAAFromRelated()
		.doubleClickOnCAARecord()
		
		// Select Entered in Error  as Termination Reason
		.chooseContactTerminationReason(DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))
		.clickSaveInCAA()
		
		//Verify the Inactive Error Message on CAA
		.verifyWarningNotification("Read-only This record’s status: Inactive")
		
		//Data Reset
		.clickOnCAAActivate()
		.chooseContactTerminationReason(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
		.clickSaveInCAA()
		
		;
	}

}
