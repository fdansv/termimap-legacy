package com.dansd.termimap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GeocoderResults {
    List<Place> resultList = new ArrayList<Place>();

    public GeocoderResults(String jsonString){
        parseString(jsonString);
    }

    protected void parseString(String jsonString) {
        JSONObject root = new JSONObject();
        try {
            root = new JSONObject(jsonString);
            extractPlaceList(root.getJSONArray("results"));
        } catch (JSONException e) {
            throw new IllegalArgumentException("Invalid JSON string. This is the string: " + jsonString);
        }

    }

    protected void extractPlaceList(JSONArray results) throws JSONException {
        List<Place> provisionalResults = new ArrayList<Place>();
        for(int i=0; i<results.length(); i++){
            Place thisPlace = new Place();
            JSONArray data = (JSONArray) results.get(i);
            JSONObject placeData = (JSONObject) data.get(0);
            thisPlace.setName(placeData.getString("name"));
            thisPlace.setCoordinates(new LatLng(placeData.getDouble("lat"), placeData.getDouble("lon")));
            String address = "";
            if(data.length()>1) {
                for (int j = 1; j < data.length(); j++) {
                    JSONObject thisData = (JSONObject) data.get(j);
                    address += thisData.getString("name");
                    if (j < data.length() - 1) {
                        address += ", ";
                    }
                }
            }
            thisPlace.setAddress(address);
            provisionalResults.add(thisPlace);
        }
        resultList = provisionalResults;
    }

    public List<Place> getResultList() {
        return resultList;
    }
}
