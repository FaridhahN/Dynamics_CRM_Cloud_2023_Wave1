package testcases.SecurityMemberSupervisor;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//******COMPLETED******

//TFD ID_5035:_32865_Cloud : Verify Active Contact account association view does not show end dated records_Member Supervisor Login

public class TestCase_5035 {

	@Test
	public void validateContactCAA(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM as member supervisor
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to Contacts
		.selectContacts()

		//Navigate to All Account

		.selectAllContactView()

		// 2. Go to any active contact which has an active contact account association
		.chooseActiveContact(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.verifyDeactivatedAcocunt(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber1", sDataSheetName))
		
		.verifyActivatedAcocunt(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber1", sDataSheetName))
		//Data Reset
		
	;
	}
}
