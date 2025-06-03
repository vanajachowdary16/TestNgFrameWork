package TestUtility;

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

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException

	{
		// properties class

		 Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		//prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")){
			options.addArguments("--headless");		
			driver = new ChromeDriver(options);
			}
			driver = new ChromeDriver();
			//driver.manage().window().setSize(new Dimension(1440,900));//full screen

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"/Users/rahulshetty//documents//geckodriver");
			driver = new FirefoxDriver();
			// Firefox
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge
			EdgeOptions options = new EdgeOptions();			
			options.addArguments("--start-maximized");
//			options.setCapability("ms:edgeOptions", "{ \"excludeSwitches\": [\"enable-automation\"] }");
		    //options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
			Map<String, Object> prefs = new LinkedHashMap<>();
			prefs.put("user_experience_metrics.personalization_data_consent_enabled", Boolean.valueOf(true));
			options.setExperimentalOption("prefs", prefs);
			driver = new EdgeDriver(options);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
	  String jsonContent = 	FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);	
	   //String to HashMap- Jackson Databind	
	  ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;	
	//{map, map}
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";			
	}
	public LandingPage launchApplication() throws IOException
	{	
//		driver = initializeDriver();
//		landingPage = new LandingPage(driver);
//		landingPage.goTo();
//		return landingPage;	
		    driver = initializeDriver();
		    if (driver != null) {
		        landingPage = new LandingPage(driver);
		        landingPage.goTo();
		        return landingPage;
		    }
		    // Handle the case when driver initialization fails
		    return null;
	}
	
	@AfterMethod(alwaysRun=true)	
	public void tearDown()
	{
//		if (driver != null) {
	        driver.quit();
//	    }
	}
}
