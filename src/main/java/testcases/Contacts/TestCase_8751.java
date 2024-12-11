package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//*********COMPLETED******************

//Test Case 8751:Cloud : Verify once the Contact's relationship end date is updated then its job function and communications should get terminated
public class TestCase_8751 {

	@Test
	public void endDateAContact(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM using member supervisor / member credentials
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// 2. Go to Contacts and Click on New
		.selectContacts().clickNewOnContactsPage()

		// Add contact account association with any member and create Job
		// func/Communication records for it

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

		// Click on Contact Account association record created
		.doubleClickOnCAARecord()

		// 3.Open the Contact account association and update the relationship end date
		// and terminated reason as "Terminated" on the contact account association then
		// save it
		.typeContactRelationshipEndDate()

		// choose termination reason
		.chooseContactTerminationReason(DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))

		// SAve the record
		.clickSaveAfterTerminationCAA()

		// 4. Now check the Job function and communication records

		// Verify contact status in contact account association

		.clickContactJobFunctionFromRelated()

		.doubleClickOnActiveJobFunction()

		// Check job function
		.verifyJobFunctionTerminationReason(DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))

		.clickGoBack()

		.clickContactCommunicationFromRelated()

		.clickCAArecordFromJobFunction()

		//.doubleClickOnContactCommunication()

		// Check communication record status
		.verifyContactCommunicationTerminationReason(DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))

		.clickSignout();
	}
}
