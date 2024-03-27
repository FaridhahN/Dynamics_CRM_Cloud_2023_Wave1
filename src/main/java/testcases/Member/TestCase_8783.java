package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8783:Cloud - Validate Account Type Transitions
//Test Case 8766:Cloud : Validate Account Type Transitions


public class TestCase_8783 {


	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		.selectAccountType("Member")

		//Click on Save 
		.clickSave() 


		//Account should be published successfully, EIN number should be captured successfully
		.entityCodeIsDisplayed()

		.verifyAccountTypeLocked()
		.clickLogout()
		.refreshPageChooseSignInUser()
		;



		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email2", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//Select Accounts Entity
		.selectAccountsTab()

		//Click on +New ( goes to Accounts Page)
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

		//Choose Is TP as Yes
		.clickIsTPYes()		

		//Enter Top Parent Relation Date
		.pickTPRD(DataInputProvider.getCellData_ColName(iRowNumber, "selectTPRelationDate", sDataSheetName))

		//Enter the Street1 address info
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))		

		//City = NY
				.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))
				
		//Enter the Zip Code address info
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))	

		// Add Contact to Primary Contact field
		.addSupplierPrimaryContact(DataInputProvider.getCellData_ColName(iRowNumber, "addSupplierPrimaryContact",sDataSheetName))

		//Verify the Primary Contact value
		.verifyPrimaryContactValue(DataInputProvider.getCellData_ColName(iRowNumber, "verifyPrimaryContactValue",sDataSheetName))

		//Save the information
		.clickSave()

		//Choose Record Status as Published
		.recordStatusPublished(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatusPublished", sDataSheetName))

		//Verify if CRM# is generated.
		.crmNumberIsDisplayed()

		//Save the information
		.clickSave()

		//Verify if Entity Code is generated.
		.entityCodeIsDisplayed()
		.verifyAccountTypeLocked();
	}
}
