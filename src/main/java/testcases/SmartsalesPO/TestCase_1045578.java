package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1045578:_1045578_Verify whether following fields available in 'Pre Qualified' BPF stage is available in Opportunity. 

public class TestCase_1045578 {


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

		//Verify the BPF statges
		.verifyPrequalifiedStage()
		;



					
	}
}
