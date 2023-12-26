package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
// TFS ID_8497:_828591:Verify User should be able to override "CP and FSP" values and user should not allow to choose "Supplier" as CP or FSP.

public class TestCase_8497 {


	@Test
	public void CPFSPOverride(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{
		//1. Login to CRM using member supervisor / member credentials
		WebDriverServiceImpl.isMemberForm=true;
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
		.chooseMemberEntryForm()
		.navigateToDoNotVerify()
		.navigatetNYInformation()
		.changeCorpParentOverride(DataInputProvider.getCellData_ColName(iRowNumber, "corpParentOverride", sDataSheetName))
		.changeIsCorporateParent(DataInputProvider.getCellData_ColName(iRowNumber, "verifyIsCorporateAccount", sDataSheetName))
	
		.noMatchforCorporateParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))
		
		;

	}

}
