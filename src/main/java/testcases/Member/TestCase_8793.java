package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_8793:_828824_Cloud : Verify whether Member User is able to publish and modify existing editable fields with out throwing any error on save

public class TestCase_8793 {


	@Test
	public void UpdateExsistingmember(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//SEarch an account
		.selectAccountsTab()


		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Update Participation type
		
		.navigateToParticipationType()
		
		//Update Stree value
		.typeStreet2(DataInputProvider.getCellData_ColName(iRowNumber, "street2", sDataSheetName)) 


		//Participation Type
		.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))
		.clickSave()

		//Verify Street 1 feild is displayed in the audit history
		.selectMembershipAuditHistory()
		.verifyupdateinAudithistory("Street 2", DataInputProvider.getCellData_ColName(iRowNumber, "street2", sDataSheetName))
		.verifyupdateinAudithistory("Participation Type", DataInputProvider.getCellData_ColName(iRowNumber, "Participation Type", sDataSheetName))

		//Navigate to Documents tab

		.NavigateToDocumentTab()
		.clickGeneralTab()

		//Update Stree value
		.typeStreet2("234 B street") 


		//Update Participation type
		.navigateToParticipationType()

		//Participation Type = Standard
		.selectParticipationType("Standard")
		.clickSave()

		;

		;
	}
}
