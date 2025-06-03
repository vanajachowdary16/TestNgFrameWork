package TestUtility;

import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestWebDriverUtility {

	public final static String PROJECT_PATH = System.getProperty("user.dir") + "/";
	public final static String webDriverPathChrome = PROJECT_PATH + "/drivers/chromedriver.exe";
	public final static long SynchronizationTime = 10;
	public static String chrome = "chrome";
	
	public static WebDriver driver;
	public static WebDriver getDriver() {
		return driver;
	}
	WebDriverWait wait;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(PROJECT_PATH + "//src//main//java//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", webDriverPathChrome);
			System.out.println(webDriverPathChrome);
			// WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriver driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public void goTo() {
		driver.get("https://www.flipkart.com/");
	}
	@AfterMethod
	public static void tearDown() {
		 try {
			if (driver != null) {
			        driver.quit();
}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void handleAutosuggestDropDown(String xPath, String searchInput){
		List<WebElement> dropOptions= driver.findElements(By.xpath(xPath));
		try {
		for(WebElement option : dropOptions) {
			if(option.getText().equalsIgnoreCase(searchInput)) {
				//WebUtilis.JsClick(driver, option);				
				option.click();
				System.out.println(option.getAttribute("innerText"));
				break;
			}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		goTo();
		return landingPage;
	}

	public WebDriver launchDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fts = new FileInputStream(
				System.getProperty("user.dir") + "/FrameWorkDesign/resources/GlobalData.properties");
		prop.load(fts);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				options.addArguments("--headless");
				driver = new ChromeDriver(options);

			}
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions edOptions = new EdgeOptions();
			edOptions.addArguments("--start-maximized");
			Map<String, Object> prefs = new LinkedHashMap();
			prefs.put("user_experience_metrics.personalization_data_consent_enabled", Boolean.valueOf(true));
			edOptions.setExperimentalOption("prefs", prefs);
			driver = new EdgeDriver(edOptions);
		}
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// String to HashMap - jackson databind

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

}
