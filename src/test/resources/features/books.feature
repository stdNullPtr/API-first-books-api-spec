Feature: Book Store API

  Background:
    Given Base URI is "http://localhost"
    And Port is "8080"
    And Base Path is "/api/v1"

  Scenario: Fetch all books
    When I make a GET request to "/books"
    Then the response status should be 200
    And the response should contain 2 books
    And the response is a list of Books

  Scenario: Retrieve a specific book by ID
    When I make a GET request to "/books/1"
    Then the response status should be 200
    And the response should contain a book with ID 1
    And the response is a Book
