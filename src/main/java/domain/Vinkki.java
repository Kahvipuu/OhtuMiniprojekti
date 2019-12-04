package domain;

public class Vinkki {

    //private Object tyyppi;
    private String tyyppi;
    private boolean luettu;

    public Vinkki(String tyyppi) {
        this.tyyppi = tyyppi;
        this.luettu = false;
    }

    public Vinkki() {
    }

    public String getTyyppi() {
        return this.tyyppi;
    }

    public boolean getLuettu() {
        return luettu;
    }

    public void setLuettu(Boolean luettu) {
        this.luettu = luettu;
    }

    @Override
    public String toString() {
        //return this.getTyyppi().toString();
        return this.getTyyppi();
    }

    public void setAll(String tieto1, String tieto2, String tieto3) {

    }

}
