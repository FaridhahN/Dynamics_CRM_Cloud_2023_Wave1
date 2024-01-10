package testcases.DateValidations;


import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_828410:_828410:Verify Application start date validation is removed by changing them in existing account

public class TestCase_828410{

	
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
				
				.CompareDPRDTPRD()
				.chooseApplicationDate(TestUtils.enddate)
				.chooseRecordStatusPublished()
				.clickSave()
				
				//VErify error message is not displayed
				.verifyErrorisNotDisplayed()
				
				;
	}
}
