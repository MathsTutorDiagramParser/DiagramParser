package com.tutor.model.graphicalPOJOObject.Circle;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

/**
 * Created by Madhavi Ruwandika on 6/4/2017.
 */
public class Circle extends GraphicalImageComponent{

    double x;
    double y;
    String filled_colour ;

    public Circle() {
    }

    public Circle(double x, double y, String filled_colour) {
        this.x = x;
        this.y = y;
        this.filled_colour = filled_colour;
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

    public String getFilled_colour() {
        return filled_colour;
    }

    public void setFilled_colour(String filled_colour) {
        this.filled_colour = filled_colour;
    }
}
