package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//******COMPLETED******

//TFS ID_8747:_1001790_:Cloud: Validate Contact communication - Owner Distribution logic  via Membership role.


public class TestCase_8747 {

	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM using member supervisor / member credentials
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
				.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
				.clicSignin().clicYesInStaySignedin()

				// 2. Go to Contacts and Click on New
				.selectContacts().clickNewOnContactsPage()

				// 3. Provide First name = Any
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

				.clickCAAFromRelated()
				//Verify CAA is displayed
				.verifyNewCAA(DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount", sDataSheetName))
				
				// 3. Go to Contact Account association view and click on the record
				.doubleClickOnCAARecord()
				

				// Click on add new Communication/Publications
				.clickThreeDotinCommunication()

				//Verify CAA is displayed 
				.verifyCAAAutopopulated()
				
				// Select "Roster-Hierarchy Roster" as Communication then save
				.typeContactCommunication(
						DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication", sDataSheetName))

				// Click on Save
				.clickSaveInContactCommunication()

				
				//Verify error message is not displayed
				.VerifyErrorMessageNotDisplayed()
				
				//Delete Communication Publication
				
				.clearCommunicationpublicationValue()
				
				// Select "Roster-Hierarchy Roster" as Communication then save
				.typeContactCommunication(
						DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication1", sDataSheetName))

				// Click on Save
				.clickSaveInContactCommunication()

				
				//Verify error message is not displayed
				.VerifyErrorMessageNotDisplayed()
				
//Delete Communication Publication
				
				.clearCommunicationpublicationValue()
				
				// Select "Roster-Hierarchy Roster" as Communication then save
				.typeContactCommunication(
						DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication2", sDataSheetName))

				// Click on Save
				.clickSaveInContactCommunication()

				
				//Verify error message is not displayed
				.VerifyErrorMessageNotDisplayed()
	
;	
		}
}
