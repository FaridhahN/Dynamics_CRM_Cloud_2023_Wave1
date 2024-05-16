package testcases.Contracts;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_777277:_777277:Verify Contract Manager lookup field is displayed in the Contract Form User Admin

public class TestCase_777277 {


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
		
		.searchcontractManagerInAdvanceLookup(DataInputProvider.getCellData_ColName(iRowNumber, "contactID", sDataSheetName))
		
		.verifyContractManagerisNotnull()
		
		.clearContractManager()
		
		.clickSearchbuttoninContractManager()
		
		.AddNewContractMAnager()
		
		.createnewContact(DataInputProvider.getCellData_ColName(iRowNumber, "firstName", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "lastName", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "primaryAccount", sDataSheetName))
		
		.verifyContractManagerisNotnull()
		
		;
		}
}
