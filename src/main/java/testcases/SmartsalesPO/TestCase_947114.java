package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1045258:_1045258_Verify whether 'Pre Qualified' BPF stage is available in Opportunity.

public class TestCase_947114 {


	@Test
	public void smartPO(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.staysignedinforOtherApp()

		//Verify Partner Sales option is not displayed
		.verifyPartnerSaleOptionIsNotDisplayed()
		;



					
	}
}
