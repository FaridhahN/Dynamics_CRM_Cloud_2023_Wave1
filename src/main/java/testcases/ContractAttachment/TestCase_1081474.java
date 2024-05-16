package testcases.ContractAttachment;

//TFS ID_1081450:_1081450_Verify Attachment Status Reason field value mapping for Attachment Status =Sent to Contract Attachment

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_31550:_Test Case 9301 :Cloud-Verify availability of new Supplier Contract Attachment Statuses
import utils.TestUtils;

public class TestCase_1081474 {


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

		.createNewCotnractAttachment(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "contractNumber", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "caAttachmentStatus", sDataSheetName), TestUtils.todaysDate())


	
		.verifyAttachmentStatusReasonSentToVendor()
		
		;
	}
}
