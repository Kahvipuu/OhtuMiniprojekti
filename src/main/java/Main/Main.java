package Main;

import data_access.JSONFileVinkkiDao;
import data_access.InMemoryVinkkiDao;
import data_access.VinkkiDao;
import domain.*;
import ui.*;

public class Main {

    public static void main(String[] args) {
        
        //VinkkiDao dao = new InMemoryVinkkiDao();
        VinkkiDao dao = new JSONFileVinkkiDao("vinkit.json");
        UI kayttoliittyma = new KonsoliUI(dao);
        
        kayttoliittyma.kaynnista();
        
//        System.out.println("Hello world!");
        
    }


}
