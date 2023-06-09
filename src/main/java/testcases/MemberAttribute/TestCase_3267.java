package testcases.MemberAttribute;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 3267:Verify a member can not have more than one General GPO Rebate rule

public class TestCase_3267 {

	@Test
	public void verifyGPORebate(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromSearchResults()

		//Navigate to the Member Attribute Page
		.selectRelatedMemberAttributesForLimMem()
		.clickNewAccountnumberMemberAttribute()
		.addNewAccountMemberAttribute(DataInputProvider.getCellData_ColName(iRowNumber, "AttributeType", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "AttributeValue", sDataSheetName))
		.verifyErrorMessage_contains_Singlequote("An exception has occured: You can't create a Member Attribute with same Attribute Type")
		.clickGoBackButton()
		.clickOnDiscardChanges()
		;

	}	
}