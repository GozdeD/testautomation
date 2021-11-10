package cucumber;

import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddOwnerStepDefinition {

	WebDriver driver;
	WebElement box1;

	@Given("Launch web site")
	public void launch_web_site() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://localhost:8088/");
	}

	@And("Click the find owners button")
	public void click_the_find_owners_button() {
		driver.findElement(By.xpath("//*[@id=\'main-navbar\']/ul/li[2]/a/span[2]")).click();
	}

	@And("Hit the {string} button")
	public void hit_the_button(String string) {
		driver.findElement(By.linkText(string)).click();
	}

	@When("Type following data in the box by map")
	public void type_data_in_the_box_by_map(DataTable table) {

		List<Map<String, String>> rows = table.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {
			driver.findElement(By.id(columns.get("box"))).sendKeys(columns.get("data"));
		}

	}

	@When("Click {string} button")
	public void click_button(String string) {
		driver.findElement(By.className("btn-default")).click();
	}

	@Then("Owner table should be visible")
	public void owner_table_should_be_visible() {
		box1 = driver.findElement(By.cssSelector("table.table-striped"));
		assertNotNull(box1);
	}
	
	/*@After
	public void close() {
		driver.close();
	} */

}
