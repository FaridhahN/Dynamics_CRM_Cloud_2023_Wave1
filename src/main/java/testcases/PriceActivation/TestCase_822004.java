package testcases.PriceActivation;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_822004:_822004:Verify whether "Price Activation responder" view is available in "Contract Contacts".

public class TestCase_822004 {


	@Test
	public void verifyAddContractAttachment(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member & Innovatix User credentials
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()
		

		//Open Existing CA
		.selectContractsTab()
		
		.openFirstContract()
		
		.navigateToContractContact()
		
		.changeTheContractView("Price Activation Responders")
		.clickNewContractButton()
		.addNewContract(DataInputProvider.getCellData_ColName(iRowNumber, "ContactName", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "ContactType", sDataSheetName))
		.clickSaveAndClose()
		.verifyErrorisNotDisplayed()
				
				
	;}
}
