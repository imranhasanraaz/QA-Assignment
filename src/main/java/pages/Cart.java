package pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import data_access_object.Cart_DAO;
import utils.WebActions;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Cart {
    private Page page;
    private final Locator subTotal;
    private final Locator totals;
    private final Locator quantityOfDarkBrownJeans;
    private final Locator updateCart;
    private final Locator contentUpdatedMessage;
    private final Locator couponField;
    private final Locator applyCoupon;
    private final Locator checkout;
    private final Locator fullTable;
    private final Locator negativeMsg;
    public Cart(Page page){
        this.page = page;
        this.negativeMsg = page.locator("//ul[@class='woocommerce-error']");
        this.subTotal = page.locator("//tr[@class='cart-subtotal']//bdi[1]");
        this.totals = page.locator("//tr[@class='order-total']//bdi[1]");
        this.quantityOfDarkBrownJeans = page.locator("//input[@name='cart[0bb4aec1710521c12ee76289d9440817][qty]']");
        this.updateCart = page.locator("//button[@name='update_cart']");
        this.couponField = page.locator("//input[@id='coupon_code']");
        this.contentUpdatedMessage = page.locator("//div[@class='woocommerce-message']");
        this.checkout = page.locator("//div[@class='wc-proceed-to-checkout']");
        this.applyCoupon = page.locator("//button[@name='apply_coupon']");
        this.fullTable = page.locator("//form[@class='woocommerce-cart-form']");
    }

    public List<Cart_DAO> getTableData() {
        List<Cart_DAO> cartList = new ArrayList<>();
        WebActions.waitUntilElementDisplayed(fullTable, 5);
        try {
            Document doc = Jsoup.parse(page.content());
            Elements rows = doc.select("tr.woocommerce-cart-form__cart-item");

            for (Element row : rows) {
                String productName = row.select("td.product-name[data-title='Product']").text();
                String productPrice = row.select("td.product-price[data-title='Price']").text();
                String productQuantity = row.select("td.product-quantity[data-title='Quantity'] input").attr("value");
                String productSubtotal = row.select("td.product-subtotal[data-title='Subtotal']").text();

                Cart_DAO cartDAO = new Cart_DAO();
                cartDAO.setProduct(productName);
                cartDAO.setPrice(productPrice);
                cartDAO.setQuantity(productQuantity);
                cartDAO.setSubtotal(productSubtotal);
                cartList.add(cartDAO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartList;
    }
    public String getSubTotals(){
        subTotal.scrollIntoViewIfNeeded();
        return subTotal.innerText();
    }
    public String getTotals(){
        return totals.innerText();
    }
    public void setQuantityOfDarkBrownJeans(String quantity){
        quantityOfDarkBrownJeans.clear();
        quantityOfDarkBrownJeans.fill(quantity);
    }
    public void clickedOnUpdatedCart()   {
        updateCart.click();
    }
    public Boolean isContentUpdateMessageVisible(){
        WebActions.waitUntilElementDisplayed(contentUpdatedMessage, 5);
       return contentUpdatedMessage.isVisible();
    }
    public void clickedOnCheckout(){
        WebActions.waitUntilElementDisplayed(checkout, 5000);
        checkout.click();
    }
    public void enterCoupon(String coupon){
        couponField.fill(coupon);
    }
    public void clickedOnApplyCoupon(){
        applyCoupon.click();
    }
    public String getCouponUpdatedMessage(){
        page.waitForTimeout(3000);
        return  negativeMsg.innerText();
    }
}