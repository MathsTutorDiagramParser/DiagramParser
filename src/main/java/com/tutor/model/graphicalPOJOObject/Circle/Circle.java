package com.tutor.model.graphicalPOJOObject.Circle;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.util.ObjectType;

/**
 * Created by Madhavi Ruwandika on 6/4/2017.
 */
public class Circle extends GraphicalImageComponent{


    String filled_colour ;

    public Circle(double x, double y, String filled_colour) {
        super(x,y);
        this.filled_colour = filled_colour;
        this.objectType = ObjectType.CIRCLE;
    }

    public String getFilled_colour() {
        return filled_colour;
    }

    public void setFilled_colour(String filled_colour) {
        this.filled_colour = filled_colour;
    }
}
