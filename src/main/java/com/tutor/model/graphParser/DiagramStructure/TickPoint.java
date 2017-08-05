package com.tutor.model.graphParser.DiagramStructure;

import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.graphicalPOJOObject.line.VerticalLine;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class TickPoint {

    private VerticalLine verticalLine;

    private Text text;

    public TickPoint(VerticalLine verticalLine) {
        this.verticalLine = verticalLine;
    }

    public VerticalLine getVerticalLine(){
        return verticalLine; }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }


}
