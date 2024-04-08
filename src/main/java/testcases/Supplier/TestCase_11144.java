package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

// TFS ID_11146 :_30797_:Cloud -Supplier_Verify Contact's 'Record Change status' is changed to Needs Approval when Comm/Publications is updated during deactivation/termination of Communication/Publications

public class TestCase_11144 {

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

		//navigate to Job function
		.clickContactJobFunctionFromRelated()

		//Open Communication/Publication
		.doubleClickOnActiveJobFunction()


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
		.clickContactJobFunctionFromRelated()

		.clickAddJobFunction()
		// Select "Bid Proposal Team" as Job function and click on Save
		.typeJobFunction(DataInputProvider.getCellData_ColName(iRowNumber, "jobFunction", sDataSheetName))

		
		// Click on Save
		.clickSaveInJobFunction()


		
		.clickSaveAndCloseInContactCommunicationWithoutScriptError()

		;

	}
}
