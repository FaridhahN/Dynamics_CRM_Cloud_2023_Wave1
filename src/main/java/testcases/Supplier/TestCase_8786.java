package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
// TFS ID_8786:Cloud - Validate (Member/Supplier) Account Type once set to value other than Prospect cannot change.


public class TestCase_8786 {


	@Test
	public void createMember(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		.selectAccountType("Prospect")		

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

		//Verify the Direct Parent value same as chosen above
		.verifyDPValue(DataInputProvider.getCellData_ColName(iRowNumber, "verifyDPValue", sDataSheetName))	

		//Save the information
		.clickSave()

		.selectAccountType("Supplier")		


		//Choose Record Status as Published
		.recordStatusPublished(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatusPublished", sDataSheetName))

		//Choose the Business Classification
		.selectBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "businessClassification", sDataSheetName))

		//Save the information
		.clickSave()



		//Verify if Entity Code is generated.
		.entityCodeIsDisplayed()
		.verifyselectAccountTypeIsReadOnly("Prospect");

	}
}
