package main;

import data_access.JSONFileVinkkiDao;
import data_access.InMemoryVinkkiDao;
import data_access.VinkkiDao;
import domain.*;
import io.ConsoleIO;
import io.IO;
import java.io.IOException;
import java.util.List;
import util.KirjaApi;
import util.ParserJson;

public class App {

    private IO io;
    private VinkkiDao dao;

    public App(IO io, VinkkiDao dao) {
        this.io = io;
        this.dao = dao;
    }

    public void run() throws IOException {
        while (true) {
            String komento = io.readLine(paaKomennot);

            if (komento.isEmpty()) {
                break;
            }

            // Vinkin lisääminen
            if (komento.equals("1")) {
                String lisaysKomento = io.readLine(this.lisaysKomennot);
                if (lisaysKomento.equals("1")) {
                    Kirja kirja = newBook();
                    dao.lisaa(kirja);
                    io.print("Kirja lisatty vinkkeihin.");
                    io.print("\n");
                }
                if (lisaysKomento.equals("2")) {
                    Blogi blogi = newBlog();
                    dao.lisaa(blogi);
                    io.print("Blogi lisatty vinkkeihin.");
                    io.print("\n");
                }
                if (lisaysKomento.equals("3")) {
                    Kirja kirja = newBookByIsbn();
                    if (kirja != null) {
                        if (kirja.getKirjanNimi().equals("") || kirja.getKirjailija().equals("")) {
                            io.print("Kirjaa ei löytynyt.");
                            io.print("\n");
                        } else {
                            dao.lisaa(kirja);
                            System.out.println("\n" + "Kirja ehdotus: " + "\n");
                            System.out.println(kirja);
                            io.print("Kirja lisatty vinkkeihin.");
                            io.print("\n");
                        }
                    }
                }
            }

            // Vinkkien listaaminen
            if (komento.equals("2")) {
                String listausKomento = io.readLine(listausKomennot);
                if (listausKomento.equals("1")) {
                    List<Vinkki> vinkit = dao.listaaTyypin("kirja");
                    printList(vinkit);
                } else if (listausKomento.equals("2")) {
                    List<Vinkki> vinkit = dao.listaaTyypin("blogi");
                    printList(vinkit);
                } else if (listausKomento.equals("3")) {
                    List<Vinkki> vinkit = dao.listaaKaikki();
                    printList(vinkit);
                } else if (listausKomento.equals("4")) {
                    List<Vinkki> vinkit = dao.listaaLuetut();
                    printList(vinkit);
                } else if (listausKomento.equals("5")) {
                    List<Vinkki> vinkit = dao.listaaLukemattomat();
                    printList(vinkit);
                }
            }

            //Vinkkien poistaminen
            if (komento.equals("3")) {
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
            if (komento.equals("4")) {
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
            if (komento.equals("5")) {
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
            //Vinkkien merkitseminen luetuksi
            if (komento.equals("6")) {
                List<Vinkki> vinkit = dao.listaaLukemattomat();
                printListWithIndex(vinkit);
                String luetuksiMerkittava = io.readLine("Anna luetun vinkin numero: ");
                try {
                    int i = Integer.parseInt(luetuksiMerkittava);
                    if (i >= 0 && i < vinkit.size()) {
                        vinkit.get(i).setLuettu(true);
                        dao.paivita();
                        io.print("Vinkki merkitty luetuksi.");
                        io.print("\n");
                    } else {
                        io.print("Vinkin numeroa ei löydetty.\n");
                    }
                } catch (Exception e) {
                    io.print("Epäkelpo syöte.\n");
                }
            }

            //Vinkkien hakeminen
            if (komento.equals("7")) {
                String hakusana = io.readLine("Anna hakusana: ");
                // Ei tehdä mitään jos hakusana on pelkkää whitespacea
                if (!hakusana.trim().equals("")) {
                    List<Vinkki> vinkit = dao.hae(hakusana);
                    printList(vinkit);
                }
            }
        }
    }

    String paaKomennot = "Komennot: \n"
            + "1: Lisää vinkki \n"
            + "2: Listaa vinkit \n"
            + "3: Poista vinkki \n"
            + "4: Avaa linkki selaimessa \n"
            + "5: Muuta olemassaolevaa vinkkiä \n"
            + "6: Merkitse vinkki luetuksi \n"
            + "7: Hae vinkkejä hakusanalla \n"
            + "tyhjä sulkee sovelluksen \n";

    String lisaysKomennot = "Komennot: \n"
            + "1: Lisää kirja \n"
            + "2: Lisää blogi \n"
            + "3: Lisää kirja ISBN:llä \n"
            + "tyhjä palaa alkuun \n";

    String listausKomennot = "Komennot: \n"
            + "1: Listaa kirjat \n"
            + "2: Listaa blogit \n"
            + "3: Listaa kaikki vinkit \n"
            + "4: Listaa luetut \n"
            + "5: Listaa lukemattomat \n"
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

    private Kirja newBookByIsbn() {
        String isbn = io.readLine("Syötä kirjan ISBN-tunnus: ");
        KirjaApi api = new KirjaApi(isbn);
        String book = "";
        try {
            book = api.getBookFromApi();
        } catch (Exception e) {
            System.out.println("\n" + "Virhe: Haku epäonnistui" + "\n");
            return null;
        }
        ParserJson parser = new ParserJson(book);
        parser.initialParse();
        String kirjailija = parser.getParsedKirjailija();
        String nimi = parser.getParsedKirja();

        Kirja uusiKirja = new Kirja(kirjailija, nimi, isbn);

        return uusiKirja;
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
        String avattava = io.readLine("Anna avattavan blogin numero: ");
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
        dao.paivita();
    }

    private void merkitseLuetuksi(int i) {
        dao.getVinkki(i).setLuettu(true);
    }

}
