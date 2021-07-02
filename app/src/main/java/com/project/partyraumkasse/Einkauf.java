package com.project.partyraumkasse;

public class Einkauf {

    private String beschreibung;

    public Einkauf(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Einkauf() {
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        beschreibung = beschreibung;
    }
}
