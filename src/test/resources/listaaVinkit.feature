Feature: List items can be listed if a proper command is given

        @aktiivinen
	Scenario: list is printed with a proper command
        Given book has been created
       	When command listaa is selected
        Then system will respond with "Vinkkeja listattu: 1"