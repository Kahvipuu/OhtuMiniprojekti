/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

import domain.Vinkki;
import java.io.File;
import org.junit.After;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Omistaja
 */
public class JSONFileVinkkiDAoTest {
    
    String file;
    VinkkiDao dao;
    Vinkki v;
    Object[] lisatytVinkit = new Object[2];
    
    @Before
    public void setUp() {
        file = "testivinkit.json";
        dao = new JSONFileVinkkiDao(file);
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
