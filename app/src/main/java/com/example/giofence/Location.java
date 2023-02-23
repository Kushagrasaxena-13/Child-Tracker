package com.example.giofence;

public class Location {
    String id;
    Object latLng;

    public Location() {
    }

    public Location(String id, Object latLng) {
        this.id = id;
        this.latLng = latLng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getLatLng() {
        return latLng;
    }

    public void setLatLng(Object latLng) {
        this.latLng = latLng;
    }
}
