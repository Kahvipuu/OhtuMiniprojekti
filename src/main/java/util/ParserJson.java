package util;

public class ParserJson {

    private String content;
    private String parsedKirja;
    private String parsedKirjailija;

    public ParserJson(String content) {
        this.content = content;
    }

    public String getParsedKirja() {
        return parsedKirja;
    }

    public String getParsedKirjailija() {
        return parsedKirjailija;
    }

    public void setParsedKirja(String parsedKirja) {
        this.parsedKirja = parsedKirja;
    }

    public void setParsedKirjailija(String parsedKirjailija) {
        this.parsedKirjailija = parsedKirjailija;
    }

    public void parseKirja(String title) {
        for (int i = 0; i < title.length(); i++) {
            char c = title.charAt(i);
            if (c == ':') {
                title = title.substring(i + 1).trim();
            }
        }
        title = title.replace("\"", " ");
        this.setParsedKirja(title);
    }

    public void parseKirjailija(String authors) {
        StringBuilder parsedAuthors = new StringBuilder();
        for (int i = 0; i < authors.length(); i++) {
            char ci = authors.charAt(i);
            if (ci == ':') {
                for (int j = i; j < authors.length(); j++) {
                    char cj = authors.charAt(j);
                    if (cj == '}') {
                        parsedAuthors.append(authors.substring(i + 1, j - 1)).append(" ");
                        break;
                    }
                }
            }
        }
        authors= parsedAuthors.toString().replace("\"", "").trim();
        this.setParsedKirjailija(authors);
    }

    public void initialParse() {
        String[] lines = content.split(",");
        String title = "";
        StringBuilder authors = new StringBuilder();
        boolean start = false;
        boolean end = false;
        for (String l : lines) {
            if (l.contains("title")) {
                title = l;
            }
            if (l.contains("authors")) {
                start = true;
            }
            if (start && !end) {
                if (l.contains("name")) {
                    authors.append(l).append(",");
                }
            }
            if (start && l.contains("]")) {
                end = true;
            }
        }
        parseKirja(title);
        parseKirjailija(authors.toString());
    }
}
