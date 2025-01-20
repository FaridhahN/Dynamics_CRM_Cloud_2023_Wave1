package testcases.Supplier;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

//Test Case ID	: Test Case 5906:Automation: Activate terminated supplier


public class TestCase_5906 {

	@Test()

	public void createSupplierWithSupplierLogin(int iRowNumber, String sDataSheetName) throws Exception {
		new LoginPage()
		//1. Login to CRM as a Supplier Supervisor  *** User should be logged in successfully 
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace > Accounts > +New  *** New Supplier Form should be opened 
		.selectAccountsTab()
		.clickNewOnAccountsPage()
		.chooseSupplierForm()

		//3. Fill in the below mandatory fields *** The given details should be saved successfully and CRM# should be generated. 
		//Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))	

		
		//Direct Parent  =2000133648
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//Direct Parent Relation Date  =Any
		.selectDPParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "selectDPRelationDate", sDataSheetName))

		//Top Parent Relation Date = Any
		.pickTPRD(DataInputProvider.getCellData_ColName(iRowNumber, "selectTPRelationDate", sDataSheetName))

		//Street 1  =Any
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//Zip Code = Any
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))

		.navigateToDoNotVerify()
		
		//Business Classification = Any
				.selectBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "businessClassification", sDataSheetName))

		//Account Status = Auto populated  ( Active)
		.defaultAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountStatus", sDataSheetName))

		//Account Type = Auto populated  (Supplier)
		.defaultAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountType", sDataSheetName))

		//Record Status = Draft (Default)
		.recordStatusDraft()

		//Save the Record
		.clickSave()

		//.crmNumberIsDisplayed()

		//4. Verify the Premier Start Date *** Premier Start Date should be auto-populated as Today's date 
		.verifyPremierStartDateAsCurrentDate()

		//5.Modify Premier Start Date to different date and save ***The new Premier Start Date should be saved 
		.pickPremierStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "premierStartDate", sDataSheetName))
		//Save the Record
		.clickSave()

		//6.Change Record Status = Published and save  *** The Supplier Record should be published and Entity Code should be generated .
		.recordStatusPublished(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatusPublished", sDataSheetName))	
		//Save the Record
		.clickSave()
		.entityCodeIsDisplayed()

		//8.Now move the Record Status = Draft and save  *** Record should be moved to Draft 
		.recordStatusDraft()
		//Save the Record
		.clickSave()

		//7.Go to Membership  Entity and verify the Premier/National Membership *** The Premier/National Membership should be created with Start Date as  Premier Start Date	
		.selectMembership()
		//Double click on the Premier: National Membership   
		.doubleClickOnNationalMembership(DataInputProvider.getCellData_ColName(iRowNumber, "premierStartDate", sDataSheetName))

		//9. Go to Membership  Entity and End the Premier/National Membership ***  The Premier Membership should be End Dated successfully
		.typeMembershipEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))

		// End reason = Anything from drop-down,
		.selectMembershipEndReason(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndReason", sDataSheetName))

		// then save
		.clickMembershipSaveAndClose()

		//10.Verify the Account Status***  The Account Status should be moved to "Terminated"
		.defaultAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "accountStatusTerminated", sDataSheetName))

		//11.Verify the Premier End Date *** Premier End Date should be populated with Premier /National Membership's End date. 
		.verifyPremierEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))

		//12.Change Record Status =Published *** The Record should be Published successfully and Premier End Date to remain intact 
		.recordStatusPublished(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatusPublished", sDataSheetName))	
		//Save the Record
		.clickSave()

		.verifyPremierEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))

		//13.Again Change the Record Status = Draft and save ***  Record to be moved to Draft successfully 
		.recordStatusDraft()
		.clickSave()

		//14.Verify Account Status *** Account Status will remain as "Terminated" 
		.pageRefresh()
		.defaultAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "accountStatusTerminated", sDataSheetName))

		//15. Go to Membership  Entity and  Click on +Add New Membership and Provide the below details *** New Premier/National Membership should be created Successfully with given date. 
		//Membership Type =Premier
		.selectMembership()

		.clickAddNewMembershipProvider()

		.selectMembershipProviderType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))

		//Membership Provider =National
		.typeMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))

		//Start Date = Future Date and save
		.selectMembershipProviderStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

		.clickAddNewMembershipProviderSave()


		//16.Verify the Account Status *** Account Status should be moved to Active 
		.pageRefresh()
		.defaultAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountStatus", sDataSheetName))

		//17.Verify Premier Start Date ***Premier Start Date should be updated to the start date of the newly added Premier/National Membership 
		.verifyPremierStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

		//18.Verify Premier End Date ***Premier End Date should be blank 
		.verifyPremierEndDateIsNull()

		//19.Change Record Status =Published and save ***The Supplier Account should be reactivated and published successfully 
		.recordStatusPublished(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatusPublished", sDataSheetName))	
		//Save the Record
		.clickSave()

		.defaultAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountStatus", sDataSheetName));	
	}
}
