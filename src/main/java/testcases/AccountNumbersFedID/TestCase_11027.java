package testcases.AccountNumbersFedID;
import org.testng.annotations.Test;

import driver.Driver;
import pages.AccountsPage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.DataInputProvider;

public class TestCase_11027 {

	//TFS ID_11027:_629043:Verify whether validation Error message should be thrown while activating the deactivated Federal Tax ID after a "DP change" for the Supplier account. .

	@Test()

	public void createSupplierAsSupplierSupervisor(int iRowNumber, String sDataSheetName) throws Exception {

		//Access Login Page
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
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

		//Choose Direct Parent
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))	

		//Choose Direct Parent Relation date
		.selectDPParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "selectDPRelationDate", sDataSheetName))

		//Enter Top Parent Relation Date
		.pickTPRD(DataInputProvider.getCellData_ColName(iRowNumber, "selectTPRelationDate", sDataSheetName))	

		//Enter the Street1 address info
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))		

		//Enter the Zip Code address info
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))	


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

		//4.Add the Fed Tax ID to the Supplier  and update the name
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeFedTaxID("Federal Tax ID")
		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
		.typeStartDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.verifyNameAndCalculatedNameInAccountNumbers();

		//		Search the Top Parent
		new AccountsPage()
		//3.Search and Select Supplier Account 1 using CRM#
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))
		.selectSupplierAccountFromSearchResults()


		//Add the Fed Tax ID to the Top Parent  and update the name
		.selectAccountNumbers()
		.clickAddNewAccountNumberInAccountNumbers()
		.chooseAccountNumberTypeFedTaxID("Federal Tax ID")
		.typeStaticFedTaxID(DataInputProvider.getCellData_ColName(iRowNumber, "fedTaxID", sDataSheetName))
		.typeStartDateInAccountNumbers()
		.clickSaveInAccountNumbersEntity()
		.verifyNameAndCalculatedNameInAccountNumbers()

		;

		//		Search New account created
		new AccountsPage()
		//3.Search and Select Supplier Account 1 using CRM#
		.searchAccount(DataInputProvider.getCellData_ColName(Driver.iTestCaseRowNumDriver, "CRMNumber",Driver.properties.getProperty("DriverSheetName")))
		.selectSupplierAccountFromSearchResults()
		.clearDP()
		//Choose Direct Parent
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent1", sDataSheetName))

		//Choose Direct Parent Relation date
		.selectDPParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "selectDPRelationDate", sDataSheetName))
		.navigateToTPRD()
		//Enter Top Parent Relation Date
		.pickTPRD(DataInputProvider.getCellData_ColName(iRowNumber, "selectTPRelationDate", sDataSheetName))
		.clickSave()
		//Verify ONly one Active Fed id of each account error 
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()


		.selectAccountNumbers()

		.doubleClickExistingAccountNumberFedTaxID()
		.clickDiscardChanges()
		.clickDeactivateInAccountNumbers()
		.clickGoBackButton()
		.clickGeneralTab()
		.clearDP()
		//Choose Direct Parent
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent1", sDataSheetName))

		//Choose Direct Parent Relation date
		.selectDPParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "selectDPRelationDate", sDataSheetName))
		.navigateToTPRD()
		//Enter Top Parent Relation Date
		.pickTPRD(DataInputProvider.getCellData_ColName(iRowNumber, "selectTPRelationDate", sDataSheetName))
		.clickSave()
		.verifyErrorisNotDisplayed()

		.selectAccountNumbersTab()
		.selectDeactivatedAccountNumber()
		.doubleClickExistingAccountNumberFedTaxID()
		.clickActivateInAccountNumbers()
		//Verify ONly one Active Fed id of each account error 
		.verifyAccountNumberErrorMessage(DataInputProvider.getCellData_ColName(iRowNumber, "errorMessage", sDataSheetName))
		.clickOKOnAccountNumberErrorMessage()

		;

		//		Search New account created
		new AccountsPage()
		//3.Search and Select Supplier Account 1 using CRM#
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))
		.selectSupplierAccountFromSearchResults()


		//Add the Fed Tax ID to the Top Parent  and update the name
		.selectAccountNumbers()
		.doubleClickExistingAccountNumberFedTaxID()

		.clickDeactivateInAccountNumbers()
		;
	}


}