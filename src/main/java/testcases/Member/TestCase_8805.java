package testcases.Member;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;

public class TestCase_8805 {
//Test Case 8805:Automation Cloud - Verify for "Prospect" Account, on update FBO Manual Override field as "Member" login "Error Message" should be thrown.


	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace > Accounts > +New 
		.selectAccountsTab()
		.clickNewOnAccountsPage()
		
		.chooseMemberForm()
		//3. Account Name
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Click on Save

		.clickSave() 

		.selectFBOOverride(DataInputProvider.getCellData_ColName(iRowNumber, "FBOManualOverride", sDataSheetName))

		.selectFBO(DataInputProvider.getCellData_ColName(iRowNumber, "fBO", sDataSheetName))

		.selectisFBO(DataInputProvider.getCellData_ColName(iRowNumber, "verifyIsFBO", sDataSheetName))

		.selectFBOEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "FBOEffectiveDate", sDataSheetName))

		.selectFBOGPOType(DataInputProvider.getCellData_ColName(iRowNumber, "FBOType", sDataSheetName))

		.selectFBORelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "verifyFBORD", sDataSheetName))

		.selectFBOGPO(DataInputProvider.getCellData_ColName(iRowNumber, "FBOGPO", sDataSheetName))


		//Click on Save 
		.clickSave() 
		.verifyMemberPermissionError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		.selectAccountsTab()
		.clickOnDiscardChanges()


		;	

		new DashboardPage()

		.selectAccountsTab()
		.clickNewOnAccountsPage()

		.chooseMemberEntryForm()

		//3. Account Name
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Click on Save

		.clickSave() 
		.navigateToDoNotVerifyMemberEntryForm()
		
.navigatetoAdditionaCriteriaFromDonotVerify()
		.selectFBOOverrideMemberEntryForm(DataInputProvider.getCellData_ColName(iRowNumber, "FBOManualOverride", sDataSheetName))

		.selectFBO(DataInputProvider.getCellData_ColName(iRowNumber, "fBO", sDataSheetName))

		.selectisFBO(DataInputProvider.getCellData_ColName(iRowNumber, "verifyIsFBO", sDataSheetName))

		.selectFBOEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "FBOEffectiveDate", sDataSheetName))

		.selectFBOGPOType(DataInputProvider.getCellData_ColName(iRowNumber, "FBOType", sDataSheetName))

		.selectFBORelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "verifyFBORD", sDataSheetName))

		.selectFBOGPO(DataInputProvider.getCellData_ColName(iRowNumber, "FBOGPO", sDataSheetName))


		//Click on Save 
		.clickSave() 
		.verifyMemberPermissionError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))



		;
	}
}
