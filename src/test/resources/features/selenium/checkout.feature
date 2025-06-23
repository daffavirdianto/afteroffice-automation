Feature: Checkout Scenario

    Background: User landend to login page
        Given User is on the login page
        When User inputs email "standard_user" and password "secret_sauce"
        Then User should be redirected to the products page
    
    Scenario: User adds items to cart and proceeds to checkout
        When User adds item "Sauce Labs Fleece Jacket" to the cart
        And User redirect to cart page
        Then User should see "Sauce Labs Fleece Jacket" in the cart
        When User clicks on Checkout button
        When User inputs fill information with first name "Daffa", last name "Virdianto", and zip code "12345"
        And User clicks on Continue button
        When User clicks on Finish button
        Then User should see "Thank you for your order!" message
        When User clicks on Back Home button
