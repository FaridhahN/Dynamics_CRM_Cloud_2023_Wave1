package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_11231:_31006_Cloud: Verify warning message is NOT displayed when there is any DP change of an account with only location type children


public class TestCase_11231 {


	@Test
	public void DPchange(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//From the account search for the existing account and select
		.selectAccountsTab()


		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
		//Navigate to record status
		.navigateToAGDate()
		.chooseRecordStatusDraftfromTop()	
		.clickSave()
		
		//Navigate to Document tab
		.NavigateToDocumentTab()
		.clickGeneralTab()
		
		//Clear exisitng DP and select new DP
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
		
		//Verify DP change confirmation message
		.verifyDPChangeConfirmationNotDisplayed()
		.chooseRecordStatusPublished()

		//Data Reset
		
		//Navigate to Document Tab
		.NavigateToDocumentTab()
		.clickGeneralTab()

		//navigate to Record status feild
		.navigateToAGDate()
		
		.chooseRecordStatusDraftfromTop()	
		.clickSave()
		
		//Navigate to Document Tab
		.NavigateToDocumentTab()
		.clickGeneralTab()
		
		//Clear existing DP and select the new one
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
		
		//Verify the DP change confirmation message
		.verifyDPChangeConfirmationNotDisplayed()

		.chooseRecordStatusPublished()

		;
	}
}
