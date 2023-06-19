package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS_ID_ 7485:Verify on "Update" following fields in Limited Member ,"Record status" should get changed to Need Approval.

public class TestCase_7485 {


	@Test
	public void createMemberTP(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Limited member 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Workplace >> Take Any Member Account 
		.selectAccountsTab()		

		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Verify following Fields are editable in Opened Member Account 
		//Account Name
		.verifyAccountNameIsEnabled()

		//Account Name 2
		//	.verifyAccountName2IsEnabled()
		
		//Street 1
		.verifyStreet1IsEnabled()

		//Class of Trade
		.verifyClassOfTradeIsEnabled()

		

		//Street 2
		//.verifyStreet2IsEnabled()

		//Delivery Info
		.verifyDeliveryInfoIsEnabled()

		//City
		.verifyCityIsEnabled()

		//State/Province
		.verifyStateIsEnabled()

		//County
		.verifyCountyIsEnabled()

		//Country
		.verifyCountryIsEnabled()

		//Zip/Postal Code
		.verifyZipIsEnabled()

		//Main Phone Number
		.verifyMainPhoneIsEnabled()

		//4.On Update/change the following fields in Member Account :                                                                                                   

		//Account Name
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Account Name 2
		.typeAccountName2(DataInputProvider.getCellData_ColName(iRowNumber, "accountName2", sDataSheetName))

		//Class of Trade
		//	.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "ClassOfTrade", sDataSheetName))

		//Street 1
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//Street 2
		.typeStreet2(DataInputProvider.getCellData_ColName(iRowNumber, "street2", sDataSheetName))

		//Delivery Info
		.typeDeliveryInfo(DataInputProvider.getCellData_ColName(iRowNumber, "deliveryInfo", sDataSheetName))

		//City
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//State/Province
		.typeState(DataInputProvider.getCellData_ColName(iRowNumber, "state", sDataSheetName))

		//County
		.typeCounty(DataInputProvider.getCellData_ColName(iRowNumber, "county", sDataSheetName))

		//Country
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		//Zip/Postal Code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))

		//Main Phone Number
		.typeMainPhone(DataInputProvider.getCellData_ColName(iRowNumber, "mainPhone", sDataSheetName))	


		//5. Click  on Save after updating the following fields. 
		.clickSave() 

		//6.Verify and Observe the Record status field after updating following fields. 
		.verifyRecordChangeStatus(DataInputProvider.getCellData_ColName(iRowNumber, "verifyRecordChangeStatus", sDataSheetName))

		;

	}
}
