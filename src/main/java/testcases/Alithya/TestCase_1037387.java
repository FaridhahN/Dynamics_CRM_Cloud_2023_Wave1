package testcases.Alithya;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1037387:_1037387:Verify "My Contacts - Sales" view is available in Contact System View


public class TestCase_1037387 {


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
		
		//Navigate to Contact page and select My contact - sales view
		.clickContacts() 
		.changeViewInContactssPage("My Contacts - Sales")
		
		//Verify columns in contact view
		.verifyContactviewColumnafternewColumnAdded()
		.clikCancelButton()
		.clickAddColumn()
		
		//Add Owner column
		.addColumnInTheView("Owner")
		.clickApplyinEditColumn()
		.verifyOwnerDate(DataInputProvider.getCellData_ColName(iRowNumber, "user", sDataSheetName))
		
		;					
	}
}
