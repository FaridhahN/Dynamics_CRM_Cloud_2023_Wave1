package testcases.ContractAttachment;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_822010:_Verify whether "Last Response Date" Field is available with Read only mode.

public class TestCase_822010 {


	@Test
	public void verifyLastResponseDateIsDisabled(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using Member Supervisor
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
		
		//Verify LastResponse Date is disabled and verify the header text -Don't forget to change the Status Date when changing the Attachment Status!
		.verifyWarningNotification()
		.verifyLastResponseDateIsDisabled();
				
		//Data Reset -Not Required.
		
		}
}
