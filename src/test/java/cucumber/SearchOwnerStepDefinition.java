package cucumber;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchOwnerStepDefinition {

	WebDriver driver;
	WebElement searchbox;

	@Given("Launch the web site")
	public void launch_the_web_site() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://localhost:8088/");
	}

	@And("Click find owners button")
	public void click_find_owners_button() {
		driver.findElement(By.xpath("//*[@id=\'main-navbar\']/ul/li[2]/a/span[2]")).click();
	}

	@And("Hit the search box")
	public void hit_the_search_box() {
		searchbox = driver.findElement(By.xpath("//div/input[@class ='form-control']"));
		searchbox.click();
	}

	@When("Type {string} in the search box")
	public void type_in_the_search_box(String string) {
		searchbox.sendKeys(string);
	}

	@And("Click find owner button")
	public void click_find_owner_button() {
		driver.findElement(By.xpath("//*[@id=\"search-owner-form\"]/div[2]/div/button")).submit();
	}

	@Then("We should see the {string} message")
	public void we_should_see_the_message(String string) {
		String error = driver.findElement(By.xpath("//*[@id=\"lastNameGroup\"]/div/span/div/p")).getText();
		System.out.println(error);
		assertEquals(error, string);
	}

}
