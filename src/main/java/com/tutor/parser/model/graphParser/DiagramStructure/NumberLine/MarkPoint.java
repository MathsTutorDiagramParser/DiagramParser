package com.tutor.parser.model.graphParser.DiagramStructure.NumberLine;

import com.tutor.parser.model.graphicalPOJOObject.Circle.Circle;
import com.tutor.parser.model.graphicalPOJOObject.Text.Text;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class MarkPoint {

    public Circle circle;
    public Text text;
    public String endOFTheThickLine;
    private boolean isInfinity;
    public boolean isFilled;


    public MarkPoint(Circle circle, String endOFTheThickLine,boolean isInfinity) {
        this.circle = circle;
        this.text = null;
        this.endOFTheThickLine = endOFTheThickLine;
        this.isInfinity = isInfinity;

    }


    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public boolean isInfinity() {
        return isInfinity;
    }

    public void setInfinity(String colour) {
        if(colour=="rgb(255,255,255)"){
            this.isFilled=true;
        }
        else {
            this.isFilled=false;
        }
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
