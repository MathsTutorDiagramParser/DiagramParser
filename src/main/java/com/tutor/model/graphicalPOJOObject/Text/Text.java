package com.tutor.model.graphicalPOJOObject.Text;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

/**
 * Created by Madhavi Ruwandika on 6/4/2017.
 */
public class Text extends GraphicalImageComponent {

    double x;
    double y;
    String text;

    public Text(double x, double y, String text) {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
