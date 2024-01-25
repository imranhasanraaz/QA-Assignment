package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ContactUs;
import pages.Homepage;
import utils.JsonUtils;

public class ContactUsSteps {
    ContactUs contactUs = new ContactUs(DriverFactory.getPage());
    Homepage homepage = new Homepage(DriverFactory.getPage());
    JsonUtils testData = new JsonUtils("testdata.json");

    @When("clicked on contact us")
    public void clicked_on_contact_us() {
        homepage.clickedOnContactUs();
    }
    @When("Fill all the information and clicked Send message")
    public void fill_all_the_information_and_clicked_send_message() {
        contactUs.setName(testData.getValue("/contactUs/name").toString());
        contactUs.setSubject(testData.getValue("/contactUs/subject").toString());
        contactUs.setEmail(testData.getValue("/contactUs/email").toString());
        contactUs.setMessage(testData.getValue("/contactUs/message").toString());

        contactUs.clickedOnSendMessage();
    }
    @Then("Validated confirmation message")
    public void validated_confirmation_message() {
        Assert.assertEquals(contactUs.getConfirmationMessage(), testData.getValue("/contactUs/confirmationMessage").toString(), "Confirmation message does not matched");
    }

}
