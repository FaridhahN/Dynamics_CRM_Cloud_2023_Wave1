package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//Test Case 8746:Cloud : Validate deactivating a contact result in updating the contact end date and inactivating the contact account association record

public class TestCase_8746 {

	@Test
	public void deactivateContact(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM using member supervisor / member credentials
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// 2. Go to Contacts and Click on New
		.selectContacts().clickNewOnContactsPage()

		// Add contact account association with any member and create Job
		// func/Communication records for it

		// Provide First name = Any & Last name = Any
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

		// Click on Contact Account association record created
		.doubleClickOnCAARecord()

		// Click on Add new Job function
		.clickAddJobFunction()

		// Select "Bid Proposal Team" as Job function and click on Save
		.typeJobFunction(DataInputProvider.getCellData_ColName(iRowNumber, "jobFunction", sDataSheetName))

		// Click on Save
		.clickSaveInJobFunction()

		// Verify created job function
		.verifyJobFunctionIsCreated(
				DataInputProvider.getCellData_ColName(iRowNumber, "jobFunction", sDataSheetName))

		.doubleClickOnCAARecord()

		// Click on add new Communication/Publications
		.clickAddContactCommunication()

		// Select "Roster-Hierarchy Roster" as Communication then save
		.typeContactCommunication(DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication", sDataSheetName))

		// Click on Save
		.clickSaveInContactCommunication()

		// Verify created communication publications
		.verifyContactCommunicationIsCreated()

		.clickSummaryTab()

		// 3. Now deactivate the contact by clicking on the deactivate button
		.clickOnContactDeactivate()

		// Confirm Deactivation screen appears. Click on Deactivate.
		.clickConfirmDeactivation()


		// 4. Check the contact account association records and the related Job func and
		// the communication records

		// 5. Check the contact account association in inactive view
		.clickCAAFromRelated()

		// Select contact inactive view
		.clickInactiveCAAView()

		// Verify relationship end date
		.verifyCAARelationEndDateInInactiveView()

		// 6.Check for the job func and communication records in inactive view
		.doubleClickOnCAARecordInactiveView()

		// Verify termination status in contact account association - Entered in Error
		.verifyStatusInCAA(DataInputProvider.getCellData_ColName(iRowNumber, "Status", sDataSheetName))

		.clickContactJobFunctionFromRelated()

		.clickInactiveJobFunctionView()

		// Check job function
		// Verify termination status
		.verifyJobFunctTerminationReasonInInactiveView(DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))

		// Verify status
		.verifyJobFunctStatusInInactiveView(DataInputProvider.getCellData_ColName(iRowNumber, "Status", sDataSheetName))

		.clickContactCommunicationFromRelated()

		.clickInactiveContactCommunicationView()

		.verifyContactCommunicationStatusInInactiveView(DataInputProvider.getCellData_ColName(iRowNumber, "Status", sDataSheetName))

		.doubleClickOnContactCommunication()

		.verifyContactCommunicationTerminationReason(DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))

		.clickSignout();
	}
}
