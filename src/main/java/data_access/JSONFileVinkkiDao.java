package data_access;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.FileWriter;
import domain.Kirja;
import domain.Vinkki;
import domain.Blogi;

public class JSONFileVinkkiDao extends InMemoryVinkkiDao {
    private String file;
    private Gson gson;

    public JSONFileVinkkiDao(String file) {
        this.file = file;
        gson = new Gson();

        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            if (reader.hasNext()) {
                JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
                for (JsonElement element : array) {
                    JsonObject obj = element.getAsJsonObject();
                    String tyyppi = gson.fromJson(obj.get("tyyppi"), String.class);
                    if (tyyppi.equals("kirja")) {
                        super.lisaa(gson.fromJson(obj, Kirja.class));
                    } else if (tyyppi.equals("blogi")) {
                        super.lisaa(gson.fromJson(obj, Blogi.class));
                    }
                }
            }
        } catch (java.io.FileNotFoundException e) {
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void lisaa(Vinkki uusiVinkki) {
        super.lisaa(uusiVinkki);
        kirjoitaJson();
    }
    
    @Override
    public void poista(int indeksi) {
        super.poista(indeksi);
        kirjoitaJson();
    }

    @Override
    public void paivita() {
        super.paivita();
        kirjoitaJson();
    }
    
    private void kirjoitaJson() {
        String json = gson.toJson(this.listaaKaikki());
        try{
            FileWriter kirjoittaja = new FileWriter(this.file);
            kirjoittaja.write(json);
            kirjoittaja.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
