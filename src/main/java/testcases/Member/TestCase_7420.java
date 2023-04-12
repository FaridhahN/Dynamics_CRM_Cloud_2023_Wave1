package testcases.Member;

import org.testng.annotations.Test;

import driver.Driver;
import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 7531:Verify user can not create membership provider with same name as duplicate
public class TestCase_7420 {

	  	
	@Test
	public void createMembershipProvider(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {
		
		//1. Login to CRM using member supervisor / member credentials 
			new LoginPage()
			.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
			.clickNext()
	  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
	  	    .clicSignin()
	  	    .clicYesInStaySignedin()
		
		//2. Go to Workplace > Accounts > +New 
	  	    .selectAccountsTab()
	  	    .selectActiveMembers()
	  	  	.clickNewOnAccountsPage()
	  	  	.chooseMemberForm()		
			
		//3. Verify Membership provider field is defaulted to No 
			.verifyDefaultMembershipProvider()
		
		//4. Provide Account name = Any
			.typeMPAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
			
			//Change the Account type to Membership provider
			.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))

			//save the record
			.clickSave() 
			
			//Verify CRM Account # is generated 
			.verifyCRMNumberIsDisplayed()	
			
		//5. Verify Membership provider field automatically turned to Yes
			.verifyMembershipProviderYes()
		
		//6. Verify Membership provider type has below options 
			//Program, DSH, Aggregation Affiliation, Affiliate Group, SOAR, N/A 
			.verifyMembershipProviderTypeOptions(DataInputProvider.getCellData_ColName(iRowNumber, "verifyMembershipProviderTypeOptions", sDataSheetName))
			
		//7. Choose Program and save the record
			.selectMembershipProviderType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
			
			//save the record
			.clickSave() 
			
			//Verify CRM Account # is generated 
			.verifyCRMNumberIsDisplayed()	

			//8. Go to the Accounts and search for CRM# 1000155584
			.selectAccountss()
			.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
			.selectAccountFromSearchResults()
			
		//9. Go to Membership entity and click on Add new membership
			 //Click add new membership
			.clickMembershipAndAddNewMembership()
			
		//10 Choose Membership type as "Program" and search for the CRM# we captured in Step#7
		 	.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
			.selectMembershipProvider(DataInputProvider.getCellData_ColName(Driver.iTestCaseRowNumDriver, "CRMNumber",Driver.properties.getProperty("DriverSheetName")))
			
		//11. Provide any start date and click on save
			.typeMembershipStartDate(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))

		//	.clickMembershipSaveAndClose()  ---Do not click on save as it triggers large cascade
	;
	}
}
