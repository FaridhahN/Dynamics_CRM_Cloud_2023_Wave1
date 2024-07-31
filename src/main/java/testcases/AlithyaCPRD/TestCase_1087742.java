package testcases.AlithyaCPRD;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087742:_1087742:Verify columns are removed from mentioned Contact Views for Channel Partner Regional Director



public class TestCase_1087742 {


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
		
		.clickContacts()
		.changeViewInContactssPage("All Channel Partner Sales Contacts")	
		.verifyColumnNameinContactView()
		.changeViewInContactssPage("My Channel Partner Sales Contacts")	
		.verifyColumnNameinContactView()
		
		;
	}
}
