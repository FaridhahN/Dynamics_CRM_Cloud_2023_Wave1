package testcases.Pipeline;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_1004927:_1004927:Verify the Pipeline stage has updated percentage values

public class TestCase_1004927 {


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
//Navigate to pipeline
				
				.navigateToPiepline()
				.clickNewPipeline()
				
				//Verify the pipelinestage
				.verifyPipeleinProbability(DataInputProvider.getCellData_ColName(iRowNumber, "pipelineStage", sDataSheetName))
		;



	}
}
