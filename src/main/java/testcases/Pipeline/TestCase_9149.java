package testcases.Pipeline;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_9149:_828455:_Cloud: Pipeline - Validate Admin Fee % is triggered from Revenue category selected


public class TestCase_9149 {

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
		
		.verifyAdminFee(DataInputProvider.getCellData_ColName(iRowNumber, "adminFee", sDataSheetName))
		.clearRevenueCategory()
		.selectRevenueCategory(DataInputProvider.getCellData_ColName(iRowNumber, "category1", sDataSheetName))
		.verifyAdminFee(DataInputProvider.getCellData_ColName(iRowNumber, "adminFee1", sDataSheetName))
		;
	}
}
