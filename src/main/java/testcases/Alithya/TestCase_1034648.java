package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1034648_1034648:Verify 'Internal Notes' is available on Quick Create Appointment Form


public class TestCase_1034648 {


	@Test
	public void Oppurtunities(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.staysignedinforOtherApp()

		//Navigate to the Sales hub
		.selectSalesHubAccount()

		//Navigate to the opportunity  page
		.clickAccounts()
		
		.changeViewinAccountsPage("My Accounts - Sales")

		//Click new opportunity button
		.openFirstAccount()

		//navigate to Acitvities
		.clickActivitiesTab()

		//click new Email activity
		.clickNewAppointmentActivity()
		
		//Type Subject
		.typeSubject(DataInputProvider.getCellData_ColName(iRowNumber, "subject", sDataSheetName))
		
		//Click Tab
		.clickTab(10)
		
		//Verify Internal Notes
		.verifyInternalNotes()
		
		//Type internal noted
		.typeInternalNotes(DataInputProvider.getCellData_ColName(iRowNumber, "internalnotes", sDataSheetName))
		
		
		//save and close
		.clickQucikCreateSaveAndCloseButton()
		
		.verifyErrorisNotDisplayed()
		;					
	}
}
