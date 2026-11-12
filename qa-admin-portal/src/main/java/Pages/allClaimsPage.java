package Pages;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page object for Admin Portal > Claims > All Claims.
 *
 * The Ant Design table selectors below are initial candidates based on
 * the existing All Plans page. Confirm them against the authenticated UI
 * before adding filter, search, action, and workflow tests.
 */
public class allClaimsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By claimsTable = By.cssSelector(".ant-table");
    private final By tableHeaders = By.cssSelector(".ant-table-thead th");
    private final By visibleClaimRows = By.cssSelector(".ant-table-tbody tr.ant-table-row");

    public allClaimsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isAllClaimsPageLoaded() {
        return wait.until(webDriver ->
            webDriver.getCurrentUrl().toLowerCase(Locale.ROOT).contains("claims")
        );
    }

    public boolean isClaimsTableVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(claimsTable))
                   .isDisplayed();
    }

    public List<String> getColumnHeaders() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(claimsTable));
        return driver.findElements(tableHeaders).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(header -> !header.isEmpty())
                .toList();
    }

    /** Number of data rows on the currently displayed page, not total paginated claims. */
    public int getVisibleClaimRowCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(claimsTable));
        return driver.findElements(visibleClaimRows).size();
    }
}
