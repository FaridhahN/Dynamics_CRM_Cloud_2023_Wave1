package testcases.ContractAttachment;

//TFS ID_1081369:_1081369_Verify Attachment Status Reason field is locked for Attachment Statuses [ Generated/Sent paperwork , Sent to Distributor, Offered to Member -On Hold , Pending Paperwork]

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_31550:_Test Case 9301 :Cloud-Verify availability of new Supplier Contract Attachment Statuses
import utils.TestUtils;

public class TestCase_1081369 {


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
		//2.Go to Contract Attachment
		.selectContractAttachmentsTab()

		.addContractAttachmentButton()

		.createNewCotnractAttachment(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "contractNumber", sDataSheetName), "Generated/Sent Paperwork", TestUtils.todaysDate())


		.clickSaveButtonContractAttachment()

		.verifyattachmentStatusReasonisLocked()

		.selectAttachmentStatusOnCA("Sent to Distributor")


		.clickSaveButtonContractAttachment()

		.verifyattachmentStatusReasonisLocked()

		.selectAttachmentStatusOnCA("Offered to Member - On Hold")


		.clickSaveButtonContractAttachment()

		.verifyattachmentStatusReasonisLocked()


		.selectAttachmentStatusOnCA("Pending Paperwork")


		.clickSaveButtonContractAttachment()

		.verifyattachmentStatusReasonisLocked()

.clickDeactivateonContractAttachment()

		;
	}
}
