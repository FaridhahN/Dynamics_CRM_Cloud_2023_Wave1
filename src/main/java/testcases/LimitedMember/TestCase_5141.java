package testcases.LimitedMember;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10596-Cloud :Verify Limited Member will not able to add new membership to a Prospect.

public class TestCase_5141 {


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

		.navigateToDP()
		.verifyPrimaryContactIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))
		.clickSave()

		.verifyPrimaryAccountNameinCAA()


		;
		new DashboardPage()
		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		

		//2. From the left navigation column ,Go to Accounts > +New
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))


		.navigateToDP()
		.clearPrimaryAccount()
		.verifyPrimaryContactIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))
		.clickSave()

		.verifyPrimaryAccountNameinCAA()

		;



		//Data Reset -Not Required




	}
}
