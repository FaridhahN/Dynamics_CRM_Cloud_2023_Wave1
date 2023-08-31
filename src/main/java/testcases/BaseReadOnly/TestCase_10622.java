package testcases.BaseReadOnly;


import org.testng.annotations.Test;

import pages.LoginPage;

import utils.DataInputProvider;
//TFS_ID_10622:Cloud : Verify whether "Member" Fields are read only for Base Read only role .

public class TestCase_10622 {


	@Test
	public void verifyLimitedMemberNonEditablefields(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Base- Read Only
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Work place >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
		//3.Verify following Fields are NOT editable in Opened Member Account
		.verifyAccountEntityIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName));
		
		//Data Reset -Not Required
	}
}
