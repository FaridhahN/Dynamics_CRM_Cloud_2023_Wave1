package testcases.ContractAttachment;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_31548_Test Case 9299 :Cloud-Verify "Successor Contract Update" attachment reason can not be saved for inappropriate attachment status
public class TestCase_31548 {


	@Test
	public void verifySuccessorCOntractUpdateReason(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member & Innovatix User credentials
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()
		
		///Go to Accounts with Contract Attachments
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
		//Choose Existing Contract Attachments with CA Supplier
		.selectRelatedContractAttachments()		
		.doubleClickExistingContractAttachment(DataInputProvider.getCellData_ColName(iRowNumber, "contractNumber", sDataSheetName))
		
		//Choose any Attachment Status that has Attachment Status Reason "Successor Contract Update" and save.
		.selectAttachmentStatusOnCA(DataInputProvider.getCellData_ColName(iRowNumber, "caAttachmentStatus", sDataSheetName))
		.selectAttachmentStatusReasonOnCA(DataInputProvider.getCellData_ColName(iRowNumber, "caAttachmentStatusReason", sDataSheetName))
		.clickSaveOnCA()
		
		//Choose any Attachment Status that does NOT have Attachment Status Reason "Successor Contract Update" and verify the same
		.selectAttachmentStatusOnCA(DataInputProvider.getCellData_ColName(iRowNumber, "caAttachmentStatus2", sDataSheetName))
		.verifyAttachmentStatusReasonSuccesorContractUpdateIsNotPresent()
		
		//Data Reset 		
		.selectAttachmentStatusOnCA(DataInputProvider.getCellData_ColName(iRowNumber, "caAttachmentStatus1", sDataSheetName))
		.selectAttachmentStatusReasonOnCA(DataInputProvider.getCellData_ColName(iRowNumber, "caAttachmentStatusReason1", sDataSheetName))
		;
		
		}
}
