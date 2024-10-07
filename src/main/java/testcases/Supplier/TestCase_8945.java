package testcases.Supplier;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_8945: Cloud -Verify if the Supplier is Published directly through UI is moved to siebel by Supplier Supervisor



public class TestCase_8945 {
	
	@Test()

	public void createSupplierWithSupplierLogin(int iRowNumber, String sDataSheetName) throws Exception {
		new LoginPage()
		//1. Login to CRM as a Supplier Supervisor  *** User should be logged in successfully 
			.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
			.clickNext()
	  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
	  	    .clicSignin()
	  	    .clicYesInStaySignedin()
		
		//2.From the left navigation column ,Go to My Work> Accounts > +New > Access the supplier form, DO NOT save the supplier form with Record Status = draft > fill in all the mandatory fields >Select Record Status =Published and save it
			.selectAccountsTab()
			.clickNewOnAccountsPage()
			.chooseSupplierForm()
		
		//Fill in all Mandatory Fields to create Supplier Account: 
			//Account Name = Any
			.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))	
			
			.navigateToPrimaryContact()
			//Business Classification = Any
			.selectBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "businessClassification", sDataSheetName))
			
			//Direct Parent  = 2000141874
			.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))
			
			//Direct Parent Relation Date  =Any
			.selectDPParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "selectDPRelationDate", sDataSheetName))
			
			//Top Parent Relation Date = Any
			.pickTPRD(DataInputProvider.getCellData_ColName(iRowNumber, "selectTPRelationDate", sDataSheetName))
			
			//Street 1  =Any
			.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))
			
			//Zip Code = Any
			.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "zipCode", sDataSheetName))
			
			//Account Status = Auto populated  ( Active)
			.defaultAccountStatus(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountStatus", sDataSheetName))
			
			//Account Type = Auto populated  (Supplier)
			.defaultAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "defaultAccountType", sDataSheetName))
			
			//Change record status as Published
			.recordStatusPublished(DataInputProvider.getCellData_ColName(iRowNumber, "recordStatusPublished", sDataSheetName))	
			
			//Save the Record
			.clickSave()
			
			//Verify entity code
			.entityCodeIsDisplayed()
						
		//Verify the Premier Start Date.
			.verifyPremierStartDateAsCurrentDate()
			
		;	
	}
}
