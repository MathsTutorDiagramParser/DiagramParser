package com.tutor.model.graphParser.DiagramStructure;

import com.tutor.model.graphicalPOJOObject.Circle.Circle;
import com.tutor.model.graphicalPOJOObject.Text.Text;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class MarkPoint {

    public Circle circle;
    public Text text;
    public String EndOFTheThickLine;


    public MarkPoint(Circle circle, String endOFTheThickLine) {
        this.circle = circle;
        this.text = null;
        EndOFTheThickLine = endOFTheThickLine;
    }


    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }



}
