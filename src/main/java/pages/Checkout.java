package pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
public class Checkout {
    private Page page;
    private final Locator cashOnDelivery;
    private final Locator firstName;
    private final Locator lastName;
    private final Locator country;
    private final Locator comboBoxSearch;
    private final Locator street;
    private final Locator city;
    private final Locator state;
    private final Locator PIN;
    private final Locator phone;
    private final Locator email;
    private final Locator orderNotes;
    private final Locator placeOrder;
    private final Locator totals;
    private final Locator paymentMethod;
    public Checkout(Page page){
        this.page = page;
        this.cashOnDelivery =page.locator("//input[@id='payment_method_cod']");
        this.firstName = page.locator("//input[@id='billing_first_name']");
        this.lastName = page.locator("//input[@id='billing_last_name']");
        this.country = page.locator("//span[@id='select2-billing_country-container']");
        this.street = page.locator("//input[@id='billing_address_1']");
        this.city = page.locator("//input[@id='billing_city']");
        this.state = page.locator("//span[@id='select2-billing_state-container']");
        this.PIN = page.locator("//input[@id='billing_postcode']");
        this.phone = page.locator("//input[@id='billing_phone']");
        this.email = page.locator("//input[@id='billing_email']");
        this.orderNotes = page.locator("//textarea[@id='order_comments']");
        this.placeOrder = page.locator("//button[@id='place_order']");
        this.comboBoxSearch = page.locator("//input[@role='combobox']");
        this.paymentMethod = page.locator("//strong[normalize-space()='Cash on delivery']");
        this.totals = page.locator("//ul[@class='woocommerce-order-overview woocommerce-thankyou-order-details order_details']//bdi[1]");
    }
    public void setFirstName(String keyword){
        firstName.fill(keyword);

    }
    public void setLastName(String keyword){
        lastName.fill(keyword);
    }
    public void setCountry(String keyword){
        country.click();
        comboBoxSearch.fill(keyword);
        page.keyboard().press("ArrowDown");
        page.keyboard().press("Enter");
    }
    public void setStreet(String keyword){
        street.fill(keyword);
    }
    public void setCity(String keyword){
        city.fill(keyword);
    }
    public void setState(String keyword){
        state.click();
        comboBoxSearch.fill(keyword);
        page.keyboard().press("ArrowDown");
        page.keyboard().press("Enter");
    }
    public void setPIN(String keyword){
        PIN.fill(keyword);
    }
    public void setPhone(String keyword){
        phone.fill(keyword);
    }
    public void setEmail(String keyword){
        email.fill(keyword);
    }
    public void setOrderNotes(String keyword){
        orderNotes.fill(keyword);
    }
    public void clickedOnPlaceOrder(){
        placeOrder.click();
    }

    public void clickedOnCashOnDelivery(){
        cashOnDelivery.click();
    }
    public String getTotals(){
        return totals.innerText();
    }
    public String getPaymentMethod(){
        return paymentMethod.innerText();
    }
    public String getNote(){
        return page.innerText(".woocommerce-table tfoot tr:nth-child(4) td");
    }
}
