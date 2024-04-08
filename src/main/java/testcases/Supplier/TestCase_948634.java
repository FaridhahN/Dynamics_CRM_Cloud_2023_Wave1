
	package testcases.Supplier;
	import org.testng.annotations.Test;
	import pages.LoginPage;
	import utils.DataInputProvider;

	public class TestCase_948634 {
		
//TFS ID_948634:_948634_:Verify 'Contract Effective Date' is available between 'Premier End Date' and 'Business Classification' fields on Supplier Form
		
		@Test()

		public void createSupplierWithAllFields(int iRowNumber, String sDataSheetName) throws Exception {
			
			new LoginPage()
			.typeEmail(DataInputProvider.getCellData_ColName(iRowNumber, "email", sDataSheetName))
			.clickNext()
	  	    .typePassword(DataInputProvider.getCellData_ColName(iRowNumber, "password", sDataSheetName))  
	  	    .clicSignin()
	  	    .clicYesInStaySignedin()
	  	   
	  	    //Select Accounts Entity
			.selectAccountsTab()
			
			//Click on +New ( goes to Accounts Page)
			.clickNewOnAccountsPage()
			
			//Choose 'Supplier Form' Option
			.chooseSupplierForm()
			
			.navigateToPrimaryContact()
			
			//VErify Contract Effective Date
			.verifyContractEffectiveDate()
			;
		}
}
