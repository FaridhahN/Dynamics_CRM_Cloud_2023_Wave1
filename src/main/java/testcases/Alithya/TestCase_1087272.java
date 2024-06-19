package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087272:_1087272:Verify the Fee Share % field accepts only numbers




public class TestCase_1087272 {


	@Test
	public void Oppurtunities(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.staysignedinforOtherApp()

		.selectSalesHubAccount()
		
		.clickAccounts()
		.clickNewAccounts()
		.changeView("Sales Form")
		.enterAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
		.navigatetoFeeshare()
		.updateFeeShare("text")
		
		.verifysubLineErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errormessage", sDataSheetName))
		.clickSave()
		.verifyErrormessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage1", sDataSheetName))
		.updateFeeShare("text23")
		.verifysubLineErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errormessage", sDataSheetName))
		.clickSave()
		.verifyErrormessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage1", sDataSheetName))
		.updateFeeShare("23")
		.verifysubLineErrorMessageNotDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "errormessage", sDataSheetName))
		.clickSave()
		.verifyErrormessageisNotDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage1", sDataSheetName))
		
		.updateFeeShare("23.23")
		.verifysubLineErrorMessageNotDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "errormessage", sDataSheetName))
		.clickSave()
		.verifyErrormessageisNotDisplayed(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage1", sDataSheetName))
		
		;					
	}
}
