package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_8804 :_30830_Cloud - Verify User should be able to override "CP and FSP" values  and user should not allow to choose "Supplier" as CP or FSP.

public class TestCase_8804 {


	@Test
	public void VerifySupplierAsCP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//Go to Workplace > Accounts and search  
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))

		//Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		.navigateToroster()
		.navigateToSponsorMemberForm()
		.navigateToCorporateParentMemberoForm()
		.navigateToFoodServices()
		.changeCorpParentOverride("Yes")
		.changeIsCorporateParent("No")
		.clearCP()
		.noMatchforCorporateParent(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput1", sDataSheetName))
		


		;
	}
}
