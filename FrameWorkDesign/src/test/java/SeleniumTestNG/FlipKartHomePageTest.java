package SeleniumTestNG;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import AbstractComponents.AbstractComponent;
import PageObjects.HomeNavigationPageObjects;
import PageObjects.LandingPage;
import Test.Uitilities.WebUtilis;
import TestUtility.BaseTest;
import TestUtility.TestWebDriverUtility;

public class FlipKartHomePageTest extends BaseTest{
	WebDriverWait wait;	
	LandingPage landingPage;
	
	@BeforeTest
	public void setUp() throws IOException {
		landingPage = launchApplication();
		
	}
	
	@Test
	public void testHomePage() throws IOException {		
		
		landingPage.searchItem("MensShirt");
		landingPage.selectFirstItem();
		landingPage.swtichToWindow();
		String shirtTitle = landingPage.getShirtTitle();
		System.out.println(shirtTitle);		
	}


}
