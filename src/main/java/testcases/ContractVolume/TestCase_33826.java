package testcases.ContractVolume;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_33826:_11386 :Cloud :Deactive a contract volume


public class TestCase_33826 {


	@Test
	public void verifyAccountNumberEntity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Member
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Navigate to Contract volume
		.selectRelatedContractVolume()

		//verify Cotnract volume for user admin
		.verifyNewContractAttachmentButton()

		.clickNewContractAttachment()

		.addContractVolume(DataInputProvider.getCellData_ColName(iRowNumber, "contractNumber", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "purchasePeriod", sDataSheetName),TestUtils.todaysDate())

		.clicksaveAndCloseButton()


		.selectfirstContractVolume()

		.clickDeactivateContractVolume()

		.verifyErrorisNotDisplayed()


		;		}
}
