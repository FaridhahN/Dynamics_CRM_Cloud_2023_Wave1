package testcases.PriceActivation;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_710199:_710199:Verify the new fields available on the Contract Attachment related to Price Activation

public class TestCase_710199 {


	@Test
	public void verifyAddContractAttachment(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member & Innovatix User credentials
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()
		
		//Open the existing account
		//2.Go to Workplace > Accounts and search for EIN 673415 
		//Open Existing CA
				.selectContractAttachmentsTab()
				.selectAllContractAttachentsView()	
				
				.openFirstContractAttachment()
				
				//Verify Tier
				.verifyTireRequested()
				
				.clickTab(10)
				
				//Verify Price Activation
				.verifyPriceActivationSection()
				
				//Navigate to System Tab
				.clickSystemTabOnCA()
				
				
	;}
}
