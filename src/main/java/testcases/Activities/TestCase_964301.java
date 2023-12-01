package testcases.Activities;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_964301: Verify in direct 'Activities' whether Task - 'Subject' field should not get concatenated with Entity code and Supplier Account Name for Supplier Accounts.  

public class TestCase_964301 {


	@Test()

	public void VerifyActivitesinSupplier(int iRowNumber, String sDataSheetName) throws Exception {

		//Access Login Page


		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()


		.navigateToActivitiesOption()
		.createTaskUnderActivities()
		.verifySubjectFieldisNull()
		.selectRagrding( DataInputProvider.getCellData_ColName(iRowNumber, "verifyEntityCode", sDataSheetName))
		.EnterTaskDetailsWithSave( DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) ,
				DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))




		;

	}


}