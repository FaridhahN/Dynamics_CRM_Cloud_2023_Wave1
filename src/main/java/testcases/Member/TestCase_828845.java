package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_828845:_828845_Cloud:Verify a Member can be converted into a Top Parent



public class TestCase_828845 {

	@Test
	public void ConvertMembertoTopParent(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to the CRM application
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

		//Navigate to system tab

		.NavigateToDocumentTab()
		.clickGeneralTab()

		//Navigate to Top Parent section and select Top parent as Yes
		.navigateToApplicationDate()
		.changeTopParentAsYes()

		//Select Top parent Classification
		.selectTopParentClassification(DataInputProvider.getCellData_ColName(iRowNumber, "topParentClassification", sDataSheetName))

		//Select Top Parent Region
		.selectRegion(DataInputProvider.getCellData_ColName(iRowNumber, "region", sDataSheetName))

		.clickSave()

		//Verify member has been changed to Top Parent 
		.verifyChangedToIsTopParent("---")

		//Data Reset

		//Navigate to system tab

		.NavigateToDocumentTab()
		.clickGeneralTab()

		//Navigate to Top Parent section and select Top parent as No
		.navigateToApplicationDate()
		.changeTopParentAsNo()

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

		;

	}	
}