package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

// TFS ID_11169 :_30796_:Cloud -Supplier_Verify Contact's 'Record Change status' is changed to Needs Approval when Contact is End dated

public class TestCase_11169 {

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

		//Navigate to Contact End Date

		//Enter the Contact End date 
		.typeContactEndDate(TestUtils.todaysDate())

		//Click Save
		.clickSave()

		//Navigate To Address details
		.clickAddresDetails()
		
		//Navigate To Summary
		.clickSummaryTab()
		
		//Verify Contact Account Association
		.verifyCAAinSummarySubGrid(false)
		
		
		//Verify the Recodr change status
		.verifyRecordChangeStatus("Needs Approval")

		//Delete the Contact End Date 
		.typeContactEndDate("")
		
		//Click save button
		.clickSave()
		
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
		.selectRecordChangeStatus("Not Applicable")
		
		//Click save button
				.clickSave()
			//Add CC	
		.clickCAAFromRelated()
		.clickAddCAA()
		.typeAccountInCAA(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput1", sDataSheetName))
		.clickSaveInCAA()
		
		;

	}
}
