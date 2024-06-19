package testcases.Contracts;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_1083051:_1083051:Verify whether new Column "CRM Account # (Sponsor Name)" is available in Tier Enhancement View for the existing contract.



public class TestCase_1083051 {


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
		
		//Oepn First Contract
		.openFirstContract()
		
		//Verify Tier Enhancement button is displayed
		.verifyTierEnchancement()
		
		//Click Tier Enhancement button is displayed
		.clickTierEnhancement()
		
			//Verify Tier Enhancement Tab
		.verifyTierEnhanceMentTab()
		;
	}
}
