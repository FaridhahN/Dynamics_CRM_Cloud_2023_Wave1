package testcases.Views;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_9171: _828633_cloud: Verify all the Membership Terms view

public class TestCase_9172 {


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
		.selectLookfor("Membership")

		//Select View
		.setSavedView("All Innovatix/Essensa Members - Current")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("All Memberships")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Current Associated Memberships")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Current Memberships")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Deactivated Memberships")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Ended Memberships")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Essensa Members - Current")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Essensa Members - Former")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()


		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Innovatix Members - Current")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()


		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Innovatix Members - Former")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		
		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("PPN Members - Current")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("PPN Members - Former")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()
		
		
		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Premier Memberships")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		
		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Current Healthcare Essensa Members")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Current Non-Healthcare Essensa Members")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		
		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Essensa Membership created in last 2 weeks")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		
		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("Innovatix Membership created in last 2 weeks")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		.clcickAdvanceFindTab()			

		//Select View
		.setSavedView("InnovatixCares Members - Current")

		//click result button
		.clickResultIcon()

		.VerifyErrorMessageNotDisplayed()

		;
	}
}
