package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commonActions.BrowserActions;
import commonActions.EmailReport;
import commonActions.ExtentReportsUtility;
import commonActions.TestDataFunctions;
import commonActions.TestTemplate;
import commonActions.ZipReport;
import pages.NewFPPage;
import pages.ReportsPage;

public class NewFPTest extends ExtentReportsUtility {
	
	public NewFPPage newfp;
	public BrowserActions browse;
	public ReportsPage report;
	public EmailReport email;
	public ZipReport zip;
	static TestDataFunctions data;
	public static String newPlanSheetName;
	public static String editPlanSheetName;
	public static String createFinancialPlanSheetName;
	static String testfile = System.getProperty("user.dir")+"\\src\\test\\resources\\test-input.xlsx";
	
	public NewFPTest(){
		newPlanSheetName = "New_plan";
		editPlanSheetName = "Edit_plan";
		createFinancialPlanSheetName="create_planner";
	}
	
	@BeforeClass
	public void beforeClass(){
		browse  = new BrowserActions();
		newfp = new NewFPPage(browse);
		zip = new ZipReport();
 		email = new EmailReport();
 		data = new TestDataFunctions();
 		browse.openBrowserInstance();
		newfp.goToEnvironment();
		}
	
	@BeforeMethod
	public void loginToApplication(){
		BrowserActions.s_assert = new SoftAssert();
		newfp.FPLogin();
		//newfp.FPLoginCreateFiancialPlanner();
	}
	
	@AfterMethod
	public void logoutOfApplication(){
		newfp.logoutFromDemo();
	}
	
	@Test(priority=0, dataProvider="newPlanData", dataProviderClass=NewFPTest.class)
	public void createNewPlan(String EmailID, String primaryName, String Number, String month, String year, 
            String value, String retirement, String expectancy, String currentCode, String code, 
            String spouseName, String spouseNo, String spouseMonth, String spouseYear, String spouseHealth,
            String spouseExpectancy){
		newfp.createNewPlan(EmailID, primaryName, Number, month, year, value, retirement, expectancy, currentCode, code,
				            spouseName,spouseNo ,spouseMonth ,spouseYear ,spouseHealth, spouseExpectancy);
		BrowserActions.s_assert.assertAll();
	}
	
	@Test(priority=1, dataProvider="editPlan", dataProviderClass=NewFPTest.class)
	public void editPlanTest(String email, String retirement){
		newfp.editPlan(email, retirement);
		BrowserActions.s_assert.assertAll();
	}
	
	@Test(priority=2, dataProvider="newPlanData", dataProviderClass=NewFPTest.class)
	public void deletePlanTest(String EmailID, String primaryName, String Number, String month, String year, 
            String value, String retirement, String expectancy, String currentCode, String code, 
            String spouseName, String spouseNo, String spouseMonth, String spouseYear, String spouseHealth,
            String spouseExpectancy){
		newfp.deletePlan(EmailID);
		BrowserActions.s_assert.assertAll();
	} 
	
	
	@Test(priority=3)
	public void helpFileTest() {
		newfp.helpFile();
		BrowserActions.s_assert.assertAll();
	}
	
	@Test(priority=4)
	public void addNewContentMail(){
		newfp.addNewContentMail();
		BrowserActions.s_assert.assertAll();
	}
	
	@Test(priority=5)
	public void comparePlanTest(){
		newfp.comparePlan();
		BrowserActions.s_assert.assertAll();
	}
	
	@Test(priority=6)
	public void HealthcareVideo(){
		newfp.HealthcareVideo();
		BrowserActions.s_assert.assertAll();
	}
	
	@Test(priority=7)
	public void financialAdvicePdf(){
		newfp.financialAdvicePdf();
		BrowserActions.s_assert.assertAll();
	}	
	
	@Test(priority=8)
	public void medicareBundlesTest(){
		newfp.medicareBundles();
		BrowserActions.s_assert.assertAll();
	}

	@Test(priority=9)
	public void fundWithInvestmentsTest(){
		newfp.fundWithInvestments();
		BrowserActions.s_assert.assertAll();
	}

	@Test(priority=10)
	public void ltcProjectionsTest(){
		newfp.goToLtcProjections();
		BrowserActions.s_assert.assertAll();
	}
	
	@Test(priority=11)
	public void viewPdfTest(){
		newfp.viewPdf();
		BrowserActions.s_assert.assertAll();
	}

	@Test(priority=12)
	public void personalInformationTest(){
		newfp.personalInformation();
		BrowserActions.s_assert.assertAll();
	}

	@Test(priority=13)
	public void changePasswordTest(){
		newfp.changePasswordTest();
		BrowserActions.s_assert.assertAll();
	}

	@DataProvider(name="newPlanData")
	public static Object[][] newPlanData(){
		return data.readAllExcel(testfile, newPlanSheetName);
	}
	
	@DataProvider(name="editPlan")
	public static Object[][] editPlan(){
		return data.readAllExcel(testfile, editPlanSheetName);
	}
	
	@DataProvider(name="financialplanner")
	public static Object[][] createFinancialPlanner(){
		return data.readAllExcel(testfile, createFinancialPlanSheetName);
	}
	
	/*
	@Test(priority=0 , dataProvider="financialplanner", dataProviderClass=NewFPTest.class)
	public void createFinancialPlannerTest(String name, String title, String email, String org, String pwd, String address, String number, 
			  String city, String fax, String zip, String country){
		newfp.createFinancialPlanner( name,  title,  email,  org,  pwd,  address,  number, 
				   city,  fax,  zip,  country );
		BrowserActions.s_assert.assertAll();
	}
	*/
	
	@AfterClass
	 public void stopTest(){
		 browse.quitDriver();
	 }
	
//	@AfterSuite
//	public void sendReport(){
//		String latestReportPath = zip.zipTestReport();
//		email.sendMail(latestReportPath);
//	 }
}
