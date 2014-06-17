package com.dansd.termimap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Francisco Dans on 17/06/2014.
 */
public class Geocoder {
    public static LatLng geocode(String place){
        String geocodingURL = "http://api.tiles.mapbox.com/v3/drivescribe.tm2-basemap/geocode/"+place+".json";
        JSONObject json = (JSONObject) JSONValue.parse(readUrl(geocodingURL));
        JSONArray mainResult = (JSONArray) ((JSONArray) (json.get("results"))).get(0);
        JSONObject mainObject = (JSONObject) mainResult.get(0);
        return new LatLng((Double)mainObject.get("lat"), (Double)mainObject.get("lon"));
    }

    private static String readUrl(String urlString) {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }
}