package testcases.ContractAttachmentSupplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID 31543:_Test Case 9294 :Cloud-Verify Contract attachment supplier status list

public class TestCase_31543 {


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
		.openFirstContractAttachment()
		
		//Access Contract Attachment Supplier and Verify Attachment Status field Options		
		.clickSuppliersTabOnCA()
		.clickNewContractAttachmentSupplierButton()		
		
		.verifyAttachmentStatusFieldOptionsOnCASupplier();
		
		//Data Reset -Not Required.
		
		}
}
