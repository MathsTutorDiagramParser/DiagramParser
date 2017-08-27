package com.tutor.parser.model.graphicalSVGObject;

/**
 * Created by Wiranji Dinelka on 6/4/2017.
 */
public class SVGLine extends SVGComponent {
    double x1;
    double y1;
    double x2;
    double y2;
    int stroke_width;

    public SVGLine(double x1, double y1, double x2, double y2,int stroke_width){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.stroke_width = stroke_width;

    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public int getStroke_width() {
        return stroke_width;
    }

    public void setStroke_width(int stroke_width) {
        this.stroke_width = stroke_width;
    }
}
