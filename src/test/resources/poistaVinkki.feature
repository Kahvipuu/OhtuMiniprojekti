Feature: A list item can be removed if a proper id is given

    @aktiivinen
    Scenario: removing is successful with a proper id
        Given book has been created 
        When command poista is selected and an id given
       	Then  system will respond with "Vinkki poistettu."


