package com.project.partyraumkasse;

public class Einkauf {

    private String beschreibung;
    private String id;

    public Einkauf(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Einkauf() {
    }

    public Einkauf(String beschreibung, String id){
        this.beschreibung = beschreibung;
        this.id  = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getId(){
        return id;
    }

    public void setBeschreibung(String beschreibung) {
        beschreibung = beschreibung;
    }
}
