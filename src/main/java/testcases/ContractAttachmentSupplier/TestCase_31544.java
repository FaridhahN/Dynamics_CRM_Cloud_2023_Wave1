package testcases.ContractAttachmentSupplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_31544:_Test Case 9295 :Cloud-Deactivate contract attachment supplier status.

public class TestCase_31544 {


	@Test
	public void deactivateCASupplier(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member & Innovatix User credentials
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()
		//Go to Accounts with Contract Attachments
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
		//Choose Existing Contract Attachments with CA Supplier
		.selectRelatedContractAttachments()		
		.doubleClickExistingContractAttachment(DataInputProvider.getCellData_ColName(iRowNumber, "contractNumber", sDataSheetName))
		
		
		//Access Contract Attachment Supplier and Deactivate	
		.clickSuppliersTabOnCA()
		.doubleClickCASupplier()		
		.clickDeactivateOnCASupplier()
		
		
		//Data Reset 		
		.clickActivateOnCASupplier();
		
		}
}
