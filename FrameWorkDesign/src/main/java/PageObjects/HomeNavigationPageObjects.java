package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractComponent;

public class HomeNavigationPageObjects extends AbstractComponent{
	
	public WebDriver driver;
	WebDriverWait wait;
	Actions action;
	public HomeNavigationPageObjects(WebDriver driver) {		
		super(driver);
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		this.action = new Actions(driver);
		
	}
	
	@FindBy(css = "._1wE2Px")
	List<WebElement> topMenuDropdowns;
	
	
	public void clickAllDropdowns() {
	    topMenuDropdowns.stream()
	        .filter(dropdown -> dropdown.isDisplayed())
	        .forEach(dropdown -> {	            
	            action.moveToElement(dropdown).click().perform();
	            System.out.println("Clicking dropdown: " + dropdown.getText());
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	        });
	}


	
	
}
