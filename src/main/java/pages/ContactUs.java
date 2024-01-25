package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ContactUs {
    private Page page;
    private final Locator name;
    private final Locator subject;
    private final Locator email;
    private final Locator message;
    private final Locator sendMessage;
    private final Locator messageConfirmation;

    public ContactUs(Page page){
        this.name = page.locator("//input[@id='wpforms-858-field_0']");
        this.subject = page.locator("//input[@id='wpforms-858-field_5']");
        this.email = page.locator("//input[@id='wpforms-858-field_4']");
        this.message = page.locator("//textarea[@id='wpforms-858-field_2']");
        this.sendMessage = page.locator("//button[@id='wpforms-submit-858']");
        this.messageConfirmation = page.locator("//*[@id='wpforms-confirmation-858']/p");
    }

    public void setEmail(String keyword){
        email.fill(keyword);
    }
    public void setName(String keyword){
        name.fill(keyword);
    }
    public void setSubject(String keyword){
        subject.fill(keyword);
    }
    public void setMessage(String keyword){
        message.fill(keyword);
    }
    public void clickedOnSendMessage(){
        sendMessage.click();
    }
    public String getConfirmationMessage(){
        return messageConfirmation.innerText();
    }
}
