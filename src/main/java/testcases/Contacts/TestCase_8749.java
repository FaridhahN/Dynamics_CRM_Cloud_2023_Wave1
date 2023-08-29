package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//Test Case 8749:Cloud : Verify if primary account of a contact has been changed then other associated Contact account association should not get end dated

public class TestCase_8749 {


	@Test
	public void changePrimaryContact(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// 2. Go to Contacts and Click on New
		.selectContacts().clickNewOnContactsPage()

		// 3. Provide First name = Any & Last name = Any
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

		//Click on Save
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
		.verifyJobFunctionIsCreated(DataInputProvider.getCellData_ColName(iRowNumber, "jobFunction", sDataSheetName))

		.doubleClickOnCAARecord()

		// 9. Click on add new Communication/Publications
		.clickAddContactCommunication()

		// Select "Roster-Hierarchy Roster" as Communication then save
		.typeContactCommunication(
				DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication", sDataSheetName))

		// Click on Save
		.clickSaveInContactCommunication()

		// Verify created communication publications
		.verifyContactCommunicationIsCreated()

		//.clickGoBack()
		//3. Now change the primary account of the contact to a different member

		//Primary Account = 1000036259
		.clickSummaryTab()
		.addAnotherPrimaryAccount(DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount1", sDataSheetName))

		// Click on Save
		.clickSave()

		//4. Check for the old/other Contact account association record
		//Check the Contact record status
		//.doubleClickOnCAARecord()

		.doubleClickOnOldCAARecord(DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount", sDataSheetName))

		.verifyNullinCaaRelationshipEndDate(
				DataInputProvider.getCellData_ColName(iRowNumber, "contactRelationshipEndDate", sDataSheetName))

		//Verify contact account association termination reason is empty
		.verifyNullinCaaTerminationReason(DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))

		.verifyStatusInCAA(
				DataInputProvider.getCellData_ColName(iRowNumber, "Status", sDataSheetName))

		.clickContactJobFunctionFromRelated()

		.doubleClickOnJobFunction()

		// Check job function
		.verifyJobFunctionTerminationReason(
				DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))
		
		.clickGoBack()

		
		.clickContactCommunicationFromRelated()
		
		.clickCAArecordFromJobFunction()

		//.doubleClickOnContactCommunication()

		// Check communication record status
		.verifyContactCommunicationTerminationReason(
				DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))

		.clickSignout();
	}
}
