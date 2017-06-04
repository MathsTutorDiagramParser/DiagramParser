package com.tutor.model;

/**
 * Created by Wiranji Dinelka on 6/4/2017.
 */
public class SVGRectangle {

    private double x;
    private double y;
    private double height;
    private double width;

    public SVGRectangle(double x, double y, double height, double width){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getHeight(){
        return this.height;
    }

    public double getWidth(){
        return this.width;
    }
}
