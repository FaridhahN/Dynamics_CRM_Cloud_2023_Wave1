package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 7277:Verify member account can not be published without one active Premier membership created

public class TestCase_7277 {

	  	
	@Test
	public void createMember(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
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
		
		//3. Fill in all the mandatory fields in the form ,Do Not add any Premier Memberships, Keep Record Status= Draft and save 
			
			//Account Name = Any
			.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
	
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
			 
			 
			 //Street 1 = Any
			 .typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))
		
			 //City = NY
			.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))
			
			 //Country =USA
			.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))
			
			//Type Zip code
			.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))
			
			 //Click on Save 
			.clickSave() 
			
		//4.Change the Record Status =Published and Save
			.chooseRecordStatusPublished()
			
			//Click on Save 
			.clickSave() 
			
			//"Member should have at least one Active Premier Membership " Error message should be displayed
			.verifyAccountDoesNotHaveMPError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
			
			//Change record status to Draft
			.chooseRecordStatusDraft()
			
		//6.  Go to Line of Business Entity and Add Line of Business with respect to the Premier Membership added in the Step above
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
			
			
		//5. Now go to Membership Entity and add any Premier Membership and save
			.clickMembershipAndAddNewMembership()
			
			// Choose Membership type 
		 	.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
			.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
			
			//Provide any start date and click on save
			.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))
			
			//Click on membership save and close
			.clickQuickCreateMembershipSaveAndClose()

		//7. Now Change the Record Status =Published and Save
			.chooseRecordStatusPublished()
			
			//Click on Save 
			.clickSave() 
		
			//Verify Entity code is generated 
			.entityCodeIsDisplayed()
		
			//Verify Premier start date is auto populated
			.verifyPremierStartDateIsAutoPopulated()
			
			.verifyAffiliateGroupIsNotNull()
			.verifyAgEffectiveDateIsNotNull()
		
		;
	}
}
