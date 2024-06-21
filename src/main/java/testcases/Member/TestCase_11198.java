package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_11198:_828832_Cloud: Verify if the State field in address only accept two characters or blank values, no numeric and/or special characters



public class TestCase_11198 {


	@Test
	public void DPchange(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//From the account search for the existing account and select
		.selectAccountsTab()


		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
		.chooseMemberForm()
		
		.navigateToDoNotVerify()
		
		.selectDonotVerify("Yes")
		
		.clickSave()
		
		.NavigateToDocumentTab()
		.clickGeneralTab()
		
		.navigateToState()
		.typeState("NYX")
		.clickSave()
		
		.verifyErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		
		
		.typeState("12")
		.clickSave()
		
		.verifyErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage1", sDataSheetName))
		
		
		.typeState("@#")
		.clickSave()
		
		.verifyErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage1", sDataSheetName))
		
		
		.typeState("CO")
		.clickSave()
		
		.verifyErrorisNotDisplayed()
		
		;
	}
}
