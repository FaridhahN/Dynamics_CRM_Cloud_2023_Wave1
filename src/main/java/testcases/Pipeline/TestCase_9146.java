package testcases.Pipeline;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_9146:_828452:_Cloud: Pipeline type Business at risk should have Business at risk Pipeline stage only and vice versa


public class TestCase_9146 {

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
		.clicksaveinPipeline()
		.verifyError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		;
	}
}
