package testcases.Contacts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//Test Case 8772:Cloud : Verify if member/member supervisor/Supplier/Supplier supervisor/Limited member can edit the primary account of a contact

public class TestCase_8772 {


	@Test
	public void updatPrimaryAccount(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContacts()

		//Navigate to All Account

		.selectAllContactView()
		// 2. Go to any active contact which has an active contact account association
		.chooseActiveContact(DataInputProvider.getCellData_ColName(iRowNumber, "activeContact", sDataSheetName))

		// Primary Account = 1000155094
		.addAnotherPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount", sDataSheetName))

		// Save the record
		.clickSave()

		// Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		.goBackandSelectAccount()
		
		// Primary Account = 1000155094
		.addAnotherPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount1", sDataSheetName))

		// Save the record
		.clickSave()

		.clickSignout() ;
		
	

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage() .refreshPageChooseSignInUser().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email1", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContacts()

		//Navigate to All Account

		.selectAllContactView()
		// 2. Go to any active contact which has an active contact account association
		.chooseActiveContact(DataInputProvider.getCellData_ColName(iRowNumber, "activeContact", sDataSheetName))

		// Primary Account = 1000155094
		.addAnotherPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount", sDataSheetName))

		// Save the record
		.clickSave()

		// Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		.goBackandSelectAccount()
		// Primary Account = 1000155094
		.addAnotherPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount1", sDataSheetName))

		// Save the record
		.clickSave()

		.clickSignout();
		

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage().refreshPageChooseSignInUser().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContacts()

		//Navigate to All Account

		.selectAllContactView()
		// 2. Go to any active contact which has an active contact account association
		.chooseActiveContact(DataInputProvider.getCellData_ColName(iRowNumber, "activeContact", sDataSheetName))

		.selectViewAtContact("Contact")
		// Primary Account = 1000155094
		.addAnotherPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount", sDataSheetName))

		// Save the record
		.clickSave()

		// Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		.goBackandSelectAccount()
		// Primary Account = 1000155094
		.addAnotherPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount1", sDataSheetName))

		// Save the record
		.clickSave()

		.clickSignout();
		
		
		
		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage().refreshPageChooseSignInUser().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email3", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContacts()

		//Navigate to All Account

		.selectAllContactView()
		// 2. Go to any active contact which has an active contact account association
		.chooseActiveContact(DataInputProvider.getCellData_ColName(iRowNumber, "activeContact", sDataSheetName))

		// Primary Account = 1000155094
		.addAnotherPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount", sDataSheetName))

		// Save the record
		.clickSave()

		// Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		.goBackandSelectAccount()
		// Primary Account = 1000155094
		.addAnotherPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount1", sDataSheetName))

		// Save the record
		.clickSave()

		.clickSignout();
		
		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage().refreshPageChooseSignInUser().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email4", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContacts()

		//Navigate to All Account

		.selectAllContactView()
		// 2. Go to any active contact which has an active contact account association
		.chooseActiveContact(DataInputProvider.getCellData_ColName(iRowNumber, "activeContact", sDataSheetName))

		// Primary Account = 1000155094
		.addAnotherPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount", sDataSheetName))

		// Save the record
		.clickSave()

		// Observe the Innovatix Contact ID field
		.isInnovatixContactIDDisplayed()

		.goBackandSelectAccount()
		// Primary Account = 1000155094
		.addAnotherPrimaryAccount(
				DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount1", sDataSheetName))

		// Save the record
		.clickSave()

		.clickSignout();
	}
}
