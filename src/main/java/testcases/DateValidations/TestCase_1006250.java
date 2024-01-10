package testcases.DateValidations;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1006250:_1006250:Verify whether user should not be able to update Start Date > Termination Date. 

public class TestCase_1006250 {


	@Test
	public void Datevalidation(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {



		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()
		//Search the account
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
		.selectRelatedMembership()
		.doubleClickMembership(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		.getMembershipStartDate()
		
		.getPastDate(TestUtils.date, 2)

		.typeMembershipEndDate(TestUtils.enddate)
		.selectMembershipEndReason(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndReason", sDataSheetName))
		.clickMembershipSaveAndClose()
		
		.verifyDateValidationError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		//Date reset

		
		;
	}
}
