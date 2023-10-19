package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_ 7137:Create new member - New Member form and Save it as prospect first
//Test Case 8763:Automation Cloud : Validate auto-generated CRM Number for Members, Suppliers
//Test Case 8791:Cloud - Verify when selecting any "Class of trade" , "Not Found" Error window should not get displayed.

public class TestCase_11232 {


	@Test
	public void createMember(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()


		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.navigateToAGDate()
		//5. Record Status = Published
		.chooseRecordStatusDraftfromTop()	
		.clickSave()
		.NavigateToSystemTab()
		.clickGeneralTab()
		.clearDP()

		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//Direct Parent Relation = Managed
		.selectDirectParentRelationManaged() 

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		.typeDPReason("Test")
		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		// Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		.typeTPReason("Test")
		//Click on Save 
		.clickSave() 
		
		.verifyDPChangeConfirmation(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		.chooseRecordStatusPublished()

		
		.NavigateToSystemTab()
		.clickGeneralTab()

		.navigateToAGDate()
		//5. Record Status = Published
		.chooseRecordStatusDraftfromTop()	
		.clickSave()
		.NavigateToSystemTab()
		.clickGeneralTab()
		.clearDP()

		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "changedirectParent", sDataSheetName))

		//Direct Parent Relation = Managed
		.selectDirectParentRelationManaged() 

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		.typeDPReason("Test")
		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		// Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		.typeTPReason("Test")
		//Click on Save 
		.clickSave() 
		.verifyDPChangeConfirmation(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))

		.chooseRecordStatusPublished()

		;
	}
}
