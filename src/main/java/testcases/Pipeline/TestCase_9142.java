package testcases.Pipeline;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_9142:_828448:Cloud: Verify Multiple Pipeline fee share accept only valid values

public class TestCase_9142 {

	@Test
	public void verifyPipelineStage(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Member with Pipeline Steward Role
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		.navigateToPiepline()
		.clickNewMultiplePipeline()
		.enterFeeshareInMultiplePipeline(DataInputProvider.getCellData_ColName(iRowNumber, "feeshare", sDataSheetName))
		.selectLeadSourceinMultipipeline(DataInputProvider.getCellData_ColName(iRowNumber, "leadSource", sDataSheetName))
		.clickSaveAndCloseMultipipleine()
		.verifyAlertIsDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		
		;
	}
}
