package testcases.DateValidations;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1005601:_1005601:Verify whether user able to update Start Date of a member's Parent, top <= Direct Parent Relation Date. 

public class TestCase_1005693 {


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

				//Navigate to Childs TPRD and change is lesser that DP premier start date
				.navigateToApplicationDate()

				//get the past date
				.getFutureDate(TestUtils.date, 2)

				.selectTopParentRelationDate(TestUtils.enddate)
				.typeTPReason("Test")
				.clickSave()
				.verifyErrorisNotDisplayed()


				//Update Childs TPRD and change is equal to DP premier start date
				
				//Enter the Application start date lesser than Premier start date
				.selectTopParentRelationDate(TestUtils.date)
				.typeTPReason("Test")
				.clickSave()
				.verifyErrorisNotDisplayed()


		;
	}
}
