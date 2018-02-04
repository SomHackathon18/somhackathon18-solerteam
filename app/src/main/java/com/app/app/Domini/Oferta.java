package com.app.app.Domini;

import org.springframework.jdbc.core.RowCallbackHandler;

public class Oferta {

    private int id_botiga;
    private String descripcio;
    private boolean enable;
    private int punts;

    public Oferta(int id_botiga, String descripcio, boolean enable, int punts) {
        this.id_botiga = id_botiga;
        this.descripcio = descripcio;
        this.enable = enable;
        this.punts = punts;
    }

    public Oferta() {
    }

    public int getId_botiga() {
        return id_botiga;
    }

    public void setId_botiga(int id_botiga) {
        this.id_botiga = id_botiga;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }
}
