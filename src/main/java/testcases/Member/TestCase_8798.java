package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_ 7137:Create new member - New Member form and Save it as prospect first

public class TestCase_8798 {


	@Test
	public void createMember(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
  	    .clicSignin()
  	    .clicYesInStaySignedin()
	
	//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
	//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromSearchResults()
		.verifyDPRelationtDateIsNotNull()
		.changeTopParentAsYes()
		.verifyDPRelationtDateIsNull()
		.clickGoBackButton()
		.clickOnDiscardChanges()
		
		
		.selectAccountss()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber1", sDataSheetName))
		
	//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromSearchResults()
		.verifyDPRelationtDateIsNotNull()
		.changeTopParentAsYes()
		.verifyDPRelationtDateIsNull()
		.clickGoBackButton()
		.clickOnDiscardChanges()
		
		;
	}
}
