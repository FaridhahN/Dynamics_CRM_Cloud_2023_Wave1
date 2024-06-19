package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_947037_:Verify fields available in the  'Opportunity' form.



public class TestCase_947037 {


	@Test
	public void smartPO(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		
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
		
		.clickSaveButton()
		.updatePrequalified()
		.updatequalify(DataInputProvider.getCellData_ColName(iRowNumber, "memberContact", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "memberAccount", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "purchaseTime", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "purchaseProcess", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "summary", sDataSheetName))
		.verifyUpdateDiscoverStage(DataInputProvider.getCellData_ColName(iRowNumber, "customerneed", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "processsolution", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "identifyCustomer", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "identityCompetitors", sDataSheetName))
		.verifyUpdateDemonstrateStage(DataInputProvider.getCellData_ColName(iRowNumber, "estlicense", sDataSheetName))
		.verifyUpdateProposeStage(TestUtils.todaysDate(),DataInputProvider.getCellData_ColName(iRowNumber, "identifysalesteam", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "developerProposal", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "internalreview", sDataSheetName), TestUtils.todaysDate())
		.verifyUpdateCloseStage(TestUtils.todaysDate(), DataInputProvider.getCellData_ColName(iRowNumber, "finalproposal", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "presentfinalproposal", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "sendthankyou", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "filedebrief", sDataSheetName))
		.verifyErrorisNotDisplayed()

		
		;



		//				
		//				//Data Reset -Deactivate the created Primary Contact
		//				.selectCAA()
		//				.clickOnCAAandDeactivate()
		//				.clickConfirmCAADeactivation();
		//				
	}
}
