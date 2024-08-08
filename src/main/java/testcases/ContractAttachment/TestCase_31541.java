package testcases.ContractAttachment;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_31541:_Test Case 9292 :Cloud-Verify contract attachment status and reason

public class TestCase_31541 {


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
		
		//Selectall Contract attachment
		.selectAllExistingContractAttachment()
		//Deactivate contract Attachment
		.clickDeactivateonContractAttachment()
		
		
		.clickNewOnCA()
		.verifyAttachmentStatusFieldOptionsOnCA();
		
		//Data Reset -Not Required.
		
		}
}
