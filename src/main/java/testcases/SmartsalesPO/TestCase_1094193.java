package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1094193:_1094193_Verify fields available in the  'Opportunity' form.

public class TestCase_1094193 {


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

		.enterTopic(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName))
		.clickTab(15)
		.enterMoreDetails("test", "test", "test", "test")

		.clickSaveButton()
		.verifyErrorisNotDisplayed()
		;

	}
}
