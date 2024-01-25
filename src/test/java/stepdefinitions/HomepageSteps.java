package stepdefinitions;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.Homepage;
import utils.JsonUtils;

import static utils.RandomEmail.generateRandomEmail;
import static utils.WebActions.getProperty;

public class HomepageSteps {
    JsonUtils testData = new JsonUtils("testdata.json");
    Homepage homepage = new Homepage(DriverFactory.getPage());

    @Given("User navigated to homepage URL")
    public void User_navigated_to_URL() {
        homepage.navigateToUrl();
    }

    @When("User is on homepage")
    public void user_is_on_homepage() {
        Assert.assertTrue(homepage.isHomepageOpen(), "Homepage is not open");
    }

    @Then("Validated homepage titles")
    public void Validated_homepage_titles() {
        Assert.assertEquals(DriverFactory.getPage().title(), testData.getValue("/homepage_title").toString(), "Homepage title is not matched");
    }

    @Then("Validated homepage URLs")
    public void Validated_homepage_URLs() {
        Assert.assertEquals(DriverFactory.getPage().url(), getProperty("url"), "Homepage urls doesn't matched");
    }

    @When("User scroll down to the footer of the page")
    public void user_scroll_down_to_the_footer_of_the_page() {
        homepage.scrollDownToSubscriptionArea();
    }

    @When("User input email")
    public void user_input_email() {
        homepage.enterEmail(generateRandomEmail());
    }

    @When("User Clicked on subscribed button")
    public void user_clicked_on_subscribed_button() {
        homepage.clickedOnSubscribe();
    }

    @Then("Validated successfully subscription message")
    public void Validated_successfully_subscription_message() {
        Assert.assertEquals(homepage.getSubscriptionConfirmationMessage(), testData.getValue("/successful_subscription_message").toString(), "Subscription confirmation message doesn't matched");
    }

    @Then("User Clicked on Everything from menu in a new tab")
    public void User_Clicked_on_Everything_from_menu() {
        BrowserContext bx1 = DriverFactory.getContext();
        Page page1 = bx1.newPage();
        page1.navigate(homepage.getEverythingButtonLink());
        DriverFactory.setNewTab(page1);
    }
}