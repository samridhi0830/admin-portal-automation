package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.allClaimsPage;
import factory.AdminDriverFactory;

public class ClaimsPageTest extends BaseTest {
    private allClaimsPage claimsPage;

    @BeforeClass
    public void openClaimsPage() throws InterruptedException {
        dashPage = loginPage.doLogin(
            prop.getProperty("email"),
            prop.getProperty("password")
        );
        claimsPage = dashPage.movetoAllClaimsPage();
    }

    @Test(description = "Claims > All Claims navigation opens a claims URL")
    public void allClaimsNavigationTest() {
        Assert.assertTrue(claimsPage.isAllClaimsPageLoaded(),
            "Claims navigation did not open a URL containing 'claims'.");
    }

    @Test(dependsOnMethods = "allClaimsNavigationTest",
          description = "All Claims listing table is displayed")
    public void claimsListingVisibleTest() {
        Assert.assertTrue(claimsPage.isClaimsTableVisible(),
            "All Claims listing table was not visible.");
    }

    @Test(dependsOnMethods = "claimsListingVisibleTest",
          description = "All Claims listing contains headers")
    public void claimsTableHeadersTest() {
        Assert.assertFalse(claimsPage.getColumnHeaders().isEmpty(),
            "All Claims table should display column headers.");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        AdminDriverFactory.quitDriver();
    }
}
