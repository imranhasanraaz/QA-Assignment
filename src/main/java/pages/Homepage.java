package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.WebActions;

public class Homepage {
    private final Locator findMore;
    private final Locator enterEmail;
    private final Locator subscribe;
    private final Locator subscriptionConfirmation;
    private final Locator everything;
    private Page page;
    private final Locator contactUs;

    public Homepage(Page page) {
        this.page = page;
        this.findMore = page.locator("//span[contains(text(),'Find More')]");
        this.enterEmail = page.locator("//input[@id='wpforms-4299-field_2']");
        this.subscribe = page.locator("//button[@id='wpforms-submit-4299']");
        this.subscriptionConfirmation = page.locator("//div[@id='wpforms-confirmation-4299']/p");
        this.everything = page.locator("//li[@id='menu-item-45']/a[@class='menu-link']");
        this.contactUs = page.locator("//li[@id='menu-item-3917']//a[@class='menu-link']");
    }

    public void navigateToUrl() {
        this.page.navigate(WebActions.getProperty("url"));

    }

    public boolean isHomepageOpen() {
        return findMore.isVisible();
    }

    public void scrollDownToSubscriptionArea() {
        enterEmail.scrollIntoViewIfNeeded();
    }

    public void clickedOnSubscribe() {
        subscribe.click();
    }

    public String getSubscriptionConfirmationMessage() {
        return subscriptionConfirmation.innerText();
    }

    public void enterEmail(String keyword) {
        enterEmail.fill(keyword);
    }

    public void scrollIntoHeader() {
        everything.scrollIntoViewIfNeeded();
    }

    public String getEverythingButtonLink() {
        return everything.getAttribute("href");
    }

    public void clickedOnEverything() {
        everything.click();
    }
    public void clickedOnContactUs(){
        contactUs.click();
    }
}
