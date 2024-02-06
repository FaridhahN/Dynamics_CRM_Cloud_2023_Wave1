package testcases.ContractAttachmentSupplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_913403:_Verify whether 'Pending Request' is available in Attachment Status while creating new 'Contract attachment supplier'.

public class TestCase_913403 {


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
		
		.selectAttachmentStatusOnCASupplier(DataInputProvider.getCellData_ColName(iRowNumber, "caSupplierAttachmentStatus", sDataSheetName))
		
		.clickSaveOnCASupplier()
		
		//Data Reset 
		
		.clickDeactivateOnCASupplier();
		
		
		}
}
