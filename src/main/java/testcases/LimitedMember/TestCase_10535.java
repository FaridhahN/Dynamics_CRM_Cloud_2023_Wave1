package testcases.LimitedMember;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_10535-Verify Limited Member fields that are editable

public class TestCase_10535 {


	@Test
	public void verifyLimitedMemberEditablefields(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Limited member 
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
		.chooseMemberForm()

		.clickSave()
		//3.Verify following Fields are editable in Opened Member Account 
		//Account Name
		.verifyAccountNameIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Account Name 2
		.verifyAccountName2IsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "accountName2", sDataSheetName))

		//Primary Contact
		.verifyPrimaryContactIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "primaryContact", sDataSheetName))


		//Street 1			
		.verifyStreet1IsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//Street 2
		.verifyStreet2IsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "street2", sDataSheetName))

		//Delivery Info
		.verifyDeliveryInfoIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "deliveryInfo", sDataSheetName))

		//City
		.verifyCityIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//County
		.verifyCountyIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "county", sDataSheetName))


		//State/Province
		.verifyStateIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "state", sDataSheetName))


		//Zip/Postal Code
		.verifyZipIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))

		//Country
		.verifyCountryIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		.verifyOverrideNameIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "overrideName", sDataSheetName))

		//Main Phone Number
		.verifyMainPhoneIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "mainPhone", sDataSheetName))


		.verifyOtherPhoneIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "otherPhone", sDataSheetName))

		.verifyFaxIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "fax", sDataSheetName))

		.verifyWebsiteIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "website", sDataSheetName))

		.verifyReceiveDirectMailIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "receiveDirectMail", sDataSheetName))

		.verifyDoNotVerifyAddresIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "doNotVerifyAddress", sDataSheetName))

		.verifyExternalAddressIDIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "externalAddressID", sDataSheetName))

		.verifyEstLocIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "numEstLoc", sDataSheetName))

		.navigatetoNAICS()

		.verifyNAICSIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "nAICS", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "industryTitle", sDataSheetName))

		.naivagateToReferredByFromNACS()

		.verifyReferredByIsEditable(DataInputProvider.getCellData_ColName(iRowNumber, "referredBy", sDataSheetName))

		.clickNyTabSave()

		//Data Reset		
		.removeNAICS()
		.removeReferredBy()
		//.clickNyTabSave()

		.clickGeneralTab()

		.removeMemberPrimaryContact()
		.clickNyTabSave();



	}
}
