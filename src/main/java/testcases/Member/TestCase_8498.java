package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_8498:_828592:Verify for "Prospect" Account, on update FBO Manual Override field as "Member" login "Error Message" should be thrown.

public class TestCase_8498 {

	@Test
	public void verifyProspect(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.clickNewOnAccountsPage()
		.chooseMemberForm()

		//Enter the Account name and Enter the FBO details by making FBO manual override
		.typeAccountName("TestFBO")
		.navigateToDoNotVerify()
		.navigateToAG()
		.selectFBOOverride(DataInputProvider.getCellData_ColName(iRowNumber, "FBOManualOverride", sDataSheetName))
		.selectisFBO(DataInputProvider.getCellData_ColName(iRowNumber, "verifyIsFBO", sDataSheetName))
		.selectFBO(DataInputProvider.getCellData_ColName(iRowNumber, "FBO", sDataSheetName))
		.selectFBORelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "FBOEffectiveDate", sDataSheetName))
		.selectFBOGPO(DataInputProvider.getCellData_ColName(iRowNumber, "FBOGPO", sDataSheetName))
		.selectFBOEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "FBOEffectiveDate", sDataSheetName))
		.selectFBOGPOType(DataInputProvider.getCellData_ColName(iRowNumber, "FBOType", sDataSheetName))
		//Verify error
		.clickSave()
		.verifyError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))

		//Check for the Member entry form
		.NavigateToSystemTab()
		.clickOnDiscardChanges()
.clickGeneralTab()
		//convert to Member Entry form and update the FBO details
		.chooseMemberEntryForm()
		.typeAccountName("TestFBO")
		.navigateToDoNotVerifyMEF()
		.navigatetoAdditionaCriteriaFromDonotVerify()
		.navigateToAGDateMEF()
		.selectFBOOverrideMemberEntryForm(DataInputProvider.getCellData_ColName(iRowNumber, "FBOManualOverride", sDataSheetName))
		.selectisFBO(DataInputProvider.getCellData_ColName(iRowNumber, "verifyIsFBO", sDataSheetName))
		.selectFBO(DataInputProvider.getCellData_ColName(iRowNumber, "FBO", sDataSheetName))
		.selectFBORelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "FBOEffectiveDate", sDataSheetName))
		.selectFBOGPO(DataInputProvider.getCellData_ColName(iRowNumber, "FBOGPO", sDataSheetName))
		.selectFBOEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "FBOEffectiveDate", sDataSheetName))
		.selectFBOGPOType(DataInputProvider.getCellData_ColName(iRowNumber, "FBOType", sDataSheetName))
//verify permission error message
		.clickSave()
		.verifyError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		;
	}
}
