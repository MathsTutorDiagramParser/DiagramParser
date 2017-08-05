package com.tutor.model.graphicalPOJOObject;

import com.tutor.model.util.ObjectType;

/**
 * Created by Madhavi Ruwandika on 6/11/2017.
 */
public class GraphicalImageComponent {

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x;
    private double y;

    private double hieght;
    public ObjectType objectType;

    public GraphicalImageComponent(ObjectType objectType) {
        this.objectType = objectType;
    }

    public GraphicalImageComponent(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.hieght = y1-y2;

    }

    public GraphicalImageComponent(double x, double y) {
        this.x = x;
        this.y = y;
        this.hieght = 0;
    }

    public GraphicalImageComponent() {

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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getLowerestXCoordinate(){
        return x;
    }

    public double getHieght() {
        return hieght;
    }

    public void setHieght(double hieght) {
        this.hieght = hieght;
    }

    public double getLowerestYCoordinate(){ return y; }
}
