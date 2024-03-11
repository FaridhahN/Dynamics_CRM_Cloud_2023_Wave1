package testcases.LimitedMember;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_8877:_828059:Cloud : Verify if Limited Member can create and add a primary contact to a prospect and Member form without throwing any Error.

public class TestCase_8877 {


	@Test
	public void verifyMembershipEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		try {
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
		.updatePrimaryAccount(DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName1", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName1", sDataSheetName))
		.clickSave()

		;

		new DashboardPage()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.chooseMemberForm()


		//Click on Save 
		.clickSave() 

		.navigateToDP()
		.clearPrimaryAccount()
		.EnterPrimaryAccount(DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName", sDataSheetName))
		.clickGeneralTab()
		.clickSave()
		.navigateToDP()
		.updatePrimaryAccount(DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "contactFirstName1", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "contactLastName1", sDataSheetName))
		.clickSave()
		.EndCAA()
		.clickSave()
		;
		}catch(Exception e) {
			new DashboardPage()

			//2. Go to Workplace >> Take Any Member Account 
			.selectAccountsTab()		
			.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
			.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
			.chooseMemberForm()
.verifyContactAccountAssociation()

			//Click on Save 
			
			.EndCAA()
			.clickSave()

			
			;
		}
		
		//Data Reset -Not Required




	}
}
