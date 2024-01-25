#Using Cucumber and Gherkin language
Feature: Assignment for QA Engineer - Automation

  Background:
    Given User navigated to homepage URL

  Scenario: Validating homepage and subscription function
    #Validating homepage titles and homepage URLs
    When User is on homepage
    Then Validated homepage titles
    And Validated homepage URLs
    #Validating subscription
    When User scroll down to the footer of the page
    And User input email
    And User Clicked on subscribed button
    Then Validated successfully subscription message

  Scenario: Validating the store's Search, Filter, cart and checkout functions
    #Validating search result
    When User is on homepage
    And User Clicked on Everything from menu in a new tab
    And User search jeans and clicked on search button
    Then Validated search results
    #Validating Dark Brown Jeans URL, Price, Name and Description
    When user filter sort by popularity
    And User Clicked on Dark Brown Jeans
    Then Validated Dark Brown Jeans URL, Price, Name and Description
    #Validating Dark Brown Jeans quantity added to cart or not
    When User change quantity
    And Clicked on ADD TO CART
    Then Validated Dark Brown Jeans and quantity added to cart or not
    #Validating filter
    When User clicked on Everything
    And User set filter and clicked on filter
    Then Validated filter results
    #Validating Black hoodie URL, Price, Name and Description
    When User clicked on Black Hoodie
    Then Validated Black hoodie URL, Price, Name and Description
    #Validating added cart positive response
    When Clicked on ADD TO CART
    And Validated added cart positive response
    #Validating cart
    When User clicked on View Cart
    Then Validated cart has Black Hoodie and Dark Brown Jeans's Name, Price, Quantity and Subtotal
    And Validated Cart totals
    When User change quantity of Dark Brown Jeans
    Then Validated again cart totals
    #Valodating Coupon
    When User enter invalid coupon
    And clicked on Apply Coupon
    Then Validated negative response
    #Validating Checkout page
    When User clicked on Checkout
    And Filled the billing address
    And Clicked on Cash on Delivery and Place Order
    Then Validated checkout page and close tab

  Scenario: Validate contact us
    When User is on homepage
    And clicked on contact us
    And Fill all the information and clicked Send message
    Then Validated confirmation message