Feature: A list item can be removed if a proper saved name is given

	Scenario: removing is successful with a proper tip name
        Given command poista is selected
       	When  name "xx" is entered
        Then  system will respond with "xx poistettu vinkeistä"


