package testcases.PriceActivation;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_777616:_777616:Verify in the Contact Type dropdown of Contract Contact, set value "Price Activation Responder" is getting displayed

public class TestCase_777616 {


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
		.addnewContractButton()
		.clickaddContract(DataInputProvider.getCellData_ColName(iRowNumber, "ContractNumber", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Vendor", sDataSheetName))
		.clickSave()
		.navigateToContractContact()
		
		.changeTheContractView("Price Activation Responders")
		.clickNewContractButton()
		.addNewContract(DataInputProvider.getCellData_ColName(iRowNumber, "ContactName", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "ContactType", sDataSheetName))
		.clickSaveAndClose()
		.verifyErrorisNotDisplayed()
				
				
	;}
}
