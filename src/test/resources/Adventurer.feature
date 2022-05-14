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


  Scenario Outline: adventurer can give the next desired position when we have to move forward
    Given An adventurer "Joe" was created with direction "<direction>"
    And the adventurer was in position (column : 1 line : 1)
    When the adventurer receive as next instruction as "A"
    Then the adventurer should have desired position as (column : <column>, line : <line>)
    Examples:
      | direction | column | line |
      | E         | 2      | 1    |
      | W         | 0      | 1    |
      | N         | 0      | 1    |
      | S         | 2      | 1    |



    # A next state (PAUSE, ROTATION, MOVE, ACTION ?)
    # a Position (X,Y)
    # All Treasurers save
