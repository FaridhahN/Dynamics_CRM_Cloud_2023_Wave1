package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

/************** COMPLETED ****/

//Test Case 8752:Cloud : Verify once the contact is end dated then the related Contact Account Association should get terminated

public class TestCase_8752 {

	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

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
		.typeContactCommunication(
				DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication", sDataSheetName))

		// Click on Save
		.clickSaveInContactCommunication()

		// Verify created communication publications
		.verifyContactCommunicationIsCreated()

		.clickSummaryTab()

		// 3. Now end date the contact with today's or future date and save the record
		.typeContactEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "contactEndDate", sDataSheetName))

		// SAve the record
		.clickSave()

		// 4. Check the record status - published
		.verifyRecordStatus(
				DataInputProvider.getCellData_ColName(iRowNumber, "recordStatus", sDataSheetName))

		.clickCAAFromRelated()

		// 5. Check the Contact account association status as Terminated inside "Contact
		// Subgrid view"

		// Select contact sub grid view
		.clickContactSubGridViewInCAA()

		// Verify termination status
		.verifyCAATerminationReasonInSubGridView(
				DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))

		// Verify relationship end date
		.verifyCAARelationEndDateInSubGridView(
				DataInputProvider.getCellData_ColName(iRowNumber, "contactRelationshipEndDate", sDataSheetName))
		
		// 6. Check the Job function and communication record status
		.doubleClickOnCAARecordInSubGridView()

		// Verify termination status in contact account association
		.verifyStatusInCAA(
				DataInputProvider.getCellData_ColName(iRowNumber, "Status", sDataSheetName))

		.clickContactJobFunctionFromRelated()

		.doubleClickOnJobFunction()

		// Verify job function termination status
		.verifyJobFunctionTerminationReason(
				DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))

		.clickCAArecordFromJobFunction()

		.clickContactCommunicationFromRelated()

		.doubleClickOnContactCommunication()

		// Verify contact communication termination status
		.verifyContactCommunicationTerminationReason(
				DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))

		.clickSignout();
	}
}
