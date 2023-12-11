package testcases.DoNotVerifyAddress;

import org.testng.annotations.Test;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_11435:_827975:Verify whether 'Do Not Verify Address' Field is available on Supplier Form and it is defaulted to No.


public class TestCase_11435 {


	@Test
	public void DoNotVerifyAddress(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{

		new WebDriverServiceImpl();
		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.clickNewOnAccountsPage()
		.chooseSupplierForm()
		.navigateToDoNotVerify()
		.verifyDonNotverifydefault()


		;
	}

}
