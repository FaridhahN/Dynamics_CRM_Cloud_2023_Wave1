package testcases.Pipeline;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_9111:_828444:_Cloud: Verify "Notes" column in the Pipeline Associated View

public class TestCase_9111 {

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
		.clickNewPipeline()
		.enterPipeleinDetails(DataInputProvider.getCellData_ColName(iRowNumber, "pipelineType", sDataSheetName), TestUtils.todaysDate(), DataInputProvider.getCellData_ColName(iRowNumber, "pipelineStage", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "category", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "leadSource", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "annualizedsales", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "notes", sDataSheetName))
		.clickSaveAndCloseinPipleine()
		.navigateToNotes(DataInputProvider.getCellData_ColName(iRowNumber, "notes", sDataSheetName))


		//Data reset
		.deactivatePipeLine()

		;
	}
}
