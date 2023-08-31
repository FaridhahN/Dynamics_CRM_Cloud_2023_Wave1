package testcases.BaseReadOnly;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10644-Cloud :Verify Base-Read Only will not able to add new membership to a prospect

public class TestCase_10644 {

	  	
	@Test
	public void verifyMembershipEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
		//1. Login to CRM as Base-Read Only 
		new LoginPage()
			.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
			.clickNext()
	  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
	  	    .clicSignin()
	  	    .clicYesInStaySignedin()

	  	 //2. Go to Workplace >> Take Any Prospect Account 
	  	   .selectAccountsTab()
	  	   .selectAllProspects()
	  	   .selectAccountFromAllProspectsView()
	  	   
	  	   
	  	 //3.Select Membership Entity
	  	   .selectRelatedMembership()
	  	   
	  	   
	  	   // 4.Verify Membership Associated View is displayed
	  	   
	  	   .verifyMembershipAssocView()
	  	   
	  	   
	  	   
	  	 //5.Verify + New Membership Button is not available
	  	   
	  	   .verifyAddNewMembershipIsNotPresent();
		
				
					
		//Data Reset -Not Required
		
		
		
			
	}
}
