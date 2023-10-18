package testcases.Member;

import org.testng.annotations.Test;

import pages.LoginPage;
import services.WebDriverServiceImpl;
import utils.DataInputProvider;
//TFS ID_11200:_30983_Cloud: Verify Prospect accounts can not be published

public class TestCase_11200 {


	@Test
	public void publishProspect(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//2. From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()

		.clickNewOnAccountsPage()
		.chooseMemberForm()

		//3. Account Name = Any
		.typeAccountName(DataInputProvider.getCellData_ColName(iRowNumber, "accountName", sDataSheetName))

		//Click on save 			
		.clickSave() 

		//4. Verify CRM Account # is generated 
		.verifyCRMNumberIsDisplayed()	

		//Class of Trade =Any
		.selectClassOfTrade(DataInputProvider.getCellData_ColName(iRowNumber, "classOfTrade", sDataSheetName))

		//Business Classification = Auto populated
		.verifyBusinessClassification(DataInputProvider.getCellData_ColName(iRowNumber, "verifyBusinessClassification", sDataSheetName))

		//Account Status = Auto Populated to Active
		.verifyDefaultAccountStatus()	

		//Application Start Date = Today's Date
		.chooseApplicationDate(DataInputProvider.getCellData_ColName(iRowNumber, "applicationDate", sDataSheetName))

		//CAMS Flag = Yes
		.changeCAMSFlagAsYes()

		//Participation Type = Standard
		.selectParticipationType(DataInputProvider.getCellData_ColName(iRowNumber, "participationType", sDataSheetName))


		//Direct Parent Entity Code = 673415
		.selectDirectParent(DataInputProvider.getCellData_ColName(iRowNumber, "directParent", sDataSheetName))

		//Direct Parent Relation = Managed
		.selectDirectParentRelationManaged() 

		//Direct Parent Relation date = Today's Date
		.selectDirectParentRelationDate(DataInputProvider.getCellData_ColName(iRowNumber, "directParentRelationDate", sDataSheetName))

		//Top Parent Relation =  OLM
		.selectTopParentRelation(DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelation", sDataSheetName))

		// Top Parent Relation Date = Today's Date
		.selectTopParentRelationDate( DataInputProvider.getCellData_ColName(iRowNumber, "topParentRelationDate", sDataSheetName))

		//Click on Save 
		.clickSave() 

		//6. Street 1 = Any
		.typeStreet1(DataInputProvider.getCellData_ColName(iRowNumber, "street1", sDataSheetName))


		//Country =USA
		.typeCountry(DataInputProvider.getCellData_ColName(iRowNumber, "country", sDataSheetName))

		//City = NY
		.typeCity(DataInputProvider.getCellData_ColName(iRowNumber, "city", sDataSheetName))


		//Type Zip code
		.typeZipCode(DataInputProvider.getCellData_ColName(iRowNumber, "ZipCode", sDataSheetName))

		
		.chooseRecordStatusPublished()
		
		//Click on Save 
				.clickSave() 
		.verifyError(DataInputProvider.getCellData_ColName(iRowNumber, "ErrorMessage", sDataSheetName));

		}
}
