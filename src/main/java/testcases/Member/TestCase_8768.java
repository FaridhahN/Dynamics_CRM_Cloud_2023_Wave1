package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8768:Cloud : Member Attributes Duplicate Check


public class TestCase_8768 {


	@Test
	public void verifyMemberAttributesEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Limited member 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromSearchResults()
		//3.Verify Member Attributes Entity for Limited Member

		.selectRelatedMemberAttributesForLimMem()
		.verifyAccountMemberAttributesAssocView()
		.clickNewAccountnumberMemberAttribute()
		.addNewAccountMemberAttribute(DataInputProvider.getCellData_ColName(iRowNumber, "AttributeType", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "AttributeValue", sDataSheetName))
		.clickGoBackButton()
		.clickNewAccountnumberMemberAttribute()
		.addNewAccountMemberAttribute(DataInputProvider.getCellData_ColName(iRowNumber, "AttributeType1", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "AttributeValue1", sDataSheetName))
		.clickGoBackButton()
		.clickNewAccountnumberMemberAttribute()
		.addNewAccountMemberAttribute(DataInputProvider.getCellData_ColName(iRowNumber, "AttributeType", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "AttributeValue", sDataSheetName))
		.verifyErrorMessage_contains_Singlequote("An exception has occured: You can't create a Member Attribute with same Attribute Type")
		.clickGoBackButton()
		.clickOnDiscardChanges()
		.clickNewAccountnumberMemberAttribute()
		.addNewAccountMemberAttribute(DataInputProvider.getCellData_ColName(iRowNumber, "AttributeType1", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "AttributeValue1", sDataSheetName))
		.verifyErrorMessage_contains_Singlequote("An exception has occured: You can't create a Member Attribute with same Attribute Type")
		.clickGoBackButton()
		.clickOnDiscardChanges()
		//Data Reset
		.deactivateAllAttributes()
		;
	}
}
