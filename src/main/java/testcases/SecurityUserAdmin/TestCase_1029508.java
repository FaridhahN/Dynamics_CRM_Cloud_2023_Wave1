package testcases.SecurityUserAdmin;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_1029508:_1029508: Verify User admin can create or edit the Program category

public class TestCase_1029508 {


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
		
		
		//click new Program Category
		.clickProgramCategories()
		
		//Add NEw Category
				.addProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "EMSAttributeCode", sDataSheetName))

		//Click save and Close
		.saveAndCloseProgramCategories()
		
		.verifyErrorisNotDisplayed()
		
		//Verify Program Categories are created
				.verifyProgramCategoriesCreated(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName", sDataSheetName))

				//Active View
				.selectProgramCategoryView(DataInputProvider.getCellData_ColName(iRowNumber, "view", sDataSheetName))

				//open categories
				.openProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName", sDataSheetName))
				
				//edit categories 
				
				.editProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName1", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "EMSAttributeCode1", sDataSheetName))
				
				
				//Click save and Close
				.saveAndCloseProgramCategories()
				
				.verifyErrorisNotDisplayed()
				
				//Data REset 
				//Delete the Program Categories

				.selectProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName1", sDataSheetName))

				//Delete Categories
				.deleteProgramCategories(DataInputProvider.getCellData_ColName(iRowNumber, "CategoryName1", sDataSheetName))

		;
	}

}
