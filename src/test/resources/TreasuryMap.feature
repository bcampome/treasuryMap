Feature: Map Creation

  Scenario: Creating an empty map
    Given Received a creation map file
    When the description start with C 6 5
    Then the map should have no treasury
    And the map should have no mountain

  Scenario: Creating a map with a single treasury
    Given Received a creation map file
    When the description start with C 6 5
    And the description contains T 4-2 1
    Then the map should have no mountain
    And the map should contain 1 treasury in (column:4, line: 2)

  Scenario: Creating a map with a group of treasures on same case
    Given Received a creation map file
    When the description start with C 6 5
    And the description contains T 1-4 3
    Then the map should have no mountain
    And the map should contain 3 treasury in (column:1, line: 4)

  Scenario: Creating a map with a lot of treasures on several cases
    Given Received a creation map file
    When the description start with C 6 5
    And the description contains T 1-4 3
    And the description contains T 4-2 1
    Then the map should have no mountain
    And the map should contain 1 treasury in (column:4, line: 2)
    And the map should contain 3 treasury in (column:1, line: 4)


  Scenario: Creating a map with one mountain
    Given Received a creation map file
    When the description start with C 6 5
    And the description contains M 5-3
    Then the map should have a mountain in (column:5, line: 3)
    And the map should have no treasury

  Scenario: Creating a map with several mountains
    Given Received a creation map file
    When the description start with C 6 5
    And the description contains M 5-3
    And the description contains M 2-5
    Then the map should have a mountain in (column:5, line: 3)
    And the map should have a mountain in (column:2, line: 5)
    And the map should have no treasury

  Scenario: Creating a map with treasurers and mountain
    Given Received a creation map file
    When the description start with C 6 5
    And the description contains T 1-4 3
    And the description contains T 4-2 1
    And the description contains M 5-3
    Then the map should have a mountain in (column:5, line: 3)
    And the map should contain 1 treasury in (column:4, line: 2)
    And the map should contain 3 treasury in (column:1, line: 4)

  Scenario: Extract a treasury from map
    Given Received a creation map file
    When the description start with C 6 5
    And the description contains T 1-4 3
    And we extract the item in (column:1, line: 4)
    Then the case in (column:1, line: 4) should be empty
    And the last item extracted should be a treasury with value of 3

