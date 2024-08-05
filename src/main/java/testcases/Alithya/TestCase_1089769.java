package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1089769:_1089769:Verify the admin fee % field is populated based on the Revenue category field



public class TestCase_1089769 {


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
		.clickOppurtunitiesPage()
		.ClickNewOppurtunity()
		.selectRevenueCategory(DataInputProvider.getCellData_ColName(iRowNumber, "category", sDataSheetName))
		.verifyAdminFee(DataInputProvider.getCellData_ColName(iRowNumber, "adminFee", sDataSheetName))
		.clearRevenueCategory()
		.selectRevenueCategory(DataInputProvider.getCellData_ColName(iRowNumber, "category1", sDataSheetName))
		.verifyAdminFee(DataInputProvider.getCellData_ColName(iRowNumber, "adminFee1", sDataSheetName))
		
		;					
	}
}
