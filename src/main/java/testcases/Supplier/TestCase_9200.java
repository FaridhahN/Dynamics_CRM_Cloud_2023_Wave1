package testcases.Supplier;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_ 9200:Cloud- Verify new Supplier Diversity Types and Sub Classifications are available


public class TestCase_9200 {


	@Test
	public void verifySupplierDiversityInformation(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

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
		//.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "crmNumberInput", sDataSheetName))
		//.selectAllSupplierView()

		//Choose the desired account from the search results
		.selectSupplierAccountFromSearchResults()


		//Add New Diversity Information
		.selectDiversityType(DataInputProvider.getCellData_ColName(iRowNumber, "diversityType", sDataSheetName))
		.verifyMinorityOwndSubClassificationOptions()
		.addMinorityOwndDiversityType(DataInputProvider.getCellData_ColName(iRowNumber, "diversityType", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "certifyingAgency", sDataSheetName)
				,DataInputProvider.getCellData_ColName(iRowNumber, "diversityStartDate", sDataSheetName),
				(DataInputProvider.getCellData_ColName(iRowNumber, "subClassification", sDataSheetName)))
		.addNewDiversityInfo()
		.verifyVeteranOwndSubClassificationOptions(DataInputProvider.getCellData_ColName(iRowNumber, "diversityType1", sDataSheetName))
		.addVeteranOwndDiversityType(DataInputProvider.getCellData_ColName(iRowNumber, "diversityType1", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "certifyingAgency", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "diversityStartDate", sDataSheetName),
				(DataInputProvider.getCellData_ColName(iRowNumber, "subClassification1", sDataSheetName)))
		.addNewDiversityInfo()
		.addLGBTOwndDiversityType(DataInputProvider.getCellData_ColName(iRowNumber, "certifyingAgency", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "diversityStartDate", sDataSheetName))
		.addNewDiversityInfo()
		.addSmallBusinesDiversityType(DataInputProvider.getCellData_ColName(iRowNumber, "certifyingAgency", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "diversityStartDate", sDataSheetName))
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
		//.clickConfirmDiversityDeactivation()
		;



	}
}
