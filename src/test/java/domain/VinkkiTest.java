package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VinkkiTest {
    
    public VinkkiTest() {
    }
 
    Kirja k;
    
    @Before
    public void setUp() {
        k = new Kirja();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
        assertEquals(10, k.testi(), 000.1);
        
    }
}
