package data_access;

import domain.*;
import data_access.*;
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
}
