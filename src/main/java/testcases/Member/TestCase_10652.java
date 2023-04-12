package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10652:Cloud :Automation:Verify Base-Read Only user is not able to add new membership to a Member


public class TestCase_10652 {

	  	
	@Test
	public void verifyBaseReadOnlyMembershipEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
		//1. Login to CRM as Base-Read Only User
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
     		
		//3.Select Membership Entity
	  	   .selectRelatedMembership()
	  	   
	  	   
	  	   // 4.Verify Membership Associated View is displayed
	  	   
	  	   .verifyMembershipAssocView()
	  	   
	  	   
	  	   
	  	 //5.Verify + New Membership Button is not available
	  	   
	  	   .verifyAddNewMembershipIsNotPresent();
		
				
					
		//Data Reset -Not Required
		
		
	
	
	}
}
