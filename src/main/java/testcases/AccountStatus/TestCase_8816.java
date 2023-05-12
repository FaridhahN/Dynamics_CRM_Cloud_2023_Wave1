package testcases.AccountStatus;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_ 7137:Create new member - New Member form and Save it as prospect first

public class TestCase_8816 {


	@Test
	public void createMember(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()

		.clickNewOnAccountsPage()
		.chooseMemberForm()

		//3. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
		.selectAccountStatus("Terminated")
		//Click on save 			
		.clickSave() 
		 

		//4. Verify Error message for Terminate Status
		.verifyTerminateStatusError()	


		//5. Select Account type as Member
		.selectAccountType("Member")

			

		//7. Select Direct Parent Entity code
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//8. Direct Parent Relation = Managed
		.selectDirectParentRelationManaged() 

		//9. Direct Parent Relation date 
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		//10. Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		//11. Top Parent Relation Date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		//6. Participation Type = Standard
				.selectParticipationType("Standard")
				
		//12. Click on save 			
		.clickSave() 
		
		
		//13. Verify Error message for 
		.verifyTerminateStatusError()	

		//14. Change the account Location type to Bill to
		.chooseLocationType("Bill To")

		//15. Click on save 			
		.clickSave() 
		 
		//16. Verify Error message for 
		.verifyTerminateStatusError()
		
		
		//17. Change the account Location type to Ship to
		.chooseLocationType("Ship To")

		//18. Click on save 			
		.clickSave() 
		 
		//19. Verify Error message for Terminate status
		.verifyTerminateStatusError()
		
		//20. Change the account Location type to Mail to
		.chooseLocationType("Mail To")

		//21. Click on save 			
		.clickSave() 
		
		
		//22. Verify Error message for Terminate status 
		.verifyTerminateStatusError()	

		
		//23. Change the account Location type to RemiT to
		.chooseLocationType("Remit To")

		//24. Click on save 			
		.clickSave() 
				
				
		//25. Verify Error message for Terminate status
		.verifyTerminateStatusError()	
		;
	}
}
