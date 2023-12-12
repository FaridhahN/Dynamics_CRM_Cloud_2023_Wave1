package testcases.DoNotVerifyAddress;

import org.testng.annotations.Test;

import pages.AccountsPage;
import pages.DashboardPage;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_827978:_827978:Verify whether on update Account Name to any , Address Field i.e. Street1 should not get auto updated.


public class TestCase_827978 {


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

		.chooseMemberForm()
		.clickSave()
		.navigateToDoNotVerifyEnterData(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))
		.selectDonotVerify("No")
		.clickSave()
		.clickSystemTab()
		.verifyMellissa()
		.verifyMellissakeybaseisNotNull()

		
		.clickGeneralTab()
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "UpdateStreet1", sDataSheetName))
		.clickSave()
		.clickSystemTab()
		.verifyMellissa()
		.verifyMellissakeybaseisNull()
		
		.clickGeneralTab()
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "UpdateName", sDataSheetName))
		.clickSave()
		.verifystreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))
		
		
		//Data Reset
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "UpdateName1", sDataSheetName))
		.typeStreet1("Test")
		.clickSave()
		.navigateToDoNotVerify()
		.selectDonotVerify("Yes")
		.clickSave()

		;

		
		


	}

}
