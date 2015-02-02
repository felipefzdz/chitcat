Feature: Read Wall
  As a user
  I want to read a user wall
  So I can read the chits created by that user and the ones of her followings
  
  Scenario: Read Wall
    Given Alice writes FirstAliceChit, 1 hours ago
    And Bob writes FirstBobChit, 5 minutes ago
    And Alice writes SecondAliceChit, 3 minutes ago
    And Charlie writes FirstCharlieChit, 50 seconds ago
    And Alice follows Charlie
    When a user reads Alice wall
    Then wall should contain "Alice - FirstAliceChit (1 hour ago), Alice - SecondAliceChit (3 minutes ago), Charlie - FirstCharlieChit (50 seconds ago)"
    