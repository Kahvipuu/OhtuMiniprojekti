
package domain;

public  class Vinkki {
    private Object tyyppi;

    public Vinkki(Object tyyppi) {
        this.tyyppi = tyyppi;
    }

    public Vinkki() {
    }

    public Object getTyyppi() {
        return tyyppi;
    }

    @Override
    public String toString() {
        return this.getTyyppi().toString();
    }
    
    
    
    
    
    
}
