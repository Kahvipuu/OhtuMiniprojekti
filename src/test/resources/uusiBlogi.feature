Feature: A new list item can be created for a blog if a proper unused name is given

	Scenario: creation is successful with a proper blog name
        Given command lisaa is selected
       	When  option blog is selected
		And   name "uusiBlogi" is entered
        Then  system will respond with "blogi lisätty vinkkeihin"


