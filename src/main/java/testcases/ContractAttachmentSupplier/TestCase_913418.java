package testcases.ContractAttachmentSupplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_913418:_Verify whether 'Requested Eligibility from Supplier' is available in Attachment Status while updating existing 'Contract attachment supplier'.

public class TestCase_913418 {


	@Test
	public void verifyAttachmentStatusUpdate(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member & Innovatix User credentials
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()
		//Go to Accounts with Contract Attachments
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
		//Choose Existing Contract Attachments with CA Supplier
		.selectRelatedContractAttachments()		
		//.doubleClickExistingContractAttachment(DataInputProvider.getCellData_ColName(iRowNumber, "contractNumber", sDataSheetName))
				.doubleClickExistingContractAttachment()
		
		//Access Contract Attachment Supplier and Update Attachment Status 		
		.clickSuppliersTabOnCA()
		.checkexistingAsupplier()		
		.selectAttachmentStatusOnCASupplier(DataInputProvider.getCellData_ColName(iRowNumber, "caSupplierAttachmentStatus", sDataSheetName))
		.clickSaveOnCASupplier()
		
		//Data Reset 		
		.selectAttachmentStatusOnCASupplier(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
		.clickSaveOnCASupplier();
		
		
		}
}
