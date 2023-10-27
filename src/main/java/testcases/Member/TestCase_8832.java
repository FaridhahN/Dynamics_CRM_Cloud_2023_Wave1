package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_8832:_828828_Cloud: Validate Top Parent Relationship Date Change

public class TestCase_8832 {


	@Test
	public void TPRDValidation(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		.typeTPReason("")
		.selectTopParentRelationDate("10/10/2023")


		//Record Status = Published
		.clickSave()

		.verifyTPexceptionreasonDisplayed()

		.typeTPReason("Test")
		.clickSave()

		.typeTPReason("")
		.clickSave()
		//Navigate to system tab

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


		//Click on Save 
		.clickSave() 

		//Verify DP Change confirmation message
		.verifyDPChangeConfirmation(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))


		//Publish the account
		.chooseRecordStatusPublished()

		.clickSave()

		.verifyTPexceptionreasonnotDisplayed()


		//Data reset -- Change the DP to the previous one

		//Navigate to Sysyem tab
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
