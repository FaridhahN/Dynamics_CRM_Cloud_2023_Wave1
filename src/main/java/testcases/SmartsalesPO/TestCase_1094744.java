package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1094744:_1094744_Verify fields available in the  qualify stage 'Opportunity' form.

public class TestCase_1094744 {


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

		.enterTopic("TestPrequalified"+TestUtils.todaysDate())	
		
		//navigate to Member account
		.navigateToMemberAccount()
		
		//Select Member account
		.selectMemberAccount(DataInputProvider.getCellData_ColName(iRowNumber, "memberAccount", sDataSheetName))

		.clickSaveButton()
		.updatePrequalified()
		.updatequalify(DataInputProvider.getCellData_ColName(iRowNumber, "memberContact", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "memberAccount", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "purchaseTime", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "purchaseProcess", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "summary", sDataSheetName))
		.verifyErrorisNotDisplayed()
		;



	}
}
