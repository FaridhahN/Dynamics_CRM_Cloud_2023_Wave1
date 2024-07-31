package testcases.AlithyaCPRM;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087566:_1087566:Verify whether following columns are available : "Topic, Estimated Revenue, Estimated Close Date, Potential Customer, Revenue Category, Stage" in 'CP-My Opportunities' view for Channel Partner Regional Manager.


public class TestCase_1087566 {


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
		.clickOppurtunitiesPage()
		.changeViewinOppurtunityPage("CP - My Opportunities")
		.clickAddColumn()
		.addColumnInTheView("Est. Revenue (Base)")
		.addColumnInTheView("Potential Customer")
		.addColumnInTheView("Active Stage")
		.clickApplyinEditColumn()
		.clikCancelButton()
		.verifyOppurtunityviewColumnafternewColumnAdded()
		.clikCancelButton()
		;					
	}
}
