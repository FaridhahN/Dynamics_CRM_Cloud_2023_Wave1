package testcases.AlithyaCPSD;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import utils.TestUtils;

//TFS ID_1089794:_1089794:Verify Channel Partner Senior Director has desired access to read, write and assign opportunities

 
public class TestCase_1089794 {


	@Test
	public void Oppurtunities(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Access Login Page		
		/*
		 * new LoginPage() .typeEmail(DataInputProvider.getCellData_ColName(iRowNumber,
		 * "email", sDataSheetName)) .clickNext()
		 * .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password",
		 * sDataSheetName)) .clicSignin() .staysignedinforOtherApp()
		 * 
		 * //Navigate to Opputrunity and create Oppurtunity .selectSalesHubAccount()
		 * .clickOppurtunitiesPage()
		 * 
		 * //Click create new oppurtunity Button .ClickNewOppurtunity()
		 * 
		 * //Enter Topic .enterTopic(DataInputProvider.getCellData_ColName(iRowNumber,
		 * "topic", sDataSheetName)+"OtherUser"+TestUtils.todaysDatewithTime())
		 * 
		 * //Select Gut Feel
		 * .selectGutFeel(DataInputProvider.getCellData_ColName(iRowNumber, "gutFeel",
		 * sDataSheetName))
		 * 
		 * //Enter est close date .typeEstimatedCloseDate(TestUtils.FutureEndDate(5))
		 * 
		 * //Enter anticipated Purchase start date
		 * .typeanticipatedPurchaseStarDate(TestUtils.todaysDate())
		 * 
		 * //Select Revenue Category
		 * .selectRevenueCategory(DataInputProvider.getCellData_ColName(iRowNumber,
		 * "category", sDataSheetName))
		 * 
		 * //enter Projected NAF
		 * .typeProjectedNAF(DataInputProvider.getCellData_ColName(iRowNumber,
		 * "projectedNAF", sDataSheetName))
		 * 
		 * //Save and verify error is not displayed .clickSaveinOpportunities()
		 * .verifyErrorisNotDisplayed()
		 * 
		 * 
		 * .clickSignout()
		 * 
		 * .pageRefresh()
		 */		
;
		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage() 
		//.refreshPageChooseSignInUser()
		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email1", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))
		.clicSignin()
		
		.staysignedinforOtherApp()

		//Navigate to Opputrunity and create Oppurtunity
				.selectSalesHubAccount()
				.clickOppurtunitiesPage()
				
				//Click create new oppurtunity Button 
				.ClickNewOppurtunity()
				
				//Enter Topic
				.enterTopic(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName)+"new "+TestUtils.todaysDatewithTime())

				//Select Gut Feel
				.selectGutFeel(DataInputProvider.getCellData_ColName(iRowNumber, "gutFeel", sDataSheetName))
				
				//Enter est close date
				.typeEstimatedCloseDate(TestUtils.FutureEndDate(5))
				
				//Enter anticipated Purchase start date
				.typeanticipatedPurchaseStarDate(TestUtils.todaysDate())
				
				//Select Revenue Category
				.selectRevenueCategory(DataInputProvider.getCellData_ColName(iRowNumber, "category", sDataSheetName))
				
				//enter Projected NAF
				.typeProjectedNAF(DataInputProvider.getCellData_ColName(iRowNumber, "projectedNAF", sDataSheetName))
				
				//Save and verify error is not displayed
				.clickSaveinOpportunities()
				.verifyErrorisNotDisplayed()

				//Click Back
		.clickgoBack()
		
		//Search and open the previously created Oppurtunity
		.searchOpportunity(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName)+"new "+TestUtils.todaysDatewithTime())
		.openOpportunity(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName)+"new "+TestUtils.todaysDatewithTime())
		
		//Updated the Topic
		.enterTopic(DataInputProvider.getCellData_ColName(iRowNumber, "topic", sDataSheetName)+"Updated")
		
		//Updated the est close Date
		.typeEstimatedCloseDate(TestUtils.FutureEndDate(3))
		
		//update Anticipated Purchase DAte
		.typeanticipatedPurchaseStarDate(TestUtils.todaysDate())
		
		//Updated the Category
		.clearRevenueCategory()
		.selectRevenueCategory(DataInputProvider.getCellData_ColName(iRowNumber, "category1", sDataSheetName))
		
		//Update NAF
		.typeProjectedNAF(DataInputProvider.getCellData_ColName(iRowNumber, "projectedNAF", sDataSheetName))
		
		//save the update
		.clickSaveinOpportunities()
		.verifyErrorisNotDisplayed()
		
		//Assign the opportunity to other user
		.assignToUser(false, DataInputProvider.getCellData_ColName(iRowNumber, "user", sDataSheetName))
		.verifygeneralErrormessageisNotDisplayed()
		
		.clickgoBack()
		
		//SEarch the opportunity created by the other user
		.searchOpportunity(DataInputProvider.getCellData_ColName(iRowNumber, "ExistingTopic", sDataSheetName))
		.openOpportunity(DataInputProvider.getCellData_ColName(iRowNumber, "ExistingTopic", sDataSheetName))
		
		//Assign the opportunity to self
		.assignToUser(true, DataInputProvider.getCellData_ColName(iRowNumber, "user", sDataSheetName))
		
		//Assign the opportunity to other
				.assignToUser(false, DataInputProvider.getCellData_ColName(iRowNumber, "user", sDataSheetName))
		.verifygeneralErrormessageisNotDisplayed()
		
		
		;					
	}
}
