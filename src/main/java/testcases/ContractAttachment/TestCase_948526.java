package testcases.ContractAttachment;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_948526:_948526_Verify 'Assigned To' field on Contract Attachment should populate with 'Owner' field's value(of related Contract) when creating CA with Attachment Status as 'Sent to Contract Attachment'

public class TestCase_948526 {


	@Test
	public void verifyAddContractAttachment(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		//Select Existing Contract Attachment
		.selectAllExistingContractAttachment()

		//Deactivate
		.clickDeactivateonContractAttachment()

		//Click New Button in Contract Attachment
		.clickNewContractAttachmentButton()

		//Select Contract ID
		.selectContractID(DataInputProvider.getCellData_ColName(iRowNumber, "contractNumber", sDataSheetName))


		//Select the Attachment Status
		.selectAttachmentStatus("Sent to Contract Attachment")

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

		//Navigate To the System Tab
		.clickSystemTabOnCA()

		//Verify the Assigned Field
		.verifyAssginedToFeild(DataInputProvider.getCellData_ColName(iRowNumber, "cassignedField", sDataSheetName))


		//Data Reset -Deactivate Contract Attachment
		.clickGoBackandDiscardChanges()
		.selectAllExistingContractAttachment()
		.clickDeactivateonContractAttachment()

		;
	}
}
