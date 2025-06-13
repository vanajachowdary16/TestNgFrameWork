package AbstractComponents;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	public final static String agrstyle = "arguments[0].style.border='3px solid red'";
	public final static String agrClick ="argument[0].click()";
	public final static String agrsStyleEmpty = "arguments[0].style.broder=''";
	public final static String agrsClick="argument[0].click();";
	
	protected WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
   
   public void waitForElementToAppear(By element) {
	   
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	   wait.until(ExpectedConditions.visibilityOfElementLocated(element));	   
   }
   public void watiForWebElementToAppear(WebElement findBy) {
	   
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	   wait.until(ExpectedConditions.visibilityOf(findBy));
   }
   
   public void presentOfElementToAppear(WebElement locator) {
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	   wait.until(ExpectedConditions.visibilityOf(locator));
   }
   
   public void waitForElementTobeClickable(WebElement element) {
	   
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	   wait.until(ExpectedConditions.elementToBeClickable(element));
	   
   }
  /* public static void highlight(WebDriver driver, WebElement element) {
		JavascriptExecutor js =(JavascriptExecutor)driver;
		try {
			
			js.executeScript(agrstyle, element);
			Thread.sleep(2000);
		}catch(Exception e){
			js.executeScript(agrstyle, element);
			
		}*/


}
