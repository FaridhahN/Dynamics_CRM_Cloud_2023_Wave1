package testcases.ContractAttachment;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_31549:_Test Case 9300 :Cloud-Verify "Approved as Primary" and  "Approved as Secondary" Attachment Status Reason is available for Approved CA's
public class TestCase_31549 {


	@Test
	public void verifyAttachmentStatusReasonForApprovedStatus(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		
		//Choose Existing Contract Attachment
		.selectRelatedContractAttachments()		
		.doubleClickExistingContractAttachment(DataInputProvider.getCellData_ColName(iRowNumber, "contractNumber", sDataSheetName))
		
		//Choose any Attachment Status that has Attachment Status Reason "Successor Contract Update" and save.
		.selectAttachmentStatusOnCA(DataInputProvider.getCellData_ColName(iRowNumber, "caAttachmentStatus", sDataSheetName))
		
	.verifyAttachmentStatusReasonOptionsForApprovedStatus()
		//Data Reset 		
		
		;
		
		}
}
