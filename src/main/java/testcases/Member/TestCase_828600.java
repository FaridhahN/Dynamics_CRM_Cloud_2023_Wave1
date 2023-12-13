package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

// TFS ID_828600:_828600:Verify Whether user able to end date Premier Membership on Top Parent ,should not get any Business Process Error .

public class TestCase_828600 {

	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
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

		//Account name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
		
		//3. Account  Type = Member
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))

		//Class of Trade =Any
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))

		//Business Classification = Auto populated
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//Account Status = Auto Populated to Active
		.verifyDefaultAccountStatus()	

		//Application Start Date = Today's Date
		.chooseApplicationStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate", sDataSheetName))

		//Is Top parent = Yes
		.changeTopParentAsYes()

		//Top parent relation date = Today's date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		//Participation Type = Standard
		.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))

		//Direct Parent Relation = Owned(Auto populated)
		.verifyDirectParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "verifyDirectParentRelation", sDataSheetName))

		//Region = Any
		.selectRegion(DataInputProvider.getCellData_ColName(iRowNumber, "region", sDataSheetName))

		//Top parent classification = Any
		.selectTopParentClassification(DataInputProvider.getCellData_ColName(iRowNumber, "topParentClassification", sDataSheetName))

		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		//Require Manual AG =Yes
		//		 .changeRequireManualAG()
		//		 
		//		 //Select affiliate group =Affiliate NonAcute
		//		 .selectAffiliateGroup(DataInputProvider.getCellData_ColName(iRowNumber, "AffiliateGroup", sDataSheetName))
		//		 
		//		 //Enter affiliate group effective date = today's date
		//		 .selectAffiliateGroupEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "AffiliateGroupEffectiveDate", sDataSheetName))

		//Click on Save
		.clickSave() 

		//4.Street 1 = Any
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//Country =USA
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))

		//Click on Save 
		.clickSave()   


		//5. Fee share eligible = Yes
		.changeFeeShareEligibleToYes()

		//Fee share eligible date = Today's date
		.selectFeeShareEligibleDate(DataInputProvider.getCellData_ColName(iRowNumber, "feeShareEligibleStartDate", sDataSheetName))

		//FBO Type = Any
		.selectFBOGPOType(DataInputProvider.getCellData_ColName(iRowNumber, "FBOType", sDataSheetName))

		//FBO effective date = Today's date
		.selectFBOEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "FBOEffectiveDate", sDataSheetName))

		//Click on Save 
		.clickSave()  	

		//6.  Click the + icon on the Line of Business Grid
		.clickLineOfBusinesses()

		//Click New Line Of Business
		.clickAddNewLineOfBusiness()

		// Line of Business =General GPO
		.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

		// Classification Type = General GPO
		.selectLOBfClassificationType(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification", sDataSheetName))

		// Start Date =Today's date
		.typeLineOfBusinessStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusinessStartDate", sDataSheetName))

		// Click on LOB Save 
		//.clickLOBSaveAndClose()
		.clickLOBSaveAndCloseDev()

		//Click add new membership
		.clickMembershipAndAddNewMembership()

		// Choose Membership type 
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		//Provide any start date and click on save
		.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

		//Click on membership save and close
		.clickQuickCreateMembershipSaveAndClose()

		//7. Record Status = Published
		.chooseRecordStatusPublished()

		//Click on Save 
		.clickSave() 

		//8. Verify Entity code is generated 
		.entityCodeIsDisplayed()

		//9.Move the record status to draft and save  ***** Record moved to draft 
				.chooseRecordStatusDraft()

				//Click on Save 
				.clickSave() 

				//10.Go to membership and Open the Premier National membership ***** Premier National membership should be opened 
				.goToMembershipPage(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
				//.doubleClickOnNationalMembership()

				//11.Provide end date = Any future date **** Account should be saved successfully 
				.typeMembershipEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))

				// End reason = Anything from dropdown,
				.selectMembershipEndReason(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndReason", sDataSheetName))

				// then save
				.clickMembershipSaveAndClose()
				
				.verifyErrorisNotDisplayed()
		;
	}
}
