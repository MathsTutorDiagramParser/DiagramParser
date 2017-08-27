package com.tutor.parser.model.graphicalPOJOObject.Rectangle;

import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.util.ObjectType;

public class Rectangle extends GraphicalImageComponent {
    Double height;
    Double width;

    public Rectangle(Double x, Double y, Double height, Double width){
        super(x,y,height);
        this.width=width;
        this.height=height;
        this.objectType= ObjectType.RECTANGLE;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setWidth(Double width) {
        this.width = width;
    }
}
