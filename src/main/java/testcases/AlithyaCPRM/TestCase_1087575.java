package testcases.AlithyaCPRM;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1087575:_1087575:Verify whether following Columns are available : "Topic, Estimated Revenue, Estimated Close Date, Potential Customer, Revenue Category, Stage" in 'All Opportunities' view for Channel Partner Regional Manager.

public class TestCase_1087575 {


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
		.changeViewinOppurtunityPage("CP - All Opportunities")
		.clickAddColumn()
		.addColumnInTheView("Est. Revenue (Base)")
		.clickAddColumnInPopup()
		.addColumnInTheView("Potential Customer")
		.clickAddColumnInPopup()
		.addColumnInTheView("Active Stage")
		.clickApplyinEditColumn()
		.clikCancelButton()
		.verifyOppurtunityviewColumnafternewColumnAdded()
		.clikCancelButton()

		;					
	}
}
