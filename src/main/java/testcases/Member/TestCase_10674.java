package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_10674-Cloud :Base Read Only should NOT have access to "# of Estimated Locations"

public class TestCase_10674 {


	@Test
	public void verifyBaseReadOnlyEstLoc(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as BaseReadOnly 
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

		//3.Verify  NIACS code field access 
		.clickNYInformationTab()
		.verifyEstimatedNumLocationsIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "numEstLoc", sDataSheetName));



		//data reset-not required





	}
}
