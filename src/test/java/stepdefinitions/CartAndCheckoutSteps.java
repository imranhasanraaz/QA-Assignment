package stepdefinitions;

import data_access_object.Cart_DAO;
import factory.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.Cart;
import pages.Checkout;
import pages.Everything;
import pages.Homepage;
import utils.JsonUtils;
import utils.WebActions;

import java.util.ArrayList;
import java.util.List;

public class CartAndCheckoutSteps {
    JsonUtils testData = new JsonUtils("testdata.json");
    Everything everything = new Everything(DriverFactory.getPage());
    Homepage homepage = new Homepage(DriverFactory.getPage());
    Cart cart = new Cart(DriverFactory.getPage());
    Checkout checkout = new Checkout(DriverFactory.getPage());

    @When("User clicked on Black Hoodie")
    public void user_clicked_on_black_hoodie() {
        everything.clickedOnBlackHoodie();
    }

    @Then("Validated Black hoodie URL, Price, Name and Description")
    public void validated_black_hoodie_url_price_name_and_description() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(DriverFactory.getPage().url(), testData.getValue("/blackHoodie/productURL").toString(), "products url doesn't match");
        softAssert.assertEquals(everything.getProductTitle(), testData.getValue("/blackHoodie/productName").toString(), "Product title does not matched");
        softAssert.assertEquals(everything.getPrice(), testData.getValue("/blackHoodie/productPrice").toString(), "Product title does not matched");
        softAssert.assertEquals(everything.getProductDescription(), testData.getValue("/blackHoodie/productDescription").toString(), "Product title does not matched");
        softAssert.assertAll();
    }

    @When("User clicked on Everything")
    public void User_clicked_on_Everything() {
        homepage.clickedOnEverything();
    }

    @Then("Validated added cart positive response")
    public void Validated_added_cart_positive_response() {
        Assert.assertTrue(everything.getAddedCartMessage().contains(testData.getValue("/blackHoodie/productName").toString()), "Cart response does not contain Black Hoodie");
    }

    @When("User clicked on View Cart")
    public void User_clicked_on_View_Cart() {
        everything.clickedOnViewCart();
    }

    @Then("Validated cart has Black Hoodie and Dark Brown Jeans's Name, Price, Quantity and Subtotal")
    public void validated_cart_has_products_name_price_quantity_and_subtotal() {cart.getTableData();
        List<Cart_DAO> actualCartList = cart.getTableData();
        List<Cart_DAO> expectedList = new ArrayList<>();

        Cart_DAO darkBrownJeans = new Cart_DAO();
        darkBrownJeans.setProduct(testData.getValue("/particularProduct/productName").toString());
        darkBrownJeans.setPrice(testData.getValue("/particularProduct/productPrice").toString());
        darkBrownJeans.setQuantity(testData.getValue("/particularProduct/checkoutQuantity").toString());
        darkBrownJeans.setSubtotal(testData.getValue("/particularProduct/subtotal").toString());
        expectedList.add(darkBrownJeans);

        Cart_DAO blackHoodie = new Cart_DAO();
        blackHoodie.setProduct(testData.getValue("/blackHoodie/productName").toString());
        blackHoodie.setPrice(testData.getValue("/blackHoodie/productPrice").toString());
        blackHoodie.setQuantity(testData.getValue("/blackHoodie/checkoutQuantity").toString());
        blackHoodie.setSubtotal(testData.getValue("/blackHoodie/subtotal").toString());
        expectedList.add(blackHoodie);

        Assert.assertEquals(actualCartList.toString(), expectedList.toString(), "Cart does not match with expected products attribute");

    }

    @Then("Validated Cart totals")
    public void Validated_Cart_totals(){
        String expectedSubtotal = testData.getValue("/cartTotals/subTotals").toString();
        String actualSubtotal = cart.getSubTotals();
        String expectedTotals = testData.getValue("/cartTotals/totals").toString();
        String actualTotals = cart.getTotals();
        Assert.assertEquals(actualSubtotal,expectedSubtotal, "Subtotal doesn't matched");
        Assert.assertEquals(actualTotals,expectedTotals, "Totals doesn't matched");
    }

    @When("User change quantity of Dark Brown Jeans")
    public void user_change_quantity_of_dark_brown_jeans() {
        cart.setQuantityOfDarkBrownJeans(testData.getValue("/ChangeQuantity/quantity").toString());
        cart.clickedOnUpdatedCart();
    }

    @Then("Validated again cart totals")
    public void validated_again_cart_totals() {
        Assert.assertTrue(cart.isContentUpdateMessageVisible());
        String expectedSubtotal = testData.getValue("/ChangeQuantity/subTotals").toString();
        String actualSubtotal = cart.getSubTotals();
        String expectedTotals = testData.getValue("/ChangeQuantity/totals").toString();
        String actualTotals = cart.getTotals();
        Assert.assertEquals(actualSubtotal,expectedSubtotal, "Updated Subtotal doesn't matched");
        Assert.assertEquals(actualTotals,expectedTotals, "Updated Totals doesn't matched");
    }
    @When("User enter invalid coupon")
    public void user_enter_invalid_coupon() {
        cart.enterCoupon(testData.getValue("/coupon/couponCode").toString());
    }

    @When("clicked on Apply Coupon")
    public void clicked_on_apply_coupon() {
        cart.clickedOnApplyCoupon();
    }

    @Then("Validated negative response")
    public void validated_negative_response() {
        Assert.assertTrue(cart.getCouponUpdatedMessage().contains(testData.getValue("/coupon/negativeResponse").toString()), "Invalid coupon negative response doesn't matched");
    }

    @When("User clicked on Checkout")
    public void user_clicked_on_checkout() {
        cart.clickedOnCheckout();
    }
    @When("Filled the billing address")
    public void Filled_the_billing_address(){
        checkout.setFirstName(testData.getValue("/billingAddress/firstName").toString());
        checkout.setLastName(testData.getValue("/billingAddress/lastName").toString());
        checkout.setCountry(testData.getValue("/billingAddress/country").toString());
        checkout.setStreet(testData.getValue("/billingAddress/street").toString());
        checkout.setCity(testData.getValue("/billingAddress/city").toString());
        checkout.setState(testData.getValue("/billingAddress/state").toString());
        checkout.setPIN(testData.getValue("/billingAddress/PIN").toString());
        checkout.setPhone(testData.getValue("/billingAddress/phone").toString());
        checkout.setEmail(testData.getValue("/billingAddress/email").toString());
        checkout.setOrderNotes(testData.getValue("/billingAddress/orderNote").toString());

    }
    @When("Clicked on Cash on Delivery and Place Order")
    public void Clicked_on_Cash_on_Delivery_and_Place_Order(){
        checkout.clickedOnCashOnDelivery();
        checkout.clickedOnPlaceOrder();
    }
    @Then("Validated checkout page and close tab")
    public void validated_checkout_page_close_tab(){
        Assert.assertEquals(checkout.getTotals(), testData.getValue("/ChangeQuantity/totals").toString(), "Totals does not match in checkout");
        Assert.assertEquals(checkout.getPaymentMethod(), testData.getValue("/paymentMethod").toString(), "Payment method does not matched");
        Assert.assertEquals(checkout.getNote(), testData.getValue("/billingAddress/orderNote").toString(), "Order does not matched in checkout");
        DriverFactory.getPage().close();
    }

}