package domain;

import java.io.IOException;

public class LinkinAvausProsessi {

    private Os os;
    private String linkki;
    ProcessBuilder builder;

    public LinkinAvausProsessi(String linkki) {
        this.linkki = linkki;
        this.os = new Os();
        this.builder = new ProcessBuilder();
    }

    public void avaaLinkki() throws IOException {
        if (isWindows()) {
            builder.command("cmd.exe", "/c", "start " + linkki);
            builder.start();
        } else if (isUnix()) {
            builder.command("bash", "-c", "xdg-open " + linkki);
            builder.start();
        } else if (isMac()) {
            builder.command("/bin/bash", "-c", "open " + linkki);
            builder.start();
        } else {
            System.out.println("Virhe! Avaaminen epäonnistui.");
        }
    }

    public boolean isWindows() {
        return (os.getOs().indexOf("win") >= 0);
    }

    public boolean isMac() {
        return (os.getOs().indexOf("mac") >= 0);
    }

    public boolean isUnix() {
        return (os.getOs().indexOf("nix") >= 0 || os.getOs().indexOf("nux") >= 0 || os.getOs().indexOf("aix") > 0);
    }

}