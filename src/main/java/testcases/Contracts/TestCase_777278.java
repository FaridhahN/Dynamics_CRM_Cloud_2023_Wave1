package testcases.Contracts;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS_777278:_777278:Verify the Tier Enhancements section is added user Admin


public class TestCase_777278 {


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
		
		//Verify General tab in the new tier enhancement
		.verifyGeneralTabinNewTierEnhancement(DataInputProvider.getCellData_ColName(iRowNumber, "sponsor", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "entity", sDataSheetName))
		
		//Verify Contrct field is not null
		.verifyContractFieldisNotNull()
		
		//Click save and close 
		.clickSaveAndCloseInContractTierEnhancement()
		
		//Verify Tier Enhancement created
		.verifyTierEnhancementCreated(DataInputProvider.getCellData_ColName(iRowNumber, "sponsor", sDataSheetName))
		
		
		
		;
	}
}
