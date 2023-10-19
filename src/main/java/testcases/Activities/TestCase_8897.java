package testcases.Activities;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 8897:Cloud : Add a new appointment

public class TestCase_8897 {


	@Test
	public void AppointmentActivity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//Login to CRM using member supervisor / member credentials 
		new LoginPage()

		.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
		.clickNext()
		.typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
		.clicSignin()

		.clicYesInStaySignedin()

		//. From the left navigation column ,Go to Accounts > +New
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
		.EnterAppointmentDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName),DataInputProvider.getCellData_ColName(iRowNumber, "TaskDetails", sDataSheetName))

		//Select Open Activities View and complete the appointment

		.selectOpenActivitiesViewAfterTask()
		.verifyTasksCompletionstatus("Open")
		.openAppointment()
		.verifyTasksPage()
		.clickMarkComplete()
		.selectAllActivity()
		.verifyTasksCompletionstatus("Completed")

		//Data reset
		.completeAllTask()
		;
	}
}
