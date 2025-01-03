package testcases.ConvertingAccounts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8868:Verify if a main account which has child accounts can not be converted to a shipto account


public class TestCase_8868 {

	@Test
	public void Verifymainaccounttolocation(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromSearchResults()
		.chooseLocationType("Ship To")
		.clickSave()
		.verifyBusinessError("Since account has child records, It cannot be converted to location account")
		//Store/Location type = Blank
		.chooseLocationTypewithOutVerifying("--Select--")	
		.clickSave()
		;

	}

}
