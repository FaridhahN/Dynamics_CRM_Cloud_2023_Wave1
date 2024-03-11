package testcases.Pipeline;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_9113:_828445: Cloud: Verify Multiple Pipeline fee share does not accept null values


public class TestCase_9113 {

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
		.clearFeeshareInMultiplePipeline()
		.selectPipelineStageinMultipipeline(DataInputProvider.getCellData_ColName(iRowNumber, "pipelineStage", sDataSheetName))
		.selectLeadSourceinMultipipeline(DataInputProvider.getCellData_ColName(iRowNumber, "leadSource", sDataSheetName))
		.clickSaveAndCloseMultipipleine()
		.verifyAlertIsDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		
		;
	}
}
