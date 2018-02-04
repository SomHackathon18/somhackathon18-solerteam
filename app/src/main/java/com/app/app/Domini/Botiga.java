package com.app.app.Domini;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Botiga {

    private int idBotiga;
    private String email;
    private String nomTenda;
    private double lat;
    private double lng;
    private int tipus;

    public Botiga() {
    }

    public Botiga(String email, String nomTenda, double lat, double lng, int tipus) {
        this.email = email;
        this.nomTenda = nomTenda;
        this.lat = lat;
        this.lng = lng;
        this.tipus = tipus;
    }

    public Botiga(int id_botiga, String email, String nom_tenda, double lat_bot, double lng_bot, int id_tipus) {
        this.idBotiga = id_botiga;
        this.email = email;
        this.nomTenda = nom_tenda;
        this.lat = lat_bot;
        this.lng = lng_bot;
        this.tipus = id_tipus;
    }

    public int getIdBotiga() {
        return idBotiga;
    }

    public void setIdBotiga(int idBotiga) {
        this.idBotiga = idBotiga;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomTenda() {
        return nomTenda;
    }

    public void setNomTenda(String nomTenda) {
        this.nomTenda = nomTenda;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getTipus() {
        return tipus;
    }

    public void setTipus(int tipus) {
        this.tipus = tipus;
    }
}
