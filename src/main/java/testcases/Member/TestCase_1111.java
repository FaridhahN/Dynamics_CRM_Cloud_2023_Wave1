package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

public class TestCase_1111 {


	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace > Accounts > +New 
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.getDPData()
		.clickNewOnAccountsPage()
		.chooseMemberForm()

		//3. Account Name
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//4. Account Name 2
		.typeAccountName2(DataInputProvider.getCellData_ColName(iRowNumber, "accountName2", sDataSheetName))

		//5. Premier Start Date
		//.pickPremierStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "PremierStartDate", sDataSheetName))

		//6. Primary Contact
		.addMemberPrimaryContact(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))

		//7.Class of Trade =Any
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))		

		//12. Application Start Date = Today's Date
		.chooseApplicationStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate", sDataSheetName))

		// Group - Auto populates
		.verifyGroup(DataInputProvider.getCellData_ColName(iRowNumber, "verifyGroup", sDataSheetName))

		//8. FacilityType
		.verifyFacilityType(DataInputProvider.getCellData_ColName(iRowNumber, "verifyFacilityType", sDataSheetName))

		//9.Business Classification =Any
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//10. Premier Owner
		.verifyPremierOwner(DataInputProvider.getCellData_ColName(iRowNumber, "verifyPremierOwner", sDataSheetName))

		//11. Account Status = Auto Populated to Active
		.verifyAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "acountStatus", sDataSheetName))	

		//13. CAMS flag
		.verifyCAMSFlag(DataInputProvider.getCellData_ColName(iRowNumber, "verifyCAMSFlag", sDataSheetName))

		//14.Exclude From Roaster
		.verifyExcludeFromRoaster(DataInputProvider.getCellData_ColName(iRowNumber, "excludeFromRoaster", sDataSheetName))

		//15. Participation Type = Standard
		.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))

		//16. BK Active
		.verifyBKActive(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBKActive", sDataSheetName))

		//17. SupplierRecord
		.typeSupplierRecord(DataInputProvider.getCellData_ColName(iRowNumber, "supplierRecord", sDataSheetName))

		//18. Ownership
		.selectOwnership(DataInputProvider.getCellData_ColName(iRowNumber, "ownership", sDataSheetName))

		//Stock symbol
		.typeStockSymbol(DataInputProvider.getCellData_ColName(iRowNumber, "stocksymbol", sDataSheetName))

		//Exchange 
		.typeExchange(DataInputProvider.getCellData_ColName(iRowNumber, "exchange", sDataSheetName))

		//IsPaymentEntity
		.verifyIsPaymentEntity(DataInputProvider.getCellData_ColName(iRowNumber, "verifyIsPaymentEntity", sDataSheetName))

		//PaymentEntity
		.addPaymentEntity(DataInputProvider.getCellData_ColName(iRowNumber, "paymentEntity", sDataSheetName))

		//CorporateRebate
		.selectCorporateRebate(DataInputProvider.getCellData_ColName(iRowNumber, "corporateRebate", sDataSheetName))	

		//CorporateRebateStartDate
		.selectCorporateRebateFeeDate(DataInputProvider.getCellData_ColName(iRowNumber, "corporateRebateFeeDate", sDataSheetName))

		/*//19. Require Manual AG =Yes
		.changeRequireManualAG()

		 //20. Select affiliate group =Affiliate NonAcute
		 .selectAffiliateGroup(DataInputProvider.getCellData_ColName(iRowNumber, "AffiliateGroup", sDataSheetName))

		 //21. Enter affiliate group effective date = today's date
		 .selectAffiliateGroupEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "AffiliateGroupEffectiveDate", sDataSheetName))
		 */

		//23. Account  Type = Member
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))

		//24. Record Change Status
		//.changeRecordStatus(DataInputProvider.getCellData_ColName(iRowNumber, "ChangeRecordStatus", sDataSheetName))	

		//Address Information

		// Street 1 = Any
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		// Street 2 = Any
		.typeStreet2(DataInputProvider.getCellData_ColName(iRowNumber, "street2", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//Country =USA
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		//Delivery info
		.typeDeliveryInfo(DataInputProvider.getCellData_ColName(iRowNumber, "deliveryInfo", sDataSheetName))

		//State
		.typeState(DataInputProvider.getCellData_ColName(iRowNumber, "state", sDataSheetName))

		//county
		.typeCounty(DataInputProvider.getCellData_ColName(iRowNumber, "county", sDataSheetName))

		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))

		//OverrideName
		.typeOverrideName(DataInputProvider.getCellData_ColName(iRowNumber, "overrideName", sDataSheetName))

		//MainPhone
		.typeMainPhone(DataInputProvider.getCellData_ColName(iRowNumber, "mainPhone", sDataSheetName))	

		//OtherPhone
		.typeOtherPhone(DataInputProvider.getCellData_ColName(iRowNumber, "otherPhone", sDataSheetName))

		//Fax
		.typeFax(DataInputProvider.getCellData_ColName(iRowNumber, "fax", sDataSheetName))

		//Website
		.typeWebsite(DataInputProvider.getCellData_ColName(iRowNumber, "website", sDataSheetName))


		//ReceiveDirectMail
		.verifyReceiveDirectMail(DataInputProvider.getCellData_ColName(iRowNumber, "receiveDirectMail", sDataSheetName))

		//FSRPTFlag
		.verifyFSRPTFlag(DataInputProvider.getCellData_ColName(iRowNumber, "FSRPTFlag", sDataSheetName))

		//DoNotVerifyAddress
		.verifyDoNotVerifyAddress(DataInputProvider.getCellData_ColName(iRowNumber, "doNotVerifyAddress", sDataSheetName))

		//ExternalAddessID
		.verifyExternalAddessID(DataInputProvider.getCellData_ColName(iRowNumber, "externalAddessID", sDataSheetName))


		//Direct Parent

		////Direct Parent Entity Code = 673415
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//Direct Parent Relation = Managed
		.selectDirectParentRelationManaged() 

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		//DPExceptionReason
		.typeDPReason(DataInputProvider.getCellData_ColName(iRowNumber, "dPReason", sDataSheetName))

		//Top Parent

		// Verify Top parent is populated 
		.verifyDirectParent(WebDriverServiceImpl.Dpdata.get("DP_Name"))

		//Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		//Top parent classification = Any
		.selectTopParentClassification(DataInputProvider.getCellData_ColName(iRowNumber, "topParentClassification", sDataSheetName))

		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		// IsTopParent
		.verifyIsTopParent(DataInputProvider.getCellData_ColName(iRowNumber, "isTopParent", sDataSheetName))

		//Click on Save

		.clickSave() 

		//Fee Share

		//		//Fee share eligible		
		//		.changeFeeShareEligibleToYes()
		//		
		//		//Fee share eligible date = Today's date
		//		.selectFeeShareEligibleDate(DataInputProvider.getCellData_ColName(iRowNumber, "feeShareEligibleStartDate", sDataSheetName))

		//Sponsor

	
		//Corporate Parent
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

		//FBO details verification
		//Verify "Is FBO" field 

		.verifyIsFBO(WebDriverServiceImpl.Dpdata.get("IsFBO"))

		//FBO
		.verifyFBO(WebDriverServiceImpl.Dpdata.get("FBO"))

		//FBORD
		.verifyFBORD(DataInputProvider.getCellData_ColName(iRowNumber, "verifyFBORD", sDataSheetName))


		//		//FBO Type = Any
		//		.selectFBOGPOType(DataInputProvider.getCellData_ColName(iRowNumber, "FBOType", sDataSheetName))
		//				
		//		//FBO effective date = Today's date
		//		.selectFBOEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "FBOEffectiveDate", sDataSheetName))

		//Click on Save 
		.clickSave() 

		//Account numbers
		//CRM number
		.verifyCRMNumberIsDisplayed()	

		//Enter Hin

		//Select account number entity
		.selectAccountNumbers()

		//Click on add new account number in Account numbers entity
		.clickAddNewAccountNumberInAccountNumbers()

		//Select Account type
		.chooseAccountNumberTypeHIN()

		//Enter HIN Account number
		.typeAccountNumberHIN()

		//Click on Save and Close
		.clickSaveAndClosInAccountNumbers()



		//Enter DEA
		//Click on add new account number in Account numbers entity
		.clickAddNewAccountNumberInAccountNumbers()

		//Select Account type
		.chooseAccountNumberTypeDEA()

		//Enter DEA account number
		.typeAccountNumberDEA()

		//Click on Save and Close
		.clickSaveAndClosInAccountNumbers()

		//Click on General tab
		.clickGeneralTab()

		//Verify DEA number
		.verifyDEA(DataInputProvider.getCellData_ColName(iRowNumber, "DEANumber", sDataSheetName))

		//Verify HIN
		.verifyHIN(DataInputProvider.getCellData_ColName(iRowNumber, "HIN", sDataSheetName))

		//Click the + icon on the Line of Business Grid
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
		.clickLOBSaveAndClose()

		//Add Membership provider
		.clickMembershipAndAddNewMembership()

		// Choose Membership type 
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		//Provide any start date and click on save
		.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

		//Click on membership save and close
		.clickQuickCreateMembershipSaveAndClose()

		//. Record Status = Published
		.chooseRecordStatusPublished()

		//Click on Save 
		.clickSave() 

		//. Verify Entity code is generated 
		.entityCodeIsDisplayed()

		;	
	}
}
