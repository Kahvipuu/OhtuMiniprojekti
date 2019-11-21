package ui;

import data_access.VinkkiDao;
import domain.Kirja;
import domain.Vinkki;
import java.util.List;
import java.util.Scanner;

public class KonsoliUI implements UI {
    
    private Scanner lukija;
    private VinkkiDao dao;
    
    public KonsoliUI(VinkkiDao dao) {
        this.dao = dao;
        this.lukija = new Scanner(System.in);
    }

    /*
    @Override
    public void kaynnista() {
        while(true) {
    
            tulostaKomennot();
    
            String komento = lukija.nextLine();
            
            if (komento.equals("")) {
                break;
            }
            
            if (komento.equals("1")) {
                Kirja kirja = uusiKirja();
                dao.lisaa(kirja);
            }
            
            if (komento.equals("2")) {
                List<Vinkki> vinkit = dao.listaaKaikki();
                for (Vinkki v : vinkit) {
                    System.out.println(v.toString());
                }
            }
            
        }
    }
    
    public void tulostaKomennot() {
        System.out.println("Komennot: \n"
                + "1: Lisää kirjavinkki \n"
                + "2: Listaa vinkit \n"
                + "tyhjä sulkee sovelluksen");
    }
    
    public Kirja uusiKirja() {
        System.out.print("Syötä kirjailijan nimi: ");
        String kirjailija = lukija.nextLine();
        System.out.print("Syötä kirjan nimi: ");
        String nimi = lukija.nextLine();
        System.out.print("Syötä kirjan ISBN-tunnus: ");
        String isbn = lukija.nextLine();
        Kirja uusiKirja = new Kirja(kirjailija, nimi, isbn);
        return uusiKirja;
    }
    */

    @Override
    public void kaynnista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
