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
}
