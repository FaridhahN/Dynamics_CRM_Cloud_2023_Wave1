package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10669:Cloud : Automation:Verify whether Base Read Only user should not be able to  create "Member attributes".


public class TestCase_10669 {


	@Test
	public void verifyMemberAttributesEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Base-Read Only
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

		//3.Verify Member Attributes Entity for Base Read Only

		.selectRelatedMemberAttributesForLimMem()
		.verifyAccountMemberAttributesAssocView()
		.verifyNewAccountMembersAttributesIsNotPresent();

		//Data Reset-Not Required




	}
}
