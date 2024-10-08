package testcases.AlithyaCPRM;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087743:_1087743:Verify columns are removed from mentioned Contact Views for Channel Partner Senior Director


public class TestCase_1087741 {


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
		.clikCancelButton()
		.changeViewInContactssPage("My Channel Partner Sales Contacts")	
		.verifyColumnNameinContactView()
		
		;
	}
}
