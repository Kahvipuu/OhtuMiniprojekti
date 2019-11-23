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
import io.StubIO;
import main.*;

public class Stepdefs {

    App app;
    StubIO io;
    VinkkiDao dao;
    List<String> inputLines;

    @Before
    public void setup() {
        inputLines = new ArrayList<>();
        dao = new InMemoryVinkkiDao();
    }

    @Given("command lisaa is selected")
    public void commandLisaaSelected() throws Throwable {
        inputLines.add("1");
    }

    @When("author {string} and title {string} and ISBN-number {string} are entered")
    public void kirjanTiedotAnnetaan(String author, String title, String isbn) {
        inputLines.add(author);
        inputLines.add(title);
        inputLines.add(isbn);

        io = new StubIO(inputLines);
        app = new App(io, dao);
        app.run();
    }

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        //System.out.println("ohjelma tulosti seuraavat rivit "+io.getPrints());
        assertTrue(io.getPrints().contains(expectedOutput));
    }

}
