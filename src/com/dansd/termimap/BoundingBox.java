package com.dansd.termimap;

/**
 * Created by franciscodans on 18/06/2014.
 */
public class BoundingBox {
    private double n,s,e,w;

    public BoundingBox(double n, double s, double e, double w){
        this.n = n;
        this.w = w;
        this.e = e;
        this.s = s;
    }

    public double getN() {
        return n;
    }

    public double getS() {
        return s;
    }

    public double getE() {
        return e;
    }

    public double getW() {
        return w;
    }
}
