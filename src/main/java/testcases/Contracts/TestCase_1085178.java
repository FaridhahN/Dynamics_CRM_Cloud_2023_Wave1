package testcases.Contracts;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_1085178:_1085178:Verify the Tier Enhancements section associated records is added user Admin

public class TestCase_1085178 {


	@Test
	public void verifyContractAttachmentSupplierDefaultValue(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member & Innovatix User credentials
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()
		
		//Open Existing CA
		.selectContractsTab()
		
		.addnewContractButton()
		
		.clickaddContract(DataInputProvider.getCellData_ColName(iRowNumber, "ContractNumber", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Vendor", sDataSheetName))
		.clickSave()
		
		//Verify Tier Enhancement button is displayed
		.verifyTierEnchancement()
		
		//Click Tier Enhancement button is displayed
		.clickTierEnhancement()
		
		//Verify Add New Tier enhancement button
		.verifyNewTierEnhancementButton()
		
		//Add Add New Tier enhancement button
		.clickNewTierEnhancementButton()
		
		//Verify New Tier Enhancement Page
		.veriyfNewTierEnhancementPage()
		
		.verifyTierEnhancementFieldLength(DataInputProvider.getCellData_ColName(iRowNumber, "sponsor", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "tierEnhancement", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "COTEligibility", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "entity", sDataSheetName))
		//Click save and close 
		.clickSaveAndCloseInContractTierEnhancement()
		
		//Verify Tier Enhancement created
		.verifyTierEnhancementCreated(DataInputProvider.getCellData_ColName(iRowNumber, "sponsor", sDataSheetName))
		
		//Click Additional Records
		.clickAdditionalRecords()
		
		//Verify Additional records fields in Contract Tier Enhancement
		.verifyContractTierEnhancementFields()
		
		
		;
	}
}
