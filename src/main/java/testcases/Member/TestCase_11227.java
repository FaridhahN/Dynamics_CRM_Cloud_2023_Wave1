package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_11227:_828840_ Cloud: Verify "This account is in Draft status." info text is displayed when Account is Terminated and Record status is in draft

public class TestCase_11227 {


	@Test
	public void VerifyDraftStatusMessage(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//Open Terminated Member View
		.selectAccountsTab()

		.selectTerminatedView()
		.openFirstTermninatedAcoount()

		//Navigate to the Record status feild
		.navigateToAGDate()

		//Record Status = Draft
		.chooseRecordStatusDraftfromTop()	
		.clickSave()
		
		//Verify Account is int Draft message
		.VerifyDraftMessage(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		
		//Change to published status
		.chooseRecordStatusPublished()
		.clickSave()
		;
	}
}
