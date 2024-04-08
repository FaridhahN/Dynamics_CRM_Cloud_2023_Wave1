package testcases.Contacts;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_828711:11249_:Cloud : Verify Primary Contact is removed from the account when a Contact Account Association is end dated

public class TestCase_828711 {

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

		//Search Account
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Click CAA from Related option
		.clickCAAFromRelated()

		//Double click CAA
		.doubleClickOnCAARecordUnderMember()


		//Termination reason
		.chooseContactTerminationReason("Terminated")


		//Enter CAA End Date
		.enterCAAEndDAteAndSave()


		.clickGoBackToMemberForm()
		.clickGeneralTab()
		//6. Click on the primary contact lookup and click on lookup for more records
		//7. Search the Contact ID - 7000133221 and click on Add
		.navigateToDP()
		.verifyPrimaryContactNullValue()

		//Update primary Contact
		.updateMemberPrimaryContact(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))

		.clickSave()

		//Record Status = Published
		.chooseRecordStatusPublished()

		//Click on Save 
		.clickSave() 


		;
	}
}
