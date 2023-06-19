package testcases.Member;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8190:Verify whether "LOB" needs to be "End Dated", on End dating corresponding Premier Membership.

public class TestCase_8190 {

	  	
	@Test
	public void reactivateTerminatedMember(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
			.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
			.clickNext()
	  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
	  	    .clicSignin()
	  	    .clicYesInStaySignedin()
		
		//2. Go to Accounts and identify any account with multiple active GPO memberships
	  	    .selectAccountsTab()
			.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
			.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
			
//			//.Move the record status to draft then save
//			.chooseRecordStatusDraft()
//			
//			//Click on Save 
//			.clickSave() 
			
		//3  Click on the Ribbon bar of the Published Account. Go to Membership Entity.  
			.goToMembershipPage(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
			
		//4. On Membership Entity, Verify by End dating any Premier Membership's in the taken Published Account
			//Any future date
			.typeMembershipEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))
			
			// End reason = Anything from dropdown,
			.selectMembershipEndReason(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndReason", sDataSheetName))
			
			// then save
			.clickMembershipSaveAndClose()
			
		//5.Verify on End dating Premier Membership, Corresponding LOB should also get end dated
			
			
			
			
		//	~~~~~~~~~~~~~~~~~~~~~~~~~~~Data reset~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			//Delete end dated membership provider			
			.goToMembershipPage(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
			
			.clickDeleteMembershipProvider()		
				 
		//Add national membership provider						 	
			 //Click add new membership
				.clickMembershipAndAddNewMembership()
				
				// Choose Membership type 
			 	.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
				.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
				
				//Provide any start date and click on save
				.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))
				
				//Click on membership save and close
				.clickQuickCreateMembershipSaveAndClose()			
			
		//Click the + icon on the Line of Business Grid ***** Corresponding LOB should be added successfully 
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
				
			//Click on Save 
			 .clickSave() 
				
		//8.Move the record status to Published and save *****  Account should be published successfully, EIN number should be captured successfully
			.chooseRecordStatusPublished()	
			
			//Click on Save 
			.clickSave() 
					
			//Account should be published successfully, EIN number should be captured successfully
			.entityCodeIsDisplayed()
			
		;
	}
}
