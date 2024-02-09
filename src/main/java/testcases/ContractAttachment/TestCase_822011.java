package testcases.ContractAttachment;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_822011:_Verify whether "Initiated By"  and "Termination Reason" Field are available with Read only mode.

public class TestCase_822011 {


	@Test
	public void verifyInitiatedByTerminationReasonFieldsAreDisabled(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using Member SupervisorVerify whether "Initiated By"  and "Termination Reason" Field are available with Read only mode.
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
		
		//Verify Initiated By and Termination Reason fields are is disabled and verify the header text -Don't forget to change the Status Date when changing the Attachment Status!
		.verifyWarningNotification()
		.scrollToPriceActivationDetails()
		.verifyInitiatiedByTerminationReasonAreDisabled();
				
		//Data Reset -Not Required.
		
		}
}
