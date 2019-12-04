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
import java.io.IOException;
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

    @Given("command lisaa ja lisaa kirja is selected")
    public void commandLisaaAndLisaaKirjaSelected() throws Throwable {
        inputLines.add("1");
        inputLines.add("1");
    }

    @Given("command lisaa ja lisaa blogi is selected")
    public void commandLisaaAndLisaaBlogiSelected() throws Throwable {
        inputLines.add("1");
        inputLines.add("2");
    }

    @Given("book has been created")
    public void bookTipCreation() {
        inputLines.add("1");
        inputLines.add("1");
        inputLines.add("Jeffrey K. Liker");
        inputLines.add("The Toyota Way");
        inputLines.add("9780071392310");
    }

    @Given("blog has been created")
    public void blogTipCreation() {
        inputLines.add("1");
        inputLines.add("2");
        inputLines.add("Source Making");
        inputLines.add("Code Smells");
        inputLines.add("https://sourcemaking.com/refactoring/smells");
    }

    @When("author {string}, title {string} and ISBN-number {string} are entered")
    public void bookInfoGiven(String author, String title, String isbn) {
        inputLines.add(author);
        inputLines.add(title);
        inputLines.add(isbn);
    }

    @When("author {string}, topic {string} and address {string} are entered")
    public void blogInfoGiven(String author, String topic, String address) {
        inputLines.add(author);
        inputLines.add(topic);
        inputLines.add(address);
    }

    @When("command listaa is selected")
    public void commandListaaSelected() {
        inputLines.add("2");
    }

    @When("command listaa kirjat is selected")
    public void commandListaaKirjatSelected() {
        inputLines.add("1");
    }

    @When("command listaa blogit is selected")
    public void commandListaaBlogitSelected() {
        inputLines.add("2");
    }

    @When("command listaa kaikki is selected")
    public void commandListaaKaikkiSelected() {
        inputLines.add("3");
    }

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) throws IOException {
        io = new StubIO(inputLines);
        app = new App(io, dao);
        app.run();
        //System.out.println("ohjelma tulosti seuraavat rivit "+io.getPrints());
        assertTrue(io.getPrints().contains(expectedOutput));
    }

    @When("command poista is selected and an id given")
    public void commandPoistaIsSelectedAndAnIdGiven() {
        inputLines.add("3");
        inputLines.add("0");
    }

    @When("command avaa linkki selaimessa is selected and an id given")
    public void commandAvaaLinkkiSelaimessaIsSelectedAndAnIdGiven() {
        inputLines.add("4");
        inputLines.add("0");
    }

    @When("commands muuta vinkkia and indeksi 0 are given")
    public void commadMuutaAndIndexZero() {
        inputLines.add("5");
        inputLines.add("0");
    }

    @When("commands merkitse luetuksi and indeksi 0 are given")
    public void commadMarkAsReadAndIndexZero() {
        inputLines.add("6");
        inputLines.add("0");
    }

    @When("new info {string}, {string} and {string} is given")
    public void newInfoGivenForTip(String info1, String info2, String info3) {
        inputLines.add(info1);
        inputLines.add(info2);
        inputLines.add(info3);
    }

}
