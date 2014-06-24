package com.dansd.termimap;

/**
 * Created by Francisco Dans on 17/06/2014.
 */
public class LatLng {
    public double lat;
    public double lon;

    public LatLng(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String toString(){
        return lat+","+lon;
    }

    public Double getLatitude() {
        return lat;
    }

    public Double getLongitude() {
        return lon;
    }
}
