package testcases.DateValidations;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1005591:_1005591:Verify whether user able to update Application Start Date < or <= or > Premier Start date. No date restriction for Application start date.

public class TestCase_1005591 {


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

		//Get the premier start date
		.getThePremierStartDate()

		.navigateToApplicationDate()

		//get the past date
		.getPastDate(TestUtils.date, 2)

		//Enter the Application start date lesser than Premier start date
		.chooseApplicationDate(TestUtils.enddate)
		.clickSave()
		.verifyErrorisNotDisplayed()

		//Enter the Application start date equal to Premier start date
		.chooseApplicationDate(TestUtils.date)
		.clickSave()
		.verifyErrorisNotDisplayed()

		//get the past date
		.getFutureDate(TestUtils.date, 2)

		//Enter the Application start date lesser than Premier start date
		.chooseApplicationDate(TestUtils.enddate)
		.clickSave()
		.verifyErrorisNotDisplayed()

		;
	}
}
