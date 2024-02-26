package testcases.SecurityInnovatixUser;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

/************** COMPLETED ****/

//TFS ID_10537 :_812464_Cloud: Verify using Innovatix user - Modify a Contact - End a Contact

public class TestCase_10356 {

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
		.typeContactName((DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName", sDataSheetName)))

		// Primary Account = 1000155094
		.selectPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))

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

		// Change the record status to Draft 
		.chooseRecordStatus("Draft")
		.clickSave()

		//Double click on CAA on grid view in Summary tab
		.doubleclickCAAinSummaryGrid()

		.enterCAAEndDAteAndSave()

		.verifyStatusInCAA("Active")

		.clickSaveAndCloseInCAA()
		.clickSaveAndCloseInCAA()
		.verifyCAAisnotPresent()

		.clickSeeAssociatedRecordsinCAA()

		.clickContactSubGridViewInCAA()
		.doubleClickOnCAARecordInSubGridView()
		.verifyTerminationStatusinJobFunction()
		.verifyTerminationStatusinCommunication()
		.verifyTerminationDateinCAA(TestUtils.todaysDate())
		.clickSaveAndCloseInCAA()
		.clickSummaryTab()
		// Change the record start from Draft to Published then save
		.chooseRecordStatus(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatus", sDataSheetName))

		// Click on Save
		.clickSave()
		.VerifyErrorMessageNotDisplayed()
		;


	}
}
