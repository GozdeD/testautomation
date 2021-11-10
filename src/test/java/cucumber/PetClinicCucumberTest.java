package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/features/petClinicCucumber" })
public class PetClinicCucumberTest extends AbstractTestNGCucumberTests{

}
