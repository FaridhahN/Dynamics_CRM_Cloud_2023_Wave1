package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_944698:_944698_Verify user with Smart PO sales team can access the Partner sales app


public class TestCase_947038 {


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

		//navigate to Member account
		.navigateToMemberAccount()
		
		//Select Member account
		.selectMemberAccount(DataInputProvider.getCellData_ColName(iRowNumber, "memberAccount", sDataSheetName))
		
		//Select MemberContact
		.selectMemberContact(DataInputProvider.getCellData_ColName(iRowNumber, "memberContact", sDataSheetName))

		
		;



					
	}
}
