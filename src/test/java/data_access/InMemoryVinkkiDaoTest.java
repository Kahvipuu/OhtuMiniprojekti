package data_access;

import domain.*;
import data_access.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InMemoryVinkkiDaoTest {

    public InMemoryVinkkiDaoTest() {
    }

    VinkkiDao dao;
    Vinkki v;
    List<Vinkki> vinkit;
    Object[] lisatytVinkit = new Object[2];

    @Before
    public void setUp() {
        dao = new InMemoryVinkkiDao();
        v = new Vinkki();
        vinkit = luoVinkkeja();
    }
    
    
    @After
    public void tearDown() {
    }

    private List<Vinkki> luoVinkkeja() {
        List<Vinkki> vinkit = new ArrayList<>();
        vinkit.add(new Kirja("Kekkonen", "Maailmani", "12345"));
        vinkit.add(new Kirja("Koivisto", "Tellervo Tellervo", "87498723"));
        vinkit.add(new Blogi("Blogaaja", "Toimii", "www.example.com"));

        return vinkit;
    }

    @Test
    public void voiLisataVinkinListaan() {
        assertEquals(dao.listaaKaikki().size(), 0);
        dao.lisaa(v);
        assertEquals(dao.listaaKaikki().size(), 1);
    }
    
    @Test
    public void voiTulostaaListanKaikistaVinkeista() {
        dao.lisaa(v);
        dao.lisaa(v);
        lisatytVinkit[0] = v;
        lisatytVinkit[1] = v;
        
        
        Object[] testiTulostus = dao.listaaKaikki().toArray();
        assertArrayEquals(lisatytVinkit, testiTulostus);
    }

    @Test
    public void voiListataKaikkiTietynTyyppisetVinkit() {
        Kirja k1 = (Kirja)vinkit.get(0);
        Kirja k2 = (Kirja)vinkit.get(1);
        Blogi b = (Blogi)vinkit.get(2);

        dao.lisaa(k1);
        dao.lisaa(b);
        dao.lisaa(k2);

        List<Vinkki> kirjat = dao.listaaTyypinMukaan("kirja");
        assertTrue(kirjat.contains(k1));
        assertTrue(kirjat.contains(k2));

        assertFalse(kirjat.contains(b));
    }

    @Test
    public void hakemallaLoytyyOikeatVinkit() {
        Kirja k1 = (Kirja)vinkit.get(0);
        Kirja k2 = (Kirja)vinkit.get(1);
        Blogi b = (Blogi)vinkit.get(2);

        dao.lisaa(k1);
        dao.lisaa(b);
        dao.lisaa(k2);

        List<Vinkki> kirjat = dao.hae("ISBN");
        assertTrue(kirjat.contains(k1));
        assertTrue(kirjat.contains(k2));

        assertFalse(kirjat.contains(b));
    }
}
