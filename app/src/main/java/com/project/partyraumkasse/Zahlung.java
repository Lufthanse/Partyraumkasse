package com.project.partyraumkasse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Zahlung implements Comparable<Zahlung>{

    private String name;
    private String betrag;
    private String grund;
    private String datum;
    private String uhrzeit;
    private String zahlungsart;

    public Zahlung(String name, String betrag, String grund, String datum, String uhrzeit, String zahlungsart) {
        this.name = name;
        this.betrag = betrag;
        this.grund = grund;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.zahlungsart = zahlungsart;
    }

    public Zahlung() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBetrag() {
        return betrag;
    }

    public void setBetrag(String betrag) {
        this.betrag = betrag;
    }

    public String getGrund() {
        return grund;
    }

    public void setGrund(String grund) {
        this.grund = grund;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(String uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    public String getZahlungsart() {
        return zahlungsart;
    }

    public void setZahlungsart(String zahlungsart) {
        this.zahlungsart = zahlungsart;
    }

    public Date getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = this.getDatum();
        Date date = new Date();
        try {
            date = formatter.parse(dateString);
        }catch(ParseException ex){

        }
        return date;
    }

    @Override
    public int compareTo(Zahlung z){
        return getDate().compareTo(this.getDate());
    }

}
