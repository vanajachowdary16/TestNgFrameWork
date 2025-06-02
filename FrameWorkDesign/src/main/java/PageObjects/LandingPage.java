package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractComponent;


public class LandingPage  extends AbstractComponent{
	
	public static String url="https://www.flipkart.com/";
	public static String loginDropdownXpath="//span[text()='Login']";
	public static String loginTextXpath ="//span[text()='Login']";
	public static String searchMensShirt="MensShirt";
	protected static WebDriver driver;
	WebDriverWait wait;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css="._27h2j1")
	WebElement fashionDropDown;
	
	@FindBy(css="._1BJVlg._11MZbx")
	WebElement mensBottomWear;
	
   @FindBy(css="div[class='_31z7R_'] a:nth-child(2)")
   WebElement mensJeans;
   
   WebElement loginDropDown = driver.findElement(By.xpath(loginDropdownXpath));
   WebElement profileOption = driver.findElement(By.cssSelector(".wsejfv"));
   WebElement loginText= driver.findElement(By.xpath(loginTextXpath));
   WebElement searchBar = driver.findElement(By.cssSelector(".Pke_EE")); 
   WebElement selectMensShirt = driver.findElement(By.cssSelector(".WKTcLC"));
   WebElement titleOfShirt = driver.findElement(By.cssSelector(".VU-ZEz"));
   String shirtTitle = titleOfShirt.getText();
   
   //System.out.println(shirtTitle);

	WebElement mensItem = driver.findElement(By.cssSelector("a[class='WKTcLC'[1]]"));
	public static void goTo()
	{
		driver.get(url);
	}

}
