package testcases.Member;

import org.testng.annotations.Test;

import driver.Driver;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_ 7473:Change Account status to inactive for a Membership Provider with type "SOAR"

public class TestCase_7473 {


	@Test
	public void changeAccountStatusInactiveMP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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

		//7. Choose SOAR and save the record
		.selectMembershipProviderType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))

		//save the record
		.clickSave() 

		//.navigateToAccountStatusFromMPConfig()

		//Verify CRM Account # is generated 
		.verifyCRMNumberIsDisplayed()


		/*	//8. Go to the Accounts and search for CRM# 1000155584  ******************Triggers large cascade*************
			.selectAccountss()
			.chooseActiveMember1(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))	

		//9. Go to Membership entity and click on Add new membership
			.selectMembershipEntity()
			.clickAddNewMembershipProvider()

		//10 Choose Membership type as "Aggregation Affiliation" and search for the CRM# we captured in Step#4 
			.selectMembershipProviderTypeInAddNewMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "MembershipProviderType", sDataSheetName))
			.typeCRMNumberInAddNewMembershipProvider(DataInputProvider.getCellData_ColName(Driver.iTestCaseRowNumDriver, "CRMNumber",Driver.properties.getProperty("DriverSheetName")))

		//11. Provide any start date and click on save
			.selectMembershipProviderStartDateInAddNewMembershipProvider(DataInputProvider.getCellData_ColName(iRowNumber, "MembershipProviderStartDate", sDataSheetName))
			.clickAddNewMembershipProviderSave()*/

		//12. Change the Account status to "Inactive" & save 			
		//.selectAccountss()
		//	.chooseActiveMember1(DataInputProvider.getCellData_ColName(Driver.iTestCaseRowNumDriver, "CRMNumber",Driver.properties.getProperty("DriverSheetName")))	
		.selectAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "accountStatus", sDataSheetName))

		//save the record
		.clickSave()

		//13. Go to any member account and try to add new membership with the inactivated membership provider
		.selectAccountss()
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromSearchResults()

		.clickMembershipAndAddNewMembership()
		.selectMembershipType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))
		.TyprMembershipProvider(DataInputProvider.getCellData_ColName(Driver.iTestCaseRowNumDriver, "CRMNumber",Driver.properties.getProperty("DriverSheetName")))
		.verifyNoRecordsFoundMsgInAddMP()
		;

	}
}
