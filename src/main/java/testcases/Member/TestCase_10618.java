package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10618-Cloud : Verify whether Base Read only role cannot create "Account Number" via Account Number Entity.

public class TestCase_10618 {

	  	
	@Test
	public void verifyAccountNumberEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
		//1. Login to CRM as Base-Read Only User
		new LoginPage()
			.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
			.clickNext()
	  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
	  	    .clicSignin()
	  	    .clicYesInStaySignedin()
		
		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromSearchResults()

		//3.Verify Account Number Entity for Base Read Only User
		
		.selectAccountNumbers()
		.verifyAddNewAccountNumberIsNotPresent()
		.doubleClickExistingAccountNumberDEA()
		.verifyAccountNameInAccountNumbersIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage1", sDataSheetName))
		.verifyAccountNumberTypeInAccountNumbersIsNotEditable()
		.verifyNameInAccountNumbersIsNotEditable()
		.verifyCalculatedNameInAccountNumbersIsNotEditable()
		.verifyStartDateInAccountNumbersIsNotEditable()
		.verifyEndDateInAccountNumbersIsNotEditable();
		
		
					
		//Data Reset -Not Required
		
		
			
	}
}
