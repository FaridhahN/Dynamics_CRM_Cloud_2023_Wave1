package testcases.Accounts;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_8598:828596_Verify new field "NAICS Code" is added in member form

public class TestCase_8598 {


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
		.clickNewOnAccountsPage()
		
		.chooseMemberForm()

		//3. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Click on save 			
		.clickSave() 
		
		.navigateToDoNotVerify()
		.navigatetNYInformation()
		
		.verifyNAICSIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "nAICS", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "industryTitle", sDataSheetName))


		;



	}
}
