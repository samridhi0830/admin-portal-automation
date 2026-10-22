package factory;

import java.util.HashMap;
import java.util.Properties;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class optionsManager {
	
	
	private Properties prop;
	ChromeOptions co;
	FirefoxOptions fo;
	EdgeOptions eo;
	public optionsManager(Properties prop) {
		this.prop=prop;
		
	}
	
	
	public ChromeOptions getChromeOptions() {
		co=new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		 // Download Directory Configuration
	   // String downloadPath =
	          //  System.getProperty("user.dir") + "\\Downloads";

	    String downloadPath= "/Users/toffee/eclipse-workspace/Admin_portal_automation/qa-admin-portal/Downloads";
	    HashMap<String, Object> prefs = new HashMap<>();

	    prefs.put("download.default_directory", downloadPath);
	    prefs.put("download.prompt_for_download", false);
	    prefs.put("safebrowsing.enabled", true);

	    co.setExperimentalOption("prefs", prefs);

		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) fo.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) fo.addArguments("--incognito");
		 // Download Directory Configuration
	    String downloadPath =
	        "/Users/toffee/eclipse-workspace/Admin_portal_automation/qa-admin-portal/Downloads";

	    fo.addPreference("browser.download.folderList", 2);
	    fo.addPreference("browser.download.dir", downloadPath);

	    // Download CSV and other files without prompting
	    fo.addPreference("browser.download.useDownloadDir", true);
	    fo.addPreference("browser.download.alwaysOpenPanel", false);

	    // Download CSV files automatically
	    fo.addPreference(
	        "browser.helperApps.neverAsk.saveToDisk",
	        "text/csv,application/csv,application/octet-stream"
	    );

	    // Do not open downloaded files in Firefox
	    fo.addPreference("pdfjs.disabled", true);
		return fo;}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) eo.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) eo.addArguments("--inPrivate");
		return eo;
	}

}
