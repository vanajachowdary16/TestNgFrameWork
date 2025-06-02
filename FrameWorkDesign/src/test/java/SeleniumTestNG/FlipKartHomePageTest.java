package SeleniumTestNG;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import PageObjects.LandingPage;
import TestUtility.TestWebDriverUtility;

@Test
public class FlipKartHomePageTest extends TestWebDriverUtility{
	
	WebDriver driver;
	WebDriverWait wait;
	
	public FlipKartHomePageTest(WebDriver driver) {
		super(driver);
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);	
	}	
	
	@Test
	public void testHomePage() {
		LandingPage landingpage = new LandingPage(driver);
		
		
	}

}
