Feature: A new list item can be created for a blog if a proper unused name is given

    @aktiivinen
    Scenario: creation is successful with a proper blog name
    Given command lisaa ja lisaa blogi is selected
    When author "Matti", topic "ohtu" and address "https://ohjelmistotuotanto-hy.github.io/" are entered
    Then system will respond with "Blogi lisatty vinkkeihin."
