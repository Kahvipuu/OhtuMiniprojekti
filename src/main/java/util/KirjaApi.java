package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class KirjaApi {

    private HttpURLConnection con;
    private String isbn;
    private StringBuilder content;

    public KirjaApi(String isbn) {
        this.isbn = isbn;
    }

    public void setContent(StringBuilder content) {
        this.content = content;
    }

    public StringBuilder getContent() {
        return content;
    }

    public String getBookFromApi() {
        this.openCon();
        this.readData();
        this.closeCon();
        return this.getContent().toString();
    }

    public void openCon() {
        String address = "http://openlibrary.org/api/books?bibkeys=ISBN:";
        String format = "&format=json&jscmd=data";

        String url = address + isbn + format;

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("GET");
        } catch (Exception e) {
        }
    }

    public void closeCon() {
        con.disconnect();
    }

    public void readData() {

        content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {

            String line;

            while ((line = in.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        } catch (Exception e) {

        }
        this.setContent(content);
    }

}
