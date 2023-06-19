package testcases.Member;

import org.testng.annotations.Test;

import driver.Driver;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_2222:Create member hierarchy

public class TestCase_2222 {

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
		
		//Go to Workplace > Accounts and search for EIN 673415 
			.selectAccountsTab()
			.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		
		//Double click on the account and go to Sub accounts entity by clicking > on the top 
			.selectDirectParentFromSearchResults()
			.selectSubaccount()
			
		//Click on Add new account 
			.clickNewAccountInSubAccount()
		
		//Account Name = Any
			.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName)+"Child1")
		
		//Top Parent Relation =  OLM
		 	.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))
	
		//Top Parent Relation Date = Today's Date
		 	.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))
		 
		//type TP reason
		 	.typeTPReason(DataInputProvider.getCellData_ColName(iRowNumber, "tpReason", sDataSheetName))
	 
		//Click on save 
		 	.clickSave() 
			 		 
		// Verify CRM Account # is generated 
 		 	.verifyCRMNumberIsDisplayedForChilAccount(1)
		
		// Account  Type = Member
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
				
			//Direct Parent Relation = Managed
			.selectDirectParentRelationManaged() 
			
			//Direct Parent Relation date = Today's Date
			.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))
			
			//DP exception reason = Any
			.typeDPReason(DataInputProvider.getCellData_ColName(iRowNumber, "DPReason", sDataSheetName))
					 
			//Click on Save 
			 .clickSave() 

			//Street 1 = Any
			.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))
				
			 //City = NY
			.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))
			
			//Type Zip code
			.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))
			
			//Click on Save
			 .clickSave() 
				 
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
		 	
			 //Click add new membership
			.clickMembershipAndAddNewMembership()
			
			// Choose Membership type 
		 	.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
			.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
			
			//Provide any start date and click on save
			.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))
			
			//Click on membership save and close
			.clickQuickCreateMembershipSaveAndClose()

			//Record Status = Published
			.chooseRecordStatusPublished()
			
			//Click on Save 
			.clickSave() 
			
			//Verify Entity code is generated 
			.entityCodeIsDisplayed()
			
			//Verify Premier start date is auto populated
			.verifyPremierStartDateIsAutoPopulated()

			
			//Go to Workplace > Accounts and search for EIN 673415 
			.selectAccountss()
			.searchAccount(DataInputProvider.getCellData_ColName(Driver.iTestCaseRowNumDriver, "CRMNumberChildAccount1", "Driver"))
			
			//Double click on the account and go to Sub accounts entity by clicking > on the top 
			.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
			.selectSubaccount()
			
			//Click on Add new account 
			.clickNewAccountInSubAccount()
			
			//Account Name = Any
			.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName)+"Child account2")
			
			//Top Parent Relation =  OLM
			 .selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))
			
			//Top Parent Relation Date = Today's Date
			 .selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))
			 
			//type TP reason
			 .typeTPReason(DataInputProvider.getCellData_ColName(iRowNumber, "tpReason", sDataSheetName))
			 
			//Click on save 
			 .clickSave() 
				 		 
			//Verify CRM Account # is generated 
			 .verifyCRMNumberIsDisplayedForChilAccount(2)
			
			//Account  Type = Member
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
			
			//Direct Parent Relation = Managed
			.selectDirectParentRelationManaged() 
			
			//Direct Parent Relation date = Today's Date
			.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))
			
			//DP exception reason = Any
			.typeDPReason(DataInputProvider.getCellData_ColName(iRowNumber, "DPReason", sDataSheetName))
				 
			//Click on Save 
			 .clickSave() 
			
			//Street 1 = Any
			.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))
			
			 //City = NY
			.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))
			
			//Type Zip code
			.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zippCode", sDataSheetName))
			
			//Click on Save
			 .clickSave() 
				 
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
			 	
			 //Click add new membership
			.clickMembershipAndAddNewMembership()
			
			// Choose Membership type 
			.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
			.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
			
			//Provide any start date and click on save
			.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))
			
			//Click on membership save and close
			.clickQuickCreateMembershipSaveAndClose()
			
			//Record Status = Published
			.chooseRecordStatusPublished()
			
			//Click on Save 
			.clickSave() 
			
			//Verify Entity code is generated 
			.entityCodeIsDisplayed()
			
			//Verify Premier start date is auto populated
			.verifyPremierStartDateIsAutoPopulated();
			

			
			
			
//			.verifyAffiliateGroupIsNotNull()
//			.verifyAgEffectiveDateIsNotNull()
			
//		//13. Verify "IS Corporate account" field
//			.verifyIsCorporateAccount(DataInputProvider.getCellData_ColName(i, "verifyIsCorporateAccount", sDataSheetName))
//		
//		//14. Verify Corporate parent name in the form
//			.verifyCorporateParentName(DataInputProvider.getCellData_ColName(i, "verifyDirectParent", sDataSheetName))
//		
//		//15. Verify "Is Food Service parent" field 
//			.verifyIsFoodServiceParent(DataInputProvider.getCellData_ColName(i, "verifyIsFoodServiceParent", sDataSheetName))
//		
//		//16 Verify Food Service parent name in the form 
//			.verifyFoodServiceParentName(DataInputProvider.getCellData_ColName(i, "verifyDirectParent", sDataSheetName))
//		
//		//17 Verify Sponsor field 
//			.verifySponsor(DataInputProvider.getCellData_ColName(i, "verifyDirectParent", sDataSheetName))
//		
//		//16 Verify "Is Sponsor" field 
//		.verifyIsSponsor(DataInputProvider.getCellData_ColName(i, "verifyIsSponsor", sDataSheetName))
//		
//			//FBO details verification
//			//Verify "Is FBO" field 
//			.verifyIsFBO(DataInputProvider.getCellData_ColName(i, "verifyIsFBO", sDataSheetName))
//			
//			//FBO
//			.verifyFBO(DataInputProvider.getCellData_ColName(i, "verifyDirectParent", sDataSheetName))
//					
//			//FBORD
//			.verifyFBORD(DataInputProvider.getCellData_ColName(i, "verifyFBORD", sDataSheetName))
//		
//		
					
			
	}

}
