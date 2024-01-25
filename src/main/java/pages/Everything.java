package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static utils.StringExtractor.IntegerExtractor;

public class Everything {
    private final Page page;
    private final Locator searchBox;
    private final Locator searchArrowButton;
    private final Locator searchResult;
    private final Locator filter;
    private final Locator darkBrownJeans;
    private final Locator productTitle;
    private final Locator productPrice;
    private final Locator productDescription;
    private final Locator quantity;
    private final Locator addToCart;
    private final Locator afterAddedCartMessage;
    private final Locator filterButton;
    private final Locator minPrice;
    private final Locator maxPrice;
    private final Locator pricefromMin;
    private final Locator pricetoMax;
    private final Locator filterResults;
    private final Locator blackHoodie;
    private final Locator viewCart;
    private final Locator goToNextPage;

    public Everything(Page page) {
        this.page = page;
        this.goToNextPage = page.locator("//a[@class='next page-numbers']");
        this.searchBox = page.locator("//input[@id='wc-block-search__input-1']");
        this.searchArrowButton = page.locator("//button[@aria-label='Search']");
        this.searchResult = page.locator("//p[@class='woocommerce-result-count']");
        this.filter = page.locator("//select[@name='orderby']");
        this.darkBrownJeans = page.locator("//h2[normalize-space()='Dark Brown Jeans']");
        this.productTitle = page.locator("//h1[@class='product_title entry-title']");
        this.productPrice = page.locator("//div[@class='summary entry-summary']//bdi[1]");
        this.productDescription = page.locator("//div[@class='woocommerce-product-details__short-description']/p");
        this.quantity = page.locator("//input[@class='input-text qty text']");
        this.addToCart = page.locator("//button[@name='add-to-cart']");
        this.afterAddedCartMessage = page.locator("//div[@class='wc-block-components-notice-banner__content']");
        this.filterButton = page.locator("//button[normalize-space()='Filter']");
        this.minPrice = page.locator("//span[@class='ui-slider-handle ui-corner-all ui-state-default' and contains(@style, 'left: 0%')]");
        this.maxPrice = page.locator("//span[@class='ui-slider-handle ui-corner-all ui-state-default' and contains(@style, 'left: 100%')]");
        this.pricefromMin = page.locator("//span[@class='from']");
        this.pricetoMax = page.locator("//span[@class='to']");
        this.filterResults = page.locator("//p[@class='woocommerce-result-count']");
        this.blackHoodie = page.locator("//h2[normalize-space()='Black Hoodie']");
        this.viewCart = page.locator("//div[@class='wc-block-components-notice-banner__content']//a[@class='button wc-forward'][normalize-space()='View cart']");
    }

    public void setSearchKeyword(String keyword) {
        searchBox.fill(keyword);
    }

    public void clickedOnSearchArrowButton() {
        searchArrowButton.click();
    }

    public String getSearchResults() {
        return searchResult.innerText();
    }

    public void selectOptionFromDropdown(String option) {
        filter.selectOption(option);
    }

    public void clickedOnDarkBrownJeans() {
        darkBrownJeans.click();
    }

    public String getProductTitle() {
        return productTitle.innerText();
    }

    public String getPrice() {
        return productPrice.innerText();
    }

    public String getProductDescription() {
        return productDescription.innerText();
    }

    public void setQuantity(String keyword) {
        quantity.clear();
        quantity.fill(keyword);
    }

    public void clickedOnAddToCart() {
        addToCart.click();
    }

    public String getAddedCartMessage() {
        return afterAddedCartMessage.innerText();
    }

    public void setMinPriceAndMaxPrice(int min, int max) {
        minPrice.click();
        while (IntegerExtractor(pricefromMin.innerText()) != min) {
            page.keyboard().press("ArrowRight");
        }

        maxPrice.click();
        while (IntegerExtractor(pricetoMax.innerText()) != max) {
            page.keyboard().press("ArrowLeft");
        }
    }

    public void clickedOnFilter() {
        filterButton.click();
    }

    public String getFilterResults() {
        return filterResults.innerText();
    }

    public void clickedOnBlackHoodie() {
        if(blackHoodie.isVisible()){
            blackHoodie.click();
        }else{
            goToNextPage.scrollIntoViewIfNeeded();
            goToNextPage.click();
            blackHoodie.click();
        }

    }

    public void clickedOnViewCart() {
        viewCart.click();
    }
}