package com.tutor.model.graphicalSVGObject;

/**
 * Created by Wiranji Dinelka on 6/4/2017.
 */
public class SVGCircle extends SVGComponent {


    double cx;
    double cy;
    String fill;

    public SVGCircle(double cx, double cy, String fill) {
        this.cx = cx;
        this.cy = cy;
        this.fill = fill;
    }

    public double getCx() {
        return cx;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public double getCy() {
        return cy;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }
}
