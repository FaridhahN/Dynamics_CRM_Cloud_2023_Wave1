package testcases.Member;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8797:Cloud - Verify whether able to Create Account Name with Length of 160 chars for Member/Non GPO without any Error Message.

public class TestCase_8797 {


	@Test
	public void VerifyAccountName(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {


		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email",
				sDataSheetName)) .clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password",
				sDataSheetName)) .clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()

		.clickNewOnAccountsPage() 
		.chooseMemberForm()

		//3. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber,
				"accountName", sDataSheetName))

		//Click on save 
		.clickSave()

		//4. Verify CRM Account # is generated 
		.verifyCRMNumberIsDisplayed()

		//5. Account Type = Member
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber,
				"accountType", sDataSheetName))

		//Class of Trade =Any
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber,
				"classOfTrade", sDataSheetName))


		//Business Classification = Auto populated
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(
				iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//Account Status = Auto Populated to Active 
		.verifyDefaultAccountStatus()

		//Application Start Date = Today's Date
		.chooseApplicationDate(DataInputProvider.getCellData_ColName(iRowNumber,
				"applicationDate", sDataSheetName))

		//CAMS Flag = Yes .changeCAMSFlagAsYes()

		//Participation Type = Standard
		.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber,
				"participationType", sDataSheetName))


		//Direct Parent Entity Code = 673415
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber,
				"directParent", sDataSheetName))

		//Direct Parent Relation = Managed 
		.selectDirectParentRelationManaged()

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(
				iRowNumber, "directParentRelationDate", sDataSheetName))

		//Top Parent Relation = OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		// Top Parent Relation Date = Today's Date 
		.selectTopParentRelationDate(
				DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate",
						sDataSheetName))

		//Click on Save 
		.clickSave()

		//6. Street 1 = Any
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1",
				sDataSheetName))

		//City = NY 
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber,
				"city", sDataSheetName))

		//Country =USA 
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber,
				"country", sDataSheetName))

		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode",
				sDataSheetName))

		//Click on Save 
		.clickSave()

		//7. Click the + icon on the Line of Business Grid 
		.clickLineOfBusinesses()

		//Click New Line Of Business 
		.clickAddNewLineOfBusiness()

		// Line of Business =General GPO
		.selectLineOfBusiness(DataInputProvider.getCellData_ColName(iRowNumber,
				"lineOfBusiness", sDataSheetName))

		// Classification Type = General GPO
		.selectLOBfClassificationType(DataInputProvider.getCellData_ColName(
				iRowNumber, "lineOfClassification", sDataSheetName))

		// Start Date =Today's date
		.typeLineOfBusinessStartDate(DataInputProvider.getCellData_ColName(
				iRowNumber, "lineOfBusinessStartDate", sDataSheetName))

		// Click on LOB Save //

		.clickLOBSaveAndClose()
		//Click add new membership 
		.clickMembershipAndAddNewMembership()

		// Choose Membership type
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber,
				"membershipProviderType", sDataSheetName))
		.selectMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber,
				"membershipProvider", sDataSheetName))

		//Provide any start date and click on save
		.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber,
				"membershipProviderStartDate", sDataSheetName))

		//Click on membership save and close
		.clickQuickCreateMembershipSaveAndClose()

		//8. Record Status = Published 
		.chooseRecordStatusPublished()

		//Click on Save 
		.clickSave()

		//9. Verify Entity code is generated 
		.entityCodeIsDisplayed()

		.clickLogout() 
		.refreshPageChooseSignInUser() ;

		new LoginPage() 
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber,
				"email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password",
				sDataSheetName)) 
		.clicSignin() 
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for EIN 673415 
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber",
				sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromSearchResults() 
		.selectSubaccount()

		//4. Click on Add new account 
		.clickNewAccountInSubAccount()

		//6. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber,
				"accountName", sDataSheetName))

		//Top Parent Relation = OLM 
		.selectTopParentRelation("Affiliate")

		//Top Parent Relation Date = Today's Date 
		.selectTopParentRelationDate(
				DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate",sDataSheetName))

		//Top Parent Relation = OLM
				.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))
		//type TP reason
		.typeTPReason(DataInputProvider.getCellData_ColName(iRowNumber, "tpReason",
				sDataSheetName))

		//Click on save 
		.clickSave()

		//7. Verify CRM Account # is generated 
		.verifyCRMNumberIsDisplayed()

		//8 Account Type = Member
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber,
				"accountType", sDataSheetName))

		//Class of Trade =Any
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber,
				"classOfTrade", sDataSheetName))

		//Business Classification = Auto populated
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(
				iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//Account Status = Auto Populated to Active 
		.verifyDefaultAccountStatus()

		//Application Start Date = Today's Date
		.chooseApplicationDate(DataInputProvider.getCellData_ColName(iRowNumber,
				"applicationDate", sDataSheetName))

		//CAMS Flag = Yes 
		.changeCAMSFlagAsNo()

		//Participation Type = Standard
		.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber,
				"participationType", sDataSheetName))

		//Direct Parent Relation = Managed 
		.selectDirectParentRelationManaged()

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(
				iRowNumber, "directParentRelationDate", sDataSheetName))

		//DP exception reason = Any 
		.typeDPReason("Test")

		//Click on Save 
		.clickSave()

		//9. Street 1 = Any
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1",
				sDataSheetName))

		//City = NY 
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber,
				"city", sDataSheetName))

		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode",
				sDataSheetName))

		//Click on Save 
		.clickSave()


		//Click add new membership 
		.clickMembershipAndAddNewMembership()

		// Choose Membership type
		.selectMembershipType("Premier")
		.selectMembershipProvider("Non-GPO")

		//Provide any start date and click on save
		.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber,
				"membershipProviderStartDate", sDataSheetName))

		//Click on membership save and close
		.clickQuickCreateMembershipSaveAndClose()

		//11. Record Status = Published 
		.chooseRecordStatusPublished()

		//Click on Save
		.clickSave()

		//12. Verify Entity code is generated 
		.entityCodeIsDisplayed()
		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromSearchResults() .typeAccountName(
				"AccountnamewithmorecharactersexampleonesixtycharactersAccountnamewithmorecharactersexampleonesixtycharactersAccountnamewithmorecharacterexampleonesixty")
		.clickSave()
		.typeAccountName("Trinity Health St. Joseph") 
		.clickSave()

		.selectAccountsTab()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber1", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromSearchResults() 
		.typeAccountName(
				"AccountnamewithmorecharactersexampleonesixtycharactersAccountnamewithmorecharactersexampleonesixtycharactersAccountnamewithmorecharacterexampleonesixty")
		.clickSave() 
		.typeAccountName("Your Health Team") 
		.clickSave() 
		.clickLogout()
		.refreshPageChooseSignInUser() ;


		//Access Login Page
		new LoginPage()	

		//Type the Username
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//Select Accounts Entity
		.selectAccountsTab()

		//Search Existing Account using CRM#
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber2", sDataSheetName))

		//Choose the desired account from the search results
		.selectSupplierAccountFromSearchResults()

		.typeAccountName("AccountnamewithmorecharactersexampleonesixtycharactersAccountnamewithmorecharactersexampleonesixtycharactersAccountnamewithmorecharacterexampleonesixty")
		.clickSave()
		.typeAccountName("2D Imaging, Inc.")
		.clickSave();

		new DashboardPage()	
		//Select Accounts Entity
		.selectAccountsTab()

		//Click on +Ne( goes to Accounts Page)
		.clickNewOnAccountsPage()

		//Choose 'Supplier Form' Option
		.chooseSupplierForm()

		//Verify Default Account Status on Supplier Form
		.defaultAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountStatus", sDataSheetName))

		//Verify Default Account Type on Supplier Form
		.defaultAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountType", sDataSheetName))		

		//Type the Account Name
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))		

		//Enter Premier Start Date
		.pickPremierStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "premierStartDate", sDataSheetName))		

		//Choose the Business Classification
		.selectBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "businessClassification", sDataSheetName))

		//Choose Direct Parent
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "SdirectParent", sDataSheetName))	

		//Choose Direct Parent Relation date
		.selectDPParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		//Enter Top Parent Relation Date
		.pickTPRD(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))	

		//Enter the Street1 address info
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))		

		//Enter the Zip Code address info
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))	


		//Save the information
		.clickSave()

		//Choose Record Status as Published
		.recordStatusPublished(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatusPublished", sDataSheetName))

		//Verify if CRM# is generated.
		.crmNumberIsDisplayed()

		//Save the information
		.clickSave()

		//Verify if Entity Code is generated.
		.entityCodeIsDisplayed();
		;
	}
}
