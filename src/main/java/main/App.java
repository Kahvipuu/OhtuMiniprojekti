package main;

import data_access.JSONFileVinkkiDao;
import data_access.InMemoryVinkkiDao;
import data_access.VinkkiDao;
import domain.*;
import io.ConsoleIO;
import io.IO;
import java.util.List;
import ui.*;

public class App {

    private IO io;
    private VinkkiDao dao;

    public App(IO io, VinkkiDao dao) {
        this.io = io;
        this.dao = dao;
    }

    public void run() {
        while (true) {
            String komento = io.readLine(komennot);

            if (komento.isEmpty()) {
                break;
            }

            if (komento.equals("1")) {
                Kirja kirja = uusiKirja();
                dao.lisaa(kirja);
                io.print("Kirja lisätty vinkkeihin. \n");

            } else if (komento.equals("2")) {
                List<Vinkki> vinkit = dao.listaaKaikki();
                for (Vinkki v : vinkit) {
                    io.print(v.toString());
                }
            }

        }
    }

    String komennot = "Komennot: \n"
                + "1: Lisää kirjavinkki \n"
                + "2: Listaa vinkit \n"
                + "tyhjä sulkee sovelluksen \n";

    public Kirja uusiKirja() {
        String kirjailija = io.readLine("Syötä kirjailijan nimi: ");
        String nimi = io.readLine("Syötä kirjan nimi: ");
        String isbn = io.readLine("Syötä kirjan ISBN-tunnus: ");
        Kirja uusiKirja = new Kirja(kirjailija, nimi, isbn);
        return uusiKirja;
    }

    public static void main(String[] args) {

        //VinkkiDao dao = new InMemoryVinkkiDao();
        VinkkiDao dao = new JSONFileVinkkiDao("vinkit.json");
        IO io = new ConsoleIO();

        App app = new App(io, dao);
        app.run();
    }

}
