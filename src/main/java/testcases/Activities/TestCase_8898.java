package testcases.Activities;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8898:Cloud : Add a recurring appointment

public class TestCase_8898 {


	@Test
	public void RecurringAppointmentActivity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//From the left navigation column ,Go to Accounts > +New
		.selectAccountsTab()

		.searchAccount(DataInputProvider.getCellData_ColName(iRowNumber, "CrmNumber", sDataSheetName))
		.selectAccountFromSearchResults()

		//Click Activities Tab
		.selectRelatedActivities()

		//Select Open Activities View
		.selectOpenActivitiesView()

		//Click New Appointment Activity
		.clickAppointmentActivity()

		//Verify Auto populated Feilds
		.verfiyAutopopulatedFieldsAppointment()



		//Enter the details in the new tasks window
		.EnterRecurringAppointmentDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))
		//.clickGoBackTaskPage()

		//Select Open Activities View and verify the status
		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Scheduled")

		//Data reset
		.completeAllTask()

		;
	}
}
