package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1978:_828701_:Cloud : Validate Contact & Job Function functionality

public class TestCase_1978 {

	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM using member supervisor / member credentials
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// 2. Go to Contacts and Click on New
		.selectContacts().clickNewOnContactsPage()

		// Add contact account association with any member and create Job
		// func/Communication records for it

		// Provide First name = Any & Last name = Any
		.typeContactName((DataInputProvider.getCellData_ColName(iRowNumber, "firstName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "lastName", sDataSheetName)))

		// Primary Account = 1000155094
		.selectPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount", sDataSheetName))

		// Save the record
		.clickSave()

		// Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		// Change the record start from Draft to Published then save
		.chooseRecordStatus(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatus", sDataSheetName))

		// Click on Save
		.clickSave()

		//Click Refresh
		.clicRefreshButton()
		
		//Verify with Blank First name

		.typeContactName("","")

		.verifyErrorMessageinName()

		//Update Name
		// Provide First name = Any & Last name = Any
		.typeContactName((DataInputProvider.getCellData_ColName(iRowNumber, "firstName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "lastName", sDataSheetName)))

		
		//update Primary account
		.clearPrimaryAccount()

		//Click save	
		.clickSaveWithoutAssertion()

		//Verify Primary account number is mandatory
		.verifyErrorMessageinPrimaryAccount()


		// Primary Account = 1000155094
		.selectPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount", sDataSheetName))

		//Click save	
		.clickSave()

		// Click on Contact Account association record created
		.doubleClickOnCAARecord()

		// Click on Add new Job function
		.clickAddJobFunction()

		// Select "Bid Proposal Team" as Job function and click on Save
		.typeJobFunction(DataInputProvider.getCellData_ColName(iRowNumber, "jobFunction", sDataSheetName))

		// Click on Save
		.clickSavebuttonInJobFunction()

		//Clear Job function
		.clearJobFunctions()

		// Select "Bid Proposal Team" as Job function and click on Save
		.typeJobFunction(DataInputProvider.getCellData_ColName(iRowNumber, "jobFunction1", sDataSheetName))

		// Click on Save
		.clickSavebuttonInJobFunction()


		//verify Error message
		.verifyErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOkInErrormessage()
		.clearJobFunctions()

		// Click on Save
		.clickSavebuttonInJobFunction()


		//verify Error message
		.verifyJobFunctionMandatory()
		

		
		;}
}
