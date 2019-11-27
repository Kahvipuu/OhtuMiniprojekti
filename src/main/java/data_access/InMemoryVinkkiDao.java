package data_access;

import domain.Vinkki;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryVinkkiDao implements VinkkiDao {

    private List<Vinkki> vinkit;

    public InMemoryVinkkiDao() {
        vinkit = new ArrayList<Vinkki>();
    }

    @Override
    public List<Vinkki> listaaKaikki() {
        return vinkit;
    }

    @Override
    public void lisaa(Vinkki vinkki) {
        vinkit.add(vinkki);
    }

    @Override
    public List<Vinkki> listaaTyypin(String tyyppi) {
        return vinkit.stream()
                     .filter(vinkki -> tyyppi.equals(vinkki.getTyyppi()))
                     .collect(Collectors.toList());
    }
}
