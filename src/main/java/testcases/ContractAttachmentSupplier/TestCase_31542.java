package testcases.ContractAttachmentSupplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_31542_Test Case 9293 :Cloud-Add a contract attachment supplier Status
public class TestCase_31542 {


	@Test
	public void addCASupplierStatus(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member & Innovatix User credentials
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		///Go to Accounts with Contract Attachments
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Choose Existing Contract Attachments with CA Supplier
		.selectRelatedContractAttachments()	
		
		.doubleClickExistingContractAttachment(DataInputProvider.getCellData_ColName(iRowNumber, "contractNumber", sDataSheetName))

		//Add CA Supplier with all fields	
		.clickSuppliersTabOnCA()
		.clickNewContractAttachmentSupplierButton()	
		.chooseSupplierAccountOnCASupplier()		
		.selectAttachmentStatusOnCASupplier(DataInputProvider.getCellData_ColName(iRowNumber, "caSupplierAttachmentStatus", sDataSheetName))
		.chooseAttachmentStatusDateOnCASupplier()
		.typeTierOnCASupplier(DataInputProvider.getCellData_ColName(iRowNumber, "tier", sDataSheetName))
		.typeCommentsOnCASupplier(DataInputProvider.getCellData_ColName(iRowNumber, "comments", sDataSheetName))
		.clickSaveOnCASupplier()

		//Data Reset 		
		.clickDeactivateOnCASupplier();


	}
}
