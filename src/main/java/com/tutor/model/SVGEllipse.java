package com.tutor.model;

/**
 * Created by Wiranji Dinelka on 6/4/2017.
 */
public class SVGEllipse {
    private double cx;
    private double cy;
    private double rx;
    private double ry;

    private double f;

    private static double distanceTol = 20;

    public SVGEllipse(double cx, double cy, double rx, double ry){
        this.cx = cx;
        this.cx = cy;
        this.rx = rx;
        this.ry = ry;

    }

    public double getCX(){
        return this.cx;
    }

    public double getCY(){
        return this.cy;
    }

    public double getRX(){
        return this.rx;
    }

    public double getRY(){
        return this.ry;
    }
}
