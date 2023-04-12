package testcases.PremierStartDate;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 7214:Verify on updating "Premier Start Date" in an existing "Premier Memberships", Validate in Member form re calculation of Premier Start Date is getting triggered.

public class TestCase_7214 {


	@Test
	public void premierstartdateverification(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		//Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))


		//4.Add the below address details ***** Account should be saved successfully
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//Country =USA
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))

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

		//9.Move the record status to draft and save  ***** Record moved to draft 
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
		.clickLineOfBusinesses()

		//Click New Line Of Business
		.clickAddNewLineOfBusiness()

		// Line of Business =General GPO
		.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness2", sDataSheetName))

		// Classification Type = General GPO
		.selectLOBfClassificationTypeAcurity(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification2", sDataSheetName))

		// Start Date =Today's date
		.typeLineOfBusinessStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusinessStartDate2", sDataSheetName))

		// Click on LOB Save 
		.clickLOBSaveAndClose()


		//25.Move the record status to Published ***** Account should be published and reactivated successfully 
		.chooseRecordStatusPublished()

		//Click on Save 
		.clickSave() 


		
		  // Click on the Ribbon bar of the Published Account. Go to Membership Entity.
		 .goToMembershipPage(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
		
		 //Start date-12/31/2020 
		 .typeMembershipStartDate("5/5/2021")
		  
		  //Click on membership save and close
		  .clickSaveAndCloseOnActivity()
		  
		  //Verify
		  .verifyPremierStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate2", sDataSheetName))
		 
		;
	}
}
