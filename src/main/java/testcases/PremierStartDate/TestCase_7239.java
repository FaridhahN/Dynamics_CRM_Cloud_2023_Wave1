package testcases.PremierStartDate;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 7239:Verify whether "Premier Start Date" field should be Read Only in Member Entry Forms.


public class TestCase_7239 {


	@Test
	public void premierstartdatelocked(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		.chooseMemberEntryForm()
			.clickAdditionalCriteria()
		.verifyPremierStartDateIsLocked()
		//3.  Fill in All Mandatory Fields .
		.clickgeneralDemographic()

		//3. Account  Type = Member
				.selectAccountTypeMEF(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))
					
					//Account name = Any
					.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
					
					//Participation Type = Standard
					.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))
					
					//Class of Trade =Any
					.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))
					
					//Business Classification = Auto populated
					.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))
					
					//Account Status = Auto Populated to Active
					.verifyDefaultAccountStatus()
					
					//Direct Parent Entity Code = 673415
					.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))
					
					//Direct Parent Relation = Managed
					.selectDirectParentRelationManaged()
					
					//Direct Parent Relation date = Today's Date
					.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))
					
					//Top Parent Relation =  OLM
					.selectTopParentRelationMEF(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))
					 
					//Top Parent Relation Date = Today's Date
					 .selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))
					 
					 // Click on Save 
					// .clickSave() 
						
				//4. Street 1 = Any
					.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))
								
					 //City = NY
					.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))
					
					 //Country =USA
					.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))
					
					//Type Zip code
					.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))
					
					//Application Start Date = Today's Date
					 .chooseApplicationDateMEF(DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate", sDataSheetName))
			
					//Click on Save 
					.clickSave() 

				//5.  Click the + icon on the Line of Business Grid
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
					.clickLOBSaveAndCloseMEF()
				 	
				 	//Click add new membership
					.clickMembershipAndAddNewMembership()
					
					// Choose Membership type 
				 	.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
					.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
					
					//Provide any start date and click on save
					.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))
					
					//Click on membership save and close
					.clickMembershipSaveAndCloseMEF()
				
				//6. Record Status = Published
					.chooseRecordStatusPublishedMEF()
					
					//Click on Save 
					.clickSave() 
					
				//7. Verify Entity code is generated 
					.entityCodeIsDisplayed()
					
				//Verify Premier start date is auto populated
					.verifyPremierStartDateIsAutoPopulated()

		//4.Observe Premier Start date Field  in a Member Form
		.verifyPremierStartDateIsLocked()

		;
	}
}
