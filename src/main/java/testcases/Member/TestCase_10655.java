package testcases.Member;

import org.testng.annotations.Test;
import driver.Driver;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10655-Cloud : Verify whether Base Read Only roles should not have access to edit the contact which as C-suite Job Function in it already.

public class TestCase_10655 {


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


		// 6. Click on Contact Account association record created
		.doubleClickOnCAARecord()

		// 7. Click on Add new Job function
		.clickAddJobFunction()

		// 8. Select "C Suite Job Function" as Job function and click on Save
		.typeJobFunction(DataInputProvider.getCellData_ColName(iRowNumber, "jobFunction", sDataSheetName))

		// Click on Save
		.clickSaveInJobFunction()
		
		.clickGoBack()

		//Logout
		.clickLogout()

		.refreshPageChooseSignInUser()


		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//Go to Workplace >> Take Any Contact
		.selectContacts()

		.selectAllContactView()

		.selectExistingContact(DataInputProvider.getCellData_ColName(Driver.iTestCaseRowNumDriver, "CRMNumber", "Driver"))

		.selectAccountFromContactSearchResults()


		// 6. Click on Contact Account association record created
		.doubleClickOnCAARecord()

		// 7. Click on Add new Job function
		.clickAddJobFunction()

		// 8. Select "Chief Executive Officer" as Job function and click on Save
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
		.clickLogout()
		.refreshPageChooseSignInUser()

		//Data Reset
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()
		.selectContacts()
		.selectAllContactView()
		.selectExistingContact(DataInputProvider.getCellData_ColName(Driver.iTestCaseRowNumDriver, "CRMNumber", "Driver"))		
		.deactivateContactAllContactsView();


	}
}
