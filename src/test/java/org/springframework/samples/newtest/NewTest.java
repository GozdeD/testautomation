package org.springframework.samples.newtest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewTest {

	WebDriver driver;
	WebElement box;
	String currUrl;
	String text;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();

		/*ChromeOptions options = new ChromeOptions();
		options.addArguments("--allow-insecure-localhost");
		options.addArguments("acceptInsecureCerts");
		options.addArguments("--ignore-certificate-errors"); */

		/*options.addArguments("--disable-notifications");
		options.addArguments("disable-infobars"); */ 

		//driver = new ChromeDriver(options);
		
		driver = new ChromeDriver();

		//driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("http://localhost:8088/");
	}

	@Test
	public void homepage() {
		assertTrue(driver.findElement(By.cssSelector("img.img-responsive")).isDisplayed());
		assertEquals(driver.findElement(By.cssSelector("div.xd-container")).getText(), "Welcome");
		driver.findElement(By.cssSelector("span.glyphicon-home")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		currUrl = driver.getCurrentUrl();
		assertEquals(currUrl, "http://localhost:8088/");
	}
	
	@Test
	public void veterinarians() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector("span.glyphicon-th-list")).click();
		currUrl = driver.getCurrentUrl();
		assertEquals(currUrl, "http://localhost:8088/vets.html");
		
		text = driver.findElement(By.xpath("/html/body/div/div/h2")).getText();
		assertEquals(text, "Veterinarians");
		
		driver.findElement(By.cssSelector("a.glyphicon-triangle-right")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		currUrl = driver.getCurrentUrl();
		assertEquals(currUrl, "http://localhost:8088/vets.html/?page=2");
		
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		assertEquals(driver.getCurrentUrl(), "http://localhost:8088/vets.html");
		
		driver.findElement(By.linkText("2")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		text = driver.findElement(By.xpath("/html/body/div/div/h2")).getText();
		assertEquals(text, "Something happened...");
		
	}
	
	public void errorPage() {
		driver.findElement(By.className("glyphicon-warning-sign")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		assertEquals(driver.getCurrentUrl(), "http://localhost:8088/oups");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		assertNotNull(driver.findElement(By.cssSelector("img[src='/resources/images/pets.png']")));
	}
	
	public void findOwnersMain() {
		box = driver.findElement(By.cssSelector("input.form-control"));
		box.sendKeys("asdef");
		driver.findElement(By.className("btn-default")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		assertNotNull(driver.findElement(By.xpath("//*[@id=\"lastNameGroup\"]/div/span/div/p")));
		text = driver.findElement(By.xpath("//*[@id=\"lastNameGroup\"]/div/span/div/p")).getText();
		assertEquals(text, "has not been found");
		
	}
	
	
	
	@AfterClass
	public void close() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		driver.close();
	}

}
