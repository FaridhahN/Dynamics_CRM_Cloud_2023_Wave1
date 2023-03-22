package testcases.Member;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import driver.Driver;
import pages.LoginPage;
import pages.MemberFormPage;
import utils.DataInputProvider;
//Test Case 7420:Create New Membership Provider with type "Program"

public class TestCase_7531 {


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
		.clickNewOnAccountsPage()
		.chooseMemberForm()		

		//4.Choose Account type as Membership provider
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))

		//Verify Membership provider field automatically turned to Yes
		.verifyMembershipProviderYes()

		//Choose Program and save the record
		.selectMembershipProviderType(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderType", sDataSheetName))

		//5.Give any name as Account name which exactly matches with the Account name in existing membership provider
		.typeMPAccountNameWithoutRandomName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//save the record
		.clickSave() 

		//System should throw an error saying user can not create duplicate membership provider 
		.verifyAccountCanNotCreateDuplicatePMError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName))

		//6.Give any different name or not as duplicate and save
		.typeMPAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		.clickSave()

		//Verify CRM Account # is generated 
		.verifyCRMNumberIsDisplayed()	
		;
	}
}
