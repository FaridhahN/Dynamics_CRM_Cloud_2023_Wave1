package testcases.BaseReadOnly;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10619-Cloud : Verify whether Base Read only role cannot create "LOB" via Line of Business Entity

public class TestCase_10619 {


	@Test
	public void verifyLineOfBusinessEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Base-Read Only
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

		//3.Verify Line of Business Entity for Base-Read Only

		.clickLineOfBusinesses()
		.verifyLineOfBusinesAssocView()
		.verifyNewLineOfBusinessIsNotPresent();
		
		
		//Data Reset-Not Required


	}
}
