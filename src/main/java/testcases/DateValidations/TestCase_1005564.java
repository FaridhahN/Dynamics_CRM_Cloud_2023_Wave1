package testcases.DateValidations;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1005564:_1005564:Verify whether user able to add Start Date < or<= DPRD for the new member account. 


public class TestCase_1005564 {


	@Test
	public void DPChange(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		
		.clickNewOnAccountsPage()
		.chooseMemberForm()

		//3. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Click on save 			
		.clickSave() 

		//4. Verify CRM Account # is generated 
		.verifyCRMNumberIsDisplayed()	

		//5. Account Type = Member
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))

		//Class of Trade =Any
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))

		//Business Classification = Auto populated
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//Account Status = Auto Populated to Active
		.verifyDefaultAccountStatus()	

		//Application Start Date = Today's Date
		.chooseApplicationDate(DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate", sDataSheetName))

		//CAMS Flag = Yes
		.changeCAMSFlagAsYes()

		//Participation Type = Standard
		.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))


		//Direct Parent Entity Code = 673415
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//Direct Parent Relation = Managed
		.selectDirectParentRelationManaged() 

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(TestUtils.todaysDate())

		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		// Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( TestUtils.todaysDate())

		//Click on Save 
		.clickSave() 

		//6. Street 1 = Any
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))
		

		//Country =USA
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))


		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))

		//Click on Save 
		.clickSave() 

		//7.  Click the + icon on the Line of Business Grid
		.clickLineOfBusinesses()

		//Click New Line Of Business
		.clickAddNewLineOfBusiness()

		// Line of Business =General GPO
		.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

		// Classification Type = General GPO
		.selectLOBfClassificationType(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification", sDataSheetName))

		// Start Date =Today's date
		.typeLineOfBusinessStartDate(TestUtils.getPastDate(TestUtils.todaysDate(), 2))

		// Click on LOB Save 
		//.clickLOBSaveAndClose()
		.clickLOBSaveAndCloseDev()
		//Click add new membership
		.clickMembershipAndAddNewMembership()

		// Choose Membership type 
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		//Provide any start date and click on save
		.typeMembershipStartDate(TestUtils.getPastDate(TestUtils.todaysDate(), 2))

		//Click on membership save and close
		.clickQuickCreateMembershipSaveAndClose()

		//8. Record Status = Published
		.chooseRecordStatusPublished()

		//Click on Save 
		.clickSave() 

		//9. Verify Entity code is generated 
		.entityCodeIsDisplayed()

		;
	}
}
