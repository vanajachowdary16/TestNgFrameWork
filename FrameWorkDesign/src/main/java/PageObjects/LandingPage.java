package PageObjects;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractComponent;


public class LandingPage  extends AbstractComponent{
	
	public static String url="https://www.flipkart.com/";
	public static String loginDropdownXpath="//span[text()='Login']";
	public static String loginTextXpath ="//span[text()='Login']";
	protected static WebDriver driver;
	WebDriverWait wait;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css="._27h2j1")
	WebElement fashionDropDown;
	
	@FindBy(css="._1BJVlg._11MZbx")
	WebElement mensBottomWear;
	
   @FindBy(css="div[class='_31z7R_'] a:nth-child(2)")
   WebElement mensJeans;
   @FindBy(css = ".wsejfv")
   WebElement profileOption;
  
   @FindBy(xpath = "//span[text()='Login']")
   WebElement loginDropDown;
   
   @FindBy(xpath = "//span[text()='Login']")
   WebElement loginText;
   
   @FindBy(css = ".Pke_EE")
   WebElement searchBar;
   
   @FindBy(xpath = "(//a[@target='_blank'])[1]")
   WebElement selectMensShirt;
  
   By titleOfShirt = By.xpath("//span[@class='VU-ZEz']");
 
   
   //System.out.println(shirtTitle);

   @FindBy(css = "a[class='WKTcLC']")
   WebElement mensItem;
   
	public void goTo()
	{
		driver.get("https://www.flipkart.com/");
	}
	public void searchItem(String item) {
		searchBar.sendKeys(item);
		searchBar.sendKeys(Keys.ENTER);
	}
	public void selectFirstItem() {
		presentOfElementToAppear(selectMensShirt);
		selectMensShirt.click();
	}
	  public String getShirtTitle() {
		  waitForElementToAppear(titleOfShirt);
		  WebElement element = driver.findElement(titleOfShirt);
		  return driver.findElement(titleOfShirt).getText();
		}
	  public void swtichToWindow() {
			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			String childWindow = allWindows.stream()
				    .filter(handle -> !handle.equals(parentWindow))
				    .findFirst()
				    .orElseThrow(() -> new RuntimeException("Child window not found"));
			driver.switchTo().window(childWindow);

		}
	
}
