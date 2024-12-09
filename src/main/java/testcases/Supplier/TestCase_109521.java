package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//  TFS ID_109521:_Verify Whether new Supplier diversity Type ' Disabled Business Enterprise'  is available.


public class TestCase_109521 {


	@Test
	public void verifyDisabledBusinessEnterprise(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		
		//AI Suggestions
		.clearAllSuggestions()


		//Add New Diversity Information
		.chooseRelatedDiversityInformation()
		.addNewDiversityInfo()
		.addDisBusEntDiversityType("Disabled Business Enterprise",
				DataInputProvider.getCellData_ColName(iRowNumber, "certifyingAgency", sDataSheetName)
				,DataInputProvider.getCellData_ColName(iRowNumber, "diversityStartDate", sDataSheetName),
				"Hispanic")

		//Data Reset
		.deactivateAllDiversityInfo()
		;



	}
}
