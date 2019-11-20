package data_access;

import java.util.List;
import domain.Vinkki;

public interface VinkkiDao {
    List<Vinkki> listaaKaikki();
    void lisaa(Vinkki vinkki);
}
