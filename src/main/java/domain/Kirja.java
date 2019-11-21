package domain;

public class Kirja extends Vinkki {

    private String kirjailija;
    private String nimi;
    private String isbn;

    public Kirja(String kirjailija, String nimi, String isbn) {
        super(Kirja.class);
        this.kirjailija = kirjailija;
        this.nimi = nimi;
        this.isbn = isbn;
    }

    public Kirja() {
    }

    public void setKirjailija(String kirjailija) {
        this.kirjailija = kirjailija;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getKirjailija() {
        return kirjailija;
    }

    public String getNimi() {
        return nimi;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Kirjailija: " + this.getKirjailija() + "\n" + "Nimi: " + this.getNimi() +  "\n" + "ISBN: " + this.getIsbn() + "\n";
    }
    
    public int testi() {
        return 10;
    }
}
