package testcases.LimitedMember;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10596-Cloud :Verify Limited Member will not able to add new membership to a Prospect.

public class TestCase_8875 {

	  	
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
		.clickNewOnAccountsPage()
		.chooseMemberForm()

		//3. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Click on save 			
		.clickSave() 


		.navigateToDP()
		
		.EnterPrimaryAccount(DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName", sDataSheetName))
		.clickGeneralTab()
		.clickSave()
		.navigateToDP()
		.updatePrimaryAccount(DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName1", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName1", sDataSheetName));
		
		
		;
				
					
		//Data Reset -Not Required
		
		
		
			
	}
}
