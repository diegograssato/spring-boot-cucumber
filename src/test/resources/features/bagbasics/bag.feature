#lang: en
Feature: Bag functionalities

  Scenario: Putting one thing in the bag
    Given the bag is empty
    When I put 1 potato in the bag
    Then the bag should contain only 1 potato

  Scenario: Putting few things in the bag
    Given the bag is empty
    When I put 1 potato in the bag
    And I put 2 cucumber in the bag
    Then the bag should contain 1 potato
    And the bag should contain 2 cucumber

  Scenario: Putting other things in the bag
    Given the bag is empty
    When I put 3 lemons in the bag
    And I put 3 apples in the bag
    Then the bag should contain 3 lemons
    And the bag should contain 3 apples