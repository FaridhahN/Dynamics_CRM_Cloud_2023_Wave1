package testcases.ProgramCategories;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_1029572:_1029572:Verify program category created is displayed under Program Category Associated View

public class TestCase_1029572 {


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
		
		
		//Data REset 
		//Delete the Program Categories
		
		.selectProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName", sDataSheetName))
		
		//Delete Categories
		.deleteProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName", sDataSheetName))
		;
	}

}
