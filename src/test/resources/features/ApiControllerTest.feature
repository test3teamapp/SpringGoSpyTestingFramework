Feature: CRUD operations for Person and User Entities
  CRUD operations for Person and User Entities

  Scenario: Create a new Person record
    When we call the api end-point createPerson
    Then a new Person record is created in the DB