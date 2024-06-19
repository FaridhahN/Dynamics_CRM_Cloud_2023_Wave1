package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1094737:_1094737_Verify fields available in the  Smart PO Section 'Opportunity' form.

public class TestCase_1094737 {


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
		.updatesmartpoSection(DataInputProvider.getCellData_ColName(iRowNumber, "intreset", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "goalsleft", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "solution", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "purchaseProcess", sDataSheetName), TestUtils.todaysDate(),DataInputProvider.getCellData_ColName(iRowNumber, "task", sDataSheetName),TestUtils.todaysDate(), DataInputProvider.getCellData_ColName(iRowNumber, "department", sDataSheetName), TestUtils.todaysDate(),  DataInputProvider.getCellData_ColName(iRowNumber, "apssystem", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "apsversion", sDataSheetName))

		.clickSaveButton()
		.verifyErrorisNotDisplayed()
		;

	}
}
