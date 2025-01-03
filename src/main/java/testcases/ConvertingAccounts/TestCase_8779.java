package testcases.ConvertingAccounts;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8779:Cloud : Verify date validation triggers when Shipto accounts converted to Member


// ~~~~~~~~~~~~~8/23/2023 ~~Commenting Creation of 2 Member accounts and use existing  account to reduce time and implement Data Reset
public class TestCase_8779 {

	@Test
	public void ConvertAccount(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException {
		
		//1. Login to CRM as Member Supervisor
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		.selectDirectParentRelationDate("1/1/2000")
		
		.clickSave()
		
		// try converting it to a member account
		.chooseRecordStatusDraft().chooseLocationTypewithOutVerifying("--Select--").clickSave()

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
		
		// 8. Record Status = Published
		.chooseRecordStatusPublished()

		// Click on Save
		.clickSave()
		
		.verifyDateValidationError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))

		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))
		
		// Click on Save
		.clickSave()
		
		//Data Reset~~~~~~~~~~~~~~~
		
		.chooseLocationType("Ship To")
		
		.selectDirectParentRelationDate("3/7/2014")				
						
		.chooseRecordStatusDraft()
		
		// Click on Save
		.clickSave()	
		
		.chooseRecordStatusPublished().clickSave()
	
//
//		// 1. Login to CRM using member supervisor / member credentials
//		new LoginPage()
//
//				.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName)).clickNext()
//				.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
//				.clicSignin()
//
//				.clicYesInStaySignedin()
//
//				// 2. From the left navigation column ,Go to Accounts > +New
//				.selectAccountsTab()
//
//				.clickNewOnAccountsPage().chooseMemberForm()
//
//				// 3. Account Name = Any
//				.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
//
//				// Click on save
//				.clickSave()
//
//				// 4. Verify CRM Account # is generated
//				.verifyCRMNumberIsDisplayed()
//
//				// 5. Account Type = Member
//				.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))
//
//				// Class of Trade =Any
//				.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))
//
//				// Business Classification = Auto populated
//				.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber,
//						"verifyBusinessClassification", sDataSheetName))
//
//				// Account Status = Auto Populated to Active
//				.verifyDefaultAccountStatus()
//
//				// Application Start Date = Today's Date
//				.chooseApplicationDate(
//						DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate", sDataSheetName))
//
//				// CAMS Flag = Yes
//				.changeCAMSFlagAsYes()
//
//				// Participation Type = Standard
//				.selectParticipationType(
//						DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))
//
//				// Store/Location type = Shipto
//				.chooseLocationType(DataInputProvider.getCellData_ColName(iRowNumber, "locationType", sDataSheetName))
//
//				// Direct Parent Entity Code = 673415
//				.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))
//
//				// Direct Parent Relation = Managed
//				.selectDirectParentRelationManaged()
//
//				// Direct Parent Relation date = Today's Date
//				.selectDirectParentRelationDate(
//						DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))
//
//				// Top Parent Relation = OLM
//				.selectTopParentRelation(
//						DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))
//
//				// Top Parent Relation Date = Today's Date
//				.selectTopParentRelationDate(
//						DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))
//
//				// Click on Save
//				.clickSave()
//
//				// 6. Street 1 = Any
//				.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))
//
//				// City = NY
//				.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))
//
//				// Country =USA
//				.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))
//
//				// Type Zip code
//				.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))
//
//				// Click on Save
//				.clickSave()
//
//				// 8. Record Status = Published
//				.chooseRecordStatusPublished()
//
//				// Click on Save
//				.clickSave()
//
//				// 9. Verify Entity code is generated
//				.entityCodeIsDisplayed()
//
//				// Application Start Date
//				.chooseApplicationDate(
//						DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate2", sDataSheetName))
		
//				.clickLineOfBusinesses()
//
//				// Click New Line Of Business
//				.clickAddNewLineOfBusiness()
//
//				// Line of Business =General GPO
//				.selectLineOfBusiness(
//						DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusiness2", sDataSheetName))
//
//				// Classification Type = General GPO
//				.selectLOBfClassificationType(
//						DataInputProvider.getCellData_ColName(iRowNumber, "lineOfClassification2", sDataSheetName))
//
//				// Start Date =Today's date
//				.typeLineOfBusinessStartDate(
//						DataInputProvider.getCellData_ColName(iRowNumber, "lineOfBusinessStartDate2", sDataSheetName))
//
//				// Click on LOB Save
//				.clickLOBSaveAndClose()
				
//				.verifyAppDateValidationErrorMsg(
//				DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName),
//				DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate", sDataSheetName))

		

		;
	}
}
