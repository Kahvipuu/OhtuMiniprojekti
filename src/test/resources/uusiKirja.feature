Feature: A new list item can be created for a book if a proper unused name is given

	Scenario: creation is successful with a proper book name
        	Given command lisää is selected
       		When  option kirja is selected
		And   name "uuden kirjan nimi" is entered
        	Then  system will respond with "kirja lisätty vinkkeihin"
