Feature: Purchase the order from Ecommerce Website

Background:
  Given I landed on Ecommerce Page

@Regression
Scenario Outline: Positive test of Submitting the order 
  Given Logged in with <name> and password <password>
  When I add product <productName> to cart
  And checkout <productName> and submit the order
  Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

Examples: 
  | name                       | password  | productName  |
  | kshitijapathak172@gmail.com| Ssmaka17# | ZARA COAT 3  |
