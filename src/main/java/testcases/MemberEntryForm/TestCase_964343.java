package testcases.MemberEntryForm;

import org.testng.annotations.Test;

import pages.ContactsPage;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_964343:_964343:Verify whether  "Contact" are available in the 'Related' Tabs of the Member Entry Form.


public class TestCase_964343 {


	@Test
	public void verifyContact(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{
		//1. Login to CRM using member supervisor / member credentials
		WebDriverServiceImpl.isMemberForm=true;
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.chooseMemberEntryForm()
		.clickSave()
		.verifyContactOption()	;
		new ContactsPage()
		.clickNewOnContactsPage()

		// 3. Provide First name = Any & Last name = Any
		.typeContactName((DataInputProvider.getCellData_ColName(iRowNumber, "firstName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "lastName", sDataSheetName)))

		// Primary Account = 1000155094
		.selectPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount", sDataSheetName))

		// Save the record
		.clickSave()

		// 4. Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		// 5. Change the record start from Draft to Published
		.chooseRecordStatus(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatus", sDataSheetName))

		// Click on Save
		.clickSave()






		;

	}

}
