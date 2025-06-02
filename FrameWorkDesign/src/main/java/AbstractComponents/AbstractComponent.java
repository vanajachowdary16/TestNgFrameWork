package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	protected WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
   
   public void waitForElementToAppear(By findBy) {
	   
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	   wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));	   
   }
   public void watiForWebElementToAppear(WebElement findBy) {
	   
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	   wait.until(ExpectedConditions.visibilityOf(findBy));
   }
   
   public void presentOfElementToAppear(WebElement locator) {
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	   wait.until(ExpectedConditions.visibilityOf(locator));
   }
   

}
