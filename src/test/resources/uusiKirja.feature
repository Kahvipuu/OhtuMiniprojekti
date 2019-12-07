Feature: A new list item can be created for a book if proper info is given

    @aktiivinen
    Scenario: creation is successful with proper book info
    Given command lisaa ja lisaa kirja is selected
    When  author "Robert Martin", title "Clean Code" and ISBN-number "978-0132350884" are entered
    Then  system will respond with "Kirja lisatty vinkkeihin."

    @aktiivinen
    Scenario: creation is successful with proper isbn
    Given command lisaa ja lisaa kirja ISBN:lla is selected
    When a valid isbn is given
    Then  system will respond with "Kirja lisatty vinkkeihin."

    @aktiivinen
    Scenario: creation is unsuccessful with invalid isbn
    Given command lisaa ja lisaa kirja ISBN:lla is selected
    When an inexistent isbn is given
    Then  system will respond with "Kirjaa ei loytynyt."

