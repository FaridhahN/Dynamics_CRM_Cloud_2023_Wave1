package testcases.AccountStatus;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_6155:_828143:Verify user should NOT be able to change the record status from Active to terminated manually

public class TestCase_6155 {


	@Test
	public void verifyAccountStatus(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		//Move record status to draft from published
		.chooseRecordStatusDraftfromTop()

		//Click on Save 
		.clickSave() 

		//Click on Save 
		.clickSystemTab()
		.clickGeneralTab()
		.navigateToApplicationDate()
		.selectAccountStatus("Terminated")

		.clickSave()

		.verifyErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))

		//Data Reset
		.clickSystemTab()
		.clickGeneralTab()
		.chooseRecordStatusPublished()
		.clickSave()


		;
	}
}
