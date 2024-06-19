package testcases.Views;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_9171: _828633_cloud: Verify all the Membership Terms view

public class TestCase_9171 {


	@Test
	public void VerifyBusinessClassification(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAdvanceSetting()

		//Navigate to Business Management page
		.navigatetoAdvanceSettings("Business Management")

		//Click Advance Find button
		.clickAdvancefindButton()

		//Navigate to Advance find page
		.navigatetoAdvanceFind("Advance Find")


		//Click new Search
		.clickNewButton()

		//Select Class of Trade Business Classifications in look for
		.selectLookfor("Membership Terms")

		//Select View
		.setSavedView("Active Membership Terms")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Inactive Membership Terms")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		;
	}
}
