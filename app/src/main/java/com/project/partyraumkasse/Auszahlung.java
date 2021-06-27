package com.project.partyraumkasse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Auszahlung {
    private String name;
    private String betrag;
    private String grund;
    private String datum;
    private String uhrzeit;
    private String kennzeichen;
    private SimpleDateFormat formatterDate;
    private SimpleDateFormat formatterTime;

    public Auszahlung(String name, String betrag, String grund) {
        this.name = name;
        this.betrag = betrag;
        this.grund = grund;
        this.formatterDate = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        this.datum = formatterDate.format(date).toString();
        this.formatterTime = new SimpleDateFormat("hh:mm:ss");
        Date time = new Date();
        this.uhrzeit = formatterTime.format(time).toString();
        this.kennzeichen = "AZ";
    }

    public String getName() {
        return name;
    }

    public String getBetrag() {
        return betrag;
    }

    public String getGrund() {
        return grund;
    }

    public String getDatum() {
        return datum;
    }

    public String getUhrzeit() {
        return uhrzeit;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBetrag(String betrag) {
        this.betrag = betrag;
    }

    public void setGrund(String grund) {
        this.grund = grund;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setUhrzeit(String uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

}
