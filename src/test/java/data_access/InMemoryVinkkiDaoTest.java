package data_access;

import domain.*;
import data_access.*;
import java.util.List;
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
    Object[] lisatytVinkit = new Object[2];

    @Before
    public void setUp() {
        dao = new InMemoryVinkkiDao();
        v = new Vinkki();
    }
    
    
    @After
    public void tearDown() {
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
        Kirja k1 = new Kirja("Kekkonen", "Maailmani", "12345");
        Kirja k2 = new Kirja("Koivisto", "Tellervo Tellervo", "87498723");
        Blogi b = new Blogi("Blogaaja", "Toimii", "www.example.com");
        dao.lisaa(k1);
        dao.lisaa(b);
        dao.lisaa(k2);

        List<Vinkki> kirjat = dao.listaaTyypin("kirja");
        assertTrue(kirjat.contains(k1));
        assertTrue(kirjat.contains(k2));

        assertFalse(kirjat.contains(b));
    }
}
