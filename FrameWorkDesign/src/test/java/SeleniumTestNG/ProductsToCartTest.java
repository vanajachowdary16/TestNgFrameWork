package SeleniumTestNG;

import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.LandingPage;
import PageObjects.ProductsToCartPageObjects;
import TestUtility.BaseTest;

public class ProductsToCartTest extends BaseTest{
	
	
	WebDriverWait wait;	
	LandingPage landingPage;
	ProductsToCartPageObjects productsToCart;
	
	@BeforeTest
	public void setUp() throws IOException {
		String url="https://rahulshettyacademy.com/seleniumPractise/";
		landingPage = launchApplication(url);
		productsToCart = new ProductsToCartPageObjects(driver);		
	}
	
	@Test
	public void testItemsaddToCart() {
		
		String[] itemsNeeded= {"Cucumber","Brocolli","Beetroot"};		
		productsToCart.addItems(driver, itemsNeeded);
		
	}
}
