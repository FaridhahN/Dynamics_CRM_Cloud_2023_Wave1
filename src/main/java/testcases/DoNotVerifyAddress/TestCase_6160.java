package testcases.DoNotVerifyAddress;

import org.testng.annotations.Test;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//Test Case 6160:Verify whether if 'Do Not Verify Address' is Set to 'Yes', Address Cleansing should be Disabled on Member Form.


public class TestCase_6160 {


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
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		.navigateToDoNotVerifyEnterData(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))

		.selectDonotVerify("No")

		.clickSave()

		.NavigateToSystemTab()
		.clickGeneralTab()
		.navigateToState()
		.getStateValue()
		.typeState("SS")
		.clickSave()

		.NavigateToSystemTab().clickGeneralTab()
		.navigateToState()
		.getStateValue()
		.verifyStateValue("SS", true)
		.clickSave()
		.NavigateToSystemTab()
		.verifyMellissa()
		
		.clickGeneralTab()
		
		.navigateToDoNotVerifyEnterData(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))

		.selectDonotVerify("Yes")

		.clickSave()

		.NavigateToSystemTab().clickGeneralTab()
		.navigateToState()
		.getStateValue()
		.typeState("SS")
		.clickSave()

		.NavigateToSystemTab().clickGeneralTab()
		.navigateToState()
		.getStateValue()
		.verifyStateValue("SS", false)
		.clickSave()
		.NavigateToSystemTab()
		.verifyMellissaisDisabled()
		
		;
	}

}
