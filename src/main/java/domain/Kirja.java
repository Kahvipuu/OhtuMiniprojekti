package domain;

public class Kirja extends Vinkki {

    private String kirjailija;
    private String kirjanNimi;
    private String isbn;

    public Kirja(String kirjailija, String nimi, String isbn) {
        super("kirja");
        this.kirjailija = kirjailija;
        this.kirjanNimi = nimi;
        this.isbn = isbn;
    }

    public Kirja() {
    }

    public void setKirjailija(String kirjailija) {
        this.kirjailija = kirjailija;
    }

    public void setKirjanNimi(String kirjanNimi) {
        this.kirjanNimi = kirjanNimi;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getKirjailija() {
        return kirjailija;
    }

    public String getKirjanNimi() {
        return kirjanNimi;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        String luettu = "";
        if (super.getLuettu()) {
            luettu = "Kirja luettu\n";
        }
        return "Kirjailija: " + this.getKirjailija() + "\n" + "Nimi: " + this.getKirjanNimi() + "\n" + "ISBN: " + this.getIsbn() + "\n" + luettu;
    }

    public int testi() {
        return 10;
    }

    @Override
    public void setAll(String kirjailija, String kirjanNimi, String isbn) {
        if (!kirjailija.isEmpty()) {
            this.kirjailija = kirjailija;
        }
        if (!kirjanNimi.isEmpty()) {
            this.kirjanNimi = kirjanNimi;
        }
        if (!isbn.isEmpty()) {
            this.isbn = isbn;
        }
    }
}
