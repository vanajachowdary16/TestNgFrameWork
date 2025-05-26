package SeleniumTestNG;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {
	public static void main(String[] args) {
		
		 String ZaraProduct="ZARA COAT 3";
		
		
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://rahulshettyacademy.com/client/login");
	driver.findElement(By.id("userEmail")).sendKeys("vanajachowdary1166@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Vanaja@12345");
	driver.findElement(By.id("login")).click();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	//products.stream().filter(p->p.getText().equals("ZARA COAT 3"));
	WebElement prod =products.stream().filter(p->p.findElement(By.cssSelector("b")).getText().equals(ZaraProduct)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	driver.findElement(By.cssSelector("[routerlink*='cart'")).click();
	List<WebElement>cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	boolean match=cartProducts.stream().filter(product->product.getText().equalsIgnoreCase(ZaraProduct)) != null;
	Assert.assertTrue(match);
}
}
