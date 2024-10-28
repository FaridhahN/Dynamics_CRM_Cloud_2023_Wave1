package testcases.SmartsalesPO;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_1004474:_1004474_Verify whether Opportunity is available in Opportunity Associated view of the 'Member Account'


public class TestCase_1004474 {


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

		//enter Topic
		.enterTopic(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName))
		//navigate to Member account
		.navigateToMemberAccount()
		
		//Select Member account
		.selectMemberAccount(DataInputProvider.getCellData_ColName(iRowNumber, "memberAccount", sDataSheetName))
		
	//click save button
		.clickSaveButton()
		
		//Navigate to Member page
		.navigateToMemberAccountPage()
		
		//Navigate to oppurtunity
		.navigatetoOppurtunity()
		
		//Select View
		.changetheOppurtunityView(DataInputProvider.getCellData_ColName(iRowNumber, "view", sDataSheetName))
				
		//Verify Oppurtunity
		.verifyOppurtunityCreated(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName))
		
		//Data resert
		
		//Open opputunity
		.openOppurtunityCreated(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName))
		
		//Mark as lost
		.clickmarkasLost();
		;



					
	}
}
