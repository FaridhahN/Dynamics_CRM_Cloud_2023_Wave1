package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10592-Cloud :Verify Limited Member will not able to add new membership to a Member.

public class TestCase_10592 {

	  	
	@Test
	public void verifyMembershipEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
		//1. Login to CRM as Limited member 
		new LoginPage()
			.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
			.clickNext()
	  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
	  	    .clicSignin()
	  	    .clicYesInStaySignedin()
		
		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.selectDraftMembers()
		.selectAccountFromDraftMembersView()
		
		//3.Verify Membership Entity for Limited Member
		.clickMembershipAndAddNewMembership()
		
		// Choose Membership type 
	 	.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
		
		//Provide any start date and click on save
		.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))
		
		//Click on Membership save and close and Verify Error Message		
		.verifyMembershipErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errormessage", sDataSheetName))
		
		.clickOKOnMembershipErrorMessage();
		
							
		//Data Reset-Not Required
		
		
		
			
	}
}
