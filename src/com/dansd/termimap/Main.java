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
                    System.exit(0);
                }
            }

        }

    }

    private static void parsePlace(String input){
        LatLng ll;
        if (input.contains(",")) {
            String[] parts = input.split(",");
            ll = new LatLng(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
        } else {
            ll = Geocoder.geocode(input);
        }
        TextMap tm = new TextMap(ll.lat, ll.lon, 5);
        tm.printImageOld();
    }
}
