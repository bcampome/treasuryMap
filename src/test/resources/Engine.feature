Feature: Engine logic

  Scenario: an adventurer move on an empty map
    Given a map was created with "C 6-4"
    And an adventurer was created with "John 1-1 E AADADAGA"
    When the engine run all instruction of adventurer(s)
    Then the adventurer "John" should be in position (column : 2, line : 3)

  Scenario Outline: an Adventurer is blocked on map border
    Given a map was created with "C 3-3"
    And an adventurer was created with "John 2-2 <direction> AAAAA"
    When the engine run all instruction of adventurer(s)
    Then the adventurer "John" should be in position (column : <column>, line : <line>)
    Examples:
      | direction | column | line |
      | N         | 2      | 1    |
      | E         | 3      | 2    |
      | S         | 2      | 3    |
      | O         | 1      | 2    |

  Scenario Outline: an Adventurer ignore move forward instruction when he is blocked by a mountain
    Given a map was created with "<map>"
    And an adventurer was created with "John 2-2 <direction> A "
    When the engine run all instruction of adventurer(s)
    Then the adventurer "John" should be in position (column : 2, line : 2)
    Examples:
      | map          | direction |
      | C 3-3\nM 3-2 | E         |
      | C 3-3\nM 2-1 | N         |
      | C 3-3\nM 2-3 | S         |
      | C 3-3\nM 1-2 | O         |

  Scenario Outline: an adventurer handle treasury(es)
    Given a map was created with "<map>"
    And an adventurer was created with "John 1-1 E AADAAGAADAA"
    When the engine run all instruction of adventurer(s)
    Then the adventurer "John" should have treasurer sum equal to <totalTreasury>
    Examples:
      | map                     | totalTreasury |
      | C 6-5\nT 4-3 3          | 3             |
      | C 6-5\nT 3-2 1\nT 5-4 3 | 4             |