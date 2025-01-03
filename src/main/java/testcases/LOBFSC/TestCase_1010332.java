package testcases.LOBFSC;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_828697:_828697:Verify whether Error Message should not be displayed when Line of business End date = Line of business Start date. 


public class TestCase_1010332 {


	@Test
	public void LOBDateValidation(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()
		//Search the account
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Verify the same scenrion in Draft status
				.chooseRecordStatusDraftfromTop()
				.clickSave()
		//Enter LOB end date same as LOB start Date
		.clickLineOfBusinesses()
		.doubleClickExistingLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

		.getLOBStartDate()
		// Start Date =Today's date
		.typeLOBEndDate(TestUtils.date)

		// Click on LOB Save 
		.clickLOBSaveAndClose()



		//Click on Save 
		.clickSave() 
		.verifyErrorisNotDisplayed()


		//Date Reset
		//7.  Click the + icon on the Line of Business Grid
		.clickLineOfBusinesses()

		//Click New Line Of Business
		.clickAddNewLineOfBusiness()

		// Line of Business =General GPO
		.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

		// Classification Type = General GPO
		.selectLOBfClassificationType(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification", sDataSheetName))

		.getFutureDate(TestUtils.todaysDate(), TestUtils.getRandomNumber(9))
		// Start Date =Today's date
		.typeLineOfBusinessStartDate(TestUtils.enddate)

		// Click on LOB Save 
		.clickLOBSaveAndClose()
		.clickSave()

		//Verify the same scenrion in Draft status
		.chooseRecordStatusDraftfromTop()
		.clickSave()

		//Enter LOB end date same as LOB start Date
		.clickLineOfBusinesses()
		.doubleClickExistingLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

		.getLOBStartDate()
		// Start Date =Today's date
		.typeLOBEndDate(TestUtils.date)

		// Click on LOB Save 
		.clickLOBSaveAndClose()



		//Click on Save 
		.clickSave() 
		.verifyErrorisNotDisplayed()


		/*
		 * //Date Reset //7. Click the + icon on the Line of Business Grid
		 * .clickLineOfBusinesses()
		 * 
		 * //Click New Line Of Business .clickAddNewLineOfBusiness()
		 * 
		 * // Line of Business =General GPO
		 * .selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber,
		 * "lineOfBusiness", sDataSheetName))
		 * 
		 * // Classification Type = General GPO
		 * .selectLOBfClassificationType(DataInputProvider.getCellData_ColName(
		 * iRowNumber, "lineOfClassification", sDataSheetName))
		 * 
		 * .getFutureDate(TestUtils.todaysDate(), TestUtils.getRandomNumber(9)) // Start
		 * Date =Today's date .typeLineOfBusinessStartDate(TestUtils.enddate)
		 * 
		 * // Click on LOB Save .clickLOBSaveAndClose() .clickSave()
		 * 
		 */

		;
	}
}
