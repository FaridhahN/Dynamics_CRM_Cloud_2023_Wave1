package testcases.ContractAttachment;



//TFS ID_813042:_813042:Verify "Successor Contract Update" attachment reason can not be saved for inappropriate attachment status

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_31550:_Test Case 9301 :Cloud-Verify availability of new Supplier Contract Attachment Statuses
import utils.TestUtils;

public class TestCase_813042 {


	@Test
	public void verifyAttachmentStatusOptions(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member & Innovatix User credentials
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//Open the existing account
		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))


		//Navigate to the Contract Attachment Option
		.navigateToContractAttachmentOption()

		//Verify New View in Contract Attachement
		.verifyDefaultViewinContractAttachment()

		//Click New Button in Contract Attachment
		.clickNewContractAttachmentButton()

		//Select Contract ID
		.selectContractID(DataInputProvider.getCellData_ColName(iRowNumber, "contractNumber", sDataSheetName))


		//Select Attachment Status
		.selectAttachmentStatus(DataInputProvider.getCellData_ColName(iRowNumber, "caAttachmentStatus", sDataSheetName))

		//Select Attachment status date

		.enterContractAttahmentstatusDate(TestUtils.todaysDate())

		//Enter price activation ID

		.enterPriceActivationID(DataInputProvider.getCellData_ColName(iRowNumber, "cPriceActivationID", sDataSheetName))

		//Enter requrest ID
		.enterrequesterid(DataInputProvider.getCellData_ColName(iRowNumber, "cRequesterID", sDataSheetName))

		//Enter Request Commnet
		.enterRequesterComments(DataInputProvider.getCellData_ColName(iRowNumber, "cRequestComment", sDataSheetName))

		//Enter attachment Comments
		.enterAttachmentTeamComment(DataInputProvider.getCellData_ColName(iRowNumber, "cAttachmentTeamComment", sDataSheetName))

		//Click save button
		.clickSaveButtonContractAttachment()

		//Click Supplier on CA
		.clickSuppliersTabOnCA()


		//Click General Tab
		.clickGeneralTabInContractAttachment()

		//click Tire Requested
		
		//Change the Attachment Status
		.selectAttachmentStatus("Approved")

		//Verify Attachment Reason
		.verifyAttachmentReasons()


		//Select Attachment Reasons
		.selectAttachmentStatusReasonOnCA("Successor Contract Update")
		
		//click save button
		.clickSaveButtonContractAttachment()

		//Change the Attachment Status
		.selectAttachmentStatus("Canceled")


		//Verify Attachment Reason is not displayed
		.verifyAttachmentReasonsisDeleted()
		
		
		//Data Reset 
		.clickGoBackandDiscardChanges()

		//Select All Existing contract Attachment and click deactivate 
		.selectAllExistingContractAttachment()
		.clickDeactivateonContractAttachment()


		;
	}
}
