package com.dansd.termimap;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean newMode = true;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if(newMode) {
                System.out.print("Enter a coordinate or the name of a region --->");
                parsePlace(scanner.next());
                newMode=false;
            }
            else while(!newMode){
                System.out.println("(Z)oom, (P)an, (N)ew search, (Q)uit?");
                String input = scanner.next();
                if(input.equals("Z")){
                    System.out.println("Enter new zoom level--->");
                }
                else if(input.equals("P")){
                    System.out.println("(N)orth, (S)outh, (E)ast, (W)est?");
                }
                else if(input.equals("N")){
                    newMode=true;
                }
                else if(input.equals("Q")){
                    break;
                }
            }

        }

    }

    private static void parsePlace(String input){
        if (input.contains(",")) {
            String[] parts = input.split(",");
            TextMap tm = new TextMap(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), 4);
        } else {
            LatLng ll = Geocoder.geocode(input);
            TextMap tm = new TextMap(ll.lat, ll.lon, 5);
        }
    }
}
