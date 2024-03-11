package testcases.LimitedMember;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10557-Cloud: Verify whether Limited Member should have access to "Activities" Entity.

public class TestCase_10557 {

	  	
	@Test
	public void verifyActivitiesEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
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

		//3.Verify Activities Entity for Limited Member
		.clickRelatedActivities()
		.verifyOpenActivityAssocView()
		.clickNewTaskActivity()
		.typeDueDate()
		.typeSubjectOnTask(DataInputProvider.getCellData_ColName(iRowNumber, "accountname", sDataSheetName))
		.typeDescriptionOnTask(DataInputProvider.getCellData_ColName(iRowNumber, "accountname", sDataSheetName))
		.clickSaveAndCloseOnActivity()
		.verifyTaskSubjectOnOpenActivityAssocView(DataInputProvider.getCellData_ColName(iRowNumber, "accountname", sDataSheetName))
		
				
					
		//Data Reset
		.completeAllTask();
		
		
			
	}
}
