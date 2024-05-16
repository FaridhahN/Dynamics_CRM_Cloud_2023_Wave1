package testcases.BusinessClassification;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_964311:_964311:Verify whether new COT's- 'Prison/Jail' and 'Correctional Healthcare ' is available in 'Class of Trade Business Classification' Entity.

public class TestCase_964311 {


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

		
		.selectadditionalInformation("Class of Trade")
		
		.enterTheValue("Prison/Jail")
		
		.selectResultinLookupSearch(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))
		
		.enterTheValue("Correctional Healthcare")
		
		.selectResultinLookupSearch(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))
		
		.clickAddButton()
		
		.clickResultButton()

		.verifyResult("Prison/Jail")
		
		.verifyResult("Correctional Healthcare")

		;	}
}
