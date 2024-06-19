package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1094956:_1094956_Verify financials fields available in the  'Opportunity' form.


public class TestCase_1094956 {


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
		.updatefinancials(DataInputProvider.getCellData_ColName(iRowNumber, "listCost", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "listImplementationcost", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "memberlandedcosmo", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "memberlandedimplementedcosmo", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "premiercosmo", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "premierimplementedcost", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "sponsorcost", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "sponsorimplement", sDataSheetName))
		.clickSaveButton()
		.verifyErrorisNotDisplayed()
		;

	}
}
