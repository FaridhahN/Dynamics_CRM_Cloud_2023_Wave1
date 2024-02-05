package testcases.ContractAttachmentSupplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_1014381:_Verify Attachment Status is defaulting to '--Select--' on creating New Contract Attachment Supplier

public class TestCase_1014381 {


	@Test
	public void verifyContractAttachmentSupplierDefaultValue(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member supervisor / member credentials 
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
		
		//Access Contract Attachment Supplier and Verify Default value is  '---'
		.clickSuppliersTabOnCA()
		.clickNewContractAttachmentSupplierButton()		
		.verifyAttachmentStatusDefaultValueOnCASupplierIsSelect();

		
		}
}
