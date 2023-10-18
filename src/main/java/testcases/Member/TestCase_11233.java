package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//Test case 11233: Cloud: Verify Top Parent Relation should be OLM for Top parent


public class TestCase_11233 {

	@Test
	public void CreateTopParent(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()
		.clickNewOnAccountsPage()
		.chooseMemberForm()

		//3. Account  Type = Member
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Is Top parent = Yes
		.changeTopParentAsYes()

		//Top parent relation = OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))
		//Top parent classification = Any
		.selectTopParentClassification(DataInputProvider.getCellData_ColName(iRowNumber, "topParentClassification", sDataSheetName))
		.typeTPReason(DataInputProvider.getCellData_ColName(iRowNumber, "tPReason", sDataSheetName))


		//Top parent relation date = Today's date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		//Click on save
		.clickSave() 

		.verifyError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))

		//	.navigateToParticipationType()
		//Participation Type = Standard
		.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))

		//5. Account  Type = Member
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))

		//Region = Any
		.selectRegion(DataInputProvider.getCellData_ColName(iRowNumber, "region", sDataSheetName))




		//Click on save
		.clickSave()
		.verifyError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
		;

	}	
}