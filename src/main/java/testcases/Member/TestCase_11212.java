package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_11212:_828837:Cloud: Verify whether Columns "DEA" ,"HIN", "Class of Trade", Name(Group) available on Account Associated View.


public class TestCase_11212 {


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
		
		.selectSubaccount()	
		.clickEditColumn()
		
		.verifySubAccountColumn()
		;
	}
}
