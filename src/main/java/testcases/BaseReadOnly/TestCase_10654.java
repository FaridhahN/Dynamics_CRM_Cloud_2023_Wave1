package testcases.BaseReadOnly;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10654-Cloud : Verify whether Base Read Only roles should not have access to add 'Owner distribution' communication in a Contact.

public class TestCase_10654 {


	@Test
	public void verifyContactsEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM as Base-Read Only
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.selectActiveMembers()
		.selectAccountFromSearchResults()

		//Verify Contacts Entity for Base Read Only
		
		.clickRelatedContacts()
		.clickNewContact()
		.addNewContactToMember((DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "mainPhone", sDataSheetName)))
		
		//Open above created contact
		.doubleClickExistingContact((DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName)))
		
		// Observe the Innovatix Contact ID field
				.isInnovatixContactIDDisplayed()

				// Change the record start from Draft to Published then save
				.chooseRecordStatus(DataInputProvider.getCellData_ColName(iRowNumber, "changeRecordStatus", sDataSheetName))
					
				
				// Click on Save
				.clickSaveAndClose()
				
				
				//Open above created contact
				.doubleClickExistingContact((DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName)))
				
				
				//Click on Contact Account association record created
				.doubleClickOnCAARecord()
				
				//Click on add new Communication/Publications
				.clickAddContactCommunication()

				//Select "Roster-Hierarchy Roster" as Communication then save
				.typeContactCommunication(DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication", sDataSheetName))

				// Click on Save
				.clickSaveInContactCommunicationWithoutScriptError()

								
				//Verify Error Message
				.verifyErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
				.clickOKOnErrorMessage()
				.clickGoBack()
				.clickDiscardChanges()
				.clickGoBack()
				.clickGoBackToMemberForm()
				
			

		
		//Data Reset
		.deactivateContact((DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName)));




	}
}
