package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//  TFS ID_828712:_ 11250: Cloud : Verify Contact's Job Function or Comm/Publication is deactivated when terminated with 'Entered in Error'

public class TestCase_828712 {


	@Test
	public void verifyJobFuncAndCommPubDeactivatedWhenEnteredinError(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContactsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "activeContact", sDataSheetName))
		.selectContactFromSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "activeContact", sDataSheetName))
		.clickCAAFromRelated()
		.doubleClickOnCAARecord()

		.clickContactJobFunctionFromRelated()
		.doubleClickOnActiveJobFunction()

		// Select Entered in Error  as Termination Reason
		.chooseContactTerminationReason(DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))
		.clickSaveAndCloseInJobFunction()

		
		.clickInactiveJobFunctionView()

		// Check job function is present under Inactive View which means its removed from grid
		// Verify termination status
		.verifyJobFunctTerminationReasonInInactiveView(
				DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))

		// Verify status =Entered in Error
		.verifyJobFunctStatusInInactiveView(
				DataInputProvider.getCellData_ColName(iRowNumber, "Status", sDataSheetName))

		//Data Reset for Job Function
		.doubleClickOnInActiveJobFunction()
		.clickOnJobFunctionActivate()
		.chooseContactTerminationReason(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
		.clickSaveAndCloseInJobFunction()


		//Communication Publication Verification Starts
		.clickContactCommunicationFromRelated()
		.doubleClickOnActiveCommunicationPublication()
		
		// Select Entered in Error  as Termination Reason
		.chooseContactTerminationReason(DataInputProvider.getCellData_ColName(iRowNumber, "terminationReason", sDataSheetName))
		.clickSaveAndCloseInCommunicationPublications()
	
		// Check job function is present under Inactive View which means its removed from grid
		// Verify termination status
		.clickInactiveContactCommunicationView()
		
		// Verify status =Entered in Error
		.verifyContactCommunicationStatusInInactiveView(
				DataInputProvider.getCellData_ColName(iRowNumber, "Status", sDataSheetName))
		
		//Data Reset for Communication Publication
		.doubleClickOnInActiveCommunicationPublication()
		.clickOnCommunicationPublicationActivate()
		.chooseContactTerminationReason(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
		.clickSaveAndCloseInCommunicationPublications();


	}

}
