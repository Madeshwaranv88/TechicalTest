Feature: Coinmarket coins portfolios check
@UI
Scenario: Check portfolios
    Given Launch coinmarket url "https://coinmarketcap.com"
    And select "20" from show rows dropdown
  	And capture "Price"
 		And filter by algorithm "PoW"
  	And click Add filter button
    And select toggle "Mineable"
    And click All cryptocurrencies and select "Coins"
    And Select price and set minimum to "100" and maximum "10000"
    When verify page content to match "Price"
    Then validate the "Price" matches for atleast one
