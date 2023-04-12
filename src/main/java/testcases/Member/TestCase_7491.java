package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//TFS ID_ 7491:Create New Business Partner account using member supervisor


public class TestCase_7491 {

	@Test
	public void createBusinessPartner(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2. Go to Account and click on +New button 
		.selectAccountsTab()
		.clickNewOnAccountsPage()
		.chooseMemberForm()
		//3 
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//Country =USA
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))

		//Enter Account name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))
		
		.clickSave()

		//Account type = Business partner
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))

		//Direct Parent = 1000039759
		//.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))
		.selectDirectParentWithoutEnitiy(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//TP relation - Affiliated
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		//DPRD = Current date
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		//TPRD =  Current date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		//then save the form
		.clickSave() 

		//Verify CRM numbers is generated
		//.verifyCRMNumberIsDisplayed()	

		//4. Verify Form layout is changed from Member to Supplier 
		.verifySupplierFormIsDisplayed()

		//5.Change the record status from Draft to Publish and save 
		.chooseStatusPublish()
		.clickSave() 			

		//6. Capture the CRM# and Entity code
		//.verifyCRMNumberIsDisplayed()	
		.entityCodeIsDisplayed()
		;
	}
}
