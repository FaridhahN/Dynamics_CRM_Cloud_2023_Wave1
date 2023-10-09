package testcases.MemberEntryForm;

import org.testng.annotations.Test;
import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;


//Test Case 8838:Cloud: Create new Shipto account - New Member entry form and Save it as prospect first

public class TestCase_8838 {


	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{
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
		.chooseMemberEntryForm()

		//3. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))


		//Direct Parent Entity Code = 673415
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//Direct Parent Relation = Managed
		.selectDirectParentRelationManaged() 

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		//6. Street 1 = Any
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//Country =USA
		//	.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))

		//Class of Trade =Any
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))

		//Business Classification = Auto populated
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//Store/Location type = Bill to
		.chooseLocationType(DataInputProvider.getCellData_ColName(iRowNumber, "locationType", sDataSheetName))	
		//Top Parent Relation =  OLM
		.selectTopParentRelationMEF(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		// Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))
		//Click on Save 
		.clickSave() 

		//4. Verify CRM Account # is generated 
		.verifyCRMNumberIsDisplayed()	

		//5. Account Type = Member
		.selectAccountTypeMEF(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))

		//Account Status = Auto Populated to Active
		.verifyDefaultAccountStatus()	

		//CAMS Flag = Yes
		//.changeCAMSFlag()

		//Click on Save 
		.clickSave() 
		.navigatetoAdditionaCriteriaFromDonotVerify()
		//7. Record Status = Published
		.chooseRecordStatusPublishedMEF()				

		//Click on Save 
		.clickSave() 

		.pageRefresh()

		//Wave2 Fix 
		.clickAdditionalCriteria()


		//8. Verify Entity code is same as DP's entity code 
		.verifyEntityCode(DataInputProvider.getCellData_ColName(iRowNumber, "DirectParent", sDataSheetName))

		//Verify Premier start date is auto populated
		.verifyPremierStartDateIsAutoPopulated()
		.navigateToAGDateMEF()
		.verifyAgEffectiveDateIsNull()
		.verifyAffiliateGroupIsNull()

		//FBO details verification
		//Verify "Is FBO" field 

		.verifyIsFBO(WebDriverServiceImpl.Dpdata.get("IsFBO"))

		//FBO
		.verifyFBO(WebDriverServiceImpl.Dpdata.get("FBO"))

		//FBORD
		.verifyFBORD(DataInputProvider.getCellData_ColName(iRowNumber, "verifyFBORD", sDataSheetName))
		.navigateToRecordStatus()
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

		;


	}
}
