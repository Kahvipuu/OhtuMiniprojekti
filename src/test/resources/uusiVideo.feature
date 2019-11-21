Feature: A new list item can be created for a video if a proper unused name is given

	Scenario: creation is successful with a proper video name
        Given command lisaa is selected
       	When  option video is selected
		And   name "video101" is entered
        Then  system will respond with "video lisätty vinkkeihin"

