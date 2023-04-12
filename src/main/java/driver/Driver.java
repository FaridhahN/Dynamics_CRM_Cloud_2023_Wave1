package driver;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import base.PreAndPost;
import utils.DataInputProvider;

public class Driver extends PreAndPost{

	public static int iTestCaseRowNum;
	public static String sTestCaseID;
	public static Properties properties = new Properties();
	public static String sRunMode;
	public static String sCategory;
	public static Method[] aMethod;
	public static int iTestCaseRowNumDriver;
	public static String sReleaseCategory;
	public static int failCount=0;
	public static int totalRowCount;
	public static int currentCount;

	@Test()
	public void executeTestcase(ITestContext testContext)throws Exception,ClassNotFoundException
	{	

		failCount=0;
		properties.load(new FileInputStream(new File("./src/test/resources/environment.properties")));
		int currentCount = testContext.getAllTestMethods()[0].getCurrentInvocationCount();	
		//Read test case id and runmode from Driver sheet
		sRunMode=DataInputProvider.getCellData_ColName(currentCount+1, properties.getProperty("ColRunMode"),properties.getProperty("DriverSheetName"));

		if(sRunMode.equalsIgnoreCase("yes")) {

			sTestCaseID=DataInputProvider.getCellData_ColName(currentCount+1, properties.getProperty("ColTestCaseName"),properties.getProperty("DriverSheetName"));
			sRunMode=DataInputProvider.getCellData_ColName(currentCount+1, properties.getProperty("ColRunMode"),properties.getProperty("DriverSheetName"));
			iTestCaseRowNumDriver=DataInputProvider.getRowNum(properties.getProperty("DriverSheetName"), sTestCaseID);
			sCategory=DataInputProvider.getCellData_ColName(currentCount+1, properties.getProperty("ColCategory"),properties.getProperty("DriverSheetName"));
			sReleaseCategory=DataInputProvider.getCellData_ColName(currentCount+1, "ReleaseCategory",properties.getProperty("DriverSheetName"));

			//Get the row number of the test case which is marked as yes from actual category sheet 
			iTestCaseRowNum=DataInputProvider.getRowNum(sCategory, sTestCaseID);

			String name[]= sTestCaseID.split(":");
			String sTClassName[]=name[0].split("_");
			String sTestClassName="TestCase_".concat(sTClassName[1].trim());
			//Execute the test cases which are marked as yes in run mode column in driver sheet
			if (sCategory.equalsIgnoreCase("Member"))
			{
				sTestClassName="testcases.Member.".concat(sTestClassName);
			}
			else if(sCategory.equalsIgnoreCase("Supplier"))
			{
				sTestClassName="testcases.Supplier.".concat(sTestClassName);
			}	
			else if(sCategory.equalsIgnoreCase("Contact"))
			{
				sTestClassName="testcases.Contacts.".concat(sTestClassName);
			}else if(sCategory.equalsIgnoreCase("LOBFSC"))
			{
				sTestClassName="testcases.LOBFSC.".concat(sTestClassName);
			}else if(sCategory.equalsIgnoreCase("ConvertingAccounts"))
			{
				sTestClassName="testcases.ConvertingAccounts.".concat(sTestClassName);
			}else if(sCategory.equalsIgnoreCase("Pipeline"))
			{
				sTestClassName="testcases.Pipeline.".concat(sTestClassName);
			}else if(sCategory.equalsIgnoreCase("CAMSFlag"))
			{
				sTestClassName="testcases.CAMSFlag.".concat(sTestClassName);
			}else if(sCategory.equalsIgnoreCase("BusinessKey"))
			{
				sTestClassName="testcases.BusinessKey.".concat(sTestClassName);
			}else if(sCategory.equalsIgnoreCase("BusinessClassification"))
			{
				sTestClassName="testcases.BusinessClassification.".concat(sTestClassName);
			}else if(sCategory.equalsIgnoreCase("MemberFormChanges"))
			{
				sTestClassName="testcases.MemberFormChanges.".concat(sTestClassName);
			}else if(sCategory.equalsIgnoreCase("Premier_StartDate"))
			{
				sTestClassName="testcases.PremierStartDate.".concat(sTestClassName);

			}else if(sCategory.equalsIgnoreCase("Activities"))
			{
				sTestClassName="testcases.Activities.".concat(sTestClassName);

			}else if(sCategory.equalsIgnoreCase("DoNotVerifyAddress"))
			{
				sTestClassName="testcases.DoNotVerifyAddress.".concat(sTestClassName);

			}else if(sCategory.equalsIgnoreCase("ExcludeFromRoaster"))
			{
				sTestClassName="testcases.ExcludeFromRoaster.".concat(sTestClassName);

			}else if(sCategory.equalsIgnoreCase("RebatePayment"))
			{
				sTestClassName="testcases.RebatePayment.".concat(sTestClassName);

			}else if(sCategory.equalsIgnoreCase("PrescriptionData"))
			{
				sTestClassName="testcases.PrescriptionData.".concat(sTestClassName);

			}else if(sCategory.equalsIgnoreCase("PatientServices"))
			{
				sTestClassName="testcases.PatientServices.".concat(sTestClassName);

			}else if(sCategory.equalsIgnoreCase("SupplierAccountNumber"))
			{
				sTestClassName="testcases.SupplierAccountNumber.".concat(sTestClassName);

			}else if(sCategory.equalsIgnoreCase("MemberAttribute"))
			{
				sTestClassName="testcases.MemberAttribute.".concat(sTestClassName);

			}	
			else if(sCategory.equalsIgnoreCase("Account_Status"))
			{
				sTestClassName="testcases.AccountStatus.".concat(sTestClassName);

			}				





			//Create instance of the class during run time
			Class<?> cls = Class.forName(sTestClassName);
			Object clsInstance = (Object) cls.getDeclaredConstructor().newInstance();
			test = extent.createTest(sTestCaseID);
			test.assignCategory(sCategory);
			test.assignCategory(sReleaseCategory);
			if (sRunMode.equalsIgnoreCase("Yes"))
			{	
				// Get all methods of the class
				aMethod=clsInstance.getClass().getDeclaredMethods();
				beforeMethod();
				aMethod[0].invoke(clsInstance,iTestCaseRowNum,sCategory);
				closeAllBrowsers();			
			}
			else
			{
				throw new SkipException("Skipping execution for  :"+sTestCaseID);
				
			}

		}

	}




@AfterMethod
public void Reports(ITestResult result) throws Exception
{
	if(sRunMode.equalsIgnoreCase("yes")) {
		try
		{
			String sTimeStamp=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
			if(result.getStatus() == ITestResult.FAILURE)
			{
				System.out.println(sTestCaseID + " is FAILED ");
				DataInputProvider.setCellData("Failed", iTestCaseRowNumDriver, "Result",Driver.properties.getProperty("DriverSheetName"));
				DataInputProvider.setCellData(sTimeStamp, iTestCaseRowNumDriver, "TimeStamp",Driver.properties.getProperty("DriverSheetName"));
				test.log(Status.FAIL, MarkupHelper.createLabel(sTestCaseID+" FAILED due to below issues:", ExtentColor.RED));
				test.fail(result.getThrowable());
			}	
			else if(result.getStatus() == ITestResult.SKIP)
			{
				System.out.println("SKIPPING .. " + sTestCaseID );
				DataInputProvider.setCellData("No Run", iTestCaseRowNumDriver, "Result",Driver.properties.getProperty("DriverSheetName"));
				DataInputProvider.setCellData("", iTestCaseRowNumDriver, "TimeStamp",Driver.properties.getProperty("DriverSheetName"));			
				test.log(Status.SKIP, MarkupHelper.createLabel(sTestCaseID+" SKIPPED", ExtentColor.YELLOW));
				test.skip(result.getThrowable());
			}
			else if(result.getStatus() == ITestResult.SUCCESS)
			{ System.out.println("Completed execution for " + sTestCaseID + result.getStatus());
			if (failCount==0 )
			{	
				DataInputProvider.setCellData("PASSED", iTestCaseRowNumDriver, "Result",Driver.properties.getProperty("DriverSheetName"));
				DataInputProvider.setCellData(sTimeStamp, iTestCaseRowNumDriver, "TimeStamp",Driver.properties.getProperty("DriverSheetName"));
				test.log(Status.PASS, MarkupHelper.createLabel(sTestCaseID+" PASSED", ExtentColor.GREEN));
			}
			else
			{
				DataInputProvider.setCellData("Failed", iTestCaseRowNumDriver, "Result",Driver.properties.getProperty("DriverSheetName"));
				DataInputProvider.setCellData(sTimeStamp, iTestCaseRowNumDriver, "TimeStamp",Driver.properties.getProperty("DriverSheetName"));
				test.log(Status.FAIL, MarkupHelper.createLabel(sTestCaseID+" FAILED :", ExtentColor.RED));
			}
			}
			else
			{   
//			System.out.println("After method SKIPPING .. " + sTestCaseID );  	
//			DataInputProvider.setCellData("No Run", iTestCaseRowNumDriver, "Result",Driver.properties.getProperty("DriverSheetName"));
//			DataInputProvider.setCellData("", iTestCaseRowNumDriver, "TimeStamp",Driver.properties.getProperty("DriverSheetName"));			
//			test.log(Status.SKIP, MarkupHelper.createLabel(sTestCaseID+" SKIPPED", ExtentColor.ORANGE));
//			test.skip(result.getThrowable());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
}
