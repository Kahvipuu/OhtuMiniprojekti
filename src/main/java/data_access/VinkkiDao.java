package data_access;

import java.util.List;
import domain.Vinkki;

public interface VinkkiDao {
    List<Vinkki> listaaKaikki();
    void lisaa(Vinkki vinkki);
    List<Vinkki> listaaTyypin(String tyyppi);
    void poista(int i);
    Vinkki getVinkki(int i);
    public void paivita();
    List<Vinkki> listaaLuetut();
    List<Vinkki> listaaLukemattomat();
    List<Vinkki> hae(String hakusana);
}
