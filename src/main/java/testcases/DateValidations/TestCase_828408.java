package testcases.DateValidations;


import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_828408:_828408:Verify Application start date validation is removed when terminating an account

public class TestCase_828408{

	
	@Test
	public void DateValidation(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
		//1. Login to CRM using member supervisor / member credentials 
				new LoginPage()

				.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
				.clickNext()
				.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
				.clicSignin()

				.clicYesInStaySignedin()

				//2. From the left navigation column ,Go to Accounts > +New
				.selectAccountsTab()


				.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
				.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
				
				.chooseRecordStatusDraftfromTop()
				.clickSave()
				.NavigateToSystemTab()
				.clickGeneralTab()
				.navigateToApplicationDate()
				.chooseTodayAsApplicationDate()
				.clickSave()
				.verifyErrorisNotDisplayed()
				
				
				
				;
	}
}
