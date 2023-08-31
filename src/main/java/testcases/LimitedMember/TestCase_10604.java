package testcases.LimitedMember;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10604-Cloud:Automation:Verify whether Limited Member should not be able to  add and create "Competitor".

public class TestCase_10604 {


	@Test
	public void verifyCompetitorEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		//3.Verify Competitors Entity for Limited Member

		.clickCompetitors()
		.verifyCompetitorNameAssocView()
		.verifyNewCompetitorIsNotPresent();
		
		
		//Data Reset-Not Required


	}
}
