package testcases.BaseReadOnly;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10624-Cloud : Verify whether Base Read Only roles should have access to create a "Contact".

public class TestCase_10624 {


	@Test
	public void verifyContactsEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Limited member 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.selectActiveMembers()
		.selectAccountFromSearchResults()

		//3.Verify Contacts Entity for Limited Member

		.clickRelatedContacts()
		.clickNewContact()
		.addNewContactToMember((DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "mainPhone", sDataSheetName)))

		//Choose Existing Contact from Contact Associated View

		.doubleClickExistingContact((DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName)))

		// Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		// Change the record start from Draft to Published then save
		.chooseRecordStatus(DataInputProvider.getCellData_ColName(iRowNumber, "changeRecordStatus", sDataSheetName))

		// Click on Save
		.clickSaveAndClose()


		//Data Reset
		.deactivateContact((DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName)));




	}
}
