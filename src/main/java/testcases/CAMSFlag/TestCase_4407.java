package testcases.CAMSFlag;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 4539:Verify CAMS rule works when changing the DP of a member who has incorrect CAMS - Old TC Name
//TFS ID_4407:_781800_Automation Direct Parent and the child member account should have matching CAMS flag values -When Prospect to Member conversion - New TC Name

public class TestCase_4407 {

	@Test
	public void VerifyCAMSOfDP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {

		// 1. Login to CRM using member supervisor / member credentials
		new LoginPage().typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
				.clickNext().typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
				.clicSignin().clicYesInStaySignedin()

				// 2. From the left navigation column ,Go to Accounts > +New
				.selectAccountsTab()

				.clickNewOnAccountsPage().chooseMemberForm()

				// 3. Account Name = Any
				.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

				// Click on save
				.clickSave()

				// 4. Verify CRM Account # is generated
				.verifyCRMNumberIsDisplayed()

				// 5. Account Type = Member
				.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))

				// Class of Trade =Any
				.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))

				// Business Classification = Auto populated
				.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber,
						"verifyBusinessClassification", sDataSheetName))

				// Account Status = Auto Populated to Active
				.verifyDefaultAccountStatus()

				// Application Start Date = Today's Date
				.chooseApplicationDate(
						DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate", sDataSheetName))

				// CAMS Flag = No
				.changeCAMSFlagAsNo()

				// Participation Type = Standard
				.selectParticipationType(
						DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))

				// Direct Parent Entity Code = 673415
				.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))
				// .selectDirectParent("623335")
				// Direct Parent Relation = Managed
				.selectDirectParentRelationManaged()

				// Direct Parent Relation date = Today's Date
				.selectDirectParentRelationDate(
						DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

				// Top Parent Relation = OLM
				// .selectTopParentRelation(
				// DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation",
				// sDataSheetName))
				.selectTopParentRelation("OLM")
				// Top Parent Relation Date = Today's Date
				.selectTopParentRelationDate(
						DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

				// Click on Save
				.clickSave()

				// 6. Street 1 = Any
				.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

				// City = NY
				.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

				// Country =USA
				.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

				// Type Zip code
				.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))

				// Click on Save
				.clickSave()

				// Click add new membership
				.clickMembershipAndAddNewMembership()

				// Choose Membership type
				.selectMembershipType(
						DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
				.selectMembershipProvider(
						DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

				// Provide any start date and click on save
				.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber,
						"membershipProviderStartDate", sDataSheetName))

				// Click on membership save and close
				.clickQuickCreateMembershipSaveAndClose()

				// 7. Click the + icon on the Line of Business Grid
				.clickLineOfBusinesses()

				// Click New Line Of Business
				.clickAddNewLineOfBusiness()

				// Line of Business =General GPO
				.selectLineOfBusiness(
						DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

				// Classification Type = General GPO
				.selectLOBfClassificationType(
						DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification", sDataSheetName))

				// Start Date =Today's date
				.typeLineOfBusinessStartDate(
						DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusinessStartDate", sDataSheetName))

				// Click on LOB Save
				.clickLOBSaveAndClose()

				// Click on Save
				.clickSave()

				// CAMS Flag = No
				.changeCAMSFlagAsNo()

				// Change the Record status as Published
				.chooseRecordStatusPublished()

				// Click Save
				.clickSave()

				// Verify entity code
				.entityCodeIsDisplayed()

				.selectSubaccount().clickNewAccountInSubAccount()
				// .chooseMemberForm()

				.chooseMemberEntryForm()

				// 6. Account Name = Any
				.typeAccountName("SubAccountwithCAMSyes")

				// Top Parent Relation = OLM
				.selectTopParentRelationMEF(
						DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

				// Top Parent Relation Date = Today's Date
				.selectTopParentRelationDate(
						DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

				// type TP reason
				.typeTPReason(DataInputProvider.getCellData_ColName(iRowNumber, "TPReason", sDataSheetName))

				// Click on save
				.clickSave()

				// 7. Verify CRM Account # is generated
				.verifyCRMNumberIsDisplayed()

				// 8 Account Type = Member
				.selectAccountTypeMEF("Prospect")

				// Class of Trade =Any
				.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))

				// Business Classification = Auto populated
				.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber,
						"verifyBusinessClassification", sDataSheetName))

				// Account Status = Auto Populated to Active
				.verifyDefaultAccountStatus()

				// Participation Type = Standard
				.selectParticipationType(
						DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))

				// Direct Parent Relation = Managed
				.selectDirectParentRelationManaged()

				// Direct Parent Relation date = Today's Date
				.selectDirectParentRelationDate(
						DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

				// DP exception reason = Any
				.typeDPReason(DataInputProvider.getCellData_ColName(iRowNumber, "DPReason", sDataSheetName))

				// Click on Save
				// .clickSave()

				// 9. Street 1 = Any
				.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

				// City = NY
				.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

				// Country =USA
				.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

				// Type Zip code
				.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))

				// Application Start Date = Today's Date
				.chooseApplicationDateMEF(
						DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate", sDataSheetName))

				// CAMS Flag = Yes
				.changeCAMSFlagAsYes()

				// Click on Save
				.clickSave()

				// 10. Click the + icon on the Line of Business Grid
				.clickLineOfBusinesses()

				// Click New Line Of Business
				.clickAddNewLineOfBusiness()

				// Line of Business =General GPO
				.selectLineOfBusiness(
						DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness", sDataSheetName))

				// Classification Type = General GPO
				.selectLOBfClassificationType(
						DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification", sDataSheetName))

				// Start Date =Today's date
				.typeLineOfBusinessStartDate(
						DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusinessStartDate", sDataSheetName))

				// Click on LOB Save
				.clickLOBSaveAndCloseMEF()

				// Click add new membership
				.clickMembershipAndAddNewMembership()

				// Choose Membership type
				.selectMembershipType(
						DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
				.selectMembershipProvider(
						DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

				// Provide any start date and click on save
				.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber,
						"membershipProviderStartDate", sDataSheetName))

				// Click on membership save and close
				.clickMembershipSaveAndCloseMEF()

				// Click on Save
				.clickSave()

				.clickgeneralDemographic()
				// 8 Account Type = Member
				.selectAccountTypeMEF("Member")
				// 11. Record Status = Published
				.chooseRecordStatusPublishedMEF()

				// Click on Save
				.clickSave()

				// .verifyErrorMessage(
				// "Member's or Non-GPO Member's CAMS Flag cannot be checked if it's Parent's
				// CAMS Flag is unchecked");
				.verifyCAMSerrorMsg();

	}

}
