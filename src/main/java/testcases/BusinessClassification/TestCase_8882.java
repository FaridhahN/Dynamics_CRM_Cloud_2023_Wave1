package testcases.BusinessClassification;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_1014617:_1014617:Verify when selecting any "Class of trade" , "Not Found" Error window should not get displayed for member role 

public class TestCase_8882 {


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
		.selectLookfor("Class of Trade Business Classifications")
		
		//click result button
		.clickResultIcon()
		
		//select first row in the result
		.clickfirstResult()
		
		.navigatetoAdvanceFind("Class of Trade Business Classifications")
		
		.verifyFieldsareEditable()
		;	}
}
