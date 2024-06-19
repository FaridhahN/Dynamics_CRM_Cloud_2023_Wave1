package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_947039:_947039_Verify whether Estimated Budget is removed from Opportunity form.


public class TestCase_947039 {


	@Test
	public void smartPO(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.staysignedinforOtherApp()

		.selectPartnerSale()
		//Navigate to Partner sales
		.navigatetoPartnerSales()
		
		//Navigate to Oppurtunities
		.clickOppurtunities()
		
		//Click New Oppurtunities Button
		.clickNewOppurtunitiesbutton()

		//navigate to Member account
		.navigateToMemberAccount()
		
		//Verify Estimated buget is not displayed
		. verifyEstimatedBudgetIsnotDisplayed()
		
		;



					
	}
}
