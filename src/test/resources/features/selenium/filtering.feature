Feature:Filtering Scenario

    Background: User landend to login page
        Given User is on the login page
        When User inputs email "standard_user" and password "secret_sauce"
        Then User should be redirected to the products page
    
    Scenario: User filters products by price low to high
        When User selects Price high to low from the filter options
        Then Verify that products are sorted by price from low to high

    Scenario: User filters products by price high to low
        When User selects Price high to low from the filter options
        Then Verify that products are sorted by price from high to low

    Scenario: User filters products by name A to Z
        When User selects Name A to Z from the filter options
        Then Verify that products are sorted by name from A to Z

    Scenario: User filters products by name Z to A
        When User selects Name Z to A from the filter options
        Then Verify that products are sorted by name from Z to A
