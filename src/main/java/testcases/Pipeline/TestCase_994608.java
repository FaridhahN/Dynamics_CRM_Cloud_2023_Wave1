package testcases.Pipeline;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_994608:_994608:Verify the Business at risk 25% pipeline stage is removed in Multipipeline


public class TestCase_994608 {

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
		.verifyPipelineStageinMultipipelein(DataInputProvider.getCellData_ColName(iRowNumber, "pipelineStage", sDataSheetName))
		;
	}
}
