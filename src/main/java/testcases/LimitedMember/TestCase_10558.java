package testcases.LimitedMember;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10558-Cloud : Verify whether Limited Member should have access to "Contact Entity"

public class TestCase_10558 {


	@Test
	public void verifyContactsEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Limited member 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.selectActiveMembers()
		.selectAccountFromSearchResults()

		//3.Verify Contacts Entity for Limited Member
		
		.clickRelatedContacts()
		.clickNewContact()
		.addNewContactToMember((DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName)),
				(DataInputProvider.getCellData_ColName(iRowNumber, "mainPhone", sDataSheetName)))

		
		//Data Reset
		.deactivateContact((DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName)));




	}
}
