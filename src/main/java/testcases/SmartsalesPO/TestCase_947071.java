package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_947071:_947071_Verify in the view whether ‘Active Stage’ field is updated with BPF stage value.

public class TestCase_947071 {


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

		.addStageinTheColumn()
		
		
		//Click New Oppurtunities Button
		.clickNewOppurtunitiesbutton()

		.enterTopic("Test"+TestUtils.todaysDate())	
		
		.updatePrequalified()
		.clickSaveButton()
		
		
		.clickBackButton()
		
		.clickTab(5)
		.clickshiftTab(1)
		
		.searchOppurtunity("Test"+TestUtils.todaysDate(), "Qualify")
		
		;



	}
}
