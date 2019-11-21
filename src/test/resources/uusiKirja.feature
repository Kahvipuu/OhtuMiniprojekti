Feature: A new list item can be created for a book if a proper unused name is given

        @aktiivinen
	Scenario: creation is successful with a proper book name
        Given command lisaa is selected
       	When  option kirja is selected
		And   name "java101" is entered
        Then  system will respond with "kirja lisätty vinkkeihin"

