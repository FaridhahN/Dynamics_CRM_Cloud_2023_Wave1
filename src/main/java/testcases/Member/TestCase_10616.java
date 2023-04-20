package testcases.Member;


import org.testng.annotations.Test;

import pages.LoginPage;

import utils.DataInputProvider;
//TFS_ID_10616:Cloud : Verify whether Base Read only role cannot edit "Member" Account.

public class TestCase_10616 {


	@Test
	public void verifyLimitedMemberNonEditablefields(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM as Base- Read Only
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Work place >> Take Any Member Account 
		.selectAccountsTab()		
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber", sDataSheetName)) 
		.selectAccountFromSearchResults()

		//3.Verify following Fields are NOT editable in Opened Member Account
		.verifyAccountNameIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
		.verifyAccountName2IsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "accountName2", sDataSheetName))
		
		
		.verifyPremierStartDateIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "membershipProviderStartDate", sDataSheetName))
		.verifyPremierEndDateIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "membershipEndDate", sDataSheetName))
		.verifyGroupIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "verifyGroup", sDataSheetName))
		.verifyClassOfTradeIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))
		.verifyPrimaryContactIsNotEditable()
		.verifyStoreLocTypeIsNotEditable()
		.verifyStoreLocNumIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "storeLocNum", sDataSheetName))
		.verifyStreet1IsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))
		
		.verifyStreet2IsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "street2", sDataSheetName))
		
		.verifyCityIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))
		.verifyCountyIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "county", sDataSheetName))
		.verifyStateIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "state", sDataSheetName))
		.verifyZipCodeIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))
		.verifyCountryIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))
		.verifyDeliveryInfoIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "deliveryInfo", sDataSheetName))
		.verifyOverrideNameIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "overrideName", sDataSheetName))
		.verifyMainPhoneIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "mainPhone", sDataSheetName))
		.verifyOtherPhoneIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "otherPhone", sDataSheetName))
		.verifyFaxIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "fax", sDataSheetName))
		.verifyWebsiteIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "website", sDataSheetName))
		.verifyReceiveDirectMailIsNotEditable()
		.verifyDoNotVerifyAddressIsNotEditable()
		.verifyDirectParentDetailsIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber1", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "dPReason", sDataSheetName))
		.verifyTopParentDetailsIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "topParent", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber1", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "tPReason", sDataSheetName))
		.verifyGroupIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "verifyGroup", sDataSheetName))
		.verifyClassOfTradeIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))
		.verifyFacilityTypeIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "verifyFacilityType", sDataSheetName))
		.verifyBusinessClassificationIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))
		.verifyPremierOwnerIsNotEditable()
		.verifyAccountStatusIsNotEditable()
		.verifyApplicationDateIsNotEditable()
		.verifyCAMSFlagIsNotEditable()
		.verifyExcludeFromRosterIsNotEditable()
		.verifyParticipationTypeIsNotEditable()
		.verifyProviderSelectMDIsNotEditable()
		.verifyBusinessKeyIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "bKActive", sDataSheetName))
		.verifyBKActiveIsNotEditable()
		.verifySiebelIDIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "siebelID", sDataSheetName))
		.verifyRegionIsNotEditable()
		.verifySponsorDetailsIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "sponsor", sDataSheetName))
		.verifySupplierRecordIsNotEditable()
		.verifyExternalAddressIDIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "externalAddressID", sDataSheetName))
		.verifyOwnershipIsNotEditable()
		.verifyStockSymbolIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "stockSymbol", sDataSheetName))
		.verifyExchangeIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "exchange", sDataSheetName))
		.verifyCorporateParentDetailsIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "corporateParent", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber1", sDataSheetName))
		.verifyPaymentEntityDetailsIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "paymentEntity", sDataSheetName))
		.verifyCorporateRebateDetailsIsNotEditable()
		.verifyFoodServiceParentDetailsIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "foodServiceParent", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "crmNumber1", sDataSheetName))
		.verifyRequireManualAGAssignmentIsNotEditable()
		.verifyAffiliateGroupIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "affiliateGroup", sDataSheetName))
		.verifyAffiliateGroupEffectiveDateIsNotEditable()
		.verifyRecordStatusIsNotEditable()
		.verifyAccountTypeIsNotEditable()
		.verifyRecordChangeStatusIsNotEditable()
		.verifyPremierRosterIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "premierRoster", sDataSheetName))
		.navigateToFeeShare()
		.verifyCurrentInternalRepIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "currentInternalRep", sDataSheetName))
		.verifyCurrentFieldRepIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "currentFieldRep", sDataSheetName))
		.verifyFBOtDetailsIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "fBO", sDataSheetName))		
		.verifyFeeShareDetailsIsNotEditable()
		.clickNYInformationTab()
		.verifyEstimatedNumLocationsIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "numEstLoc", sDataSheetName))
		.verifyNAICSIsNotEditable()
		.verifyReferredByIsNotEditable(DataInputProvider.getCellData_ColName(iRowNumber, "referredBy", sDataSheetName));


		//Data Reset Not Required
	}
}
