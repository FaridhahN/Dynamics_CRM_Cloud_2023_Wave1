package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1089770:_1089770:TFS ID Verify Projected GAF and NAF fields are calculated correctly based on Contract volume, admin fee, fee share


public class TestCase_1089770 {


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
		.changeViewinOppurtunityPage("CP - Open Opportunities")
		.verifyGutFeelOptioninView()
		.ClickNewOppurtunity()
		.enterTopic(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName))
		.clickSaveinOpportunities()
		.typeContractVolume(DataInputProvider.getCellData_ColName(iRowNumber, "contractVolume", sDataSheetName))
		.typeAdminFee(DataInputProvider.getCellData_ColName(iRowNumber, "adminFee", sDataSheetName))
		.typefeeShare(DataInputProvider.getCellData_ColName(iRowNumber, "feeshare", sDataSheetName))
		.clickSaveinOpportunities()
		
		.verifyGAF(DataInputProvider.getCellData_ColName(iRowNumber, "contractVolume",sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "adminFee", sDataSheetName))
		.verifyNAF(DataInputProvider.getCellData_ColName(iRowNumber, "contractVolume",sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "adminFee", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "feeshare", sDataSheetName))
		;					
	}
}
