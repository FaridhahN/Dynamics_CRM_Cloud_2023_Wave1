package testcases.COT;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_964318:_964318:Verify whether value 'Infusion Pharmacy'  is available in 'PAS Vertical' for Class of Trade-Mail Order Pharmacy
 
public class TestCase_964318 {


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

		//Select additional infromation 

		.selectadditionalInformation("Class of Trade")

		//Enter the value

		.enterTheValue(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))
		
		.clickParentField()
		
		.selectResultinLookupSearch(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))
		
		.clickAddButton()
		
		.clickResultButton()

		//select first COT in the result
		.openFirstCOT()

		//Navigate to Advance finding page
		.navigatetoAdvanceFind("Class of Trade Business Classifications")

		.verifyPASVerticalField()
		
		.verifyPASVerticalFiledvalue(DataInputProvider.getCellData_ColName(iRowNumber, "pasVertical", sDataSheetName))
		

		;	}
}
