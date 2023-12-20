package testcases.BusinessClassification;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_978018:_978018:Verify whether on update COT - 'Charity', Business Classification 'Non Healthcare' is getting auto populated.

public class TestCase_978018 {


	@Test
	public void verifyCOT(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()

		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.chooseMemberForm()

		//Change the record status to draft
		.navigateToDoNotVerify()
		.navigateToAG()

		.chooseRecordStatusDraft()
		.clickSave()

		//navigate To Business classification
		.NavigateToSystemTab()
		.clickGeneralTab()
		.navigateToBusinessClassification()

		//Clear class of Trade and select the COT
		.clearClassOfTrade()
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))
		//Business Classification = Auto populated
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//Change the record status to published
		.navigateToDoNotVerify()
		.navigateToAG()

		.chooseRecordStatusPublished()
		.clickSave()


		//Data Reset
		//navigate To Business classification
		.NavigateToSystemTab()
		.clickGeneralTab()
		.navigateToBusinessClassification()

		//Clear class of Trade and select the COT
		.clearClassOfTrade()
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade1", sDataSheetName))
		//Business Classification = Auto populated
		.clickSave()


		;
	}
}
