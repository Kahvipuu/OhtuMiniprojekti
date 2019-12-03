package main;

import data_access.JSONFileVinkkiDao;
import data_access.InMemoryVinkkiDao;
import data_access.VinkkiDao;
import domain.*;
import io.ConsoleIO;
import io.IO;
import java.io.IOException;
import java.util.List;

public class App {

    private IO io;
    private VinkkiDao dao;

    public App(IO io, VinkkiDao dao) {
        this.io = io;
        this.dao = dao;
    }

    public void run() throws IOException {
        while (true) {
            String command = io.readLine(mainCommands);

            if (command.isEmpty()) {
                break;
            }

            // Vinkin lisääminen
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

            // Vinkkien listaaminen
            if (command.equals("2")) {
                String listCommand = io.readLine(listCommands);
                if (listCommand.equals("1")) {
                    List<Vinkki> vinkit = dao.listaaTyypin("kirja");
                    printList(vinkit);
                } else if (listCommand.equals("2")) {
                    List<Vinkki> vinkit = dao.listaaTyypin("blogi");
                    printList(vinkit);
                } else if (listCommand.equals("3")) {
                    List<Vinkki> vinkit = dao.listaaKaikki();
                    printList(vinkit);
                }
            }

            //Vinkkien poistaminen
            if (command.equals("3")) {
                List<Vinkki> vinkit = dao.listaaKaikki();
                printListWithIndex(vinkit);
                String poistettava = io.readLine("Anna poistettavan vinkin numero: ");
                try {
                    int i = Integer.parseInt(poistettava);
                    if (i >= 0 && i < vinkit.size()) {
                        dao.poista(i);
                        io.print("Vinkki poistettu.");
                        io.print("\n");
                    } else {
                        io.print("Epäkelpo syöte.\n");
                    }
                } catch (Exception e) {
                    io.print("Epäkelpo syöte.\n");
                }
            }
            // Vinkkien avaaminen selaimessa
            if (command.equals("4")) {
                List<Vinkki> vinkit = dao.listaaTyypin("blogi");
                printListWithIndex(vinkit);
                String linkki = this.linkinAvaus(vinkit);
                if (!linkki.equals("virhe")) {
                    LinkinAvausProsessi prosessi = new LinkinAvausProsessi(linkki);
                    prosessi.avaaLinkki();
                    io.print("Linkki avattu selaimeen.");
                } else {
                    io.print("Epäkelpo syöte.\n");
                }
            }

            //Vinkkien muuttaminen
            if (command.equals("5")) {
                List<Vinkki> vinkit = dao.listaaKaikki();
                printListWithIndex(vinkit);
                String muutettava = io.readLine("Anna muutettavan vinkin numero: ");
                try {
                    int indeksi = Integer.parseInt(muutettava);
                    if (indeksi >= 0 && indeksi < vinkit.size()) {
                        vinkinMuuttaminen(indeksi);
                        io.print("Vinkkia muutettu.");
                        io.print("\n");
                    } else {
                        io.print("Epäkelpo syöte.\n");
                    }
                } catch (Exception e) {
                    io.print("Epäkelpo syöte.\n");
                }
            }

        }
    }

    String mainCommands = "Komennot: \n"
            + "1: Lisää vinkki \n"
            + "2: Listaa vinkit \n"
            + "3: Poista vinkki \n"
            + "4: Avaa linkki selaimessa \n"
            + "5: Muuta olemassaolevaa vinkkiä \n"
            + "tyhjä sulkee sovelluksen \n";

    String newTipCommands = "Komennot: \n"
            + "1: Lisää kirja \n"
            + "2: Lisää blogi \n"
            + "tyhjä palaa alkuun \n";

    String listCommands = "Komennot: \n"
            + "1: Listaa kirjat \n"
            + "2: Listaa blogit \n"
            + "3: Listaa kaikki vinkit \n"
            + "tyhjä palaa alkuun \n";

    public Kirja newBook() {
        String kirjailija = io.readLine("Syötä kirjailijan nimi: ");
        String nimi = io.readLine("Syötä kirjan nimi: ");
        String isbn = io.readLine("Syötä kirjan ISBN-tunnus: ");
        Kirja uusiKirja = new Kirja(kirjailija, nimi, isbn);
        return uusiKirja;
    }

    private Blogi newBlog() {
        String kirjoittaja = io.readLine("Syötä kirjoittajan nimi: ");
        String aihe = io.readLine("Syötä aihe: ");
        String osoite = io.readLine("Syötä osoite: ");
        Blogi newBlog = new Blogi(kirjoittaja, aihe, osoite);
        return newBlog;
    }

    public void printList(List<Vinkki> vinkit) {
        for (Vinkki v : vinkit) {
            io.print(v.toString());
        }
        io.print("Vinkkeja listattu: " + vinkit.size());
        io.print("\n");
    }

    public void printListWithIndex(List<Vinkki> vinkit) {
        for (int i = 0; i < vinkit.size(); i++) {
            io.print("#" + i);
            io.print(vinkit.get(i).toString());
        }
        io.print("\n");
    }

    public String linkinAvaus(List<Vinkki> vinkit) {
        if (vinkit.isEmpty()) {
            return "virhe";
        }
        String avattava = io.readLine("Anna avattavan linkin id (jarjestysnumero) alkaen luvusta 0: ");
        Vinkki vinkki = new Vinkki();
        int id = this.tarkistaSyote(avattava);
        if (id == -1) {
            return "virhe";
        }
        if (id < 0 || id >= vinkit.size()) {
            return "virhe";
        }

        vinkki = vinkit.get(id);

        return haeLinkinTyyppi(vinkki);
    }

    public int tarkistaSyote(String syote) {
        int id = 0;
        try {
            id = Integer.parseInt(syote);
        } catch (Exception e) {
            return -1;
        }
        return id;
    }

    public String haeLinkinTyyppi(Vinkki vinkki) {
        String linkki = "";
        if (vinkki.getTyyppi().equals("blogi")) {
            Blogi b = new Blogi();
            b = (Blogi) vinkki;
            linkki = b.getOsoite();
        }
        return linkki;
    }

    public static void main(String[] args) throws IOException {

        VinkkiDao dao = new JSONFileVinkkiDao("vinkit.json");
        IO io = new ConsoleIO();

        App app = new App(io, dao);
        app.run();
    }

    private void vinkinMuuttaminen(int i) {
        String vinkinTyyppi = dao.getVinkki(i).getTyyppi();
        io.print("Anna uudet tiedot, tyhjä säilyttää vanhan");
        if (vinkinTyyppi.equals("kirja")) {
            String kirjailija = io.readLine("Syötä kirjailijan nimi: ");
            String KirjanNimi = io.readLine("Syötä kirjan nimi: ");
            String isbn = io.readLine("Syötä kirjan ISBN-tunnus: ");
            dao.getVinkki(i).setAll(kirjailija, KirjanNimi, isbn);
        }
        if (vinkinTyyppi.equals("blogi")) {
            String kirjoittaja = io.readLine("Syötä kirjoittajan nimi: ");
            String aihe = io.readLine("Syötä aihe: ");
            String osoite = io.readLine("Syötä osoite: ");
            dao.getVinkki(i).setAll(kirjoittaja, aihe, osoite);
        }
        dao.saveJson();
    }
}
