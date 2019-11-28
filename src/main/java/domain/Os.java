package domain;

public class Os {

    private String os;

    public Os() {
        this.os = System.getProperty("os.name").toLowerCase();
    }

    public String getOs() {
        return os;
    }

    @Override
    public String toString() {
        return "Kayttojarjestelmasi on " + this.os;
    }

}
