Feature: Read Timeline
  As a user
  I want to read a user timeline
  So I can read the chits created by that user
  
  Scenario: Read Timeline
    Given Alice writes FirstAliceChit, 1 hours ago
    And Bob writes FirstBobChit, 5 minutes ago
    And Alice writes SecondAliceChit, 3 minutes ago
    When a user reads Alice timeline
    Then timeline should contain "SecondAliceChit (3 minutes ago), FirstAliceChit (1 hour ago)"
    