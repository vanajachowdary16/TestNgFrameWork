package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
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
	
	public HomeNavigationPageObjects(WebDriver driver) {		
		super(driver);
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="._27h2j1")
	WebElement fashionDropDown;
	
	public void clickFashionDropdown() {
		Actions action = new Actions(driver);
		 action.moveToElement(fashionDropDown).click().perform();
	}
	
}
