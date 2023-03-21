package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_7147:Create new Shipto account - New Member form through sub account and save it as prospect first

public class TestCase_7147 {

	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
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
		.selectAccountFromSearchResults()
		.getDPData()
		.selectSubaccount()

		//4. Click on Add new account 
		.clickNewAccountInSubAccount()

		//5. Verify Direct parent and Top parent are populated 
		.verifyDirectParent(WebDriverServiceImpl.Dpdata.get("DP_Name"))
		.verifyTopParent(WebDriverServiceImpl.Dpdata.get("TopParent_Name"))

		//6. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		//Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		//type TP reason
		.typeTPReason(DataInputProvider.getCellData_ColName(iRowNumber, "tpReason", sDataSheetName))

		//Click on save 
		.clickSave() 

		//7 Verify CRM Account # is generated 
		.verifyCRMNumberIsDisplayed()

		//8.Account  Type = Member
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))

		//Class of Trade =Any
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))

		//Business Classification = Auto populated
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//Account Status = Auto Populated to Active
		.verifyDefaultAccountStatus()	

		//Store/Location type = Shipto
		.chooseLocationType(DataInputProvider.getCellData_ColName(iRowNumber, "locationType", sDataSheetName))	

		//CAMS Flag = Yes
		.changeCAMSFlagAsYes()

		//Direct Parent Relation = Managed
		.selectDirectParentRelationManaged() 

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		//DP exception reason = Any
		.typeDPReason(DataInputProvider.getCellData_ColName(iRowNumber, "dpReason", sDataSheetName))

		//Click on Save 
		.clickSave() 

		//9. Type street
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//Country =USA
		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))

		//Click on Save 
		.clickSave() 

		//10. Record Status = Published
		.chooseRecordStatusPublished()		

		//Click on Save 
		.clickSave() 
		.pageRefresh()
		
		//11. Verify Entity code is same as DP's entity code 
		.verifyEntityCode(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))
         
		
		
		//Verify Premier start date is auto populated
		.verifyPremierStartDateIsAutoPopulated()

		/*
		 * .verifyAgEffectiveDateIsNull() .verifyAffiliateGroupIsNull()
		 */

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
		//.verifySponsorLocked()
		;
	}

}
