package com.tutor.model.graphParser.DiagramStructure.Trignometry;

import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.graphicalPOJOObject.line.Line;

/**
 * Created by Vithusha on 8/6/2017.
 */
public class LineStructure  {

    private Line line;
    private Text lengthText;
    private Text lowerEndPointText;
    private Text higherEndPointText;

    public LineStructure(Line line){
        this.line = line;
    }
    public Line getLine() {
        return line;
    }

    public void setLengthText(Text lengthText) {
        this.lengthText = lengthText;
    }

    public Text getLengthText() {
        return lengthText;
    }

    public void setEndPointText(Text lowerEndPointText , Text higherEndPointText ) {
        this.lowerEndPointText = lowerEndPointText;
        this.higherEndPointText = higherEndPointText;

    }

    public Text getLowerEndPointText() {
        return lowerEndPointText;
    }

    public Text getHigherEndPointText() {
        return higherEndPointText;
    }
}
