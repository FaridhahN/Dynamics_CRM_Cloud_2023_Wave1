package testcases.SecurityLimitedMember;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//******COMPLETED******

//TFD ID_695326:_695326_Verify "Employee Id" *& "Hire Date" Fields Read Only  on User and Information forms_Member Supervisor Login

public class TestCase_695326 {

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
		.verifyId_HireFeild()
		
		;}
}
