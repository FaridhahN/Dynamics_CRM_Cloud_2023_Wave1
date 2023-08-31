package testcases.BaseReadOnly;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10620:Cloud : Verify whether Base Read only role cannot create "Membership" via Membership Entity


public class TestCase_10620 {

	  	
	@Test
	public void verifyBaseReadOnlyMembershipEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
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
		.verifyEndReasonIsNotEditable()
		.clickGoBack()
		.verifyAddNewMembershipIsNotPresent();
	
	//Data Reset -Not Required.
	}
}
