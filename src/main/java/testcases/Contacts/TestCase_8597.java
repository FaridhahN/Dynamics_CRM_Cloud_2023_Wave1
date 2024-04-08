package testcases.Contacts;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_8597:_828715_: Verify whether user is able to add "Mobile Number" in Contact Entity and is appearing on contact form & Contact associated view.

public class TestCase_8597 {

	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Accounts > +New 
		.selectAccountsTab()

		//Search Account
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Click Contact from Related option
		.clickRelatedContacts()

		//click Add new contact 
		.clickNewContact()
		//Add new contact
		.addNewContactToMember((DataInputProvider.getCellData_ColName(iRowNumber, "firstName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "lastName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "mainPhone", sDataSheetName)))

		//Click Existing contact
		.doubleclickexistingConact()

		//Click Additional infromation
		.clickAdditionalInfromation()

		//Verify PHone number
		.verifyPhonenumber(DataInputProvider.getCellData_ColName(iRowNumber, "mainPhone", sDataSheetName))
		;
	}
}
