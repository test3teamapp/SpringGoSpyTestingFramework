Feature: CRUD operations for Person and User Entities
  CRUD operations for Person and User Entities

  Scenario: Create a new Person record
    When we call the api end-point createPerson
    Then a new Person record is created in the DB

  Scenario: Check that a "testperson" record exists
    When we call the api end-point to find a record with id "testperson"
    Then the record exists in the DB