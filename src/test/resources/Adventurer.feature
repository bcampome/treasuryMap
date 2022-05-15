Feature: Adventurer logic

  Scenario Outline: adventurer handle D, G, A instructions
    Given An adventurer "John" was created
    When the adventurer receive as next instruction as "<instruction>"
    Then the adventurer should apply next instruction <instructionTag>
    Examples:
      | instruction | instructionTag |
      | D           | ROTATE_RIGHT   |
      | G           | ROTATE_LEFT    |
      | A           | MOVE_FORWARD   |

  Scenario Outline: adventurer rotate with D or G instruction
    Given An adventurer "John" was created with direction "<direction>"
    And the adventurer received next instruction as "<instruction>"
    When the adventurer apply next instruction
    Then the adventurer should have direction as "<newDirection>"
    Examples:
      | direction | instruction | newDirection |
      | E         | D           | S            |
      | E         | G           | N            |
      | S         | D           | O            |
      | S         | G           | E            |
      | N         | D           | E            |
      | N         | G           | O            |
      | O         | D           | N            |
      | O         | G           | S            |


  Scenario Outline: adventurer doesn't provide next position when he have to rotate
    Given An adventurer "Joe" was created
    When the adventurer receive as next instruction as "<instruction>"
    Then The adventurer should not have desired position
    Examples:
      | instruction |
      | D           |
      | G           |

    Scenario: adventurer can give current position
      Given An adventurer "Doe" was created
      When the adventurer is in position (column : 1 line : 1)
      Then the adventurer should provide current position as (column : 1, line : 1)

  Scenario Outline: adventurer can give the next desired position when we have to move forward
    Given An adventurer "Joe" was created with direction "<direction>"
    And the adventurer was in position (column : 1 line : 1)
    When the adventurer receive as next instruction as "A"
    Then the adventurer should have desired position as (column : <column>, line : <line>)
    Examples:
      | direction | column | line |
      | N         | 1      | 0    |
      | E         | 2      | 1   |
      | S         | 1      | 2    |
      | O         | 0      | 1    |

  Scenario: An adventurer must handle a treasury he finds.
  Given An adventurer "Joe" was created
    When the adventurer finds a treasury of 2
    Then the adventurer should provide 2 as sum of treasures he found

  Scenario: An adventurer must handle a new treasury he finds in addition of other founds before
    Given An adventurer "Joe" was created
    And the adventurer found a treasury of 5
    When the adventurer finds a treasury of 3
    Then the adventurer should provide 8 as sum of treasures he found

    # All Treasurers save
