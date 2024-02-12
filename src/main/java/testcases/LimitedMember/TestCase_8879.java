package testcases.LimitedMember;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_8879-Cloud : Limited member should have access to "NAICS code"

public class TestCase_8879 {


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
		.chooseMemberForm()

		.clickSave()
		
		.navigateToDoNotVerify()
		.navigatetNYInformation()
		//3.Verify  NIACS code field access 
		.verifyEstLocIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "numEstLoc", sDataSheetName))

		.verifyNAICSIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "nAICS", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "industryTitle", sDataSheetName))


		.clickNyTabSave()

		//data reset
		.removeNAICS();




	}
}
