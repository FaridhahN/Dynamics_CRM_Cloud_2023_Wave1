package testcases.LimitedMember;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_9649-Cloud : Cloud: Verify if Limited member can access "Referred by" field

public class TestCase_9649 {


	@Test
	public void verifyLimitedMemberEditablefields(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Limited member 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		.navigateToDoNotVerify()
		.navigatetNYInformation()

		//3.Verify  Referred By field access 
		.verifyEstLocIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "numEstLoc", sDataSheetName))
		.navigatetoNAICS()
		.verifyReferredByIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "referredBy", sDataSheetName))						
		.clickNyTabSave()



		//Data Reset
		.removeReferredBy();





	}
}
