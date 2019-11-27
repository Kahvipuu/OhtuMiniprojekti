package main;

import data_access.JSONFileVinkkiDao;
import data_access.InMemoryVinkkiDao;
import data_access.VinkkiDao;
import domain.*;
import io.ConsoleIO;
import io.IO;
import java.util.List;

public class App {

    private IO io;
    private VinkkiDao dao;

    public App(IO io, VinkkiDao dao) {
        this.io = io;
        this.dao = dao;
    }

    public void run() {
        while (true) {
            String command = io.readLine(mainCommands);

            if (command.isEmpty()) {
                break;
            }

            if (command.equals("1")) {
                String newTipcommand = io.readLine(newTipCommands);
                if (newTipcommand.equals("1")) {
                    Kirja kirja = newBook();
                    dao.lisaa(kirja);
                    io.print("Kirja lisatty vinkkeihin.");
                    io.print("\n");
                }
                if (newTipcommand.equals("2")) {
                    Blogi blogi = newBlog();
                    dao.lisaa(blogi);
                    io.print("Blogi lisatty vinkkeihin.");
                    io.print("\n");
                }
            }
            if (command.equals("2")) {
                List<Vinkki> vinkit = dao.listaaKaikki();
                for (Vinkki v : vinkit) {
                    io.print(v.toString());
                }
                io.print("Vinkkeja listattu: " + vinkit.size());
                io.print("\n");
            }

        }
    }

    String mainCommands = "Komennot: \n"
            + "1: Lisää vinkki \n"
            + "2: Listaa vinkit \n"
            + "tyhjä sulkee sovelluksen \n";

    String newTipCommands = "Komennot: \n"
            + "1: Lisää kirja \n"
            + "2: Lisää blogi \n"
            + "tyhjä palaa alkuun \n";

    public Kirja newBook() {
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

    private Blogi newBlog() {
        String kirjoittaja = io.readLine("Syötä kirjoittajan nimi: ");
        String aihe = io.readLine("Syötä aihe: ");
        String osoite = io.readLine("Syötä osoite: ");
        Blogi newBlog = new Blogi(kirjoittaja, aihe, osoite);
        return newBlog;
    }

}
