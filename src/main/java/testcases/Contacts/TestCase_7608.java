package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

// ******COMPLETED******

//Test Case 7608:Automation : Create a new contact and add job function and communication/publication

public class TestCase_7608 {

	@Test
	public void createContactWithJBandCommPub(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM as member supervisor
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

				// 4. Observe the Innovatix Contact ID field
				.isInnovatixContactIDDisplayed()

				// 5. Change the record start from Draft to Published
				.chooseRecordStatus(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatus", sDataSheetName))

				// Click on Save
				.clickSave()

				// 6. Click on Contact Account association record created
				.doubleClickOnCAARecord()

				// 7. Click on Add new Job function
				.clickAddJobFunction()

				// 8. Select "Bid Proposal Team" as Job function and click on Save
				.typeJobFunction(DataInputProvider.getCellData_ColName(iRowNumber, "jobFunction", sDataSheetName))

				// Click on Save
				.clickSaveInJobFunction()

				// Verify created job function
				.verifyJobFunctionIsCreated(
						DataInputProvider.getCellData_ColName(iRowNumber, "jobFunction", sDataSheetName))

				.doubleClickOnCAARecord()

				// 9. Click on add new Communication/Publications
				.clickAddContactCommunication()

				// 10. Select "Roster-Hierarchy Roster" as Communication then save
				.typeContactCommunication(
						DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication", sDataSheetName))

				// Click on Save
				.clickSaveInContactCommunication()

				// Verify created communication publications
				.verifyContactCommunicationIsCreated()

				.clickSignout();
	}
}
