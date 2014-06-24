package com.dansd.termimap;

/**
 * Created by Francisco Dans on 02/05/2014.
 */
public class Place {
    private LatLng coordinates;
    private String name;
    private String address;

    public Place(final LatLng coordinates){
        this.coordinates = coordinates;
    }

    public Place(double latitude, double longitude) {
        this(new LatLng(latitude, longitude));
    }

    public Place() {

    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude(){
        return coordinates.getLatitude();
    }

    public Double getLongitude(){
        return coordinates.getLongitude();
    }

    @Override
    public String toString(){
        return name+", "+address;
    }
}
