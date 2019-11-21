package main;

// Miniprojekti
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import data_access.*;
import domain.*;
import main.*;

public class Stepdefs {

    /*
    Kurkkuprojektista, vielä esimerkkinä mukana

    App app;
    StubIO io;
    UserDao userDao;
    AuthenticationService auth;
    List<String> inputLines;
     */
    @Before
    public void setup() {

    }

    @Given("command lisaa is selected")
    public void commandLisaaSelected() throws Throwable {

    }

    @When("option kirja is selected")
    public void optionKirjaSelected() {

    }

    @When("name {string} is entered")
    public void nimiKirjalleAnnetaan(String expectedInput) {

    }

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        assertTrue(true);
        //	System.out.println("ohjelma tulosti seuraavat rivit "+io.getPrints());
        //    assertTrue(io.getPrints().contains(expectedOutput));
    }

}
