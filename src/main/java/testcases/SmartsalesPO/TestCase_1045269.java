package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1045269:_1045269_Verify whether in the view whether ‘Active Stage’ field is updated with 'Pre Qualified' BPF stage .

public class TestCase_1045269 {


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
		
		//navigate to Member account
		.navigateToMemberAccount()
		
		//Select Member account
		.selectMemberAccount(DataInputProvider.getCellData_ColName(iRowNumber, "memberAccount", sDataSheetName))

	
		.clickSaveButton()
		
		
		.clickBackButton()
		
		.clickTab(5)
		.clickshiftTab(1)
		
		.searchOppurtunity("Test"+TestUtils.todaysDate(), "Pre-Qualified")
		
		;



	}
}
