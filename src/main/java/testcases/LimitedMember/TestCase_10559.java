package testcases.LimitedMember;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10559-Cloud: Verify whether Limited Member should not be able to  create "Line of business".

public class TestCase_10559 {


	@Test
	public void verifyLineOfBusinessEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		//3.Verify Line of Business Entity for Limited Member

		.clickLineOfBusinesses()
		.verifyLineOfBusinesAssocView()
		.verifyNewLineOfBusinessIsNotPresent();
		
		
		//Data Reset-Not Required


	}
}
