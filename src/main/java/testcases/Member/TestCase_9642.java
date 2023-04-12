package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_ 7137:Create new member - New Member form and Save it as prospect first

public class TestCase_9642 {


	@Test
	public void createMember(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. Go to Accounts and click on New button **** New account form should be displayed 
		.selectAccountsTab()
		.clickNewOnAccountsPage()
		.chooseMemberForm()
		.verifySaveAndNewButtonNotDisplayed()

		;
	}
}
