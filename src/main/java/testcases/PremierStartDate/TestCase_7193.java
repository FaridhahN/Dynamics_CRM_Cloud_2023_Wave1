package testcases.PremierStartDate;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 7193:Verify and Validate "Premier Start Date" Logic based on Multiple GPO Membership records.

public class TestCase_7193 {

	  	
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
		
		//3.  Fill in All Mandatory Fields .
			
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
		 	 
			 //Street 1 = Any
			 .typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))
		
			 //City = NY
			.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))
			
			 //Country =USA
			.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))
			
			//Type Zip code
			.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))
			
		//4.Observe Premier Start date Field  in a Member Form
			.verifyPremierStartDateIsLocked()
			
			
		//5.Click On Save ,Observe Premier Start date.

			//Click on Save 
			.clickSave() 
			
			.verifyPremierStartDateIsNull()
			
		//6.Click on Publish without adding any one Premier Membership ,user should get Error Message like "Account doesn't have Premier Membership"
			.chooseRecordStatusPublished()
			
			//Click on Save 
			.clickSave() 
			
			//"Member should have at least one Active Premier Membership " Error message should be displayed
			.verifyAccountDoesNotHaveMPError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))
			
			//Change record status to Draft
			.chooseRecordStatusDraft()
			
		//7. Try to Add one Premier Membership .       
			.clickMembershipAndAddNewMembership()
			
			//Click and Choose option list  :Membership Type - Premier,  
		 	.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
			
		 	//Membership Provider -Acurity
		 	.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
			
			//Start date-1/1/2021 .
			.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))
			
			//Click on membership save and close
			.clickQuickCreateMembershipSaveAndClose()
			
		// Click the + icon on the Line of Business Grid
			.clickLineOfBusinesses()
			
			//Click New Line Of Business
			.clickAddNewLineOfBusiness()
		
			// Line of Business =General GPO
			.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))
			
			// Classification Type = General GPO
			.selectLOBfClassificationType(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification", sDataSheetName))
			
			// Start Date =Today's date
			.typeLineOfBusinessStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))
			
			// Click on LOB Save 
			.clickLOBSaveAndClose()
		
		//8. Click on Publish ,after adding one Premier Membership.
			.chooseRecordStatusPublished()
			
			//Click on Save 
			.clickSave() 
		
			//Verify Entity code is generated 
			.entityCodeIsDisplayed()
		
		//9.Observe Premier Start Date after Publish.
			.verifyPremierStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))
			
			//Move record status to draft from published
			.chooseRecordStatusDraft()
			
			//Click on Save 
			.clickSave() 
			
		//10.Add One or More Premier Membership. Observe Premier Start date. 			
		//Membership Provider -2	
			.clickMembershipAndAddNewMembership()
			
			//Click and Choose option list  :Membership Type - Premier,  
		 	.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
			
		 	//Membership Provider -Advantage Health Partners
		 	.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider2", sDataSheetName))
			
			//Start date-12/31/2020
			.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate2", sDataSheetName))
			
			//Click on membership save and close
			.clickQuickCreateMembershipSaveAndClose()
			
		//Membership Provider -3	
			.clickMembershipAndAddNewMembership()
			//Click and Choose option list  :Membership Type - Premier,  
		 	.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
			
		 	//Membership Provider -National
		 	.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider3", sDataSheetName))
			
			//Start date-12/15/2020
			.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate3", sDataSheetName))
			
			//Click on membership save and close
			.clickQuickCreateMembershipSaveAndClose()
			
		//11.Try to add one more Membership Entity with Future date i.e.1/14/2021 .			
		//Membership Provider -4	
			.clickMembershipAndAddNewMembership()
			//Click and Choose option list  :Membership Type - Premier,  
		 	.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
			
		 	//Membership Provider -Intersectta
		 	.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider4", sDataSheetName))
			
			//Start date-1/14/2021
			.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate4", sDataSheetName))
			
			//Click on membership save and close
			.clickQuickCreateMembershipSaveAndClose()
			
		
		//Observe Premier Start date Field.
			.pageRefresh()
			.verifyPremierStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate3", sDataSheetName))

		//12.  Try to add one more Membership Entity with older date i.e.12/14/2020 			
		//Membership Provider -5	
			.clickMembershipAndAddNewMembership()
			//Click and Choose option list  :Membership Type - Premier,  
		 	.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
			
		 	//Membership Provider -PremierChoice
		 	.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider5", sDataSheetName))
			
			//Start date-12/14/2020
			.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate5", sDataSheetName))
			
			//Click on membership save and close
			.clickQuickCreateMembershipSaveAndClose()
			
			//Click on Publish ,after adding one Premier Membership.
			.chooseRecordStatusPublished()
			
			//Click on Save 
			.clickSave() 
			
			//Premier/National and Premier Choice Memberships cannot be active in the same account 
			.verifyAccountDoesNotHaveMPError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage1", sDataSheetName))
		;
	}
}
