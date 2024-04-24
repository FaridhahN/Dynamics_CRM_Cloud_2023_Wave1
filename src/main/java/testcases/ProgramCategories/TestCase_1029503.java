package testcases.ProgramCategories;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//TFS ID_1029503:_1029503:Verify Program categories entity available on the Member form


public class TestCase_1029503 {


	@Test
	public void PrescriptionData(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
	{

		new WebDriverServiceImpl();
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
		;
	}

}
