package testcases.MemberAttribute;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 3290:Verify a member cannot have a General GPO Rebate rule if the member higher up has General GPO “Check Parent Do not Override” rebate rule

public class TestCase_3291 {

	@Test
	public void verifyGPORebate(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
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
			 
			//Top parent relation date = Today's date
			 .selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))
			 
			//Click on save
			.clickSave() 
				
		//4. Verify CRM Account # is generated 
			.verifyCRMNumberIsDisplayed()				 
			
		//5. Account  Type = Member
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
			
			//Direct Parent Relation = Owned(Auto populated)
			.verifyDirectParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "verifyDirectParentRelation", sDataSheetName))
			
			//Region = Any
			.selectRegion(DataInputProvider.getCellData_ColName(iRowNumber, "region", sDataSheetName))
			
			//Top parent classification = Any
			.selectTopParentClassification(DataInputProvider.getCellData_ColName(iRowNumber, "topParentClassification", sDataSheetName))
			
			//Click on Save
			.clickSave() 
			
		//6. Street 1 = Any
			 .typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))
				
			 //City = NY
			.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))
			
			 //Country =USA
			.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))
			
			//Type Zip code
			.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))
			
			 //Click on Save 
			.clickSave() 
		
			
		//7. Refresh the page to unlock the Fee share eligible field 
			.pageRefresh()
			
		//8. Fee share eligible = Yes
			.changeFeeShareEligibleToYes()
			
			//Fee share eligible date = Today's date
			.selectFeeShareEligibleDate(DataInputProvider.getCellData_ColName(iRowNumber, "feeShareEligibleStartDate", sDataSheetName))
		
			//FBO Type = Any
			.selectFBOGPOType(DataInputProvider.getCellData_ColName(iRowNumber, "FBOType", sDataSheetName))
			
			//FBO effective date = Today's date
			.selectFBOEffectiveDate(DataInputProvider.getCellData_ColName(iRowNumber, "FBOEffectiveDate", sDataSheetName))
			
			//Click on Save 
			.clickSave() 
		
		//9.  Click the + icon on the Line of Business Grid
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
			
		//10. Record Status = Published
			.chooseRecordStatusPublished()
			
			//Click on Save 
			.clickSave() 
			
		//11. Verify Entity code is generated 
			.entityCodeIsDisplayed()
			.getTopParentEntity()
			.createSubAccount(2)
			.selectRelatedMemberAttributesForLimMem()
			.clickNewAccountnumberMemberAttribute()
			.addNewAccountMemberAttribute(DataInputProvider.getCellData_ColName(iRowNumber, "AttributeType", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "AttributeValue", sDataSheetName))
			.clickSaveAndCloseButton()
			.createSubAccount(1)
			.selectRelatedMemberAttributesForLimMem()
			.clickNewAccountnumberMemberAttribute()
			.addNewAccountMemberAttribute(DataInputProvider.getCellData_ColName(iRowNumber, "AttributeType1", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "AttributeValue1", sDataSheetName))
			.verifyErrorMessage_contains_Sinlgequote("You can't create this record as there is a member higher up in the hierarchy that has a 'Check Parent Do Not Override' rule for the same LOB")
			.clickGoBackandDiscardChanges()
			.clickNewAccountnumberMemberAttribute()
			.addNewAccountMemberAttribute(DataInputProvider.getCellData_ColName(iRowNumber, "AttributeType2", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "AttributeValue2", sDataSheetName))
			;
			
	}	
}