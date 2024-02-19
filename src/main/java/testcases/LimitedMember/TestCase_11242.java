package testcases.LimitedMember;

import org.testng.annotations.Test;

import pages.ContactsPage;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//******COMPLETED******

//TFS ID_11242:_828065_Cloud : Limited Member's ability to end date Contact Account Associations

public class TestCase_11242 {

	@Test
	public void validateContactCAA(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM as member supervisor
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin().clicYesInStaySignedin()

		//2. Go to Work place >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		.clickCAAFromRelated()

		.doubleClickcotactName(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContactName", sDataSheetName))
		
		.typeContactRelationshipEndDate()
		.clickSaveAfterTerminationCAA()
		.VerifyErrorMessageNotDisplayed()
		
		//Data Reset
		.clickGoBackToMemberForm()
		.clickGeneralTab()
		.navigateToPrmiaryAccount()
		.addMemberPrimaryContact(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))
		.clickSave()
		.clickCAAFromRelated()
		.refreshCAA()
		.verifyNewCAAContactname(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContactName", sDataSheetName))
		
		;
		
		

	}
}
