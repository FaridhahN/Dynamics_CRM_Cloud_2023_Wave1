package testcases.Member;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8778:Cloud : Verify a Business Partner record can be created and published by a Member Supervisor - Positive case

public class TestCase_8778 {

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
		.chooseSupplierForm()

		//Account type = Business partner
		.selectAccountType(DataInputProvider.getCellData_ColName(iRowNumber, "accountType", sDataSheetName))


		//Enter Account name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))


		//3 
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))

		//Country =USA
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "County", sDataSheetName))

		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))


		.selectBusinessClassification("Association")
		
		//DPRD = Current date
		.selectDPParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		//TPRD =  Current date
		.pickTPRD( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		//then save the form
		.clickSave() 

		
		.recordStatusPublished("Published")
		.clickSave() 			

		//6. Capture the CRM# and Entity code
		//.verifyCRMNumberIsDisplayed()	
		.entityCodeIsDisplayed()
		;
	}
}
