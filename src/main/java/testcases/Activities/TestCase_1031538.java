package testcases.Activities;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataInputProvider;

//TFS ID_964301: Verify in direct 'Activities' whether Task - 'Subject' field should not get concatenated with Entity code and Supplier Account Name for Supplier Accounts.  

public class TestCase_1031538 {


	@Test()

	public void VerifyActivitesinSupplier(int iRowNumber, String sDataSheetName) throws Exception {

		//Access Login Page


		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()
		.clicYesInStaySignedin()

		.navigateToActivitiesTab()
		.createTaskUnderActivities()
		.selectInformationview()
		.verifySubjectFieldisNull()
		.selectRagrding( DataInputProvider.getCellData_ColName(iRowNumber, "verifyEntityCode", sDataSheetName))
		.EnterTaskDetails( DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), 
				DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName),
				DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName) ,
				DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		//Select Open Activities View and completed the activity
				.verifyTasksCompletionstatus("Open")
				.openTasks()
				.verifyTasksPage()
				.clickMarkComplete()
			
				//Data reset
				.completeAllTask()



		;

	}


}