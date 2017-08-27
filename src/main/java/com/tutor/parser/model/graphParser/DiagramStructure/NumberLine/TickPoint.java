package com.tutor.parser.model.graphParser.DiagramStructure.NumberLine;

import com.tutor.parser.model.graphicalPOJOObject.Text.Text;
import com.tutor.parser.model.graphicalPOJOObject.line.VerticalLine;

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
