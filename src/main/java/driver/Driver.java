package driver;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
				System.out.println("Executing : " + sTestCaseID);
			}
			else if(sCategory.equalsIgnoreCase("Supplier"))
			{
				sTestClassName="testcases.Supplier.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}	
			else if(sCategory.equalsIgnoreCase("Contact"))
			{
				sTestClassName="testcases.Contacts.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("LOBFSC"))
			{
				sTestClassName="testcases.LOBFSC.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("ConvertingAccounts"))
			{
				sTestClassName="testcases.ConvertingAccounts.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("Pipeline"))
			{
				sTestClassName="testcases.Pipeline.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("CAMSFlag"))
			{
				sTestClassName="testcases.CAMSFlag.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("BusinessKey"))
			{
				sTestClassName="testcases.BusinessKey.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("BusinessClassification"))
			{
				sTestClassName="testcases.BusinessClassification.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("MemberFormChanges"))
			{
				sTestClassName="testcases.MemberFormChanges.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("Premier_StartDate"))
			{
				sTestClassName="testcases.PremierStartDate.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}else if(sCategory.equalsIgnoreCase("Activities"))
			{
				sTestClassName="testcases.Activities.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}else if(sCategory.equalsIgnoreCase("DoNotVerifyAddress"))
			{
				sTestClassName="testcases.DoNotVerifyAddress.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}else if(sCategory.equalsIgnoreCase("ExcludeFromRoster"))
			{
				sTestClassName="testcases.ExcludeFromRoster.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}else if(sCategory.equalsIgnoreCase("RebatePayment"))
			{
				sTestClassName="testcases.RebatePayment.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}else if(sCategory.equalsIgnoreCase("PrescriptionData"))
			{
				sTestClassName="testcases.PrescriptionData.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}else if(sCategory.equalsIgnoreCase("PatientServices"))
			{
				sTestClassName="testcases.PatientServices.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}else if(sCategory.equalsIgnoreCase("SupplierAccountNumber"))
			{
				sTestClassName="testcases.SupplierAccountNumber.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}else if(sCategory.equalsIgnoreCase("MemberAttribute"))
			{
				sTestClassName="testcases.MemberAttribute.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}	
			else if(sCategory.equalsIgnoreCase("Account_Status"))
			{
				sTestClassName="testcases.AccountStatus.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}				

			else if(sCategory.equalsIgnoreCase("MembershipProvider"))
			{
				sTestClassName="testcases.MembershipProvider.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}

			else if(sCategory.equalsIgnoreCase("BaseReadOnly"))
			{
				sTestClassName="testcases.BaseReadOnly.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}

			else if(sCategory.equalsIgnoreCase("AccountNumbers"))
			{
				sTestClassName="testcases.AccountNumbers.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}

			else if(sCategory.equalsIgnoreCase("MemberEntryForm"))
			{
				sTestClassName="testcases.MemberEntryForm.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}
			else if(sCategory.equalsIgnoreCase("LimitedMember"))
			{
				sTestClassName="testcases.LimitedMember.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}

			else if(sCategory.equalsIgnoreCase("AGRules"))
			{
				sTestClassName="testcases.AGRules.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}
			else if(sCategory.equalsIgnoreCase("DateValidations"))
			{
				sTestClassName="testcases.DateValidations.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}else if(sCategory.equalsIgnoreCase("AccountNumbersFedID"))
			{
				sTestClassName="testcases.AccountNumbersFedID.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}else if(sCategory.equalsIgnoreCase("AccountNumbersRemitra")) {
				sTestClassName="testcases.AccountNumbersRemitra.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}else if(sCategory.equalsIgnoreCase("NonGPO")) {
				sTestClassName="testcases.NonGPO.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}
			else if(sCategory.equalsIgnoreCase("BrandingApprovals")) {
				sTestClassName="testcases.BrandingApprovals.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}
			else if(sCategory.equalsIgnoreCase("AccountNumbersGLN")) {
				sTestClassName="testcases.AccountNumbersGLN.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}
			else if(sCategory.equalsIgnoreCase("Competitors")) {
				sTestClassName="testcases.Competitors.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}
			else if(sCategory.equalsIgnoreCase("Membership")) {
				sTestClassName="testcases.Membership.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}	else if(sCategory.equalsIgnoreCase("AccountNumbersDUNS")) {
				sTestClassName="testcases.AccountNumbersDUNS.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}	
			else if(sCategory.equalsIgnoreCase("ContractAttachmentSupplier")) {
				sTestClassName="testcases.ContractAttachmentSupplier.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}	
			else if(sCategory.equalsIgnoreCase("ContractAttachment")) {
				sTestClassName="testcases.ContractAttachment.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}	
			else if(sCategory.equalsIgnoreCase("MemberformsRelatedmenu")) {
				sTestClassName="testcases.MemberformsRelatedmenu.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}

			else if(sCategory.equalsIgnoreCase("AccountRanking")) {
				sTestClassName="testcases.AccountRanking.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("SecurityMember")) {
				sTestClassName="testcases.SecurityMember.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("SecurityMemberSupervisor")) {
				sTestClassName="testcases.SecurityMemberSupervisor.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("SecuritySupplier")) {
				sTestClassName="testcases.SecuritySupplier.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("SecuritySupplierSupervisor")) {
				sTestClassName="testcases.SecuritySupplierSupervisor.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("SecurityInnovatixUser")) {
				sTestClassName="testcases.SecurityInnovatixUser.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("SecurityLimitedMember")) {
				sTestClassName="testcases.SecurityLimitedMember.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("Accounts")) {
				sTestClassName="testcases.Accounts.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("SecurityBaseReadonly")) {
				sTestClassName="testcases.SecurityBaseReadonly.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("SecurityInnovatixSupervisor")) {
				sTestClassName="testcases.SecurityInnovatixSupervisor.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("MembershipCategories")) {
				sTestClassName="testcases.MembershipCategories.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("PremierPromise")) {
				sTestClassName="testcases.PremierPromise.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("COT")) {
				sTestClassName="testcases.COT.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("Contracts")) {
				sTestClassName="testcases.Contracts.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("DSHMembership")) {
				sTestClassName="testcases.DSHMembership.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("ProgramCategories")) {
				sTestClassName="testcases.ProgramCategories.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("ProgramMembership")) {
				sTestClassName="testcases.ProgramMembership.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);

			}else if(sCategory.equalsIgnoreCase("BuisnessPartner")) {
				sTestClassName="testcases.BuisnessPartner.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("PriceActivation")) {
				sTestClassName="testcases.PriceActivation.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
			}else if(sCategory.equalsIgnoreCase("ContractVolume")) {
				sTestClassName="testcases.ContractVolume.".concat(sTestClassName);
				System.out.println("Executing : " + sTestCaseID);
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
