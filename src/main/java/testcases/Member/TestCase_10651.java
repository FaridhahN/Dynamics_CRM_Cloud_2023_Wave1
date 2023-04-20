package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10651-Cloud : Verify whether Base Read Only roles should not have access to add C-suite Job Function in a Contact.

public class TestCase_10651 {


	@Test
	public void verifyContactsEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Base Read only
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

		//3.Verify Contacts Entity for Base Read Only

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


		// 6. Click on Contact Account association record created
		.doubleClickOnCAARecord()

		// 7. Click on Add new Job function
		.clickAddJobFunction()

		// 8. Select "Bid Proposal Team" as Job function and click on Save
		.typeJobFunction(DataInputProvider.getCellData_ColName(iRowNumber, "jobFunction", sDataSheetName))

		// Click on Save
		.clickSaveInJobFunctionWithoutScriptError()

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
