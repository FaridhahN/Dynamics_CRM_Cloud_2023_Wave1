package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1095095_1095095_Verify fields available in the  Discover stage of 'Opportunity' form.

public class TestCase_1095355 {


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
		.verifyUpdateDiscoverStage(DataInputProvider.getCellData_ColName(iRowNumber, "customerneed", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "processsolution", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "identifyCustomer", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "identityCompetitors", sDataSheetName))
		.verifyUpdateDemonstrateStage(DataInputProvider.getCellData_ColName(iRowNumber, "customerneed", sDataSheetName))
		.verifyUpdateProposeStage(TestUtils.todaysDate(),DataInputProvider.getCellData_ColName(iRowNumber, "identifysalesteam", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "developerProposal", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "internalreview", sDataSheetName), TestUtils.todaysDate())
		.verifyErrorisNotDisplayed()
		;



	}
}
