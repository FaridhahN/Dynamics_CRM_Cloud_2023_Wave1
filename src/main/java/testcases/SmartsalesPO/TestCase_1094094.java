package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1094094:_1094094_Verify fields available under Summary Tab in the  'Opportunity' form.

public class TestCase_1094094 {


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

						
		.enterSummraysection(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "referringPartner", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "partnerType", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "memberAccount", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "memberContact", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "purchaseTime", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "estLicense", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "actualLicense", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "purchaseProcess", sDataSheetName))
		
		.enterMoreDetails("test", "test", "test", "test")

		.clickSaveButton()
		.verifyErrorisNotDisplayed()
		;

	}
}
