Feature: Searching tips will return the tips matching the search keyword

    @aktiivinen
    Scenario: searching for the writer of an added book will list the corresponding book
        Given book has been created
        And blog has been created
        When command hae is selected and keyword "Liker" is given
        Then system will respond with "Vinkkeja listattu: 1"
