package testcases.BaseReadOnly;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10621:Cloud : Verify whether Base Read only role cannot create "Account Representative" via Account Representative Entity


public class TestCase_10621 {


	@Test
	public void verifyBaseReadOnlyAccountRepEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Base-Read Only User
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.selectActiveMembers()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		
		.selectAccountFromSearchResults()

		//3.Select Account Representative Entity and verify +New Account Representative button
		.verifyAddNewAccountRepresentativeIsNotPresent()	  	   

		// 4.Verify Account Representative Associated View is displayed

		.verifyAccountRepAssocView();


		//Data Reset -Not Required


	}
}
