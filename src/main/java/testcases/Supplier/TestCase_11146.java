package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

// TFS ID_11146 :_30797_:Cloud -Supplier_Verify Contact's 'Record Change status' is changed to Needs Approval when Comm/Publications is updated during deactivation/termination of Communication/Publications

public class TestCase_11146 {

	@Test
	public void verifyInnovatixIDIsDisplayed(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContactsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectContactFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))


		//Verify Contact Account Association
		.verifyCAAinSummarySubGrid(true)


		//Open existing CAA
		.doubleClickOnCAARecord()

		//navigate to Communiction
		.clickContactCommunicationFromRelated()

		//Open Communication/Publication
		.doubleClickOnActiveCommunicationPublication()


		//Terminate Communication/Publication 
		.chooseCommunicationReason(DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))

		//Click Save
		.clickSaveAndCloseInCommunicationPublications()

		//deactivate Communication
		.deactivateAllCommunications()


		//Click go back
		.clickGoBack()
		//Navigate To Summary
		.clickSummaryTab()
		.clickSave()

		//Verify the Recodr change status
		.verifyRecordChangeStatus("Needs Approval")

		//Data Reset 
		.clickSignout()
		.pageRefresh()
		;

		new LoginPage()
		.refreshPageChooseSignInUser()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContactsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.selectContactFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		//Change the record status
		.selectRecordChangeStatus("Approved")

		//Click save button
		.clickSave()
		//Add CC	
		.clickCAAFromRelated()

		//Open existing CAA
		.doubleClickOnCAARecord()

		//Create New Communication and publication
		//navigate to Communiction
		.clickContactCommunicationFromRelated()

		.addnewCommunicationPublications()
		.typeContactCommunication(DataInputProvider.getCellData_ColName(iRowNumber, "communicationPublication", sDataSheetName))
		.clickSaveAndCloseInContactCommunicationWithoutScriptError()

		;

	}
}
