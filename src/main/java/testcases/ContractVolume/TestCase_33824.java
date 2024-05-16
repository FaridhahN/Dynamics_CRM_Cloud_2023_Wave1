package testcases.ContractVolume;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_33824:_11388 :Cloud : Verify user need a special permission to create a contract volume in crm


public class TestCase_33824 {

	  	
	@Test
	public void verifyAccountNumberEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
		//1. Login to CRM as Member
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

		//Navigate to Contract volume
		.selectRelatedContractVolume()
		
		//verify Cotnract volume for user admin
		.verifyNewContractAttachmentButton()
		
		
		//Data Reset 
				.clickSignout()
				.pageRefresh()
				;

				new LoginPage()
				.refreshPageChooseSignInUser()
				.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
				.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
				.clicSignin().clicYesInStaySignedin()
				//2. Go to Workplace >> Take Any Member Account 
				.selectAccountsTab()		
				.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
				.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

				//Navigate to Contract volume
				.selectRelatedContractVolume()
				
				//verify Cotnract volume for user admin
				.verifyNewContractAttachmentButtonisnotDisplayed()

		;
		}
}
