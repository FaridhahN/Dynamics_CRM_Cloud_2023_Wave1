package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1034647:_1034647:Verify 'Internal Notes' field is available on Main Appointment Form




public class TestCase_1034647 {


	@Test
	public void Oppurtunities(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.staysignedinforOtherApp()

		.selectSalesHubAccount()
		.clickActivities()
		.clickAppointment()
		.typeSubject(DataInputProvider.getCellData_ColName(iRowNumber, "subject", sDataSheetName))
		.clickTab(3)
		.selectRagrding(DataInputProvider.getCellData_ColName(iRowNumber, "regardingName", sDataSheetName))
		.clickTab(8)
		.verifyInternalNotes()
		.typeInternalNotes(DataInputProvider.getCellData_ColName(iRowNumber, "internalnotes", sDataSheetName))
		
		;					
	}
}
