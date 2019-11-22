package data_access;

import domain.Vinkki;
import java.util.ArrayList;
import java.util.List;

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
}