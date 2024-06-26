package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_11232:_31007_ Cloud: Verify warning message is displayed when there is any DP change of an account with children (non location type)

public class TestCase_11232 {


	@Test
	public void DPChange(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//SEarch an account
		.selectAccountsTab()


		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Navigate to the Record status feild
		.navigateToAGDate()

		//Record Status = Published
		.chooseRecordStatusDraftfromTop()	
		.clickSave()

		//Navigate to Documents tab

		.NavigateToDocumentTab()
		.clickGeneralTab()

		//Change DP after clearing
		.clearDP()
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//Direct Parent Relation = Managed
		.selectDirectParentRelationManaged() 

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		//Type the DP reason
		.typeDPReason("Test")

		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		// Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		//Type TP reason
		.typeTPReason("Test")

		//Click on Save 
		.clickSave() 

		//Verify DP Change confirmation message
		.verifyDPChangeConfirmation(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		.chooseRecordStatusPublished()

		//Data reset -- Change the DP to the previous one

		//Navigate to Document tab
		.NavigateToDocumentTab()
		.clickGeneralTab()

		//Navigate to the record status field 
		.navigateToAGDate()

		//Record Status = Published
		.chooseRecordStatusDraftfromTop()	
		.clickSave()
		.NavigateToDocumentTab()
		.clickGeneralTab()

		//Change back to the previous DP
		.clearDP()

		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "changedirectParent", sDataSheetName))

		//Direct Parent Relation = Managed
		.selectDirectParentRelationManaged() 

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		//Type DP reason
		.typeDPReason("Test")
		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		// Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		//Type TP reason
		.typeTPReason("Test")
		//Click on Save 
		.clickSave() 

		//Verify DP change confirmation message
		.verifyDPChangeConfirmation(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))

		//Publish the account
		.chooseRecordStatusPublished()

		;
	}
}
