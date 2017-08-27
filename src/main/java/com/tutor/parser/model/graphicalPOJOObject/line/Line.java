package com.tutor.parser.model.graphicalPOJOObject.line;

import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;

/**
 * Created by Madhavi Ruwandika on 6/3/2017.
 */
public class Line extends GraphicalImageComponent{


    int stroke_width;


    public Line(double x1, double y1, double x2, double y2, int stroke_width) {
        super(x1,y1,x2,y2);
        this.stroke_width = stroke_width;
    }


    public int getStroke_width() {
        return stroke_width;
    }

    public void setStroke_width(int stroke_width) {
        this.stroke_width = stroke_width;
    }

    public double getLowerestXCoordinate(){
        if( getX1() < getX2()){
            setHieght(getY1()-getY2());
            return getX1();
        } else {
            setHieght(getY2()-getY1());
            return getX2();
        }
    }

    public double getLowerestYCoordinate(){
        if(getY1() < getY2()){
            return getY1();
        }
        else return getY2();
    }

    public double getHighestXCoordinate(){
        if( getX1() < getX2()){
            return getX2();
        } else {
            return getX1();
        }
    }

}
