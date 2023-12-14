package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

// TFS ID_109563:_Verify whether following 'Sub Classification Types' are available for Supplier Types-'Disabled Business Enterprise', 'Service Disabled Veteran', 'Women Owned'.


public class TestCase_109563 {


	@Test
	public void verifySubclassificationtypesOnDiversityInfo(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		
		//Choose the desired account from the search results
		.selectSupplierAccountFromSearchResults()


		//Add New Diversity Information
		.chooseRelatedDiversityInformation()
		.addNewDiversityInfo()
		.verifyWomenOwndSubClassificationOptions(DataInputProvider.getCellData_ColName(iRowNumber, "diversityType2", sDataSheetName))
		.addWomenOwndDiversityType(DataInputProvider.getCellData_ColName(iRowNumber, "diversityType2", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "certifyingAgency", sDataSheetName)
				,DataInputProvider.getCellData_ColName(iRowNumber, "diversityStartDate", sDataSheetName),
				(DataInputProvider.getCellData_ColName(iRowNumber, "subClassification2", sDataSheetName)))
		.addNewDiversityInfo()
		.verifySerDisVetSubClassificationOptions(DataInputProvider.getCellData_ColName(iRowNumber, "diversityType3", sDataSheetName))
		.addSerDisVetDiversityType(DataInputProvider.getCellData_ColName(iRowNumber, "diversityType3", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "certifyingAgency", sDataSheetName)
				,DataInputProvider.getCellData_ColName(iRowNumber, "diversityStartDate", sDataSheetName),
				"Asian-Pacific")
		.addNewDiversityInfo()
		.verifyDisBusEntSubClassificationOptions(DataInputProvider.getCellData_ColName(iRowNumber, "diversityType4", sDataSheetName))
		.addDisBusEntDiversityType(DataInputProvider.getCellData_ColName(iRowNumber, "diversityType4", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "certifyingAgency", sDataSheetName)
				,DataInputProvider.getCellData_ColName(iRowNumber, "diversityStartDate", sDataSheetName),
				"Black")

		//Data Reset
		.deactivateAllDiversityInfo()
		;



	}
}
