package testcases.SecurityInnovatixUser;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_1004581:_1004581_Verify user able to create Email list.

public class TestCase_1004581 {


	@Test
	public void SupplierAccountNumber(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for  
		.changeToMarketing()
		.clickEmailList()
		.clickNewEmailList()
		.enterEmailDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Name", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "Type", sDataSheetName))
		.VerifyErrorMessageNotDisplayed()
		;
	}

}
