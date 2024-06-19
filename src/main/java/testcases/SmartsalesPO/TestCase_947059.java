package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_947059:_947059_Verify whether 'Est. # of Licenses' is available in Demonstrate BPF.

public class TestCase_947059 {


	@Test
	public void smartPO(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.staysignedinforOtherApp()

		.selectPartnerSale()
		//Navigate to Partner sales
		.navigatetoPartnerSales()
		
		//Navigate to Oppurtunities
		.clickOppurtunities()
		
		//Click New Oppurtunities Button
		.clickNewOppurtunitiesbutton()

		//click Demonstrate status
		.clickDemonstratestatus()

		//verify Estimate license field
		.verifyEstNoOfLicenses()
		
		//click the close button
		.clickCloseButtonProcessStage()
		;



					
	}
}
