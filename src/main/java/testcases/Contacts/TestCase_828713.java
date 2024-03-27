package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

// TFS ID_828713:_Test case 11176: Verify when Contact's Contact Account Association is ended , Record Change Status changes to 'Needs Approval' using Supplier

public class TestCase_828713 {

	@Test
	public void verifyCAANeedsApprovalBySupplier(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

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
		.selectPrimaryAccountByClickingOnSearchLensIcon("Allergy Asthma and Immunology, PLLC")

		// Save the record
		.clickSave()

		// 4. Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		// 5. Change the record start from Draft to Published
		.chooseRecordStatus(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatus", sDataSheetName))

		// Click on Save
		.clickSave()

		.verifyRecordChangeStatus("Not Applicable")				

		.clickCAAFromRelated()
		.doubleClickOnCAARecord()

		//CAA End Date
		.typeContactRelationshipEndDate()
		.clickSaveAndCloseInCAA()

		//Verify Record Change Status =Needs Approval
		.clickSummaryTab()
		.verifyRecordChangeStatus("Needs Approval");
		
		
		//Data Reset Not Required

	}
}
