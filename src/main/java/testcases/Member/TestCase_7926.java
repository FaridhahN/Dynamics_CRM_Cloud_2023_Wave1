package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_ 7926 :Cloud : Verify limited member will not able to terminate a member


public class TestCase_7926 {

	  	
	@Test
	public void verifyLimMemberAccountTermination(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
		//1. Login to CRM as Limited member 
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
     		

		//3.Verify Membership Entity fields and check account termination
			.clickExistingMembershipOnMemberForm()
				
		
		//6.Verify and Observe the following fields are read only. 		
			
		.verifyMembershipEntityIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))		
		.verifyAccountOnMembershipIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage1", sDataSheetName))
		.verifyMembershipTypeIsNotEditable()
		.verifyMPOnMembershipIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage2", sDataSheetName))
		.verifyStopCascadeIsNotEditable()
		.verifyStartDateIsNotEditable()
		.verifyEndDateIsNotEditable()
		.verifyMembershipLevelIsNotEditable()
		.verifyEndReasonIsNotEditable();
	
	
	}
}
