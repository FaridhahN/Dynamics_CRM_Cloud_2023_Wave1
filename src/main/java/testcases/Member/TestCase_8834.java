package testcases.Member;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8834:Cloud: Verify reactivating a terminated member

public class TestCase_8834 {


	@Test
	public void reactivateTerminatedMember(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Accounts and click on New button **** New account form should be displayed 
		.selectAccountsTab()
		.clickNewOnAccountsPage()
		.chooseMemberForm()

		//3.Provide the below values in the form and save **** Account should be saved and CRM# should be captured successfully
		// Account  Type = Member
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))

		//Account name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		
		//Class of Trade =Any
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))

		//Country =USA
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))
				
		//Business Classification = Auto populated
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//Account Status = Auto Populated to Active
		.verifyDefaultAccountStatus()	

		//Application Start Date = Today's Date
		.chooseApplicationStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate", sDataSheetName))

		//Participation Type = Standard
		.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))

		//Direct Parent CRM number = 2000092265/822660
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//Direct Parent Relation = Managed
		.selectDirectParentRelationManaged()

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		//4.Add the below address details ***** Account should be saved successfully
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//Country =USA
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))
		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		//Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))
	

		// Click on Save 
		.clickSave() 


		//5.Go to Membership entity and add ***** Premier GPO membership should be added successfully 						 	
		//Click add new membership
		.clickMembershipAndAddNewMembership()

		// Choose Membership type 
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		//Provide any start date and click on save
		.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

		//Click on membership save and close
		.clickQuickCreateMembershipSaveAndClose()			

		//6.Click the + icon on the Line of Business Grid ***** Corresponding LOB should be added successfully 
		.clickLineOfBusiness()

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

		//Click on Save 
		.clickSave() 


		//7.Verify Premier start date is displayed 	*****Premier start date should be displayed in the form
		.verifyPremierStartDateIsAutoPopulated()

		//8.Move the record status to Published and save *****  Account should be published successfully, EIN number should be captured successfully
		.chooseRecordStatusPublished()	

		//Click on Save 
		.clickSave() 

		//Account should be published successfully, EIN number should be captured successfully
		.entityCodeIsDisplayed()

		//Verify Premier start date is auto populated
		.verifyPremierStartDateIsAutoPopulated()

		.verifyAffiliateGroupIsNotNull()
		.verifyAgEffectiveDateIsNotNull()

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

		//12.Verify the account status  ***** Account status should become terminated 
		.pageRefresh()
		.verifyAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "accountStatus", sDataSheetName))

		//13.Verify Premier end date **** Premier end date should be populated with the same date  as National membership end date 
		.verifyPremierEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))

		//14.Verify the AG and AG effective date **** AG should become NON Premier.AG effective date should be Premier end date 
		.verifyAffiliateGroup(DataInputProvider.getCellData_ColName(iRowNumber, "affiliateGroup", sDataSheetName))
		.verifyAgEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))

		//15. Now move the record status to Published and save **** Account should be published successfully 
		.chooseRecordStatusPublished()	

		//Click on Save 
		.clickSave() 

		//16.For reactivation, move the record status to draft *** Record should be moved to draft 
		.chooseRecordStatusDraft()

		//Click on Save 
		.clickSave()

		//17.Go to membership and add New membership **** Account should be saved successfully 
		//Add Membership provider
		//Click add new membership
		.clickMembershipAndAddNewMembership()

		// Choose Membership type 
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider2", sDataSheetName))

		//Provide any start date and click on save
		.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate2", sDataSheetName))

		//Click on membership save and close
		.clickQuickCreateMembershipSaveAndClose()			

		//18.Now add corresponding LOB **** LOB should be added successfully 
		.clickLineOfBusiness()

		//Click New Line Of Business
		.clickAddNewLineOfBusiness()

		// Line of Business =General GPO
		.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness2", sDataSheetName))

		// Classification Type = General GPO
		.selectLOBfClassificationType(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification2", sDataSheetName))

		// Start Date =Today's date
		.typeLineOfBusinessStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusinessStartDate2", sDataSheetName))

		// Click on LOB Save 
		.clickLOBSaveAndClose()


		//25.Move the record status to Published ***** Account should be published and reactivated successfully 
		.chooseRecordStatusPublished()

		//Click on Save 
		.clickSave() 

		//19.Verify the Account status ***** Account status should be Active 
		
		//.verifyDefaultAccountStatus()
		.verifyAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "accountStatus1", sDataSheetName))

		//20.Verify the Premier start date **** Premier start date should be Acurity's start date 
		.pageRefresh()
		.verifyPremierStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate2", sDataSheetName))

		//21.Verify Premier end date ***** Premier end date should be blank 
		.pageRefresh()
		.verifyPremierEndDateIsNull()

		//22.Verify DPRD ***** DPRD should be same as new Premier start date 
		.verifyDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate2", sDataSheetName))
		//23.Verify TPRD ****TPRD should be same as New Premier start date 
		.verifyTopParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate2", sDataSheetName))

		//24.Verify AG and AG effective date  ***** AG should not be NON Premier.AG effective date should be updated
		.verifyAffiliateGroup(DataInputProvider.getCellData_ColName(iRowNumber, "affiliateGroupAfterReactivation", sDataSheetName))
		.verifyAgEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate2", sDataSheetName))		


		;
	}
}
