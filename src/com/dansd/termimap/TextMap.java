package com.dansd.termimap;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class TextMap {
    ArrayList<String> textedMap = new ArrayList<String>();
    static int reductionFactor = 8;
    private BufferedImage theImage;

    public TextMap(double lat, double lon, int zoom){
        try {
            URL url = new URL("http://ec2-54-234-130-165.compute-1.amazonaws.com:3000/style/"+getXYZ(lat, lon, zoom)+".png?id=tmstyle:///home/fdans/termimap.tm2&mtime=1402936723745");
            this.convertToText(ImageIO.read(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertToText(BufferedImage theImage){
        this.theImage = theImage;
        for(int y = 0; y<this.theImage.getHeight(); y = y+reductionFactor){
            String line = "";
            for(int x=0; x<this.theImage.getWidth(); x = x+ reductionFactor/2){
                Color average = this.getAverage(x, y);
                if(average.getRed()>0 && average.getBlue()>0 &&average.getGreen()>0) {
                    line += ANSI_BLACK+"\u2588";
                }
                else if(average.getRed()>0) {
                    line += ANSI_BLUE+"\u2588";
                }
                else{
                    line += ANSI_YELLOW+"\u2588";
                }
            }
            this.textedMap.add(line);

        }
        theImage.getHeight();
    }

    public void printImage(){
        for(String line:textedMap){
            System.out.println(line);
        }
        System.out.println(ANSI_RESET); //resets the console color
    }

    private Color getAverage(int _x, int _y) {
        int r =0, g=0, b=0;
        int size = 0;
        for(int x=_x; x<_x+reductionFactor/2; x++){
            for(int y=_y; y<_y+reductionFactor; y++){
                Color thisColor = new Color(theImage.getRGB(x,y));
                r+=thisColor.getRed();
                g+=thisColor.getGreen();
                b+=thisColor.getBlue();
                size++;
            }
        }
        Color average = new Color(r/size, g/size, b/size);
        return average;
    }
    private String getXYZ(double lat, double lon, int zoom){
        int xtile = (int)Math.floor( (lon + 180) / 360 * (1<<zoom) ) ;
        int ytile = (int)Math.floor( (1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1<<zoom) ) ;
        if (xtile < 0)
            xtile=0;
        if (xtile >= (1<<zoom))
            xtile=((1<<zoom)-1);
        if (ytile < 0)
            ytile=0;
        if (ytile >= (1<<zoom))
            ytile=((1<<zoom)-1);
        return("" + zoom + "/" + xtile + "/" + ytile);
    }
}
