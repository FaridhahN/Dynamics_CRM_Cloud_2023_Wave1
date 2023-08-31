package testcases.LimitedMember;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10596-Cloud :Verify Limited Member will not able to add new membership to a Prospect.

public class TestCase_10596 {

	  	
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
		
		//2. From the left navigation column ,Go to Accounts > +New
		.clickNewOnAccountsPage()
		.chooseMemberForm()
	  
  	  	//3. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
	
		//Click on save 			
		.clickSave() 
	
		//4. Verify CRM Account # is generated 
		.verifyCRMNumberIsDisplayed()
		
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
		
				
					
		//Data Reset -Not Required
		
		
		
			
	}
}
