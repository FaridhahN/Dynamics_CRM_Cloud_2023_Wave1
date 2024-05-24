package testcases.ContractAttachmentSupplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_913451:_913451:Verify whether 'Pending Request' and 'Requested Eligibility from Supplier' are available in Attachment Status of 'Contract attachment supplier' in Advance Filter.

public class TestCase_913451 {


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

		//Select Contract Attachment Supplier in look for
		.selectLookfor("Contract Attachment Suppliers")

		//Select additional infromation 

		.selectadditionalInformation("Attachment Status")

		//Enter the value

		.enterTheValue(DataInputProvider.getCellData_ColName(iRowNumber, "AttachmentStatus", sDataSheetName))
		
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
