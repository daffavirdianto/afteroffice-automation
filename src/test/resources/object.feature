Feature: Object API

  Background:
    Given The base url is "https://whitesmokehouse.com"

  Scenario:
    When Send a http "POST" request to "/webhook/api/login" with body:
    """
    {
      "email": "daffa.virdianto1@gmail.com",
      "password": "D@ffa123"
    }
    """"""
    Then The response status must be 200
    And Save the token from the response to local storage

  Scenario:
    Given Make sure token in local storage not empty
    When Send a http "GET" request to "/webhook/api/objects" with body:
      """
      {}
      """
    Then The response status must be 200

  Scenario:
    Given Make sure token in local storage not empty
    When Send a http "POST" request to "/webhook/api/objects" with body:
    """
    {
      "name": "ASUS Vivo Book",
      "data": {
        "year": "2024",
        "price": "1849.99",
        "cpu_model": "Intel Core i7",
        "hard_disk_size": "1 TB",
        "capacity": "2 cpu",
        "screen_size": "14 Inch",
        "color": "black"
      }
    }
    """
    Then The response status must be 200
    And Save the objectId from the response to local storage
    And name in the response must be "ASUS Vivo Book"
    And year in the response must be 2024
    And price in the response must be 1849.99
    And cpu_model in the response must be "Intel Core i7"
    And hard_disk_size in the response must be "1 TB"
    And capacity in the response must be "2 cpu"
    And screen_size in the response must be "14 Inch"
    And color in the response must be "black"

  Scenario:
    Given Make sure token in local storage not empty
    When Send a http "PUT" request to "update-url" with body:
    """
    {
      "name": "ASUS Vivo Book Pro",
      "data": {
        "year": "2025",
        "price": "1999.99",
        "cpu_model": "Intel Core i9",
        "hard_disk_size": "2 TB",
        "capacity": "4 cpu",
        "screen_size": "15 Inch",
        "color": "silver"
      }
    }
    """
    Then The response status must be 200
    And name update in the response must be "ASUS Vivo Book Pro"
    And year update in the response must be 2025
    And price update in the response must be 1999.99
    And cpu_model update in the response must be "Intel Core i9"
    And hard_disk_size update in the response must be "2 TB"
    And capacity update in the response must be "4 cpu"
    And screen_size update in the response must be "15 Inch"
    And color update in the response must be "silver"

  Scenario:
    Given Make sure token in local storage not empty
    When Send a http "DELETE" request to "delete-url" with body:
      """
      {}
      """
    Then The response status must be 200
