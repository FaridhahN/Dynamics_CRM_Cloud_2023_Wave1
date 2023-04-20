package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_8908:_709834_Cloud: Verify DEA and HIN number field are available in the Accounts view

public class TestCase_8908 {

	  	
	@Test
	public void VerifyHINInput(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()
		.selectActiveMembers()
		.verifyAccountnumberColumns()
		.searchString(DataInputProvider.getCellData_ColName(iRowNumber, "DEANumber", sDataSheetName))
		.verifyAccountisDisplayed()
		.clickclearButton()
		.searchString(DataInputProvider.getCellData_ColName(iRowNumber, "HIN", sDataSheetName))
		.verifyAccountisDisplayed()
		;
	}
}
