package testcases.MemberEntryForm;

import org.testng.annotations.Test;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_7139:Create new member - New Member entry form through sub account and save it as prospect first
import utils.TestUtils;

public class TestCase_7139 {


	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{
		//1. Login to CRM using member supervisor / member credentials
		WebDriverServiceImpl.isMemberForm=true;
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.getDPData()
		.selectSubaccount()


		//4. Click on Add new account 
		.clickNewAccountInSubAccount()
		.chooseMemberEntryForm()


		//5. Verify Direct parent and Top parent are populated 
		.verifyDirectParent(WebDriverServiceImpl.Dpdata.get("DP_Name"))
		.verifyTopParent(WebDriverServiceImpl.Dpdata.get("TopParent_Name"))

		//6. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Direct Parent Relation = Managed
		.selectDirectParentRelationManaged() 

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(TestUtils.todaysDate())


		//DP exception reason = Any
		.typeDPReason(DataInputProvider.getCellData_ColName(iRowNumber, "DPReason", sDataSheetName))

		//9.  Street 1 = Any
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))

		//Country =USA
		//	.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		//Class of Trade =Any
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))

		//Business Classification = Auto populated
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//Account Status = Auto Populated to Active
		.verifyDefaultAccountStatus()	

		//Participation Type = Standard
		.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))


		//Top Parent Relation =  OLM
		.selectTopParentRelationMEF(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		//Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		//type TP reason
		.typeTPReason(DataInputProvider.getCellData_ColName(iRowNumber, "TPReason", sDataSheetName))

		//Click on save 
		.clickSave() 

		//7. Verify CRM Account # is generated 
		.verifyCRMNumberIsDisplayed()	


		//8 Account  Type = Member
		.selectAccountTypeMEF(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))


		//Application Start Date = Today's Date
		.chooseApplicationDateMEF(DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate", sDataSheetName))

		//CAMS Flag = Yes
		.changeCAMSFlagAsYes()

		//Click on Save 
		.clickSave() 

		//10.  Click the + icon on the Line of Business Grid
		.clickLineOfBusinessesMEF()

		//Click New Line Of Business
		.clickAddNewLineOfBusiness()

		// Line of Business =General GPO
		.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

		// Classification Type = General GPO
		.selectLOBfClassificationType(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification", sDataSheetName))

		// Start Date =Today's date
		.typeLineOfBusinessStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusinessStartDate", sDataSheetName))

		// Click on LOB Save 
		.clickLOBSaveAndCloseMEF()


		//Click add new membership
		.clickMembershipAndAddNewMembership()

		// Choose Membership type 
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		//Provide any start date and click on save
		.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

		//Click on membership save and close
		.clickMembershipSaveAndCloseMEF()

		//11. Record Status = Published
		.chooseRecordStatusPublishedMEF()

		//Click on Save 
		.clickSave() 

		//12. Verify Entity code is generated 
		.entityCodeIsDisplayed()

		//Verify Premier start date is auto populated
		.verifyPremierStartDateIsAutoPopulated()

		.verifyAffiliateGroupIsNotNullMEF()
		.verifyAgEffectiveDateIsNotNull()

		//FBO details verification
		//Verify "Is FBO" field 

		.verifyIsFBO(WebDriverServiceImpl.Dpdata.get("IsFBO"))

		//FBO
		.verifyFBO(WebDriverServiceImpl.Dpdata.get("FBO"))

		//FBORD
		.verifyFBORD(DataInputProvider.getCellData_ColName(iRowNumber, "verifyFBORD", sDataSheetName))
		.navigateToSponsor()
		//17 Verify Sponsor field 
		.verifySponsor(WebDriverServiceImpl.Dpdata.get("SponsorName"))

		//16 Verify "Is Sponsor" field 
		.verifyIsSponsor(WebDriverServiceImpl.Dpdata.get("isSponsor"))
		.navigateToCorporateParent()

		//13. Verify "IS Corporate account" field
		.verifyIsCorporateAccountMEF(WebDriverServiceImpl.Dpdata.get("IsCorporate"))

		//14. Verify Corporate parent name in the form
		.verifyCorporateParentName(WebDriverServiceImpl.Dpdata.get("CorporateName"))
		.navigateToFoodServices()
		//15. Verify "Is Food Service parent" field 
		.verifyIsFoodServiceParent(WebDriverServiceImpl.Dpdata.get("isFoodService"))

		//16 Verify Food Service parent name in the form 
		.verifyFoodServiceParentName(WebDriverServiceImpl.Dpdata.get("FoodServiceName"))



		.clickGoBackButton()
		.changeMemberForm()
		.clickSave()

		/*
		 * //19 Go to > and click on Membership entity and double click on the Top
		 * parent membership entity
		 * .goToMembershipPage(DataInputProvider.getCellData_ColName(iRowNumber,
		 * "membershipProvider", sDataSheetName)) //.doubleClickOnNationalMembership()
		 * 
		 * //20 Click on > and go to Audit history .selectMembershipAuditHistory()
		 * 
		 * //21 Verify "Is Member Add mail sent" flag turned from No to Yes
		 * .verifyIsMemberAddMailSent()
		 * 
		 * //22 Verify the time-stamp on which the flag gets updated
		 * .verifyTimeStampInTPMembershipAuditHistory()
		 */

		;

	}

}
