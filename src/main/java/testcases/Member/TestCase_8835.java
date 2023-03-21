package testcases.Member;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MemberFormPage;
import utils.DataInputProvider;
//Test Case 8835:Cloud: Add and update Primary contact to a Member account

public class TestCase_8835 {

	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Accounts > +New 
		.selectAccountsTab()
		.clickNewOnAccountsPage()
		.chooseMemberForm()

		//Account  Type = Member
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))

		//Account name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Class of Trade =Any
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))

		//Business Classification = Auto populated
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//3. Fill in the mandatory fields below
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//Country =USA
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))	
		//Account Status = Auto Populated to Active
		.verifyDefaultAccountStatus()	
		//Primary contact = 7000331193
		.addMemberPrimaryContact(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))

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

		// Click on Save 
		.clickSave() 

		//4.Click the + icon on the Line of Business Grid
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

		//. Verify Entity code is generated 
		.entityCodeIsDisplayed()

		//Verify Premier start date is auto populated
		.verifyPremierStartDateIsAutoPopulated()

		.verifyAffiliateGroupIsNotNull()
		.verifyAgEffectiveDateIsNotNull()


		//5. Verify the Primary contact name displayed in the field
		.verifyPrimaryContactValue(DataInputProvider.getCellData_ColName(iRowNumber, "PrimaryContactName", sDataSheetName))

		//Record Status = Draft
		.chooseRecordStatusDraft()

		//Click on Save 
		.clickSave() 
		.entityCodeIsDisplayed()	

		//6. Click on the primary contact lookup and click on lookup for more records
		//7. Search the Contact ID - 7000133221 and click on Add
		.updateMemberPrimaryContact(DataInputProvider.getCellData_ColName(iRowNumber, "updatePrimaryContact", sDataSheetName))


		//Record Status = Published
		.chooseRecordStatusPublished()

		//Click on Save 
		.clickSave() 

		//8. Verify the newly updated Primary contact name is displayed in the form
		.verifyPrimaryContactValue(DataInputProvider.getCellData_ColName(iRowNumber, "verifyUpdatePrimaryContactName", sDataSheetName));


	}
}
