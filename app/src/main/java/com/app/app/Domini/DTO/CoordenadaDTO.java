package com.app.app.Domini.DTO;

public class CoordenadaDTO {

    private double lat;
    private double lng;

    public CoordenadaDTO() {
    }

    public CoordenadaDTO(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
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
}
