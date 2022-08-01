Feature: Check API Call
@API
  Scenario: Check basic api call
    Given Setup the apikey to convert "10000000" in "GTQ" to "GBP"
    When "GBP" converted value is retrieved
    Then Convert amount received in destCurrency to "DOGE"
