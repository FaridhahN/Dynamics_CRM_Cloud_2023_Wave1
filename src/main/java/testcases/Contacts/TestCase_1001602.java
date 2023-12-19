package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

// TFS ID_1001602:_Test case 11248: Cloud : Limited Member_Verify change in 'Receive all Communications' field of a Contact Account Association changes Record Change Status as 'Needs Approval'
public class TestCase_1001602 {

	@Test
	public void verifyCAANeedsApprovalWhenReceiveAllCommIsChanged(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// 2. Go to Contacts and Click on New
		.selectContacts().clickNewOnContactsPage()

		// 3. Provide First name = Any & Last name = Any
		.typeContactName((DataInputProvider.getCellData_ColName(iRowNumber, "firstName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "lastName", sDataSheetName)))

		// Primary Account
		.selectPrimaryAccountByClickingOnSearchLensIcon("Barbara Davis Patton")

		// Save the record
		.clickSave()

		// 4. Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		// 5. Change the record start from Draft to Published
		.chooseRecordStatus(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatus", sDataSheetName))

		// Click on Save
		.clickSave()

		.verifyRecordChangeStatus("Not Applicable")				

		.clickCAAFromRelated()
		.doubleClickOnCAARecord()
				

		//Change Receive All Communications
		.chooseReceiveAllCommunicationsOnCAA("No")
		.clickSaveAndCloseInCAA()

		//Verify Record Change Status =Needs Approval
		.clickSummaryTab()
		.verifyRecordChangeStatus("Needs Approval");
		
		//Data Reset Not Required

	}
}
