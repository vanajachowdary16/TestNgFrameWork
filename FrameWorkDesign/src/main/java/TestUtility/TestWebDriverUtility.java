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
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestWebDriverUtility {
	
	public final static String PROJECT_PATH = System.getProperty("user.dir") + "/" ;
	public final static String webDriverPathChrome = PROJECT_PATH + "/drivers/chromedriver.exe";
	public final static String browser = "chrome";
	public final static long SynchronizationTime = 45;
	public final static String agrstyle = "arguments[0].style.border='3px solid red'";
	public final static String agrClick ="argument[0].click()";
	public final static String agrsStyleEmpty = "arguments[0].style.broder=''";
	public final static String agrsClick="argument[0].click();";
	
	public WebDriver driver;
	WebDriverWait wait;
	public LandingPage landingpage;
	
	public TestWebDriverUtility(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
		
	}
	public WebDriver launchDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fts= new FileInputStream(System.getProperty("user.dir")+
				"/FrameWorkDesign/resources/GlobalData.properties");
		prop.load(fts);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
				
			}
		driver = new ChromeDriver();
			
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver","");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			EdgeOptions edOptions = new EdgeOptions();
			edOptions.addArguments("--start-maximized");
			Map<String, Object> prefs= new LinkedHashMap();
			prefs.put("user_experience_metrics.personalization_data_consent_enabled", Boolean.valueOf(true));
			edOptions.setExperimentalOption("prefs", prefs);
			driver = new EdgeDriver(edOptions);
		}
		return driver;		
		
	}
	public List<HashMap<String, String>>  getJsonDataToMap(String filePath) throws IOException{
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// String to HashMap - jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
				{});
		return data;
		
	}
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+ ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+ ".png";
	}
	public LandingPage launchApplication() throws IOException {
		driver = launchDriver();
		if(driver!=null) {
			landingpage = new LandingPage(driver);
			landingpage.goTo();
			return landingpage;
		}
		return null;
		
	}
	public void tearDown() {
		driver.quit();
	}

}
