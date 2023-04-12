package testcases.PatientServices;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;

//Test Case 9523:Cloud : Edit Senior Living /LTC beds count

public class TestCase_9523 {


	@Test
	public void PatientServices(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException 
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
		.selectAccountFromSearchResults()
		.selectPatientServices()
		.clickNewPatientServices()
		.AddGeneralActivities("2","2","2")
		.navigatetoSeniorLivingLTC()
		.AddSeniorLivingData("2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2")
		.navigatetoSeniorLivingLTC()
		.verifyNoOfBeds("22")
		.AddSeniorLivingData("2", "2", "2", "3", "2", "3", "2", "2", "2", "2", "2", "2")
		.navigatetoSeniorLivingLTC()
		.verifyNoOfBeds("24");
		;
	}

}
