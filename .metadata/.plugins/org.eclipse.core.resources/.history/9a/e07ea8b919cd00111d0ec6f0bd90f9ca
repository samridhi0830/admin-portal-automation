package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseTest;
import utilities.LoggerUtil;

public class PlanPageTest extends BaseTest {
	@BeforeClass
	public void dashageSetup() throws InterruptedException {
		System.out.println("Current URL before login: " + driver.getCurrentUrl());
	    dashPage = loginPage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
	    }
	
	@DataProvider(name = "dateSelectFilter")
	public Object[][] getPlanData() {
	    return new Object[][] {
	    	{"Today"},
	    	{"Yesterday"},
	    	{"This Week"},
	    	{"This Month"},
	    	{"Last Week"},
	    	{"Last Month"},
	    	{"This FY 26-27"},
	    	{"Last FY 25-26"}
	    };
	    }
	
	
	@Test(dataProvider="dateSelectFilter")
	public void allPlanCountTest(String date) throws InterruptedException, IOException {
		LoggerUtil.info("For "+date);
		dashPage.selectDateFilter(date);
		int planSoldOnHomePageCard=dashPage.planSold();
		planPage=dashPage.moveToPlansPage();
		Thread.sleep(3000);
		int planCountInListing=planPage.getPlanCount();
		LoggerUtil.info("Total plans on listing = "+planCountInListing);
		planPage.clearDownloadFolder();
		planPage.clickExportBtn(date);
		if(date.equals("This FY 26-27") || date.equals("Last FY 25-26")) {
			Thread.sleep(10000);
		}
		Thread.sleep(5000);
		int PlanCountInExport= planPage.getPlanCountFromCSV();
		LoggerUtil.info("Total plans in export =" +PlanCountInExport);
		Thread.sleep(8000);
		planPage.returnToDashboardHomePage();
		Boolean flag;
		if(planSoldOnHomePageCard==planCountInListing && PlanCountInExport==planSoldOnHomePageCard) {
			flag=true;
		}
		else {
			flag=false;
		}
		Assert.assertTrue(flag, "Not same");
	
	}
	

	

}
