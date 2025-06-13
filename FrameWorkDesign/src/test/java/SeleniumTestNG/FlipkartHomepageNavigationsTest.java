package SeleniumTestNG;

import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.HomeNavigationPageObjects;
import PageObjects.LandingPage;
import TestUtility.TestWebDriverUtility;

public class FlipkartHomepageNavigationsTest extends TestWebDriverUtility{
	
	WebDriverWait wait;	
	LandingPage landingPage;
	HomeNavigationPageObjects homenavigations;
	
	@BeforeTest
	public void setUp() throws IOException {
		landingPage = launchApplication();
		homenavigations = new HomeNavigationPageObjects(driver);
		
	}
	@Test
	public void testNavigations() {
		homenavigations.clickAllDropdowns();	
	}

}
