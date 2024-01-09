package testcases.DateValidations;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1005696:_1005696:Verify whether user should not be able to update Start Date of a member's Parent > Top Parent Relation Date.



public class TestCase_1005696 {


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

	//Switch DP and get the premier start date of DP
	.navigateToDP()
	.selectdP()
	.getThePremierStartDate()
	.clickGoBack()

	//Navigate to Childs DPRD and change is lesser that DP premier start date
	.navigateToApplicationDate()

	//get the past date
	.getPastDate(TestUtils.date, 2)

	//Enter the Application start date greater than Premier start date
	.selectTopParentRelationDate(TestUtils.date)
	.typeTPReason("Test")
	.clickSave()
	.verifyDateValidationError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))




	;
	}
}
