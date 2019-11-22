package data_access;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import domain.Kirja;
import domain.Vinkki;
import java.util.*;

public class JSONFileVinkkiDao implements VinkkiDao {
    private String file;
    private List<Vinkki> vinkit;
    private Gson gson;

    public JSONFileVinkkiDao(String file) {
        this.file = file;
        vinkit = new ArrayList<>();
        gson = new Gson();

        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            if (reader.hasNext()) {
                JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
                for (JsonElement element : array) {
                    JsonObject obj = element.getAsJsonObject();
                    String tyyppi = gson.fromJson(obj.get("tyyppi"), String.class);
                    if (tyyppi.equals("kirja")) {
                        vinkit.add(gson.fromJson(obj, Kirja.class));
                    }
                }
            }
        } catch (java.io.FileNotFoundException e) {
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Vinkki> listaaKaikki() {
        return vinkit;
    }

    @Override
    public void lisaa(Vinkki uusiVinkki) {
        vinkit.add(uusiVinkki);

        String json = gson.toJson(vinkit);
        try{
            FileWriter kirjoittaja = new FileWriter(file);
            kirjoittaja.write(json);
            kirjoittaja.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

}
