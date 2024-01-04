package testcases.Competitors;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_827912:_Test case 8615: Verify Competitor Lookup View is present in the Account Entity


public class TestCase_827912 {


	@Test
	public void verifyCompetitorLookUpView(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Innovatix User
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//Select Competitor Look Up View 
		.selectAccountsTab()	
		.selectCompetitorLookUpView()
		.verifyFieldsInCompetitorLookUpView()
		
		
		;

	}
}
