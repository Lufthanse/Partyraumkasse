package com.project.partyraumkasse;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public class Event {

    public String datum;
    public String uhrzeit;
    public String bezeichnung;
    public String ort;
    public String hinweise;
    private SimpleDateFormat formatterDate;
    private SimpleDateFormat formatterTime;

    public Event(String bezeichnung, String datum, String uhrzeit, String ort, String hinweise){
        this.ort = ort;
        this.hinweise = hinweise;
        this.bezeichnung = bezeichnung;
        this.formatterDate = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        this.datum = formatterDate.format(date).toString();
        this.formatterTime = new SimpleDateFormat("HH:mm:ss");
        ZonedDateTime zdt = ZonedDateTime.now();
        Date time = new Date();
        formatterTime.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        this.uhrzeit = formatterTime.format(time).toString();
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

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getHinweise() {
        return hinweise;
    }

    public void setHinweise(String hinweise) {
        this.hinweise = hinweise;
    }

    public Event(){

    }
}
