package com.automation.globan.booking.tests;

import static com.automation.globan.booking.utils.LivingDocumentation.captureScreenshot;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.remote.CapabilityType.ACCEPT_SSL_CERTS;
import static org.testng.ITestResult.FAILURE;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.globan.booking.utils.BaseWebServises;
import com.automation.globan.booking.utils.Constants;

public class BaseTest {
	public static WebDriver driver;

	protected BaseWebServises baseWeb = new BaseWebServises();

	public static FirefoxOptions firefoxOption() {
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference(Constants.FIREFOX_OPTION, false);
		return options;
	}

	public static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments(Constants.ADD_ARGUMENTS_MAXIMIZED);
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		options.setExperimentalOption(Constants.SET_EXPERIMENTAL_OPT, chromePrefs);
		chromePrefs.put(Constants.CH_PUT_SERVICES, false);
		chromePrefs.put(Constants.CH_PUT_SERVICES_DISABLE_PSSWD, false);
		options.setExperimentalOption(Constants.SWT, new String[] { Constants.ENABLE_AUTOMATION });
		options.setCapability(ACCEPT_SSL_CERTS, true);
		return options;
	}

	@BeforeSuite
	@Parameters({ Constants.BROWSER })
	public void startDriver(@Optional(Constants.CHROME_BROWSER) String browserName) {

		if (browserName.equalsIgnoreCase(Constants.CHROME_BROWSER)) {
			System.setProperty(Constants.CHROME_SET, Constants.PATH_LINUX);
			driver = new ChromeDriver(chromeOption());
		}

		else if (browserName.equalsIgnoreCase(Constants.FIREFOX_BROWSER)) {
			System.setProperty(Constants.SET_PROPERTY_GECKO,
					System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			driver = new FirefoxDriver(firefoxOption());
		}

		else if (browserName.equalsIgnoreCase(Constants.IE_BROWSER)) {
			System.setProperty(Constants.SET_PROPERTY_IE,
					System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		else if (browserName.equalsIgnoreCase(Constants.IE_SAFARI)) {
			driver = new SafariDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, SECONDS);
		driver.navigate().to(Constants.URL);

	}

	@AfterSuite
	public void stopDriver() {
		driver.quit();
	}

	@AfterMethod
	public void screenshotOnFailure(ITestResult result) {
		if (result.getStatus() == FAILURE) {
			System.out.println("Failed!");
			System.out.println("Taking Screenshot....");
			captureScreenshot(driver, result.getName());
		}
	}
	
	
	
	
	
}
