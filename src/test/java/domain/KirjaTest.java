package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KirjaTest {
    Kirja k;
    
    public KirjaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        k = new Kirja("Kaapo Kirjailija", "Nimike", "2345-5432");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void StringMuodostuuOikein() {
        assertEquals(k.toString(), "Kirjailija: Kaapo Kirjailija\nNimi: Nimike\nISBN: 2345-5432\n");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
