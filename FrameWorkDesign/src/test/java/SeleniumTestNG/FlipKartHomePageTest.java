package SeleniumTestNG;

import java.io.IOException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.LandingPage;
import TestUtility.BaseTest;


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
