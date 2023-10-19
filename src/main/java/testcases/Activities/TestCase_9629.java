package testcases.Activities;

import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
//Test Case 9629:Verify whether user should not get any script error, on add /update "Phone" call activity.

public class TestCase_9629 {


	@Test
	public void UpdatePhoneActivity(int iRowNumber, String sDataSheetName) throws Exception, InterruptedException  {

		//1. Login to CRM using member supervisor / member credentials 
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

		
		//Click New Fax Activity
		.clickNewPhoneActivity()

		//Verify Autom populated Feilds
		.verfiyAutopopulatedFieldsFax()

		
		//Enter the details in the new tasks window
		.EnterFPhoneDetails(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Due Date", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Duration", sDataSheetName), DataInputProvider.getCellData_ColName(iRowNumber, "Phonenumber", sDataSheetName))
		//.clickGoBackTaskPage()

		//Select Open Activities View
		.selectOpenActivitiesViewAfterTask()

		//Verify the task status is open
		.verifyTasksCompletionstatus("Open")

		//Verify the Subject
		.verifySubject(DataInputProvider.getCellData_ColName(iRowNumber, "Subject", sDataSheetName))

		//Edit the phone call task
		.openPhoneCall()

		//Edit the phone task details
		.EditPhoneDetails("Edit the subject")
	
		//Verify the subject is edited
		.verifySubject("Edit the subject")
		
		//Data reset
		.completeAllTask()
		

		;
	}
}
