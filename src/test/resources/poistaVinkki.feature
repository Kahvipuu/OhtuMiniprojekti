Feature: A list item can be removed if a proper id is given

    @aktiivinen
    Scenario: removing is successful with a proper id
        Given book has been created 
        When command poista is selected and an id given
       	Then  system will respond with "Vinkki poistettu."

    @aktiivinen
    Scenario: removing is unsuccessful with an invalid id
        Given book has been created 
        When command poista is selected and an invalid id given
       	Then system will respond with "Epakelpo syote."
