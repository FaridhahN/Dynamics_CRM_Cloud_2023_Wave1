package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
import utils.TestUtils;
//TFS ID_7150: Create new Shipto to main account conversion

public class TestCase_7150 {


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
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.getDPData()
		.clickNewOnAccountsPage()
		.chooseMemberForm()

		//Account name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Account  Type = Member
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))


		//Class of Trade =Any
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))

		//Business Classification = Auto populated
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//Account Status = Auto Populated to Active
		.verifyDefaultAccountStatus()	

		//Store/Location type = Shipto
		.chooseLocationType(DataInputProvider.getCellData_ColName(iRowNumber, "locationType", sDataSheetName))

		//Direct Parent Entity Code = 673415
				.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

				
				
		//Direct Parent Relation = Managed
		.selectDirectParentRelationManaged() 

		
		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(TestUtils.todaysDate()) 

		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		//Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( TestUtils.todaysDate())

		//Click on save 			
		.clickSave() 

		//4. Street 1 = Any
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//Country =USA
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))

		//Click on Save 
		.clickSave() 

		//5. Record Status = Published
		.chooseRecordStatusPublished()	

		//Click on Save 
		.clickSave() 

		.verifyAgEffectiveDateIsNull()
		.verifyAffiliateGroupIsNull()

		.pageRefresh()
		.clickSave() 


		//6. Verify Entity code is same as DP's entity code  
		.verifyEntityCode(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))


		//Verify Premier start date is auto populated
		.verifyPremierStartDateIsAutoPopulated()

		.navigateToApplicationDate()
		.navigateToCorporateParentMemberoForm()

		//13. Verify "IS Corporate account" field
		.verifyIsCorporateAccount(WebDriverServiceImpl.Dpdata.get("IsCorporate"))

		//14. Verify Corporate parent name in the form
		.verifyCorporateParentName(WebDriverServiceImpl.Dpdata.get("CorporateName"))

		//15. Verify "Is Food Service parent" field 
		.verifyIsFoodServiceParent(WebDriverServiceImpl.Dpdata.get("isFoodService"))

		//16 Verify Food Service parent name in the form 
		.verifyFoodServiceParentName(WebDriverServiceImpl.Dpdata.get("FoodServiceName"))

		//17 Verify Sponsor field 
		.verifySponsor(WebDriverServiceImpl.Dpdata.get("SponsorName"))

		//16 Verify "Is Sponsor" field 
		.verifyIsSponsor(WebDriverServiceImpl.Dpdata.get("isSponsor"))	

		//Move the status from Published to draft and update the following:
		.chooseRecordStatusDraft()

		//Click on Save 
		.clickSave()
		.pageRefresh()
		.verifyDefaultAccountStatus()
		//Application Start Date = Today's Date
		.chooseApplicationDateWithoutScrolling(DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate", sDataSheetName))

		//Participation Type = Standard
		.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))

		//Click on Save 
		.clickSave()

		//Store/Location type = Null	
		.chooseLocationTypeNull()

		.clickSave()

		//Click the + icon on the Line of Business Grid
		.clickLineOfBusinesses()

		//Click New Line Of Business
		.clickAddNewLineOfBusiness()

		// Line of Business =General GPO
		.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

		// Classification3 Type = General GPO
		.selectLOBfClassificationType(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification", sDataSheetName))

		// Start Date =Today's date
		.typeLineOfBusinessStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusinessStartDate", sDataSheetName))

		// Click on LOB Save 
		//.clickLOBSaveAndClose()
		.clickLOBSaveAndCloseDev()
		
		//Click the + icon on the Line of Business Grid
				.clickLineOfBusinesses()

				//Click New Line Of Business
				.clickAddNewLineOfBusiness()

				// Line of Business =General GPO
				.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness2", sDataSheetName))

				// Classification3 Type = General GPO
				.selectLOBfClassificationType(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification2", sDataSheetName))

				// Start Date =Today's date
				.typeLineOfBusinessStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusinessStartDate", sDataSheetName))

				// Click on LOB Save 
				//.clickLOBSaveAndClose()
				.clickLOBSaveAndCloseDev()

		//8. Record Status = Published
		.clickGeneralThenChooseRecordStatusPublished()

		//Click on Save 
		.clickSave() 

		//9. Verify Entity code is generated 
		.verifyEntityCodeIsNotDPEntityCode(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//Verify Premier start date is auto populated
		.verifyPremierStartDateIsAutoPopulated()

		.verifyAffiliateGroupIsNotNull()
		.verifyAgEffectiveDateIsNotNull()
		
		.navigateToApplicationDate()
		.navigateToCorporateParentMemberoForm()

		//10. Verify "IS Corporate account" field
		//13. Verify "IS Corporate account" field
		.verifyIsCorporateAccount(WebDriverServiceImpl.Dpdata.get("IsCorporate"))

		//14. Verify Corporate parent name in the form
		.verifyCorporateParentName(WebDriverServiceImpl.Dpdata.get("CorporateName"))

		//15. Verify "Is Food Service parent" field 
		.verifyIsFoodServiceParent(WebDriverServiceImpl.Dpdata.get("isFoodService"))

		//16 Verify Food Service parent name in the form 
		.verifyFoodServiceParentName(WebDriverServiceImpl.Dpdata.get("FoodServiceName"))

		//17 Verify Sponsor field 
		.verifySponsor(WebDriverServiceImpl.Dpdata.get("SponsorName"))

		//16 Verify "Is Sponsor" field 
		.verifyIsSponsor(WebDriverServiceImpl.Dpdata.get("isSponsor"));

		/*	//16  Go to > and click on Membership entity and double click on the Top parent membership entity
			.goToMembershipPage(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
			//.doubleClickOnNationalMembership()

		//17 Click on > and go to Audit history 
			.selectMembershipAuditHistory()

		//18 Verify "Is Member Add mail sent" flag turned from No to Yes 
			.verifyIsMemberAddMailSent()

		//19 Verify the time-stamp on which the flag gets updated 
			.verifyTimeStampInTPMembershipAuditHistory();
		 */

	}
}
