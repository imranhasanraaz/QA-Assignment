package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.Everything;
import utils.JsonUtils;

import static utils.StringExtractor.IntegerExtractor;


public class EverythingSteps {
    JsonUtils testData = new JsonUtils("testdata.json");
    Everything everything = new Everything(DriverFactory.getPage());

    @When("User search jeans and clicked on search button")
    public void user_search_jeans_and_clicked_on_search_button() {
        everything.setSearchKeyword(testData.getValue("/searchData/searchKeyword").toString());
        everything.clickedOnSearchArrowButton();
    }

    @Then("Validated search results")
    public void validated_search_results() {
        Assert.assertEquals(everything.getSearchResults(), testData.getValue("/searchData/searchResults").toString(), "Search result count does not matched");
    }

    @When("user filter sort by popularity")
    public void user_filter_sort_by_popularity() {
        everything.selectOptionFromDropdown("Sort by popularity");

    }

    @When("User Clicked on Dark Brown Jeans")
    public void user_clicked_on_Dark_Brown_Jeans() {
        everything.clickedOnDarkBrownJeans();
    }

    @Then("Validated Dark Brown Jeans URL, Price, Name and Description")
    public void validated_Dark_Brown_Jeans_URL_Price_Name_and_Description() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(DriverFactory.getPage().url(), testData.getValue("/particularProduct/productURL").toString(), "products url doesn't match");
        softAssert.assertEquals(everything.getProductTitle(), testData.getValue("/particularProduct/productName").toString(), "Product title does not matched");
        softAssert.assertEquals(everything.getPrice(), testData.getValue("/particularProduct/productPrice").toString(), "Product title does not matched");
        softAssert.assertEquals(everything.getProductDescription(), testData.getValue("/particularProduct/productDescription").toString(), "Product title does not matched");
        softAssert.assertAll();
    }

    @When("User change quantity")
    public void user_change_quantity() {
        everything.setQuantity(testData.getValue("/particularProduct/checkoutQuantity").toString());

    }

    @When("Clicked on ADD TO CART")
    public void clicked_on_add_to_cart() {
        everything.clickedOnAddToCart();
    }

    @Then("Validated Dark Brown Jeans and quantity added to cart or not")
    public void validated_Dark_Brown_Jeans_and_quantity_added_to_cart_or_not() {
        String addedCartMessage = everything.getAddedCartMessage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(IntegerExtractor(addedCartMessage), Integer.parseInt(testData.getValue("/particularProduct/checkoutQuantity").toString()), "Quantity does not matched after added cart");
        softAssert.assertTrue(addedCartMessage.contains(testData.getValue("/particularProduct/productName").toString()), "Products Name doesn't matched");
        softAssert.assertAll();
    }

    @When("User set filter and clicked on filter")
    public void User_set_filter_and_clicked_on_filter() {
        everything.setMinPriceAndMaxPrice(100, 200);
        everything.clickedOnFilter();
    }

    @Then("Validated filter results")
    public void validated_filter_results() {
        Assert.assertEquals(everything.getFilterResults(), testData.getValue("/filterData/filterResults").toString(), "Filter results doesn't matched");
    }

}