package domain;

public class Blogi extends Vinkki{
    
    private String kirjoittaja;
    private String aihe;
    private String osoite;
    
    public Blogi(String kirjoittaja, String aihe, String osoite){
        super("blogi");
        this.kirjoittaja = kirjoittaja;
        this.aihe = aihe;
        this.osoite = osoite;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    public void setKirjoittaja(String kirjoittaja) {
        this.kirjoittaja = kirjoittaja;
    }

    public String getAihe() {
        return aihe;
    }

    public void setAihe(String aihe) {
        this.aihe = aihe;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }
    
    @Override
    public String toString(){
        return "Kirjoittaja: " +kirjoittaja +"\nAihe: " +aihe +"\nOsoite: " +osoite +"\n";
    }

    
}
