/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import data_access.InMemoryVinkkiDao;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Omistaja
 */
public class KonsoliUITest {
    KonsoliUI konsoliUI;
    InMemoryVinkkiDao dao;
    
    @Before
    public void setUp() {
        dao = new InMemoryVinkkiDao();
        konsoliUI = new KonsoliUI(dao);
    }
    
    
    
    
    
}
