package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

// TFS ID_109506:_Verify Whether new Supplier diversity Type ' Service Disabled Veteran'  is available.


public class TestCase_109506 {


	@Test
	public void verifyServiceDisabledVeteran(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()


		//Select Accounts Entity
		.selectAccountsTab()

		//Search Existing Account using CRM#
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		//.selectAllSupplierView()

		//Choose the desired account from the search results
		.selectSupplierAccountFromSearchResults()

		//AI changes
		.clearAllSuggestions()
		
		//Add New Diversity Information
		.chooseRelatedDiversityInformation()
		.addNewDiversityInfo()
		.addSerDisVetDiversityType("Service Disabled Veteran",
				DataInputProvider.getCellData_ColName(iRowNumber, "certifyingAgency", sDataSheetName)
				,"Asian-Pacific")
		
		//Data Reset
		.deactivateAllDiversityInfo()
		;



	}
}
