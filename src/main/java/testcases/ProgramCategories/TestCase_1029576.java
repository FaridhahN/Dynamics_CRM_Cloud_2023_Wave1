package testcases.ProgramCategories;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_1029576:_1029576:Verify active program category can be viewed under Active Program Categories view


public class TestCase_1029576 {


	@Test
	public void programCategory(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		//2.Go to Workplace > Accounts and search for  
		.selectAccountsTab()

		//Search GPO Active Member
		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//3.Double click on the account and go to Sub accounts entity by clicking > on the top 
		.selectAccountFromGlobalSearchResults(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))

		//Select Program Category
		.selectProgramCategories()

		//Verify Program Category view
		.verifyProgramCategoryPage()

		//click new Program Category
		.clickProgramCategories()

		//Add NEw Category
		.addProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "EMSAttributeCode", sDataSheetName))

		//
		//Click save and Close
		.saveAndCloseProgramCategories()

		//Verify Program Categories are created
		.verifyProgramCategoriesCreated(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName", sDataSheetName))

		//Active View
		.selectProgramCategoryView(DataInputProvider.getCellData_ColName(iRowNumber, "view", sDataSheetName))

		//Data REset 
		//Delete the Program Categories

		.selectProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName", sDataSheetName))

		//Delete Categories
		.deleteProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName", sDataSheetName))


		//Create Other Program cateogries

		//click new Program Category
		.clickProgramCategories()

		//Add NEw Category
		.addProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName1", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "EMSAttributeCode1", sDataSheetName))

		//
		//Click save and Close
		.saveAndCloseProgramCategories()

		//Verify Program Categories are created
		.verifyProgramCategoriesCreated(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName1", sDataSheetName))

		//Select existing categories
		.selectProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName1", sDataSheetName))

		//deactivate category
		.deactivateProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName1", sDataSheetName))

		.clickGoBackButton()
		//inactive view
		.selectProgramCategoryView(DataInputProvider.getCellData_ColName(iRowNumber, "view1", sDataSheetName))

		//Verify Program Categories are created
		.verifyProgramCategoriesCreated(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName1", sDataSheetName))

		//Data REset 
				//Delete the Program Categories

				.selectProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName1", sDataSheetName))

				//Delete Categories
				.deleteProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName1", sDataSheetName))
		;
	}

}
