
package domain;

public class Vinkki {
    //private Object tyyppi;
    private String tyyppi;

    public Vinkki(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    public Vinkki() {
    }

    public String getTyyppi() {
        return this.tyyppi;
    }

    @Override
    public String toString() {
        //return this.getTyyppi().toString();
        return this.getTyyppi();
    }

    
    public void setAll(String tieto1, String tieto2, String tieto3) {
        
    }

}
