package testcases.SecurityMemberSupervisor;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//******COMPLETED******

//TFD ID_695331:_695331_Verify "Employee Id" *& "Hire Date" Fields Read Only  on User and Information forms _Member Login

public class TestCase_6152 {

	@Test
	public void validateContactDeactivate(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM as member supervisor
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		// Go to AdvanceSettings
		.selectAdvanceSetting()
		.navigatetoAdvanceSettings("Business Management")	
		.navigateToUserOption()
		.clickUsers()
		.selectUsers()
		.verifyId_Department()
		
		;
	}
}
