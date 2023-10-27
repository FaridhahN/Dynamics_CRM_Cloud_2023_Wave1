package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_8796:_828825_Cloud : Verify whether On Changing Back and Forth "Is Top Parent", Corporate Parent Relation Date Should Not Be Changed

public class TestCase_8796 {


	@Test
	public void DPChange(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//SEarch an account
		.selectAccountsTab()


		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))


		//Navigate to Top Parent No
		.navigateToApplicationDate()
		.changeTopParentAsNo()

		//Verify Corporate parent DAte is not changes to today date
		.navigateToSponsorMemberForm()
		.navigateToCorporateParentMemberoForm()

		.verifyCorpParenttartDateisNotTodayDate()


		//Navigate to Documents tab

		.NavigateToDocumentTab()
		.clickGeneralTab()

		//Navigate to Top Parent Yes
		.navigateToApplicationDate()
		.changeTopParentAsYes()
		.navigateToSponsorMemberForm()
		.navigateToCorporateParentMemberoForm()
		//Verify Corporate parent DAte is not changes to today date
		
		.verifyCorpParenttartDateisNotTodayDate()

		;
	}
}
