
	package testcases.BulkImport;
	import org.testng.annotations.Test;
	import utils.DataInputProvider;
import utils.TestUtils;

	public class TestCase_3333 {
		
//Create supplier with all available fields		
		@Test()

		public void CreateSupplierSheet(int iRowNumber, String sDataSheetName) throws Exception {
			
			new DataInputProvider()
			.setCellDataBulkImport("DES"+TestUtils.getDate(),"Account Name",System.getProperty("user.dir")+"\\data\\DES\\"+DataInputProvider.getCellData_ColName(iRowNumber, "FileName", sDataSheetName)+".xlsx","Sheet1");
			new DataInputProvider()
			.setCellDataBulkImport("www.test.com","Website",System.getProperty("user.dir")+"\\data\\DES\\"+DataInputProvider.getCellData_ColName(iRowNumber, "FileName", sDataSheetName)+".xlsx","Sheet1");
			new DataInputProvider()
			.setCellDataBulkImport(TestUtils.getRandomNumberString(),"Federal Tax ID",System.getProperty("user.dir")+"\\data\\DES\\"+DataInputProvider.getCellData_ColName(iRowNumber, "FileName", sDataSheetName)+".xlsx","Sheet1");
			new DataInputProvider()
			.setCellDataBulkImport("DES_Test_lname","First Name",System.getProperty("user.dir")+"\\data\\DES\\"+DataInputProvider.getCellData_ColName(iRowNumber, "FileName", sDataSheetName)+".xlsx","Sheet1");	
			new DataInputProvider()
			.setCellDataBulkImport("DES_Test_fname","Last name",System.getProperty("user.dir")+"\\data\\DES\\"+DataInputProvider.getCellData_ColName(iRowNumber, "FileName", sDataSheetName)+".xlsx","Sheet1");	
			new DataInputProvider()
			.setCellDataBulkImport("DES_Test_fname","Last name",System.getProperty("user.dir")+"\\data\\DES\\"+DataInputProvider.getCellData_ColName(iRowNumber, "FileName", sDataSheetName)+".xlsx","Sheet1");	
			new DataInputProvider()
			.setCellDataBulkImport("DES@gmail.com","Email address",System.getProperty("user.dir")+"\\data\\DES\\"+DataInputProvider.getCellData_ColName(iRowNumber, "FileName", sDataSheetName)+".xlsx","Sheet1");	
	  	
			;
		}
}
