package testcases.ContractAttachment;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_799505:_799505:Verify Vendor status and vendor status date have been removed from contract attachment page

public class TestCase_799505 {


	@Test
	public void verifyAttachmentStatusOptions(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member & Innovatix User credentials
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()
		
		//Open Existing CA
		.selectContractAttachmentsTab()
		.selectAllContractAttachentsView()	
		
		//Click New CA
		.clickNewOnCA()
		
		//Verify Vendor status and Vendor status date is not displayed
		.openAllContractAttachmentView()
		
		;
		//Data Reset -Not Required.
		
		}
}
