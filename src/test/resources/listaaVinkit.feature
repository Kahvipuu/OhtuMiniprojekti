Feature: Tips can be listed if a proper command is given

        @aktiivinen
	Scenario: list of all tips is printed with a proper command
        Given book has been created
        And blog has been created
       	When command listaa is selected
        And command listaa kaikki is selected
        Then system will respond with "Vinkkeja listattu: 2"

        @aktiivinen
        Scenario: list of book tips is printed with a proper command
        Given book has been created
        And blog has been created
        When command listaa is selected
        And command listaa kirjat is selected
        Then system will respond with "Vinkkeja listattu: 1"

        @aktiivinen
        Scenario: list of blog tips is printed with a proper command
        Given book has been created
        When command listaa is selected
        And command listaa blogit is selected
        Then system will respond with "Vinkkeja listattu: 0"

        @aktiivinen
        Scenario: list of unread tips is printed with a proper command
        Given book has been created
        And command merkitse luetuksi and an index are given
        When command listaa lukemattomat is selected
        Then system will respond with "Vinkkeja listattu: 0"

        @aktiivinen
        Scenario: list of tips marked as read is printed with a proper command
        Given book has been created
        And command merkitse luetuksi and an index are given
        When command listaa luetut is selected
        Then system will respond with "Vinkkeja listattu: 1"