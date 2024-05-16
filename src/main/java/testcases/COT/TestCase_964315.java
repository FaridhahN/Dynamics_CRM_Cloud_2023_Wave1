package testcases.COT;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_964315:_964315:Verify whether value 'Infusion Pharmacy'  is available in 'PAS Vertical' .

public class TestCase_964315 {


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
		
		//select first COT in the result
		.openFirstCOT()
		
		//Navigate to Advance finding page
		.navigatetoAdvanceFind("Class of Trade Business Classifications")
		
		//Update PAS Vertical
		.selectPAS(DataInputProvider.getCellData_ColName(iRowNumber, "pasVertical", sDataSheetName))
		
		//Save the changes and verify no error message
		.clickSaveCOT()
		
		.VerifyErrorMessageNotDisplayed()
		
		//Data reset
		.selectPAS(DataInputProvider.getCellData_ColName(iRowNumber, "pasVertical1", sDataSheetName))
		
		.clickSaveCOT()
		;	}
}
