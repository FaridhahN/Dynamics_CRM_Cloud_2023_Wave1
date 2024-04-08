package testcases.Supplier;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

public class TestCase_964277  {

	//TFS ID_964277:_964277_:Verify Supplier Supervisor can publish supplier without ‘Contract Effective Date’.


	@Test
	public void createSupplierTPwithConractEffectiveDate(int iRowNumber, String sDataSheetName)throws Exception,InterruptedException {

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

		//Choose Is TP as Yes
		.clickIsTPYes()	

		//Enter Top Parent Relation Date
		.pickTPRD(TestUtils.todaysDate())	

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

		;


	}

}