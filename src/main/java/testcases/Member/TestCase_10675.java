package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_10675-Cloud: Verify "Referred by" field is read only for Base Read Only  user


public class TestCase_10675 {

	  	
	@Test
	public void verifyBaseReadOnlyEstLoc(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
		//1. Login to CRM as BaseReadOnly 
		new LoginPage()
			.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
			.clickNext()
	  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
	  	    .clicSignin()
	  	    .clicYesInStaySignedin()
		
		//2. Go to Workplace >> Take Any Member Account 
	  	  .selectAccountsTab()		
			.selectActiveMembers()
			.selectAccountFromSearchResults()

		//3.Verify  NIACS code field access 
			.clickNYInformationTab()
			.verifyReferredByIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "referredBy", sDataSheetName));
								
			
		
		//data reset-not required			
			
	
				
			
	}
}
