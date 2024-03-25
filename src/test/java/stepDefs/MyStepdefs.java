package stepDefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {

    WebDriver driver;


    @Given("the user is using {string} as a browser")
    public void theUserIsUsingAsABrowser(String browser) {
        if (browser.equals("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }

    }

    @And("the user is on the registration page")
    public void theUserIsOnTheRegistrationPage() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }


    @When("the user registers with the following details")
    public void theUserRegistersWithTheFollowingDetails(DataTable table) {
        Map<String, String> formdata = table.asMap(String.class, String.class);

        Map<String, String> labelForAttributes = new HashMap<>();
        labelForAttributes.put("TermsAccept", "sign_up_25");
        labelForAttributes.put("AgeAccept", "sign_up_26");
        labelForAttributes.put("AgreeToCodeOfEthicsAndConduct", "fanmembersignup_agreetocodeofethicsandconduct");

        for (Map.Entry<String, String> field : formdata.entrySet()) {
            WebElement input;
            if (labelForAttributes.containsKey(field.getKey())) {

                WebElement label = driver.findElement(By.cssSelector("label[for='" + labelForAttributes.get(field.getKey()) + "']"));
                boolean shouldSelect = Boolean.parseBoolean(field.getValue());
                if (shouldSelect) {
                    label.click();
                }
            } else {
                input = driver.findElement(By.name(field.getKey()));
                input.sendKeys(field.getValue());
            }

        }

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.bold.gray.text-center.margin-bottom-40")));
    }


    @Then("the registration should be {string}")
    public void theRegistrationShouldBe(String Outcome) {
        List<WebElement> validationElements = driver.findElements(By.cssSelector("span.warning.field-validation-error"));
        if (validationElements.isEmpty()) {
            WebElement messageElement = driver.findElement(By.cssSelector("h2.bold.gray.text-center.margin-bottom-40"));
            String actual = messageElement.getText();
            String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
            if (Outcome.equals("successful")) {
                Assert.assertEquals(expected, actual);
            } else {
                Assert.fail("Test failed. Registration was successful, but it was expected to fail.");
            }
        } else {
            if (Outcome.equals("unsuccessful")) {
                Assert.assertTrue("Test passed. Registration failed as expected.", true);
            } else {
                Assert.fail("Test failed. Registration failed, but it was expected to be successful.");
            }
        }
    }
}
