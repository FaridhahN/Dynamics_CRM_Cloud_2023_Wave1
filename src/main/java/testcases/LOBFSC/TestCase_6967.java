package testcases.LOBFSC;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 6967:Verify Food Services LOB is NOT added automatically when LOB is not General GPO and Location Type Account is converted to Member

public class TestCase_6967 {

	@Test
	public void VerifyFoodServiceLOB(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

				//Store/Location type = Shipto
				.chooseLocationType(DataInputProvider.getCellData_ColName(iRowNumber, "locationType", sDataSheetName))	

				//Direct Parent Entity Code = 673415
				.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

				//Direct Parent Relation = Managed
				.selectDirectParentRelationManaged() 

				//Direct Parent Relation date = Today's Date
				.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

				//Top Parent Relation =  OLM
				.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

				// Top Parent Relation Date = Today's Date
				.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

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

				//Click add new membership
				.clickMembershipAndAddNewMembership()

				// Choose Membership type 
				.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
				.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

				//Provide any start date and click on save
				.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

				//Click on membership save and close
				.clickQuickCreateMembershipSaveAndClose()

				//8. Record Status = Published
				.chooseRecordStatusPublished()

				//Click on Save 
				.clickSave() 

				//9. Verify Entity code is generated 
				.entityCodeIsDisplayed()

				//Store/Location type = Blank
				.chooseLocationTypewithOutVerifying("---")	
				
				//8. Record Status = Published
				.chooseRecordStatusDraft()

				//Click on Save 
				.clickSave() 

				//Click add new membership
				.clickMembershipAndAddNewMembership()

				// Choose Membership type 
				.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
				.selectMembershipProvider("National")

				//Provide any start date and click on save
				.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

				//Click on membership save and close
				.clickQuickCreateMembershipSaveAndClose()

				//7.  Click the + icon on the Line of Business Grid
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

				//8. Record Status = Published
				.chooseRecordStatusPublished()

				//Click on Save 
				.clickSave() 


				.clickLineOfBusinesses()

				.verifyFoodServiceLOBisNotDisplayed()


				.clickNewOnAccountsPage()

				.chooseMemberForm()

				//3. Account Name = Any
				.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName)+1)

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
				
				//Store/Location type = Shipto
				.chooseLocationType(DataInputProvider.getCellData_ColName(iRowNumber, "locationType", sDataSheetName))	

				//Participation Type = Standard
				.selectParticipationType("Cellular Services Only")


				//Direct Parent Entity Code = 673415
				.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

				//Direct Parent Relation = Managed
				.selectDirectParentRelationManaged() 

				//Direct Parent Relation date = Today's Date
				.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

				//Top Parent Relation =  OLM
				.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

				// Top Parent Relation Date = Today's Date
				.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

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

				//Click add new membership
				.clickMembershipAndAddNewMembership()

				// Choose Membership type 
				.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
				.selectMembershipProvider("Non-GPO")

				//Provide any start date and click on save
				.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

				//Click on membership save and close
				.clickQuickCreateMembershipSaveAndClose()


				//8. Record Status = Published
				.chooseRecordStatusPublished()

				//Click on Save 
				.clickSave() 

				//Store/Location type = Blank
				.chooseLocationTypewithOutVerifying("---")	
				
				//8. Record Status = Published
				.chooseRecordStatusDraft()

				//Click on Save 
				.clickSave() 

				//Click add new membership
				.clickMembershipAndAddNewMembership()

				// Choose Membership type 
				.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
				.selectMembershipProvider("National")

				//Provide any start date and click on save
				.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

				//Click on membership save and close
				.clickQuickCreateMembershipSaveAndClose()

				//7.  Click the + icon on the Line of Business Grid
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

				//8. Record Status = Published
				.chooseRecordStatusPublished()

				//Click on Save 
				.clickSave() 

				//7.  Click the + icon on the Line of Business Grid
				.clickLineOfBusinesses()

				.verifyFoodServiceLOBisNotDisplayed()
		;

	}

}
