package testcases.Supplier;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

public class TestCase_8953 {
	
//	Test Case 8953:Cloud -Terminate a supplier location type account
		
	@Test()

	public void publishExistingSupplierAsSupplier(int iRowNumber, String sDataSheetName) throws Exception {
		
		//Access Login Page
		new LoginPage()
		
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
  	    .clicSignin()
  	    .clicYesInStaySignedin()
		
		//Select Accounts Entity
		.selectAccountsTab()
		
		//Search Existing Account using CRM#
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		
		//Choose the desired account from the search results
		.selectSupplierAccountFromSearchResults()		
		
		
		//3.Go to Related Tab > Membership Entity of the "Parent Account",Verify and observe Premier National is present in the Membership Grid.
		.selectMembership()
		
		//4.Double click on the Premier: National Membership  
		.doubleClickOnNewNationalMembership()

		//5.Verify by providing value on End date and leave End reason Blank  .click save.Note:  End Reason is a Mandatory field for Terminating Premier Membership ,End Reason value must be provided.  
		.typeMembershipEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))
		// then save
		.clickMembershipSave()
		//Req Change. End Reason Auto populates to 'Change'
		//.verifyEndReasonCanNotBeBlankError(DataInputProvider.getCellData_ColName(iRowNumber, "expectedAccountStatusErrorText", sDataSheetName))
		
		//6.Similar Verify by input , Premier End date < Premier Start date.  
		.typeMembershipEndDate("7/3/1976")
		
		// then save
		.clickMembershipSave()
		.verifyDateValidationError(DataInputProvider.getCellData_ColName(iRowNumber, "expectedErrorText1", sDataSheetName))

		//7.try to Terminate the "Parent Account"  which has Active "Child Accounts" in it with End date and End reason in the Premier: National Membership. Click on Save. 
		.typeMembershipEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))
		
		// End reason = Anything from dropdown,
		.selectMembershipEndReason(DataInputProvider.getCellData_ColName(iRowNumber, "memberShipEndReason", sDataSheetName))
		
		// then save
		.clickMembershipSaveAndClose()
		
		//8.Verify and observe Premier End date is getting updated in the Supplier form.
		.verifyPremierEndDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))
		
		//9. Verify and observe Account status=Terminated.
		.defaultAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "accountStatusTerminated", sDataSheetName))
		
		
		//Data Reset
		//~~~~~~~~~~~~~~~~~
			.selectMembership()
			
			.clickAddNewMembershipProvider()
			
			.selectMembershipProviderType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
	
			//Membership Provider =National
			.typeMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProvider", sDataSheetName))
	
			//Start Date = Future Date and save
			.selectMembershipProviderStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))
			
			.clickAddNewMembershipProviderSave()
		

		;
		
		}
}